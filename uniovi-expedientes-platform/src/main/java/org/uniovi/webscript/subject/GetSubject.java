package org.uniovi.webscript.subject;

import org.uniovi.dto.Subject;
import org.uniovi.service.SubjectService;
import org.uniovi.webscript.command.GetWebScript;

public class GetSubject extends GetWebScript<Subject> {
    private SubjectService subjectService;
    @Override
    public Subject getData(String UUID) {
        return subjectService.getSubject(UUID);
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
}
