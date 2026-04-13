package org.uniovi.webscript.task;

import lombok.Setter;
import org.uniovi.dto.Task;
import org.uniovi.service.TaskService;
import org.uniovi.webscript.command.SaveWebScript;
@Setter
public class UpdateTask extends SaveWebScript {
    private TaskService taskService;

    @Override
    public String executeSave(String json) {
        return taskService.updateTask(gson.fromJson(json, Task.class));
    }
}
