package com.server.tradedoc.logic.api.client;

import com.server.tradedoc.logic.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * BannerClientAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/public/api-client")
public class BannerClientAPI {

    @Autowired
    private BannerService bannerService;

    /**
     * findAll
     *
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/find-all-banner" , method = RequestMethod.GET)
    public ResponseEntity<?> findAll() throws URISyntaxException {
        return ResponseEntity.ok(bannerService.findAllBannerByStatus());
    }
}
