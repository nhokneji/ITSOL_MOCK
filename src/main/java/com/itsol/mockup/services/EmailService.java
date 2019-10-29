package com.itsol.mockup.services;

import com.itsol.mockup.web.dto.request.EmailRequest;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(EmailRequest emailRequest);

}
