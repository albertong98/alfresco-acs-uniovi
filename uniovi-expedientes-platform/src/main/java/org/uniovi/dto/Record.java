package org.uniovi.dto;

import org.springframework.extensions.webscripts.servlet.FormData;

import java.util.*;

public class Record {
    public String uuid;
    public String recordNumber;
    public Student student;
    public String recordTitle;
    public Date openingDate;
    public String status;
    public String center;
    public String type;
    public String responsible;

    public List<File> fileData;
    public Map<String, FormData.FormField> files;

    public Record(String uuid,String recordNumber, Student student, String recordTitle, Date openingDate, String status, String center, String type, String responsible,List<File> fileData) {
        this.uuid = uuid;
        this.recordNumber = recordNumber;
        this.recordTitle = recordTitle;
        this.student = student;
        this.openingDate = openingDate;
        this.status = status;
        this.center = center;
        this.type = type;
        this.responsible = responsible;
        this.files = new HashMap<>();
        this.fileData = fileData;
    }

    public Record(){}
}
