package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities;


import java.io.Serializable;

/**
 * Created by SCIEV on 26/02/2018.
 */

@SuppressWarnings("serial")
public class TipoUi   implements Serializable{
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
