package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.AdsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<AdsEntity , Long> {
    List<AdsEntity> findByStatus(Integer status);
}
