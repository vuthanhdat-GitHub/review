package com.server.tradedoc.logic.converter;

import com.server.tradedoc.logic.dto.BlogsDTO;
import com.server.tradedoc.logic.entity.BlogsEntity;
import org.springframework.stereotype.Component;

@Component
public class BlogsConverter extends MapperManager<BlogsDTO , BlogsEntity> {
}
