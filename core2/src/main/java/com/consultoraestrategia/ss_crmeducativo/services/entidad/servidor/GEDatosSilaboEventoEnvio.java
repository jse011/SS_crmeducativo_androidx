package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.AreaInstrumento;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.Dimension;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionCaracteristica;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoAprendizajeEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoCampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoReferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboCompetencia;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEventoCompentenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TecnicaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoInstrumento;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumento;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadTipo;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 16/05/2018.
 */
//Este POJO esta relacionado con BEDatosSesionAprendizaje
public class GEDatosSilaboEventoEnvio extends BEDatosServidor {
    private List<ActividadAprendizaje> actividad;

    private List<RecursoArchivo> recursoArchivo;
    private List<RecursoReferenciaC> recursoReferencia;
    private List<TareasRecursosC> tareasRecursos;
    private List<RecursoDidacticoEventoC> recursoDidactico;
    private List<TareasC> tareas;

    public List<ProductoEventoReferencia> producto_evento_referencia;
    public List<ProductoAprendizajeEvento> productoAprendizaje;
    private List<UnidadAprendizaje> unidadAprendizaje;
    private List<UnidadTipo> unidadTipo;

    private List<SesionAprendizaje> sesiones;
    public List<ProductoEventoCampoTematico> obtenerProductoEventoCampoTematico;

    private List<SilaboEvento> silaboEvento;
    private List<SilaboEventoCompentenciaDesempenioIcd> silaboeventocompetenciadesempenioicd;
    private List<SilaboEventoDesempenioIcdCampotematico> silaboeventodesempenioicdcampotematico;
    //public List<BESilaboEventoTipo> obtenerSilaboEventoTipo { get; set; }
    private List<SilaboCompetencia> silabocompetencia;
    private List<T_GC_REL_COMPETENCIA_SESION_EVENTO> competenciaSesion;

    private List<SesionEventoCompetenciaDesempenioIcd> rel_sesion_evento_competencia_desempenioicd;
    private List<SesionEventoDesempenioIcdCampotematico> sesion_desempenio_icd_campotematico;

    private List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> rel_unidad_evento_competencia_desempenio_icd;
    private List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> rel_unidad_evento_competencia_desempenio_icd_campo_tematico;
    private List<CompetenciaUnidad> competenciaUnidad;
    //public List<BE_Gc_Rel_Sesion_Evento_Desempenio_Icd_Instrumento> sesion_desempenio_icd_instrumento { get; set; }
    private List<InstrumentoEvaluacion> instrumento_eval;
    private List<SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica> sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas;// en el antiguo servicio: rel_sesion_evento_competencia_desempenio_icd_instrumento_tecnica
    private List<Competencia> competencias;
    //public List<BECompetenciaDesempenioIcd> rel_competencia_desempenio_icd { get; set; }
    private List<DesempenioIcd> rel_desempenio_icd;
    private List<Desempenio> obtenerDesempenio;
    private List<Icds> icds;
    private List<CampoTematico> campoTematico;
    //public List<BESesionEventoCampoTematico> obtenerSesionEventoCampoTematico { get; set; }
    //public List<BESesionEventoCompetenciaDesempenioIcdCampoTematico> obtenerSesionEventoCompetenciaDesempenioICDCampoTematico { get; set; }
    //public List<BESilaboEventoCampoTematico> obtenerSilaboEventoCampoTematico { get; set; }
    //public List<BEUnidadEventoCampoTematico> obtenerUnidadEventoCampoTematico { get; set; }
    //public List<BEUnidadEventoCompetenciaDesempenioIcdInstrumento> obtenerUnidadEventoCompetenciaDesempenioICDInstrumento { get; set; }
    //public List<BEVinculoUnidadAprendEvento> obtenerVinculoUnidadAprendEvento { get; set; }
    //public List<BEVinculoUnidadAprendizajeEventoCampoTematico> obtenerVinculoUnidadAprendEventoCampoTematico { get; set; }
    //public List<BEUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica> obtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica { get; set; }
    private List<T_GC_REL_UNIDAD_APREN_EVENTO_TIPO> rel_unidad_apren_evento_tipo;
    private List<CalendarioPeriodo> calendarioPeriodos;
    private List<CargaCursoCalendarioPeriodo> obtenerCargaCursosCalendarioPeriodo;
    private List<CalendarioPeriodoDetalle> obtenerCalendarioPeriodoDetalle;
    private List<AreaInstrumento> obtenerAreaInstrumento;
    private List<TipoInstrumento> obtenerTipoInstrumento;
    private List<Dimension> obtenerDimension;
    private List<DimensionCaracteristica> obtenerDimensionCaracteristica;
    private List<DimensionObservada> obtenerDimensionObservada;
    private List<InstrumentoObservado> obtenerInstrumentoObservado;
    //private GEDatosTareasRecursos datosTareaRecursos;
    private List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProceso;

