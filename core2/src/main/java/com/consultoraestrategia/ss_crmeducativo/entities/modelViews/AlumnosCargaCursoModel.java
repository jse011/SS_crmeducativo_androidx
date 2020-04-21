package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 9/03/2018.
 */

public class AlumnosCargaCursoModel extends ModelViewAbstract<Persona, AlumnosCargaCursoModel>{

    public static AlumnosCargaCursoModel SQLView(){
        return new AlumnosCargaCursoModel();
    }
    @Override
    protected AlumnosCargaCursoModel getFindInstance() {
        return this;
    }

    @Override
    From<Persona> _from() {
        return new From<>(select, Persona.class)
                .innerJoin(Contrato.class)
                .on(Persona_Table.personaId.withTable()
                        .eq(Contrato_Table.personaId.withTable()))
                .innerJoin(DetalleContratoAcad.class)
                .on(Contrato_Table.idContrato.withTable()
                        .eq(DetalleContratoAcad_Table.idContrato.withTable()));
    }

    @Override
    public Where<Persona> getQuery() {
        return where;
    }

    public Where<Persona> getQuery(int cargaCursoId) {
        where(DetalleContratoAcad_Table.cargaCursoId.withTable().is(cargaCursoId));
        return getQuery();
    }

}
