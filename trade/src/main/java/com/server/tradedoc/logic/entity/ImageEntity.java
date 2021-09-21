package com.server.tradedoc.logic.entity;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "pathfile")
    private String pathFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private ProductsEntity products;

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

    public ProductsEntity getProducts() {
        return products;
    }

    public void setProducts(ProductsEntity products) {
        this.products = products;
    }
}
