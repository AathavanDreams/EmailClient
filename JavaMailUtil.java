package EmailClient;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaMailUtil {

    private static ArrayList<OldEmails> OldEmailsArray=new ArrayList<>();

    public static void GetOldEmails() throws IOException, ClassNotFoundException {
        OldEmailsArray = OldEmails.readFile();
    }
    public static void sendMail(String recepient, String subject, String content, String type) throws Exception {
        if (type.equals("Bday")) {
            System.out.println("Preparing to send birthday wish to " + recepient);
        }else{ System.out.println("Preparing to send email to " + recepient);}

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "aathumai2@gmail.com";
        String password = "dvcaajhqprknhyhn";

        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient, subject, content);
        Transport.send(message);

        if (type.equals("Bday")) {
            System.out.println("Success! Sent Birthday wish to" + recepient);
        }else{ System.out.println("Success! Sent mail to" + recepient);}

        String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String timeSt = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        OldEmails oldemail = new OldEmails(recepient, subject, content, date, timeSt);

        OldEmailsArray.add(oldemail);

        try {
            OldEmails.writeToFile(OldEmailsArray);
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String subject, String content){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(content);
            return message;
        } catch (Exception ex){
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null,ex);
        }
        return null;
    }
}