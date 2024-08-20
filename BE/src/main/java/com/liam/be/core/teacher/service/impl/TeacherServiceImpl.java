package com.liam.be.core.teacher.service.impl;

import com.liam.be.core.teacher.model.TeacherClassRequest;
import com.liam.be.core.teacher.model.TeacherRequest;
import com.liam.be.core.teacher.model.TeacherResponse;
import com.liam.be.core.teacher.repository.TeacherRepository;
import com.liam.be.core.teacher.service.TeacherService;
import com.liam.be.entity.Account;
import com.liam.be.entity.User;
import com.liam.be.infrastructure.constant.RoleUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<TeacherResponse> getPageTeacher(Integer page, Integer size, String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        return teacherRepository.getAllTeacher(pageable, "%" + searchText + "%");
    }

    @Override
    public List<TeacherResponse> getAllTeacher() {
        return teacherRepository.getAllTeacher();
    }

    @Override
    public TeacherRequest addTeacher(TeacherRequest teacher) {
        teacher.setPassword("12345678");
        Account account = new Account();

        account.setRole(RoleUser.TEACHER);
        account.setPassword(passwordEncoder.encode(teacher.getPassword()));
        User user = teacher.tranferUser(teacher, new User());
        user.setCode(checkCode(genCodeTeacher(teacher.getName()), 0));
        user.setEmail(teacher.getEmail());
        user.setAccount(account);
        return teacher.userTranfer(teacherRepository.save(user));
    }

    @Override
    public TeacherResponse getTeacherId(Long id) {
        return teacherRepository.getTeacherById(id).orElseThrow();
    }

    @Override
    public Boolean removeTeacher(Long id) {
        teacherRepository.deleteById(id);
        return true;
    }

    @Override
    public TeacherRequest updateTeacher(TeacherRequest teacher) {
        User user = teacher.tranferUser(teacher, teacherRepository.findById(teacher.getId()).orElseThrow());
        user.setEmail(teacher.getEmail());
        return teacher.userTranfer(teacherRepository.save(user));
    }

    @Override
    public Set<TeacherResponse> getTeacherClass(TeacherClassRequest request) {
        String[] days = request.getDayOfWeek().split(",");
        Arrays.sort(days, (day1, day2) -> {
            DayOfWeek dayOfWeek1 = DayOfWeek.valueOf(day1.toUpperCase());
            DayOfWeek dayOfWeek2 = DayOfWeek.valueOf(day2.toUpperCase());
            return dayOfWeek1.compareTo(dayOfWeek2);
        });
        return teacherRepository.getTeacherClass(days, request.getStudy(), request.getId());
    }

    private String genCodeTeacher(String name) {
        String nameNoAccent = StringUtils.stripAccents(name);
        String[] arrName = nameNoAccent.split(" ");
        StringBuilder code = new StringBuilder(arrName[arrName.length - 1]);
        for (int i = 0; i < arrName.length - 1; i++) {
            code.append(arrName[i].charAt(0));
        }
        return code.toString();
    }

    private String checkCode(String code, Integer index) {
        String codeIndex = code + (index == 0 ? "" : String.valueOf(index));
        if (teacherRepository.findByCode(codeIndex).orElse(null) == null) {
            return codeIndex;
        }
        return checkCode(code, index + 1);
    }
}
