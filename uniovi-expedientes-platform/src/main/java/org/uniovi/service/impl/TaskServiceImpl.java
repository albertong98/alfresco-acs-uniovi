package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.search.SearchService;
import org.uniovi.dto.Task;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.NodeService;
import org.uniovi.service.TaskService;

public class TaskServiceImpl implements TaskService {
    private NodeService nodeService;
    private SearchService searchService;
    @Override
    public String createTask(Task task) {
        return saveTask(task,nodeService.createNodeRef(UnioviContentModel.TYPE_TASK,UnioviContentModel.tasksSiteName));
    }

    @Override
    public String updateTask(Task task) {
        return saveTask(task,nodeService.getNodeRef(task.uuid));
    }

    @Override
    public Task getTask(String UUID) {
        return null;
    }

    @Override
    public String updateScore(Task task) {
        //TODO configurable en datalist?
        if(task.score < 0.0 || task.score > 10.0)
            throw new IllegalArgumentException("Task score must be between 0 and 10");
        nodeService.checkAndSetNumberProperty(UnioviContentModel.PROP_TASK_SCORE,task.score,nodeService.getNodeRef(task.uuid),true);
        return task.uuid;
    }

    private void setPropertiesFromDto(Task task, NodeRef node){
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_TASK_TITLE,task.title,node,true);
        nodeService.checkAndSetStringProperty(UnioviContentModel.PROP_TASK_DESCRIPTION,task.description,node,true);
        nodeService.checkAndSetDateProperty(UnioviContentModel.PROP_TASK_DUE_DATE,task.dueDate,node,true);
        nodeService.checkAndSetNumberProperty(UnioviContentModel.PROP_TASK_SCORE,task.score,node,true);
    }

    private String saveTask(Task task, NodeRef nodeRef){
        setPropertiesFromDto(task,nodeRef);
        return nodeRef.getId();
    }

    //SETTERS
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}
