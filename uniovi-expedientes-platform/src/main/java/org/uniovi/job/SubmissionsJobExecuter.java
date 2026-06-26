package org.uniovi.job;

import org.uniovi.service.TaskService;

public class SubmissionsJobExecuter {
    private TaskService taskService;
    public void execute() {
        taskService.checkAndUpdatStudentTasks();
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
