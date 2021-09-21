package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.FilesProductEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FilesProductRepository
 *
 * @author DatDV
 */
@Repository
public interface FilesProductRepository extends JpaRepository<FilesProductEntity , Long> {
    List<FilesProductEntity> findByProductsAndProductType(ProductsEntity products , String productType);
    void deleteByProductsAndProductType(ProductsEntity products , String productType);
    void deleteAllByProducts(ProductsEntity products);
}
