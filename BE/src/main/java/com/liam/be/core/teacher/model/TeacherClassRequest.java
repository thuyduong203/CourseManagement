package com.liam.be.core.teacher.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeacherClassRequest {
    private Long id;
    private String dayOfWeek;
    private Integer study;
}
