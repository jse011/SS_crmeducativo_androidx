package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

/**
 * Created by SCIEV on 14/02/2018.
 */

public class FormaEvaluacionUi {
    private int Id;
    private String nombre;

    public FormaEvaluacionUi() {
    }

    public FormaEvaluacionUi(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormaEvaluacionUi that = (FormaEvaluacionUi) o;

        return Id == that.Id;
    }

    @Override
    public int hashCode() {
        return Id;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
