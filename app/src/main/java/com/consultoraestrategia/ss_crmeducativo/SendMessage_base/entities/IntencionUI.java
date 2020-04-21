package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities;

/**
 * Created by irvinmarin on 18/12/2017.
 */

public class IntencionUI {
    private int id;
    private String nombre;

    public IntencionUI() {
    }

    public IntencionUI(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String toStringClass() {

        return this.toString();
    }
}
