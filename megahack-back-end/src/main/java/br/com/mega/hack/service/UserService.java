package br.com.mega.hack.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.dto.user.UserInfo;
import br.com.mega.hack.model.dto.user.UserRequest;
import br.com.mega.hack.model.dto.user.UserResponse;
import br.com.mega.hack.repository.UserRepository;
import br.com.mega.hack.service.exception.ObjectNotFoundException;
import br.com.mega.hack.util.JWTUtil;

@Service
public class UserService {
	@Autowired
	private UserRepository usuarioRepository;
	@Autowired
	private BCryptPasswordEncoder encode;
	@Autowired
	private EmailService emailService;
	@Autowired
	private JWTUtil jwtUtil;
	
	public void register(UserRequest request) {
		UserApp user = new UserApp(request);
		user.setPassword(encode.encode(user.getPassword()));
		usuarioRepository.save(user);
		
		//generateTokenAndSendEmail(user);
	}
	
	public Boolean existEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	public void sendEmailVerifyEmail(String email) {
		UserApp user = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		generateTokenAndSendEmail(user);
	}
	
	public void generateTokenAndSendEmail(UserApp user) {
		String token = jwtUtil.generateToken(user.getEmail());
		emailService.sendConfirmationRegisterHtml(user, token);	
	}
	
	
	public void verifyEmail(String token) {
		String email = jwtUtil.userName(token);
		
		if(Objects.nonNull(email)) {
			UserApp user = usuarioRepository.findByEmail(email)
					.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
			user.emailVerified();
			usuarioRepository.save(user);
		}
	}

	public UserInfo userInfo() {
		UserApp user = usuarioRepository.findOneById(jwtUtil.userId());
		
		return new UserInfo.Builder().withUser(user).build();
	}
	
	public UserResponse getByUUID(String uuid) {
		UserApp user = usuarioRepository.findOneByUuid(uuid);
		
		return new UserResponse(user);
	}

	
}
