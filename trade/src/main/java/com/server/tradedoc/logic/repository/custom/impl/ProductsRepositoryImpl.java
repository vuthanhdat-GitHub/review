package com.server.tradedoc.logic.repository.custom.impl;

import com.server.tradedoc.logic.builder.SearchProductBuilder;
import com.server.tradedoc.logic.dto.reponse.ProductsSearchDTO;
import com.server.tradedoc.logic.entity.ProductsEntity;
import com.server.tradedoc.logic.repository.custom.ProductsRepositoryCustom;
import com.server.tradedoc.utils.BuildMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProductsRepositoryImpl
 *
 * @author DatDV
 */
@Repository
public class ProductsRepositoryImpl extends RepositoryCustomUtils<ProductsEntity> implements ProductsRepositoryCustom {

    @Autowired
    private BuildMapUtils buildMapUtils;

    /**
     * findAllProductByCondition
     *
     * @param builder
     * @param pageable
     * @return List<ProductsSearchDTO>
     */
    @Override
    public List<ProductsSearchDTO> findAllProductByCondition(SearchProductBuilder builder, Pageable pageable) {
        Map<String , Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("        pro.id AS product_id,");
        sql.append("        pro.productname AS product_name,");
        sql.append("        (SELECT ");
        sql.append("            array_to_string( ARRAY_AGG ( C.NAME ), ',' ) ");
        sql.append("         FROM");
        sql.append("            product_category pc ");
        sql.append("            INNER JOIN category C ON pc.categoryid = C.ID ");
        sql.append("         WHERE 1=1 AND pc.productid = pro.ID ) AS category_name, ");
        sql.append("        (SELECT ");
        sql.append("            array_to_string( ARRAY_AGG ( fp.pathfile ), ',' ) ");
        sql.append("         FROM");
        sql.append("            files_product fp ");
        sql.append("            INNER JOIN products p ON p.id = fp.productid ");
        sql.append("         WHERE 1 = 1 AND fp.producttype = 'MT4' AND fp.productid = pro.id ) AS mt4_file, ");
        sql.append("        (SELECT ");
        sql.append("            array_to_string( ARRAY_AGG ( fp.pathfile ), ',' ) ");
        sql.append("         FROM");
        sql.append("            files_product fp ");
        sql.append("            INNER JOIN products p ON p.id = fp.productid ");
        sql.append("         WHERE 1 = 1 AND fp.producttype = 'MT5' AND fp.productid = pro.id ) AS mt5_file, ");
        sql.append("        pro.price AS price,");
        sql.append("        pro.pathfile AS path_file,");
        sql.append("        pro.description AS description,");
        sql.append("        pro.type AS type,");
        sql.append("        pro.avatar AS avatar,");
        sql.append("        pro.title AS title,");
        sql.append("        pro.collection,");
        sql.append("        pro.createdby AS created_by,");
        sql.append("        pro.modifiedby AS modified_by,");
        sql.append("        pro.createddate AS created_date,");
        sql.append("        pro.modifieddate AS modified_date ");
        sql.append("FROM products pro ");
        sql.append("    INNER JOIN product_category pc  ON pro.id = pc.productid ");
        sql.append("    INNER JOIN category ca ON pc.categoryid = ca.id ");
        sql.append("WHERE 1=1 ");
        if (!builder.getProductName().equals("")) {
            sql.append("AND pro.productname LIKE :productname ");
        }
        if (builder.getCategoryId() != null) {
            sql.append("AND ca.id = :categoryid ");
        }
        if (!builder.getProductType().equals("")) {
            sql.append("AND pro.type LIKE :producttype ");
        }
        if (builder.getPriceForm() != null) {
            sql.append("AND pro.price >= :priceform ");
        }
        if (builder.getPriceTo() != null) {
            sql.append("AND pro.price <= :priceto ");
        }
        if (!builder.getCollection().equals("")) {
            sql.append("AND pro.collection LIKE :collection ");
        }
        sql.append("GROUP BY pro.id ");
        sql.append("ORDER BY pro.ID DESC ");
        return this.getResultList(sql.toString() , parameter , "findAllProductsByCondition" , pageable);
    }

