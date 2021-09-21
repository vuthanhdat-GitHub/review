package com.server.tradedoc.logic.dto.paymentrequest;

public class PayPalRequest extends AbstractPaymentRequest {
    private String message;
    private Long customerId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
