package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.dto.AdsDTO;
import com.server.tradedoc.logic.service.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/ads")
public class AdsAdminAPI {

    @Autowired
    private AdsService adsService;

    @RequestMapping(value = "/create-ads", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody AdsDTO adsDTO) {
        return ResponseEntity.ok(adsService.create(adsDTO));
    }

    @RequestMapping(value = "/update-ads-by-status", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestParam("id") Long id) {
        return ResponseEntity.ok(adsService.updateByStatus(id));
    }

    @RequestMapping(value = "/delete-ads", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
        return ResponseEntity.ok(adsService.delete(id));
    }

    @RequestMapping(value = "/get-all-ads" , method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(adsService.findAllAds());
    }

}
