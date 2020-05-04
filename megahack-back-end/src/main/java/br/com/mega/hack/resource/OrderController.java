package br.com.mega.hack.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mega.hack.model.dto.OrderRequest;
import br.com.mega.hack.service.OrderService;
import feign.Body;

@RestController
@RequestMapping("/orders")
public class OrderController {
 
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<?> findListByUser(){
		return ResponseEntity.ok(orderService.findListByUser());
	}
	
	@GetMapping("/establishment/{id}")
	public ResponseEntity<?> findListByEstablishment(@PathVariable Long id){
		return ResponseEntity.ok(orderService.findListByEstablishment(id));
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<?> findListByUserAndStatus(@PathVariable String status){
		return ResponseEntity.ok(orderService.findListByUserAndStatus(status));
	}
	
	@GetMapping("/establishment/{id}/status/{status}")
	public ResponseEntity<?> findListByEstablishmentAndStatus(@PathVariable Long id,@PathVariable String status){
		return ResponseEntity.ok(orderService.findListByEstablishmentAndStatus(id, status));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> gerOrder(@PathVariable Long id){
		return ResponseEntity.ok(orderService.getOrder(id));
	}
	 
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
		return ResponseEntity.ok(orderService.createOrder(orderRequest));
	} 
	
	@GetMapping("/{id}/value")
	public ResponseEntity<?> value(@PathVariable Long id) {
		return ResponseEntity.ok(orderService.value(id));
	} 
	
	@PutMapping("/cancel/{id}")
	public ResponseEntity<?> cancelOrder(@PathVariable Long id){
		return ResponseEntity.ok(orderService.cancelOrder(id));
	}
	
	
}
