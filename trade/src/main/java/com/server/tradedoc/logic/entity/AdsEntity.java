package com.server.tradedoc.logic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ads")
public class AdsEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "linkads")
    private String linkAds;

    @Column(name = "linkimageads")
    private String linkImageAds;

    @Column(name = "status")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkAds() {
        return linkAds;
    }

    public void setLinkAds(String linkAds) {
        this.linkAds = linkAds;
    }

    public String getLinkImageAds() {
        return linkImageAds;
    }

    public void setLinkImageAds(String linkImageAds) {
        this.linkImageAds = linkImageAds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
