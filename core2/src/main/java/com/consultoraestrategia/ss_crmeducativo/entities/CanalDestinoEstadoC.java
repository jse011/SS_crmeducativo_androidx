package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by irvinmarin on 17/08/2017.
 */
@Table(database = AppDatabase.class)
public class CanalDestinoEstadoC extends BaseEntity {
    @Column
    private String canalComDestinoEstadoId;
    @Column
    private int canalComId;
    @Column
    private String mensajeUsuarioId;
    @Column
    private int estadoId;
    @Column
    private String fechaRecibido;
    @Column
    private String fechaVisto;
    @Column
    private String fechaRespuesta;
    @Column
    private int estadoExportado;


    public CanalDestinoEstadoC() {
    }

    public CanalDestinoEstadoC(int canalComId, String mensajeUsuarioId, int estadoId, String fechaRecibido, String fechaVisto, String fechaRespuesta) {
        this.canalComId = canalComId;
        this.mensajeUsuarioId = mensajeUsuarioId;
        this.estadoId = estadoId;
        this.fechaRecibido = fechaRecibido;
        this.fechaVisto = fechaVisto;
        this.fechaRespuesta = fechaRespuesta;

    }


    public String getCanalComDestinoEstadoId() {
        return canalComDestinoEstadoId;
    }

    public void setCanalComDestinoEstadoId(String canalComDestinoEstadoId) {
        this.canalComDestinoEstadoId = canalComDestinoEstadoId;
    }

    public int getEstadoExportado() {
        return estadoExportado;
    }

    public void setEstadoExportado(int estadoExportado) {
        this.estadoExportado = estadoExportado;
    }



    public int getCanalComId() {
        return canalComId;
    }

    public void setCanalComId(int canalComId) {
        this.canalComId = canalComId;
    }

    public String getMensajeUsuarioId() {
        return mensajeUsuarioId;
    }

    public void setMensajeUsuarioId(String mensajeUsuarioId) {
        this.mensajeUsuarioId = mensajeUsuarioId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(String fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public String getFechaVisto() {
        return fechaVisto;
    }

    public void setFechaVisto(String fechaVisto) {
        this.fechaVisto = fechaVisto;
    }

    public String getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(String fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }
}
