package org.uniovi.dto;

import org.springframework.extensions.webscripts.servlet.FormData;

public class File {
    public String fileId;
    public String title;
    public String description;
    public String name;
    public String mimetype;

    public FormData.FormField field;
    public File(String fileId, String title, String description,String mimetype, String name) {
        this.fileId = fileId;
        this.title = title;
        this.description = description;
        this.mimetype = mimetype;
        this.name = name;
    }
}
