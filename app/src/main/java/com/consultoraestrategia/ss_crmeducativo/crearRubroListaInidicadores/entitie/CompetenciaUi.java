package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie;

import java.util.List;

/**
 * Created by SCIEV on 16/04/2018.
 */

public class CompetenciaUi {
    public enum Tipo {COMPETENCIA_BASE, COMPETENCIA_TRANS, COMPETENCIA_ENFQ}

    private String id;
    private String titulo;
    private CapacidadUi capacidadUi;
    private List<Object> items;
    private Tipo tipo = Tipo.COMPETENCIA_BASE;

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

    public CapacidadUi getCapacidadUi() {
        return capacidadUi;
    }

    public void setCapacidadUi(CapacidadUi capacidadUi) {
        this.capacidadUi = capacidadUi;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
