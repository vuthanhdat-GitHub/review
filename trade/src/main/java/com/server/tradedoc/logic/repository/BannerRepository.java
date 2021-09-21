package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BannerRepository
 *
 * @author DatDV
 */
@Repository
public interface BannerRepository extends JpaRepository<BannerEntity , Long> {
    List<BannerEntity> findByStatus(int status);
}
