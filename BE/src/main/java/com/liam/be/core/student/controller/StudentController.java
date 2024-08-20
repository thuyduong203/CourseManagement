package com.liam.be.core.student.controller;

import com.liam.be.core.student.model.StudentRequest;
import com.liam.be.core.student.model.StudentResponse;
import com.liam.be.core.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public Page<StudentResponse> getStudent(Integer page, Integer size, String searchText) {
        return service.getAllStudent(page,size, searchText);
    }

    @GetMapping("/course/{code}")
    public Page<StudentResponse> getStudentCourse(@PathVariable String code, Integer page, Integer size, String searchText) {
        return service.getAllStudentCourse(code,page,size, searchText);
    }

    @GetMapping("/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        return service.getStudentId(id);
    }

    @PostMapping("/add")
    public StudentRequest addStudent(@RequestBody StudentRequest student) {
        return service.addStudent(student);
    }

    @PostMapping("/update")
    public StudentRequest updateStudent(@RequestBody StudentRequest student) {
        return service.updateStudent(student);
    }

    @DeleteMapping("/remove/{id}")
    public Boolean addStudent(@PathVariable Long id) {
        return service.removeStudent(id);
    }
}
