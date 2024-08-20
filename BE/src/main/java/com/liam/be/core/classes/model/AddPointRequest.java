package com.liam.be.core.classes.model;

import lombok.Data;

@Data
public class AddPointRequest {
    private String codeClass;
    private Long studentId;
    private Long lessonId;
    private Integer point;
}
