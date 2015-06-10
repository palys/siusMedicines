package siusMedicines.mail;

public class MailUtils {

	public static void sendMail(String to, String subject, String text) {
		new Thread(new MailSender(to, subject, text)).start();
	}
}
