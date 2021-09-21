package com.server.tradedoc.logic.dto;

import java.util.List;

public class BlogsDTO extends AbstractDTO {
    private String title;
    private String subTitle;
    private String avatar;
    private String description;
    private List<FilesBlogDTO> filesBlog;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FilesBlogDTO> getFilesBlog() {
        return filesBlog;
    }

    public void setFilesBlog(List<FilesBlogDTO> filesBlog) {
        this.filesBlog = filesBlog;
    }
}
