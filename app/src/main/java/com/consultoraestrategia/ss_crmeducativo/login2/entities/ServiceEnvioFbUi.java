package com.consultoraestrategia.ss_crmeducativo.login2.entities;

import java.util.List;

public class ServiceEnvioFbUi extends ServiceEnvioUi {

    private long fechaModificacion;
    private String key;
    private int sesionAprendizajeId;
    private String tareaId;
    private String rubroEvaluacionId;
    private int sesionAprendizajeDocenteId;
    private List<Detalle> detalles;
    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }
    public String nombreSesionDocenteId;

    public String getTareaId() {
        return tareaId;
    }

    public void setRubroEvaluacionId(String rubroEvaluacionId) {
        this.rubroEvaluacionId = rubroEvaluacionId;
    }

    public String getRubroEvaluacionId() {
        return rubroEvaluacionId;
    }

    public void setSesionAprendizajeId(int sesionAprendizajeId) {
        this.sesionAprendizajeId = sesionAprendizajeId;
    }

    public int getSesionAprendizajeId() {
        return sesionAprendizajeId;
    }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceEnvioFbUi that = (ServiceEnvioFbUi) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    public void setSesionAprendizajeDocenteId(int sesionAprendizajeDocenteId) {
        this.sesionAprendizajeDocenteId = sesionAprendizajeDocenteId;
    }

    public int getSesionAprendizajeDocenteId() {
        return sesionAprendizajeDocenteId;
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalle> detalles) {
        this.detalles = detalles;
    }

    public String getNombreSesionDocenteId() {
        return nombreSesionDocenteId;
    }

    public void setNombreSesionDocenteId(String nombreSesionDocenteId) {
        this.nombreSesionDocenteId = nombreSesionDocenteId;
    }

    public static class Detalle{
        private int instrumentoEvalId;
        private String nombre;
        private String rubroEvalProcesoId;
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

        public String getPreguntaPortalAlumnoId() {
            return preguntaPortalAlumnoId;
        }

        public void setPreguntaPortalAlumnoId(String preguntaPortalAlumnoId) {
            this.preguntaPortalAlumnoId = preguntaPortalAlumnoId;
        }
    }
}
