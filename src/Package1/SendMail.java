package Package1;
import java.util.Properties;  
import java.util.UUID;
import javax.mail.*;  
import javax.mail.internet.*;  
  
public class SendMail {  
 public static void sendmail(String email) {  
  
   final String user="reg.infosec.noreply@gmail.com";//to be changed in all peers 
   final String password="debuggers";//to be changed in all peers   
  Properties mailProperties = new Properties();   
   mailProperties.put("mail.smtp.host", "smtp.gmail.com");
   mailProperties.put("mail.smtp.starttls.enable","true");
   mailProperties.put("mail.smtp.socketFactory.port", "465");
   mailProperties.put("mail.smtp.auth", "true");
   mailProperties.put("mail.smtp.port", "465");
   mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 
   Session session = Session.getDefaultInstance(mailProperties,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
   String uuid = UUID.randomUUID().toString();
   uuid = uuid.substring(0, 7);
   DAO dao=new DAO();
   //add details to database
   dao.addUser(email,uuid);
  
    try {  
     MimeMessage mail = new MimeMessage(session);  
   
     mail.setFrom(new InternetAddress(user));  
     mail.addRecipient(Message.RecipientType.TO,new InternetAddress(email));  
     mail.setSubject("Peer2Peer network");  
     mail.setText("Here is your key for network: "+(uuid));  
    Transport.send(mail);     
     System.out.println("Mail has been sent!!!");  
   
     } catch (MessagingException e) {e.printStackTrace();}  
 }  
}  