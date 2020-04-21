package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Desempenio;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrollo;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionDesarrolloEstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.EstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoAprendizajeEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoCampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.ProductoEventoReferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadEventoCompetenciaDesempenioIcdInstrumento;

import java.util.List;

public class BEDatosUnidades {
    private List<CampoTematico> campoTematico;

    private List<Competencia> competencia;

    private List<CompetenciaUnidad> relCompetenciaUnidad;

    private List<Desempenio> desempenio;

    private List<DesempenioIcd> relDesempenioIcd;

    private List<Icds> icds;

    private List<SesionEventoCompetenciaDesempenioIcd> relSesionEventoCompetenciaDesempenioicd;

    private List<SesionEventoDesempenioIcdCampotematico> sesionDesempenioIcdCampotematico;

    private List<T_GC_REL_COMPETENCIA_SESION_EVENTO> relCompetenciaSesion;

    private List<T_GC_REL_UNIDAD_APREN_EVENTO_TIPO> relUnidadAprenEvento_tipo;

    private List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> relUnidadEventoCompetenciaDesempenioIcd;

    private List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> relUnidadEventoCompetenciaDesempenioIcdCampoTematico;

    private List<UnidadAprendizaje> unidadAprendizaje;

    private long fecha_servidor;

    private List<SesionAprendizaje> sesion;

    private List<RecursoReferenciaC> relRecursoReferencia;

    private List<RecursoDidacticoEventoC> recursoDidactico;

    private List<RecursoArchivo> relRecursoArchivo;

    private List<Archivo> archivo;

    private List<ActividadAprendizaje> actividad;

    private List<Tipos> tipos;
    private List<ProductoEventoReferencia> relProductoReferencia;
    private List<ProductoAprendizajeEvento> producto;
    private List<ProductoEventoCampoTematico> relProductoEventoCampoTematico;
    private List<InstrumentoEvaluacion> instrumentoEvaluacion;
    private List<UnidadEventoCompetenciaDesempenioIcdInstrumento> unidadCompetenciaDesempenioIcdInstrumento;


    public List<CampoTematico> getCampoTematico() {
        return campoTematico;
    }

    public void setCampoTematico(List<CampoTematico> campoTematico) {
        this.campoTematico = campoTematico;
    }

    public List<Competencia> getCompetencia() {
        return competencia;
    }

    public void setCompetencia(List<Competencia> competencia) {
        this.competencia = competencia;
    }

    public List<CompetenciaUnidad> getRelCompetenciaUnidad() {
        return relCompetenciaUnidad;
    }

    public void setRelCompetenciaUnidad(List<CompetenciaUnidad> relCompetenciaUnidad) {
        this.relCompetenciaUnidad = relCompetenciaUnidad;
    }

    public List<Desempenio> getDesempenio() {
        return desempenio;
    }

    public void setDesempenio(List<Desempenio> desempenio) {
        this.desempenio = desempenio;
    }

    public List<DesempenioIcd> getRelDesempenioIcd() {
        return relDesempenioIcd;
    }

    public void setRelDesempenioIcd(List<DesempenioIcd> relDesempenioIcd) {
        this.relDesempenioIcd = relDesempenioIcd;
    }

    public List<Icds> getIcds() {
        return icds;
    }

    public void setIcds(List<Icds> icds) {
        this.icds = icds;
    }

    public List<SesionEventoCompetenciaDesempenioIcd> getRelSesionEventoCompetenciaDesempenioicd() {
        return relSesionEventoCompetenciaDesempenioicd;
    }

    public void setRelSesionEventoCompetenciaDesempenioicd(List<SesionEventoCompetenciaDesempenioIcd> relSesionEventoCompetenciaDesempenioicd) {
        this.relSesionEventoCompetenciaDesempenioicd = relSesionEventoCompetenciaDesempenioicd;
    }

    public List<SesionEventoDesempenioIcdCampotematico> getSesionDesempenioIcdCampotematico() {
        return sesionDesempenioIcdCampotematico;
    }

    public void setSesionDesempenioIcdCampotematico(List<SesionEventoDesempenioIcdCampotematico> sesionDesempenioIcdCampotematico) {
        this.sesionDesempenioIcdCampotematico = sesionDesempenioIcdCampotematico;
    }

    public List<T_GC_REL_COMPETENCIA_SESION_EVENTO> getRelCompetenciaSesion() {
        return relCompetenciaSesion;
    }

    public void setRelCompetenciaSesion(List<T_GC_REL_COMPETENCIA_SESION_EVENTO> relCompetenciaSesion) {
        this.relCompetenciaSesion = relCompetenciaSesion;
    }

