package org.uniovi.webscript.task;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.Task;
import org.uniovi.service.TaskService;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;

public class UpdateTask extends SaveWebScript {
    private TaskService taskService;


    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files) {
        Task task = gson.fromJson(json, Task.class);
        task.files = files;
        return taskService.updateTask(task);
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

}
