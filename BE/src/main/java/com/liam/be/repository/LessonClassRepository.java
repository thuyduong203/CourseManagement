package com.liam.be.repository;

import com.liam.be.entity.LessonClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonClassRepository extends JpaRepository<LessonClass, String> {
}
