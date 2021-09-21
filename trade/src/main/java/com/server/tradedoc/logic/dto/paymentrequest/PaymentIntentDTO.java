package com.server.tradedoc.logic.dto.paymentrequest;

import com.server.tradedoc.logic.enums.Currency;

public class PaymentIntentDTO {

    private String description;
    private Long productId;
    private Currency currency;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
