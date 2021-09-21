package com.server.tradedoc.logic.repository.custom;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.entity.CategoryEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * CategoryRepositoryCustom
 *
 * @author DatDV
 */
public interface CategoryRepositoryCustom {
    List<CategoryEntity> findCategoryUseByIdIn(List<Long> ids);
    List<CategoryEntity> findAllCategory(SearchCategoryBuilder builder, Pageable pageable);
    Long countCategory(SearchCategoryBuilder builder);
}
