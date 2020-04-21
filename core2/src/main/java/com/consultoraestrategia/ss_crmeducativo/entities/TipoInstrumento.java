package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by SCIEV on 15/08/2018.
 */
@Table(database = AppDatabase.class)
public class TipoInstrumento extends BaseEntity{
    @PrimaryKey
    public int tipoInstrumentoId;
    @Column
    public String nombre;
    @Column
    public int estado;

    public int getTipoInstrumentoId() {
        return tipoInstrumentoId;
    }

    public void setTipoInstrumentoId(int tipoInstrumentoId) {
        this.tipoInstrumentoId = tipoInstrumentoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
