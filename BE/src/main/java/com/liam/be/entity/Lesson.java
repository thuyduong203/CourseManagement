package com.liam.be.entity;

import com.liam.be.entity.base.BaseEntity;
import com.liam.be.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lesson")
public class Lesson extends BaseEntity {

    @Column(columnDefinition = EntityProperties.DEFINITION_NAME)
    private String name;

    @Column(columnDefinition = EntityProperties.DEFINITION_DESCRIPTION)
    private String description;

    private Integer quantity;
}
