package com.tododev.ToDoBot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BoardList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String boardName;
    private String description;


    @ManyToOne
    ApplicationUser applicationUser;

    @OneToMany(mappedBy = "boardList")
    List<Section> sections;



    public BoardList() {
    }

    public BoardList(String boardName, String description, ApplicationUser applicationUser) {
        this.boardName = boardName;
        this.description = description;
        this.applicationUser = applicationUser;

    }

    public long getId() {
        return id;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getDescription() {
        return description;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public List<Section> getSections() {
        return sections;
    }
}
