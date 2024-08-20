package com.liam.be.core.classes.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Map;

public interface ClassStudentResponse {
    Long getIdStudent();

    String getCodeStudent();

    String getNameStudent();

    Integer getLessLearn();

    String getTimeStart();

    String getDays();

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

    String getPointLesson();

    default Map getPoints() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(getPointLesson(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
