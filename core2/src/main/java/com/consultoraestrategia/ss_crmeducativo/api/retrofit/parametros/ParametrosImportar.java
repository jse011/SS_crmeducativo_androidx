package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.google.gson.annotations.SerializedName;

public class ParametrosImportar<T extends BEVariables> extends ApiRetrofit.Parameters {
    @SerializedName("variablesImport")
    T objeto;

    public ParametrosImportar() {
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }
}
