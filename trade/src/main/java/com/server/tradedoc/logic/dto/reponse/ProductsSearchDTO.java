package com.server.tradedoc.logic.dto.reponse;

import com.server.tradedoc.logic.dto.ImageDTO;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class ProductsSearchDTO {
    private Long productId;
    private String productName;
    private String categoryName;
    private String mt4File;
    private String mt5File;
    private Integer price;
    private String pathFile;
    private String description;
    private String type;
    private String avatar;
    private String title;
    private String collection;
    private List<ImageDTO> imageDTOS;
    private List<String> types;
    private String createdBy;
    private String modifiedBy;
    private Date createdDate;
    private Date modifiedDate;

    public ProductsSearchDTO() {
    }

    public ProductsSearchDTO(Long productId, String productName, String categoryName, String mt4File, String mt5File, Integer price, String pathFile, String description, String type, String avatar, String title, String collection, String createdBy, String modifiedBy, Date createdDate, Date modifiedDate) {
        this.productId = productId;
        this.productName = productName;
        this.categoryName = categoryName;
        this.mt4File = mt4File;
        this.mt5File = mt5File;
        this.price = price;
        this.pathFile = pathFile;
        this.description = description;
        this.type = type;
        this.avatar = avatar;
        this.title = title;
        this.collection = collection;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageDTO> getImageDTOS() {
        return imageDTOS;
    }

    public void setImageDTOS(List<ImageDTO> imageDTOS) {
        this.imageDTOS = imageDTOS;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getMt4File() {
        return mt4File;
    }

    public void setMt4File(String mt4File) {
        this.mt4File = mt4File;
    }

    public String getMt5File() {
        return mt5File;
    }

    public void setMt5File(String mt5File) {
        this.mt5File = mt5File;
    }
}
