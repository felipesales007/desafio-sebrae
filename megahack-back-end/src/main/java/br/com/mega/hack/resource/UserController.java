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

import br.com.mega.hack.model.dto.user.UserInfo;
import br.com.mega.hack.model.dto.user.UserRequest;
import br.com.mega.hack.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public void salvar(@Valid @RequestBody UserRequest request) {
		userService.register(request);
	}

	@GetMapping("/user-info")
	public ResponseEntity<?> tokenInfo() {

		UserInfo userInfo = userService.userInfo();

		return ResponseEntity.ok().body(userInfo);
	}
	
	@GetMapping("/uuid/{uuid}")
	public ResponseEntity<?> geyByUUID(@PathVariable String uuid) {

		
		
		return ResponseEntity.ok().body(userService.getByUUID(uuid));
	}
	

	@PostMapping("/verify-email/{email}")
	public void sendVerifyEmail(@PathVariable String email) {
		userService.sendEmailVerifyEmail(email);
	}

	@PutMapping("/verify-email")
	public void verifyEmail(String token) {
		userService.verifyEmail(token);
	}

	@GetMapping("/exists/{email}")
	public ResponseEntity<?> existsEmail(@PathVariable String email) {
		return ResponseEntity.ok(userService.existEmail(email));
	}

}
