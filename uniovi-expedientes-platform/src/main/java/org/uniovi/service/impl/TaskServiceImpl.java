package org.uniovi.service.impl;

import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.SearchService;
import org.uniovi.dto.Task;
import org.uniovi.service.TaskService;

public class TaskServiceImpl implements TaskService {
    private NodeService nodeService;
    private SearchService searchService;
    @Override
    public String createTask(Task task) {
        return "En desarrollo";
    }

    @Override
    public String updateTask(Task task) {
        return "En desarrollo";
    }

    @Override
    public Task getTask(String UUID) {
        return null;
    }
}
