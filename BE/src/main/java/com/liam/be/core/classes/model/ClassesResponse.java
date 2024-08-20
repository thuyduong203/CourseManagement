package com.liam.be.core.classes.model;

import com.liam.be.infrastructure.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;

public interface ClassesResponse {
    Long getId();

    String getCode();

    Integer getStudy();

    Integer getLessLearn();

    String getDays();


    Integer getTotalLesson();

    Integer getQuantity();

    default String[] getDayOfWeek() {
        try {
            String[] daysOfWeek = getDays().split(",");
            Arrays.sort(daysOfWeek, (day1, day2) -> {
                DayOfWeek dayOfWeek1 = DayOfWeek.valueOf(day1.toUpperCase());
                DayOfWeek dayOfWeek2 = DayOfWeek.valueOf(day2.toUpperCase());
                return dayOfWeek1.compareTo(dayOfWeek2);
            });
            return daysOfWeek;
        } catch (NullPointerException ignored) {
        }
        return new String[0];
    }

    String getCourseName();

    Long getCourseId();

    Long getTeacherId();

    String getTeacherName();

    Integer getMaxStudent();

    Integer getCountStudent();

    String getStatus();

    @Value("#{target.time_start}")
    LocalDateTime getStartTime();

    default String getTimeStart() {
        return DateUtil.dateToString(getStartTime());
    }


}
