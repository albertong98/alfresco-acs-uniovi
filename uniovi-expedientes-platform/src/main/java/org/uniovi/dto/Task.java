package org.uniovi.dto;

import org.springframework.extensions.webscripts.servlet.FormData;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Task {
    public String uuid;
    public String subjectUUID;
    public String title;
    public String description;

    public Date dueDate;

    public List<File> fileData;
    public Map<String, FormData.FormField> files;

    public Task(String uuid, String subjectUUID, String title, String description, Date dueDate, List<File> fileData) {
        this.uuid = uuid;
        this.subjectUUID = subjectUUID;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.fileData = fileData;
    }

    public Task(){}
}
