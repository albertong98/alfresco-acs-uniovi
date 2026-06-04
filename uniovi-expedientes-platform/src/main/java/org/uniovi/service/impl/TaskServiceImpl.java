package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.poi.ss.formula.functions.T;
import org.uniovi.dto.Student;
import org.uniovi.dto.StudentTask;
import org.uniovi.dto.Subject;
import org.uniovi.dto.Task;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.*;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private NodeService nodeService;
    private SubjectService subjectService;
    private StudentService studentService;
    private FileService fileService;

    @Override
    public String createTask(Task task) {
        Subject subject = subjectService.getSubject(task.subjectUUID);

        task.uuid = saveTask(task,nodeService.createNodeRefInYearFolder(UnioviContentModel.TYPE_TASK,UnioviContentModel.tasksSiteName));

        List<Student> studentList = subject.enrolledStudentsUUID.stream().map(uuid -> studentService.getStudent(uuid)).toList();
        for(Student student: studentList){
            createStudentTask(student,task);
        }

        return task.uuid;
    }

    @Override
    public String updateTask(Task task) {
        return saveTask(task,nodeService.getNodeRef(task.uuid));
    }

    @Override
    public Task getTask(String UUID) {
        return getTaskProperties(nodeService.getNodeRef(UUID));
    }

    @Override
    public StudentTask getStudentTask(String UUID) {
        return getStudentTaskProperties(nodeService.getNodeRef(UUID));
    }

    @Override
    public String updateScore(StudentTask studentTask) {
        //TODO configurable en datalist?
        if(studentTask.score < UnioviContentModel.minScore || studentTask.score > UnioviContentModel.maxScore)
            throw new IllegalArgumentException(String.format("Task score must be between %f and %f",UnioviContentModel.minScore,UnioviContentModel.maxScore));

        NodeRef nodeRef = nodeService.getNodeRef(studentTask.uuid);

        String status = studentTask.score <= UnioviContentModel.passingScore ? UnioviContentModel.taskPassedStatus : UnioviContentModel.taskFailedStatus;

        nodeService.checkAndSetNumberProperty(UnioviContentModel.PROP_STUDENT_TASK_SCORE, studentTask.score,nodeRef,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STATUS, status ,nodeRef,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_OBSERVATIONS, studentTask.observations,nodeRef,false);

        return studentTask.uuid;
    }

    @Override
    public void submitTask(StudentTask studentTask) {
        NodeRef nodeRef = nodeService.getNodeRef(studentTask.uuid);
        fileService.insertFilesIntoNode(nodeRef,studentTask.submissionData,studentTask.submissions);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STATUS,UnioviContentModel.taskSubmittedStatus,nodeRef,true);
    }

    private StudentTask createStudentTask(Student student, Task task){
        StudentTask studentTask = new StudentTask();

        NodeRef studentFolder = studentService.findStudentFolderByUO(student.uo);

        NodeRef nodeRef = nodeService.createNodeRef(UnioviContentModel.TYPE_STUDENT_TASK,studentFolder);

        studentTask.uuid = nodeRef.getId();
        studentTask.parentTaskUUID = task.uuid;
        studentTask.studentUUID = student.uuid;
        studentTask.taskStatus = UnioviContentModel.taskPendingStatus;

        setPropertiesFromDto(studentTask,nodeRef);

        return studentTask;
    }


    private void setPropertiesFromDto(StudentTask studentTask, NodeRef node){
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STUDENT_UUID, studentTask.studentUUID,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STUDENT_TASK_PARENT_UUID, studentTask.parentTaskUUID,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STATUS, studentTask.taskStatus,node,true);
    }

    private void setPropertiesFromDto(Task task, NodeRef node){
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_TASK_TITLE, task.title,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_TASK_DESCRIPTION, task.description,node,true);
        nodeService.checkAndSetDateProperty(UnioviContentModel.PROP_TASK_DUE_DATE, task.dueDate,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_SUBJECT_UUID, task.subjectUUID,node,true);
    }

    private Task getTaskProperties(NodeRef node){
        Task task = new Task();
        task.title = nodeService.getStringProperty(node,UnioviContentModel.PROP_TASK_TITLE);
        task.description = nodeService.getStringProperty(node,UnioviContentModel.PROP_TASK_DESCRIPTION);
        task.dueDate = nodeService.getDateProperty(node,UnioviContentModel.PROP_TASK_DUE_DATE);
        task.subjectUUID = nodeService.getStringProperty(node,UnioviContentModel.PROP_SUBJECT_UUID);
        return task;
    }

    private StudentTask getStudentTaskProperties(NodeRef node){
        StudentTask studentTask = new StudentTask();
        studentTask.taskStatus = nodeService.getStringProperty(node,UnioviContentModel.PROP_STATUS);
        studentTask.studentUUID = nodeService.getStringProperty(node,UnioviContentModel.PROP_STUDENT_UUID);
        studentTask.parentTaskUUID = nodeService.getStringProperty(node,UnioviContentModel.PROP_STUDENT_TASK_PARENT_UUID);
        studentTask.observations = nodeService.getStringProperty(node,UnioviContentModel.PROP_OBSERVATIONS);
        studentTask.score = (Double) nodeService.getNumberProperty(node,UnioviContentModel.PROP_STUDENT_TASK_SCORE);
        return studentTask;
    }

    private String saveTask(Task task, NodeRef nodeRef){
        setPropertiesFromDto(task,nodeRef);
        fileService.insertFilesIntoNode(nodeRef,task.fileData,task.files);
        return nodeRef.getId();
    }

    //SETTERS
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
