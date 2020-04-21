package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.ComentarioPredeterminado;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class BEDatosRubroEvaluacionProceso  extends BEDatosServidor {

    private List<RubroEvaluacionProcesoC> rubroEvaluacionProceso;
    private List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula;
    private List<EvaluacionProcesoC> evaluacionProceso;
    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> ObtenerRubroEvaluacionProcesoEquipo ;
    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> ObtenerRubroEvaluacionProcesoIntegrante;
    private List<EquipoEvaluacionProcesoC> obtenerEquipoEvaluacionProceso;
    private List<RubroEvaluacionProcesoCampotematicoC> rubro_evaluacion_proceso_campotematico;
    private List<CriterioRubroEvaluacionC> obtenerCriterioRubroEvaluacionProceso;
    private List<ComentarioPredeterminado> comentarioPredeterminado;
    private List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentario;
    private List<ArchivosRubroProceso> archivoRubroProceso;

    public BEDatosRubroEvaluacionProceso() {
    }

    public void addRubroEvaluacionProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProceso) {
        if(this.rubroEvaluacionProceso==null){
            this.rubroEvaluacionProceso = new ArrayList<>();
        }
        this.rubroEvaluacionProceso.addAll(rubroEvaluacionProceso);
    }

    public List<RubroEvaluacionProcesoC> getRubroEvaluacionProceso() {
        return rubroEvaluacionProceso;
    }

    public List<RubroEvalRNPFormulaC> getRubroEvalProcesoFormula() {
        return rubroEvalProcesoFormula;
    }

    public void addRubroEvalProcesoFormula(List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula) {
        if(this.rubroEvalProcesoFormula==null){
            this.rubroEvalProcesoFormula = new ArrayList<>();
        }
        this.rubroEvalProcesoFormula.addAll(rubroEvalProcesoFormula);
    }

    public List<EvaluacionProcesoC> getEvaluacionProceso() {
        return evaluacionProceso;
    }

    public void addEvaluacionProceso(List<EvaluacionProcesoC> evaluacionProceso) {
        if(this.evaluacionProceso==null){
            this.evaluacionProceso = new ArrayList<>();
        }
        this.evaluacionProceso.addAll(evaluacionProceso);
    }

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> getObtenerRubroEvaluacionProcesoEquipo() {
        return ObtenerRubroEvaluacionProcesoEquipo;
    }

    public void addObtenerRubroEvaluacionProcesoEquipo(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> ObtenerRubroEvaluacionProcesoEquipo) {
        if(this.ObtenerRubroEvaluacionProcesoEquipo==null){
            this.ObtenerRubroEvaluacionProcesoEquipo = new ArrayList<>();
        }
        this.ObtenerRubroEvaluacionProcesoEquipo.addAll(ObtenerRubroEvaluacionProcesoEquipo);
    }

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> getObtenerRubroEvaluacionProcesoIntegrante() {
        return ObtenerRubroEvaluacionProcesoIntegrante;
    }

    public void addObtenerRubroEvaluacionProcesoIntegrante(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> obtenerRubroEvaluacionProcesoIntegrante) {
        if(this.ObtenerRubroEvaluacionProcesoIntegrante==null){
            this.ObtenerRubroEvaluacionProcesoIntegrante = new ArrayList<>();
        }
        this.ObtenerRubroEvaluacionProcesoIntegrante.addAll(obtenerRubroEvaluacionProcesoIntegrante);
    }

    public List<EquipoEvaluacionProcesoC> getObtenerEquipoEvaluacionProceso() {
        return obtenerEquipoEvaluacionProceso;
    }

    public void addObtenerEquipoEvaluacionProceso(List<EquipoEvaluacionProcesoC> obtenerEquipoEvaluacionProceso) {
        if(this.obtenerEquipoEvaluacionProceso==null){
            this.obtenerEquipoEvaluacionProceso = new ArrayList<>();
        }
        this.obtenerEquipoEvaluacionProceso.addAll(obtenerEquipoEvaluacionProceso);
    }

    public List<RubroEvaluacionProcesoCampotematicoC> getRubro_evaluacion_proceso_campotematico() {
        return rubro_evaluacion_proceso_campotematico;
    }

    public void addRubro_evaluacion_proceso_campotematico(List<RubroEvaluacionProcesoCampotematicoC> getRubro_evaluacion_proceso_campotematico) {
        if(this.rubro_evaluacion_proceso_campotematico==null){
            this.rubro_evaluacion_proceso_campotematico = new ArrayList<>();
        }
        this.rubro_evaluacion_proceso_campotematico.addAll(getRubro_evaluacion_proceso_campotematico);
    }


    public void setRubroEvaluacionProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProceso) {
        this.rubroEvaluacionProceso = rubroEvaluacionProceso;
    }

    public void setRubroEvalProcesoFormula(List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula) {
        this.rubroEvalProcesoFormula = rubroEvalProcesoFormula;
    }

    public void setEvaluacionProceso(List<EvaluacionProcesoC> evaluacionProceso) {
        this.evaluacionProceso = evaluacionProceso;
    }

    public void setObtenerRubroEvaluacionProcesoEquipo(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> obtenerRubroEvaluacionProcesoEquipo) {
        ObtenerRubroEvaluacionProcesoEquipo = obtenerRubroEvaluacionProcesoEquipo;
    }

    public void setObtenerRubroEvaluacionProcesoIntegrante(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> obtenerRubroEvaluacionProcesoIntegrante) {
        ObtenerRubroEvaluacionProcesoIntegrante = obtenerRubroEvaluacionProcesoIntegrante;
    }

    public void setObtenerEquipoEvaluacionProceso(List<EquipoEvaluacionProcesoC> obtenerEquipoEvaluacionProceso) {
        this.obtenerEquipoEvaluacionProceso = obtenerEquipoEvaluacionProceso;
    }

    public void setRubro_evaluacion_proceso_campotematico(List<RubroEvaluacionProcesoCampotematicoC> rubro_evaluacion_proceso_campotematico) {
        this.rubro_evaluacion_proceso_campotematico = rubro_evaluacion_proceso_campotematico;
    }

    public List<CriterioRubroEvaluacionC> getObtenerCriterioRubroEvaluacionProceso() {
        return obtenerCriterioRubroEvaluacionProceso;
    }

    public void addObtenerCriterioRubroEvaluacionProceso(List<CriterioRubroEvaluacionC> obtenerCriterioRubroEvaluacionProceso) {
        if(this.obtenerCriterioRubroEvaluacionProceso==null){
            this.obtenerCriterioRubroEvaluacionProceso = new ArrayList<>();
        }
        this.obtenerCriterioRubroEvaluacionProceso.addAll(obtenerCriterioRubroEvaluacionProceso);
    }

    public void setObtenerCriterioRubroEvaluacionProceso(List<CriterioRubroEvaluacionC> obtenerCriterioRubroEvaluacionProceso) {
        this.obtenerCriterioRubroEvaluacionProceso = obtenerCriterioRubroEvaluacionProceso;
    }

    public List<ComentarioPredeterminado> getComentarioPredeterminado() {
        return comentarioPredeterminado;
    }

    public void addComentarioPredeterminado(List<ComentarioPredeterminado> comentarioPredeterminado) {
        if(this.comentarioPredeterminado==null){
            this.comentarioPredeterminado = new ArrayList<>();
        }
        this.comentarioPredeterminado.addAll(comentarioPredeterminado);
    }

    public void setComentarioPredeterminado(List<ComentarioPredeterminado> comentarioPredeterminado) {
        this.comentarioPredeterminado = comentarioPredeterminado;
    }

    public List<RubroEvaluacionProcesoComentario> getRubroEvaluacionProcesoComentario() {
        return rubroEvaluacionProcesoComentario;
    }

    public void addRubroEvaluacionProcesoComentario(List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentario) {
        if(this.rubroEvaluacionProcesoComentario==null){
            this.rubroEvaluacionProcesoComentario = new ArrayList<>();
        }
        this.rubroEvaluacionProcesoComentario.addAll(rubroEvaluacionProcesoComentario);
    }

    public void setRubroEvaluacionProcesoComentario(List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentario) {
        this.rubroEvaluacionProcesoComentario = rubroEvaluacionProcesoComentario;
    }

    public List<ArchivosRubroProceso> getArchivoRubroProceso() {
        return archivoRubroProceso;
    }

    public void addArchivoRubroProceso(List<ArchivosRubroProceso> archivoRubroProceso) {
        if(this.archivoRubroProceso==null){
            this.archivoRubroProceso = new ArrayList<>();
        }
        this.archivoRubroProceso.addAll(archivoRubroProceso);
    }

    public void setArchivoRubroProceso(List<ArchivosRubroProceso> archivoRubroProceso) {
        this.archivoRubroProceso = archivoRubroProceso;
    }

    @Override
    public String toString() {
        return "BEDatosRubroEvaluacionProceso{" +
                "rubroEvaluacionProceso=" + rubroEvaluacionProceso +
                ", rubroEvalProcesoFormula=" + rubroEvalProcesoFormula +
                ", evaluacionProceso=" + evaluacionProceso +
                ", ObtenerRubroEvaluacionProcesoEquipo=" + ObtenerRubroEvaluacionProcesoEquipo +
                ", ObtenerRubroEvaluacionProcesoIntegrante=" + ObtenerRubroEvaluacionProcesoIntegrante +
                ", obtenerEquipoEvaluacionProceso=" + obtenerEquipoEvaluacionProceso +
                ", rubro_evaluacion_proceso_campotematico=" + rubro_evaluacion_proceso_campotematico +
                ", obtenerCriterioRubroEvaluacionProceso=" + obtenerCriterioRubroEvaluacionProceso +
                ", comentarioPredeterminado=" + comentarioPredeterminado +
                ", rubroEvaluacionProcesoComentario=" + rubroEvaluacionProcesoComentario +
                ", archivoRubroProceso=" + archivoRubroProceso +
                '}';
    }
}
