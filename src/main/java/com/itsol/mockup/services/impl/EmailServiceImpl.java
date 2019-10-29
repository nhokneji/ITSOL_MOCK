package com.itsol.mockup.services.impl;

import com.itsol.mockup.services.EmailService;
import com.itsol.mockup.web.dto.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl extends BaseService implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.from.address}")
    private String fromAddress;

    @Override
    public void sendEmail(EmailRequest emailRequest) {
        logger.info("SEND EMAIL START");
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromAddress);
            helper.setTo(emailRequest.getToEmail());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getMessage());
            javaMailSender.send(mimeMessage);
            logger.info(String.valueOf(mimeMessage));
        } catch (Exception e) {
            logger.error("send email err" + e.getMessage(), e);
        }

    }
}
