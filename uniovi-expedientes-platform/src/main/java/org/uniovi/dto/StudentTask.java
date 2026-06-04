package org.uniovi.dto;

import org.springframework.extensions.webscripts.servlet.FormData;

import java.util.List;
import java.util.Map;

public class StudentTask{
    public String uuid;
    public String parentTaskUUID;
    public String studentUUID;
    public Float score;
    public String taskStatus;

    public List<File> submissionData;
    public Map<String, FormData.FormField> submissions;

    public StudentTask(String parentTaskUUID,String uuid,String studentUUID, Float score, String taskStatus) {
        this.parentTaskUUID = parentTaskUUID;
        this.uuid = uuid;
        this.studentUUID = studentUUID;
        this.score = score;
        this.taskStatus = taskStatus;
    }

    public StudentTask(){}
}
