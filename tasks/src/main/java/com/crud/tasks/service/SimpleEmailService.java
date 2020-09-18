package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation");
        try {
            SimpleMailMessage simpleMessage = createMailMessage(mail);
            javaMailSender.send(simpleMessage);
            LOGGER.info("Email has been send");
        } catch (MailException e) {
            LOGGER.error("Failed to process email sending", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(mail.getReceiveEmial());
        simpleMessage.setSubject(mail.getSubject());
        simpleMessage.setText(mail.getMessage());
        return simpleMessage;
    }
}
