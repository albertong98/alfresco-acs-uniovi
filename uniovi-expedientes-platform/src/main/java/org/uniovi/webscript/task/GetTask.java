package org.uniovi.webscript.task;

import lombok.Setter;
import org.uniovi.dto.Task;
import org.uniovi.service.TaskService;
import org.uniovi.webscript.command.GetWebScript;
@Setter
public class GetTask extends GetWebScript<Task> {
    private TaskService taskService;
    @Override
    public Task getData(String UUID) {
        return taskService.getTask(UUID);
    }
}
