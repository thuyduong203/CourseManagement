package com.liam.be.core.lesson.repository;

import com.liam.be.core.lesson.model.LessonResponse;
import com.liam.be.entity.Lesson;
import com.liam.be.repository.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LessonManagerRepository extends LessonRepository {

    @Query(value = """
            select cl.id, name, status, description,cl.ordinal,quantity, cl.lesson_id as idLesson from lesson l
            join course_lesson cl on cl.lesson_id = l.id
            where cl.course_id = :courseId
            order by ordinal
            """, nativeQuery = true)
    List<LessonResponse> getAllCourseId(Long courseId);

    @Query(value = """
            select cl.id, name, status, description,cl.ordinal,
            quantity, cl.lesson_id as idLesson from lesson l
            join course_lesson cl on cl.lesson_id = l.id
            where cl.course_id = :courseId and name like concat('%',:searchText,'%')
            order by cl.ordinal
            """, nativeQuery = true)
    Page<LessonResponse> getLessonCourseId(Pageable pageable,
                                           @Param("searchText") String searchText,
                                           @Param("courseId") Long courseId);
    @Query(value = """
            select lc.id, name, lc.status, description,cl.ordinal,
            quantity, l.id as idLesson, c.id as idClass from lesson l
            left join course_lesson cl on cl.lesson_id = l.id
            left join classes c on c.course_id = cl.course_id
            left join lesson_class lc on lc.lesson_id = l.id and lc.classes_id = c.id
            where c.code = :codeClass and  name like concat('%',:searchText,'%')
            order by cl.ordinal
            """, nativeQuery = true)
    Page<LessonResponse> getLessonClass(Pageable pageable,
                                           @Param("searchText") String searchText,
                                           @Param("codeClass") String codeClass);
@Query(value = """
            select id, name, status, description,quantity from lesson
            where name like concat('%',:searchText,'%')
            order by created_at desc
            """, nativeQuery = true)
    Page<LessonResponse> getLesson(Pageable pageable,
                                           @Param("searchText") String searchText);

    @Query(value = """
            select id, name, status, description,quantity from lesson
            where id = :lessonId
            """, nativeQuery = true)
    LessonResponse getLessonId(Long lessonId);


    @Query(value = """
            select cl.id, name, status, description,cl.ordinal,
            quantity, cl.lesson_id as idLesson from lesson l
            join course_lesson cl on cl.lesson_id = l.id
            where cl.course_id = :idCourse
            order by cl.ordinal
            """, nativeQuery = true)
    List<LessonResponse> getLessonCourse(Long idCourse);
}
