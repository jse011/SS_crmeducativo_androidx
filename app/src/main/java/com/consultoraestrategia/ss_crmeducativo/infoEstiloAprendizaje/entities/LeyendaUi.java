package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities;

public class LeyendaUi {

    private String nombre;
    private String color;

    public LeyendaUi() {
    }

    public LeyendaUi(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
