package com.liam.be.core.classes.repository;

import com.liam.be.core.classes.model.*;
import com.liam.be.entity.Classes;
import com.liam.be.repository.ClassRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassesRepository extends ClassRepository {
    @Query(value = """
            select c.id, c.code, c.status,c.time_start, s.study,
                        GROUP_CONCAT(DISTINCT s.day_of_week) as days,
                        concat(te.code, ' - ', te.name) as teacherName,
                        count(distinct lc.id) as lessLearn,
                        te.id as teacherId,
                        concat(co.code, ' - ', co.name) as courseName,
                        co.id as courseId,
                        c.max_student as maxStudent,
                        count(DISTINCT l.lesson_id) as totalLesson,
                        count(DISTINCT cs.student_id) as countStudent
                        from classes c
                        join course co on c.course_id = co.id
                        left join user te on te.id = c.teacher_id
                        left join class_student cs on cs.class_id =  c.id
                        left join schedule s on s.classes_id = c.id and s.time_study
                        left join course_lesson l on l.course_id = c.course_id
                        left join lesson_class lc on lc.classes_id = c.id and (lc.status = 3 or lc.status = 2)
                        where c.code like :searchText or co.name like :searchText or co.code like:searchText
                        group by c.id, c.code, c.status, s.study,c.created_at
                        order by c.created_at desc
            """, nativeQuery = true)
    Page<ClassesResponse> getPageClass(Pageable pageable, String searchText);

    @Query(value = """
            select c.id, c.code, c.status,c.time_start, s.study,
                        GROUP_CONCAT(DISTINCT s.day_of_week) as days,
                        concat(te.code, ' - ', te.name) as teacherName,
                        count(distinct lc.id) as lessLearn,
                        te.id as teacherId,
                        concat(co.code, ' - ', co.name) as courseName,
                        co.id as courseId,
                        c.max_student as maxStudent,
                        count(DISTINCT l.lesson_id) as totalLesson,
                        count(DISTINCT cs.student_id) as countStudent
                        from classes c
                        join course co on c.course_id = co.id
                        join class_student cs on cs.class_id =  c.id and cs.student_id = :id
                        left join user te on te.id = c.teacher_id
                        left join schedule s on s.classes_id = c.id and s.time_study
                        left join course_lesson l on l.course_id = c.course_id
                        left join lesson_class lc on lc.classes_id = c.id and (lc.status = 3 or lc.status = 2)
                        where c.code like :searchText
                        group by c.id, c.code, c.status, s.study,c.created_at
                        order by c.created_at desc
            """, nativeQuery = true)
    Page<ClassesResponse> getPageClassStudent(Pageable pageable, String searchText, Long id);

    @Query(value = """
            select c.id, c.code, c.status,c.time_start, s.study,
                        GROUP_CONCAT(DISTINCT s.day_of_week) as days,
                        concat(te.code, ' - ', te.name) as teacherName,
                        count(distinct lc.id) as lessLearn,
                        te.id as teacherId,
                        concat(co.code, ' - ', co.name) as courseName,
                        co.id as courseId,
                        c.max_student as maxStudent,
                        count(DISTINCT l.lesson_id) as totalLesson,
                        count(DISTINCT cs.student_id) as countStudent
                        from classes c
                        join course co on c.course_id = co.id
                        join class_student cs on cs.class_id =  c.id
                        join user te on te.id = c.teacher_id and te.id = :id
                        left join schedule s on s.classes_id = c.id and s.time_study
                        left join course_lesson l on l.course_id = c.course_id
                        left join lesson_class lc on lc.classes_id = c.id and (lc.status = 3 or lc.status = 2)
                        where c.code like :searchText
                        group by c.id, c.code, c.status, s.study,c.created_at
                        order by c.created_at desc
            """, nativeQuery = true)
    Page<ClassesResponse> getPageClassTeacher(Pageable pageable, String searchText, Long id);

    @Query(value = """
            select c.id, c.code, c.status,c.time_start, s.study,
            GROUP_CONCAT(DISTINCT s.day_of_week) as days,
            concat(te.code, ' - ', te.name) as teacherName,
            te.id as teacherId,
            concat(co.code, ' - ', co.name) as courseName,
            co.id as courseId,
            c.max_student as maxStudent,
            count(DISTINCT cs.student_id) as countStudent
            from classes c
            join course co on c.course_id = co.id
            left join user te on te.id = c.teacher_id
            left join class_student cs on cs.class_id =  c.id
            left join schedule s on s.classes_id = c.id
            where c.id = :id
            group by c.id, c.code, c.status, s.study
            """, nativeQuery = true)
    Optional<ClassesResponse> getClassById(Long id);

    @Query(value = """
            select c.id,
            count(DISTINCT s.id) as lessLearn,
            (SELECT SUM(quantity) FROM lesson l
             JOIN course_lesson cl on cl.lesson_id = l.id
             WHERE course_id = c.course_id) as quantity
            from classes c
            left join schedule s on s.classes_id = c.id
            left join course_lesson l on l.course_id = c.course_id
            where c.code = :code
            group by c.id
            """, nativeQuery = true)
    Optional<ClassTimeStudyResponse> getClassScheduleByCode(String code);

    @Query(value = """
            SELECT MAX(CAST(SUBSTRING(code, 5) AS UNSIGNED))
            FROM classes
            WHERE code LIKE :code
                    """, nativeQuery = true)
    Integer getMaxCode(String code);


    @Query(value = """
            SELECT
                u.id AS idStudent,
                u.code AS codeStudent,
                u.name AS nameStudent,
                c.time_start as timeStart,
                (select count(DISTINCT sch.id) from schedule sch where sch.time_study < CURRENT_TIME
            and sch.classes_id = c.id) as lessLearn,
                GROUP_CONCAT(DISTINCT s.day_of_week) as days,
                JSON_OBJECTAGG(
                        IFNULL(p.lesson_id, ''),p.points) AS pointLesson
            FROM
                classes c
                    JOIN
                class_student cs ON cs.class_id = c.id
                    JOIN
                user u ON u.id = cs.student_id
                    LEFT JOIN
                point p ON p.student_id = u.id AND p.classes_id = c.id
                    LEFT JOIN
                course_lesson l ON l.course_id = c.course_id
                left join schedule s on s.classes_id = c.id
            where c.code = :codeClass and u.name like concat('%', :searchText,'%')
            group by u.id, u.code, u.name, c.id
            """, nativeQuery = true)
    List<ClassStudentResponse> getStudentClass(@Param("searchText") String searchText, @Param("codeClass") String codeClass);

    @Query(value = """
            SELECT
                u.id AS idStudent,
                u.code AS codeStudent,
                u.name AS nameStudent
            FROM
                classes c
                    JOIN
                class_student cs ON cs.class_id = c.id
                    JOIN
                user u ON u.id = cs.student_id
            where c.code = :codeClass and u.name like concat('%', :searchText,'%')
            group by u.id, u.code, u.name, c.id
            """, nativeQuery = true)
    List<StudentInClassResponse> getStudentInClass(@Param("searchText") String searchText, @Param("codeClass") String codeClass);

    @Query(value = """
            select l.id, l.name, l.quantity, lc.status from classes c
            join course co on co.id = c.course_id
            join course_lesson cl on cl.course_id = co.id
            join lesson l on  cl.lesson_id = l.id
            left join lesson_class lc on lc.lesson_id = l.id and lc.classes_id = c.id
            where c.code = :codeClass
            order by cl.ordinal
            """, nativeQuery = true)
    List<ClassLessonResponse> getAllLesson(@Param("codeClass") String codeClass);

    Optional<Classes> findByCode(String code);

    @Query(value = """
            select s.id, s.time_study, s.day_of_week, s.study, s.status\s
            from schedule s
            join classes c on c.id = s.classes_id and c.code = :codeClass
            where s.time_study >= :dateStart and s.time_study <= :date
            order by s.time_study
            """, nativeQuery = true)
    Page<ClassScheduleResponse> getScheduleClass(Pageable pageable, @Param("dateStart") LocalDateTime dateStart, @Param("date") LocalDateTime date, @Param("codeClass") String codeClass);

    @Query(value = """
            select s.id, s.time_study, s.day_of_week, s.study, s.status\s
            from schedule s
            join classes c on c.id = s.classes_id and c.code = :codeClass
            where s.time_study >= :date and s.time_study <= :dateStart
            order by s.time_study
            """, nativeQuery = true)
    Page<ClassScheduleResponse> getScheduleClassHistory(Pageable pageable, @Param("dateStart") LocalDateTime dateStart, @Param("date") LocalDateTime date, @Param("codeClass") String codeClass);

    @Query(value = """
            select s.id, s.time_study, s.day_of_week, s.study, s.status\s
            from schedule s
            join classes c on c.id = s.classes_id and c.code = :codeClass
            order by s.time_study
            """, nativeQuery = true)
    Page<ClassScheduleResponse> getScheduleClassAll(Pageable pageable, @Param("codeClass") String codeClass);

    @Query(value = """
            SELECT
                u.id AS idStudent,
                u.code AS codeStudent,
                u.name AS nameStudent,
                c.time_start as timeStart,
                (select count(DISTINCT sch.id) from schedule sch where sch.time_study < CURRENT_TIME
            and sch.classes_id = c.id) as lessLearn,
                GROUP_CONCAT(DISTINCT s.day_of_week) as days,
                JSON_OBJECTAGG(
                        IFNULL(p.lesson_id, ''),p.points) AS pointLesson
            FROM
                classes c
                    JOIN
                class_student cs ON cs.class_id = c.id and cs.student_id = :id
                    JOIN
                user u ON u.id = cs.student_id
                    LEFT JOIN
                point p ON p.student_id = u.id AND p.classes_id = c.id
                    LEFT JOIN
                course_lesson l ON l.course_id = c.course_id
                left join schedule s on s.classes_id = c.id
            where c.code = :codeClass and u.name like concat('%', :searchText,'%')
            group by u.id, u.code, u.name, c.id
            """, nativeQuery = true)
    List<ClassStudentResponse> getStudentClassRoleStudent(String searchText, String codeClass, Long id);
}
