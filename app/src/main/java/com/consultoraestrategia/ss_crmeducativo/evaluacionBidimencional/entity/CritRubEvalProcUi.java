package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity;

import org.parceler.Parcel;

/**
 * Created by @stevecampos on 23/02/2018.
 */
public class CritRubEvalProcUi {
    private int id;
    private int rubEvalProcId;
    private int valorTipoNotaId;
    private String desc;

    public CritRubEvalProcUi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRubEvalProcId() {
        return rubEvalProcId;
    }

    public void setRubEvalProcId(int rubEvalProcId) {
        this.rubEvalProcId = rubEvalProcId;
    }

    public int getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setValorTipoNotaId(int valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }
}
