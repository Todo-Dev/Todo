package com.tododev.ToDoBot.model;

import javax.persistence.*;


@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String title;

    @ManyToOne
    BoardList boardList;

    public Section(String title, BoardList boardList) {
        this.title = title;
        this.boardList = boardList;
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
