package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades;

import java.util.List;

/**
 * Created by kike on 11/04/2018.
 */

public class PadreFinalBimestreUi {
    private String contador;
    private String nombre;
    private List<HijosFinalBimestreUi> HijosBimestreList;
    private String color;

    public PadreFinalBimestreUi() {
    }

    public PadreFinalBimestreUi(String contador, String nombre) {
        this.contador = contador;
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<HijosFinalBimestreUi> getHijosBimestreList() {
        return HijosBimestreList;
    }

    public void setHijosBimestreList(List<HijosFinalBimestreUi> hijosBimestreList) {
        HijosBimestreList = hijosBimestreList;
    }
}
