package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by SCIEV on 20/10/2017.
 */
@Table(database = AppDatabase.class)
public class CriterioRubroEvaluacionC extends BaseEntity {

    @Column
    private String criteriosEvaluacionId;
    @Column
    private String rubroEvalProcesoId;
    @Column
    private String valorTipoNotaId;
    @Column
    private String descripcion;

    public CriterioRubroEvaluacionC() {
    }

    public CriterioRubroEvaluacionC(int androidId) {
        super(androidId);
    }

    public String getCriteriosEvaluacionId() {
        return criteriosEvaluacionId;
    }

    public void setCriteriosEvaluacionId(String criteriosEvaluacionId) {
        this.criteriosEvaluacionId = criteriosEvaluacionId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public String getValorTipoNotaId() {
        return valorTipoNotaId;
    }

    public void setValorTipoNotaId(String valorTipoNotaId) {
        this.valorTipoNotaId = valorTipoNotaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "CriterioRubroEvaluacionC{" +
                "criteriosEvaluacionId='" + criteriosEvaluacionId + '\'' +
                ", rubroEvalProcesoId='" + rubroEvalProcesoId + '\'' +
                ", valorTipoNotaId='" + valorTipoNotaId + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
