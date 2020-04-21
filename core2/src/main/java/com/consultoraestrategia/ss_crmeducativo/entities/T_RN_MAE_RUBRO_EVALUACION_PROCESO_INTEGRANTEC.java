package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by SCIEV on 23/02/2018.
 */
@Table(database = AppDatabase.class)
public class T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC extends BaseRelEntity {

    @PrimaryKey
    private String rubroEvaluacionEquipoId;
    @PrimaryKey
    private int personaId;

    public T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC() {
    }

    public T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC(int androidId) {
        super(androidId);
    }

    public String getRubroEvaluacionEquipoId() {
        return rubroEvaluacionEquipoId;
    }

    public void setRubroEvaluacionEquipoId(String rubroEvaluacionEquipoId) {
        this.rubroEvaluacionEquipoId = rubroEvaluacionEquipoId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }
}
