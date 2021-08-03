package com.tododev.ToDoBot.repository;

import com.tododev.ToDoBot.model.Section;
import com.tododev.ToDoBot.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.section = :next_section WHERE t.id = :task_id")
    void updateOnMove(@Param("task_id") Long task_id, @Param("next_section") Section new_column);

}
