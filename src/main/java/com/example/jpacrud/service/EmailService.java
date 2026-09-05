package com.example.jpacrud.service;

import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {

    private static final String EMAIL_USERNAME = "dinhtung11012202@gmail.com";
    private static final String EMAIL_PASSWORD = "jhul wkbx qqhe huds";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final int SMTP_PORT = 587;

    public static void sendOTP(String toEmail, String otp, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.ssl.trust", SMTP_HOST);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendActivationEmail(String toEmail, String otp) {
        String subject = "Kich hoat tai khoan - JPA CRUD";
        String content = "<html><body>"
                + "<h2>Chao mung ban den voi JPA CRUD!</h2>"
                + "<p>Vui long nhap ma OTP de kich hoat tai khoan:</p>"
                + "<h1 style='color: #1877f2;'>" + otp + "</h1>"
                + "<p>Ma OTP co hieu luc trong 5 phut.</p>"
                + "<p>Neu ban khong yeu cau, vui long bo qua email nay.</p>"
                + "<hr>"
                + "<p>JPA CRUD - ThS. Nguyen Huu Trung</p>"
                + "</body></html>";
        sendOTP(toEmail, otp, subject, content);
    }

    public static void sendForgotPasswordEmail(String toEmail, String otp) {
        String subject = "Dat lai mat khau - JPA CRUD";
        String content = "<html><body>"
                + "<h2>Yeu cau dat lai mat khau</h2>"
                + "<p>Ban da yeu cau dat lai mat khau. Vui long nhap ma OTP:</p>"
                + "<h1 style='color: #e74c3c;'>" + otp + "</h1>"
                + "<p>Ma OTP co hieu luc trong 5 phut.</p>"
                + "<p>Neu ban khong yeu cau, vui long bo qua email nay.</p>"
                + "<hr>"
                + "<p>JPA CRUD - ThS. Nguyen Huu Trung</p>"
                + "</body></html>";
        sendOTP(toEmail, otp, subject, content);
    }
}