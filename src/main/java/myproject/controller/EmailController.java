package myproject.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController { //Sending Email
	
	private final String MY_EMAIL = "cuongvip1295@gmail.com";
	private final String MY_PASSWORD = "Cuong@12101995";
	private final String RECIVER = "dohungcuongdev1295@gmail.com";
	private final String ATTACH_FILE_DIR = "C:\\";
	private final String ATTACH_FILE_NAME = "log.txt";
	private final String SUBJECT = "My subject";
	private final String CONTENT = "my message content";
	private final String BODY_PART_CONTENT = "my message body part content";
	
	@RequestMapping(value = "/sendemail")
	public String sendEmail() throws AddressException, MessagingException, IOException {
	   sendmail();
	   return "Email sent successfully";   
	}
	
   private void sendmail() throws AddressException, MessagingException, IOException {
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587");
	   
	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	      protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication(MY_EMAIL, MY_PASSWORD);
	      }
	   });
	   Message msg = new MimeMessage(session);
	   msg.setFrom(new InternetAddress(MY_EMAIL, false));

	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECIVER));
	   msg.setSubject(SUBJECT);
	   msg.setContent(CONTENT, "text/html");
	   msg.setSentDate(new Date());

	   MimeBodyPart messageBodyPart = new MimeBodyPart();
	   messageBodyPart.setContent(BODY_PART_CONTENT, "text/html");

	   Multipart multipart = new MimeMultipart();
	   multipart.addBodyPart(messageBodyPart);
	   MimeBodyPart attachPart = new MimeBodyPart();

	   attachPart.attachFile(ATTACH_FILE_DIR+ATTACH_FILE_NAME);
	   multipart.addBodyPart(attachPart);
	   msg.setContent(multipart);
	   Transport.send(msg);   
	}
}
