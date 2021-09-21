package com.server.tradedoc.logic.api.client;

import com.server.tradedoc.logic.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * DiscountClientAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/public/api-client")
public class DiscountClientAPI {

    @Autowired
    private DiscountService discountService;

    /**
     * findDiscountForClient
     *
     * @return
     */
    @RequestMapping(value = "/get-discount-active" , method = RequestMethod.GET)
    public ResponseEntity<?> findDiscountActive() {
        return ResponseEntity.ok(discountService.findDiscountForClient());
    }
}
