package com.example;

//import com.example.GoNowUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoNowUserRepository extends JpaRepository<GoNowUser, Long> {
    
}
