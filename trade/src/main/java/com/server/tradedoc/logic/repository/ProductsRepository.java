package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.ProductsEntity;
import com.server.tradedoc.logic.repository.custom.ProductsRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProductsRepository
 *
 * @author DatDV
 */
public interface ProductsRepository extends JpaRepository<ProductsEntity , Long>, ProductsRepositoryCustom {

}
