package org.uniovi.webscript.task;

import org.uniovi.dto.Task;
import org.uniovi.service.TaskService;
import org.uniovi.webscript.command.GetWebScript;

public class GetTask extends GetWebScript<Task> {
    private TaskService taskService;
    @Override
    public Task getData(String UUID) {
        return taskService.getTask(UUID);
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
