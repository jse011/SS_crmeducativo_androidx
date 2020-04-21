package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class CasoArchivo extends BaseEntity{
    public static final int TIPO_VIDEO = 534, TIPO_DOCUMENTO = 535, TIPO_IMAGEN = 533, TIPO_AUDIO = 536, TIPO_HOJA_CALCULO = 538, TIPO_DIAPOSITIVA = 539, TIPO_PDF = 540;
    @Column
    private String archivoCasoId;//archivoCasoID
    @Column
    private String casoId;
    @Column
    private int tipoId;
    @Column
    private String path;
    @Column
    private String localPath;
    @Column
    private String nombre;

    public String getCasoId() {
        return casoId;
    }

    public void setCasoId(String casoId) {
        this.casoId = casoId;
    }

    public String getArchivoCasoId() {
        return archivoCasoId;
    }

    public void setArchivoCasoId(String archivoCasoId) {
        this.archivoCasoId = archivoCasoId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
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

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
