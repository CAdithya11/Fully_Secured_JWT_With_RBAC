package com.example.JWT.Token.repo;

import com.example.JWT.Token.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM user WHERE user_id=:user_id",nativeQuery = true)
    Optional<User> findUserByUserId(@Param("user_id") Integer user_id);

    @Query(value = "SELECT * FROM user WHERE email=:email",nativeQuery = true)
    Optional<User> findUserByEmail(@Param("email")String email);
}
