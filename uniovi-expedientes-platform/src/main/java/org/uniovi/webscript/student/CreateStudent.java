package org.uniovi.webscript.student;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.Student;
import org.uniovi.service.StudentService;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;


public class CreateStudent extends SaveWebScript {
    private StudentService studentService;

    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files) {
        return studentService.createStudent(gson.fromJson(json,Student.class));
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
