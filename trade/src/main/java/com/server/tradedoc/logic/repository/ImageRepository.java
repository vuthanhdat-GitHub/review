package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.ImageEntity;
import com.server.tradedoc.logic.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ImageRepository
 *
 * @author DatDV
 */
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity , Long> {
    void deleteImageEntityByProducts(ProductsEntity products);
}
