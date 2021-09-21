package com.server.tradedoc.logic.dto.reponse;

public class StatisticalAdminResponse {
    private String totalPayPal;
    private String totalBitCoin;
    private String totalVisa;
    private String totalCustomer;

    public String getTotalPayPal() {
        return totalPayPal;
    }

    public void setTotalPayPal(String totalPayPal) {
        this.totalPayPal = totalPayPal;
    }

    public String getTotalBitCoin() {
        return totalBitCoin;
    }

    public void setTotalBitCoin(String totalBitCoin) {
        this.totalBitCoin = totalBitCoin;
    }

    public String getTotalVisa() {
        return totalVisa;
    }

    public void setTotalVisa(String totalVisa) {
        this.totalVisa = totalVisa;
    }

    public String getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(String totalCustomer) {
        this.totalCustomer = totalCustomer;
    }
}
