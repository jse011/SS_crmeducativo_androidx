package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class RubroEvaluacionProcesoComentario extends BaseEntity{

    @Column
    private String evaluacionProcesoComentarioId;
    @Column
    private String evaluacionProcesoId;
    @Column
    private String comentarioId;
    @Column
    private String descripcion;
    @Column
    private int delete;

    public RubroEvaluacionProcesoComentario() { }

    public String getEvaluacionProcesoComentarioId() {
        return evaluacionProcesoComentarioId;
    }

    public void setEvaluacionProcesoComentarioId(String evaluacionProcesoComentarioId) {
        this.evaluacionProcesoComentarioId = evaluacionProcesoComentarioId;
    }

    public String getEvaluacionProcesoId() {
        return evaluacionProcesoId;
    }

    public void setEvaluacionProcesoId(String evaluacionProcesoId) {
        this.evaluacionProcesoId = evaluacionProcesoId;
    }

    public String getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(String comentarioId) {
        this.comentarioId = comentarioId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
}
