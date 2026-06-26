package org.uniovi.webscript.task;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.StudentTask;
import org.uniovi.service.TaskService;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;

public class SubmitStudentTask extends SaveWebScript {
    private TaskService taskService;
    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files) {
        StudentTask studentTask = gson.fromJson(json, StudentTask.class);
        studentTask.submissions = files;
        return taskService.submitTask(studentTask);
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
}
