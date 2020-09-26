package com.consultoraestrategia.ss_crmeducativo.entities.retrofit;

import java.util.ArrayList;
import java.util.List;

public class BECambiosMovilFb {
    private long fechaModificacion;
    private String key;
    private int sesionAprendizajeId;
    private String tareaId;
    private String rubroEvaluacionId;
    private String nombre;
    private String tipo;
    private List<Detalle> detalles = new ArrayList<>();
    private int sesionAprendizajeDocenteId;
    private int silaboEventoId;
    private int cargaCursoId;
    private int rubroFormal;
    private int calendarioPeriodoId;

    public long getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(long fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getRubroEvaluacionId() {
        return rubroEvaluacionId;
    }

    public void setRubroEvaluacionId(String rubroEvaluacionId) {
        this.rubroEvaluacionId = rubroEvaluacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public int getSesionAprendizajeDocenteId() {
        return sesionAprendizajeDocenteId;
    }

    public void setSesionAprendizajeDocenteId(int sesionAprendizajeDocenteId) {
        this.sesionAprendizajeDocenteId = sesionAprendizajeDocenteId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public void setSilaboEventoId(int silaboEventoId) {
        this.silaboEventoId = silaboEventoId;
    }

    public int getCargaCursoId() {
        return cargaCursoId;
    }

    public void setCargaCursoId(int cargaCursoId) {
        this.cargaCursoId = cargaCursoId;
    }

    public int getRubroFormal() {
        return rubroFormal;
    }

    public void setRubroFormal(int rubroFormal) {
        this.rubroFormal = rubroFormal;
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public void setCalendarioPeriodoId(int calendarioPeriodoId) {
        this.calendarioPeriodoId = calendarioPeriodoId;
    }

    public static class Detalle {
        private int instrumentoEvalId;
        private String nombre;
        private String rubroEvalProcesoId;
        private String pregunta;
        private String preguntaPortalAlumnoId;

        public int getInstrumentoEvalId() {
            return instrumentoEvalId;
        }

        public void setInstrumentoEvalId(int instrumentoEvalId) {
            this.instrumentoEvalId = instrumentoEvalId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getRubroEvalProcesoId() {
            return rubroEvalProcesoId;
        }

        public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
            this.rubroEvalProcesoId = rubroEvalProcesoId;
        }

        public String getPregunta() {
            return pregunta;
        }

        public void setPregunta(String pregunta) {
            this.pregunta = pregunta;
        }

        public String getPreguntaPortalAlumnoId() {
            return preguntaPortalAlumnoId;
        }

        public void setPreguntaPortalAlumnoId(String preguntaPortalAlumnoId) {
            this.preguntaPortalAlumnoId = preguntaPortalAlumnoId;
        }
    }
}
