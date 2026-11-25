package org.uniovi.service;

import org.uniovi.dto.Degree;

public interface DegreeService {
    String createDegree(Degree degree);
    String updateDegree(Degree degree);
    Degree getDegree(String UUID);
}
