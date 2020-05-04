package br.com.mega.hack.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mega.hack.model.Chat;
import br.com.mega.hack.model.Establishment;
import br.com.mega.hack.model.Order;
import br.com.mega.hack.model.OrderItems;
import br.com.mega.hack.model.Product;
import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.dto.OrderItemsResponse;
import br.com.mega.hack.model.dto.OrderRequest;
import br.com.mega.hack.model.dto.OrderResponse;
import br.com.mega.hack.model.dto.ProductProjection;
import br.com.mega.hack.model.dto.user.ChatRequest;
import br.com.mega.hack.model.dto.user.ChatResponse;
import br.com.mega.hack.model.enums.ChatType;
import br.com.mega.hack.model.enums.StatusOrder;
import br.com.mega.hack.model.enums.TypeMessage;
import br.com.mega.hack.repository.EstablishmentRepository;
import br.com.mega.hack.repository.OrderItemsRepository;
import br.com.mega.hack.repository.OrderRepository;
import br.com.mega.hack.service.exception.ObjectNotFoundException;
import br.com.mega.hack.util.JWTUtil;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private WebSocketService socketService;
	@Autowired
	private ChatService chatService;
	@Autowired
	private EstablishmentRepository establishmentRepository;

	public OrderResponse createOrder(OrderRequest orderRequest) {
		Order newOrder = orderRequest.getNewOrder();
		newOrder.setUser(new UserApp(jwtUtil.userId()));
		Order order = orderRepository.save(newOrder);

		List<OrderItems> orderItems = orderRequest.getOrderItems().stream().map(o -> new OrderItems(new Product(o.getProduct()), order, o.getAmount()))
				.collect(Collectors.toList());

		OrderResponse orderResponse = new OrderResponse(order);
		orderItemsRepository.saveAll(orderItems);
				
		ChatRequest request = new ChatRequest();
		Establishment establishment = establishmentRepository.findOneById(orderRequest.getEstablishment());
		request.setMessage(orderResponse.getId().toString());
		request.setType(ChatType.ORDER);
		request.setTo(establishment.getUser().getUuid());
		
		Double sum = orderItems.stream().mapToDouble(OrderItems::getAmount)
			    .reduce(0, (i, v) -> (i + v) + 5.99);
		
		request.setValue(sum);
		    
		ChatResponse chat = chatService.saveAndSend(request);

		socketService.sendMessageToUser(TypeMessage.ORDER, chat, chat.getTo());
		socketService.sendMessageToUser(TypeMessage.ORDER, chat, chat.getFrom());
		return orderResponse;
	}

	public OrderResponse cancelOrder(Long id) {
		Order order = orderRepository.getOne(id);
		order.setStatus(StatusOrder.CANCELADO);
		return new OrderResponse(orderRepository.save(order));
	}

	public OrderResponse getOrder(Long id) {
		OrderResponse map = orderRepository.findById(id).map(o -> new OrderResponse(o))
				.orElseThrow(() -> new ObjectNotFoundException());
		List<OrderItems> orderItems = orderItemsRepository.findByOrderId(id);
		
		List<OrderItemsResponse> items = orderItems.stream().map(o -> new OrderItemsResponse(o))
				.collect(Collectors.toList());
		
		map.setOrderItems(items);
		
		return map;
		
	}

	public List<OrderResponse> findListByUser() {
		return orderRepository.findListByUser(jwtUtil.userId()).stream().map(o -> new OrderResponse(o)).collect(Collectors.toList());
	}

	public List<OrderResponse> findListByEstablishment(Long id) {
		return orderRepository.findListByEstablishment(id).stream().map(o -> new OrderResponse(o)).collect(Collectors.toList());
	}

	public List<OrderResponse> findListByUserAndStatus(String status) {
		return orderRepository.findListByUserAndStatus(jwtUtil.userId(), StatusOrder.valueOf(status)).stream().map(o -> new OrderResponse(o))
				.collect(Collectors.toList());
	}

	public List<OrderResponse> findListByEstablishmentAndStatus(Long id, String status) {
		return orderRepository.findListByEstablishmentAndStatus(id, StatusOrder.valueOf(status)).stream().map(o -> new OrderResponse(o))
				.collect(Collectors.toList());
	}
	
	public void accomplishedDelivery(long idOrder) {
		Order order =  orderRepository.findById(idOrder).get();
		order.setStatus(StatusOrder.ENTREGUE);
		orderRepository.save(order);
	}
	
	public Order deliveryTheWay(Long idOrder) {
		Order order =  orderRepository.findById(idOrder).get();
		order.setStatus(StatusOrder.A_CAMINHO);
		return orderRepository.save(order);
		
	}

	public ProductProjection value(Long id) {
		return orderItemsRepository.getValue(id);
	}
}
