package com.liam.be.core.course.model;

import com.liam.be.core.lesson.model.LessonRequest;
import com.liam.be.core.lesson.model.LessonResponse;

import java.math.BigDecimal;
import java.util.List;

public interface CourseResponse {
    String getId();

    String getName();

    String getCode();

    BigDecimal getPrice();

    Integer getLessonQuantity();

    String getDescription();

    String getStatus();

    String getImageUrl();

    Integer getClassQuantity();

    String getTimeEnd();

    Integer getStudentQuantity();

}

