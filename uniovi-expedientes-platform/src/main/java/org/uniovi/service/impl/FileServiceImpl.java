package org.uniovi.service.impl;

import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.File;
import org.uniovi.model.UnioviContentModel;
import org.uniovi.service.FileService;
import org.uniovi.service.NodeService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class FileServiceImpl implements FileService {
    private NodeService nodeService;
    private ContentService contentService;
    @Override
    public void insertFilesIntoNode(NodeRef nodeRef, List<File> fileData, Map<String, FormData.FormField> files) {
        for(File file: fileData){
            FormData.FormField field = files.get(file.fileId);

            NodeRef fileRef = nodeService.createNodeRef(UnioviContentModel.TYPE_FILE,nodeRef);

            nodeService.checkAndSetStringProperty(ContentModel.PROP_DESCRIPTION,file.description,fileRef,false);
            nodeService.checkAndSetStringProperty(ContentModel.PROP_TITLE,file.title,fileRef,false);

            try(InputStream in = field.getInputStream()){
                ContentWriter writer = contentService.getWriter(fileRef,ContentModel.PROP_CONTENT,true);
                writer.putContent(in);
            }catch (IOException e) {
                throw new AlfrescoRuntimeException("ERROR trying to process file with ID "+file.fileId,e);
            }
        }
    }

    //SETTERS

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }
}
