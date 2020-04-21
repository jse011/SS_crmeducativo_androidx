package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 7/03/2018.
 */

public class TipoEvaluacionModel extends ModelViewAbstract<T_RN_MAE_TIPO_EVALUACION, TipoEvaluacionModel>{

    public static TipoEvaluacionModel SQLView(){
        return new TipoEvaluacionModel();
    }

    private TipoEvaluacionModel() {
    }

    @Override
    protected TipoEvaluacionModel getFindInstance() {
        return this;
    }

    @Override
    From<T_RN_MAE_TIPO_EVALUACION> _from() {
        return new From<>(select ,T_RN_MAE_TIPO_EVALUACION.class);
    }

    @Override
    public Where<T_RN_MAE_TIPO_EVALUACION> getQuery() {
        return where.and(T_RN_MAE_TIPO_EVALUACION_Table.estado.is(true));
    }
}
