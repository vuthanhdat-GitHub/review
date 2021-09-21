package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.HistoryPaymentEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import com.server.tradedoc.logic.repository.custom.HistoryPaymentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * HistoryPaymentRepository
 *
 * @author DatDV
 */
public interface HistoryPaymentRepository extends JpaRepository<HistoryPaymentEntity , Long> , HistoryPaymentRepositoryCustom {
    void deleteAllByProduct(ProductsEntity productsEntity);
}
