package testmail;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 使成为事实邮件的特快专递功能
 * @author Winter Lau
 */
 
public class Mailer {
 
    /**
     * 发送邮件
     *
     * @param smtpHost
     * @param email
     * @throws MessagingException
     */
    protected static void sendMail(String smtpHost, String email)
            throws MessagingException {
 
        Properties mailProperties = System.getProperties();
        mailProperties.put("mail.smtp.host", smtpHost);
        mailProperties.put("mail.smtp.port", "25");
        mailProperties.put("mail.smtp.auth", "false");
        Session mailSession = Session.getInstance(mailProperties, null);
        MimeMessage mailMessage = new MimeMessage(mailSession);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart("related");
        messageBodyPart.setText("这搭是邮件内部实质意义");
 
        multipart.addBodyPart(messageBodyPart);
        mailMessage.setContent(multipart);
        mailMessage.setSentDate(new Date());
        mailMessage.setFrom(new InternetAddress("lijianyu@myhexin.com"));
        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
                email));
        mailMessage.setSubject("hi，邮件发送测试");
        Transport.send(mailMessage);
    }
    
    public static void main(String[] args) throws MessagingException{
    	sendMail("mail.myhexin.com", "lijianyu@myhexin.com");
    }
}