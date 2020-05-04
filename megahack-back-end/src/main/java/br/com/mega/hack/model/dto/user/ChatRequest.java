package br.com.mega.hack.model.dto.user;

import javax.validation.constraints.NotNull;

import br.com.mega.hack.model.enums.ChatType;

public class ChatRequest {

	@NotNull
	private String message;

	@NotNull
	private ChatType type;
	
	@NotNull
	private String to;

	private Double value;
	
	
	
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public ChatType getType() {
		return type;
	}

	public void setType(ChatType type) {
		this.type = type;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to	 = to;
	}

}
