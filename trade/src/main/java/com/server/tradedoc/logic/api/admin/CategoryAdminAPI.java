package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.builder.SearchHistoryPaymentBuilder;
import com.server.tradedoc.logic.dto.CategoryDTO;
import com.server.tradedoc.logic.dto.reponse.CountResponse;
import com.server.tradedoc.logic.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * CategoryAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/admin")
public class CategoryAdminAPI {

    @Autowired
    private CategoryService categoryService;

    /**
     * createCategory
     *
     * @param categoryDTO
     * @return
     */
    @RequestMapping(value = "/create-or-update-category" , method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.createOrUpdateCategory(categoryDTO));
    }

    /**
     * deleteCategory
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete-category", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@RequestParam("ids") List<Long> ids){
        return ResponseEntity.ok(categoryService.deleteCategory(ids));
    }

    /**
     * countCategory
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/count" , method = RequestMethod.GET)
    public ResponseEntity<CountResponse> countCategory(@RequestParam Map<String , String> model){
        SearchCategoryBuilder searchCategoryBuilder = initSearchCategoryBuilder(model);
        return ResponseEntity.ok(categoryService.count(searchCategoryBuilder));
    }

    /**
     * showCategory
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/show-all-category" , method = RequestMethod.GET)
    public ResponseEntity<?> showCategory(@RequestParam Map<String , String> model){
        SearchCategoryBuilder builder = initSearchCategoryBuilder(model);
        Pageable pageable = PageRequest.of(Integer.parseInt(model.get("page")) - 1 , Integer.parseInt(model.get("maxPageItem")));
        return ResponseEntity.ok(categoryService.showAllCategory(builder , pageable));
    }

    /**
     * findOneCategory
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/find-one-category" , method = RequestMethod.GET)
    public ResponseEntity<?> findOneCategory(@RequestParam("id") Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    /**
     * initSearchCategoryBuilder
     *
     * @param model
     * @return
     */
    private SearchCategoryBuilder initSearchCategoryBuilder (Map<String , String> model) {
        SearchCategoryBuilder builder = new SearchCategoryBuilder.builder()
                .setName(model.get("categoryName"))
                .builder();
        return builder;
    }
}
/*.builder()
                .setEmailCustomer(model.get("emailCustomer"))
                .setProductName(model.get("productName"))
                .setPaymentDateFrom(model.get("paymentDateFrom"))
                .setPaymentDateTo(model.get("paymentDateTo"))
                .setCustomerName(model.get("customerName"))
                .setPhoneNumber(model.get("phoneNumber"))
                .setPriceTo(model.get("priceTo"))
                .setPriceForm(model.get("priceForm"))
                .builder();*/
