package com.liam.be.repository;

import com.liam.be.entity.MailSender;
import com.liam.be.infrastructure.constant.CategoryMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailSenderRepository extends JpaRepository<MailSender, String> {
    MailSender findByCode(CategoryMail code);
}
