package org.uniovi.webscript.degree;

import org.uniovi.service.DegreeService;
import org.uniovi.webscript.command.GetWebScript;

public class GetDegree extends GetWebScript {
    private DegreeService degreeService;

    @Override
    public Object getData(String UUID) {
        return degreeService.getDegree(UUID);
    }

    public void setDegreeService(DegreeService degreeService) {
        this.degreeService = degreeService;
    }
}
