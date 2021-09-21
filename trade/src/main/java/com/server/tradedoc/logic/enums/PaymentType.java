package com.server.tradedoc.logic.enums;

public enum PaymentType {

    PAY_PAL("paypal"),
    BIT_COIN("bitcoin"),
    VISA("visa");

    private String value;

    PaymentType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
