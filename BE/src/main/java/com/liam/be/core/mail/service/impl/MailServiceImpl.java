package com.liam.be.core.mail.service.impl;

import com.liam.be.core.mail.service.MailService;
import com.liam.be.entity.MailSender;
import com.liam.be.repository.MailSenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailSenderRepository mailSenderRepository;

    @Override
    public List<MailSender> getAllMail() {
        return mailSenderRepository.findAll();
    }

    @Override
    public MailSender getMailId(String id) {
        return mailSenderRepository.findById(id).orElseThrow();
    }

    @Override
    public MailSender updateMail(MailSender mail) {
        mailSenderRepository.save(mail);
        return mail;
    }

}
