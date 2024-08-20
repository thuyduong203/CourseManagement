package com.liam.be.core.security.impl;

import com.liam.be.core.security.UserService;
import com.liam.be.core.student.repository.StudentRepository;
import com.liam.be.core.teacher.model.TeacherRequest;
import com.liam.be.core.teacher.repository.TeacherRepository;
import com.liam.be.entity.Account;
import com.liam.be.entity.User;
import com.liam.be.infrastructure.constant.RoleUser;
import com.liam.be.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String genCodeStudent(String name) {
        String nameNoAccent = StringUtils.stripAccents(name);
        String[] arrName = nameNoAccent.split(" ");
        StringBuilder code = new StringBuilder(arrName[arrName.length - 1]);
        for (int i = 0; i < arrName.length - 1; i++) {
            code.append(arrName[i].charAt(0));
        }
        return code.toString();
    }

    @Override
    public String checkCode(String code, Integer index) {
        String codeIndex = code + (index == 0 ? "" : String.valueOf(index));
        if (userRepository.findByCode(codeIndex.toLowerCase()).orElse(null) == null) {
            return codeIndex;
        }
        return checkCode(code, index + 1);
    }

    @Override
    public TeacherRequest addAdmin(TeacherRequest teacher) {
        teacher.setPassword("12345678");
        teacher.setCode(checkCode(genCodeStudent(teacher.getName()), 0));
        Account account = new Account();
        account.setRole(RoleUser.ADMIN);
        account.setPassword(passwordEncoder.encode(teacher.getPassword()));
        User user = teacher.tranferUser(teacher, new User());
        user.setEmail(teacher.getEmail());
        user.setAccount(account);
        teacherRepository.save(user);
        return teacher;
    }
}
