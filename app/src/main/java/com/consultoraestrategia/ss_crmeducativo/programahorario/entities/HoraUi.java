package com.consultoraestrategia.ss_crmeducativo.programahorario.entities;

import java.util.Objects;

public class HoraUi {
    private String etiqueta;
    private String desde;
    private String hasta;

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoraUi)) return false;

        HoraUi horaUi = (HoraUi) o;

        if (desde != null ? !desde.equals(horaUi.desde) : horaUi.desde != null) return false;
        return hasta != null ? hasta.equals(horaUi.hasta) : horaUi.hasta == null;
    }

    @Override
    public int hashCode() {
        int result = desde != null ? desde.hashCode() : 0;
        result = 31 * result + (hasta != null ? hasta.hashCode() : 0);
        return result;
    }
}
