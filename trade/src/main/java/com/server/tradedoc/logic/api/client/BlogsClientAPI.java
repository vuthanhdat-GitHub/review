package com.server.tradedoc.logic.api.client;

import com.server.tradedoc.logic.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * BlogsClientAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/public/api-client")
public class BlogsClientAPI {

    @Autowired
    private BlogsService blogsService;

    /**
     * findAll
     *
     * @param model
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/blogs-find-all", method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam Map<String, String> model) throws URISyntaxException {
        Pageable pageable = PageRequest.of(Integer.parseInt(model.get("page")) - 1, Integer.parseInt(model.get("maxPageItem")));
        return ResponseEntity.ok(blogsService.findAll(pageable));
    }

    /**
     * count
     *
     * @return
     */
    @RequestMapping(value = "/blogs-count", method = RequestMethod.GET)
    public ResponseEntity<?> count() {
        return ResponseEntity.ok(blogsService.count());
    }

    /**
     * findOne
     *
     * @param id
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/blogs-find-one", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id) throws URISyntaxException {
        return ResponseEntity.ok(blogsService.findOne(id));
    }
}
