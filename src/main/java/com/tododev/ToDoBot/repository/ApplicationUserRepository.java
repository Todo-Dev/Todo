package com.tododev.ToDoBot.repository;

import com.tododev.ToDoBot.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser , Long> {
    ApplicationUser findApplicationUserByUsername(String username);
}
