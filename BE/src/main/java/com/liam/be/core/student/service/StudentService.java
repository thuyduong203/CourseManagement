package com.liam.be.core.student.service;

import com.liam.be.core.student.model.StudentRequest;
import com.liam.be.core.student.model.StudentResponse;
import com.liam.be.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    Page<StudentResponse> getAllStudent(Integer page,Integer size, String searchText);

    StudentRequest addStudent(StudentRequest student);

    StudentResponse getStudentId(Long id);

    Boolean removeStudent(Long id);

    StudentRequest updateStudent(StudentRequest student);

    Page<StudentResponse> getAllStudentCourse(String code, Integer page, Integer size, String searchText);
}
