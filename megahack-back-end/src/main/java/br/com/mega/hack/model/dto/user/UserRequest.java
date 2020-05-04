package br.com.mega.hack.model.dto.user;

import javax.validation.constraints.NotNull;

import br.com.mega.hack.model.UserType;
import br.com.mega.hack.model.enums.Role;

public class UserRequest {

	private Long id;

	@NotNull
	private String email;

	@NotNull
	private String name;

	private Role permission;

	private String telefone;

	@NotNull
	private String password;

	@NotNull
	private UserType type;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public Role getPermission() {
		return permission;
	}

	public void setPermission(Role permission) {
		this.permission = permission;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}
