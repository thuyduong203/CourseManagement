package com.liam.be.infrastructure.listener;

import com.liam.be.entity.base.BaseEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.Calendar;


public class BaseEntityListener {
    @PrePersist
    private void onCreate(BaseEntity entity) {
        entity.setCreatedAt(getLongDate());
        entity.setUpdatedAt(getLongDate());
        entity.setUpdatedBy("Admin");
        entity.setCreatedBy("Admin");
    }

    @PreUpdate
    private void onUpdate(BaseEntity entity) {
        entity.setUpdatedAt(getLongDate());
        String updatedBy = "Admin";
        entity.setUpdatedBy(updatedBy);
    }

    private LocalDateTime getLongDate() {
        return LocalDateTime.now();
    }
}
