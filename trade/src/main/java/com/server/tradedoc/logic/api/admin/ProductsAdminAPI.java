package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.builder.SearchProductBuilder;
import com.server.tradedoc.logic.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * ProductsAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/admin")
public class ProductsAdminAPI {

    @Autowired
    private ProductsService productsService;

    /**
     * createProducts
     *
     * @param fileMt5
     * @param fileMt4
     * @param avatar
     * @param data
     * @return
     */
    @RequestMapping(value = "/create-products", method = RequestMethod.POST)
    public ResponseEntity<?> createProducts(@RequestPart(name = "fileProductMt5" , required = false) List<MultipartFile> fileMt5,
                                            @RequestPart(name = "fileProductMt4", required = false) List<MultipartFile> fileMt4,
                                            @RequestPart(name = "avatar" , required = false) MultipartFile avatar,
                                            @RequestParam String data) {
        return ResponseEntity.ok(productsService.createProduct(fileMt5, fileMt4, data, avatar));
    }

    /**
     * updateProducts
     *
     * @param fileMt5
     * @param fileMt4
     * @param avatar
     * @param data
     * @return
     */
    @RequestMapping(value = "/update-products", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProducts(@RequestPart(name = "fileProductMt5" , required = false) List<MultipartFile> fileMt5,
                                            @RequestPart(name = "fileProductMt4" , required = false) List<MultipartFile> fileMt4,
                                            @RequestPart(name = "avatar" , required = false) MultipartFile avatar,
                                            @RequestParam String data) {
        return ResponseEntity.ok(productsService.updateProduct(fileMt5,fileMt4,data,avatar));
    }

    /**
     * deleteProducts
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete-products", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProducts(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(productsService.deleteProduct(ids));
    }

    /**
     * findAllProductByCondition
     *
     * @param model
     * @return
     * @throws URISyntaxException
     */
    @RequestMapping(value = "/find-all-product-by-condition", method = RequestMethod.GET)
    public ResponseEntity<?> findAllProductByCondition(@RequestParam Map<String, String> model) throws URISyntaxException {
        SearchProductBuilder builder = initSearchProductBuilder(model);
        Pageable pageable = PageRequest.of(Integer.parseInt(model.get("page")) - 1, Integer.parseInt(model.get("maxPageItem")));
        return ResponseEntity.ok(productsService.getProductByCondition(builder, pageable));
    }

    /**
     * countProductByCondition
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/count-product-by-condition", method = RequestMethod.GET)
    public ResponseEntity<?> countProductByCondition(@RequestParam Map<String, String> model) {
        SearchProductBuilder builder = initSearchProductBuilder(model);
        return ResponseEntity.ok(productsService.count(builder));
    }

    /**
     * findOneProduct
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find-one-product", method = RequestMethod.GET)
    public ResponseEntity<?> findOneProduct(@RequestParam("id") Long id) {
        return ResponseEntity.ok(productsService.getById(id , "admin"));
    }

    /**
     * getProductTypes
     *
     * @return
     */
    @RequestMapping(value = "/get-product-types", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getProductTypes() {
        return ResponseEntity.ok(productsService.getProductTypes());
    }

    /**
     * getProductCollection
     *
     * @return
     */
    @RequestMapping(value = "/get-product-collection" , method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getProductCollection() {
        return ResponseEntity.ok(productsService.getProductCollection());
    }

    private SearchProductBuilder initSearchProductBuilder(Map<String, String> model) {
        SearchProductBuilder builder = new SearchProductBuilder.builder()
                .setCategoryId(model.get("categoryId") != "" ? Long.parseLong(model.get("categoryId")) : null)
                .setPriceForm(model.get("priceForm") != "" ? Integer.parseInt(model.get("priceForm")) : null)
                .setPriceTo(model.get("priceTo") != "" ? Integer.parseInt(model.get("priceTo")) : null)
                .setProductName(model.get("productName"))
                .setProductType(model.get("productType"))
                .setCollection(model.get("collection"))
                .builder();
        return builder;
    }
}
