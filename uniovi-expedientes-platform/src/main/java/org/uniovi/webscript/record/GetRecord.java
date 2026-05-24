package org.uniovi.webscript.record;

import org.uniovi.dto.Record;
import org.uniovi.service.RecordService;
import org.uniovi.webscript.command.GetWebScript;

public class GetRecord extends GetWebScript<Record> {
    private RecordService recordService;
    @Override
    public Record getData(String UUID) {
        return recordService.getRecord(UUID);
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }
}
