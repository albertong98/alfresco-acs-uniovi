package org.uniovi.webscript.student;

import lombok.Setter;
import org.uniovi.dto.Student;
import org.uniovi.service.StudentService;
import org.uniovi.webscript.command.GetWebScript;
@Setter
public class GetStudent extends GetWebScript<Student> {
    private StudentService studentService;
    @Override
    public Student getData(String UUID) {
        return studentService.getStudent(UUID);
    }
}
