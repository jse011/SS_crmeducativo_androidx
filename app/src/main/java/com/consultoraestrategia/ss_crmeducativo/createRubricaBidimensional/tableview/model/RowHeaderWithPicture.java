package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model;

/**
 * Created by @stevecampos on 28/02/2018.
 */

public class RowHeaderWithPicture extends RowHeader {
    private String url;

    public RowHeaderWithPicture() {
    }

    public RowHeaderWithPicture(String id, String title, String boldText, String url) {
        super(id, title, boldText);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
