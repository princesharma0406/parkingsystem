import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMail {

 public void emailWithEmbeddedImage() {

  try {

   final String from = "multilevel.car.parking.sys@gmail.com"; 
   final String password = "career123A";
   
   String toAddress = "sharad.jtp@gmail.com";
   String ccAddress ="cc@gmail.com";
   String bccAddress = "bcc@gmail.com";
   String name = "Job";
   
   // JavaMail session object
   Session session;

   // The JavaMail message object
   Message mesg;

   //SMTP server properties 
   Properties properties = new Properties();
   properties.put("mail.smtp.host", "smtp.gmail.com");
   properties.put("mail.smtp.port", 587);
   properties.put("mail.smtp.auth", "true");
   properties.put("mail.smtp.starttls.enable", "true");

   // authenticate sender username and password 
   Authenticator auth = new Authenticator() {
    public PasswordAuthentication getPasswordAuthentication() {
     return new PasswordAuthentication(from, password);
    }
   };

   // initialize session object
   session = Session.getInstance(properties, auth);
   session.setDebug(false);

   // initialize message object
   mesg = new MimeMessage(session);

   // from Address
   mesg.setFrom(new InternetAddress(from));

   // Email Addresses
   InternetAddress toAdd = new InternetAddress(toAddress);
   InternetAddress ccAdd = new InternetAddress(ccAddress);
   InternetAddress bccAdd = new InternetAddress(bccAddress);

   mesg.addRecipient(Message.RecipientType.TO, toAdd);
   mesg.addRecipient(Message.RecipientType.CC, ccAdd);
   mesg.addRecipient(Message.RecipientType.BCC, bccAdd);

   // email Subject
   mesg.setSubject("Visitor Pass");

   // message body.
   Multipart mp = new MimeMultipart("related");

   String cid = "qr";

   MimeBodyPart pixPart = new MimeBodyPart();
   pixPart.attachFile("D:\\akash\\images\\photo.png");
   pixPart.setContentID("<" + cid + ">");
   pixPart.setDisposition(MimeBodyPart.INLINE);

   MimeBodyPart textPart = new MimeBodyPart();
   textPart.setText("<html>" + "Hello " + name + ", <br> "
     + "Please find your visiting QR code <br> "
     + "<div><img src=\"cid:" + cid
     + "\" /></div></html>" + "Thanks & Regards, <br> "
     + "Bill</html>", "US-ASCII", "html");

   // Attach text and image to message body
   mp.addBodyPart(textPart);
   mp.addBodyPart(pixPart);

   // Setting message content
   mesg.setContent(mp);

   // Send mail
   Transport.send(mesg);

  } catch (MessagingException e) {
   System.err.println(e);
   e.printStackTrace(System.err);
  } catch (IOException e) {
   System.err.println(e);
   e.printStackTrace();
  }
 }
 
 public static void main(String[] args) {
  
  new SendMail().emailWithEmbeddedImage();
  
 }
}