    public List<T_GC_REL_UNIDAD_APREN_EVENTO_TIPO> getRelUnidadAprenEvento_tipo() {
        return relUnidadAprenEvento_tipo;
    }

    public void setRelUnidadAprenEvento_tipo(List<T_GC_REL_UNIDAD_APREN_EVENTO_TIPO> relUnidadAprenEvento_tipo) {
        this.relUnidadAprenEvento_tipo = relUnidadAprenEvento_tipo;
    }

    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> getRelUnidadEventoCompetenciaDesempenioIcd() {
        return relUnidadEventoCompetenciaDesempenioIcd;
    }

    public void setRelUnidadEventoCompetenciaDesempenioIcd(List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> relUnidadEventoCompetenciaDesempenioIcd) {
        this.relUnidadEventoCompetenciaDesempenioIcd = relUnidadEventoCompetenciaDesempenioIcd;
    }

    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> getRelUnidadEventoCompetenciaDesempenioIcdCampoTematico() {
        return relUnidadEventoCompetenciaDesempenioIcdCampoTematico;
    }

    public void setRelUnidadEventoCompetenciaDesempenioIcdCampoTematico(List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> relUnidadEventoCompetenciaDesempenioIcdCampoTematico) {
        this.relUnidadEventoCompetenciaDesempenioIcdCampoTematico = relUnidadEventoCompetenciaDesempenioIcdCampoTematico;
    }

    public List<UnidadAprendizaje> getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public void setUnidadAprendizaje(List<UnidadAprendizaje> unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public long getFecha_servidor() {
        return fecha_servidor;
    }

    public void setFecha_servidor(long fecha_servidor) {
        this.fecha_servidor = fecha_servidor;
    }

    public List<SesionAprendizaje> getSesion() {
        return sesion;
    }

    public void setSesion(List<SesionAprendizaje> sesion) {
        this.sesion = sesion;
    }

    public List<RecursoReferenciaC> getRelRecursoReferencia() {
        return relRecursoReferencia;
    }

    public void setRelRecursoReferencia(List<RecursoReferenciaC> relRecursoReferencia) {
        this.relRecursoReferencia = relRecursoReferencia;
    }

    public List<RecursoDidacticoEventoC> getRecursoDidactico() {
        return recursoDidactico;
    }

    public void setRecursoDidactico(List<RecursoDidacticoEventoC> recursoDidactico) {
        this.recursoDidactico = recursoDidactico;
    }

    public List<RecursoArchivo> getRelRecursoArchivo() {
        return relRecursoArchivo;
    }

    public void setRelRecursoArchivo(List<RecursoArchivo> relRecursoArchivo) {
        this.relRecursoArchivo = relRecursoArchivo;
    }

    public List<Archivo> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<Archivo> archivo) {
        this.archivo = archivo;
    }

    public List<ActividadAprendizaje> getActividad() {
        return actividad;
    }

    public void setActividad(List<ActividadAprendizaje> actividad) {
        this.actividad = actividad;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    public List<ProductoEventoReferencia> getRelProductoReferencia() {
        return relProductoReferencia;
    }

    public void setRelProductoReferencia(List<ProductoEventoReferencia> relProductoReferencia) {
        this.relProductoReferencia = relProductoReferencia;
    }

    public List<ProductoAprendizajeEvento> getProducto() {
        return producto;
    }

    public void setProducto(List<ProductoAprendizajeEvento> producto) {
        this.producto = producto;
    }

    public List<ProductoEventoCampoTematico> getRelProductoEventoCampoTematico() {
        return relProductoEventoCampoTematico;
    }

    public void setRelProductoEventoCampoTematico(List<ProductoEventoCampoTematico> relProductoEventoCampoTematico) {
        this.relProductoEventoCampoTematico = relProductoEventoCampoTematico;
    }

    public List<InstrumentoEvaluacion> getInstrumentoEvaluacion() {
        return instrumentoEvaluacion;
    }

    public void setInstrumentoEvaluacion(List<InstrumentoEvaluacion> instrumentoEvaluacion) {
        this.instrumentoEvaluacion = instrumentoEvaluacion;
    }

    public List<UnidadEventoCompetenciaDesempenioIcdInstrumento> getUnidadCompetenciaDesempenioIcdInstrumento() {
        return unidadCompetenciaDesempenioIcdInstrumento;
    }

    public void setUnidadCompetenciaDesempenioIcdInstrumento(List<UnidadEventoCompetenciaDesempenioIcdInstrumento> unidadCompetenciaDesempenioIcdInstrumento) {
        this.unidadCompetenciaDesempenioIcdInstrumento = unidadCompetenciaDesempenioIcdInstrumento;
    }
}
