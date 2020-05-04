package br.com.mega.hack.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.mega.hack.model.dto.ProductRequest;
import br.com.mega.hack.model.dto.ProductResponse;
import br.com.mega.hack.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest request) {
		return ResponseEntity.created(null).body(productService.save(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProduct(id));
	}
	

	@GetMapping("/establishment/{id}")
	public ResponseEntity<?> getProducts(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductsByEstablishment(id));
	}

	@PutMapping("/{id}/active/{active}")
	public ResponseEntity<?> enableDisableProduct(@PathVariable Long id, @PathVariable Boolean active) {
		productService.EnableDisableProduct(id, active);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{id}/picture")
	public ResponseEntity<ProductResponse> uploadPicture(MultipartFile file, @PathVariable Long id) {
		return ResponseEntity.created(null).body(productService.uploadPicture(file, id));
	}
}
