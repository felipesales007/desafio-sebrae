package br.com.mega.hack.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.dto.security.PrincipalUser;
import br.com.mega.hack.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApp user = userRepository.findByEmail(username).orElse(null);
			if(Objects.nonNull(user))
				return new PrincipalUser(user);
		throw new UsernameNotFoundException("Usuario nao encontrado");
	}
}
