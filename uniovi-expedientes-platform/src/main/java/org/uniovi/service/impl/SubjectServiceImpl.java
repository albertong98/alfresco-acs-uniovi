package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeRef;
import org.uniovi.dto.Subject;
import org.uniovi.dto.SubjectEnrollment;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.FileService;
import org.uniovi.service.NodeService;
import org.uniovi.service.StudentService;
import org.uniovi.service.SubjectService;

import java.util.HashSet;

public class SubjectServiceImpl implements SubjectService {
    private NodeService nodeService;
    private FileService fileService;

    private StudentService studentService;

    @Override
    public String createSubject(Subject subject) {
        return saveSubject(subject,nodeService.createNodeRefInYearFolder(UnioviContentModel.TYPE_SUBJECT, UnioviContentModel.subjectSiteName));
    }

    @Override
    public String updateSubject(Subject subject) {
        return saveSubject(subject,nodeService.getNodeRef(subject.uuid));
    }

    @Override
    public Subject getSubject(String UUID) {
        return getSubjectProperties(nodeService.getNodeRef(UUID));
    }

    @Override
    public String enrollSubject(SubjectEnrollment subjectEnrollment) {
        NodeRef student = studentService.findStudentFolderByUO(subjectEnrollment.studentUo);
        for(String subjectUUID : subjectEnrollment.subjectUUIDs){
            NodeRef subject = nodeService.getNodeRef(subjectUUID);
            HashSet<String> enrolled = nodeService.getCollectionProperty(subject,UnioviContentModel.PROP_ENROLLED);
            enrolled.add(student.getId());
            nodeService.checkAndSetCollectionProperty(UnioviContentModel.PROP_ENROLLED,enrolled,subject,true);
        }
        return student.getId();
    }

    private String saveSubject(Subject subject, NodeRef nodeRef){
        setPropertiesFromDto(subject,nodeRef);
        fileService.insertFilesIntoNode(nodeRef,subject.fileData,subject.files);
        return nodeRef.getId();
    }

    private void setPropertiesFromDto(Subject subject, NodeRef nodeRef){
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_SUBJECT_NAME,subject.name,nodeRef,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_SCHOOL_YEAR,subject.schoolYear,nodeRef,true);
        nodeService.checkAndSetCollectionProperty(UnioviContentModel.PROP_ENROLLED,subject.enrolledStudentsUUID,nodeRef,false);
    }

    private Subject getSubjectProperties(NodeRef nodeRef){
        Subject subject = new Subject();
        subject.name = nodeService.getStringProperty(nodeRef,UnioviContentModel.PROP_SUBJECT_NAME);
        subject.schoolYear = nodeService.getStringProperty(nodeRef,UnioviContentModel.PROP_SCHOOL_YEAR);
        subject.enrolledStudentsUUID = nodeService.getCollectionProperty(nodeRef,UnioviContentModel.PROP_ENROLLED);
        return subject;
    }

    //SETTERS
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
