package br.com.mega.hack.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.mega.hack.model.dto.user.ChatRequest;
import br.com.mega.hack.model.enums.ChatType;

@Entity
@Table(name = "chat")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String message;

	@Enumerated(EnumType.STRING)
	@Column(name = "chat_type")
	private ChatType chatType;

	private LocalDateTime dateTime;

	@Column(name = "to_user")
	private String to;

	@Column(name = "from_user")
	private String from;

	private Double value;

	public Chat() {

	}

	public Chat(ChatRequest request, String from) {
		this.message = request.getMessage();
		this.chatType = request.getType();
		this.to = request.getTo();
		this.from = from;
		this.dateTime = LocalDateTime.now();
		this.value = request.getValue();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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

	public void setMessage(String menssage) {
		this.message = menssage;
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

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

}