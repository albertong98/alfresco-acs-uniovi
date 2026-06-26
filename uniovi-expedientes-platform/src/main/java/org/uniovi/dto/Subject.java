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

    public List<File> fileData;
    public Map<String, FormData.FormField> files;

}