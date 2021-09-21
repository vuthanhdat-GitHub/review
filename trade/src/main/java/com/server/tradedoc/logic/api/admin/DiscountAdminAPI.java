package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.dto.DiscountDTO;
import com.server.tradedoc.logic.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * DiscountAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/admin")
public class DiscountAdminAPI {

    @Autowired
    private DiscountService discountService;

    /**
     * create
     *
     * @param discountDTO
     * @return
     */
    @RequestMapping(value = "/create-discount", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody DiscountDTO discountDTO) throws ParseException {
        return ResponseEntity.ok(discountService.create(discountDTO));
    }

    /**
     * findOne
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find-one-discount", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id) {
        return ResponseEntity.ok(discountService.findOne(id));
    }

    /**
     * notificationDiscount
     *
     * @return
     */
    @RequestMapping(value = "/notification-discount", method = RequestMethod.GET)
    public ResponseEntity<?> notificationDiscount() {
        return ResponseEntity.ok(discountService.notificationDiscount());
    }

    /**
     * updateDiscountById
     *
     * @return
     */
    @RequestMapping(value = "/update-status-discount", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDiscountById(@RequestParam("id") Long idDiscountForUpdate) {
        return ResponseEntity.ok(discountService.updateStatusDiscount(idDiscountForUpdate));
    }

    /**
     * deleteDiscountById
     *
     * @param idDiscountForDelete
     * @return
     */
    @RequestMapping(value = "/delete-discount", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDiscountById(@RequestParam("id") Long idDiscountForDelete) {
        return ResponseEntity.ok(discountService.delete(idDiscountForDelete));
    }

    /**
     * getAllDiscount
     *
     * @return
     */
    @RequestMapping(value = "/get-all-discount" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllDiscount() {
        return ResponseEntity.ok(discountService.getAllDiscount());
    }
}
