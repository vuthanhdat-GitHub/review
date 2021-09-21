package com.server.tradedoc.logic.repository;

import com.server.tradedoc.logic.entity.BlogsEntity;
import com.server.tradedoc.logic.entity.FilesBlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FilesBlogRepository
 *
 * @author DatDV
 */
@Repository
public interface FilesBlogRepository extends JpaRepository<FilesBlogEntity , Long> {
    void deleteByBlogs(BlogsEntity blogs);
}
