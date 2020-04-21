package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class CasoReporte extends BaseEntity {
    @Column
    private String casoReporteId;
    @Column
    private String casoId;
    @Column
    private int usuarioDestinoId;
    @Column
    private int organigramaId;

    public String getCasoReporteId() {
        return casoReporteId;
    }

    public void setCasoReporteId(String casoReporteId) {
        this.casoReporteId = casoReporteId;
    }

    public String getCasoId() {
        return casoId;
    }

    public void setCasoId(String casoId) {
        this.casoId = casoId;
    }

    public int getUsuarioDestinoId() {
        return usuarioDestinoId;
    }

    public void setUsuarioDestinoId(int usuarioDestinoId) {
        this.usuarioDestinoId = usuarioDestinoId;
    }

    public int getOrganigramaId() {
        return organigramaId;
    }

    public void setOrganigramaId(int organigramaId) {
        this.organigramaId = organigramaId;
    }
}
