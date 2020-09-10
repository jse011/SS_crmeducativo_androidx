package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = AppDatabase.class)
public class TareaRubroEvaluacionProceso extends BaseEntity {
    @Column
    private String tareaRubroEvaProcesoId;
    @Column
    private String tareaId;
    @Column
    private String rubroEvalProcesoId;

    public TareaRubroEvaluacionProceso() {
    }

    public String getTareaRubroEvaProcesoId() {
        return tareaRubroEvaProcesoId;
    }

    public void setTareaRubroEvaProcesoId(String tareaRubroEvaProcesoId) {
        this.tareaRubroEvaProcesoId = tareaRubroEvaProcesoId;
    }

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }
}