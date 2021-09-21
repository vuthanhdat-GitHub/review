package com.server.tradedoc.logic.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "blogs")
public class BlogsEntity extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subTitle;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "description" , columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "blogs" , fetch = FetchType.LAZY)
    private List<FilesBlogEntity> filesBlog;

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

    public List<FilesBlogEntity> getFilesBlog() {
        return filesBlog;
    }

    public void setFilesBlog(List<FilesBlogEntity> filesBlog) {
        this.filesBlog = filesBlog;
    }
}
