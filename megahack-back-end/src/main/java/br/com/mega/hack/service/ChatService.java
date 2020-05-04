package br.com.mega.hack.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.mega.hack.model.Chat;
import br.com.mega.hack.model.Establishment;
import br.com.mega.hack.model.UserApp;
import br.com.mega.hack.model.dto.establishment.EstablishmentResponse;
import br.com.mega.hack.model.dto.user.ChatRequest;
import br.com.mega.hack.model.dto.user.ChatResponse;
import br.com.mega.hack.model.dto.user.UserResponse;
import br.com.mega.hack.model.enums.TypeMessage;
import br.com.mega.hack.repository.ChatRepository;
import br.com.mega.hack.repository.EstablishmentRepository;
import br.com.mega.hack.repository.UserRepository;
import br.com.mega.hack.util.JWTUtil;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;
	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private WebSocketService socketService;
	@Autowired
	private UserRepository userRepository;

	public ChatResponse saveAndSend(@Valid ChatRequest request) {
		String userUUID = jwtUtil.userUUID();
		Chat chat = new Chat(request, userUUID);
		Chat save = chatRepository.save(chat);
		ChatResponse chatResponse = new ChatResponse(save, userUUID);
		socketService.sendMessageToUser(TypeMessage.CHAT, chatResponse, chatResponse.getTo());
		socketService.sendMessageToUser(TypeMessage.CHAT, chatResponse, chatResponse.getFrom());
		return chatResponse;
	}

	public List<ChatResponse> findAllByUserUUID(String userUUIDSearch) {
		String userUUID = jwtUtil.userUUID();
		return chatRepository.findConversation(userUUID, userUUIDSearch).stream().map(c -> new ChatResponse(c, userUUID)).collect(Collectors.toList());
	}

	public List<ChatResponse> findAllTo(String to) {
		String userUUID = jwtUtil.userUUID();
		return chatRepository.findAllByFromOrTo(userUUID, to).stream().map(c -> new ChatResponse(c, userUUID)).collect(Collectors.toList());
	}

	public Set<ChatResponse> getLatestContactsUser(Integer page, Integer size) {
		String userUUID = jwtUtil.userUUID();
		PageRequest pg = PageRequest.of(page == null ? 0 : page, size == null ? 20 : size);

		Set<ChatResponse> latestContacts = chatRepository.findForLatestContactsTo(userUUID).stream().map(c -> new ChatResponse(c, userUUID))
				.collect(Collectors.toSet());

		List<String> uuids = latestContacts.stream().map(m -> {
			if (!m.getFrom().equals(userUUID)) {
				return m.getFrom();
			} else {
				return m.getTo();
			}
		}).collect(Collectors.toList());

		if (!uuids.isEmpty()) {
			Map<String, UserApp> users = userRepository.findByUUIDIn(uuids, pg).stream().collect(Collectors.toMap(UserApp::getUuid, UserApp::getThis));

			latestContacts.forEach(l -> {
				if (!l.getFrom().equals(userUUID)) {
					l.setUser(new UserResponse(users.get(l.getFrom())));
				} else {
					l.setUser(new UserResponse(users.get(l.getTo())));
				}
			});

		}

		return latestContacts;
	}

	public Set<ChatResponse> getLatestContactsEstablishment(Integer page, Integer size) {
		String userUUID = jwtUtil.userUUID();
		PageRequest pg = PageRequest.of(page == null ? 0 : page, size == null ? 20 : size);

		Set<ChatResponse> latestContacts = chatRepository.findForLatestContactsFrom(userUUID).stream().map(c -> new ChatResponse(c, userUUID))
				.collect(Collectors.toSet());

		List<String> uuids = latestContacts.stream().map(m -> {
			if (!m.getFrom().equals(userUUID)) {
				return m.getFrom();
			} else {
				return m.getTo();
			}
		}).collect(Collectors.toList());

		if (!uuids.isEmpty()) {
			Map<String, Establishment> users = establishmentRepository.findByUserUuidIn(uuids, pg).stream()
					.collect(Collectors.toMap(Establishment::userUUID, Establishment::getThis));

			latestContacts.forEach(l -> {

				if (!l.getFrom().equals(userUUID)) {
					Establishment establishment = users.get(l.getFrom());
					l.setEstablishment(new EstablishmentResponse(establishment));
					l.setUser(new UserResponse(establishment.getUser()));

				} else {
					Establishment establishment = users.get(l.getTo());
					l.setEstablishment(new EstablishmentResponse(establishment));
					l.setUser(new UserResponse(establishment.getUser()));
				}
			});

		}

		return latestContacts;
	}

}
