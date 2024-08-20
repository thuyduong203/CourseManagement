package com.liam.be.entity;

import com.liam.be.entity.base.BaseEntity;
import com.liam.be.infrastructure.constant.StatusEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classes_id")
    private Classes classes;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Integer study;

    private LocalDateTime timeStudy;

}
