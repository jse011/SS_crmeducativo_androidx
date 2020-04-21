package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.google.gson.annotations.SerializedName;

public class ParametrosExportarGlobal extends ApiRetrofit.Parameters {
    @SerializedName("vobj_InsertarListEntidades")
    BEGuardarEntidadesGlobal beGuardarEntidadesGlobal;

    public ParametrosExportarGlobal() {
    }

    public BEGuardarEntidadesGlobal getBeGuardarEntidadesGlobal() {
        return beGuardarEntidadesGlobal;
    }

    public void setBeGuardarEntidadesGlobal(BEGuardarEntidadesGlobal beGuardarEntidadesGlobal) {
        this.beGuardarEntidadesGlobal = beGuardarEntidadesGlobal;
    }

    @Override
    public String toString() {
        return "ParametrosExportarGlobal{" +
                "beGuardarEntidadesGlobal=" + beGuardarEntidadesGlobal +
                '}';
    }
}
