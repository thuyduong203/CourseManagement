package com.liam.be.core.teacher.model;

import com.liam.be.infrastructure.constant.GenderUser;
import com.liam.be.infrastructure.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TeacherClassResponse {
    private String id;
    private String name;
}
