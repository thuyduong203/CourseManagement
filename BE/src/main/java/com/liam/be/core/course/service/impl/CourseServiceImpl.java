package com.liam.be.core.course.service.impl;

import com.liam.be.core.course.model.CourseRequest;
import com.liam.be.core.course.model.CourseResponse;
import com.liam.be.core.course.model.LessonCourseRequest;
import com.liam.be.core.course.repository.CourseManagerRepository;
import com.liam.be.core.course.service.CourseService;
import com.liam.be.entity.Course;
import com.liam.be.entity.CourseLesson;
import com.liam.be.entity.Lesson;
import com.liam.be.infrastructure.constant.StatusEntity;
import com.liam.be.repository.CourseLessonRepository;
import com.liam.be.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseManagerRepository courseRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseLessonRepository courseLessonRepository;

    @Override
    public Page<CourseResponse> getAllCourse(Integer page, Integer size, String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.getAllCourse(pageable, searchText);
    }

    @Override
    public List<CourseResponse> getAllCourse() {
        return courseRepository.getAllCourse();
    }

    @Override
    public CourseResponse getCourseId(Long id) {
        return courseRepository.getCourseId(id).orElseThrow();
    }

    @Override
    public CourseRequest addCourse(CourseRequest request) {
        Course course = new Course();
        course.setCode("COURSE" + (courseRepository.count() + 1));
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setPrice(new BigDecimal(request.getPrice()));
        course.setStatus(StatusEntity.HOAT_DONG);
        course.setTimeEnd(request.getTimeEnd());
        course.setImageUrl(request.getImageUrl());
        courseRepository.save(course);
        return request;
    }

    @Override
    public CourseRequest updateCourse(CourseRequest request) {
        Course course = courseRepository.findById(request.getId()).orElseThrow();
        course.setName(request.getName());
        course.setStatus(StatusEntity.valueOf(request.getStatus()));
        course.setDescription(request.getDescription());
        course.setPrice(new BigDecimal(request.getPrice()));
        course.setTimeEnd(request.getTimeEnd());
        course.setImageUrl(request.getImageUrl());
        courseRepository.save(course);
        return request;
    }

    @Override
    public LessonCourseRequest addLessonCourse(LessonCourseRequest request) {
        Course course = courseRepository.findById(request.getIdCourse()).orElseThrow();

        List<Lesson> lessons = lessonRepository.findAllById(request.getLessonsId());
        if (request.getCourseLessonIds()!= null && !request.getCourseLessonIds().isEmpty()){
            List<CourseLesson> courseLessonsDelete = courseLessonRepository.findAllById(request.getCourseLessonIds());
            courseLessonRepository.deleteAll(courseLessonsDelete);
        }

        List<CourseLesson> courseLessons = IntStream.range(0, lessons.size())
                .mapToObj(index -> CourseLesson.builder()
                        .course(course)
                        .lesson(lessons.get(index))
                        .ordinal(index + 1)
                        .build())
                .collect(Collectors.toList());

        courseLessonRepository.saveAll(courseLessons);

        return request;
    }


}
