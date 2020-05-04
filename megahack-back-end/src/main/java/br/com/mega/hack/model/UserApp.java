package br.com.mega.hack.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.mega.hack.model.dto.security.UserInfoGoogle;
import br.com.mega.hack.model.dto.user.UserRequest;
import br.com.mega.hack.model.enums.Role;

@Entity
@Table(name = "user")
public class UserApp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email(message = "Email Inválido")
	@NotEmpty(message = "Preenchimento obrigatório")
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "permission")
	private Role permission;

	private String telefone;

	private UserType type;

	private boolean enabled;

	private boolean emailVerified;

	private String picture;

	private String uuid;

	public UserApp(UserRequest request) {
		this.id = request.getId();
		this.email = request.getEmail();
		this.name = request.getName();
		this.enabled = true;
		this.permission = request.getPermission();
		this.password = request.getPassword();
		this.uuid = UUID.randomUUID().toString();
		this.type = request.getType();
	}

	public UserApp(UserInfoGoogle infoGoogle) {
		this.email = infoGoogle.getEmail();
		this.picture = infoGoogle.getPicture();
		this.password = "$2a$10$kSnJNBPTeSsA6rCyAPg9kulmasLMY7dhqN5qZlEyTjOv2MVEpMNNmdsxsddd222";
		this.name = infoGoogle.getName();
	}

	public UserApp getThis() {
		return this;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void emailVerified() {
		this.emailVerified = true;
		this.enabled();
	}

	public void enabled() {
		this.enabled = true;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public UserApp(Long id) {
		this.id = id;
	}

	public Role getPermission() {
		return permission;
	}

	public void setPermission(Role permission) {
		this.permission = permission;
	}

	public UserApp() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}