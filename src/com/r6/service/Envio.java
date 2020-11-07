package com.r6.service;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Envio {
	private final Properties properties = new Properties();

	private String password = "Julz2619";

	private Session session;

	private void init() {

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", 25);
		properties.put("mail.smtp.mail.sender", "julsluz1926@gmail.com");
		properties.put("mail.smtp.user", "julsluz1926@gmail.com");
		properties.put("mail.smtp.auth", "true");

		session = Session.getDefaultInstance(properties);
	}

	public void sendEmail() {

		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("andy.aguilarmasis@gmail.com"));
			message.setSubject("Prueba");
			message.setText("Esto es una prueba");
			Transport t = session.getTransport("smtp");
			t.connect((String) properties.get("mail.smtp.user"), password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (MessagingException me) {
			me.printStackTrace();
			return;
		}

	}
	public static void main(String[] args) {
		Envio enviar = new Envio();
		enviar.sendEmail();
		
	}

}