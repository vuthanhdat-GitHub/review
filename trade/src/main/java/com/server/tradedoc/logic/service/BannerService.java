package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.BannerDTO;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;
import com.server.tradedoc.logic.dto.reponse.DeleteResponse;
import com.server.tradedoc.logic.dto.reponse.UpdateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;

/**
 * BannerService
 *
 * @author DatDV
 */
public interface BannerService {
    CreatedResponse create(MultipartFile image);
    UpdateResponse updateStatusBanner(Long id);
    DeleteResponse delete(Long id);
    List<BannerDTO> findAllBannerByStatus() throws URISyntaxException;
    List<BannerDTO> findAllBanner() throws URISyntaxException;
}
