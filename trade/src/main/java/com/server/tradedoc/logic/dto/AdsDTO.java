package com.server.tradedoc.logic.dto;

public class AdsDTO extends AbstractDTO {
    private String name;
    private String linkAds;
    private String linkImageAds;
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
