package com.example.demo.respository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    boolean existsById(Long id);
    boolean existsByusername(String username);
    boolean existsByemail(String email);
    boolean existsBypassword(String password);

    User getUserByusername(String username);



}
