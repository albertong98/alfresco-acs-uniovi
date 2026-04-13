package org.uniovi.service.impl;

import lombok.Setter;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.SearchService;
import org.uniovi.dto.Record;
import org.uniovi.dto.Student;
import org.uniovi.service.StudentService;

@Setter
public class StudentServiceImpl implements StudentService {
    private NodeService nodeService;
    private SearchService searchService;
    @Override
    public String createStudent(Student student) {
        return "En desarrollo";
    }

    @Override
    public String updateStudent(Record record) {
        return "En desarrollo";
    }

    @Override
    public Student getStudent(String UUID) {
        return null;
    }
}
