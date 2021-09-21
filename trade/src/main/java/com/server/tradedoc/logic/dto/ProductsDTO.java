package com.server.tradedoc.logic.dto;

import com.server.tradedoc.logic.dto.custom.CategoryDTOCustom;

import java.util.List;

public class ProductsDTO extends AbstractDTO {

    //field of DTO
    private String productName;
    private Integer price;
    private String description;
    private String inputParam;
    private String title;
    private String avatar;
    private List<String> types;
    private String collection;
    private List<Long> categoryIds;
    private List<CategoryDTOCustom> categorys;
    private List<FilesProductDTO> filesProducts;

    //method of DTO
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<CategoryDTOCustom> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryDTOCustom> categorys) {
        this.categorys = categorys;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public List<FilesProductDTO> getFilesProducts() {
        return filesProducts;
    }

    public void setFilesProducts(List<FilesProductDTO> filesProducts) {
        this.filesProducts = filesProducts;
    }
}
