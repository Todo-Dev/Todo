package com.tododev.ToDoBot.model;

public class MovedTask {
    private long taskId; //of that task
    private long oldSectionId;
    private long newSectionId;

    public MovedTask(long taskId,  long oldSectionId, long newSectionId) {
        this.taskId = taskId;
        this.oldSectionId = oldSectionId;
        this.newSectionId = newSectionId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getOldSectionId() {
        return oldSectionId;
    }

    public void setOldSectionId(long oldSectionId) {
        this.oldSectionId = oldSectionId;
    }

    public long getNewSectionId() {
        return newSectionId;
    }

    public void setNewSectionId(long newSectionId) {
        this.newSectionId = newSectionId;
    }
}
