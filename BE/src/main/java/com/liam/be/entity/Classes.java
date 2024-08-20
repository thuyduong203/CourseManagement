package com.liam.be.entity;

import com.liam.be.entity.base.BaseEntity;
import com.liam.be.infrastructure.constant.EntityProperties;
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
@Table(name = "classes")
public class Classes extends BaseEntity {

    @Column(length = EntityProperties.LENGTH_CODE)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime timeStart;

    private String background = "https://www.gstatic.com/classroom/themes/img_code.jpg";

    private Short maxStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;

//    private String days;

//    private Integer study;

    @ManyToMany
    @JoinTable(
            name = "class_student",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<User> students = new HashSet<>();
}
