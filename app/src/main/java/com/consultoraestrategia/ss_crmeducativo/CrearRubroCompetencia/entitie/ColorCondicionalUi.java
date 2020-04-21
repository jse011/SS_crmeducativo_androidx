package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

import android.graphics.Color;

import org.parceler.Parcel;

/**
 * Created by SCIEV on 6/11/2017.
 */
@Parcel
public class ColorCondicionalUi {
    public int id;
    public boolean selectDesde;
    public int desde;
    public boolean selectHasta;
    public int hasta;
    public int ColorTexto;
    public int ColorFondo;

    public ColorCondicionalUi() {
        ColorTexto = Color.BLACK;
        ColorFondo = Color.TRANSPARENT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelectDesde() {
        return selectDesde;
    }

    public void setSelectDesde(boolean selectDesde) {
        this.selectDesde = selectDesde;
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public boolean isSelectHasta() {
        return selectHasta;
    }

    public void setSelectHasta(boolean selectHasta) {
        this.selectHasta = selectHasta;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public int getColorTexto() {
        return ColorTexto;
    }

    public void setColorTexto(int colorTexto) {
        ColorTexto = colorTexto;
    }

    public int getColorFondo() {
        return ColorFondo;
    }

    public void setColorFondo(int colorFondo) {
        ColorFondo = colorFondo;
    }
}

