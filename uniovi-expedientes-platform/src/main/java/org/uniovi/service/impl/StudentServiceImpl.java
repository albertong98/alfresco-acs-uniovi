package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.search.SearchService;
import org.uniovi.dto.Student;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.NodeService;
import org.uniovi.service.StudentService;

public class StudentServiceImpl implements StudentService {
    private NodeService nodeService;
    private SearchService searchService;
    @Override
    public String createStudent(Student student) {
        return saveStudent(student,nodeService.createNodeRef(UnioviContentModel.TYPE_STUDENT,UnioviContentModel.studentsSiteName));
    }

    @Override
    public String updateStudent(Student student) {
        return saveStudent(student,nodeService.getNodeRef(student.uuid));
    }

    @Override
    public Student getStudent(String UUID) {
        return getStudentProperties(nodeService.getNodeRef(UUID));
    }

    private void setPropertiesFromDto(Student student, NodeRef node) {
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STUDENT_ID,student.id,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STUDENT_UO,student.uo,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STUDENT_NAME,student.name,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STUDENT_SURNAME,student.surname,node,true);
    }

    private String saveStudent(Student student, NodeRef nodeRef){
        setPropertiesFromDto(student,nodeRef);
        return nodeRef.getId();
    }

    private Student getStudentProperties(NodeRef node){
        Student student = new Student();
        student.uuid = node.getId();
        student.id = nodeService.getStringProperty(node,UnioviContentModel.PROP_STUDENT_ID);
        student.uo = nodeService.getStringProperty(node,UnioviContentModel.PROP_STUDENT_UO);
        student.name = nodeService.getStringProperty(node,UnioviContentModel.PROP_STUDENT_NAME);
        student.surname = nodeService.getStringProperty(node,UnioviContentModel.PROP_STUDENT_SURNAME);
        return student;
    }

    //SETTERS
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}
