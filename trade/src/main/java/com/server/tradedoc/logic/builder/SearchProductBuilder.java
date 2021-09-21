package com.server.tradedoc.logic.builder;

/**
 * SearchProductBuilder
 *
 * @author DatDV
 */
public class SearchProductBuilder {

    private Long categoryId;
    private String productName;
    private Integer priceTo;
    private Integer priceForm;
    private String productType;
    private String collection;

    public Long getCategoryId() {
        return categoryId;
    }
    public String getProductName() {
        return productName;
    }
    public Integer getPriceTo() {
        return priceTo;
    }
    public Integer getPriceForm() {
        return priceForm;
    }
    public String getProductType() {
        return productType;
    }
    public String getCollection() {
        return collection;
    }

    public SearchProductBuilder(builder builder){
        this.categoryId = builder.categoryId;
        this.productName = builder.productName;
        this.priceTo = builder.priceTo;
        this.priceForm = builder.priceForm;
        this.productType = builder.productType;
        this.collection = builder.collection;
    }

    public static class builder{
        private Long categoryId;
        private String productName;
        private Integer priceTo;
        private Integer priceForm;
        private String productType;
        private String collection;

        public builder setCategoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }
        public builder setPriceTo(Integer priceTo) {
            this.priceTo = priceTo;
            return this;
        }
        public builder setPriceForm(Integer priceForm) {
            this.priceForm = priceForm;
            return this;
        }
        public builder setProductType(String productType) {
            this.productType = productType;
            return this;
        }
        public builder setCollection(String collection) {
            this.collection = collection;
            return this;
        }

        public SearchProductBuilder builder(){ return new SearchProductBuilder(this); }
    }
}
