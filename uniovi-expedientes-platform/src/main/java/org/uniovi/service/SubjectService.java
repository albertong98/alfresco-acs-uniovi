package org.uniovi.service;

import org.uniovi.dto.Subject;
import org.uniovi.dto.SubjectEnrollment;

public interface SubjectService {

    String createSubject(Subject subject);
    String updateSubject(Subject subject);
    Subject getSubject(String UUID);
    String enrollSubject(SubjectEnrollment subjectEnrollment);
}
