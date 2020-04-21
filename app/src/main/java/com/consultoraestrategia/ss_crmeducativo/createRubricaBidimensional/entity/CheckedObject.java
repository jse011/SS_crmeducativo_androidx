package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import java.io.Serializable;

/**
 * Created by @stevecampos on 8/02/2018.
 */

public class CheckedObject implements Serializable {
    protected boolean checked;
    protected String title;

    public CheckedObject() {
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
