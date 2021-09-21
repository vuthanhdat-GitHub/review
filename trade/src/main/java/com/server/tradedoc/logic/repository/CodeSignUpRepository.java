package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.CodeSignUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CodeSignUpRepository
 *
 * @author DatDV
 */
@Repository
public interface CodeSignUpRepository extends JpaRepository<CodeSignUpEntity , Long> {
    CodeSignUpEntity findByCodeAndEmail(String code , String email);
    void deleteByCodeAndEmail(String code , String email);
}
