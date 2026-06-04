package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeRef;
import org.uniovi.dto.Student;
import org.uniovi.dto.StudentTask;
import org.uniovi.dto.Subject;
import org.uniovi.dto.Task;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.NodeService;
import org.uniovi.service.StudentService;
import org.uniovi.service.SubjectService;
import org.uniovi.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private NodeService nodeService;
    private SubjectService subjectService;
    private StudentService studentService;

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
    public StudentTask getTask(String UUID) {
        return null;
    }

    @Override
    public String updateScore(StudentTask studentTask) {
        //TODO configurable en datalist?
        if(studentTask.score < 0.0 || studentTask.score > 10.0)
            throw new IllegalArgumentException("Task score must be between 0 and 10");
        nodeService.checkAndSetNumberProperty(UnioviContentModel.PROP_STUDENT_TASK_SCORE, studentTask.score,nodeService.getNodeRef(studentTask.uuid),true);
        return studentTask.uuid;
    }

    @Override
    public void submitTask(StudentTask studentTask) {

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

    private String saveTask(Task task, NodeRef nodeRef){
        setPropertiesFromDto(task,nodeRef);
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
}
