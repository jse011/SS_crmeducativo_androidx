package com.consultoraestrategia.ss_crmeducativo.comentarios.entidades;


public class ComentarioUi {

    private String id;
    private int usuarioid;
    private String contenido;
    private String url;
    private String nombre;
    private Long FechaCreacion;
    private TipoUi tipoUi;

    public TipoUi getTipoUi() {
        return tipoUi;
    }

    public void setTipoUi(TipoUi tipoUi) {
        this.tipoUi = tipoUi;
    }

    public ComentarioUi() {
    }

    public ComentarioUi(int usuarioid, String contenido) {
        this.usuarioid = usuarioid;
        this.contenido = contenido;
    }

    public Long getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Long fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "ComentarioUi{" +
                "id='" + id + '\'' +
                ", usuarioid=" + usuarioid +
                ", contenido='" + contenido + '\'' +
                ", url='" + url + '\'' +
                ", nombre='" + nombre + '\'' +
                ", FechaCreacion='" + FechaCreacion + '\'' +
                ", tipoUi=" + tipoUi +
                '}';
    }
}
