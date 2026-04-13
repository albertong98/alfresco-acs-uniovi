package org.uniovi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Record {
    public String recordNumber;
    public Student student;
    public Date openingDate;
    public String recordStatus;
    public String center;
}
