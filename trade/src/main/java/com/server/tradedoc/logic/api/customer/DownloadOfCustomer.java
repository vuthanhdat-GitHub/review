package com.server.tradedoc.logic.api.customer;

import com.server.tradedoc.logic.service.BlogsService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * DownloadOfCustomer
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/customer")
public class DownloadOfCustomer {

    @Autowired
    private BlogsService blogsService;

    /**
     * downloadFileBlogs
     *
     * @param idFileForDownload : id file for download
     * @return InputStreamResource
     * @throws IOException
     */
    @RequestMapping(value = "/download-file-blog", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFileBlogs(@RequestParam("idFileBlog") Long idFileForDownload) throws IOException {
        HttpHeaders responseHeader = new HttpHeaders();
        try {
            File file = blogsService.getFileUsingIdFileBlog(idFileForDownload);
            byte[] data = FileUtils.readFileToByteArray(file);
            responseHeader.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            responseHeader.set("Content-disposition", "attachment; filename=" + file.getName());
            responseHeader.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            return new ResponseEntity<InputStreamResource>(inputStreamResource, responseHeader, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity<InputStreamResource>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
