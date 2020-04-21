package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by irvinmarin on 17/08/2017.
 */
@Table(database = AppDatabase.class)
public class MensajeIntencionItemC extends BaseEntity {

    @Column
    private String mensajeIntencionItemId;
    @Column
    private String mensajeId;
    @Column
    private int intencionItemId;
    @Column
    private int estadoExportado;


    public MensajeIntencionItemC() {
    }




    public String getMensajeIntencionItemId() {
        return mensajeIntencionItemId;
    }

    public void setMensajeIntencionItemId(String mensajeIntencionItemId) {
        this.mensajeIntencionItemId = mensajeIntencionItemId;
    }

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public int getIntencionItemId() {
        return intencionItemId;
    }

    public void setIntencionItemId(int intencionItemId) {
        this.intencionItemId = intencionItemId;
    }

    public int getEstadoExportado() {
        return estadoExportado;
    }

    public void setEstadoExportado(int estadoExportado) {
        this.estadoExportado = estadoExportado;
    }
}
