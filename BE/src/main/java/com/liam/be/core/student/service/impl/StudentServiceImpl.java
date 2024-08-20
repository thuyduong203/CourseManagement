package com.liam.be.core.student.service.impl;

import com.liam.be.core.security.UserService;
import com.liam.be.core.student.model.StudentRequest;
import com.liam.be.core.student.model.StudentResponse;
import com.liam.be.core.student.repository.StudentRepository;
import com.liam.be.core.student.service.StudentService;
import com.liam.be.entity.Course;
import com.liam.be.entity.User;
import com.liam.be.infrastructure.EmailServiceImpl;
import com.liam.be.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    UserService userService;
    @Autowired
    private EmailServiceImpl emailService;


    @Override
    public Page<StudentResponse> getAllStudent(Integer page, Integer size, String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.getAllStudent(pageable, "%" + searchText + "%");
    }

    @Override
    public StudentRequest addStudent(StudentRequest student) {
        student.setCode(userService.checkCode(userService.genCodeStudent(student.getName()), 0));
        User user = student.tranferUser(student, new User());
        user.setCode(student.getCode());
        user.setEmail(student.getEmail());
        Course course = courseRepository.findById(student.getCourseId()).orElseThrow();
        course.getCourseStudents().add(user);
        courseRepository.save(course);
        emailService.sendDangKyKhoa(student.getEmail(), student.getName(), course.getName());
        return student;
    }


    @Override
    public StudentResponse getStudentId(Long id) {
        return studentRepository.getStudentById(id).orElseThrow();
    }

    @Override
    public Boolean removeStudent(Long id) {
        studentRepository.deleteById(id);
        return true;
    }

    @Override
    public StudentRequest updateStudent(StudentRequest student) {
        User user = student.tranferUser(student, studentRepository.findById(student.getId()).orElseThrow());
        user.setEmail(student.getEmail());
        return student.userTranfer(studentRepository.save(user));
    }

    @Override
    public Page<StudentResponse> getAllStudentCourse(String code, Integer page, Integer size, String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.getAllStudentCourseId(code, "%" + searchText + "%",pageable);
    }


}
