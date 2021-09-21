package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.converter.AdsConverter;
import com.server.tradedoc.logic.dto.AdsDTO;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;
import com.server.tradedoc.logic.dto.reponse.DeleteResponse;
import com.server.tradedoc.logic.dto.reponse.UpdateResponse;
import com.server.tradedoc.logic.entity.AdsEntity;
import com.server.tradedoc.logic.repository.AdsRepository;
import com.server.tradedoc.logic.service.AdsService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private AdsConverter adsConverter;

    @Override
    public CreatedResponse create(AdsDTO adsDTO) {
        CreatedResponse response = new CreatedResponse();
        if (adsDTO.getName().equals("")) {
            throw new CustomException("name not empty" , CommonUtils.putError("name" , "ERR_0034"));
        }
        if (adsDTO.getLinkAds().equals("")) {
            throw new CustomException("link ads not empty" , CommonUtils.putError("linkAds" , "ERR_0034"));
        }
        if (adsDTO.getLinkImageAds().equals("")) {
            throw new CustomException("link image ads not empty" , CommonUtils.putError("linkImageAds" , "ERR_0034"));
        }
        List<AdsEntity> resultEntity = adsRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        AdsEntity entity = adsConverter.toEntity(adsDTO);
        if (resultEntity.size() >= 2) {
            entity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        } else {
            entity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        }
        response.setIdInserted(adsRepository.save(entity).getId());
        return response;
    }

    @Override
    public UpdateResponse updateByStatus(Long id) {
        UpdateResponse response = new UpdateResponse();
        AdsEntity adsEntity = adsRepository.findById(id).get();
        List<AdsEntity> resultEntity = adsRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        List<Long> ids = resultEntity.stream().map(AdsEntity::getId).collect(Collectors.toList());
        if (resultEntity.size() >= 2 && !ids.contains(id)) {
            throw new CustomException("active up to two ads" , CommonUtils.putError("status" , "ERR_0012"));
        }
        if (adsEntity.getStatus().equals(AppConstant.ACTIVE.ACTIVE_STATUS)) {
            adsEntity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        } else {
            adsEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        }
        response.setIdUpdated(adsRepository.save(adsEntity).getId());
        return response;
    }

    @Override
    public DeleteResponse delete(Long id) {
        DeleteResponse response = new DeleteResponse();
        adsRepository.deleteById(id);
        response.setIdsDeleted(Arrays.asList(id));
        return response;
    }

    @Override
    public List<AdsDTO> findAllAdsByStatus() {
        return adsConverter.toListDto(adsRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS));
    }

    @Override
    public List<AdsDTO> findAllAds() {
        return adsConverter.toListDto(adsRepository.findAll());
    }
}
