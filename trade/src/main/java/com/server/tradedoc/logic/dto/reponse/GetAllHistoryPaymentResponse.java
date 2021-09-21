package com.server.tradedoc.logic.dto.reponse;

import java.util.List;

public class GetAllHistoryPaymentResponse {
    private Long countItem;
    private List<HistoryPaymentSearchDTO> historyPayments;

    public Long getCountItem() {
        return countItem;
    }

    public void setCountItem(Long countItem) {
        this.countItem = countItem;
    }

    public List<HistoryPaymentSearchDTO> getHistoryPayments() {
        return historyPayments;
    }

    public void setHistoryPayments(List<HistoryPaymentSearchDTO> historyPayments) {
        this.historyPayments = historyPayments;
    }
}
