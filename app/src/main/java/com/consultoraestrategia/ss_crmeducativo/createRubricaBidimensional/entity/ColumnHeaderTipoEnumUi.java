package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.ColumnHeader;

/**
 * Created by SCIEV on 5/04/2018.
 */

public class ColumnHeaderTipoEnumUi extends ColumnHeader {
    public enum  TipoEnumUi {SELECTOR_VALORES, SELECTOR_IMGENES}
    private TipoEnumUi tipoEnumUi = TipoEnumUi.SELECTOR_VALORES;

    public ColumnHeaderTipoEnumUi() {
        tipoEnumUi = TipoEnumUi.SELECTOR_VALORES;
    }

    public ColumnHeaderTipoEnumUi(String id, String title, String subtitle,String icono, int backgroundColor, int textColor) {
        super(id, title, subtitle, backgroundColor, textColor);
        this.icono = icono;
    }

    public TipoEnumUi getTipoEnumUi() {
        return tipoEnumUi;
    }

    public void setTipoEnumUi(TipoEnumUi tipoEnumUi) {
        this.tipoEnumUi = tipoEnumUi;
    }
    String icono;

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
}
