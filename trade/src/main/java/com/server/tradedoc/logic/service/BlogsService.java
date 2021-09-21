package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.BlogsDTO;
import com.server.tradedoc.logic.dto.reponse.CountResponse;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;
import com.server.tradedoc.logic.dto.reponse.FindAllBlogsResponse;
import com.server.tradedoc.logic.dto.reponse.UpdateResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public interface BlogsService {
    CreatedResponse create(String data , MultipartFile avatar , List<MultipartFile> blogFiles);
    UpdateResponse update(String data , MultipartFile avatar, List<MultipartFile> blogFiles);
    List<Long> delete(List<Long> ids);
    FindAllBlogsResponse findAll(Pageable pageable) throws URISyntaxException;
    CountResponse count();
    BlogsDTO findOne(Long id) throws URISyntaxException;
    File getFileUsingIdFileBlog(Long idFileBlog);
}
