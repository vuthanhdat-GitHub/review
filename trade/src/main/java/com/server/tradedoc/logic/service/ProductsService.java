package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.builder.SearchProductBuilder;
import com.server.tradedoc.logic.dto.ProductsDTO;
import com.server.tradedoc.logic.dto.paymentrequest.PayPalDTO;
import com.server.tradedoc.logic.dto.paymentrequest.PaymentIntentDTO;
import com.server.tradedoc.logic.dto.reponse.*;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * ProductsService
 *
 * @author DatDV
 */
public interface ProductsService {
    List<ProductsDTO> getAllProducts(List<Long> categoryIds, String collection) throws URISyntaxException;
    GetProductByConditionResponse getProductByCondition(SearchProductBuilder builder , Pageable pageable) throws URISyntaxException;
    CountResponse count(SearchProductBuilder builder);
    CreatedResponse createProduct(List<MultipartFile> fileMt5 , List<MultipartFile> fileMt4 , String data , MultipartFile avatar);
    UpdateResponse updateProduct(List<MultipartFile> fileMt5, List<MultipartFile> fileMt4 , String data, MultipartFile avatar);
    List<Long> deleteProduct(List<Long> ids);
    Map<String , Object> buyFileFree(Long productId , String type);
    Map<String , Object> createPayment(PayPalDTO payPalDTO);
    Map<String, Object> completePayment(String payerId , String paymentId , Long productId , String type);
    ProductsDTO getById(Long id , String role);
    Map<String , String> getProductTypes();
    Map<String , String> getProductCollection();
    Map<String , String> createCheckoutSessionStripe(Long productId , String discountCode) throws StripeException;
    Map<String, Object> retrieveStripe(String idStripe, Long productId, String type) throws StripeException;
}
