package com.server.tradedoc.logic.dto;

public class FilesProductDTO extends AbstractDTO {
    private String name;
    private String productType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
