package com.consultoraestrategia.ss_crmeducativo.infoRubro.entities;





public class ValorTipoNotaUi {
    private String id;
    private TipoNotaUi tipoNotaUi;
    private String icono;
    private String titulo;
    private String alias;
    private String detalle;

    public ValorTipoNotaUi(String id, TipoNotaUi tipoNotaUi) {
        this.id = id;
        this.tipoNotaUi = tipoNotaUi;
    }

    public ValorTipoNotaUi() {
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoNotaUi getTipoNotaUi() {
        return tipoNotaUi;
    }

    public void setTipoNotaUi(TipoNotaUi tipoNotaUi) {
        this.tipoNotaUi = tipoNotaUi;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "ValorTipoNotaUi{" +
                "id='" + id + '\'' +
                ", tipoNotaUi=" + tipoNotaUi +
                ", icono='" + icono + '\'' +
                ", titulo='" + titulo + '\'' +
                ", alias='" + alias + '\'' +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
