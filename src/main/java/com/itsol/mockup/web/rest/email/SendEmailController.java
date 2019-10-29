package com.itsol.mockup.web.rest.email;

import com.itsol.mockup.services.EmailService;
import com.itsol.mockup.web.dto.request.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping(value = "/email")
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/SendEmail", method = RequestMethod.GET)
    public void sendEmail() {
//        emailService.sendEmail(toEmail, subject, message);
    }

}
