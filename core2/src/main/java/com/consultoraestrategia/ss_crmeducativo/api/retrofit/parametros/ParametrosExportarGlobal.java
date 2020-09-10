package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.retrofit.BERubroEvalEnvioSimple;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParametrosExportarGlobal extends ApiRetrofit.Parameters {
    @SerializedName("vobj_InsertarListEntidades")
    BEGuardarEntidadesGlobal beGuardarEntidadesGlobal;
    @SerializedName("vlst_BERubroEvalEnvioSimple")
    List<BERubroEvalEnvioSimple> beRubroEvalEnvioSimpleList;
    @SerializedName("list_personas")
    List<Persona> personaList;

    public ParametrosExportarGlobal() {
    }

    public BEGuardarEntidadesGlobal getBeGuardarEntidadesGlobal() {
        return beGuardarEntidadesGlobal;
    }

    public void setBeGuardarEntidadesGlobal(BEGuardarEntidadesGlobal beGuardarEntidadesGlobal) {
        this.beGuardarEntidadesGlobal = beGuardarEntidadesGlobal;
    }

    public List<BERubroEvalEnvioSimple> getBeRubroEvalEnvioSimpleList() {
        return beRubroEvalEnvioSimpleList;
    }

    public void setBeRubroEvalEnvioSimpleList(List<BERubroEvalEnvioSimple> beRubroEvalEnvioSimpleList) {
        this.beRubroEvalEnvioSimpleList = beRubroEvalEnvioSimpleList;
    }

    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public String toString() {
        return "ParametrosExportarGlobal{" +
                "beGuardarEntidadesGlobal=" + beGuardarEntidadesGlobal +
                '}';
    }
}
