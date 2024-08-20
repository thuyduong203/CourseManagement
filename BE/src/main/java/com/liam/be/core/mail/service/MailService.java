package com.liam.be.core.mail.service;

import com.liam.be.entity.MailSender;

import java.util.List;

public interface MailService {
    List<MailSender> getAllMail();

    MailSender getMailId(String id);

    MailSender updateMail(MailSender mail);
}
