package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;

@QueryModel(database = AppDatabase.class, allFields = true)
public class RubroEvaluacion {
    @Column
    public String rubroEvalProcesoId;
    @Column
    public String evaluacionProcesoId;

    public RubroEvaluacion() {
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }

    public String getEvaluacionProcesoId() {
        return evaluacionProcesoId;
    }

    public void setEvaluacionProcesoId(String evaluacionProcesoId) {
        this.evaluacionProcesoId = evaluacionProcesoId;
    }
}


