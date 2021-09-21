package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * BlogsAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/admin")
public class BlogsAdminAPI {

    @Autowired
    private BlogsService blogsService;

    /**
     * create
     *
     * @param avatar
     * @param data
     * @param blogFiles
     * @return
     */
    @RequestMapping(value = "/create-blog", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestPart(name = "avatar" , required = false) MultipartFile avatar,
                                    @RequestParam("data") String data,
                                    @RequestPart(name = "fileBlogs" , required = false) List<MultipartFile> blogFiles) {
        return ResponseEntity.ok(blogsService.create(data, avatar , blogFiles));
    }

    /**
     * update
     *
     * @param avatar
     * @param data
     * @param blogFiles
     * @return
     */
    @RequestMapping(value = "/update-blog", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestPart(name = "avatar" , required = false) MultipartFile avatar,
                                    @RequestParam("data") String data,
                                    @RequestPart(name = "fileBlogs" , required = false) List<MultipartFile> blogFiles) {
        return ResponseEntity.ok(blogsService.update(data, avatar, blogFiles));
    }

    /**
     * delete
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete-blogs", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(blogsService.delete(ids));
    }

    /**
     * findOne
     *
     * @param id
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/find-one-blog", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id) throws URISyntaxException {
        return ResponseEntity.ok(blogsService.findOne(id));
    }

    /**
     *findAll
     *
     * @param model
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/find-all-blog" ,method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam Map<String , String> model) throws URISyntaxException {
        Pageable pageable = PageRequest.of(Integer.parseInt(model.get("page")) - 1 , Integer.parseInt(model.get("maxPageItem")));
        return ResponseEntity.ok(blogsService.findAll(pageable));
    }

    /**
     * count
     *
     * @return
     */
    @RequestMapping(value = "/blogs-count" , method = RequestMethod.GET)
    public ResponseEntity<?> count() {
        return ResponseEntity.ok(blogsService.count());
    }
}
