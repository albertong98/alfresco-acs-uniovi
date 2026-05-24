package org.uniovi.webscript.student;

import org.uniovi.dto.Student;
import org.uniovi.service.StudentService;
import org.uniovi.webscript.command.SaveWebScript;


public class UpdateStudent extends SaveWebScript {
    private StudentService studentService;
    @Override
    public String executeSave(String json) {
        return studentService.updateStudent(gson.fromJson(json,Student.class));
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
