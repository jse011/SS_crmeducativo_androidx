package com.consultoraestrategia.ss_crmeducativo.infoRubro.entities;

/**
 * Created by SCIEV on 28/08/2018.
 */

public class CompetenciaUi {
    public enum Tipo{
        COMPETENCIA_BASE, COMPETENCIA_TRANS, COMPETENCIA_ENFQ
    }

    private int id;
    private String titulo;
    private Tipo tipo = Tipo.COMPETENCIA_BASE;

    public CompetenciaUi() {
    }

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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
