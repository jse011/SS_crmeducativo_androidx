package com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites;

public class SalaUi {
    private String nombre;
    private String alias;
    private String descripcion;
    private String color;
    private TipoSalaEnum tipoSala;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setTipoSala(TipoSalaEnum tipoSala) {
        this.tipoSala = tipoSala;
    }

    public TipoSalaEnum getTipoSala() {
        return tipoSala;
    }
}
