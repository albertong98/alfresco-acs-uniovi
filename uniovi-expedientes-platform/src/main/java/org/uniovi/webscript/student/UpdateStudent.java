package org.uniovi.webscript.student;

import lombok.Setter;
import org.uniovi.dto.Student;
import org.uniovi.service.StudentService;
import org.uniovi.webscript.command.SaveWebScript;

@Setter
public class UpdateStudent extends SaveWebScript {
    private StudentService studentService;
    @Override
    public String executeSave(String json) {
        Student student = gson.fromJson(json,Student.class);
        return studentService.createStudent(student);
    }
}
