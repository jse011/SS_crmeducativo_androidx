package com.consultoraestrategia.ss_crmeducativo.main.entities;

/**
 * Created by irvinmarin on 30/10/2017.
 */

public class HijoUI {
    private int idHijo;
    private String nombres;

    public HijoUI(int idHijo, String nombres) {
        this.idHijo = idHijo;
        this.nombres = nombres;
    }

    public int getIdHijo() {
        return idHijo;
    }

    public void setIdHijo(int idHijo) {
        this.idHijo = idHijo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Override
    public String toString() {
        return "HijoUI{" +
                "idHijo=" + idHijo +
                ", nombres='" + nombres + '\'' +
                '}';
    }
}
