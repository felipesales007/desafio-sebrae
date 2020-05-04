package br.com.mega.hack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import br.com.mega.hack.model.dto.MessageSocket;
import br.com.mega.hack.model.enums.TypeMessage;

@Service
public class WebSocketService {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public void sendMessageToUser(TypeMessage type, Object payload, String user) {
		messagingTemplate.convertAndSendToUser(user,"/queue/reply", new MessageSocket(type, payload));
	}

	
}
