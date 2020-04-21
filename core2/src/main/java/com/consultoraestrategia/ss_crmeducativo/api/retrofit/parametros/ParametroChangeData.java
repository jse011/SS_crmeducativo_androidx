package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SCIEV on 26/07/2018.
 */

public class ParametroChangeData extends ApiRetrofit.Parameters {


    int sesionEventoId;

    String rubroEvaluacionId;
    @SerializedName("GrupoEquipoId")
    String grupoEquipoId;
    @SerializedName("variablesImport")
    BEVariables beVariables;
    @SerializedName("vint_UsuarioId")
    String usuario;

    public ParametroChangeData() {
    }

    public int getSesionEventoId() {
        return sesionEventoId;
    }

    public void setSesionEventoId(int sesionEventoId) {
        this.sesionEventoId = sesionEventoId;
    }

    public String getRubroEvaluacionId() {
        return rubroEvaluacionId;
    }

    public void setRubroEvaluacionId(String rubroEvaluacionId) {
        this.rubroEvaluacionId = rubroEvaluacionId;
    }

    public String getGrupoEquipoId() {
        return grupoEquipoId;
    }

    public void setGrupoEquipoId(String grupoEquipoId) {
        this.grupoEquipoId = grupoEquipoId;
    }

    public BEVariables getBeVariables() {
        return beVariables;
    }

    public void setBeVariables(BEVariables beVariables) {
        this.beVariables = beVariables;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
