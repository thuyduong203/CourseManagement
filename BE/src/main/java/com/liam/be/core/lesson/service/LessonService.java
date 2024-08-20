package com.liam.be.core.lesson.service;

import com.liam.be.core.course.model.CourseResponse;
import com.liam.be.core.lesson.model.LessonRequest;
import com.liam.be.core.lesson.model.LessonResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LessonService {

    LessonRequest addLesson(LessonRequest lesson);

    Boolean removeLesson(Long id);

    LessonRequest updateLesson(LessonRequest lesson);

    Page<LessonResponse> getLessonByCourse(Integer page, Integer size, String searchText, Long idCourse);

    LessonResponse getLessonById(Long lessonId);

    Page<LessonResponse> getLesson(Integer page, Integer size, String searchText);

    Page<LessonResponse> getLessonByClass(Integer page, Integer size, String searchText, String codeClass);

    List<LessonResponse> getLessonCourse(Long idCourse);
}
