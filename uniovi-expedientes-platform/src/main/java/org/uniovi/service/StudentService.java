package org.uniovi.service;

import org.uniovi.dto.Record;
import org.uniovi.dto.Student;

public interface StudentService {
    String createStudent(Student student);
    String updateStudent(Record record);
    Student getStudent(String UUID);
}
