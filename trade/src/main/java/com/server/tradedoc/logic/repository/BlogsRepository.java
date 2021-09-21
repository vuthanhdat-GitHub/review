package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.BlogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * BlogsRepository
 *
 * @author DatDV
 */
@Repository
public interface BlogsRepository extends JpaRepository<BlogsEntity , Long> {
}
