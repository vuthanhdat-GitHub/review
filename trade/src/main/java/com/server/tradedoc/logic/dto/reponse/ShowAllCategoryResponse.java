package com.server.tradedoc.logic.dto.reponse;

import com.server.tradedoc.logic.dto.CategoryDTO;

import java.util.List;

public class ShowAllCategoryResponse {
    private Long countItem;
    private List<CategoryDTO> categorys;

    public Long getCountItem() {
        return countItem;
    }

    public void setCountItem(Long countItem) {
        this.countItem = countItem;
    }

    public List<CategoryDTO> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryDTO> categorys) {
        this.categorys = categorys;
    }
}
