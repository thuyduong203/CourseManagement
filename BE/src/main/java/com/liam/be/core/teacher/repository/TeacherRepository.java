package com.liam.be.core.teacher.repository;

import com.liam.be.core.teacher.model.TeacherResponse;
import com.liam.be.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TeacherRepository extends UserRepository {
    @Query(value = """
            select u.id,u.code, name, birth_date
            from user u
            join account a on u.account_id = a.id
            where role = 'TEACHER'
            """, nativeQuery = true)
    List<TeacherResponse> getAllTeacher();


    @Query(value = """
            SELECT u.id, u.code, u.name, u.birth_date
            FROM user u
                     JOIN account a ON u.account_id = a.id
                     LEFT JOIN classes c ON c.teacher_id = u.id
            WHERE a.role = 'TEACHER'
              AND ((
                      SELECT COUNT(s.id)
                      FROM schedule s
                               JOIN classes c2 ON c2.id = s.classes_id
                      WHERE c2.teacher_id = u.id
                        AND s.day_of_week IN (:days)
                        AND (s.study = :study OR s.study + 1 = :study OR s.study - 1 = :study)
                  ) = 0 OR c.id = :classId)
            GROUP BY u.id
            """, nativeQuery = true)
    Set<TeacherResponse> getTeacherClass(String[] days, Integer study, Long classId);


    @Query(value = """
            select u.id,u.code, u.status, name, birth_date, email, phone, address, gender,
            count(distinct c.id) as totalClass
            from user u
            join account a on u.account_id = a.id
            left join classes c on c.teacher_id = u.id
            where role = 'TEACHER'
            and name like :searchText
            group by u.id, u.code, u.status, name, birth_date, email, phone, address, gender
            """, nativeQuery = true)
    Page<TeacherResponse> getAllTeacher(Pageable pageable, String searchText);

    @Query(value = """
            select u.id,code, status, name, birth_date, email, phone, address, gender
            from user u
            join account a on u.account_id = a.id
            where u.id = :id and role = 'TEACHER'
            """, nativeQuery = true)
    Optional<TeacherResponse> getTeacherById(Long id);

}
