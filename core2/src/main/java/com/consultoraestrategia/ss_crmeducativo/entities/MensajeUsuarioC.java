package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.List;

/**
 * Created by irvinmarin on 17/08/2017.
 */
@Table(database = AppDatabase.class)
public class MensajeUsuarioC extends BaseEntity {

    @Column
    private String mensajeUsuarioId;
    @Column
    private int usuarioDestinoId;
    @Column
    private String mensajeId;
    @Column
    private int listaUsuarioId;
    @Column
    private int estadoMensajeId;
    @Column
    private int estadoRespuestaId;
    @Column
    private String fechaRecibido;
    @Column
    private String fechaVisto;
    @Column
    private String fechaRespuesta;
    @Column
    private boolean importante;
    @Column
    private boolean destacado;
    @Column
    private int estadoExportado;
    @Column
    private String idChatMessage;

    private List<CanalDestinoEstadoC> CanalDestEstado;

    public MensajeUsuarioC() {
    }



    public String getMensajeUsuarioId() {
        return mensajeUsuarioId;
    }

    public void setMensajeUsuarioId(String mensajeUsuarioId) {
        this.mensajeUsuarioId = mensajeUsuarioId;
    }

    public int getUsuarioDestinoId() {
        return usuarioDestinoId;
    }

    public void setUsuarioDestinoId(int usuarioDestinoId) {
        this.usuarioDestinoId = usuarioDestinoId;
    }

    public String getMensajeId() {
        return mensajeId;
    }

    public void setMensajeId(String mensajeId) {
        this.mensajeId = mensajeId;
    }

    public int getListaUsuarioId() {
        return listaUsuarioId;
    }

    public void setListaUsuarioId(int listaUsuarioId) {
        this.listaUsuarioId = listaUsuarioId;
    }

    public int getEstadoMensajeId() {
        return estadoMensajeId;
    }

    public void setEstadoMensajeId(int estadoMensajeId) {
        this.estadoMensajeId = estadoMensajeId;
    }

    public int getEstadoRespuestaId() {
        return estadoRespuestaId;
    }

    public void setEstadoRespuestaId(int estadoRespuestaId) {
        this.estadoRespuestaId = estadoRespuestaId;
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

    public boolean isImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }

    public boolean isDestacado() {
        return destacado;
    }

    public void setDestacado(boolean destacado) {
        this.destacado = destacado;
    }

    public int getEstadoExportado() {
        return estadoExportado;
    }

    public void setEstadoExportado(int estadoExportado) {
        this.estadoExportado = estadoExportado;
    }

    public String getIdChatMessage() {
        return idChatMessage;
    }

    public void setIdChatMessage(String idChatMessage) {
        this.idChatMessage = idChatMessage;
    }

    public List<CanalDestinoEstadoC> getCanalDestEstado() {
        return CanalDestEstado;
    }

    public void setCanalDestEstado(List<CanalDestinoEstadoC> canalDestEstado) {
        CanalDestEstado = canalDestEstado;
    }
}
