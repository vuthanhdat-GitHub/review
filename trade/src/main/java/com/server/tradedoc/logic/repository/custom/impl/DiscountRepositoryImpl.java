package com.server.tradedoc.logic.repository.custom.impl;

import com.server.tradedoc.logic.entity.DiscountEntity;
import com.server.tradedoc.logic.repository.custom.DiscountRepositoryCustom;
import com.server.tradedoc.utils.DateTimeUtils;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DiscountRepositoryImpl
 *
 * @author DatDV
 */
@Repository
public class DiscountRepositoryImpl extends RepositoryCustomUtils<DiscountEntity> implements DiscountRepositoryCustom {

    /**
     * findByDateStartAndDateStart
     *
     * @param expireDateStart
     * @param expireDateEnd
     * @return
     */
    @Override
    public List<DiscountEntity> findByDateStartAndDateStart(Date expireDateStart, Date expireDateEnd) throws ParseException {
        Map<String, Object> parameter = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM discount dis ");
        sql.append("WHERE 1=1 ");
        sql.append("AND  (( dis.expiredateend >= :expireDateStart AND dis.expiredatestart <= :expireDateStart) ");
        sql.append("OR (dis.expiredateend >= :expireDateEnd AND dis.expiredatestart <= :expireDateEnd) ");
        sql.append("OR (dis.expiredateend >= :expireDateStart AND dis.expiredatestart <= :expireDateEnd)) ");
        parameter.put("expireDateEnd" , DateTimeUtils.convertStringRequestToTimesTamp(DateTimeUtils.formatDateTimeQuery(DateTimeUtils.convertDateToStringUseFormatDate(expireDateEnd , null).trim()), "dd/MM/yyyy"));
        parameter.put("expireDateStart" , DateTimeUtils.convertStringRequestToTimesTamp(DateTimeUtils.formatDateTimeQuery(DateTimeUtils.convertDateToStringUseFormatDate(expireDateStart , null).trim()), "dd/MM/yyyy"));
        return this.getResultList(sql.toString() , parameter , DiscountEntity.class);
    }
}
