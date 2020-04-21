package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities;

/**
 * Created by SCIEV on 16/07/2018.
 */

public enum Estilo{
    BLANCO("#90A4AE"),AZUL("#1976d2"),ANARANJADO("#FF6D00"),ROJO("#D32F2F"),VERDE("#388e3c");
    private String color;
    Estilo(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
