package com.consultoraestrategia.ss_crmeducativo.entities.retrofit;

import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoCampotematicoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoComentario;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;

import java.util.List;

public class BERubroEvalEnvioSimple {
      ////#region Grupo
    private GrupoEquipoC grupoEquipo;
    private List<EquipoC> equipos;
    private List<EquipoIntegranteC> equipoIntegrantes;
        ////#endregion

    //#region Rubro
    private RubroEvaluacionProcesoC rubroEvaluacionProceso;
    private List<RubroEvaluacionProcesoC> rubroEvaluacionAsociado;
    private List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula;
    private List<EvaluacionProcesoC> evaluacionProceso;
    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> ObtenerRubroEvaluacionProcesoEquipo;
    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> ObtenerRubroEvaluacionProcesoIntegrante;
    private List<EquipoEvaluacionProcesoC> obtenerEquipoEvaluacionProceso;
    private List<RubroEvaluacionProcesoCampotematicoC> rubro_evaluacion_proceso_campotematico;
    private List<CriterioRubroEvaluacionC> obtenerCriterioRubroEvaluacionProceso;
    //private List<BEComentarioPredeterminado> comentarioPredeterminado;
    private List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentario;
    private List<ArchivosRubroProceso> archivoRubroProceso;
    //#endregion

    //#region Tarea
    private List<TareasRecursosC> tareasRecursos;
    private List<RecursoDidacticoEventoC> recursoDidactico;
    private TareasC tarea;
    private List<RecursoArchivo> recursoArchivo;
    private List<Archivo> archivo;
    //#endregion
    private long fecha_servidor;

    public GrupoEquipoC getGrupoEquipo() {
        return grupoEquipo;
    }

    public void setGrupoEquipo(GrupoEquipoC grupoEquipo) {
        this.grupoEquipo = grupoEquipo;
    }

