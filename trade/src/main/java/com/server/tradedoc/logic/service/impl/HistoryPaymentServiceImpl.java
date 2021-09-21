package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.converter.HistoryPaymentConverter;
import com.server.tradedoc.logic.dto.HistoryPaymentDTO;
import com.server.tradedoc.logic.dto.reponse.CountResponse;
import com.server.tradedoc.logic.dto.reponse.GetAllHistoryPaymentResponse;
import com.server.tradedoc.logic.dto.reponse.HistoryPaymentSearchDTO;
import com.server.tradedoc.logic.dto.reponse.StatisticalAdminResponse;
import com.server.tradedoc.logic.entity.HistoryPaymentEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import com.server.tradedoc.logic.entity.UserEntity;
import com.server.tradedoc.logic.enums.PaymentType;
import com.server.tradedoc.logic.repository.HistoryPaymentRepository;
import com.server.tradedoc.logic.service.HistoryPaymentService;
import com.server.tradedoc.logic.service.UserService;
import com.server.tradedoc.utils.FilesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

/**
 * HistoryPaymentServiceImpl
 *
 * @author DatDV
 */
@Service
public class HistoryPaymentServiceImpl implements HistoryPaymentService {

    @Autowired
    private HistoryPaymentRepository historyPaymentRepository;

    @Autowired
    private HistoryPaymentConverter historyPaymentConverter;

    @Autowired
    private FilesUtils filesUtils;

    @Autowired
    private UserService userService;

    /**
     * getAllPaymentType
     *
     * @return
     */
    @Override
    public Map<String, String> getAllPaymentType() {
        Map<String, String> result = new HashMap<>();
        Stream.of(PaymentType.values()).forEach(item -> {
            result.put(item.name(), item.getValue());
        });
        return result;
    }

    /**
     * save
     *
     * @param productsEntity
     * @param userEntity
     * @param paymentType
     * @param total
     * @return
     */
    @Override
    @Transactional
    public HistoryPaymentDTO save(ProductsEntity productsEntity, UserEntity userEntity, PaymentType paymentType, String total) {
        HistoryPaymentEntity historyPaymentEntity = new HistoryPaymentEntity();
        historyPaymentEntity.setPaymentType(paymentType.toString());
        historyPaymentEntity.setProduct(productsEntity);
        historyPaymentEntity.setCustomer(userEntity);
        historyPaymentEntity.setTotal(Long.parseLong(total.split("\\.")[0]));
        historyPaymentEntity.setStatus(AppConstant.statusSendFile.SUCCESS);
        historyPaymentEntity.setDeleted(AppConstant.deletedStatus.ACTIVE);
        historyPaymentEntity.setCreatedDate(new Date(System.currentTimeMillis()));
        historyPaymentEntity.setModifiedDate(new Date(System.currentTimeMillis()));
        return historyPaymentConverter.toDto(historyPaymentRepository.save(historyPaymentEntity));
    }

    /**
     * getAllHistoryPayment
     *
     * @param builder
     * @param pageable
     * @return
     */
    @Override
    public GetAllHistoryPaymentResponse getAllHistoryPayment(SearchHistoryPaymentBuilder builder, Pageable pageable) {
        GetAllHistoryPaymentResponse response = new GetAllHistoryPaymentResponse();
        List<HistoryPaymentSearchDTO> result = historyPaymentRepository.findAllHistoryPayment(builder, pageable);
        result.forEach(item -> {
            try {
                item.setProductAvatar(filesUtils.genFilePath(item.getProductAvatar()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        response.setHistoryPayments(result);
        response.setCountItem(historyPaymentRepository.countByCondition(builder));
        return response;
    }

    /**
     * countByCondition
     *
     * @param builder
     * @return
     */
    @Override
    public CountResponse countByCondition(SearchHistoryPaymentBuilder builder) {
        CountResponse result = new CountResponse();
        result.setCountItem(historyPaymentRepository.countByCondition(builder));
        return result;
    }

    /**
     * statisticalAdmin
     *
     * @return
     */
    @Override
    public StatisticalAdminResponse statisticalAdmin() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        StatisticalAdminResponse response = new StatisticalAdminResponse();
        response.setTotalCustomer(userService.countUserCustomer().toString());
        response.setTotalBitCoin(format.format(historyPaymentRepository.sumTotalByPaymentType(PaymentType.BIT_COIN.toString())));
        response.setTotalPayPal(format.format(historyPaymentRepository.sumTotalByPaymentType(PaymentType.PAY_PAL.toString())));
        response.setTotalVisa(format.format(historyPaymentRepository.sumTotalByPaymentType(PaymentType.VISA.toString())));
        return response;
    }

}
