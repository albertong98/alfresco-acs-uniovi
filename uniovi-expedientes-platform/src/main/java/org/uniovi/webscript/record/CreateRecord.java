package org.uniovi.webscript.record;

import lombok.Setter;
import org.uniovi.service.RecordService;
import org.uniovi.dto.Record;
import org.uniovi.webscript.command.SaveWebScript;


@Setter
public class CreateRecord extends SaveWebScript {
    private RecordService recordService;
    @Override
    public String executeSave(String json) {
        return recordService.createRecord(gson.fromJson(json, Record.class));
    }
}
