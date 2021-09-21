package com.server.tradedoc.logic.repository.custom;

import com.server.tradedoc.logic.entity.DiscountEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * DiscountRepositoryCustom
 *
 * @author DatDV
 */
public interface DiscountRepositoryCustom {
    List<DiscountEntity> findByDateStartAndDateStart(Date expireDateStart , Date expireDateEnd) throws ParseException;
}
