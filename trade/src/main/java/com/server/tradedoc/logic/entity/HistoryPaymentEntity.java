package com.server.tradedoc.logic.entity;

import com.server.tradedoc.logic.dto.reponse.HistoryPaymentSearchDTO;

import javax.persistence.*;
import java.util.Date;

/**
 * HistoryPaymentEntity
 *
 * @author DatDV
 */
@Entity
@Table(name = "history_payment")
@SqlResultSetMapping(name = "findAllHistoryPayment" , classes = {
        @ConstructorResult(targetClass = HistoryPaymentSearchDTO.class ,
                columns = {
                        /*@ColumnResult(name = "created_by", type = String.class),
                        @ColumnResult(name = "modified_by", type = String.class),*/
                        @ColumnResult(name = "payment_type", type = String.class),
                        @ColumnResult(name = "payment_total", type = Long.class),
                        @ColumnResult(name = "customer_id", type = Long.class),
                        @ColumnResult(name = "history_status", type = String.class),
                        @ColumnResult(name = "customer_name", type = String.class),
                        @ColumnResult(name = "customer_email", type = String.class),
                        @ColumnResult(name = "customer_phone", type = String.class),
                        @ColumnResult(name = "product_price", type = Integer.class),
                        @ColumnResult(name = "product_name", type = String.class),
                        @ColumnResult(name = "product_avatar", type = String.class),
                        @ColumnResult(name = "created_date", type = Date.class),
                        @ColumnResult(name = "modified_date", type = Date.class),
                })
})
public class HistoryPaymentEntity extends BaseEntity {

    @Column(name = "paymenttype")
    private String paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    private UserEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productid")
    private ProductsEntity product;

    @Column(name = "total")
    private Long total;

    @Column(name = "status")
    private Integer status;

    @Column(name = "deleted")
    private Integer deleted;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
