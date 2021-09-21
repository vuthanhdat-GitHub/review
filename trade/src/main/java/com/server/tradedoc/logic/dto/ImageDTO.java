package com.server.tradedoc.logic.dto;

public class ImageDTO extends AbstractDTO {

    private String name;
    private String pathFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
}
