package org.uniovi.model;

import org.alfresco.service.namespace.QName;

public class UnioviContentModel {
    private static final String unioviNamespaceUri = "http://www.uniovi.es/model/content/1.0";

    /** ==== TYPES ==== **/
    public static final QName TYPE_RECORD = QName.createQName(unioviNamespaceUri,"record");
    public static final QName TYPE_STUDENT = QName.createQName(unioviNamespaceUri,"student");
    public static final QName TYPE_TASK = QName.createQName(unioviNamespaceUri,"task");
    public static final QName TYPE_STUDENT_TASK = QName.createQName(unioviNamespaceUri,"studentTask");
    public static final QName TYPE_FILE = QName.createQName(unioviNamespaceUri,"file");
    public static final QName TYPE_SUBJECT = QName.createQName(unioviNamespaceUri,"subject");

    /** ==== ASPECTS ==== **/
    public static final QName ASPECT_RECORD = QName.createQName(unioviNamespaceUri,"recordAsp");
    public static final QName PROP_RECORD_NUMBER = QName.createQName(unioviNamespaceUri,"recordNumber");
    public static final QName PROP_OPENING_DATE = QName.createQName(unioviNamespaceUri,"openingDate");
    public static final QName PROP_RECORD_TITLE = QName.createQName(unioviNamespaceUri,"recordTitle");
    public static final QName PROP_CENTER = QName.createQName(unioviNamespaceUri,"center");
    public static final QName PROP_TYPE = QName.createQName(unioviNamespaceUri,"type");
    public static final QName PROP_RESPONSIBLE = QName.createQName(unioviNamespaceUri,"responsible");


    public static final QName ASPECT_TASK = QName.createQName(unioviNamespaceUri,"taskAsp");
    public static final QName PROP_TASK_TITLE = QName.createQName(unioviNamespaceUri,"taskTitle");
    public static final QName PROP_TASK_DESCRIPTION = QName.createQName(unioviNamespaceUri,"description");
    public static final QName PROP_TASK_DUE_DATE = QName.createQName(unioviNamespaceUri,"dueDate");
    public static final QName PROP_SUBJECT_UUID = QName.createQName(unioviNamespaceUri,"subjectUUID");
    public static final QName PROP_TASK_SUBJECT_NAME = QName.createQName(unioviNamespaceUri,"taskSubjectName");

    public static final QName ASPECT_STUDENT_TASK = QName.createQName(unioviNamespaceUri,"studentTaskAsp");
    public static final QName PROP_STUDENT_TASK_SCORE = QName.createQName(unioviNamespaceUri,"score");
    public static final QName PROP_STUDENT_TASK_PARENT_UUID = QName.createQName(unioviNamespaceUri,"parentTaskUUID");
    public static final QName PROP_STUDENT_TASK_DUE_DATE = QName.createQName(unioviNamespaceUri,"studentTaskDueDate");
    public static final QName PROP_STUDENT_TASK_TITLE = QName.createQName(unioviNamespaceUri,"studentTaskTitle");

    public static final QName ASPECT_STUDENT = QName.createQName(unioviNamespaceUri,"studentAsp");
    public static final QName PROP_STUDENT_ID = QName.createQName(unioviNamespaceUri,"studentId");
    public static final QName PROP_STUDENT_UO = QName.createQName(unioviNamespaceUri,"studentUo");

    public static final QName PROP_STUDENT_NAME = QName.createQName(unioviNamespaceUri,"studentName");
    public static final QName PROP_STUDENT_SURNAME = QName.createQName(unioviNamespaceUri,"studentSurname");


    public static final QName ASPECT_STUDENT_UUID = QName.createQName(unioviNamespaceUri,"studentUUIDAsp");
    public static final QName PROP_STUDENT_UUID = QName.createQName(unioviNamespaceUri,"studentUUID");


    public static final QName ASPECT_STATUS = QName.createQName(unioviNamespaceUri,"statusAsp");
    public static final QName PROP_STATUS = QName.createQName(unioviNamespaceUri,"status");
    public static final QName PROP_OBSERVATIONS = QName.createQName(unioviNamespaceUri,"status");

    public static final QName ASPECT_SUBJECT = QName.createQName(unioviNamespaceUri,"subjectAsp");
    public static final QName PROP_SCHOOL_YEAR = QName.createQName(unioviNamespaceUri,"schoolYear");
    public static final QName PROP_SUBJECT_NAME = QName.createQName(unioviNamespaceUri,"subjectName");
    public static final QName PROP_ENROLLED = QName.createQName(unioviNamespaceUri,"enrolled");

    public static final QName ASPECT_FILES = QName.createQName(unioviNamespaceUri,"filesAsp");
    public static final QName PROP_FILES = QName.createQName(unioviNamespaceUri,"filesUUIDs");
    /**CONSTANTS**/
    public static final String recordsSiteName ="records";
    public static final String studentsSiteName ="students";
    public static final String tasksSiteName ="tasks";
    public static final String subjectSiteName ="subjects";


    public static final String taskPendingStatus ="Pendiente";
    public static final String taskSubmittedStatus ="Entregado";
    public static final String taskNotSubmittedStatus ="No entregado";
    public static final String taskPassedStatus ="Aprobado";
    public static final String taskFailedStatus ="Suspenso";

    public static final double passingScore = 5.0;
    public static final double minScore = 0.0;
    public static final double maxScore = 10.0;
}
