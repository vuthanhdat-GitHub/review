package com.server.tradedoc.logic.dto.custom;

import com.server.tradedoc.logic.dto.AbstractDTO;

public class CategoryDTOCustom extends AbstractDTO {

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
