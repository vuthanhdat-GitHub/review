package com.server.tradedoc.logic.entity;

import javax.persistence.*;

@Entity
@Table(name = "files_product")
public class FilesProductEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "pathfile")
    private String pathFile;

    @Column(name = "producttype")
    private String productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private ProductsEntity products;

    public ProductsEntity getProducts() {
        return products;
    }

    public void setProducts(ProductsEntity products) {
        this.products = products;
    }

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
