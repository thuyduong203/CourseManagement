package com.liam.be.core.lesson.model;

public interface LessonResponse {
    String getId();

    String getName();

    String getStatus();

    Long getIdLesson();

    Long getIdClass();

    String getDescription();

    Integer getOrdinal();

    Integer getQuantity();
}
