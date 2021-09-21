package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * ImageAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/public/api-client")
public class ImageAdminAPI {

    @Autowired
    private ImageService imageService;

    /**
     * createImage
     *
     * @param image
     * @param role
     * @param model
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/create-image", method = RequestMethod.POST)
    public ResponseEntity<?> createImage(@RequestParam("upload") MultipartFile image,
                                         @RequestParam("ckCsrfToken") String role,
                                         @RequestParam Map<String, String> model) throws URISyntaxException {
        return ResponseEntity.ok(imageService.createImage(image, model.get("role")));
    }

    /**
     * deleteImageFormDir : delete image file in folder server
     *
     * @param fileName : file name for delete form dir
     */
    @RequestMapping(value = "/delete-file-form-dir" , method = RequestMethod.DELETE)
    public void deleteImageFormDir(@RequestParam("fileName") String fileName,
                                   @RequestParam Map<String, String> model) {
        imageService.deleteFileFormDir(fileName , model.get("role"));
    }
}
