package com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities;

public enum TipoCompetencia {

    BASE("Competencia Base"), OTROS("Competencia Transversal/ Enfoque Transversal");

private String nombre;


    TipoCompetencia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
