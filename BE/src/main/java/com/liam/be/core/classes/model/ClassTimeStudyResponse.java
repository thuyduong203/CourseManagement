package com.liam.be.core.classes.model;

import java.time.DayOfWeek;
import java.util.Arrays;

public interface ClassTimeStudyResponse {

    String getId();
    String getDays();

    Integer getQuantity();


    Integer getLessLearn();

    Integer getStudy();
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
}
