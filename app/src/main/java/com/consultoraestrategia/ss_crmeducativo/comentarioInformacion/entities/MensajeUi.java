package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities;

public class MensajeUi {
    private String evaluacionId;

    public void setEvaluacionId(String evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public String getEvaluacionId() {
        return evaluacionId;
    }

    public enum TipoMensaje {NORMAL, ENTIDAD, PREDETERMINADO }
    private String id;
    private TipoMensaje tipoMensaje = TipoMensaje.PREDETERMINADO;
    private String descripcion;
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


}
