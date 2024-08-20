package com.liam.be.core.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LessonCourseRequest {
    private Long idCourse;
    private List<Long> lessonsId;
    private List<String> courseLessonIds;
}
