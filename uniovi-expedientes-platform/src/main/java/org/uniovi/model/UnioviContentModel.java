package org.uniovi.model;

import org.alfresco.service.namespace.QName;

public class UnioviContentModel {
    private static final String unioviNamespaceUri = "http://www.uniovi.es/model/content/1.0";

    /** ==== TYPES ==== **/
    public static final QName TYPE_RECORD = QName.createQName(unioviNamespaceUri,"record");
    public static final QName TYPE_STUDENT = QName.createQName(unioviNamespaceUri,"student");
    public static final QName TYPE_TASK = QName.createQName(unioviNamespaceUri,"task");

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
    public static final QName PROP_TASK_SCORE = QName.createQName(unioviNamespaceUri,"score");

    public static final QName ASPECT_STUDENT = QName.createQName(unioviNamespaceUri,"studentAsp");
    public static final QName PROP_STUDENT_ID = QName.createQName(unioviNamespaceUri,"studentId");
    public static final QName PROP_STUDENT_UO = QName.createQName(unioviNamespaceUri,"studentUo");

    public static final QName PROP_STUDENT_NAME = QName.createQName(unioviNamespaceUri,"studentName");
    public static final QName PROP_STUDENT_SURNAME = QName.createQName(unioviNamespaceUri,"studentSurname");


    public static final QName ASPECT_STUDENTS = QName.createQName(unioviNamespaceUri,"studentsAsp");
    public static final QName PROP_STUDENTS_UUIDs = QName.createQName(unioviNamespaceUri,"studentUUIDs");


    public static final QName ASPECT_STATUS = QName.createQName(unioviNamespaceUri,"statusAsp");
    public static final QName PROP_STATUS = QName.createQName(unioviNamespaceUri,"status");


    /**CONSTANTS**/
    public static final String recordsSiteName ="records";
    public static final String studentsSiteName ="students";
    public static final String tasksSiteName ="tasks";


}
