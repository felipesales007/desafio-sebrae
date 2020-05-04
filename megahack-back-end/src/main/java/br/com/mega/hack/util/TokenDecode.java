package br.com.mega.hack.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDecode {

	@JsonProperty("user_id")
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
