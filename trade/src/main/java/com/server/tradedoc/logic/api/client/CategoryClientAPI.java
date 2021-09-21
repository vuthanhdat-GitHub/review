package com.server.tradedoc.logic.api.client;

import com.server.tradedoc.logic.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoryClientAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/public/api-client")
public class CategoryClientAPI {

    @Autowired
    private CategoryService categoryService;

    /**
     * getAllCategory
     *
     * @return
     */
    @RequestMapping(value = "/get-all-category" , method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(categoryService.showAll());
    }
}
