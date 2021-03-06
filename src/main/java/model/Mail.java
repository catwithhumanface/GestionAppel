package model;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    public static void sendEmailAbsence(String emailTo, String nomCours, String dateCours) {
        Properties prop = new Properties();
        String user = "miage.ut1@gmail.com";
        String pass = "Projet020909!";
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("user.name", "allococon@gmail.com");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject("Notification - Absence");
            message.setText("Bonjour,"
                    + "\n\n Vous avez été abesent(e) au cours : " + nomCours + " au " + dateCours + "."
                    + "\n\n Veuillez déposer le justificatif. ");
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendMailDepotJustifi(String prenom, String nom, String emailTo, String nomCours, String dateCours) {
        Properties prop = new Properties();
        String user = "allococon@gmail.com";
        String pass = "Projet02090909!";
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 465);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        prop.put("user.name", "allococon@gmail.com");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("joohyun.ann@ut-capitole.fr"));
            message.setSubject("Notification - Reçu de justificatif");
            message.setText("Bonjour,"
                    + "\n\n Etudiant  : " + prenom + " " + nom +
                    " vient de déposer un justificatif pour son absence du cours " + nomCours + " au " + dateCours + "."
                    + "\n\n Veuillez valider le justificatif. ");
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
