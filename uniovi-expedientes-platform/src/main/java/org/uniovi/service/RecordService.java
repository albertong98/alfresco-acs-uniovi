package org.uniovi.service;

import org.uniovi.dto.Record;

public interface RecordService {
    String createRecord(Record record);
    String updateRecord(Record record);
    Record getRecord(String UUID);
}
