package com.tododev.ToDoBot.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BoardList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty()
    @Column(nullable = false)
    private String boardName;
    private String description;


    @ManyToOne
    ApplicationUser applicationUser;

    @OneToMany(mappedBy = "boardList",cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @OrderBy("id")
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

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setApplicationUser(com.tododev.ToDoBot.model.ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public void setSections(List<com.tododev.ToDoBot.model.Section> sections) {
        this.sections = sections;
    }
}
