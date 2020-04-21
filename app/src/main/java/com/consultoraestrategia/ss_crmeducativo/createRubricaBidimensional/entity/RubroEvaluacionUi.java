package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.model.ColumnHeader;

import java.io.Serializable;

/**
 * Created by @stevecampos on 14/12/2017.
 */

public class RubroEvaluacionUi extends ColumnHeader implements Serializable {
    private String id;
    private String title;

    public RubroEvaluacionUi(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
