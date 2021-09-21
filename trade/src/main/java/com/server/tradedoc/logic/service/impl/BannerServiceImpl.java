package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.converter.BannerConverter;
import com.server.tradedoc.logic.dto.BannerDTO;
import com.server.tradedoc.logic.dto.reponse.CreatedResponse;
import com.server.tradedoc.logic.dto.reponse.DeleteResponse;
import com.server.tradedoc.logic.dto.reponse.UpdateResponse;
import com.server.tradedoc.logic.entity.BannerEntity;
import com.server.tradedoc.logic.repository.BannerRepository;
import com.server.tradedoc.logic.service.BannerService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.FilesUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BannerServiceImpl
 *
 * @author DatDV
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private FilesUtils filesUtils;

    @Autowired
    private BannerConverter bannerConverter;

    /**
     * create
     *
     * @param image
     * @return CreatedResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public CreatedResponse create(MultipartFile image) {
        CreatedResponse response = new CreatedResponse();
        List<BannerEntity> entities = bannerRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        BannerEntity bannerEntity = new BannerEntity();
        if (entities.size() > 2) {
            bannerEntity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        } else {
            bannerEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        }
        String fileName = filesUtils.generateFileName(image.getOriginalFilename());
        bannerEntity.setName(fileName);
        bannerEntity.setPathFile(filesUtils.save(image, "/banner/", fileName));
        response.setIdInserted(bannerRepository.save(bannerEntity).getId());
        return response;
    }

    /**
     * updateStatusBanner
     *
     * @param id
     * @return UpdateResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public UpdateResponse updateStatusBanner(Long id) {
        UpdateResponse response = new UpdateResponse();
        List<BannerEntity> entities = bannerRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        if (entities.size() > 3) {
            throw new CustomException("Only three banners are allowed", CommonUtils.putError("status", "ERR_0025"));
        }
        BannerEntity bannerEntity = bannerRepository.findById(id).get();
        if (bannerEntity.getStatus().equals(AppConstant.ACTIVE.ACTIVE_STATUS)) {
            bannerEntity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        } else if (bannerEntity.getStatus().equals(AppConstant.ACTIVE.INACTIVE_STATUS)) {
            bannerEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        }
        bannerRepository.save(bannerEntity);
        response.setIdUpdated(id);
        return response;
    }

    /**
     * delete
     *
     * @param id
     * @return DeleteResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public DeleteResponse delete(Long id) {
        DeleteResponse response = new DeleteResponse();
        bannerRepository.deleteById(id);
        response.setIdsDeleted(Arrays.asList(id));
        return response;
    }

    /**
     * findAllBannerByStatus
     *
     * @return  List BannerDTO
     * @throws URISyntaxException
     */
    @Override
    public List<BannerDTO> findAllBannerByStatus() throws URISyntaxException {
        List<BannerDTO> result = new ArrayList<>();
        List<BannerEntity> bannerEntities = bannerRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        for (BannerEntity bannerEntity : bannerEntities) {
            bannerEntity.setPathFile(filesUtils.genFilePath(bannerEntity.getPathFile()));
            result.add(bannerConverter.toDto(bannerEntity));
        }
        return result;
    }

    /**
     * findAllBanner
     *
     * @return List BannerDTO
     * @throws URISyntaxException
     */
    @Override
    public List<BannerDTO> findAllBanner() throws URISyntaxException {
        List<BannerDTO> result = new ArrayList<>();
        List<BannerEntity> bannerEntities = bannerRepository.findAll();
        for (BannerEntity bannerEntity : bannerEntities) {
            bannerEntity.setPathFile(filesUtils.genFilePath(bannerEntity.getPathFile()));
            result.add(bannerConverter.toDto(bannerEntity));
        }
        return result;
    }
}
