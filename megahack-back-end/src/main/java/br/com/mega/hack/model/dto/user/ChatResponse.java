package br.com.mega.hack.model.dto.user;

import java.time.LocalDateTime;

import br.com.mega.hack.model.Chat;
import br.com.mega.hack.model.Establishment;
import br.com.mega.hack.model.dto.establishment.EstablishmentResponse;
import br.com.mega.hack.model.enums.ChatType;

public class ChatResponse {

	private Long id;
	private String message;
	private ChatType chatType;
	private String to;
	private String from;
	private LocalDateTime dateTime;
	private Double value;

	private UserResponse user;
	private EstablishmentResponse establishment;

	public ChatResponse(Chat chat, String userUUID) {
		this.id = chat.getId();
		this.message = chat.getMessage();
		this.chatType = chat.getChatType();
		this.from = chat.getFrom();
		this.to = chat.getTo();
		this.dateTime = chat.getDateTime();
		this.value = chat.getValue();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public EstablishmentResponse getEstablishment() {
		return establishment;
	}

	public void setEstablishment(EstablishmentResponse establishment) {
		this.establishment = establishment;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMenssage(String message) {
		this.message = message;
	}

	public ChatType getChatType() {
		return chatType;
	}

	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
