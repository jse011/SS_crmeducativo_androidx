package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie;

/**
 * Created by SCIEV on 12/10/2017.
 */

public class IntervaloUi {
    private int Id;
    private int Desde;
    private int Hasta;
    private String ColorTexto;
    private String ColorFondo;

    public IntervaloUi() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getDesde() {
        return Desde;
    }

    public void setDesde(int desde) {
        Desde = desde;
    }

    public int getHasta() {
        return Hasta;
    }

    public void setHasta(int hasta) {
        Hasta = hasta;
    }

    public String getColorTexto() {
        return ColorTexto;
    }

    public void setColorTexto(String colorTexto) {
        ColorTexto = colorTexto;
    }

    public String getColorFondo() {
        return ColorFondo;
    }

    public void setColorFondo(String colorFondo) {
        ColorFondo = colorFondo;
    }
}
