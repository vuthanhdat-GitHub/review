package com.server.tradedoc.logic.dto.paymentrequest;

import java.util.List;

public class AbstractPaymentRequest {
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
