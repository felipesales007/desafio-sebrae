package br.com.mega.hack.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mega.hack.model.DeliveryOrder;
import br.com.mega.hack.model.Order;
import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.dto.DeliveryResponse;
import br.com.mega.hack.model.dto.DeliveryRouteResponse;
import br.com.mega.hack.model.enums.StatusOrder;
import br.com.mega.hack.repository.DeliveryOrderRepository;
import br.com.mega.hack.repository.OrderRepository;
import br.com.mega.hack.util.JWTUtil;

@Service
public class DeliveryService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private DeliveryOrderRepository deliveryOrderRepository;
	
	@Autowired
	private OrderService orderService;
	
	public List<DeliveryResponse> getDeliveryAvailable(){
		return  orderRepository.findByStatus(StatusOrder.PRONTO)
				.stream().map(o -> new DeliveryResponse(o))
				.collect(Collectors.toList());
	}
	
	public DeliveryRouteResponse deliveryAccepted(Long idOrder) {
		
		Order order =  orderService.deliveryTheWay(idOrder);
		
		DeliveryOrder deliveryOrder =  new DeliveryOrder();
		deliveryOrder.setOrder(new Order(order.getId()));
		deliveryOrder.setDeliveryman(new UserApp(jwtUtil.userId()));
		deliveryOrder.setDelivered(false);
		deliveryOrderRepository.save(deliveryOrder);
		
		return new DeliveryRouteResponse(order);
	}
	
	public void accomplishedDelivery(Long idOrder) {
		orderService.accomplishedDelivery(idOrder);
		DeliveryOrder deliveryOrder =  new DeliveryOrder();
		deliveryOrder.setOrder(new Order(idOrder));
		deliveryOrder.setDeliveryman(new UserApp(jwtUtil.userId()));
		deliveryOrder.setDelivered(true);
		deliveryOrderRepository.save(deliveryOrder);	
	}

	public List<DeliveryResponse> getDeliveriesMade() {
		return  orderRepository.findByDeliveryAndStatus(jwtUtil.userId(),StatusOrder.ENTREGUE)
				.stream().map(o -> new DeliveryResponse(o))
				.collect(Collectors.toList());
	}
	
}
