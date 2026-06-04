package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeRef;
import org.uniovi.dto.Subject;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.NodeService;
import org.uniovi.service.SubjectService;

public class SubjectServiceImpl implements SubjectService {
    private NodeService nodeService;

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

    private String saveSubject(Subject subject, NodeRef nodeRef){
        setPropertiesFromDto(subject,nodeRef);
        return nodeRef.getId();
    }

    private void setPropertiesFromDto(Subject subject, NodeRef nodeRef){
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_SUBJECT_NAME,subject.name,nodeRef,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_SCHOOL_YEAR,subject.schoolYear,nodeRef,true);
        nodeService.checkAndSetCollectionProperty(UnioviContentModel.PROP_ENROLLED,subject.enrolledStudentsUUID,nodeRef,true);
        nodeService.checkAndSetCollectionProperty(UnioviContentModel.PROP_PROFESSORS,subject.professors,nodeRef,true);
    }

    private Subject getSubjectProperties(NodeRef nodeRef){
        Subject subject = new Subject();
        subject.name = nodeService.getStringProperty(nodeRef,UnioviContentModel.PROP_SUBJECT_NAME);
        subject.schoolYear = nodeService.getStringProperty(nodeRef,UnioviContentModel.PROP_SCHOOL_YEAR);
        subject.enrolledStudentsUUID = nodeService.getCollectionProperty(nodeRef,UnioviContentModel.PROP_ENROLLED);
        subject.professors = nodeService.getCollectionProperty(nodeRef,UnioviContentModel.PROP_PROFESSORS);
        return subject;
    }

    //SETTERS
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }
}
