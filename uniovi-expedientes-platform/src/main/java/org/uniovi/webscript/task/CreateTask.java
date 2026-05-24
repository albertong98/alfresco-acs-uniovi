package org.uniovi.webscript.task;


import org.uniovi.dto.Task;
import org.uniovi.service.TaskService;
import org.uniovi.webscript.command.SaveWebScript;

public class CreateTask extends SaveWebScript {
    private TaskService taskService;
    @Override
    public String executeSave(String json) {
        return taskService.createTask(gson.fromJson(json,Task.class));
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
