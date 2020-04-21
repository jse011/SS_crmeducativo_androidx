package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

public class MensajeUi {
    public enum TipoMensaje {NORMAL, ENTIDAD, PREDETERMINADO }
    private String id;
    private TipoMensaje tipoMensaje = TipoMensaje.PREDETERMINADO;
    private String descripcion;
    private TipoComentarioUi tipoComentarioUi;
    private int alumnoId;
    private String rubroEvaluacionId;
    private long fechaCreacion;

    public long getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(long fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(TipoMensaje tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoComentarioUi getTipoComentarioUi() {
        return tipoComentarioUi;
    }

    public void setTipoComentarioUi(TipoComentarioUi tipoComentarioUi) {
        this.tipoComentarioUi = tipoComentarioUi;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public String getRubroEvaluacionId() {
        return rubroEvaluacionId;
    }

    public void setRubroEvaluacionId(String rubroEvaluacionId) {
        this.rubroEvaluacionId = rubroEvaluacionId;
    }

    @Override
    public String toString() {
        return "MensajeUi{" +
                "id='" + id + '\'' +
                ", tipoMensaje=" + tipoMensaje +
                ", descripcion='" + descripcion + '\'' +
                ", tipoComentarioUi=" + tipoComentarioUi +
                ", alumnoId=" + alumnoId +
                ", rubroEvaluacionId='" + rubroEvaluacionId + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
