package br.com.mega.hack.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.mega.hack.model.dto.security.UserInfoGoogle;

@FeignClient(name="google", url="${mega.hack.auth.google}")
public interface GoogleAuthClient {
	
	@GetMapping(path="/oauth2/v3/userinfo")
	ResponseEntity<UserInfoGoogle> userInfo(@RequestHeader(name = "Authorization") String token);

}
