package lk.ijse.gdse.fitlifegym.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailSendViewController  {
    private MailSendViewController mailSendViewController;
    @FXML
    private Button btnSendMail;

    @FXML
    private Label lblMail;

    @FXML
    private TextArea txtBody;

    @FXML
    private TextField txtSubject;

    @Setter
    private MemberManageViewController memberManageViewController;
    @Setter
    private Stage stage;

    public void setMail(String mail) {

        lblMail.setText(mail);
    }

    @FXML
    void btnSendMailOnAction(ActionEvent event) {
        String recipientEmail = lblMail.getText();
        String subject = txtSubject.getText();
        String body = txtBody.getText();

        if (recipientEmail.isEmpty() || subject.isEmpty() || body.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields before sending email!").show();
            return;
        }

        final String FROM_EMAIL = "prashan7070@gmail.com";
        final String PASSWORD = "dqir qioy kiay nbeo";

        Task<Void> emailTask = new Task<>() {
            @Override
            protected Void call() {
                try {
                    sendEmailWithGmail(FROM_EMAIL, PASSWORD, recipientEmail, subject, body);
                    Platform.runLater(() -> {
                        new Alert(Alert.AlertType.INFORMATION, "Email sent successfully..!").showAndWait();
                    });
                } catch (MessagingException e) {
                    Platform.runLater(() -> {
                        new Alert(Alert.AlertType.ERROR, "Failed to send email: " + e.getMessage()).showAndWait();
                    });
                }
                return null;
            }
        };

        // Run the task in a new thread
        Thread emailThread = new Thread(emailTask);
        emailThread.setDaemon(true); // Ensure thread exits when application closes
        emailThread.start();

        txtSubject.clear();
        txtBody.clear();
    }

    private void sendEmailWithGmail(String fromEmail, String password, String toEmail, String subject, String messageBody) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        Message message = prepareMassage(session, fromEmail, toEmail, subject, messageBody);
        if (message != null) {
            Transport.send(message);
            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully..!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Email send unsuccessfully..!").showAndWait();
        }
    }

    private Message prepareMassage(Session session, String fromEmail, String toEmail, String subject, String messageBody) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(toEmail)});
            message.setSubject(subject);
            message.setText(messageBody);

            return message;
        } catch (MessagingException e) {
            Logger.getLogger(MailSendViewController.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }






}