    public List<EquipoC> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoC> equipos) {
        this.equipos = equipos;
    }

    public List<EquipoIntegranteC> getEquipoIntegrantes() {
        return equipoIntegrantes;
    }

    public void setEquipoIntegrantes(List<EquipoIntegranteC> equipoIntegrantes) {
        this.equipoIntegrantes = equipoIntegrantes;
    }

    public RubroEvaluacionProcesoC getRubroEvaluacionProceso() {
        return rubroEvaluacionProceso;
    }

    public void setRubroEvaluacionProceso(RubroEvaluacionProcesoC rubroEvaluacionProceso) {
        this.rubroEvaluacionProceso = rubroEvaluacionProceso;
    }

    public List<RubroEvaluacionProcesoC> getRubroEvaluacionAsociado() {
        return rubroEvaluacionAsociado;
    }

    public void setRubroEvaluacionAsociado(List<RubroEvaluacionProcesoC> rubroEvaluacionAsociado) {
        this.rubroEvaluacionAsociado = rubroEvaluacionAsociado;
    }

    public List<RubroEvalRNPFormulaC> getRubroEvalProcesoFormula() {
        return rubroEvalProcesoFormula;
    }

    public void setRubroEvalProcesoFormula(List<RubroEvalRNPFormulaC> rubroEvalProcesoFormula) {
        this.rubroEvalProcesoFormula = rubroEvalProcesoFormula;
    }

    public List<EvaluacionProcesoC> getEvaluacionProceso() {
        return evaluacionProceso;
    }

    public void setEvaluacionProceso(List<EvaluacionProcesoC> evaluacionProceso) {
        this.evaluacionProceso = evaluacionProceso;
    }

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> getObtenerRubroEvaluacionProcesoEquipo() {
        return ObtenerRubroEvaluacionProcesoEquipo;
    }

    public void setObtenerRubroEvaluacionProcesoEquipo(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> obtenerRubroEvaluacionProcesoEquipo) {
        ObtenerRubroEvaluacionProcesoEquipo = obtenerRubroEvaluacionProcesoEquipo;
    }

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> getObtenerRubroEvaluacionProcesoIntegrante() {
        return ObtenerRubroEvaluacionProcesoIntegrante;
    }

    public void setObtenerRubroEvaluacionProcesoIntegrante(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> obtenerRubroEvaluacionProcesoIntegrante) {
        ObtenerRubroEvaluacionProcesoIntegrante = obtenerRubroEvaluacionProcesoIntegrante;
    }

    public List<EquipoEvaluacionProcesoC> getObtenerEquipoEvaluacionProceso() {
        return obtenerEquipoEvaluacionProceso;
    }

    public void setObtenerEquipoEvaluacionProceso(List<EquipoEvaluacionProcesoC> obtenerEquipoEvaluacionProceso) {
        this.obtenerEquipoEvaluacionProceso = obtenerEquipoEvaluacionProceso;
    }

    public List<RubroEvaluacionProcesoCampotematicoC> getRubro_evaluacion_proceso_campotematico() {
        return rubro_evaluacion_proceso_campotematico;
    }

    public void setRubro_evaluacion_proceso_campotematico(List<RubroEvaluacionProcesoCampotematicoC> rubro_evaluacion_proceso_campotematico) {
        this.rubro_evaluacion_proceso_campotematico = rubro_evaluacion_proceso_campotematico;
    }

    public List<CriterioRubroEvaluacionC> getObtenerCriterioRubroEvaluacionProceso() {
        return obtenerCriterioRubroEvaluacionProceso;
    }

    public void setObtenerCriterioRubroEvaluacionProceso(List<CriterioRubroEvaluacionC> obtenerCriterioRubroEvaluacionProceso) {
        this.obtenerCriterioRubroEvaluacionProceso = obtenerCriterioRubroEvaluacionProceso;
    }

    public List<RubroEvaluacionProcesoComentario> getRubroEvaluacionProcesoComentario() {
        return rubroEvaluacionProcesoComentario;
    }

    public void setRubroEvaluacionProcesoComentario(List<RubroEvaluacionProcesoComentario> rubroEvaluacionProcesoComentario) {
        this.rubroEvaluacionProcesoComentario = rubroEvaluacionProcesoComentario;
    }

    public List<ArchivosRubroProceso> getArchivoRubroProceso() {
        return archivoRubroProceso;
    }

    public void setArchivoRubroProceso(List<ArchivosRubroProceso> archivoRubroProceso) {
        this.archivoRubroProceso = archivoRubroProceso;
    }

    public List<TareasRecursosC> getTareasRecursos() {
        return tareasRecursos;
    }

    public void setTareasRecursos(List<TareasRecursosC> tareasRecursos) {
        this.tareasRecursos = tareasRecursos;
    }

    public List<RecursoDidacticoEventoC> getRecursoDidactico() {
        return recursoDidactico;
    }

    public void setRecursoDidactico(List<RecursoDidacticoEventoC> recursoDidactico) {
        this.recursoDidactico = recursoDidactico;
    }

    public TareasC getTarea() {
        return tarea;
    }

    public void setTarea(TareasC tarea) {
        this.tarea = tarea;
    }

    public List<RecursoArchivo> getRecursoArchivo() {
        return recursoArchivo;
    }

    public void setRecursoArchivo(List<RecursoArchivo> recursoArchivo) {
        this.recursoArchivo = recursoArchivo;
    }

    public List<Archivo> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<Archivo> archivo) {
        this.archivo = archivo;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BERubroEvalEnvioSimple that = (BERubroEvalEnvioSimple) o;

        return rubroEvaluacionProceso != null ? rubroEvaluacionProceso.equals(that.rubroEvaluacionProceso) : that.rubroEvaluacionProceso == null;
    }

    @Override
    public int hashCode() {
        return rubroEvaluacionProceso != null ? rubroEvaluacionProceso.hashCode() : 0;
    }

}
