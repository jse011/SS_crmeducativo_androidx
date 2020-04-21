package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class ArchivoAsistencia extends BaseEntity {
    public static final int TIPO_VIDEO = 379, TIPO_VINCULO = 380, TIPO_DOCUMENTO = 397, TIPO_IMAGEN = 398, TIPO_AUDIO = 399, TIPO_HOJA_CALCULO = 400, TIPO_DIAPOSITIVA = 401, TIPO_PDF = 402;
    @Column
    private String archivoAsistenciaId;
    @Column
    private String path;
    @Column
    private String nombre;
    @Column
    private int tipoId;
    @Column
    private String justificacionId;
    @Column
    private String localpath;

    public String getArchivoAsistenciaId() {
        return archivoAsistenciaId;
    }

    public void setArchivoAsistenciaId(String archivoAsistenciaId) {
        this.archivoAsistenciaId = archivoAsistenciaId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public String getJustificacionId() {
        return justificacionId;
    }

    public void setJustificacionId(String justificacionId) {
        this.justificacionId = justificacionId;
    }

    public String getLocalpath() {
        return localpath;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }
}
