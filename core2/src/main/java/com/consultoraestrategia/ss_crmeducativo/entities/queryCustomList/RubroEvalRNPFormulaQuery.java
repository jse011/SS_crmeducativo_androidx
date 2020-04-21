package com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;

@QueryModel(database = AppDatabase.class, allFields = true)
public class RubroEvalRNPFormulaQuery {
    @Column
    public String rubroFormulaId;
    @Column
    public String rubroEvaluacionPrimId;
    @Column
    public String rubroEvaluacionSecId;
    @Column
    public String titulo;

    public String getRubroFormulaId() {
        return rubroFormulaId;
    }

    public String getRubroEvaluacionPrimId() {
        return rubroEvaluacionPrimId;
    }

    public String getRubroEvaluacionSecId() {
        return rubroEvaluacionSecId;
    }

    public String getTitulo() {
        return titulo;
    }
}
