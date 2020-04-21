package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.RelProgramaEducativoTipoNota;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;

import java.util.List;

public class BEDatosTipoNota {
    public List<TipoNotaC> tipoNota;
    public List<ValorTipoNotaC> valorTipoNota;
    public List<RelProgramaEducativoTipoNota> relProgramaEducativoTipoNota;
    public List<EscalaEvaluacion> escalaEvaluacion;
    public List<T_RN_MAE_TIPO_EVALUACION> tipoEvaluacion;
    public List<Tipos> tipos;
    private long fecha_servidor;

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

    public List<RelProgramaEducativoTipoNota> getRelProgramaEducativoTipoNota() {
        return relProgramaEducativoTipoNota;
    }

    public void setRelProgramaEducativoTipoNota(List<RelProgramaEducativoTipoNota> relProgramaEducativoTipoNota) {
        this.relProgramaEducativoTipoNota = relProgramaEducativoTipoNota;
    }

    public List<EscalaEvaluacion> getEscalaEvaluacion() {
        return escalaEvaluacion;
    }

    public void setEscalaEvaluacion(List<EscalaEvaluacion> escalaEvaluacion) {
        this.escalaEvaluacion = escalaEvaluacion;
    }

    public List<T_RN_MAE_TIPO_EVALUACION> getTipoEvaluacion() {
        return tipoEvaluacion;
    }

    public void setTipoEvaluacion(List<T_RN_MAE_TIPO_EVALUACION> tipoEvaluacion) {
        this.tipoEvaluacion = tipoEvaluacion;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }
}
