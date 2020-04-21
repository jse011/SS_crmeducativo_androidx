package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 7/03/2018.
 */

public class SilaboEventoCargaCursoModel extends ModelViewAbstract<SilaboEvento,SilaboEventoCargaCursoModel> {

    public static SilaboEventoCargaCursoModel SQLView(){
        return new SilaboEventoCargaCursoModel();
    }
    private SilaboEventoCargaCursoModel(){
        super();
    }

    @Override
    protected SilaboEventoCargaCursoModel getFindInstance() {
        return this;
    }

    @Override
    From<SilaboEvento> _from() {
        return new From<>(select,SilaboEvento.class);
    }

    @Override
    public Where<SilaboEvento> getQuery() {
        return where;
    }

    public Where<SilaboEvento> getQuery(int cargaCursoId) {
        where(SilaboEvento_Table.cargaCursoId.withTable().is(cargaCursoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO));
        return getQuery();
    }
}
