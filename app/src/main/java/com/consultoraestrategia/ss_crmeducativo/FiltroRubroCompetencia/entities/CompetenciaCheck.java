package com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities;

public class CompetenciaCheck {

    private TipoCompetencia tipoCompetencia;
    private Boolean checked;

    public TipoCompetencia getTipoCompetencia() {
        return tipoCompetencia;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setTipoCompetencia(TipoCompetencia tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public CompetenciaCheck(TipoCompetencia tipoCompetencia, Boolean checked) {
        this.tipoCompetencia = tipoCompetencia;
        this.checked = checked;
    }
}
