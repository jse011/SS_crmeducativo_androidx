package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by irvinmarin on 22/05/2017.
 */
@Table(database = AppDatabase.class)
public class JustificacionC extends BaseEntity {

    @Column
    private String justificacionId;
    @Column
    private String asistenciaId;
    @Column
    private String descripcion;
    @Column
    private int tipoJustificacionId;
    @Column
    private int usuarioCreador;
    @Column
    private int estadoExportado;
    @Column
    public int tipoIngresoId;
    @Column
    public String asistenciaSesionId;
    @Column
    public String asistenciaDocenteId;



    public JustificacionC() {
    }

    public JustificacionC(String justificacionId, String asistenciaId, String descripcion, int tipoJustificacionId, int usuarioCreador, int estadoExportado) {
        this.justificacionId = justificacionId;
        this.asistenciaId = asistenciaId;
        this.descripcion = descripcion;
        this.tipoJustificacionId = tipoJustificacionId;
        this.usuarioCreador = usuarioCreador;
        this.estadoExportado = estadoExportado;
    }

    public String getJustificacionId() {
        return justificacionId;
    }

    public void setJustificacionId(String justificacionId) {
        this.justificacionId = justificacionId;
    }

    public String getAsistenciaId() {
        return asistenciaId;
    }

    public void setAsistenciaId(String asistenciaId) {
        this.asistenciaId = asistenciaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoJustificacionId() {
        return tipoJustificacionId;
    }

    public void setTipoJustificacionId(int tipoJustificacionId) {
        this.tipoJustificacionId = tipoJustificacionId;
    }

    public int getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(int usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public int getEstadoExportado() {
        return estadoExportado;
    }

    public void setEstadoExportado(int estadoExportado) {
        this.estadoExportado = estadoExportado;
    }

    public int getTipoIngresoId() {
        return tipoIngresoId;
    }

    public void setTipoIngresoId(int tipoIngresoId) {
        this.tipoIngresoId = tipoIngresoId;
    }

    public String getAsistenciaSesionId() {
        return asistenciaSesionId;
    }

    public void setAsistenciaSesionId(String asistenciaSesionId) {
        this.asistenciaSesionId = asistenciaSesionId;
    }

    public String getAsistenciaDocenteId() {
        return asistenciaDocenteId;
    }

    public void setAsistenciaDocenteId(String asistenciaDocenteId) {
        this.asistenciaDocenteId = asistenciaDocenteId;
    }
}
