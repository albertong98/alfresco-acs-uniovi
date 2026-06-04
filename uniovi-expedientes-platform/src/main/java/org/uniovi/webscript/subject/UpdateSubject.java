package org.uniovi.webscript.subject;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.Subject;
import org.uniovi.service.SubjectService;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;

public class UpdateSubject extends SaveWebScript {
    private SubjectService subjectService;
    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files) {
        Subject subject = gson.fromJson(json, Subject.class);
        subject.files = files;
        return subjectService.updateSubject(subject);
    }
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
}
