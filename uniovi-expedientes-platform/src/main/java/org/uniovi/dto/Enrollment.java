package org.uniovi.dto;

import java.util.List;

public class Enrollment {
    public String id;
    public String schoolYear;
    public String enrolledStudentUUID;
    public List<String> enrolledSubjecsUUIDs;
    public String enrolledDegreeUUID;

    public Enrollment(){}
    public Enrollment(String id, String schoolYear, String enrolledStudentUUID, List<String> enrolledSubjecsUUIDs, String enrolledDegreeUUID) {
        this.id = id;
        this.schoolYear = schoolYear;
        this.enrolledStudentUUID = enrolledStudentUUID;
        this.enrolledSubjecsUUIDs = enrolledSubjecsUUIDs;
        this.enrolledDegreeUUID = enrolledDegreeUUID;
    }
}
