package com.consultoraestrategia.ss_crmeducativo.infoRubro.entities;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class CapacidadUi {
    private int id;
    private String titulo;
    private CompetenciaUi competenciaUi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CompetenciaUi getCompetenciaUi() {
        return competenciaUi;
    }

    public void setCompetenciaUi(CompetenciaUi competenciaUi) {
        this.competenciaUi = competenciaUi;
    }
}
