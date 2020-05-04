package br.com.mega.hack.config.websocket;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import br.com.mega.hack.util.JWTUtil;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
		String token = (String) attributes.get("token");
		String uuid = new  JWTUtil().userUUID(token);
		return new StompPrincipal(uuid);
	}
	
}
