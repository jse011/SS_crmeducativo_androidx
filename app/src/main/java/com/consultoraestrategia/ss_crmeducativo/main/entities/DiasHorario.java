package com.consultoraestrategia.ss_crmeducativo.main.entities;

/**
 * Created by irvinmarin on 20/03/2018.
 */

public class DiasHorario {
    private String nombreDia;
    private String hora;

    public DiasHorario(String nombreDia, String hora) {
        this.nombreDia = nombreDia;
        this.hora = hora;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return nombreDia + " " + hora;
    }
}
