package com.consultoraestrategia.ss_crmeducativo.test.ui;

import java.util.List;

/**
 * Created by @stevecampos on 5/01/2018.
 */

public class EntitySLResponse extends EntityResponse {
    public List<EntityResponse> responseList;

    public EntitySLResponse() {
    }

    public List<EntityResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<EntityResponse> responseList) {
        this.responseList = responseList;
    }
}
