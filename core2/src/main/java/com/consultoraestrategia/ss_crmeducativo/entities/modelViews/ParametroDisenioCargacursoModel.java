package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 19/03/2018.
 */

public class ParametroDisenioCargacursoModel extends ModelViewAbstract<ParametrosDisenio, ParametroDisenioCargacursoModel> {
    public static ParametroDisenioCargacursoModel SQLView(){
        return new ParametroDisenioCargacursoModel();
    }
    @Override
    protected ParametroDisenioCargacursoModel getFindInstance() {
        return this;
    }

    @Override
    From<ParametrosDisenio> _from() {
        return new From<>(select,ParametrosDisenio.class)
                .innerJoin(SilaboEvento.class)
                .on(SilaboEvento_Table.parametroDisenioId.withTable()
                        .is(ParametrosDisenio_Table.parametroDisenioId.withTable()))
                .innerJoin(PlanCursos.class)
                .on(SilaboEvento_Table.planCursoId.withTable()
                        .eq(PlanCursos_Table.planCursoId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(PlanCursos_Table.planCursoId.withTable()
                        .eq(CargaCursos_Table.planCursoId.withTable()));
    }

    @Override
    public Where<ParametrosDisenio> getQuery() {
        return where;
    }

    public Where<ParametrosDisenio> getQuery(int cargaCursoId) {
        where(CargaCursos_Table.cargaCursoId.withTable().is(cargaCursoId));
        return getQuery();
    }
}
