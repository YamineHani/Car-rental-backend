package com.carrental.carrental.service;

import com.carrental.carrental.model.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    // it allows you to log messages at different log levels
    private final static Logger logger = LoggerFactory.getLogger(com.carrental.carrental.model.EmailSender.class);
    private final JavaMailSender javaMailSender;

    @Override
    @Async // don't block client
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("yasmine@gmail.com");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("failed to send email", e); // logger because I don't want user to see it
            throw new IllegalStateException("failed to send email");
        }
    }

}

