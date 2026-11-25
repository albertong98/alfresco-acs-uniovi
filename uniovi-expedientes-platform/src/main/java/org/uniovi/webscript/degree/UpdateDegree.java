package org.uniovi.webscript.degree;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.Degree;
import org.uniovi.service.DegreeService;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;

public class UpdateDegree extends SaveWebScript {
    private DegreeService degreeService;

    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files) {
        Degree degree = gson.fromJson(json,Degree.class);
        return degreeService.updateDegree(degree);
    }

    public void setDegreeService(DegreeService degreeService) {
        this.degreeService = degreeService;
    }
}
