package org.uniovi.webscript.task;

import org.uniovi.dto.StudentTask;
import org.uniovi.service.TaskService;
import org.uniovi.webscript.command.GetWebScript;

public class GetStudentTask extends GetWebScript<StudentTask> {
    private TaskService taskService;
    @Override
    public StudentTask getData(String UUID) {
        return taskService.getStudentTask(UUID);
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
