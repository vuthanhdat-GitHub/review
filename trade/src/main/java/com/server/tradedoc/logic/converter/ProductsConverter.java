package com.server.tradedoc.logic.converter;

import com.server.tradedoc.logic.dto.ProductsDTO;
import com.server.tradedoc.logic.entity.ProductsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * ProductsConverter
 *
 * @author DatDV
 */
@Component
public class ProductsConverter extends MapperManager<ProductsDTO, ProductsEntity> {

    @Autowired
    private ModelMapper modelMapper;

    public ProductsDTO customConvertToDto(ProductsEntity productsEntity) {
        ProductsDTO result = modelMapper.map(productsEntity , ProductsDTO.class);
        result.setTypes(Arrays.asList(productsEntity.getType().split(",")));
        return result;
    }
}
