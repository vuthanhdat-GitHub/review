package com.server.tradedoc.logic.api.client;

import com.server.tradedoc.logic.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

/**
 * ProductsClientAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/public/api-client")
public class ProductsClientAPI {

    @Autowired
    private ProductsService productsService;

    /**
     * getAllProducts
     *
     * @param categoryIds
     * @return List ProductsDTO
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/get-all-products", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProducts(@RequestParam("categoryIds") List<Long> categoryIds,
                                            @RequestParam("collection") String collection) throws URISyntaxException {
        return ResponseEntity.ok(productsService.getAllProducts(categoryIds, collection));
    }

    /**
     * findOne : function findOne
     *
     * @param id : id for findOne
     * @return Object ProductsDTO
     */
    @RequestMapping(value = "/find-one", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id) {
        return ResponseEntity.ok(productsService.getById(id, "client"));
    }
}
