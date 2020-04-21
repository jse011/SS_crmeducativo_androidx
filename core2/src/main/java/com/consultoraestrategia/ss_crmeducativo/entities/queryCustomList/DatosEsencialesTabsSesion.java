package com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList;

/**
 * Created by SCIEV on 12/02/2018.
 */

import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.QueryModel;

@QueryModel(database = AppDatabase.class, allFields = true)
public class DatosEsencialesTabsSesion {
    @Column
    public int calendarioPeriodoId;
    @Column
    public int silaboEventoId;
    @Column
    public int rubroEvalResultadoId;
    @Column
    public int nivel;
    @Column
    public int programaEduId;

    public DatosEsencialesTabsSesion() {
    }

    public int getCalendarioPeriodoId() {
        return calendarioPeriodoId;
    }

    public int getSilaboEventoId() {
        return silaboEventoId;
    }

    public int getRubroEvalResultadoId() {
        return rubroEvalResultadoId;
    }

    public int getNivel() {
        return nivel;
    }

    public int getProgramaEduId() {
        return programaEduId;
    }
}
