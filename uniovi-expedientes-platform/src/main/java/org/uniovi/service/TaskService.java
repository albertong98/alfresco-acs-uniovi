package org.uniovi.service;

import org.uniovi.dto.StudentTask;
import org.uniovi.dto.Task;

public interface TaskService {
    String createTask(Task task);
    String updateTask(Task task);
    Task getTask(String UUID);
    StudentTask getStudentTask(String UUID);

    String updateScore(StudentTask studentTask);

    String submitTask(StudentTask studentTask);

    void checkAndUpdatStudentTasks();
}
