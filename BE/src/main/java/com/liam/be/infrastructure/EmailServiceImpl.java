package com.liam.be.infrastructure;

import com.liam.be.entity.MailSender;
import com.liam.be.infrastructure.constant.CategoryMail;
import com.liam.be.repository.MailSenderRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private MailSenderRepository mailSenderRepository;

    @Value("spring.mail.username")
    private String MAILFROM;

    public void sendDangKyKhoa(String to, String name, String course) {
        new Thread(() -> {
            MailSender mailSender = mailSenderRepository.findByCode(CategoryMail.DANG_KY_KHOA_HOC);
            if (mailSender == null) {
                return;
            }
            String content = mailSender.getContent();
            content = content.replace("[NAME]", name);
            content = content.replace("[COURSE]", course);
            try {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom(new InternetAddress(MAILFROM));
                helper.setTo(to);
                helper.setSubject(mailSender.getSubject());
                helper.setText(content, true); // Đặt true để cho phép sử dụng HTML

                emailSender.send(message);
            } catch (MessagingException | MailException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendThamGiaLop(String to, String name,String code,String password,
                               String course, String classes, String time) {
        new Thread(() -> {
            MailSender mailSender = mailSenderRepository.findByCode(CategoryMail.THAM_GIA_LOP);
            if (mailSender == null) {
                return;
            }
            String content = mailSender.getContent();
            content = content.replace("[NAME]", name);
            content = content.replace("[COURSE]", course);
            content = content.replace("[CLASS]", classes);
            content = content.replace("[TIME]", time);
            content = content.replace("[USERNAME]", code);
            content = content.replace("[PASSWORD]", password);
            try {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom(new InternetAddress(MAILFROM));
                helper.setTo(to);
                helper.setSubject(mailSender.getSubject());
                helper.setText(content, true); // Đặt true để cho phép sử dụng HTML

                emailSender.send(message);
            } catch (MessagingException | MailException e) {
                e.printStackTrace();
            }
        }).start();
    }

}