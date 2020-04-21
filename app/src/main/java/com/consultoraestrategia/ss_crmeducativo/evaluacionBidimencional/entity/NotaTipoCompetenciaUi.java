package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;

/**
 * Created by @stevecampos on 23/02/2018.
 */

public class NotaTipoCompetenciaUi extends NotaUi {
    private TipoCompenciaEnum tipoCompenciaEnum = TipoCompenciaEnum.COMPETENCIA_BASE;
    private TipoIndicadorUi tipoIndicadorUi = TipoIndicadorUi.DEFAULT;
    private String url;

    public TipoCompenciaEnum getTipoCompenciaEnum() {
        return tipoCompenciaEnum;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTipoCompenciaEnum(TipoCompenciaEnum tipoCompenciaEnum) {
        this.tipoCompenciaEnum = tipoCompenciaEnum;
    }

    public TipoIndicadorUi getTipoIndicadorUi() {
        return tipoIndicadorUi;
    }

    public void setTipoIndicadorUi(TipoIndicadorUi tipoIndicadorUi) {
        this.tipoIndicadorUi = tipoIndicadorUi;
    }
}
