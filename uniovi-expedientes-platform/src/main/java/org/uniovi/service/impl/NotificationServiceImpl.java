package org.uniovi.service.impl;

import org.uniovi.dto.Student;
import org.uniovi.dto.StudentTask;
import org.uniovi.service.NotificationService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendScoreNotificationToStudent(Student student, StudentTask studentTask) {

    }

    private void sendNotificationWithTemplate(List<String> toEmails, String template, Map<String, Serializable> params){

    }

}
