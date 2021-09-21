package com.server.tradedoc.logic.repository.custom.impl;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.entity.CategoryEntity;
import com.server.tradedoc.logic.repository.custom.CategoryRepositoryCustom;
import com.server.tradedoc.utils.BuildMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CategoryRepositoryImpl
 *
 * @author DatDV
 */
@Repository
public class CategoryRepositoryImpl extends RepositoryCustomUtils<CategoryEntity> implements CategoryRepositoryCustom {

    @Autowired
    private BuildMapUtils buildMapUtils;

    /**
     * findCategoryUseByIdIn
     *
     * @param ids
     * @return
     */
    @Override
    public List<CategoryEntity> findCategoryUseByIdIn(List<Long> ids) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM category c INNER JOIN product_category pc ON c.id = pc.categoryid ");
        sql.append("WHERE 1=1 AND c.id IN (:idCategoryUse)");
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("idCategoryUse", ids);
        return this.getResultList(sql.toString(), parameter, CategoryEntity.class);
    }

    /**
     * findAllCategory
     *
     * @param builder
     * @param pageable
     * @return
     */
    @Override
    public List<CategoryEntity> findAllCategory(SearchCategoryBuilder builder, Pageable pageable) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    A.id AS category_id, ");
        sql.append("    A.createdby AS created_by, ");
        sql.append("    A.createddate AS created_date, ");
        sql.append("    A.modifiedby AS modified_by, ");
        sql.append("    A.modifieddate AS modified_date, ");
        sql.append("    A.code AS category_code, ");
        sql.append("    A.name AS category_name ");
        sql.append("FROM category A ");
        sql.append("WHERE 1=1 ");
        if (!builder.getName().equals("")) {
            sql.append("   AND LOWER(A.name) LIKE :name ");
        }
        sql.append("ORDER BY A.id DESC ");
        return this.getResultList(sql.toString(), parameter, "findAllCategory", pageable);
    }

    /**
     * countCategory
     *
     * @param builder
     * @return
     */
    @Override
    public Long countCategory(SearchCategoryBuilder builder) {
        Map<String, Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("   COUNT(*) ");
        sql.append("FROM category A ");
        sql.append("WHERE 1=1 ");
        if (!builder.getName().equals("")) {
            sql.append("   AND LOWER(A.name) LIKE LOWER(:name) ");
        }
        Long count = Long.parseLong(this.getSingleResult(sql.toString() , parameter).toString());
        return count;
    }
}
