package com.javacourse.myproject3.repository;

import com.javacourse.myproject3.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByPersonID(String personID);
    boolean existsByPersonID(String personID);
}
