package com.server.tradedoc.logic.entity;

import com.server.tradedoc.logic.dto.reponse.ProductsSearchDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@SqlResultSetMapping(name = "findAllProductsByCondition" , classes = {
        @ConstructorResult(targetClass = ProductsSearchDTO.class ,
                columns = {
                        @ColumnResult(name = "product_id", type = Long.class),
                        @ColumnResult(name = "product_name", type = String.class),
                        @ColumnResult(name = "category_name", type = String.class),
                        @ColumnResult(name = "mt4_file", type = String.class),
                        @ColumnResult(name = "mt5_file", type = String.class),
                        @ColumnResult(name = "price", type = Integer.class),
                        @ColumnResult(name = "path_file", type = String.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "type", type = String.class),
                        @ColumnResult(name = "avatar", type = String.class),
                        @ColumnResult(name = "title", type = String.class),
                        @ColumnResult(name = "collection", type = String.class),
                        @ColumnResult(name = "created_by", type = String.class),
                        @ColumnResult(name = "modified_by", type = String.class),
                        @ColumnResult(name = "created_date", type = Date.class),
                        @ColumnResult(name = "modified_date", type = Date.class)
                })
})
public class ProductsEntity extends BaseEntity {

    @Column(name = "productname")
    private String productName;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description" , columnDefinition = "TEXT")
    private String description;

    @Column(name = "inputparam" , columnDefinition = "TEXT")
    private String inputParam;

    @Column(name = "pathfile")
    private String pathFile;

    @Column(name = "title")
    private String title;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "type")
    private String type;

    @Column(name = "collection")
    private String collection;

    @OneToMany(mappedBy = "products" , fetch = FetchType.LAZY)
    private List<ImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "products" , fetch = FetchType.LAZY)
    private List<FilesProductEntity> filesProducts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category" , joinColumns = @JoinColumn(name = "productid") ,
            inverseJoinColumns = @JoinColumn(name = "categoryid"))
    private List<CategoryEntity> categorys;

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

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<CategoryEntity> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<CategoryEntity> categorys) {
        this.categorys = categorys;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FilesProductEntity> getFilesProducts() {
        return filesProducts;
    }

    public void setFilesProducts(List<FilesProductEntity> filesProducts) {
        this.filesProducts = filesProducts;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
