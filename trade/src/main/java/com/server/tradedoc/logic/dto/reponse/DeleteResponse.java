package com.server.tradedoc.logic.dto.reponse;

import java.util.List;

public class DeleteResponse {
    private List<Long> idsDeleted;

    public List<Long> getIdsDeleted() {
        return idsDeleted;
    }

    public void setIdsDeleted(List<Long> idsDeleted) {
        this.idsDeleted = idsDeleted;
    }
}
