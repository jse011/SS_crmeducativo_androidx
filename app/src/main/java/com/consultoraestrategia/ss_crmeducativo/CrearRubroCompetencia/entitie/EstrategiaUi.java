package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

public class EstrategiaUi {
    private int estrategiaId;
    private String estrategia;

    public int getEstrategiaId() {
        return estrategiaId;
    }

    public void setEstrategiaId(int estrategiaId) {
        this.estrategiaId = estrategiaId;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
    }


    @Override
    public String toString() {
        return estrategia+"";
    }
}
