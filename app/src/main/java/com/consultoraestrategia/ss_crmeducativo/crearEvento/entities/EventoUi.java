package com.consultoraestrategia.ss_crmeducativo.crearEvento.entities;

public class EventoUi {
    private String id;
    private String nombre;
    private String descripcion;
    private long fecha;
    private String hora;
    private String calendarioId;
    private String nombreCalendario;
    private int tipoEventoId;
    private String nombreTipoEvento;
    private String foto;
    private int entidadId;
    private int georeferencia;
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCalendarioId() {
        return calendarioId;
    }

    public void setCalendarioId(String calendarioId) {
        this.calendarioId = calendarioId;
    }

    public int getTipoEventoId() {
        return tipoEventoId;
    }

    public void setTipoEventoId(int tipoEventoId) {
        this.tipoEventoId = tipoEventoId;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public int getGeoreferencia() {
        return georeferencia;
    }

    public void setGeoreferencia(int georeferencia) {
        this.georeferencia = georeferencia;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
