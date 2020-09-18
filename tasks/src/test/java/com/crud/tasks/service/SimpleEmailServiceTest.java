package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("pilar36@wp.pl", "Test maila trolololo", "Dzialaaaa!");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mail.getReceiveEmial());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getMessage());

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(simpleMailMessage);
    }
}