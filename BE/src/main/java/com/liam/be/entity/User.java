package com.liam.be.entity;

import com.liam.be.entity.base.BaseEntity;
import com.liam.be.infrastructure.constant.EntityProperties;
import com.liam.be.infrastructure.constant.GenderUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(columnDefinition = EntityProperties.DEFINITION_NAME)
    private String name;

    @Column(length = EntityProperties.LENGTH_CODE)
    private String code;

    private LocalDateTime birthDate;

    @Column(length = EntityProperties.LENGTH_PHONE)
    private String phone;

    @Column(length = EntityProperties.LENGTH_ADDRESS)
    private String address;

    @Enumerated(EnumType.STRING)
    private GenderUser gender;

    @ManyToMany(mappedBy = "students")
    private Set<Classes> classes = new HashSet<>();

    @ManyToMany(mappedBy = "courseStudents")
    private Set<Course> courses = new HashSet<>();

    @Column(length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
