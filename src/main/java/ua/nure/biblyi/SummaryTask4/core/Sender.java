package ua.nure.biblyi.SummaryTask4.core;




import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;



/**
 * Created by dmitry on 23.01.17.
 */
public class Sender {
    private String username = "epamtour@gmail.com";
    private String password = "awdrgyjil";
    private Properties props;

    public Sender() {

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void send(String subject, String text, String toEmail){
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            //From
            message.setFrom(new InternetAddress(username));
            //To
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            //Title
            message.setSubject(subject);
            //Text
            message.setText(text);

            //Send
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
