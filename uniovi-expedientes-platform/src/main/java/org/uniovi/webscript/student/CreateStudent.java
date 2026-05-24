package org.uniovi.webscript.student;

import org.uniovi.dto.Student;
import org.uniovi.service.StudentService;
import org.uniovi.webscript.command.SaveWebScript;


public class CreateStudent extends SaveWebScript {
    private StudentService studentService;
    @Override
    public String executeSave(String json) {
        return studentService.createStudent(gson.fromJson(json,Student.class));
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
