package com.liam.be.repository;

import com.liam.be.entity.Course;
import com.liam.be.entity.CourseLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLessonRepository extends JpaRepository<CourseLesson, String> {
}
