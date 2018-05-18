package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class SimpleEmailService {
    public static final int TASKS_SERVICE = 1000;
    public static final int BOARDS_SERVICE = 2000;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailCreatorService mailCreatorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public void send(Mail mail, int service) {
        LOGGER.info("Starting email preparation...");
        try {
            MimeMessagePreparator mailMessage = createMimeMessage(mail, service);
            javaMailSender.send(mailMessage);
            LOGGER.info("Email has been sent.");
        } catch (MailException e) {
            LOGGER.info("failed to process email sending: ", e.getStackTrace(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, int service) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            switch (service) {
                case TASKS_SERVICE:
                    messageHelper.setText(mailCreatorService.buildTasksCardEmail(mail.getMessage()), true);
                    break;
                case BOARDS_SERVICE:
                    messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
                    break;
                default:
                    break;
            }
        };
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
        ofNullable(mail.getToCc()).ifPresent(mailMessage::setCc);
        return mailMessage;
    }
}
