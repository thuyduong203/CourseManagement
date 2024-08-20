package com.liam.be.core.security;

import com.liam.be.core.teacher.model.TeacherRequest;

public interface UserService {
    String genCodeStudent(String name);
    String checkCode(String code, Integer index);

    TeacherRequest addAdmin(TeacherRequest teacher);
}
