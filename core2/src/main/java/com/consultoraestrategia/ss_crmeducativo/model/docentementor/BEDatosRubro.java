package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

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

import java.util.List;

public class BEDatosRubro {
    private List<RubroEvaluacionProcesoC> rubroEvaluacionProceso;

    private List<RubroEvalRNPFormulaC> relRubroEvaluacionRNPFormula;

    private List<EvaluacionProcesoC> evaluacionProceso;

    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> rubroEvaluacionProcesoEquipo;

    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> rubroEvaluacionProcesoIntegrante;

    private List<EquipoEvaluacionProcesoC> equipoEvaluacionProceso;

    private List<CriterioRubroEvaluacionC> criterioRubroEvaluacionProceso;

    private List<RubroEvaluacionProcesoCampotematicoC> rubroEvaluacionProcesoCampotematico;

    private List<ComentarioPredeterminado> comentarioPredeterminado;

    private List<ArchivosRubroProceso> archivoRubroProceso;

    private List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentario;

    private long fecha_servidor;

    public List<RubroEvaluacionProcesoC> getRubroEvaluacionProceso() {
        return rubroEvaluacionProceso;
    }

    public void setRubroEvaluacionProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProceso) {
        this.rubroEvaluacionProceso = rubroEvaluacionProceso;
    }

    public List<RubroEvalRNPFormulaC> getRelRubroEvaluacionRNPFormula() {
        return relRubroEvaluacionRNPFormula;
    }

    public void setRelRubroEvaluacionRNPFormula(List<RubroEvalRNPFormulaC> relRubroEvaluacionRNPFormula) {
        this.relRubroEvaluacionRNPFormula = relRubroEvaluacionRNPFormula;
    }

    public List<EvaluacionProcesoC> getEvaluacionProceso() {
        return evaluacionProceso;
    }

    public void setEvaluacionProceso(List<EvaluacionProcesoC> evaluacionProceso) {
        this.evaluacionProceso = evaluacionProceso;
    }

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> getRubroEvaluacionProcesoEquipo() {
        return rubroEvaluacionProcesoEquipo;
    }

    public void setRubroEvaluacionProcesoEquipo(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> rubroEvaluacionProcesoEquipo) {
        this.rubroEvaluacionProcesoEquipo = rubroEvaluacionProcesoEquipo;
    }

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> getRubroEvaluacionProcesoIntegrante() {
        return rubroEvaluacionProcesoIntegrante;
    }

    public void setRubroEvaluacionProcesoIntegrante(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> rubroEvaluacionProcesoIntegrante) {
        this.rubroEvaluacionProcesoIntegrante = rubroEvaluacionProcesoIntegrante;
    }

    public List<EquipoEvaluacionProcesoC> getEquipoEvaluacionProceso() {
        return equipoEvaluacionProceso;
    }

    public void setEquipoEvaluacionProceso(List<EquipoEvaluacionProcesoC> equipoEvaluacionProceso) {
        this.equipoEvaluacionProceso = equipoEvaluacionProceso;
    }

    public List<CriterioRubroEvaluacionC> getCriterioRubroEvaluacionProceso() {
        return criterioRubroEvaluacionProceso;
    }

    public void setCriterioRubroEvaluacionProceso(List<CriterioRubroEvaluacionC> criterioRubroEvaluacionProceso) {
        this.criterioRubroEvaluacionProceso = criterioRubroEvaluacionProceso;
    }

    public List<RubroEvaluacionProcesoCampotematicoC> getRubroEvaluacionProcesoCampotematico() {
        return rubroEvaluacionProcesoCampotematico;
    }

    public void setRubroEvaluacionProcesoCampotematico(List<RubroEvaluacionProcesoCampotematicoC> rubroEvaluacionProcesoCampotematico) {
        this.rubroEvaluacionProcesoCampotematico = rubroEvaluacionProcesoCampotematico;
    }

    public List<ComentarioPredeterminado> getComentarioPredeterminado() {
        return comentarioPredeterminado;
    }

    public void setComentarioPredeterminado(List<ComentarioPredeterminado> comentarioPredeterminado) {
        this.comentarioPredeterminado = comentarioPredeterminado;
    }

    public List<ArchivosRubroProceso> getArchivoRubroProceso() {
        return archivoRubroProceso;
    }

    public void setArchivoRubroProceso(List<ArchivosRubroProceso> archivoRubroProceso) {
        this.archivoRubroProceso = archivoRubroProceso;
    }

    public List<RubroEvaluacionProcesoComentario> getRubroEvaluacionProcesoComentario() {
        return rubroEvaluacionProcesoComentario;
    }

    public void setRubroEvaluacionProcesoComentario(List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentario) {
        this.rubroEvaluacionProcesoComentario = rubroEvaluacionProcesoComentario;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }
}
