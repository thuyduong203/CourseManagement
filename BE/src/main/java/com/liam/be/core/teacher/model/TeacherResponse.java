package com.liam.be.core.teacher.model;

import com.liam.be.infrastructure.constant.GenderUser;
import com.liam.be.infrastructure.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface TeacherResponse {
    String getId();

    String getCode();

    String getName();

    @Value("#{target.birth_date}")
    LocalDateTime getDateBirth();

    default String getBirthDate() {
        return DateUtil.dateToString(getDateBirth());
    }

    String getEmail();

    String getPhone();

    Integer getTotalClass();

    String getAddress();

    String getStatus();

    GenderUser getGender();
}
