package br.com.mega.hack.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mega.hack.model.enums.Role;
import br.com.mega.hack.service.AuthService;

@RestController
@RequestMapping("/oauth2")
public class AuthController {
	@Autowired
	private AuthService authService;

	@GetMapping("/google/login")
	public ResponseEntity<?> authenticateUser(Role role) {

		OAuth2AccessToken token = authService.autenticate(role);

		return ResponseEntity.ok().body(token);
	}

}
