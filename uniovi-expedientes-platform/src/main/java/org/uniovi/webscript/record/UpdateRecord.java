package org.uniovi.webscript.record;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.Record;
import org.uniovi.service.RecordService;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;


public class UpdateRecord extends SaveWebScript {
    private RecordService recordService;

    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files){
        Record record = gson.fromJson(json, Record.class);
        record.files = files;
        return recordService.updateRecord(record);
    }

}
