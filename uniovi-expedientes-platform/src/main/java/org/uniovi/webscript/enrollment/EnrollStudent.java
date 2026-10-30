package org.uniovi.webscript.enrollment;

import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.webscript.command.SaveWebScript;

import java.util.Map;

public class EnrollStudent extends SaveWebScript {
    @Override
    public String executeSave(String json, Map<String, FormData.FormField> files) {
        return null;
    }
}
