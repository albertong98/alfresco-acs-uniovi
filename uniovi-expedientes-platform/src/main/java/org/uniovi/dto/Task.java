package org.uniovi.dto;

import java.util.Date;

public class Task {
    public String uuid;
    public String title;
    public String description;
    public Student student;
    public Date dueDate;
    public Float score;
    public String taskStatus;

    public Task(String uuid, String title, String description, Student student, Date dueDate, Float score, String taskStatus) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.student = student;
        this.dueDate = dueDate;
        this.score = score;
        this.taskStatus = taskStatus;
    }

    public Task(){}
}
