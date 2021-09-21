package com.server.tradedoc.logic.builder;

/**
 * SearchHistoryPaymentBuilder
 *
 * @author DatDV
 */
public class SearchHistoryPaymentBuilder {

    private String emailCustomer;
    private String productName;
    private String paymentDateFrom;
    private String paymentDateTo;
    private String customerName;
    private String phoneNumber;
    private Integer priceTo;
    private Integer priceForm;

    public String getEmailCustomer() {
        return emailCustomer;
    }
    public String getProductName() {
        return productName;
    }
    public String getPaymentDateFrom() {
        return paymentDateFrom;
    }
    public String getPaymentDateTo() {
        return paymentDateTo;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Integer getPriceTo() {
        return priceTo;
    }
    public Integer getPriceForm() {
        return priceForm;
    }

    public SearchHistoryPaymentBuilder(builder builder){
        this.emailCustomer = builder.emailCustomer;
        this.productName = builder.productName;
        this.paymentDateFrom = builder.paymentDateFrom;
        this.paymentDateTo = builder.paymentDateTo;
        this.customerName = builder.customerName;
        this.phoneNumber = builder.phoneNumber;
        this.priceTo = builder.priceTo;
        this.priceForm = builder.priceForm;
    }

    public static class builder{
        private String emailCustomer;
        private String productName;
        private String paymentDateFrom;
        private String paymentDateTo;
        private String customerName;
        private String phoneNumber;
        private Integer priceTo;
        private Integer priceForm;

        public builder setEmailCustomer(String emailCustomer) {
            this.emailCustomer = emailCustomer;
            return this;
        }
        public builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }
        public builder setPaymentDateFrom(String paymentDateFrom) {
            this.paymentDateFrom = paymentDateFrom;
            return this;
        }
        public builder setPaymentDateTo(String paymentDateTo) {
            this.paymentDateTo = paymentDateTo;
            return this;
        }
        public builder setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }
        public builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public builder setPriceTo(Integer priceTo) {
            this.priceTo = priceTo;
            return this;
        }
        public builder setPriceForm(Integer priceForm) {
            this.priceForm = priceForm;
            return this;
        }
        public SearchHistoryPaymentBuilder builder() { return new SearchHistoryPaymentBuilder(this); }
    }

}
