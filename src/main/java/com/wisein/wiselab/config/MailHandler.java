package com.wisein.wiselab.config;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailHandler {

    private JavaMailSender mailSender;

    public MailHandler(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(MailDTO mailDTO) {
        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

            messageHelper.setFrom("wisein@gmail.com", "WISEADMIN");
            messageHelper.setTo(mailDTO.getToAddress());
            messageHelper.setSubject(mailDTO.getTitle());
            messageHelper.setText(mailDTO.getMessageContent(), true);

            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
