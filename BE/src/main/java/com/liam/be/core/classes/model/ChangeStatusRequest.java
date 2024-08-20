package com.liam.be.core.classes.model;

import lombok.Data;

@Data
public class ChangeStatusRequest {
    private String id;
    private Integer status;
    private Long idLesson;
    private Long idClass;
}
