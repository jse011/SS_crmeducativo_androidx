package com.consultoraestrategia.ss_crmeducativo.stiker2.entities;

import android.text.TextUtils;

import com.aghajari.emojiview.sticker.Sticker;

public class StikersUi extends Sticker{
    private static final long serialVersionUID = 3L;
    private String tipo;

    public StikersUi(Object data) {
        super(data);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
