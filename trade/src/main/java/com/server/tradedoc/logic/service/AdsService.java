package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.AdsDTO;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;
import com.server.tradedoc.logic.dto.reponse.DeleteResponse;
import com.server.tradedoc.logic.dto.reponse.UpdateResponse;

import java.util.List;

public interface AdsService {
    CreatedResponse create(AdsDTO adsDTO);
    UpdateResponse updateByStatus(Long id);
    DeleteResponse delete(Long id);
    List<AdsDTO> findAllAdsByStatus();
    List<AdsDTO> findAllAds();
}
