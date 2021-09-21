package com.server.tradedoc.logic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "discount")
public class DiscountEntity extends BaseEntity {
    
    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "discountpercent")
    private Integer discountPercent;

    @Column(name = "status")
    private Integer status;

    @Column(name = "expiredatestart")
    private Date expireDateStart;

    @Column(name = "expiredateend")
    private Date expireDateEnd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExpireDateStart() {
        return expireDateStart;
    }

    public void setExpireDateStart(Date expireDateStart) {
        this.expireDateStart = expireDateStart;
    }

    public Date getExpireDateEnd() {
        return expireDateEnd;
    }

    public void setExpireDateEnd(Date expireDateEnd) {
        this.expireDateEnd = expireDateEnd;
    }
}
