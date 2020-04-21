package com.consultoraestrategia.ss_crmeducativo.main.entities;

public class AnioAcademicoUi {
    private int anioAcademicoId;
    private String nombre;
    private boolean vigente;
    private boolean toogle;
    private int georeferencia;

    public int getAnioAcademicoId() {
        return anioAcademicoId;
    }

    public void setAnioAcademicoId(int anioAcademicoId) {
        this.anioAcademicoId = anioAcademicoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public boolean isToogle() {
        return toogle;
    }

    public void setToogle(boolean toogle) {
        this.toogle = toogle;
    }

    @Override
    public String toString() {
        return nombre ;
    }

    public void setGeoreferencia(int georeferencia) {
        this.georeferencia = georeferencia;
    }

    public int getGeoreferencia() {
        return georeferencia;
    }
}
