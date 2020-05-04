package br.com.mega.hack.model.dto;

import br.com.mega.hack.model.enums.TypeMessage;

public class MessageSocket {
	public TypeMessage type;
	public Object payload;
	
	public MessageSocket (TypeMessage type,  Object payload) {
		this.payload = payload;
		this.type = type;
	}
}
