package com.liam.be.core.course.controller;

import com.liam.be.core.course.model.CourseRequest;
import com.liam.be.core.course.model.CourseResponse;
import com.liam.be.core.course.model.LessonCourseRequest;
import com.liam.be.core.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService service;

    @GetMapping("/page")
    public Page<CourseResponse> getCourse(Integer page, Integer size, String searchText) {
        return service.getAllCourse(page, size, searchText);
    }

    @GetMapping
    public List<CourseResponse> getAllCourse(Integer page, Integer size, String searchText) {
        return service.getAllCourse();
    }

    @GetMapping("/{id}")
    public CourseResponse getCourse(@PathVariable Long id) {
        return service.getCourseId(id);
    }

    @PostMapping("/add")
    public CourseRequest addCourse(@RequestBody CourseRequest courseRequest) {
        return service.addCourse(courseRequest);
    }

    @PostMapping("/add-lesson")
    public LessonCourseRequest addLessonCourse(@RequestBody LessonCourseRequest request) {
        return service.addLessonCourse(request);
    }

    @PostMapping("/update")
    public CourseRequest updateCourse(@RequestBody CourseRequest courseRequest) {
        return service.updateCourse(courseRequest);
    }
}
