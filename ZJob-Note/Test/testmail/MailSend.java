package testmail;
import java.util.Date;  
import java.util.Properties;  
import javax.mail.Address;  
import javax.mail.Authenticator;  
import javax.mail.BodyPart;
import javax.mail.Message;  
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
  
public class MailSend{  
    // 邮箱服务器  
//    private String host = "smtp.163.com";  
	private String host = "mail.myhexin.com";
    
    // 这个是你的邮箱用户名  
    private String username = "lijianyu@myhexin.com";  
    // 你的邮箱密码  
    private String password = "heavensay";  
      
    private String mail_to = "lijianyu@myhexin.com,zny818@163.com";  
    private String mail_cc = "zny818@163.com";
    
    private String mail_from = "lijianyu@myhexin.com";  
  
    private String mail_subject = " 行情采集出现问题！ ";  
  
//    private String personalName = "heaven";  
  
    public MailSend(){}  
  
    /** 
     * 此段代码用来发送普通电子邮件 
     */  
    public void send(String mail_body) throws Exception{  
        try{  
            Properties props=new Properties();  
            Authenticator auth = new EmailAutherticator(); // 进行邮件服务器用户认证  
            props.put("mail.smtp.host", host);  
            props.put("mail.smtp.auth", "true");  
            Session session = Session.getDefaultInstance(props, auth);  
            //设置session,和邮件服务器进行通讯。  
            MimeMessage message = new MimeMessage(session);  
            //message.setContent("foobar, "application/x-foobar"); // 设置邮件格式  
            message.setSubject(mail_subject); // 设置邮件主题
            message.setContent(mail_body,"text/html;charset=utf-8");  
            
//            //创建一个MimeMultipart对象来存放多个BodyPart对象
//            Multipart mul = new MimeMultipart();
//            //创建一个含有信件内容的BodyPart的对象
//            BodyPart mdp = new MimeBodyPart();
//            mdp.setContent(mail_body, "text/html;charset=utf-8");
//            //将含有信件内容的BodyPart加入到MimeMultipart对象中
//            mul.addBodyPart(mdp);
//            message.setContent(mul);
            
            message.setSentDate(new Date()); // 设置邮件发送日期  
            Address address = new InternetAddress(mail_from/*, personalName*/);  
            message.setFrom(address); // 设置邮件发送者的地址  
            
            String[] mail_tos = mail_to.split(",");
            String[] mail_ccs = mail_cc.split(",");
            
            Address[] toAddress = null; // 设置邮件接收方的地址
            Address[] ccAddress = null; // 设置邮件抄送人的地址
       
            if(mail_tos!=null){
            	toAddress = new InternetAddress[mail_tos.length];
	            for(int i=0;i<mail_tos.length;i++){
	            	toAddress[i]=new InternetAddress(mail_tos[i]);
	            }
            }
            
            if(mail_ccs!=null){
            	ccAddress = new InternetAddress[mail_ccs.length];
	            for(int i=0;i<mail_ccs.length;i++){
	            	ccAddress[i]=new InternetAddress(mail_ccs[i]);
	            }
            }
            message.addRecipients(Message.RecipientType.TO, toAddress);
            message.addRecipients(Message.RecipientType.CC, ccAddress);  
            Transport.send(message); // 发送邮件  
            
            System.out.println("send success!");  
        }catch (Exception ex){  
            ex.printStackTrace();  
            throw new Exception(ex.getMessage());  
        }  
    }  
  
    /** 
     * 用来进行服务器对用户的认证 
     */  
    public class EmailAutherticator extends Authenticator{
        public EmailAutherticator(){  
            super();  
        }  
  
        public EmailAutherticator(String user, String pwd){  
            super();  
            username = user;  
            password = pwd;  
        }  
  
        public PasswordAuthentication getPasswordAuthentication(){  
            return new PasswordAuthentication(username, password);  
        }  
    }  
  
    public static void main(String[] args){  
        MailSend sendmail = new MailSend();  
        try{  
//        	sendmail.send(" abcd ");
//            sendmail.send(" 行情采集错误 ");  
        	
        	sendmail.send("中国 ");
        	
//            System.out.println(MimeUtility.encodeText(new String("行情".getBytes(),"GBK"),"GBK","B"));
//            System.out.println(MimeUtility.encodeText(new String("行情".getBytes(),"GBK"),"UTF-8","B"));
//            System.out.println(MimeUtility.encodeText("行情"));
//            =?GBK?B?6KGM5oOF?=
//            	=?UTF-8?B?55Cb5bG+5YSP?=
//            	=?UTF-8?B?6KGM5oOF?=

//            		
//            System.out.println(new String("6KGM5oOF".getBytes(),"GBK"));
//            System.out.println(new String("55Cb5bG+5YSP".getBytes(),"GBK"));
            
        }catch (Exception ex){  
            ex.printStackTrace();  
        }  
    }  
  
}  