package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie;

/**
 * Created by SCIEV on 16/04/2018.
 */

public class CapacidadUi {

    private String id;
    private String titulo;
    private CompetenciaUi competenciaUi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
