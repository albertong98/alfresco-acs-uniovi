package org.uniovi.webscript.command;

import org.alfresco.error.AlfrescoRuntimeException;
import org.apache.http.HttpStatus;
import org.apache.tika.mime.MimeTypes;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;


import java.io.IOException;

public abstract class CreateWebScript<T> extends AbstractWebScript {
    @Override
    public void execute(WebScriptRequest webScriptRequest, WebScriptResponse webScriptResponse) throws IOException {
        try{
            String json = webScriptRequest.getContent().getContent();
            T data = getData(json);

            String ID = executeCreate(data);

            webScriptResponse.setStatus(HttpStatus.SC_CREATED);
            webScriptResponse.setContentType(MimeTypes.PLAIN_TEXT);
            webScriptResponse.getWriter().write(ID);
        }catch(AlfrescoRuntimeException e){
            webScriptResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            webScriptResponse.getWriter().write("HTTP 500 - Internal Server Error. Contacte con su equipo de TI.");
        }
    }

    public abstract String executeCreate(T data);

    public abstract T getData(String json);
}
