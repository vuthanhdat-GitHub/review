package com.server.tradedoc.logic.api.client;

import com.server.tradedoc.logic.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api-client")
public class AdsClientAPI {

    @Autowired
    private AdsService adsService;

    @RequestMapping(value = "/get-all-ads-active" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllAdsClient() {
        return ResponseEntity.ok(adsService.findAllAdsByStatus());
    }
}
