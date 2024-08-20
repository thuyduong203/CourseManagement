package com.liam.be.core.student.repository;

import com.liam.be.core.student.model.StudentResponse;
import com.liam.be.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends UserRepository {
    @Query(value = """
            select u.id, code, status, name, birth_date, email, phone, address, gender from user u
            join account a on u.account_id = a.id
            where a.role = 'STUDENT'
            and name like :searchText
            """, nativeQuery = true)
    Page<StudentResponse> getAllStudent(Pageable pageable, String searchText);

    @Query(value = """
            select u.id, u.code, u.status, u.name, birth_date, email, phone, address, gender
            from user u
            join course_student cs on cs.student_id = u.id
            join course co on co.id = cs.course_id
            join classes c on c.course_id = cs.course_id
            where u.name like :searchText and c.code = :code
            """, nativeQuery = true)
    Page<StudentResponse> getAllStudentCourseId(@Param("code") String code,@Param("searchText") String searchText, Pageable pageable);

    @Query(value = """
            select u.id,code, status, name, birth_date, email, phone, address, gender from user u
            join account a on u.account_id = a.id
            where u.id = :id and role = 'STUDENT'
            """, nativeQuery = true)
    Optional<StudentResponse> getStudentById(Long id);

}
