package com.server.tradedoc.logic.dto.reponse;

import com.server.tradedoc.logic.dto.DiscountDTO;

public class NotificationDiscount {
    private Integer timeRemaining;
    private String message;
    private DiscountDTO discount;

    public Integer getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Integer timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }
}
