package org.uniovi.service;

import org.alfresco.service.cmr.repository.NodeRef;
import org.uniovi.dto.Record;
import org.uniovi.dto.Student;

public interface StudentService {
    String createStudent(Student student);
    String updateStudent(Student student);
    Student getStudent(String UUID);
}
