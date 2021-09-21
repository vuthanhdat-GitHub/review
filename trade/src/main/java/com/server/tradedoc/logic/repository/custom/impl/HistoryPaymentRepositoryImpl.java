package com.server.tradedoc.logic.repository.custom.impl;

import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.dto.reponse.HistoryPaymentSearchDTO;
import com.server.tradedoc.logic.entity.HistoryPaymentEntity;
import com.server.tradedoc.logic.repository.custom.HistoryPaymentRepositoryCustom;
import com.server.tradedoc.utils.BuildMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HistoryPaymentRepositoryImpl
 *
 * @author DatDV
 */
@Repository
public class HistoryPaymentRepositoryImpl extends RepositoryCustomUtils<HistoryPaymentEntity> implements HistoryPaymentRepositoryCustom {

    @Autowired
    private BuildMapUtils buildMapUtils;

    /**
     * findAllHistoryPayment
     *
     * @param builder
     * @param pageable
     * @return
     */
    @Override
    public List<HistoryPaymentSearchDTO> findAllHistoryPayment(SearchHistoryPaymentBuilder builder, Pageable pageable) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT                                            ");
        sql.append("    his.paymenttype AS payment_type,              ");
        sql.append("    his.total AS payment_total,                   ");
        sql.append("    his.customerid AS customer_id,                ");
        sql.append("    CASE ");
        sql.append("        WHEN his.status = 1 THEN 'Thành Công'   ");
        sql.append("        WHEN his.status = 0 THEN 'Thất Bại'     ");
        sql.append("    END AS history_status,                       ");
        sql.append("    cus.fullname AS customer_name,              ");
        sql.append("    cus.email AS customer_email,                  ");
        sql.append("    cus.numberphone AS customer_phone,            ");
        sql.append("    pro.price AS product_price,                   ");
        sql.append("    pro.productname AS product_name,             ");
        sql.append("    pro.avatar AS product_avatar,                 ");
        sql.append("    his.createddate AS created_date,              ");
        sql.append("    his.modifieddate AS modified_date            ");
        sql.append("FROM ");
        sql.append("users cus                                          ");
        sql.append("INNER JOIN history_payment his ON cus.id = his.customerid ");
        sql.append("INNER JOIN products pro ON pro.id = his.productid ");
        sql.append("WHERE 1=1 ");
        if (!builder.getCustomerName().equals("")) {
            sql.append("AND cus.fullname LIKE :customername ");
        }
        if (!builder.getEmailCustomer().equals("")) {
            sql.append("AND cus.email LIKE :emailcustomer ");
        }
        if (!builder.getPhoneNumber().equals("")) {
            sql.append("AND cus.numberphone LIKE :phonenumber ");
        }
        if (builder.getPriceForm() != null) {
            sql.append("AND pro.price >= :priceform ");
        }
        if (builder.getPriceTo() != null) {
            sql.append("AND pro.price <= :priceto ");
        }
        if (!builder.getProductName().equals("")) {
            sql.append("AND pro.productname LIKE :productname ");
        }
        if (!builder.getPaymentDateFrom().equals("")) {
            sql.append("AND his.createddate >= :paymentdatefrom ");
        }
        if (!builder.getPaymentDateTo().equals("")) {
            sql.append("AND his.createddate <= :paymentdateto ");
        }
        sql.append("ORDER BY his.createddate DESC ");
        return this.getResultList(sql.toString(), parameter, "findAllHistoryPayment", pageable);
    }

    /**
     * countByCondition
     *
     * @param builder
     * @return
     */
    @Override
    public Long countByCondition(SearchHistoryPaymentBuilder builder) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT            COUNT(*)                   ");
        sql.append("FROM                                             ");
        sql.append("users cus                                        ");
        sql.append("INNER JOIN history_payment his ON cus.id = his.customerid ");
        sql.append("INNER JOIN products pro ON pro.id = his.productid ");
        sql.append("WHERE 1=1 ");
        if (!builder.getCustomerName().equals("")) {
            sql.append("AND cus.fullname LIKE :customername ");
        }
        if (!builder.getEmailCustomer().equals("")) {
            sql.append("AND cus.email LIKE :emailcustomer ");
        }
        if (!builder.getPhoneNumber().equals("")) {
            sql.append("AND cus.numberphone LIKE :phonenumber ");
        }
        if (builder.getPriceForm() != null) {
            sql.append("AND pro.price >= :priceform ");
        }
        if (builder.getPriceTo() != null) {
            sql.append("AND pro.price <= :priceto ");
        }
        if (!builder.getProductName().equals("")) {
            sql.append("AND pro.productname LIKE :productname ");
        }
        if (!builder.getPaymentDateFrom().equals("")) {
            sql.append("AND his.createddate >= :paymentdatefrom ");
        }
        if (!builder.getPaymentDateTo().equals("")) {
            sql.append("AND his.createddate <= :paymentdateto ");
        }
        Long count = Long.parseLong(this.getSingleResult(sql.toString() , parameter).toString());
        return count;
    }

    /**
     * sumTotalByPaymentType
     *
     * @param paymentType
     * @return
     */
    @Override
    public Long sumTotalByPaymentType(String paymentType) {
        StringBuilder sql = new StringBuilder();
        Map<String , Object> parameter = new HashMap<>();
        sql.append("SELECT SUM(his.total) FROM history_payment his WHERE 1=1 AND his.paymenttype = :paymenttype AND his.status = :status");
        parameter.put("paymenttype" , paymentType);
        parameter.put("status" , 1);
        return this.getSingleResult(sql.toString() , parameter) == null ? 0L : Long.parseLong(this.getSingleResult(sql.toString() , parameter).toString());
    }
}
