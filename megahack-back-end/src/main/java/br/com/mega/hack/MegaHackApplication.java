package br.com.mega.hack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import br.com.mega.hack.config.ConfigProperty;
import br.com.mega.hack.service.EmailService;
import br.com.mega.hack.service.SmtpEmailService;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperty.class)
@EnableFeignClients
public class MegaHackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegaHackApplication.class, args);
	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	

}
