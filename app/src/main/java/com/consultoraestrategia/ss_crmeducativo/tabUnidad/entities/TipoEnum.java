package com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities;

public enum  TipoEnum {
    COMPETENCIA_BASE("Competencia Base", 347),
    COMPETENCIA_TRASVERSAL("Competencia Transversal", 348);
    public String nombre;
    public int id;

    TipoEnum(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
