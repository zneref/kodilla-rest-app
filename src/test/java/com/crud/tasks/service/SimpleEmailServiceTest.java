package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", null, "test subject", "test message");
        SimpleMailMessage testMsg = new SimpleMailMessage();
        testMsg.setTo("test@test.com");
        testMsg.setText("test message");
        testMsg.setSubject("test subject");
        //testMsg.setCc("dupa@wolowa");

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(testMsg);
    }
}