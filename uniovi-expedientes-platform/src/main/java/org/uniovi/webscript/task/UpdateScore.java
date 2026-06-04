package org.uniovi.webscript.task;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.StudentTask;
import org.uniovi.service.TaskService;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;

public class UpdateScore extends SaveWebScript {
    private TaskService taskService;

    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files){
        return taskService.updateScore(gson.fromJson(json, StudentTask.class));
    }
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

}
