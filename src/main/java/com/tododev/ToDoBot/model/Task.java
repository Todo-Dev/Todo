package com.tododev.ToDoBot.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String taskName;
    private String description;


    @ManyToOne
    Section section;

    public Task(String taskName, String description, Section section) {
        this.taskName = taskName;
        this.description = description;
        this.section = section;
    }

    public long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task() {
    }

}
