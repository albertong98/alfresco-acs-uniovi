package org.uniovi.webscript.student;

import com.google.gson.Gson;
import lombok.Setter;
import org.uniovi.dto.Student;
import org.uniovi.service.StudentService;
import org.uniovi.webscript.command.CreateWebScript;

@Setter
public class CreateStudent extends CreateWebScript<Student> {
    private StudentService studentService;
    @Override
    public String executeCreate(Student data) {
        return studentService.createStudent(data);
    }

    @Override
    public Student getData(String json) {
        return new Gson().fromJson(json,Student.class);
    }
}
