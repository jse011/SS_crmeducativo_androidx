package com.consultoraestrategia.ss_crmeducativo.entities;

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import java.util.List;

/**
 * Created by SCIEV on 23/02/2018.
 */
@Table(database = AppDatabase.class)
public class T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC extends BaseEntity {
    @Column
    private String rubroEvaluacionEquipoId;
    @Column
    private String equipoId;
    @Column
    private String nombreEquipo;
    @Column
    private String rubroEvalProcesoId;

    public T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC() {
    }

    public String getRubroEvaluacionEquipoId() {
        return rubroEvaluacionEquipoId;
    }

    public void setRubroEvaluacionEquipoId(String rubroEvaluacionEquipoId) {
        this.rubroEvaluacionEquipoId = rubroEvaluacionEquipoId;
    }

    public String getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(String equipoId) {
        this.equipoId = equipoId;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getRubroEvalProcesoId() {
        return rubroEvalProcesoId;
    }

    public void setRubroEvalProcesoId(String rubroEvalProcesoId) {
        this.rubroEvalProcesoId = rubroEvalProcesoId;
    }
    private List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> integrantecList;

    public List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> getIntegrantecList() {
        return integrantecList;
    }

    public void setIntegrantecList(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> integrantecList) {
        this.integrantecList = integrantecList;
    }
}
