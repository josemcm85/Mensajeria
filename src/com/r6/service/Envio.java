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
	private Session session;

	public void sendEmail(String emailSender, String Password, String emailReceiver, String Subject, String Body,
			String[] attachFiles) {
		// sets smtp server properties
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", 25);
		properties.put("mail.smtp.mail.sender", emailSender);
		properties.put("mail.smtp.user", emailSender);
		properties.put("mail.smtp.auth", "true");
		session = Session.getDefaultInstance(properties);

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceiver));
			message.setSubject(Subject);

			// creates multipart
			Multipart emailContent = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(Body);
			emailContent.addBodyPart(textBodyPart);

			// adds attachment
			if (attachFiles != null && attachFiles.length > 0) {
				for (String filePath : attachFiles) {
					MimeBodyPart attachPart = new MimeBodyPart();
					try {
						attachPart.attachFile(filePath);
					} catch (IOException ex) {
						ex.printStackTrace();
					}

					emailContent.addBodyPart(attachPart);
				}
			}
			message.setContent(emailContent);
			
			// send message
			Transport t = session.getTransport("smtp");
			t.connect((String) properties.get("mail.smtp.user"), Password);
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("Si se envio el correo... ");
			t.close();
		} catch (MessagingException me) {
			System.out.println("No se envio el correo... ");
			me.printStackTrace();
			return;
		}

	}

	public static void main(String[] args) {
		Envio enviar = new Envio();
		
		// attachments
        String[] attachFiles = new String[2];
        attachFiles[0] = "file path";
        attachFiles[1] = "file path";
        
		enviar.sendEmail(
				//sender
				"email", 
				"password",
				//receiver 
				"email", 
				"Subject --", 
				"Body --", 
				attachFiles);
	}
}