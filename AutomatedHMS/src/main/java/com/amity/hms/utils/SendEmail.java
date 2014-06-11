package com.amity.hms.utils;
/*
public class SendEmail {

	public void send(){
		
	}
	
	public static void main(String[] args) {
		System.out.println("TT");
	}
}*/

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmail {
 
	private final static String USERNAME = "amity.hostel.outpass@gmail.com";
	private final static String PWD = "Amity@123";
	
	public static void send(String emailSubject, String emailBody, String toAddress, String ... ccAddress) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PWD);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress));
			
			for (String ccMailAdd : ccAddress) {
				System.out.println(ccMailAdd);
				message.addRecipient(RecipientType.CC, new InternetAddress(ccMailAdd));
			}
			
			message.setSubject(emailSubject);
			message.setText(emailBody);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}