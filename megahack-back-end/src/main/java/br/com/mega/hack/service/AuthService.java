package br.com.mega.hack.service;

import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.mega.hack.client.GoogleAuthClient;
import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.dto.security.PrincipalUser;
import br.com.mega.hack.model.dto.security.UserInfoGoogle;
import br.com.mega.hack.model.enums.Role;
import br.com.mega.hack.repository.UserRepository;
import br.com.mega.hack.security.TokenProvider;
import br.com.mega.hack.security.exception.CustomOauthException;

@Service
public class AuthService {
	@Autowired
	private UserRepository usuarioRepository;
	@Autowired
	private TokenProvider tokenProvider;
	@Autowired
	private GoogleAuthClient googleAuthClient;
	@Autowired
	private BCryptPasswordEncoder encode;

	public OAuth2AccessToken autenticate(Role role) {
		ResponseEntity<UserInfoGoogle> userInfo = null;

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getHeader("auth-google-token");

		if (Objects.isNull(token)) {
			throw new CustomOauthException("Bearer");
		}

		try {
			userInfo = googleAuthClient.userInfo("Bearer " + token);
		} catch (Exception e) {
			throw new CustomOauthException("Error!");
		}

		return updateUser(userInfo.getBody(), role);

	}

	public OAuth2AccessToken updateUser(UserInfoGoogle infoGoogle, Role role) {
		try {
			UserApp user = usuarioRepository.findByEmail(infoGoogle.getEmail()).orElse(new UserApp());
			if (Objects.isNull(user.getId())) {
				createUser(infoGoogle, user, role);
			} else {
				user.setPicture(infoGoogle.getPicture());
				user.setEmailVerified(infoGoogle.getEmailVerifield());
			}
			usuarioRepository.save(user);
			PrincipalUser principalUser = new PrincipalUser().newUser(user);

			return tokenProvider.createToken(principalUser);
		} catch (Exception e) {
			throw new CustomOauthException("Error!");
		}
	}

	public void createUser(UserInfoGoogle infoGoogle, UserApp user, Role role) {
		user.setEmail(infoGoogle.getEmail());
		user.setPermission(role);
		user.setPicture(infoGoogle.getPicture());
		user.setPassword(encode.encode(UUID.randomUUID().toString()));
		user.setName(infoGoogle.getName());
	}

}