    private List<UnidadEventoCompetenciaDesempenioIcdInstrumento>obtenerUnidadEventoCompetenciaDesempenioICDInstrumento;
    private List<UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica>obtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica;
    private List<TecnicaEvaluacion>tecnicaEvaluacion;
    public GEDatosSilaboEventoEnvio() {
    }

    public List<ProductoEventoCampoTematico> getObtenerProductoEventoCampoTematico() {
        return obtenerProductoEventoCampoTematico;
    }

    public void setObtenerProductoEventoCampoTematico(List<ProductoEventoCampoTematico> obtenerProductoEventoCampoTematico) {
        this.obtenerProductoEventoCampoTematico = obtenerProductoEventoCampoTematico;
    }

    public List<ActividadAprendizaje> getActividad() {
        return actividad;
    }

    public void setActividad(List<ActividadAprendizaje> actividad) {
        this.actividad = actividad;
    }

    public List<RecursoArchivo> getRecursoArchivo() {
        return recursoArchivo;
    }

    public void setRecursoArchivo(List<RecursoArchivo> recursoArchivo) {
        this.recursoArchivo = recursoArchivo;
    }

    public List<RecursoReferenciaC> getRecursoReferencia() {
        return recursoReferencia;
    }

    public void setRecursoReferencia(List<RecursoReferenciaC> recursoReferencia) {
        this.recursoReferencia = recursoReferencia;
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

    public List<TareasC> getTareas() {
        return tareas;
    }

    public void setTareas(List<TareasC> tareas) {
        this.tareas = tareas;
    }

    public List<UnidadAprendizaje> getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public void setUnidadAprendizaje(List<UnidadAprendizaje> unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public List<UnidadTipo> getUnidadTipo() {
        return unidadTipo;
    }

    public void setUnidadTipo(List<UnidadTipo> unidadTipo) {
        this.unidadTipo = unidadTipo;
    }

    public List<SesionAprendizaje> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionAprendizaje> sesiones) {
        this.sesiones = sesiones;
    }

    public List<SilaboEvento> getSilaboEvento() {
        return silaboEvento;
    }

    public void setSilaboEvento(List<SilaboEvento> silaboEvento) {
        this.silaboEvento = silaboEvento;
    }

    public List<SilaboEventoCompentenciaDesempenioIcd> getSilaboeventocompetenciadesempenioicd() {
        return silaboeventocompetenciadesempenioicd;
    }

    public void setSilaboeventocompetenciadesempenioicd(List<SilaboEventoCompentenciaDesempenioIcd> silaboeventocompetenciadesempenioicd) {
        this.silaboeventocompetenciadesempenioicd = silaboeventocompetenciadesempenioicd;
    }

    public List<SilaboEventoDesempenioIcdCampotematico> getSilaboeventodesempenioicdcampotematico() {
        return silaboeventodesempenioicdcampotematico;
    }

    public void setSilaboeventodesempenioicdcampotematico(List<SilaboEventoDesempenioIcdCampotematico> silaboeventodesempenioicdcampotematico) {
        this.silaboeventodesempenioicdcampotematico = silaboeventodesempenioicdcampotematico;
    }

    public List<SilaboCompetencia> getSilabocompetencia() {
        return silabocompetencia;
    }

    public void setSilabocompetencia(List<SilaboCompetencia> silabocompetencia) {
        this.silabocompetencia = silabocompetencia;
    }

    public List<T_GC_REL_COMPETENCIA_SESION_EVENTO> getCompetenciaSesion() {
        return competenciaSesion;
    }

    public void setCompetenciaSesion(List<T_GC_REL_COMPETENCIA_SESION_EVENTO> competenciaSesion) {
        this.competenciaSesion = competenciaSesion;
    }

    public List<SesionEventoCompetenciaDesempenioIcd> getRel_sesion_evento_competencia_desempenioicd() {
        return rel_sesion_evento_competencia_desempenioicd;
    }

    public void setRel_sesion_evento_competencia_desempenioicd(List<SesionEventoCompetenciaDesempenioIcd> rel_sesion_evento_competencia_desempenioicd) {
        this.rel_sesion_evento_competencia_desempenioicd = rel_sesion_evento_competencia_desempenioicd;
    }

    public List<SesionEventoDesempenioIcdCampotematico> getSesion_desempenio_icd_campotematico() {
        return sesion_desempenio_icd_campotematico;
    }

    public void setSesion_desempenio_icd_campotematico(List<SesionEventoDesempenioIcdCampotematico> sesion_desempenio_icd_campotematico) {
        this.sesion_desempenio_icd_campotematico = sesion_desempenio_icd_campotematico;
    }

    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> getRel_unidad_evento_competencia_desempenio_icd() {
        return rel_unidad_evento_competencia_desempenio_icd;
    }

    public void setRel_unidad_evento_competencia_desempenio_icd(List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> rel_unidad_evento_competencia_desempenio_icd) {
        this.rel_unidad_evento_competencia_desempenio_icd = rel_unidad_evento_competencia_desempenio_icd;
    }

    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> getRel_unidad_evento_competencia_desempenio_icd_campo_tematico() {
        return rel_unidad_evento_competencia_desempenio_icd_campo_tematico;
    }

    public void setRel_unidad_evento_competencia_desempenio_icd_campo_tematico(List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> rel_unidad_evento_competencia_desempenio_icd_campo_tematico) {
        this.rel_unidad_evento_competencia_desempenio_icd_campo_tematico = rel_unidad_evento_competencia_desempenio_icd_campo_tematico;
    }

    public List<CompetenciaUnidad> getCompetenciaUnidad() {
        return competenciaUnidad;
    }

    public void setCompetenciaUnidad(List<CompetenciaUnidad> competenciaUnidad) {
        this.competenciaUnidad = competenciaUnidad;
    }

    public List<InstrumentoEvaluacion> getInstrumento_eval() {
        return instrumento_eval;
    }

    public void setInstrumento_eval(List<InstrumentoEvaluacion> instrumento_eval) {
        this.instrumento_eval = instrumento_eval;
    }

    public List<SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica> getSesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas() {
        return sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas;
    }

    public void setSesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas(List<SesionEventoCompetenciaDesempenioIcdInstrumentoTecnica> sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas) {
        this.sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas = sesionEventoCompetenciaDesempenioIcdInstrumentoTecnicas;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    public List<DesempenioIcd> getRel_desempenio_icd() {
        return rel_desempenio_icd;
    }

    public void setRel_desempenio_icd(List<DesempenioIcd> rel_desempenio_icd) {
        this.rel_desempenio_icd = rel_desempenio_icd;
    }

    public List<Icds> getIcds() {
        return icds;
    }

    public void setIcds(List<Icds> icds) {
        this.icds = icds;
    }

    public List<CampoTematico> getCampoTematico() {
        return campoTematico;
    }

    public void setCampoTematico(List<CampoTematico> campoTematico) {
        this.campoTematico = campoTematico;
    }

    public List<T_GC_REL_UNIDAD_APREN_EVENTO_TIPO> getRel_unidad_apren_evento_tipo() {
        return rel_unidad_apren_evento_tipo;
    }

    public void setRel_unidad_apren_evento_tipo(List<T_GC_REL_UNIDAD_APREN_EVENTO_TIPO> rel_unidad_apren_evento_tipo) {
        this.rel_unidad_apren_evento_tipo = rel_unidad_apren_evento_tipo;
    }

    public List<CalendarioPeriodo> getCalendarioPeriodos() {
        return calendarioPeriodos;
    }

    public void setCalendarioPeriodos(List<CalendarioPeriodo> calendarioPeriodos) {
        this.calendarioPeriodos = calendarioPeriodos;
    }

    public List<AreaInstrumento> getObtenerAreaInstrumento() {
        return obtenerAreaInstrumento;
    }

    public void setObtenerAreaInstrumento(List<AreaInstrumento> obtenerAreaInstrumento) {
        this.obtenerAreaInstrumento = obtenerAreaInstrumento;
    }

    public List<TipoInstrumento> getObtenerTipoInstrumento() {
        return obtenerTipoInstrumento;
    }

    public void setObtenerTipoInstrumento(List<TipoInstrumento> obtenerTipoInstrumento) {
        this.obtenerTipoInstrumento = obtenerTipoInstrumento;
    }

    public List<Dimension> getObtenerDimension() {
        return obtenerDimension;
    }

    public void setObtenerDimension(List<Dimension> obtenerDimension) {
        this.obtenerDimension = obtenerDimension;
    }

    public List<DimensionCaracteristica> getObtenerDimensionCaracteristica() {
        return obtenerDimensionCaracteristica;
    }

    public void setObtenerDimensionCaracteristica(List<DimensionCaracteristica> obtenerDimensionCaracteristica) {
        this.obtenerDimensionCaracteristica = obtenerDimensionCaracteristica;
    }

    public List<DimensionObservada> getObtenerDimensionObservada() {
        return obtenerDimensionObservada;
    }

    public void setObtenerDimensionObservada(List<DimensionObservada> obtenerDimensionObservada) {
        this.obtenerDimensionObservada = obtenerDimensionObservada;
    }

    public List<InstrumentoObservado> getObtenerInstrumentoObservado() {
        return obtenerInstrumentoObservado;
    }

    public void setObtenerInstrumentoObservado(List<InstrumentoObservado> obtenerInstrumentoObservado) {
        this.obtenerInstrumentoObservado = obtenerInstrumentoObservado;
    }

/*
    public GEDatosTareasRecursos getDatosTareaRecursos() {
        return datosTareaRecursos;
    }

    public void setDatosTareaRecursos(GEDatosTareasRecursos datosTareaRecursos) {
        this.datosTareaRecursos = datosTareaRecursos;
    }*/

    public List<TareaRubroEvaluacionProceso> getTareaRubroEvaluacionProceso() {
        return tareaRubroEvaluacionProceso;
    }

    public void setTareaRubroEvaluacionProceso(List<TareaRubroEvaluacionProceso> tareaRubroEvaluacionProceso) {
        this.tareaRubroEvaluacionProceso = tareaRubroEvaluacionProceso;
    }


    public List<Desempenio> getObtenerDesempenio() {
        return obtenerDesempenio;
    }

    public void setObtenerDesempenio(List<Desempenio> obtenerDesempenio) {
        this.obtenerDesempenio = obtenerDesempenio;
    }

    public List<CargaCursoCalendarioPeriodo> getObtenerCargaCursosCalendarioPeriodo() {
        return obtenerCargaCursosCalendarioPeriodo;
    }

    public void setObtenerCargaCursosCalendarioPeriodo(List<CargaCursoCalendarioPeriodo> obtenerCargaCursosCalendarioPeriodo) {
        this.obtenerCargaCursosCalendarioPeriodo = obtenerCargaCursosCalendarioPeriodo;
    }

    public List<CalendarioPeriodoDetalle> getObtenerCalendarioPeriodoDetalle() {
        return obtenerCalendarioPeriodoDetalle;
    }

    public void setObtenerCalendarioPeriodoDetalle(List<CalendarioPeriodoDetalle> obtenerCalendarioPeriodoDetalle) {
        this.obtenerCalendarioPeriodoDetalle = obtenerCalendarioPeriodoDetalle;
    }

    public List<ProductoEventoReferencia> getProducto_evento_referencia() {
        return producto_evento_referencia;
    }

    public void setProducto_evento_referencia(List<ProductoEventoReferencia> producto_evento_referencia) {
        this.producto_evento_referencia = producto_evento_referencia;
    }

    public List<ProductoAprendizajeEvento> getProductoAprendizaje() {
        return productoAprendizaje;
    }

    public void setProductoAprendizaje(List<ProductoAprendizajeEvento> productoAprendizaje) {
        this.productoAprendizaje = productoAprendizaje;
    }

    public List<UnidadEventoCompetenciaDesempenioIcdInstrumento> getObtenerUnidadEventoCompetenciaDesempenioICDInstrumento() {
        return obtenerUnidadEventoCompetenciaDesempenioICDInstrumento;
    }

    public void setObtenerUnidadEventoCompetenciaDesempenioICDInstrumento(List<UnidadEventoCompetenciaDesempenioIcdInstrumento> obtenerUnidadEventoCompetenciaDesempenioICDInstrumento) {
        this.obtenerUnidadEventoCompetenciaDesempenioICDInstrumento = obtenerUnidadEventoCompetenciaDesempenioICDInstrumento;
    }

    public List<UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica> getObtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica() {
        return obtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica;
    }

    public void setObtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica(List<UnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica> obtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica) {
        this.obtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica = obtenerUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica;
    }

    public List<TecnicaEvaluacion> getTecnicaEvaluacion() {
        return tecnicaEvaluacion;
    }

    public void setTecnicaEvaluacion(List<TecnicaEvaluacion> tecnicaEvaluacion) {
        this.tecnicaEvaluacion = tecnicaEvaluacion;
    }
}
