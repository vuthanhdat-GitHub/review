package com.server.tradedoc.logic.converter;

import com.server.tradedoc.logic.dto.CategoryDTO;
import com.server.tradedoc.logic.entity.CategoryEntity;
import org.springframework.stereotype.Component;

/**
 * CategoryConverter
 *
 * @author DatDV
 */
@Component
public class CategoryConverter extends MapperManager<CategoryDTO , CategoryEntity> {
}
