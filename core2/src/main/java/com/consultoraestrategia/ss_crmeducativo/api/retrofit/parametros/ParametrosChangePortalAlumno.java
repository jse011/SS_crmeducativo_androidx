package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParametrosChangePortalAlumno extends ApiRetrofit.Parameters {

    @SerializedName("usuarioId")
    private int usuarioId;
    @SerializedName("fechaCambio")
    private long fechaCambio;
    @SerializedName("vlst_RubroEvaluacionId")
    private List<String> rubroEvaluacionidList;
    @SerializedName("vercion")
    private int vercion;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(long fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public void setRubroEvaluacionidList(List<String> rubroEvaluacionidList) {
        this.rubroEvaluacionidList = rubroEvaluacionidList;
    }

    public List<String> getRubroEvaluacionidList() {
        return rubroEvaluacionidList;
    }

    public int getVercion() {
        return vercion;
    }

    public void setVercion(int vercion) {
        this.vercion = vercion;
    }
}
