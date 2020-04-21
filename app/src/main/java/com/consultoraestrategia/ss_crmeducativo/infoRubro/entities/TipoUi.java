package com.consultoraestrategia.ss_crmeducativo.infoRubro.entities;



public class TipoUi {
    public enum Tipo {SELECTOR_NUMERICO, SELECTOR_VALORES, SELECTOR_ICONOS, VALOR_NUMERICO}

    private Tipo tipo;

    public TipoUi() {
        tipo = Tipo.VALOR_NUMERICO;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
