package com.liam.be.entity.base;

import com.liam.be.infrastructure.constant.EntityProperties;
import com.liam.be.infrastructure.constant.StatusEntity;
import com.liam.be.infrastructure.listener.BaseEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Setter
@Getter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    private StatusEntity status = StatusEntity.HOAT_DONG;
}
