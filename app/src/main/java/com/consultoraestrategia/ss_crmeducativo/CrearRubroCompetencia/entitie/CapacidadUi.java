package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

public class CapacidadUi {
    private int id;
    private String nombre;
    private CompetenciaUi competencia;

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

    public void setCompetencia(CompetenciaUi competencia) {
        this.competencia = competencia;
    }

    public CompetenciaUi getCompetencia() {
        return competencia;
    }
}
