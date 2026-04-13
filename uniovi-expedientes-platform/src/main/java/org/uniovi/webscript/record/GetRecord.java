package org.uniovi.webscript.record;

import lombok.Setter;
import org.uniovi.dto.Record;
import org.uniovi.service.RecordService;
import org.uniovi.webscript.command.GetWebScript;
@Setter
public class GetRecord extends GetWebScript<Record> {
    private RecordService recordService;
    @Override
    public Record getData(String UUID) {
        return recordService.getRecord(UUID);
    }
}
