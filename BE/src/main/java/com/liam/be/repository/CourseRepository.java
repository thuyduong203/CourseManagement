package com.liam.be.repository;

import com.liam.be.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = """
        select sum(l.quantity) from course c
        join course_lesson cl on cl.course_id = c.id
        join lesson l on l.id = c.id
        where c.id = :idCourse
        """, nativeQuery = true)
    Integer totalLesson(Long idCourse);
}
