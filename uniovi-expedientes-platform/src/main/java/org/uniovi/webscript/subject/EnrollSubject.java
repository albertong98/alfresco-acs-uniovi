package org.uniovi.webscript.subject;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.SubjectEnrollment;
import org.uniovi.service.SubjectService;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;

public class EnrollSubject extends SaveWebScript {
    private SubjectService subjectService;
    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files) {
        SubjectEnrollment subjectEnrollment = gson.fromJson(json,SubjectEnrollment.class);
        return subjectService.enrollSubject(subjectEnrollment);
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
}
