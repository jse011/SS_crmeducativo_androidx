package com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor;

import com.consultoraestrategia.ss_crmeducativo.entities.ActividadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoReferenciaC;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.BEDatosServidor;

import java.util.List;

/**
 * Created by SCIEV on 23/05/2018.
 */
//Este POJO es una copia de BESilaboEvento
public class BEDatosSesionAprendizaje  extends BEDatosServidor {
    List<SesionAprendizaje> sesionAprendizaje;

    public List<Competencia> competencias;
    public List<Competencia> capacidades;
    public List<DesempenioIcd> desempenioIcd;
    public List<Icds> icd;
    ///public List<BEInstrumentoEvaluacion> instrumentoEvaluacion; por agregar tambien en BESilaboEvento
    public List<CampoTematico> campoTematico;
    //public List<BEProductoAprendizaje_Evento> productoApendizajeEvento;
    public List<RecursoDidacticoEventoC> recursoDidacticoEvento;
    public List<ActividadAprendizaje> actividadEvento;// BESilaboEvento se llama actividad
    public List<TareasC> tareaEvento;
    public List<TareasRecursosC> tareaEventoRecurso;
    //public List<BEArchivo> archivo; No existe en BESilaboEvento

    //Sesion
    public List<CompetenciaUnidad> competenciaUnidad;//BECompetenciaSesionUnidad
    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> competenciaUnidadDesempenioIcd;//BERelUnidadEventoCompetenciaDesempenioICD
    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> competenciaUnidadDesempenioIcdCampoTematico;
    //public List<BEUnidadEventoCompetenciaDesempenioIcdInstrumento> competenciaUnidadDesempenioIcdInstrumento;
    //public List<BEUnidadEventoCompetenciaDesempenioIcdInstrumentoTecnica> competenciaUnidadDesempenioIcdInstrumentoTecnica;
    //public List<BEUnidadEventoCampoTematico> unidadCampoTematico;


    //Unidad
    public List<UnidadAprendizaje> unidadAprendizaje;
    public List<T_GC_REL_COMPETENCIA_SESION_EVENTO> competenciaSesion;
    public List<SesionEventoCompetenciaDesempenioIcd> competenciaSesionDesempenioIcd;//getRel_sesion_evento_competencia_desempenioicd
    public List<SesionEventoDesempenioIcdCampotematico> competenciaSesionDesempenioIcdCampoTematico;//getSesion_desempenio_icd_campotematico

    public List<RecursoArchivo> recursoArchivo;
    List<RecursoReferenciaC> recursoEventoReferencia;//
    //public List<BE_Gc_Rel_Sesion_Evento_Desempenio_Icd_Instrumento> competenciaSesionDesempenioIcdInstrumento;
    // public List<BERelSesionEventoCompetenciaDesempenioIcdInstrumentoIcd> ccompetenciaSesionDesempenioIcdInstrumentoTecnica;
    //public List<BESesionEventoCampoTematico> sesionCampoTematico;
    public BEDatosSesionAprendizaje() {
    }

    public List<SesionAprendizaje> getSesionAprendizaje() {
        return sesionAprendizaje;
    }

