package com.liam.be.core.classes.model;

import com.liam.be.infrastructure.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

public interface ClassScheduleResponse {
    String getId();

    @Value("#{target.time_study}")
    LocalDateTime getTimeStudy();

    default String getTime() {
        return DateUtil.dateToString(getTimeStudy());
    }

    Integer getStudy();

    @Value("#{target.day_of_week}")
    String getDayOfWeek();

    String getStatus();
}
