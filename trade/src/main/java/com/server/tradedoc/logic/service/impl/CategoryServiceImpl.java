package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.logic.builder.SearchCategoryBuilder;
import com.server.tradedoc.logic.converter.CategoryConverter;
import com.server.tradedoc.logic.dto.CategoryDTO;
import com.server.tradedoc.logic.dto.reponse.CountResponse;
import com.server.tradedoc.logic.dto.reponse.DeleteResponse;
import com.server.tradedoc.logic.dto.reponse.ShowAllCategoryResponse;
import com.server.tradedoc.logic.entity.CategoryEntity;
import com.server.tradedoc.logic.repository.CategoryRepository;
import com.server.tradedoc.logic.service.CategoryService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CategoryServiceImpl
 *
 * @author DatDV
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    @Transactional
    public CategoryDTO createOrUpdateCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getName().equals("") || categoryDTO.getName() == null) {
            throw new CustomException("category name not null", CommonUtils.putError("categoryDTO", "ERR_0034"));
        }
        categoryDTO.setCode(categoryDTO.getName().toUpperCase().trim().replace(" ", "_"));
        return categoryConverter.toDto(categoryRepository.save(categoryConverter.toEntity(categoryDTO)));
    }

    @Override
    @Transactional
    public DeleteResponse deleteCategory(List<Long> ids) {
        DeleteResponse response = new DeleteResponse();
        List<CategoryEntity> categoryEntities = categoryRepository.findCategoryUseByIdIn(ids);
        if (!categoryEntities.isEmpty()) {
            throw new CustomException("Catelogy is being used", CommonUtils.putError("ids", "ERR_0030"));
        }
        List<CategoryEntity> categoryEntityList = categoryRepository.findCategoryEntitiesByIdIn(ids);
        if (categoryEntityList.isEmpty()) {
            throw new CustomException("category not find", CommonUtils.putError("ids", "ERR_0030"));
        }
        List<Long> idByDelete = categoryEntityList.stream().map(CategoryEntity::getId).collect(Collectors.toList());
        for (Long id : idByDelete) {
            categoryRepository.deleteById(id);
        }
        response.setIdsDeleted(idByDelete);
        return response;
    }

    @Override
    public ShowAllCategoryResponse showAllCategory(SearchCategoryBuilder builder, Pageable pageable) {
        ShowAllCategoryResponse response  = new ShowAllCategoryResponse();
        List<CategoryEntity> categoryEntities = categoryRepository.findAllCategory(builder, pageable);
        response.setCategorys(categoryConverter.toListDto(categoryEntities));
        response.setCountItem(categoryRepository.countCategory(builder));
        return response;
    }

    @Override
    public List<CategoryDTO> showAll() {
        List<CategoryDTO> resultDb = categoryConverter.toListDto(categoryRepository.findAll());
        List<CategoryDTO> result = new ArrayList<>();
        for (CategoryDTO categoryDTO : resultDb) {
            categoryDTO.setProducts(new ArrayList<>());
            result.add(categoryDTO);
        }
        return result;
    }

    @Override
    public CategoryDTO findById(Long id) {
        return categoryConverter.toDto(categoryRepository.findById(id).get());
    }

    @Override
    public CountResponse count(SearchCategoryBuilder builder) {
        CountResponse result = new CountResponse();
        result.setCountItem(categoryRepository.countCategory(builder));
        return result;
    }

}
