package com.liam.be.core.course.model;

import com.liam.be.core.lesson.model.LessonRequest;
import com.liam.be.entity.Course;
import com.liam.be.entity.Lesson;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {
    private Long id;
    private String code;
    private String name;
    private String imageUrl;
    private String description;
    private String price;
    private String status;
    private String timeEnd;
}
