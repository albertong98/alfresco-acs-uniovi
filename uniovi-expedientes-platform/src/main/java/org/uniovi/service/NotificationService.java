package org.uniovi.service;

import org.uniovi.dto.Student;
import org.uniovi.dto.StudentTask;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface NotificationService {
    void sendScoreNotificationToStudent(Student student, StudentTask studentTask);
}
