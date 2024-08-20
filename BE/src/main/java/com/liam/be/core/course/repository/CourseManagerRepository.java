package com.liam.be.core.course.repository;

import com.liam.be.core.course.model.CourseResponse;
import com.liam.be.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseManagerRepository extends CourseRepository {

    @Query(value = """
            select c.id, c.code, c.name, c.description, price, c.status,
            c.image_url as imageUrl,
            count(DISTINCT l.lesson_id) as lessonQuantity,
            count(DISTINCT ct.student_id) as studentQuantity,
            count(DISTINCT cl.id) as classQuantity,
            c.time_end as timeEnd
            from course c
            left join course_lesson l on l.course_id = c.id
            left join classes cl on cl.course_id = c.id
            left join class_student ct on ct.class_id = cl.id
            where c.name like concat('%',:searchText,'%') or c.code like concat('%',:searchText,'%')
            group by c.id, c.created_at
            order by c.created_at desc
            """, nativeQuery = true)
    Page<CourseResponse> getAllCourse(Pageable pageable, String searchText);

    @Query(value = """
            select c.id, code, c.name, c.description, price, c.status, count(l.id) as lessonQuantity from course c
            left join course_lesson l on l.course_id = c.id
            group by c.id, c.created_at
            order by c.created_at desc
            """, nativeQuery = true)
    List<CourseResponse> getAllCourse();

    @Query(value = """
            select c.id, c.code, c.name, c.description, price, c.status,
            c.image_url as imageUrl,
            count(DISTINCT l.lesson_id) as lessonQuantity,
            count(DISTINCT ct.student_id) as studentQuantity,
            count(DISTINCT cl.id) as classQuantity,
            c.time_end as timeEnd
            from course c
            left join course_lesson l on l.course_id = c.id
            left join classes cl on cl.course_id = c.id
            left join class_student ct on ct.class_id = cl.id
            where c.id = :id
            group by c.id, c.created_at
            """, nativeQuery = true)
    Optional<CourseResponse> getCourseId(Long id);
}
