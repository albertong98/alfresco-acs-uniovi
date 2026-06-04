package org.uniovi.webscript.record;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.service.RecordService;
import org.uniovi.dto.Record;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;

public class CreateRecord extends SaveWebScript {
    private RecordService recordService;

    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files) {
        Record record = gson.fromJson(json, Record.class);
        record.files = files;
        return recordService.createRecord(record);
    }

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }
}
