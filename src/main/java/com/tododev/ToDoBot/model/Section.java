package com.tododev.ToDoBot.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @NotEmpty()
    @Column(nullable = false)
    private String title;

    @ManyToOne
    BoardList boardList;

    @OneToMany(mappedBy = "section" ,cascade = CascadeType.ALL)
    List<Task> taskList;


    public Section(String title, BoardList boardList) {
        this.title = title;
        this.boardList = boardList;
    }


    public List<Task> getTaskList() {
        return taskList;
    }

    public Section() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BoardList getBoardList() {
        return boardList;
    }

}
