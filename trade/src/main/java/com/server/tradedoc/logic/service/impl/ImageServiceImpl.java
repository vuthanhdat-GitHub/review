package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.logic.dto.reponse.ImageResponse;
import com.server.tradedoc.logic.service.ImageService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.FilesUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private FilesUtils filesUtils;

    @Override
    public ImageResponse createImage(MultipartFile image, String role) throws URISyntaxException {
        if (image.isEmpty()) {
            throw new CustomException("image undefined", CommonUtils.putError("image", "ERR_0034"));
        }
        if (!role.equals("ROLE_MANAGER")) {
            throw new CustomException("access_denied", CommonUtils.putError("role", "ERR_0021"));
        }
        ImageResponse response = new ImageResponse();
        String nameFileServer = filesUtils.generateFileName(image.getOriginalFilename());
        String fileName = filesUtils.save(image, "/image/", nameFileServer);
        response.setUrl(filesUtils.genFilePath(fileName));
        response.setFileName(nameFileServer);
        response.setUploaded(1);
        return response;
    }

    @Override
    public void deleteFileFormDir(String fileName , String role) {
        if (!role.equals("ROLE_MANAGER")) {
            throw new CustomException("access_denied" , CommonUtils.putError("role", "ERR_0021"));
        }
        filesUtils.delete("/image/" + fileName);
    }
}
