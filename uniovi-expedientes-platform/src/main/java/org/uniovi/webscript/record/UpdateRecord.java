package org.uniovi.webscript.record;

import org.uniovi.dto.Record;
import org.uniovi.service.RecordService;
import org.uniovi.webscript.command.SaveWebScript;


public class UpdateRecord extends SaveWebScript {
    private RecordService recordService;
    @Override
    public String executeSave(String json) {
        return recordService.updateRecord(gson.fromJson(json, Record.class));
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }
}
