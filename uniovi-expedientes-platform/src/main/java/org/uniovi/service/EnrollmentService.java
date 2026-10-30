package org.uniovi.service;

import org.alfresco.service.cmr.repository.NodeRef;
import org.uniovi.dto.Enrollment;

public interface EnrollmentService {
    public NodeRef enroll(Enrollment enrollment);
}
