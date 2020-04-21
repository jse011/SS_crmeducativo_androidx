package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SCIEV on 26/07/2018.
 */

public class ParametroNotificacion extends ApiRetrofit.Parameters {
    @SerializedName("vobj_notificacion")
    private Object notificacion;

    public Object getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Object notificacion) {
        this.notificacion = notificacion;
    }
}
