package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.google.gson.annotations.SerializedName;

public class ParametrosRegistroEvaluacion extends ApiRetrofit.Parameters {
    @SerializedName("vint_SilaboEventoId")
    private int silaboEventoId;
    @SerializedName("vint_CargaCursoId")
    private int cargaCursoId;
    @SerializedName("vint_CalendarioPeriodoId")
    private int calendarioPeriodId;
    @SerializedName("vint_RubroFormal")
    private int rubroFormal;
    @SerializedName("vint_usuarioId")
    private int usuarioId;

    public ParametrosRegistroEvaluacion() {
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getCalendarioPeriodId() {
        return calendarioPeriodId;
    }

    public void setCalendarioPeriodId(int calendarioPeriodId) {
        this.calendarioPeriodId = calendarioPeriodId;
    }

    public int getRubroFormal() {
        return rubroFormal;
    }

    public void setRubroFormal(int rubroFormal) {
        this.rubroFormal = rubroFormal;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
