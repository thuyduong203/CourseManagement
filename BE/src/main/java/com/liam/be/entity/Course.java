package com.liam.be.entity;

import com.liam.be.entity.base.BaseEntity;
import com.liam.be.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    @Column(length = EntityProperties.LENGTH_CODE)
    private String code;

    @Column(columnDefinition = EntityProperties.DEFINITION_NAME)
    private String name;

    @Column(columnDefinition = EntityProperties.DEFINITION_DESCRIPTION)
    private String description;

    private BigDecimal price;

    private String timeEnd;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String imageUrl;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CourseLesson> lessons = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<User> courseStudents = new HashSet<>();
}
