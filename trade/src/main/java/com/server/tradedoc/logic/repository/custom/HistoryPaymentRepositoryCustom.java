package com.server.tradedoc.logic.repository.custom;

import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.dto.reponse.HistoryPaymentSearchDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface HistoryPaymentRepositoryCustom {
    List<HistoryPaymentSearchDTO> findAllHistoryPayment(SearchHistoryPaymentBuilder builder , Pageable pageable);
    Long countByCondition(SearchHistoryPaymentBuilder builder);
    Long sumTotalByPaymentType(String paymentType);
}
