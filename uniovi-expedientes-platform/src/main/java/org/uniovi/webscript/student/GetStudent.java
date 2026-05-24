package org.uniovi.webscript.student;

import org.uniovi.dto.Student;
import org.uniovi.service.StudentService;
import org.uniovi.webscript.command.GetWebScript;

public class GetStudent extends GetWebScript<Student> {
    private StudentService studentService;
    @Override
    public Student getData(String UUID) {
        return studentService.getStudent(UUID);
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
