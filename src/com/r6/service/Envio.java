package com.r6.service;
import java.io.File;
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
import javax.swing.SwingWorker;

public class Envio extends SwingWorker<Void, Integer>{

	private final Properties properties = new Properties();
	private Session session;
	public Correo correo;
	
	public void enviarSincronico() {
		System.out.println("iniciado");
		// sets smtp server properties
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.port", 25);
				properties.put("mail.smtp.mail.sender", correo.getEmailSender());
				properties.put("mail.smtp.user", correo.getEmailSender());
				properties.put("mail.smtp.auth", "true");
				session = Session.getDefaultInstance(properties);

				try {

					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo.getEmailReceiver()));
					message.setSubject(correo.getSubject());
					message.addRecipient(Message.RecipientType.BCC, new InternetAddress(correo.getCopiados()));
					// creates multipart
					Multipart emailContent = new MimeMultipart();
					MimeBodyPart textBodyPart = new MimeBodyPart();
					textBodyPart.setText(correo.getBody());
					emailContent.addBodyPart(textBodyPart);

					// adds attachment
					//size limit 50 mb - gmail
					if (correo.getAttachedFiles() != null && correo.getAttachedFiles().size() > 0) {
						for (File filePath : correo.getAttachedFiles()) {
							MimeBodyPart attachPart = new MimeBodyPart();
							try {
								attachPart.attachFile(filePath);
							} catch (IOException ex) {
								ex.printStackTrace();
							}

							emailContent.addBodyPart(attachPart);
						}
					}
					message.setContent(emailContent, correo.getTipo());
					
					// send message
					Transport t = session.getTransport("smtp");
					t.connect((String) properties.get("mail.smtp.user"), correo.getPassword());
					t.sendMessage(message, message.getAllRecipients());
					System.out.println("Si se envio el correo... ");
					t.close();
				} catch (MessagingException me) {
					System.out.println("No se envio el correo... ");
					System.out.println("tamaño de archivo excedido - menor a 50 mb");
					me.printStackTrace();
				}

	}

	@Override
	protected Void doInBackground() throws Exception {
		System.out.println("iniciado");
		// sets smtp server properties
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.port", 25);
				properties.put("mail.smtp.mail.sender", correo.getEmailSender());
				properties.put("mail.smtp.user", correo.getEmailSender());
				properties.put("mail.smtp.auth", "true");
				session = Session.getDefaultInstance(properties);

				try {

					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo.getEmailReceiver()));
					message.setSubject(correo.getSubject());

					// creates multipart
					Multipart emailContent = new MimeMultipart();
					MimeBodyPart textBodyPart = new MimeBodyPart();
					textBodyPart.setText(correo.getBody());
					emailContent.addBodyPart(textBodyPart);

					// adds attachment
					//size limit 50 mb - gmail
					if (correo.getAttachedFiles() != null && correo.getAttachedFiles().size() > 0) {
						for (File filePath : correo.getAttachedFiles()) {
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
					t.connect((String) properties.get("mail.smtp.user"), correo.getPassword());
					t.sendMessage(message, message.getAllRecipients());
					System.out.println("Si se envio el correo... ");
					t.close();
				} catch (MessagingException me) {
					System.out.println("No se envio el correo... ");
					System.out.println("tamaño de archivo excedido - menor a 50 mb");
					me.printStackTrace();
					return null;
				}

		
		return null;
	}

	public Correo getCorreo() {
		return correo;
	}

	public void setCorreo(Correo correo) {
		this.correo = correo;
	}
	
}

