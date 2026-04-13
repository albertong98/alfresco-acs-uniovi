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

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class GetWebScript<T> extends AbstractWebScript {
    @Override
    public void execute(WebScriptRequest webScriptRequest, WebScriptResponse webScriptResponse) throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        try{
            String UUID = webScriptRequest.getExtensionPath();

            T data = getData(UUID);

            webScriptResponse.setContentEncoding(StandardCharsets.UTF_8.name());
            webScriptResponse.setContentType(MimetypeMap.MIMETYPE_JSON);
            webScriptResponse.getWriter().write(gson.toJson(data));
            webScriptResponse.setStatus(HttpStatus.SC_OK);
        }catch (IllegalArgumentException | JSONException e){
            throw new WebScriptException(HttpStatus.SC_BAD_REQUEST,e.getMessage());
        }catch(AlfrescoRuntimeException e){
            throw new WebScriptException(HttpStatus.SC_INTERNAL_SERVER_ERROR,"Internal server Error");
        }
    }

    public abstract T getData(String UUID);
}
