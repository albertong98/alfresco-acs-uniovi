package org.uniovi.dto;

import org.springframework.extensions.webscripts.servlet.FormData;

import java.util.Date;
import java.util.Map;

public class Task {
    public String uuid;
    public String subjectUUID;
    public String title;
    public String description;

    public Date dueDate;

    public Map<String, FormData.FormField> files;

    public Task(String uuid, String subjectUUID, String title, String description, Date dueDate) {
        this.uuid = uuid;
        this.subjectUUID = subjectUUID;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Task(){}
}
