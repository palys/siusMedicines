package siusMedicines.mail;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender implements Runnable {

	private final String to;
	
	private final String subject;
	
	private final String text;
	
	private final static String from = "sius.medicines@poczta.onet.pl";
	
	private final static String password = "Sius1Med!";
	
	private final static String host = "smtp.poczta.onet.pl";

	public MailSender(String to, String subject, String text) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
	
	private Properties getProperties() {
		
		Properties properties = System.getProperties();

		properties.setProperty("mail.user", from);
		properties.setProperty("mail.password", "Sius1Med!");
		
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "465");


		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.debug","true");
		properties.put("mail.smtp.socketFactory.port","465");
		properties.put("mail.smtp.socketFactory.fallback","false");
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		return properties;
	}
	
	private Session getSession(Properties properties) {
		
		return Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
	}
	
	private Message generateMessage(String from, String to, Session session, String subject, String text) throws MessagingException {
		
		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(text);

		return message;
	}

	@Override
	public void run() {

		Properties props = getProperties();
		Session session = getSession(props);
		
		try {
			Message message = generateMessage(from, to, session, subject, text);
			Transport.send(message);
			//TODO notify success
		} catch (Exception e) {
			Logger.getGlobal().warning("Sending mail failed due to exception " + e.getMessage());
		}
		
	}
	
}
