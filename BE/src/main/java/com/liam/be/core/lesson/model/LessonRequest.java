package com.liam.be.core.lesson.model;

import com.liam.be.entity.Lesson;
import com.liam.be.infrastructure.constant.StatusEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonRequest {
    private Long id;

    private String name;

    private String description;

    private Integer ordinal;
    private Integer quantity;

    public Lesson tranferLesson(LessonRequest req, Lesson lesson) {
        lesson.setId(req.getId());
        lesson.setName(req.getName());
        lesson.setDescription(req.getDescription());
        lesson.setQuantity(req.getQuantity());
        return lesson;
    }

    public LessonRequest lessTranfer(Lesson lesson) {
        return LessonRequest.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .description(lesson.getDescription())
                .quantity(lesson.getQuantity())
                .build();
    }
}
