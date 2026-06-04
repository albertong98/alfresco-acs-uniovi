package org.uniovi.service;

import org.uniovi.dto.StudentTask;
import org.uniovi.dto.Task;

public interface TaskService {
    String createTask(Task task);
    String updateTask(Task task);
    StudentTask getTask(String UUID);

    String updateScore(StudentTask studentTask);

    void submitTask(StudentTask studentTask);
}
