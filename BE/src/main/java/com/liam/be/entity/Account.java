package com.liam.be.entity;

import com.liam.be.infrastructure.constant.EntityProperties;
import com.liam.be.infrastructure.constant.RoleUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = EntityProperties.LENGTH_PASSWORD)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleUser role;

    @OneToOne(mappedBy = "account")
    private User user;
}