    /**
     * countProductByCondition
     *
     * @param builder: SearchProductBuilder where clause using get count
     * @return Long : total product in database using where clause
     */
    @Override
    public Long countProductByCondition(SearchProductBuilder builder) {
        Map<String , Object> parameter = buildMapUtils.buildMapSearch(builder);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM ");
        sql.append("(SELECT ");
        sql.append("        pro.id AS product_id,");
        sql.append("        pro.productname AS product_name,");
        sql.append("        (SELECT ");
        sql.append("            array_to_string( ARRAY_AGG ( C.NAME ), ',' ) ");
        sql.append("         FROM");
        sql.append("            product_category pc ");
        sql.append("            INNER JOIN category C ON pc.categoryid = C.ID ");
        sql.append("         WHERE 1=1 AND pc.productid = pro.ID ) AS category_name, ");
        sql.append("        (SELECT ");
        sql.append("            array_to_string( ARRAY_AGG ( fp.pathfile ), ',' ) ");
        sql.append("         FROM");
        sql.append("            files_product fp ");
        sql.append("            INNER JOIN products p ON p.id = fp.productid ");
        sql.append("         WHERE 1 = 1 AND fp.producttype = 'MT4' AND fp.productid = pro.id ) AS mt4_file, ");
        sql.append("        (SELECT ");
        sql.append("            array_to_string( ARRAY_AGG ( fp.pathfile ), ',' ) ");
        sql.append("         FROM");
        sql.append("            files_product fp ");
        sql.append("            INNER JOIN products p ON p.id = fp.productid ");
        sql.append("         WHERE 1 = 1 AND fp.producttype = 'MT5' AND fp.productid = pro.id ) AS mt5_file, ");
        sql.append("        pro.price AS price,");
        sql.append("        pro.pathfile AS path_file,");
        sql.append("        pro.description AS description,");
        sql.append("        pro.type AS type,");
        sql.append("        pro.avatar AS avatar,");
        sql.append("        pro.title AS title,");
        sql.append("        pro.createdby AS created_by,");
        sql.append("        pro.modifiedby AS modified_by,");
        sql.append("        pro.createddate AS created_date,");
        sql.append("        pro.modifieddate AS modified_date ");
        sql.append("FROM products pro ");
        sql.append("    INNER JOIN product_category pc  ON pro.id = pc.productid ");
        sql.append("    INNER JOIN category ca ON pc.categoryid = ca.id ");
        sql.append("WHERE 1=1 ");
        if (!builder.getProductName().equals("")) {
            sql.append("AND pro.productname LIKE :productname ");
        }
        if (builder.getCategoryId() != null) {
            sql.append("AND ca.id = :categoryid ");
        }
        if (!builder.getProductType().equals("")) {
            sql.append("AND pro.type LIKE :producttype ");
        }
        if (builder.getPriceForm() != null) {
            sql.append("AND pro.price >= :priceform ");
        }
        if (builder.getPriceTo() != null) {
            sql.append("AND pro.price <= :priceto ");
        }
        if (!builder.getCollection().equals("")) {
            sql.append("AND pro.collection = :collection ");
        }
        sql.append("GROUP BY pro.id ");
        sql.append("ORDER BY pro.ID DESC ) t ");
        Long count = Long.parseLong(this.getSingleResult(sql.toString() , parameter).toString());
        return count;
    }

    /**
     * findAllProductByCategoryIds : filter product in client using categoryIds
     *
     * @param categoryIds: categoryId using filter
     * @return List<ProductsEntity>
     */
    @Override
    public List<ProductsEntity> findAllProductByCategoryIds(List<Long> categoryIds , String collection) {
        Map<String , Object> parameter = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("        p.productname, ");
        sql.append("        p.price, ");
        sql.append("        p.description, ");
        sql.append("        p.inputparam, ");
        sql.append("        p.pathfile, ");
        sql.append("        p.title, ");
        sql.append("        p.avatar, ");
        sql.append("        p.type, ");
        sql.append("        p.id, ");
        sql.append("        p.collection, ");
        sql.append("        p.createddate, ");
        sql.append("        p.modifieddate, ");
        sql.append("        p.createdby, ");
        sql.append("        p.modifiedby ");
        sql.append("FROM products p ");
        sql.append("    INNER JOIN product_category pc ON p.id = pc.productid ");
        sql.append("    INNER JOIN category c ON c.id = pc.categoryid ");
        sql.append("WHERE 1=1 ");
        if (!collection.equals("")) {
            sql.append("AND p.collection = :collection ");
            parameter.put("collection" , collection);
        }
        if (!categoryIds.isEmpty()){
            sql.append("AND pc.categoryid IN (:categoryIds) ");
            parameter.put("categoryIds" , categoryIds);
        }
        sql.append("GROUP BY p.id ");
        return this.getResultList(sql.toString() , parameter , ProductsEntity.class);
    }

}
