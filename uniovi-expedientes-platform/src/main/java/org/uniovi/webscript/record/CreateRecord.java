package org.uniovi.webscript.record;

import org.uniovi.service.RecordService;
import org.uniovi.dto.Record;
import org.uniovi.webscript.command.SaveWebScript;

public class CreateRecord extends SaveWebScript {
    private RecordService recordService;
    @Override
    public String executeSave(String json) {
        return recordService.createRecord(gson.fromJson(json, Record.class));
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }
}
