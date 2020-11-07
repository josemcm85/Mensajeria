package com.r6.service;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Envio {
	private final Properties properties = new Properties();

	private String password = "";

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
			
			//text
			message.setSubject("Subject test");
			Multipart emailContent = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText("multipart text");
			
			//Attachment Body Part
			MimeBodyPart file = new MimeBodyPart();
			file.attachFile("PATH");
			
			//attach all
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(file);
			message.setContent(emailContent);
			
			// send message
			Transport t = session.getTransport("smtp");
			t.connect((String) properties.get("mail.smtp.user"), password);
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("Si se envio el correo... ");
			t.close();
		} catch (MessagingException me) {
			System.out.println("No se envio el correo... ");
			me.printStackTrace();
			return;
		} catch (IOException e) {
			System.out.println("No se pudo enviar el correo");
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		Envio enviar = new Envio();
		enviar.sendEmail();
		
	}

}
