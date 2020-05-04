package br.com.mega.hack.model.dto.user;

import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.UserType;

public class UserResponse {

	private Long id;
	private String email;
	private String name;
	private String picture;
	private boolean emailVerified;
	private String uuid;
	private UserType type;

	public UserResponse(UserApp userApp) {
		this.id = userApp.getId();
		this.email = userApp.getEmail();
		this.name = userApp.getName();
		this.picture = userApp.getPicture();
		this.emailVerified = userApp.isEmailVerified();
		this.uuid = userApp.getUuid();
		this.type = userApp.getType();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

}
