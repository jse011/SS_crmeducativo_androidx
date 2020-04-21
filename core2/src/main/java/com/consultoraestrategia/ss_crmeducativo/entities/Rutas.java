package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class Rutas extends BaseModel {
    public static final int ARCHIVOS = 1, ALUMNO = 2 ;
    @PrimaryKey
    private int rutaId;
    @Column
    private String ruta_ArchivoAsistencia;
    @Column
    private String ruta_Archivo;

    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }

    public String getRuta_ArchivoAsistencia() {
        return ruta_ArchivoAsistencia;
    }

    public void setRuta_ArchivoAsistencia(String ruta_ArchivoAsistencia) {
        this.ruta_ArchivoAsistencia = ruta_ArchivoAsistencia;
    }

    public String getRuta_Archivo() {
        return ruta_Archivo;
    }

    public void setRuta_Archivo(String ruta_Archivo) {
        this.ruta_Archivo = ruta_Archivo;
    }
}
