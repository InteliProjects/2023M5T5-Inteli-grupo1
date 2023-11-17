package com.pegasus.pegasus.repositories;

//importando ferramentas
import com.pegasus.pegasus.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsernameCustom(@Param("username") String username);

    @Query("SELECT u.username FROM User u WHERE u.username = :username")
    String findUsername(@Param("username") String username);

    User findByPassword(String password);

    User findByEmail(String email);

    @Query("SELECT u.email FROM User u WHERE u.email = :email")
    String findEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmailCustom(@Param("email") String email);
}
