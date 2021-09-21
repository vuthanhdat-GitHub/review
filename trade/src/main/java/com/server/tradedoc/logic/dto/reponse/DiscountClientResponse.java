package com.server.tradedoc.logic.dto.reponse;

import com.server.tradedoc.logic.dto.DiscountDTO;

public class DiscountClientResponse {
    private Integer timeRemaining;
    private DiscountDTO discount;

    public Integer getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Integer timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }
}
