package org.uniovi.webscript.record;

import com.google.gson.Gson;
import lombok.Setter;
import org.uniovi.service.RecordService;
import org.uniovi.dto.Record;
import org.uniovi.webscript.command.CreateWebScript;


@Setter
public class CreateRecord extends CreateWebScript<Record> {
    private RecordService recordService;
    @Override
    public String executeCreate(Record data) {
        return recordService.createExpediente(data);
    }
    @Override
    public Record getData(String json) {
        return new Gson().fromJson(json, Record.class);
    }
}
