package br.com.mega.hack.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.mega.hack.model.UserApp;

public interface EmailService {
	//void sendConfirmationRegister(UserMoney obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendConfirmationRegisterHtml(UserApp obj, String token);
	
	void sendHtmlEmail(MimeMessage msg);

//	void sendNewPasswordEmail(UserMoney money);
}
