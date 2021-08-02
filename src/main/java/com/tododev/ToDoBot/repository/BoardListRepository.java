package com.tododev.ToDoBot.repository;

import com.tododev.ToDoBot.model.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardListRepository extends JpaRepository<BoardList,Long> {

}
