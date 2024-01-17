package com.smit.homework.secretcontacts;


import java.lang.System.Logger.Level;
import java.net.InetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import com.smit.homework.secretcontacts.model.ProjectLogger;


@SpringBootApplication
public class SecretContactsApplication implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private ProjectLogger logger;

	@Value("${secret.contacts.api.server.port}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(SecretContactsApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.log(Level.INFO, "Spring Boot Secret Contacts API running on "
				+ InetAddress.getLoopbackAddress().getHostAddress() + ":" + serverPort);
	}
}