    public void setSesionAprendizaje(List<SesionAprendizaje> sesionAprendizaje) {
        this.sesionAprendizaje = sesionAprendizaje;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    public List<Competencia> getCapacidades() {
        return capacidades;
    }

    public void setCapacidades(List<Competencia> capacidades) {
        this.capacidades = capacidades;
    }

    public List<DesempenioIcd> getDesempenioIcd() {
        return desempenioIcd;
    }

    public void setDesempenioIcd(List<DesempenioIcd> desempenioIcd) {
        this.desempenioIcd = desempenioIcd;
    }

    public List<Icds> getIcd() {
        return icd;
    }

    public void setIcd(List<Icds> icd) {
        this.icd = icd;
    }

    public List<CampoTematico> getCampoTematico() {
        return campoTematico;
    }

    public void setCampoTematico(List<CampoTematico> campoTematico) {
        this.campoTematico = campoTematico;
    }

    public List<RecursoDidacticoEventoC> getRecursoDidacticoEvento() {
        return recursoDidacticoEvento;
    }

    public void setRecursoDidacticoEvento(List<RecursoDidacticoEventoC> recursoDidacticoEvento) {
        this.recursoDidacticoEvento = recursoDidacticoEvento;
    }

    public List<ActividadAprendizaje> getActividadEvento() {
        return actividadEvento;
    }

    public void setActividadEvento(List<ActividadAprendizaje> actividadEvento) {
        this.actividadEvento = actividadEvento;
    }

    public List<TareasC> getTareaEvento() {
        return tareaEvento;
    }

    public void setTareaEvento(List<TareasC> tareaEvento) {
        this.tareaEvento = tareaEvento;
    }

    public List<TareasRecursosC> getTareaEventoRecurso() {
        return tareaEventoRecurso;
    }

    public void setTareaEventoRecurso(List<TareasRecursosC> tareaEventoRecurso) {
        this.tareaEventoRecurso = tareaEventoRecurso;
    }

    public List<CompetenciaUnidad> getCompetenciaUnidad() {
        return competenciaUnidad;
    }

    public void setCompetenciaUnidad(List<CompetenciaUnidad> competenciaUnidad) {
        this.competenciaUnidad = competenciaUnidad;
    }

    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> getCompetenciaUnidadDesempenioIcd() {
        return competenciaUnidadDesempenioIcd;
    }

    public void setCompetenciaUnidadDesempenioIcd(List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD> competenciaUnidadDesempenioIcd) {
        this.competenciaUnidadDesempenioIcd = competenciaUnidadDesempenioIcd;
    }

    public List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> getCompetenciaUnidadDesempenioIcdCampoTematico() {
        return competenciaUnidadDesempenioIcdCampoTematico;
    }

    public void setCompetenciaUnidadDesempenioIcdCampoTematico(List<T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO> competenciaUnidadDesempenioIcdCampoTematico) {
        this.competenciaUnidadDesempenioIcdCampoTematico = competenciaUnidadDesempenioIcdCampoTematico;
    }

    public List<T_GC_REL_COMPETENCIA_SESION_EVENTO> getCompetenciaSesion() {
        return competenciaSesion;
    }

    public void setCompetenciaSesion(List<T_GC_REL_COMPETENCIA_SESION_EVENTO> competenciaSesion) {
        this.competenciaSesion = competenciaSesion;
    }

    public List<SesionEventoCompetenciaDesempenioIcd> getCompetenciaSesionDesempenioIcd() {
        return competenciaSesionDesempenioIcd;
    }

    public void setCompetenciaSesionDesempenioIcd(List<SesionEventoCompetenciaDesempenioIcd> competenciaSesionDesempenioIcd) {
        this.competenciaSesionDesempenioIcd = competenciaSesionDesempenioIcd;
    }

    public List<SesionEventoDesempenioIcdCampotematico> getCompetenciaSesionDesempenioIcdCampoTematico() {
        return competenciaSesionDesempenioIcdCampoTematico;
    }

    public void setCompetenciaSesionDesempenioIcdCampoTematico(List<SesionEventoDesempenioIcdCampotematico> competenciaSesionDesempenioIcdCampoTematico) {
        this.competenciaSesionDesempenioIcdCampoTematico = competenciaSesionDesempenioIcdCampoTematico;
    }

    public List<UnidadAprendizaje> getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public void setUnidadAprendizaje(List<UnidadAprendizaje> unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public List<RecursoArchivo> getRecursoArchivo() {
        return recursoArchivo;
    }

    public void setRecursoArchivo(List<RecursoArchivo> recursoArchivo) {
        this.recursoArchivo = recursoArchivo;
    }

    public List<RecursoReferenciaC> getRecursoEventoReferencia() {
        return recursoEventoReferencia;
    }

    public void setRecursoEventoReferencia(List<RecursoReferenciaC> recursoEventoReferencia) {
        this.recursoEventoReferencia = recursoEventoReferencia;
    }
}
