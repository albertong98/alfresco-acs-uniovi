package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeRef;
import org.uniovi.dto.Record;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.FileService;
import org.uniovi.service.NodeService;
import org.uniovi.service.RecordService;
import org.uniovi.service.StudentService;
public class RecordServiceImpl implements RecordService {
    private NodeService nodeService;
    private StudentService studentService;

    private FileService fileService;
    @Override
    public String createRecord(Record record) {
        return saveRecord(record,nodeService.createNodeRefInYearFolder(UnioviContentModel.TYPE_RECORD,UnioviContentModel.recordsSiteName));
    }

    @Override
    public String updateRecord(Record record) {
        return saveRecord(record,nodeService.getNodeRef(record.uuid));
    }

    private String saveRecord(Record record,NodeRef nodeRef){
        setPropertiesFromDto(record,nodeRef);
        studentService.setStudent(record.student,nodeRef);
        fileService.insertFilesIntoNode(nodeRef,record.fileData,record.files);
        return nodeRef.getId();
    }
    @Override
    public Record getRecord(String UUID) {
        return getRecordProperties( nodeService.getNodeRef(UUID) );
    }

    private void setPropertiesFromDto(Record record, NodeRef node){
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_RECORD_NUMBER,record.recordNumber,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_RECORD_TITLE,record.recordTitle,node,true);
        nodeService.checkAndSetDateProperty(UnioviContentModel.PROP_OPENING_DATE,record.openingDate,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_STATUS,record.status,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_CENTER,record.center,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_TYPE,record.type,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_RESPONSIBLE,record.responsible,node,false);
    }

    private Record getRecordProperties(NodeRef node){
        Record record = new Record();
        record.recordNumber = nodeService.getStringProperty(node,UnioviContentModel.PROP_RECORD_NUMBER);
        record.recordTitle = nodeService.getStringProperty(node,UnioviContentModel.PROP_RECORD_TITLE);
        record.openingDate = nodeService.getDateProperty(node,UnioviContentModel.PROP_OPENING_DATE);
        record.status = nodeService.getStringProperty(node,UnioviContentModel.PROP_STATUS);
        record.center = nodeService.getStringProperty(node,UnioviContentModel.PROP_CENTER);
        record.type = nodeService.getStringProperty(node,UnioviContentModel.PROP_TYPE);
        record.responsible = nodeService.getStringProperty(node,UnioviContentModel.PROP_RESPONSIBLE);
        return record;
    }

    //SETTERS
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
