package com.assessment.auth.repo;

import com.assessment.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo  extends JpaRepository<User,Integer> {

    @Query(
            nativeQuery=true,
            value="select * from users u where u.email=?1"
    )
    Optional<User> findByEmail(String email);
}
