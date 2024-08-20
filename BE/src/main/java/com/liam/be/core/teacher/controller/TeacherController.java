package com.liam.be.core.teacher.controller;

import com.liam.be.core.teacher.model.TeacherClassRequest;
import com.liam.be.core.teacher.model.TeacherRequest;
import com.liam.be.core.teacher.model.TeacherResponse;
import com.liam.be.core.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService service;

    @GetMapping("/page")
    public Page<TeacherResponse> getTeacher(Integer page, Integer size, String searchText) {
        return service.getPageTeacher(page,size, searchText);
    }

    @GetMapping
    public List<TeacherResponse> getAllTeacher(Integer page, Integer size, String searchText) {
        return service.getAllTeacher();
    }
    @GetMapping("/class")
    public Set<TeacherResponse> getTeacherClass(@ModelAttribute TeacherClassRequest request) {
        return service.getTeacherClass(request);
    }

    @GetMapping("/{id}")
    public TeacherResponse getTeacher(@PathVariable Long id) {
        return service.getTeacherId(id);
    }

    @PostMapping("/add")
    public TeacherRequest addTeacher(@RequestBody TeacherRequest teacher) {
        return service.addTeacher(teacher);
    }

    @PostMapping("/update")
    public TeacherRequest updateTeacher(@RequestBody TeacherRequest teacher) {
        return service.updateTeacher(teacher);
    }

    @DeleteMapping("/remove/{id}")
    public Boolean addTeacher(@PathVariable Long id) {
        return service.removeTeacher(id);
    }
}
