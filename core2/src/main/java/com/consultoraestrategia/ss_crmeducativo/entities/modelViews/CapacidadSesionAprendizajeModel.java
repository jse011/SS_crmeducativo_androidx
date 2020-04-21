package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;


import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 5/03/2018.
 */

public class CapacidadSesionAprendizajeModel extends ModelViewAbstract<Competencia,CapacidadSesionAprendizajeModel> {

    public static CapacidadSesionAprendizajeModel SQLView(){
        return new CapacidadSesionAprendizajeModel();
    }

    private CapacidadSesionAprendizajeModel() {
        super();
    }

    @Override
    protected CapacidadSesionAprendizajeModel getFindInstance() {
        return this;
    }

    @Override
    From<Competencia> _from() {
        return new From<>(select,Competencia.class)
                .innerJoin(Competencia.class).as("Competecia_Padre")
                .on(Competencia_Table.superCompetenciaId.withTable()
                        .eq(Competencia_Table.competenciaId.withTable(NameAlias.builder("Competecia_Padre").build())))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable()));
    }

    @Override
    public Where<Competencia> getQuery() {
        return where;
    }

    public Where<Competencia> getQuery(int competenciaId, int sesionAprendizajeId) {
        where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .and(Competencia_Table.competenciaId.withTable(NameAlias.builder("Competecia_Padre").build()).is(competenciaId));
        return getQuery();
    }

    public Where<Competencia> getQuery(int sesionAprendizajeId) {
        where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId));
        return getQuery();
    }
}

