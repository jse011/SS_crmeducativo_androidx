package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 23/05/2018.
 */

public class GEDatosRubroEvaluacionProceso extends BEDatosServidor {
    private BEDatosEnvioGrupo beDatosEnvioGrupo;
    private BEDatosEnvioTipoNota beDatosEnvioTipoNota;
    private BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso;
    private GEDatosTareasRecursos beDatosTareaRecursos;
    private BEDatosEvaluacionResultado beDatosRubroEvaluacionResultado;

    public GEDatosRubroEvaluacionProceso() {
    }

    public BEDatosEnvioGrupo getBeDatosEnvioGrupo() {
        return beDatosEnvioGrupo;
    }

    public void setBeDatosEnvioGrupo(BEDatosEnvioGrupo beDatosEnvioGrupo) {
        this.beDatosEnvioGrupo = beDatosEnvioGrupo;
    }

    public BEDatosEnvioTipoNota getBeDatosEnvioTipoNota() {
        return beDatosEnvioTipoNota;
    }

    public void setBeDatosEnvioTipoNota(BEDatosEnvioTipoNota beDatosEnvioTipoNota) {
        this.beDatosEnvioTipoNota = beDatosEnvioTipoNota;
    }

    public BEDatosRubroEvaluacionProceso getBeDatosRubroEvaluacionProceso() {
        return beDatosRubroEvaluacionProceso;
    }

    public void setBeDatosRubroEvaluacionProceso(BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso) {
        this.beDatosRubroEvaluacionProceso = beDatosRubroEvaluacionProceso;
    }

    public GEDatosTareasRecursos getBeDatosTareaRecursos() {
        return beDatosTareaRecursos;
    }

    public void setBeDatosTareaRecursos(GEDatosTareasRecursos beDatosTareaRecursos) {
        this.beDatosTareaRecursos = beDatosTareaRecursos;
    }

    public BEDatosEvaluacionResultado getBeDatosRubroEvaluacionResultado() {
        return beDatosRubroEvaluacionResultado;
    }

    public void setBeDatosRubroEvaluacionResultado(BEDatosEvaluacionResultado beDatosRubroEvaluacionResultado) {
        this.beDatosRubroEvaluacionResultado = beDatosRubroEvaluacionResultado;
    }

    @Override
    public String toString() {
        return "GEDatosRubroEvaluacionProceso{" +
                "beDatosEnvioGrupo=" + beDatosEnvioGrupo.toString() +
                ", beDatosEnvioTipoNota=" + beDatosEnvioTipoNota.toString() +
                ", beDatosRubroEvaluacionProceso=" + beDatosRubroEvaluacionProceso.toString() +
                '}';
    }
}
