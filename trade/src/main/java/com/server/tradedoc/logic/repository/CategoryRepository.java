package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.CategoryEntity;
import com.server.tradedoc.logic.repository.custom.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * CategoryRepository
 *
 * @author DatDV
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity , Long> , CategoryRepositoryCustom {
    List<CategoryEntity> findCategoryEntitiesByIdIn(List<Long> ids);
}
