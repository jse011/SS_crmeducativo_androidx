package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class ArchivosRubroProceso extends BaseEntity {


    public static final int TIPO_IMAGEN = 554;
    public static final int TIPO_VIDEO = 555;
    @Column
    private String archivoRubroId;
    @Column
    private String url;
    @Column
    private int tipoArchivoId;
    @Column
    private String evaluacionProcesoId;
    @Column
    private String localpath;
    @Column
    private int delete;

    public ArchivosRubroProceso() { }

    public String getArchivoRubroId() {
        return archivoRubroId;
    }

    public void setArchivoRubroId(String archivoRubroId) {
        this.archivoRubroId = archivoRubroId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTipoArchivoId() {
        return tipoArchivoId;
    }

    public void setTipoArchivoId(int tipoArchivoId) {
        this.tipoArchivoId = tipoArchivoId;
    }

    public String getEvaluacionProcesoId() {
        return evaluacionProcesoId;
    }

    public void setEvaluacionProcesoId(String evaluacionProcesoId) {
        this.evaluacionProcesoId = evaluacionProcesoId;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }

    public String getLocalpath() {
        return localpath;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getDelete() {
        return delete;
    }
}
