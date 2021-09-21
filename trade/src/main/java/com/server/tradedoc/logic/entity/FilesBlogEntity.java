package com.server.tradedoc.logic.entity;

import javax.persistence.*;

@Entity
@Table(name = "files_blogs")
public class FilesBlogEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "pathfile")
    private String pathFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogId")
    private BlogsEntity blogs;

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

    public BlogsEntity getBlogs() {
        return blogs;
    }

    public void setBlogs(BlogsEntity blogs) {
        this.blogs = blogs;
    }
}
