package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.DiscountDTO;
import com.server.tradedoc.logic.dto.reponse.*;

import java.text.ParseException;
import java.util.List;

/**
 * DiscountService
 *
 * @author DatDV
 */
public interface DiscountService {
    CreatedResponse create(DiscountDTO discountDTO) throws ParseException;
    UpdateResponse updateStatusDiscount(Long id);
    DeleteResponse delete(Long id);
    List<DiscountDTO> getAllDiscount();
    DiscountDTO findOne(Long id);
    DiscountClientResponse findDiscountForClient();
    NotificationDiscount notificationDiscount();
}
