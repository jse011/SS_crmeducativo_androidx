package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosEnvioTipoNota  extends BEDatosServidor {
    private List<TipoNotaC> tipoNota;
    private List<ValorTipoNotaC> valorTipoNota;
    public List<RelProgramaEducativoTipoNota> Rel_Programa_Educativo_TipoNota;
    private List<EscalaEvaluacion> escalaEvaluacion;
    private List<T_RN_MAE_TIPO_EVALUACION> rn_mae_tipo_evaluacion;

    public BEDatosEnvioTipoNota() {
    }


    public List<TipoNotaC> getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(List<TipoNotaC> tipoNota) {
        this.tipoNota = tipoNota;
    }

    public List<ValorTipoNotaC> getValorTipoNota() {
        return valorTipoNota;
    }

    public void setValorTipoNota(List<ValorTipoNotaC> valorTipoNota) {
        this.valorTipoNota = valorTipoNota;
    }

    public List<EscalaEvaluacion> getEscalaEvaluacion() {
        return escalaEvaluacion;
    }

    public void setEscalaEvaluacion(List<EscalaEvaluacion> escalaEvaluacion) {
        this.escalaEvaluacion = escalaEvaluacion;
    }

    public List<T_RN_MAE_TIPO_EVALUACION> getRn_mae_tipo_evaluacion() {
        return rn_mae_tipo_evaluacion;
    }

    public void setRn_mae_tipo_evaluacion(List<T_RN_MAE_TIPO_EVALUACION> rn_mae_tipo_evaluacion) {
        this.rn_mae_tipo_evaluacion = rn_mae_tipo_evaluacion;
    }

    public List<RelProgramaEducativoTipoNota> getRel_Programa_Educativo_TipoNota() {
        return Rel_Programa_Educativo_TipoNota;
    }

    public void setRel_Programa_Educativo_TipoNota(List<RelProgramaEducativoTipoNota> rel_Programa_Educativo_TipoNota) {
        Rel_Programa_Educativo_TipoNota = rel_Programa_Educativo_TipoNota;
    }

    @Override
    public String toString() {
        return "BEDatosEnvioTipoNota{" +
                "tipoNota=" + tipoNota +
                ", valorTipoNota=" + valorTipoNota +
                ", Rel_Programa_Educativo_TipoNota=" + Rel_Programa_Educativo_TipoNota +
                ", escalaEvaluacion=" + escalaEvaluacion +
                ", rn_mae_tipo_evaluacion=" + rn_mae_tipo_evaluacion +
                '}';
    }
}
