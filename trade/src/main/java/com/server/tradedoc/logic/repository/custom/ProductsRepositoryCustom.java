package com.server.tradedoc.logic.repository.custom;

import com.server.tradedoc.logic.builder.SearchProductBuilder;
import com.server.tradedoc.logic.dto.reponse.ProductsSearchDTO;
import com.server.tradedoc.logic.entity.ProductsEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductsRepositoryCustom {
    List<ProductsSearchDTO> findAllProductByCondition(SearchProductBuilder builder , Pageable pageable);
    Long countProductByCondition(SearchProductBuilder builder);
    List<ProductsEntity> findAllProductByCategoryIds(List<Long> categoryIds , String collection);
}
