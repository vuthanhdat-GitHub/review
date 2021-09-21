package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;

/**
 * BannerAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/admin")
public class BannerAdminAPI {

    @Autowired
    private BannerService bannerService;

    /**
     * create
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/create-banner", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestParam("fileImageBanner") MultipartFile multipartFile) {
        return ResponseEntity.ok(bannerService.create(multipartFile));
    }

    /**
     * updateStatusBanner
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/update-banner-status", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatusBanner(@RequestParam("id") Long id) {
        return ResponseEntity.ok(bannerService.updateStatusBanner(id));
    }

    /**
     * delete
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete-banner", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        return ResponseEntity.ok(bannerService.delete(id));
    }

    /**
     * findAllBanner
     *
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/find-all" , method = RequestMethod.GET)
    public ResponseEntity<?> findAllBanner() throws URISyntaxException {
        return ResponseEntity.ok(bannerService.findAllBanner());
    }

}
