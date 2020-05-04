package br.com.mega.hack.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.mega.hack.model.UserApp;

public abstract class AbstractEmailService implements EmailService {
	@Value("${default.sender}")
	private String sender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender javaMailSender;
	
//	@Override
//	public void sendConfirmationRegister(UserMoney user) {
//		SimpleMailMessage sm = prepareSimlpleMailMessageFromPedido(user);
//		sendEmail(sm);
//	}
	
	@Override
	public void sendConfirmationRegisterHtml(UserApp obj, String token) {
		try {
			MimeMessage mm = prepareMimeMessageMessageFromPedido(obj, token);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
	}
	
	protected MimeMessage prepareMimeMessageMessageFromPedido(UserApp obj, String token) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(obj.getEmail());
		helper.setFrom(sender);
		helper.setSubject("Verificaçao de e-mail");
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(obj.toString());
		helper.setText(htmlFromTemplatePedido(obj, token), true);
		return mimeMessage;
	};
//	
	protected String htmlFromTemplatePedido(UserApp user, String token) {
		Context context  = new Context();
		context.setVariable("user", user);
		context.setVariable("token", token);
		context.setVariable("yourMoneyUi", "");
		return templateEngine.process("email/verify-email", context);
	}

}