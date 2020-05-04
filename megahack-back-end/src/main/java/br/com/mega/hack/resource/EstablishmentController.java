package br.com.mega.hack.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.mega.hack.model.dto.establishment.EstablishmentRequest;
import br.com.mega.hack.model.dto.establishment.EstablishmentResponse;
import br.com.mega.hack.service.EstablishmentService;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

	@Autowired
	private EstablishmentService establishmentService;

	@GetMapping
	public ResponseEntity<List<EstablishmentResponse>> getAll() {
		return ResponseEntity.ok(establishmentService.getAll());
	}
	
	@GetMapping("/neighborhood/{id}")
	public ResponseEntity<List<EstablishmentResponse>> getAllNeighborhood(@PathVariable String id) {
		return ResponseEntity.ok(establishmentService.getAllNeighborhood(id));
	}
	
	@GetMapping("/uuid/{uuid}")
	public ResponseEntity<EstablishmentResponse> getAllUuid(@PathVariable String uuid) {
		return ResponseEntity.ok(establishmentService.getAllUuid(uuid));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstablishmentResponse> getAllById(@PathVariable Long id) {
		return ResponseEntity.ok(establishmentService.getAllByUserId(id));
	}

	@PostMapping
	public ResponseEntity<EstablishmentResponse> save(@Valid @RequestBody EstablishmentRequest establishmentRequest) {
		return ResponseEntity.created(null).body(establishmentService.save(establishmentRequest));
	}
	
	@PostMapping("/{id}/picture")
	public ResponseEntity<EstablishmentResponse> uploadPicture(MultipartFile file, @PathVariable Long id) {
		return ResponseEntity.created(null).body(establishmentService.uploadPicture(file, id));
	}

}
