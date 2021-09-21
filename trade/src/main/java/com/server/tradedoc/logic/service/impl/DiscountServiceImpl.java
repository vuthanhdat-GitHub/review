package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.converter.DiscountConverter;
import com.server.tradedoc.logic.dto.DiscountDTO;
import com.server.tradedoc.logic.dto.reponse.*;
import com.server.tradedoc.logic.entity.DiscountEntity;
import com.server.tradedoc.logic.repository.DiscountRepository;
import com.server.tradedoc.logic.service.DiscountService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.DateTimeUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * DiscountServiceImpl
 *
 * @author DatDV
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountConverter discountConverter;

    /**
     * create
     *
     * @param discountDTO : request body for insert
     * @return CreatedResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public CreatedResponse create(DiscountDTO discountDTO) throws ParseException {
        if (discountDTO.getCode().equals("")) {
            throw new CustomException("code discount not empty" , CommonUtils.putError("code" , "ERR_0034"));
        }
        if (discountDTO.getDescription().equals("")) {
            throw new CustomException("description discount not empty" , CommonUtils.putError("description" , "ERR_0034"));
        }
        if (discountDTO.getDiscountPercent() < 0 || discountDTO.getDiscountPercent() > 100) {
            throw new CustomException("discount percent not be less than zero and greater than one hundred" , CommonUtils.putError("discountPercent" , "ERR_0034"));
        }
        if (discountDTO.getExpireDateStart() == null) {
            throw new CustomException("expire start date discount not empty" , CommonUtils.putError("expireDateStart" , "ERR_0034"));
        }
        if (discountDTO.getExpireDateEnd() == null) {
            throw new CustomException("expire end date discount not empty" , CommonUtils.putError("expireDateEnd" , "ERR_0034"));
        }
        if (DateTimeUtils.compareBeforeDateTimeNow(discountDTO.getExpireDateStart())) {
            throw new CustomException("The start date cannot be prior to the current date" , CommonUtils.putError("discountDTO" , "ERR_0030"));
        }
        if (discountDTO.getExpireDateStart().after(discountDTO.getExpireDateEnd())) {
            throw new CustomException("the start date cannot be after the end date" , CommonUtils.putError("discountDTO" , "ERR_0025"));
        }
        List<DiscountEntity> discountEntities = discountRepository.findByDateStartAndDateStart(discountDTO.getExpireDateStart() , discountDTO.getExpireDateEnd());
        if (!discountEntities.isEmpty()) {
            throw new CustomException("The same time period as the other discount" , CommonUtils.putError("discountDTO" , "ERR_0030"));
        }
        DiscountEntity discountEntityValidation = discountRepository.findByCode(discountDTO.getCode());
        if (discountEntityValidation != null) {
            throw new CustomException("code discount already exist" , CommonUtils.putError("expireDateEnd" , "ERR_0034"));
        }
        CreatedResponse response = new CreatedResponse();
        List<DiscountEntity> discountEntitiesDB = discountRepository.findAllByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        discountDTO.setCode(discountDTO.getCode().toUpperCase().trim().replace(" " , "_"));
        DiscountEntity discountEntity = discountConverter.toEntity(discountDTO);
        if (discountEntitiesDB.size() > 0) {
            discountEntity.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        } else {
            discountEntity.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        }
        discountEntity.setCreatedDate(new Date(System.currentTimeMillis()));
        discountEntity.setModifiedDate(new Date(System.currentTimeMillis()));
        response.setIdInserted(discountRepository.save(discountEntity).getId());
        return response;
    }

    /**
     * updateStatusDiscount
     *
     * @param id : id of discount for update
     * @return UpdateResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public UpdateResponse updateStatusDiscount(Long id) {
        UpdateResponse response = new UpdateResponse();
        DiscountEntity discountEntity = discountRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        if (discountEntity != null && !discountEntity.getId().equals(id)) {
            throw new CustomException("There are other discount currently active" , CommonUtils.putError("status" , "ERR_0025"));
        }
        DiscountEntity discountEntityUpdate = discountRepository.findById(id).get();
        if (discountEntityUpdate.getStatus().equals(AppConstant.ACTIVE.ACTIVE_STATUS)) {
            discountEntityUpdate.setStatus(AppConstant.ACTIVE.INACTIVE_STATUS);
        } else {
            discountEntityUpdate.setStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        }
        discountRepository.save(discountEntityUpdate);
        response.setIdUpdated(id);
        return response;
    }

    /**
     * delete
     *
     * @param id : id discount for delete
     * @return DeleteResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public DeleteResponse delete(Long id) {
        DeleteResponse response = new DeleteResponse();
        discountRepository.deleteById(id);
        response.setIdsDeleted(Arrays.asList(id));
        return response;
    }

    /**
     * getAllDiscount
     *
     * @return List<DiscountDTO>
     */
    @Override
    public List<DiscountDTO> getAllDiscount() {
        List<DiscountEntity> discountEntities = discountRepository.findAllByOrderByIdDesc();
        return discountConverter.toListDto(discountEntities);
    }

    /**
     * findOne
     *
     * @param id
     * @return DiscountDTO {com.server.tradedoc.logic.dto}
     */
    @Override
    public DiscountDTO findOne(Long id) {
        DiscountDTO discountDTO = discountConverter.toDto(discountRepository.findById(id).get());
        return discountDTO;
    }

    /**
     * findDiscountForClient
     *
     * @return DiscountDTO {com.server.tradedoc.logic.dto}
     */
    @Override
    public DiscountClientResponse findDiscountForClient() {
        DiscountClientResponse response = new DiscountClientResponse();
        DiscountEntity resultEntity = discountRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        if (resultEntity != null) {
            if (!DateTimeUtils.equalsDateTimeNow(resultEntity.getExpireDateStart()) && DateTimeUtils.compareAfterDateTimeNow(resultEntity.getExpireDateStart())) {
                response.setDiscount(null);
                response.setTimeRemaining(0);
                return response;
            }
            if (DateTimeUtils.minusDayAndDateTimeNow(resultEntity.getExpireDateEnd()) <= 0) {
                response.setDiscount(null);
                response.setTimeRemaining(0);
                return response;
            }
            response.setDiscount(discountConverter.toDto(resultEntity));
            response.setTimeRemaining(Integer.parseInt(DateTimeUtils.minusDayAndDateTimeNow(resultEntity.getExpireDateEnd()).toString()));
        } else {
            response.setDiscount(null);
            response.setTimeRemaining(0);
        }
        return response;
    }

    /**
     * notificationDiscount
     *
     * @return NotificationDiscount {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    public NotificationDiscount notificationDiscount() {
        NotificationDiscount response = new NotificationDiscount();
        DiscountEntity discountEntity = discountRepository.findByStatus(AppConstant.ACTIVE.ACTIVE_STATUS);
        if (discountEntity == null) {
            return new NotificationDiscount();
        }
        if (DateTimeUtils.compareAfterDateTimeNow(discountEntity.getExpireDateStart())) {
            return new NotificationDiscount();
        }
        Long timeRemaining = DateTimeUtils.minusDayAndDateTimeNow(discountEntity.getExpireDateEnd());
        if (timeRemaining > 3) {
            response.setTimeRemaining(Integer.parseInt(timeRemaining.toString()));
            response.setMessage("");
        }
        if (timeRemaining <= 3) {
            response.setTimeRemaining(Integer.parseInt(timeRemaining.toString()));
            response.setMessage("thời hạn discount còn lại "+timeRemaining+" ngày lưu ý tắt trạng thái hoạt động của discount");
        }
        response.setDiscount(discountConverter.toDto(discountEntity));
        return response;
    }
}
