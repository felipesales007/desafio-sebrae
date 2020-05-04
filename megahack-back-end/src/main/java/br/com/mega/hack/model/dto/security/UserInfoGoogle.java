package br.com.mega.hack.model.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoGoogle {

	private String sub;
	private String name;
	private String email;
	private String picture;

	@JsonProperty(value = "email_verified")
	private Boolean emailVerifield;

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEmailVerifield() {
		return emailVerifield;
	}

	public void setEmailVerifield(Boolean emailVerifield) {
		this.emailVerifield = emailVerifield;
	}

}
