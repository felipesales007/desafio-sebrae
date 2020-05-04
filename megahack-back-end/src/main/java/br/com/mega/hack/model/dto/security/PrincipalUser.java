package br.com.mega.hack.model.dto.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.mega.hack.model.UserApp;

public class PrincipalUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String password;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean enabled;
	private boolean emailVerified;
	private String uuid;

	public PrincipalUser(UserApp user) {
		this.id = user.getId();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getPermission().getRole()));
		this.enabled = user.isEnabled();
		this.emailVerified = user.isEmailVerified();
		this.uuid = user.getUuid();
	}

	public PrincipalUser newUser(UserApp user) {
		this.id = user.getId();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		this.enabled = user.isEnabled();
		this.emailVerified = user.isEmailVerified();
		this.uuid = UUID.randomUUID().toString();
		return this;
	}

	public PrincipalUser() {

	}
	
	

	public String getUuid() {
		return uuid;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

}
