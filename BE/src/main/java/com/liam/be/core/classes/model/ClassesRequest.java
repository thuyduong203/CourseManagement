package com.liam.be.core.classes.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassesRequest {
    private Long id;

    private String code;

    private Long courseId;

    private Long teacherId;

    private String timeStart;

    private Short maxStudent;

    private String[] dayOfWeek;

    private Integer study;

    private String status;
}
