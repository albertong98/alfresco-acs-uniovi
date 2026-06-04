package org.uniovi.service;

import org.alfresco.service.cmr.repository.NodeRef;
import org.springframework.extensions.webscripts.servlet.FormData;
import org.uniovi.dto.File;

import java.util.List;
import java.util.Map;

public interface FileService {
    public void insertFilesIntoNode(NodeRef nodeRef, List<File> fileData, Map<String, FormData.FormField> files);
}
