package org.uniovi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Task {
    public String title;
    public String description;
    public Student student;
    public Date dueDate;
    public float score;
    public String taskStatus;
}
