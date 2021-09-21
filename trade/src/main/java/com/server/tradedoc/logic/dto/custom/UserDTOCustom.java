package com.server.tradedoc.logic.dto.custom;

import com.server.tradedoc.logic.dto.AbstractDTO;

public class UserDTOCustom extends AbstractDTO {
    private String fullName;
    private String numberPhone;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
