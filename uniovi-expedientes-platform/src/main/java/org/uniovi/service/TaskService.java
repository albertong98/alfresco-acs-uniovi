package org.uniovi.service;

import org.uniovi.dto.Task;

public interface TaskService {
    String createTask(Task task);
    String updateTask(Task task);
    Task getTask(String UUID);

    String updateScore(Task task);
}
