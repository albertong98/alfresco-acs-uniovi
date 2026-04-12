package org.uniovi.service.impl;

import lombok.Setter;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.SearchService;
import org.uniovi.dto.Record;
import org.uniovi.service.RecordService;

@Setter
public class RecordServiceImpl implements RecordService {
    private NodeService nodeService;
    private SearchService searchService;
    @Override
    public String createExpediente(Record record) {
        return "En desarrollo";
    }
}
