package background;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**Class Mail,
 *
 * @author Clement Baranger clement.baranger@etudiant.univ-lr.fr
 * @version 1.1
 * @since 1.1
 */
public class Mail {
	/**
	 * Variable de nom d'utilisateur.
	 */
	final String username;

	/**
	 * Variable de mot de passe.
	 */
	final String password;

	/**
	 * Variable de propriéter.
	 */
	Properties props;

	/**Constructeur
	 * <p>
	 */
	public Mail() {	
		props = new Properties();
		this.username ="repixtc@gmail.com";
		this.password="acersapu";
		props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
	}


	/**
	 * Méthode sendTo, servant à envoyé un email à une personne s'incrivant sur l'application.
	 * <p>
	 *
	 * @param mail (requis),
	 * @param subject (requis),.
	 * @param  msg (requis),
	 * @exception  MessagingException,
	 */
	public boolean sendTo(String mail,String subject,String msg) {
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){protected PasswordAuthentication getPasswordAuthentication(){return new PasswordAuthentication(username, password);}});
        try {
        	Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
        	throw new RuntimeException(e);
        }
	}
	
	
	
}
