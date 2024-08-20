package com.liam.be.core.course.service;

import com.liam.be.core.course.model.CourseRequest;
import com.liam.be.core.course.model.CourseResponse;
import com.liam.be.core.course.model.LessonCourseRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Page<CourseResponse> getAllCourse(Integer page, Integer size, String searchText);
    List<CourseResponse> getAllCourse();

    CourseResponse getCourseId(Long id);

    CourseRequest addCourse(CourseRequest course);

    CourseRequest updateCourse(CourseRequest request);

    LessonCourseRequest addLessonCourse(LessonCourseRequest request);

}
