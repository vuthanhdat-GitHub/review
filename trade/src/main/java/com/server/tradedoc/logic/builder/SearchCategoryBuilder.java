package com.server.tradedoc.logic.builder;

/**
 * SearchCategoryBuilder
 *
 * @author DatDV
 */
public class SearchCategoryBuilder {
    private String name;

    public String getName() {
        return name;
    }

    public SearchCategoryBuilder(builder builder) {
        this.name = builder.name;
    }

    public static class builder {
        private String name;

        public builder setName(String name) {
            this.name = name;
            return this;
        }

        public SearchCategoryBuilder builder() { return new SearchCategoryBuilder(this); }
    }
}
