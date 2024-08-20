package com.liam.be.entity;

import com.liam.be.infrastructure.constant.CategoryMail;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mail_sender")
public class MailSender {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryMail code;

    private String name;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;
}
