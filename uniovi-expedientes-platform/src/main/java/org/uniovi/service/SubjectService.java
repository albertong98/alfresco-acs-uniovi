package org.uniovi.service;

import org.uniovi.dto.Subject;

public interface SubjectService {

    String createSubject(Subject subject);
    String updateSubject(Subject subject);
    Subject getSubject(String UUID);
}
