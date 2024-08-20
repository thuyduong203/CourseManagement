package com.liam.be.core.mail.controller;

import com.liam.be.core.mail.service.MailService;
import com.liam.be.entity.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    private MailService service;

    @GetMapping
    public List<MailSender> getAllMail() {
        return service.getAllMail();
    }

    @GetMapping("/{id}")
    public MailSender getMail(@PathVariable String id) {
        return service.getMailId(id);
    }

    @PostMapping("/update")
    public MailSender updateMail(@RequestBody MailSender mail) {
        return service.updateMail(mail);
    }

}
