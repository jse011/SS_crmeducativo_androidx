package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by irvinmarin on 23/06/2017.
 */
@Table(database = AppDatabase.class)
public class ColorCondicionalC extends BaseEntity {
    @Column
    private String colorCondicionalId;
    @Column
    private int desde;
    @Column
    private int hasta;
    @Column
    private boolean incluidoDesde;
    @Column
    private boolean incluidoHasta;
    @Column
    private String colorTexto;
    @Column
    private String colorFondo;
    @Column
    private int rubroEvalResultadoId;
    @Column
    private String rubroEvalProcesoId;

    public ColorCondicionalC() {
        super();
    }

    public ColorCondicionalC(int androidId) {
        super(androidId);
    }

    public String getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(String colorFondo) {
        this.colorFondo = colorFondo;
    }

    public int getRubroEvalResultadoId() {
        return rubroEvalResultadoId;
    }

    public void setRubroEvalResultadoId(int rubroEvalResultadoId) {
        this.rubroEvalResultadoId = rubroEvalResultadoId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public String getColorCondicionalId() {
        return colorCondicionalId;
    }

    public void setColorCondicionalId(String colorCondicionalId) {
        this.colorCondicionalId = colorCondicionalId;
    }


    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public boolean isIncluidoDesde() {
        return incluidoDesde;
    }

    public void setIncluidoDesde(boolean incluidoDesde) {
        this.incluidoDesde = incluidoDesde;
    }

    public boolean isIncluidoHasta() {
        return incluidoHasta;
    }

    public void setIncluidoHasta(boolean incluidoHasta) {
        this.incluidoHasta = incluidoHasta;
    }

    public String getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(String colorTexto) {
        this.colorTexto = colorTexto;
    }


}
