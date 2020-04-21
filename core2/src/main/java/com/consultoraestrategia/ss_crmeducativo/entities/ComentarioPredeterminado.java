package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class ComentarioPredeterminado extends BaseEntity {
    @Column
    public String comentarioId;
    @Column
    public int tipoComentarioId;
    @Column
    public String descripcion;
    @Column
    public boolean estado;

    public ComentarioPredeterminado() {
    }

    public String getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(String comentarioId) {
        this.comentarioId = comentarioId;
    }

    public int getTipoComentarioId() {
        return tipoComentarioId;
    }

    public void setTipoComentarioId(int tipoComentarioId) {
        this.tipoComentarioId = tipoComentarioId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
