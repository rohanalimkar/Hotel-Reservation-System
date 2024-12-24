package Repository;

import java.util.Properties;

import Model.EmailMaster;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import logindermo.Mail.CreateOTP;

public class SendMailRepositoryImp implements SendMailRepository {
	@Override
	public boolean sendEmail(EmailMaster mail) {
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2,TLSv1.3");
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2,TLSv1.3");

        // Gmail SMTP server settings
        String host = "smtp.gmail.com";
        String port = "587";  // Use port 587 for STARTTLS
        String username = "rohanalimkar@gmail.com";  // Your Gmail address
        String appPassword = "zdtg pcwh xhwb iize";  // Your App Password from Gmail
        String from = "rohanalimkar@gmail.com";     // Your email address
        String to = mail.getTo();    // Recipient email address
        String subject = mail.getSubject();

        // Generate OTP
        String bodyText = mail.getText();

        // Set up properties for the SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");  // Enable STARTTLS for port 587

        // Create session with the provided username (email) and app password
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, appPassword);
            }
        });

        try {
            // Create the email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(bodyText);

            // Send the email using Transport class
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
	}

}
