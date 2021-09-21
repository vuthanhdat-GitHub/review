package com.server.tradedoc.logic.dto.reponse;

import com.server.tradedoc.logic.dto.BlogsDTO;

import java.util.List;

public class FindAllBlogsResponse {
    private Long countItem;
    private List<BlogsDTO> blogs;

    public Long getCountItem() {
        return countItem;
    }

    public void setCountItem(Long countItem) {
        this.countItem = countItem;
    }

    public List<BlogsDTO> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogsDTO> blogs) {
        this.blogs = blogs;
    }
}
