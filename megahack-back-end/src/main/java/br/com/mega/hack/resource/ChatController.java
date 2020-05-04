package br.com.mega.hack.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mega.hack.model.dto.user.ChatRequest;
import br.com.mega.hack.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody ChatRequest request) {
		return ResponseEntity.created(null).body(chatService.saveAndSend(request));
	}

	@GetMapping("/latest-contacts/users")
	public ResponseEntity<?> lastestContacts(Integer page, Integer size) {
		return ResponseEntity.ok(chatService.getLatestContactsUser(page, size));
	}
	
	@GetMapping("/latest-contacts/establishment")
	public ResponseEntity<?> lastestContactsEstablishment(Integer page, Integer size) {
		return ResponseEntity.ok(chatService.getLatestContactsEstablishment(page, size));
	}
	
	@GetMapping("/{userUUID}")
	public ResponseEntity<?> findAll(@PathVariable String userUUID) {
		return ResponseEntity.ok(chatService.findAllByUserUUID(userUUID));
	}

	@GetMapping("/to/{id}")
	public ResponseEntity<?> getTo(@PathVariable String id) {
		return ResponseEntity.ok(chatService.findAllTo(id));
	}
}
