package com.server.tradedoc.logic.service.impl;

import com.google.gson.Gson;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.server.tradedoc.constants.AppConstant;
import com.server.tradedoc.logic.builder.SearchProductBuilder;
import com.server.tradedoc.logic.converter.ProductsConverter;
import com.server.tradedoc.logic.dto.HistoryPaymentDTO;
import com.server.tradedoc.logic.dto.ProductsDTO;
import com.server.tradedoc.logic.dto.paymentrequest.PayPalDTO;
import com.server.tradedoc.logic.dto.reponse.*;
import com.server.tradedoc.logic.entity.*;
import com.server.tradedoc.logic.enums.*;
import com.server.tradedoc.logic.repository.*;
import com.server.tradedoc.logic.service.HistoryPaymentService;
import com.server.tradedoc.logic.service.ProductsService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.FilesUtils;
import com.server.tradedoc.utils.JwtTokenUtils;
import com.server.tradedoc.utils.MailUtils;
import com.server.tradedoc.utils.error.CustomException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ProductsServiceImpl
 *
 * @author DatDV
 */
@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private Gson gson;

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private FilesProductRepository filesProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsConverter productsConverter;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HistoryPaymentService historyPaymentService;

    @Autowired
    private HistoryPaymentRepository historyPaymentRepository;

    @Autowired
    private FilesUtils filesUtils;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private APIContext apiContext;

    @Value("${stripe.public.key}")
    private String secretKeyStripe;

    /**
     * getAllProducts : get all product for client
     *
     * @param categoryIds : list id category for filter product
     * @param collection  : collection for filter product
     * @return List ProductsDTO {com.server.tradedoc.logic.dto}
     * @throws URISyntaxException
     */
    @Override
    public List<ProductsDTO> getAllProducts(List<Long> categoryIds, String collection) throws URISyntaxException {
        if (collection.equals("")) {
            throw new CustomException("collection product empty", CommonUtils.putError("collection", "ERR_0034"));
        }
        List<ProductsEntity> entitys = productsRepository.findAllProductByCategoryIds(categoryIds, collection);
        List<ProductsDTO> result = new ArrayList<>();
        if (!entitys.isEmpty()) {
            for (ProductsEntity productsEntity : entitys) {
                productsEntity.setAvatar(filesUtils.genFilePath(productsEntity.getAvatar()));
                productsEntity.setFilesProducts(new ArrayList<>());
                ProductsDTO productsDTO = productsConverter.customConvertToDto(productsEntity);
                result.add(productsDTO);
            }
        }
        return result;
    }

    /**
     * getProductByCondition : function for search of admin
     *
     * @param builder  : params for where clause
     * @param pageable : pageable for limit , offset
     * @return List ProductsSearchDTO {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    public GetProductByConditionResponse getProductByCondition(SearchProductBuilder builder, Pageable pageable) throws URISyntaxException {
        GetProductByConditionResponse response = new GetProductByConditionResponse();
        List<ProductsSearchDTO> result = productsRepository.findAllProductByCondition(builder, pageable);
        List<ProductsSearchDTO> products = new ArrayList<>();
        for (ProductsSearchDTO productsSearchDTO : result) {
            productsSearchDTO.setTypes(Arrays.asList(productsSearchDTO.getType().split(",")));
            productsSearchDTO.setType(null);
            productsSearchDTO.setAvatar(filesUtils.genFilePath(productsSearchDTO.getAvatar()));
            products.add(productsSearchDTO);
        }
        response.setProducts(products);
        response.setCountItem(productsRepository.countProductByCondition(builder));
        return response;
    }

    /**
     * count : function get total item of product
     *
     * @param builder : params for where clause
     * @return : CountResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    public CountResponse count(SearchProductBuilder builder) {
        CountResponse result = new CountResponse();
        result.setCountItem(productsRepository.countProductByCondition(builder));
        return result;
    }

    /**
     * createProduct : insert new product to database
     *
     * @param fileMt5: list file mt5 of product
     * @param fileMt4  : list file mt4 of product
     * @param data     : data insert of new product
     * @param avatar   : file avatar of product
     * @return CreatedResponse : response after insert new product success
     */
    @Override
    @Transactional
    public CreatedResponse createProduct(List<MultipartFile> fileMt5, List<MultipartFile> fileMt4, String data, MultipartFile avatar) {
        CreatedResponse response = new CreatedResponse();
        ProductsDTO productsDTO = gson.fromJson(data, ProductsDTO.class);
        String productType = StringUtils.join(productsDTO.getTypes(), ",");

        // validate empty params
        if ((fileMt5.isEmpty() || fileMt5.get(0).isEmpty()) && productType.contains("MT5")) {
            throw new CustomException("fileMt5 empty", CommonUtils.putError("fileMt5", "ERR_0034"));
        } else if (!fileMt5.get(0).isEmpty() && !productType.contains("MT5")) {
            throw new CustomException("type MT5 empty", CommonUtils.putError("fileMt5", "ERR_0034"));
        }
        if ((fileMt4.isEmpty() || fileMt4.get(0).isEmpty()) && productType.contains("MT4")) {
            throw new CustomException("fileMt4 empty", CommonUtils.putError("fileMt4", "ERR_0034"));
        } else if (!fileMt4.get(0).isEmpty() && !productType.contains("MT4")) {
            throw new CustomException("type MT4 empty", CommonUtils.putError("fileMt4", "ERR_0034"));
        }
        if (avatar == null || avatar.isEmpty()) {
            throw new CustomException("avatar empty", CommonUtils.putError("avatar", "ERR_0034"));
        }
        if (productsDTO.getCategoryIds() == null || productsDTO.getCategoryIds().isEmpty()) {
            throw new CustomException("category not null", CommonUtils.putError("data", "ERR_0034"));
        }

        //insert new product
        ProductsEntity productsEntity = productsConverter.toEntity(productsDTO);
        productsEntity.setType(productType);
        productsEntity.setAvatar(filesUtils.save(avatar, "/avatar_product/", filesUtils.generateFileName(avatar.getOriginalFilename())));
        List<CategoryEntity> categoryEntities = categoryRepository.findCategoryEntitiesByIdIn(productsDTO.getCategoryIds());
        productsEntity.setCategorys(categoryEntities);
        productsEntity.setModifiedDate(new Date(System.currentTimeMillis()));
        productsEntity.setCreatedDate(new Date(System.currentTimeMillis()));
        ProductsEntity productsEntityAfterInsert = productsRepository.save(productsEntity);

        // upload mt4 file of product
        if (!fileMt4.get(0).isEmpty()) {
            for (MultipartFile multipartFile : fileMt4) {
                FilesProductEntity filesProductEntity = new FilesProductEntity();
                String fileName = filesUtils.generateFileName(multipartFile.getOriginalFilename());
                filesProductEntity.setPathFile(filesUtils.save(multipartFile, "/fileproducts/", fileName));
                filesProductEntity.setName(fileName);
                filesProductEntity.setProductType(ProductTypes.MT4.toString());
                filesProductEntity.setProducts(productsEntityAfterInsert);
                filesProductEntity.setCreatedDate(new Date(System.currentTimeMillis()));
                filesProductEntity.setModifiedDate(new Date(System.currentTimeMillis()));
                filesProductRepository.save(filesProductEntity);
            }
        }

        // upload mt5 file of product
        if (!fileMt5.get(0).isEmpty()) {
            for (MultipartFile multipartFile : fileMt5) {
                FilesProductEntity filesProductEntity = new FilesProductEntity();
                String fileName = filesUtils.generateFileName(multipartFile.getOriginalFilename());
                filesProductEntity.setPathFile(filesUtils.save(multipartFile, "/fileproducts/", fileName));
                filesProductEntity.setName(fileName);
                filesProductEntity.setProductType(ProductTypes.MT5.toString());
                filesProductEntity.setProducts(productsEntityAfterInsert);
                filesProductEntity.setCreatedDate(new Date(System.currentTimeMillis()));
                filesProductEntity.setModifiedDate(new Date(System.currentTimeMillis()));
                filesProductRepository.save(filesProductEntity);
            }
        }

        // set param response of API
        response.setIdInserted(productsEntityAfterInsert.getId());
        return response;
    }

    /**
     * updateProduct
     *
     * @param fileMt5
     * @param fileMt4
     * @param data
     * @param avatar
     * @return UpdateResponse {com.server.tradedoc.logic.dto.reponse}
     */
    @Override
    @Transactional
    public UpdateResponse updateProduct(List<MultipartFile> fileMt5, List<MultipartFile> fileMt4, String data, MultipartFile avatar) {
        UpdateResponse response = new UpdateResponse();
        ProductsDTO productsDTO = gson.fromJson(data, ProductsDTO.class);
        String productType = StringUtils.join(productsDTO.getTypes(), ",");

        ProductsEntity productsEntity = productsRepository.findById(productsDTO.getId()).get();

        if (!productType.contains("MT4")) {
            filesProductRepository.deleteByProductsAndProductType(productsEntity, ProductTypes.MT4.toString());
        }

        if (!productType.contains("MT5")) {
            filesProductRepository.deleteByProductsAndProductType(productsEntity, ProductTypes.MT5.toString());
        }
        productsDTO.setAvatar(productsEntity.getAvatar());
        productsDTO.setCreatedDate(productsEntity.getCreatedDate());
        productsDTO.setModifiedDate(new Date(System.currentTimeMillis()));
        productsDTO.setModifiedBy(jwtTokenUtils.getUserNameFromToken());
        productsEntity = productsConverter.toEntity(productsDTO);
        if (avatar != null && !avatar.isEmpty()) {
            productsEntity.setAvatar(filesUtils.save(avatar, "/avatar_product/", filesUtils.generateFileName(avatar.getOriginalFilename())));
        }
        productsEntity.setType(productType);
        productsEntity.setModifiedDate(new Date(System.currentTimeMillis()));
        List<CategoryEntity> categoryEntities = categoryRepository.findCategoryEntitiesByIdIn(productsDTO.getCategoryIds());
        productsEntity.setCategorys(categoryEntities);
        ProductsEntity productsEntityAfterUpdate = productsRepository.save(productsEntity);

        // upload mt4 file of product
        if (!fileMt4.isEmpty() && !fileMt4.get(0).isEmpty()) {
            filesProductRepository.deleteByProductsAndProductType(productsEntityAfterUpdate, ProductTypes.MT4.toString());
            for (MultipartFile multipartFile : fileMt4) {
                FilesProductEntity filesProductEntity = new FilesProductEntity();
                String fileName = filesUtils.generateFileName(multipartFile.getOriginalFilename());
                filesProductEntity.setPathFile(filesUtils.save(multipartFile, "/fileproducts/", fileName));
                filesProductEntity.setName(fileName);
                filesProductEntity.setProductType(ProductTypes.MT4.toString());
                filesProductEntity.setProducts(productsEntityAfterUpdate);
                filesProductEntity.setCreatedDate(new Date(System.currentTimeMillis()));
                filesProductEntity.setModifiedDate(new Date(System.currentTimeMillis()));
                filesProductRepository.save(filesProductEntity);
            }
        }

        // upload mt5 file of product
        if (!fileMt5.isEmpty() && !fileMt5.get(0).isEmpty()) {
            filesProductRepository.deleteByProductsAndProductType(productsEntityAfterUpdate, ProductTypes.MT5.toString());
            for (MultipartFile multipartFile : fileMt5) {
                FilesProductEntity filesProductEntity = new FilesProductEntity();
                String fileName = filesUtils.generateFileName(multipartFile.getOriginalFilename());
                filesProductEntity.setPathFile(filesUtils.save(multipartFile, "/fileproducts/", fileName));
                filesProductEntity.setName(fileName);
                filesProductEntity.setProductType(ProductTypes.MT5.toString());
                filesProductEntity.setProducts(productsEntityAfterUpdate);
                filesProductEntity.setCreatedDate(new Date(System.currentTimeMillis()));
                filesProductEntity.setModifiedDate(new Date(System.currentTimeMillis()));
                filesProductRepository.save(filesProductEntity);
            }
        }
        response.setIdUpdated(productsEntityAfterUpdate.getId());
        return response;
    }

    /**
     * deleteProduct : delete product in database
     *
     * @param ids : List id product for datele
     * @return List<Long>
     */
    @Override
    @Transactional
    public List<Long> deleteProduct(List<Long> ids) {
        for (Long id : ids) {
            ProductsEntity productsEntity = productsRepository.findById(id).get();
            imageRepository.deleteImageEntityByProducts(productsEntity);
            historyPaymentRepository.deleteAllByProduct(productsEntity);
            filesProductRepository.deleteAllByProducts(productsEntity);
            productsRepository.deleteById(id);
        }
        return ids;
    }

    /**
     * buyFileFree
     *
     * @param productId
     * @param type
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> buyFileFree(Long productId, String type) {
        Map<String, Object> response = new HashMap<>();
        ProductsEntity productsEntity = productsRepository.findById(productId).get();
        if (!productsEntity.getPrice().equals(0)) {
            throw new CustomException("product not free", CommonUtils.putError("productId", "ERR_0025"));
        }
        if (!productsEntity.getType().contains(type)) {
            throw new CustomException("product type not mapping", CommonUtils.putError("type", "ERR_0015"));
        }
        List<String> filePaths = filesProductRepository.findByProductsAndProductType(productsEntity, type).stream().map(FilesProductEntity::getPathFile).collect(Collectors.toList());
        String template = "Dear "+jwtTokenUtils.getFullNameFromToken()+" \n";
        template += "\n";
        template += "\n";
        template += "Thanks a lot for purchasing "+productsEntity.getProductName()+"! The details of your purchase are";
        template += "\n";
        template += "\n";
        template += "Please, don't delete this email";
        template += "\n";
        template += "\n";
        template += "Best regards,\n" +
                "- Indicator Markets -";
        template += "\n";
        template += "\n";
        template += "Trading Solutions\n" +
                "https://www.indicatormarkets.com/";
        template += "\n";
        template += "\n";
        template += "the file as attached";
        String subject = "Indicator Markets Team";
        Boolean mailSendResult = mailUtils.sendMultiFileToMail(template, jwtTokenUtils.getEmailFromToken(), subject, filePaths);
        if (!mailSendResult) {
            throw new CustomException("cannot send email", CommonUtils.putError("email", "ERR_0013"));
        }

        response.put("status", "success");
        return response;
    }

    /**
     * createPayment : create payment paypal
     *
     * @param payPalDTO
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> createPayment(PayPalDTO payPalDTO) {
        DiscountEntity discountEntity = null;
        ProductsEntity productsEntity = productsRepository.findById(payPalDTO.getProductId()).get();
        Double totalAmount = Double.parseDouble(productsEntity.getPrice().toString() + ".0");
        if (!payPalDTO.getDiscountCode().equals("")) {
            discountEntity = discountRepository.findByCodeAndStatus(payPalDTO.getDiscountCode() , AppConstant.ACTIVE.ACTIVE_STATUS);
            if (discountEntity == null) {
                throw new CustomException("discount code not active" , CommonUtils.putError("discountCode" , "ERR_0034"));
            }
            totalAmount = (Double.parseDouble(productsEntity.getPrice().toString() + ".0") / 100) * (100 - discountEntity.getDiscountPercent());
        }

        Map<String, Object> response = new HashMap<String, Object>();

        Amount amount = new Amount();
        amount.setCurrency(payPalDTO.getCurrent());
        amount.setTotal(totalAmount.toString());
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(PayPalPaymentMethod.paypal.toString());
        Payment payment = new Payment();
        payment.setIntent(PayPalPaymentIntent.sale.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("https://indicatorsmarket.netlify.app/checkout/cancel");
        redirectUrls.setReturnUrl("https://indicatorsmarket.netlify.app/checkout/success");
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;

        try {
            String redirectUrl = "";
            createdPayment = payment.create(apiContext);
            if (createdPayment != null) {
                List<Links> links = createdPayment.getLinks();
                for (Links link : links) {
                    if (link.getRel().equals("approval_url")) {
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            return null;
        }

        return response;
    }

    /**
     * completePayment
     *
     * @param payerId
     * @param paymentId
     * @param productId
     * @param type
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> completePayment(String payerId, String paymentId, Long productId, String type) {
        Map<String, Object> response = new HashMap<>();
        if (type.equals("")) {
            throw new CustomException("product type empty", CommonUtils.putError("type", "ERR_0034"));
        }
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        try {
            Payment createdPayment = payment.execute(apiContext, paymentExecution);
            if (createdPayment != null && createdPayment.getPayer().getStatus().equals("VERIFIED")) {
                response.put("status", "success");
                ProductsEntity productsEntity = productsRepository.findById(productId).get();
                UserEntity userEntity = userRepository.findById(jwtTokenUtils.getUserIdFromToken()).get();
                List<String> pathFilesProducts = filesProductRepository.findByProductsAndProductType(productsEntity, type).stream().map(FilesProductEntity::getPathFile).collect(Collectors.toList());
                if (productsEntity.getId() != null && userEntity.getId() != null) {
                    String template = "Dear "+jwtTokenUtils.getFullNameFromToken()+" \n";
                    template += "\n";
                    template += "\n";
                    template += "Thanks a lot for purchasing "+productsEntity.getProductName()+"! The details of your purchase are";
                    template += "\n";
                    template += "\n";
                    template += "Please, don't delete this email";
                    template += "\n";
                    template += "\n";
                    template += "Best regards,\n" +
                            "- Indicator Markets -";
                    template += "\n";
                    template += "\n";
                    template += "Trading Solutions\n" +
                            "https://www.indicatormarkets.com/";
                    template += "\n";
                    template += "\n";
                    template += "the file as attached";
                    String subject = "Indicator Markets Team";
                    Boolean resultSendMail = mailUtils.sendMultiFileToMail(template, jwtTokenUtils.getEmailFromToken(), subject, pathFilesProducts);
                    if (!resultSendMail) {
                        throw new CustomException("cannot send email", CommonUtils.putError("email", "ERR_0013"));
                    }
                    HistoryPaymentDTO historyPaymentDTO = historyPaymentService.save(productsEntity, userEntity, PaymentType.PAY_PAL, createdPayment.getTransactions().get(0).getAmount().getTotal());
                    response.put("status_history", historyPaymentDTO.getStatus() == 1 ? "success_send" : "error_send");
                } else {
                    if (productsEntity.getId() == null) {
                        throw new CustomException("cannot find product", CommonUtils.putError("productId", "ERR_0034"));
                    }
                    if (userEntity.getId() == null) {
                        throw new CustomException("cannot find customer", CommonUtils.putError("customerId", "ERR_0034"));
                    }
                }
            }
        } catch (PayPalRESTException e) {
            throw new CustomException("error paypal", CommonUtils.putError("paypal", e.getMessage()));
        }
        return response;
    }

    /**
     * getById
     *
     * @param id
     * @param role
     * @return
     */
    @Override
    public ProductsDTO getById(Long id, String role) {
        ProductsDTO[] response = {null};
        productsRepository.findById(id).ifPresent(productsEntity -> {
            response[0] = productsConverter.customConvertToDto(productsEntity);
            try {
                response[0].setAvatar(filesUtils.genFilePath(productsEntity.getAvatar()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            if (role.equals("client")) {
                response[0].setFilesProducts(null);
            }
            response[0].setCategoryIds(productsEntity.getCategorys().stream().map(CategoryEntity::getId).collect(Collectors.toList()));
        });
        return response[0];
    }

    /**
     * getProductTypes
     *
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> getProductTypes() {
        Map<String, String> result = new HashMap<>();
        Stream.of(ProductTypes.values()).forEach(item -> {
            result.put(item.name(), item.getValue());
        });
        return result;
    }

    /**
     * getProductCollection
     *
     * @return Map<String, String>
     */
    @Override
    public Map<String, String> getProductCollection() {
        Map<String, String> result = new HashMap<>();
        Stream.of(ProductCollection.values()).forEach(item -> {
            result.put(item.name(), item.getValue());
        });
        return result;
    }

    /**
     * createCheckoutSessionStripe
     *
     * @param productId
     * @return
     * @throws StripeException
     */
    @Override
    public Map<String, String> createCheckoutSessionStripe(Long productId , String discountCode) throws StripeException {
        DiscountEntity discountEntity = null;
        ProductsEntity productsEntity = productsRepository.findById(productId).get();
        Double totalAmount = Double.parseDouble(productsEntity.getPrice().toString() + ".0");
        if (!discountCode.equals("")) {
            discountEntity = discountRepository.findByCodeAndStatus(discountCode , AppConstant.ACTIVE.ACTIVE_STATUS);
            if (discountEntity == null) {
                throw new CustomException("discount code not active" , CommonUtils.putError("discountCode" , "ERR_0034"));
            }
            totalAmount = (Double.parseDouble(productsEntity.getPrice().toString() + ".0") / 100) * (100 - discountEntity.getDiscountPercent());
        }
        Long amount = totalAmount.longValue() * 100;
        Stripe.apiKey = secretKeyStripe;
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://indicatorsmarket.netlify.app/checkout/success")
                .setCancelUrl("https://indicatorsmarket.netlify.app/checkout/cancel")
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(amount)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(productsEntity.getProductName())
                                                                .build()
                                                ).build()
                                ).build()
                ).build();
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap();
        responseData.put("id", session.getId());
        return responseData;
    }

    /**
     * retrieveStripe
     *
     * @param idStripe
     * @param productId
     * @param type
     * @return
     * @throws StripeException
     */
    @Override
    public Map<String, Object> retrieveStripe(String idStripe, Long productId, String type) throws StripeException {
        Map<String, Object> response = new HashMap<>();
        if (type.equals("")) {
            throw new CustomException("product type empty", CommonUtils.putError("type", "ERR_0034"));
        }
        Stripe.apiKey = secretKeyStripe;
        Session session = Session.retrieve(idStripe);
        if (session != null && session.getPaymentStatus().equals("paid")) {
            ProductsEntity productsEntity = productsRepository.findById(productId).get();
            UserEntity userEntity = userRepository.findById(jwtTokenUtils.getUserIdFromToken()).get();
            List<String> pathFilesProducts = filesProductRepository.findByProductsAndProductType(productsEntity, type).stream().map(FilesProductEntity::getPathFile).collect(Collectors.toList());
            if (productsEntity.getId() != null && userEntity.getId() != null) {
                response.put("status", "success");
                String template = "Dear "+jwtTokenUtils.getFullNameFromToken()+" \n";
                template += "\n";
                template += "\n";
                template += "Thanks a lot for purchasing "+productsEntity.getProductName()+"! The details of your purchase are";
                template += "\n";
                template += "\n";
                template += "Please, don't delete this email";
                template += "\n";
                template += "\n";
                template += "Best regards,\n" +
                        "- Indicator Markets -";
                template += "\n";
                template += "\n";
                template += "Trading Solutions\n" +
                        "https://www.indicatormarkets.com/";
                template += "\n";
                template += "\n";
                template += "the file as attached";
                String subject = "Indicator Markets Team";
                Boolean resultSendMail = mailUtils.sendMultiFileToMail(template, jwtTokenUtils.getEmailFromToken(), subject, pathFilesProducts);
                if (!resultSendMail) {
                    throw new CustomException("cannot send email", CommonUtils.putError("email", "ERR_0013"));
                }
                HistoryPaymentDTO historyPaymentDTO = historyPaymentService.save(productsEntity, userEntity, PaymentType.VISA, session.getAmountTotal().toString());
                response.put("status_history", historyPaymentDTO.getStatus() == 1 ? "success_send" : "error_send");
            } else {
                if (productsEntity.getId() == null) {
                    throw new CustomException("cannot find product", CommonUtils.putError("productId", "ERR_0034"));
                }
                if (userEntity.getId() == null) {
                    throw new CustomException("cannot find customer", CommonUtils.putError("customerId", "ERR_0034"));
                }
            }
        } else {
            throw new CustomException("unpaid payment stripe", CommonUtils.putError("idStripe", "EER_0025"));
        }
        return response;
    }

}
