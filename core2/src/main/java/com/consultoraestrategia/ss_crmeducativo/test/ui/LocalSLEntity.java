package com.consultoraestrategia.ss_crmeducativo.test.ui;

import com.consultoraestrategia.ss_crmeducativo.entities.LocalEntity;

import java.util.List;

/**
 * Created by @stevecampos on 5/01/2018.
 */

public class LocalSLEntity<T extends LocalEntity> extends LocalEntity {

    public List<T> secondLevelList;

    public LocalSLEntity() {
    }

    public List<T> getSecondLevelList() {
        return secondLevelList;
    }

    public void setSecondLevelList(List<T> secondLevelList) {
        this.secondLevelList = secondLevelList;
    }
}
