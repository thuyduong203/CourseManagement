package com.liam.be.repository;

import com.liam.be.core.security.UserLoginResponse;
import com.liam.be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("""
              select u from User u where LOWER(u.code) = :code or lower(u.email) = :code
            """)
    Optional<User> findByCode(String code);

    @Query(value = """
            select u.id,code, status, name, birth_date, email, phone, address, gender,role from user u
            join account a on u.account_id = a.id
            where u.code = :code""", nativeQuery = true)
    Optional<UserLoginResponse> getUserLogin(String code);

}
