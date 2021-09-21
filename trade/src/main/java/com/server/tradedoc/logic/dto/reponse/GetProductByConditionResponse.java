package com.server.tradedoc.logic.dto.reponse;

import java.util.List;

public class GetProductByConditionResponse {
    private Long countItem;
    private List<ProductsSearchDTO> products;

    public Long getCountItem() {
        return countItem;
    }

    public void setCountItem(Long countItem) {
        this.countItem = countItem;
    }

    public List<ProductsSearchDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsSearchDTO> products) {
        this.products = products;
    }
}
