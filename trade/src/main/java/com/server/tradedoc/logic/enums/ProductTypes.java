package com.server.tradedoc.logic.enums;

public enum ProductTypes {
    MT4("MT4"),
    MT5("MT5");

    private String value;

    ProductTypes(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
