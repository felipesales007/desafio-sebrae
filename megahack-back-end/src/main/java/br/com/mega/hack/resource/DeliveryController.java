package br.com.mega.hack.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mega.hack.model.dto.DeliveryResponse;
import br.com.mega.hack.model.dto.DeliveryRouteResponse;
import br.com.mega.hack.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping
	public ResponseEntity<List<DeliveryResponse>> getDeliveryAvailable() {
		return ResponseEntity.ok(deliveryService.getDeliveryAvailable());
	}
	
	@GetMapping("deliveries/made")
	public ResponseEntity<List<DeliveryResponse>> getDeliveriesMade() {
		return ResponseEntity.ok(deliveryService.getDeliveriesMade());
	}
	
	@GetMapping("/accepted/order/{id}")
	public ResponseEntity<DeliveryRouteResponse> deliveryAccepted(@PathVariable Long id) {
		return ResponseEntity.ok(deliveryService.deliveryAccepted(id));
	}
	
	@PutMapping("/accomplished/order/{id}")
	public ResponseEntity<?> accomplishedDelivery(@PathVariable Long id) {
		deliveryService.accomplishedDelivery(id);
		return ResponseEntity.ok().build();
	}
}
