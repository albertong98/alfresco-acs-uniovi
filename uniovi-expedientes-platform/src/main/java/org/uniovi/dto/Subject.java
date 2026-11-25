package org.uniovi.dto;

import org.springframework.extensions.webscripts.servlet.FormData;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Subject {
    public String uuid;
    public String schoolYear;
    public String name;
    public HashSet<String> enrolledStudentsUUID;

    public String degreeYear;
    public String degreeUUID;
    public List<File> fileData;
    public Map<String, FormData.FormField> files;

    public Subject(){};
    public Subject(String uuid, String schoolYear, String name, HashSet<String> enrolledStudentsUUID, String degreeYear, String degreeUUID, List<File> fileData, Map<String, FormData.FormField> files) {
        this.uuid = uuid;
        this.schoolYear = schoolYear;
        this.name = name;
        this.enrolledStudentsUUID = enrolledStudentsUUID;
        this.degreeYear = degreeYear;
        this.degreeUUID = degreeUUID;
        this.fileData = fileData;
        this.files = files;
    }
}