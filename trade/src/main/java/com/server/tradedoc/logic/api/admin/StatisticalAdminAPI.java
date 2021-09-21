package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.service.HistoryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatisticalAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/admin")
public class StatisticalAdminAPI {

    @Autowired
    private HistoryPaymentService historyPaymentService;

    /**
     * statisticalAdmin
     *
     * @return
     */
    @RequestMapping(value = "/statistical" , method = RequestMethod.GET)
    public ResponseEntity<?> statisticalAdmin() {
        return ResponseEntity.ok(historyPaymentService.statisticalAdmin());
    }
}
