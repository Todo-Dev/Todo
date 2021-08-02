package com.tododev.ToDoBot.repository;

import com.tododev.ToDoBot.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {

}
