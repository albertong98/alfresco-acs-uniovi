package org.uniovi.webscript.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.repo.content.MimetypeMap;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;
import org.springframework.extensions.webscripts.servlet.FormData;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class SaveWebScript extends AbstractWebScript {
    protected Gson gson;
    protected final String PARAM_DATA = "data";

    public SaveWebScript(){
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
    }
    @Override
    public void execute(WebScriptRequest webScriptRequest, WebScriptResponse webScriptResponse) throws IOException {
        try{
            //TODO Revisar si puedo meter FormField en File
            FormData formData = (FormData) webScriptRequest.parseContent();
            Map<String, FormData.FormField> files = Arrays.stream(
                    formData.getFields()).filter(field -> field.getIsFile()
            ).collect(Collectors.toMap(
                    formField -> formField.getName()
                    ,formField -> formField
            ));

            String ID = executeSave(webScriptRequest.getParameter(PARAM_DATA),files);

            webScriptResponse.setStatus(HttpStatus.SC_CREATED);
            webScriptResponse.setContentType(MimetypeMap.MIMETYPE_TEXT_PLAIN);
            webScriptResponse.getWriter().write(ID);
        } catch (IllegalArgumentException | JSONException e){
            throw new WebScriptException(HttpStatus.SC_BAD_REQUEST,e.getMessage());
        }catch(AlfrescoRuntimeException e){
            throw new WebScriptException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal server Error");
        }
    }

    public abstract String executeSave(String json,Map<String,FormData.FormField> files);
}
