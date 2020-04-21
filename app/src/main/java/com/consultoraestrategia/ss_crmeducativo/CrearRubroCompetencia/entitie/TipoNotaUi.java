package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class TipoNotaUi implements Serializable {
    public enum TipoNota{IMAGEN,TEXTO,DEFECTO,VALOR_NUMERICO, SELECTOR_NUMERICO};

    String id;
    String nombre;
    int tipoId;
    List<ValorTipoNotaUi> valorTipoNotaUis;
    TipoNota tipoNota;
    EscalaEvaluacionUi escalaEvaluacionUi;
    public TipoNotaUi() {
        valorTipoNotaUis = new ArrayList<>();
        tipoNota = TipoNota.DEFECTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public List<ValorTipoNotaUi> getValorTipoNotaUis() {
        return valorTipoNotaUis;
    }

    public void setValorTipoNotaUis(List<ValorTipoNotaUi> valorTipoNotaUis) {
        this.valorTipoNotaUis = valorTipoNotaUis;
    }

    public TipoNota getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(TipoNota tipoNota) {
        this.tipoNota = tipoNota;
    }

    public EscalaEvaluacionUi getEscalaEvaluacionUi() {
        return escalaEvaluacionUi;
    }

    public void setEscalaEvaluacionUi(EscalaEvaluacionUi escalaEvaluacionUi) {
        this.escalaEvaluacionUi = escalaEvaluacionUi;
    }
}
