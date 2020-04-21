package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;


import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 5/03/2018.
 */

public class CompetenciaSesionAprendizajeModel extends ModelViewAbstract<Competencia,CompetenciaSesionAprendizajeModel> {

    public static CompetenciaSesionAprendizajeModel SQLView(){
        return new CompetenciaSesionAprendizajeModel();
    }

    private CompetenciaSesionAprendizajeModel() {
        super();
    }

    @Override
    protected CompetenciaSesionAprendizajeModel getFindInstance() {
        return this;
    }

    @Override
    From<Competencia> _from() {
        return new From<>(select,Competencia.class)
                .innerJoin(Competencia.class).as("Capacidad")
                .on(Competencia_Table.competenciaId.withTable()
                        .eq(Competencia_Table.superCompetenciaId.withTable(NameAlias.builder("Capacidad").build())))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(Competencia_Table.competenciaId.withTable(NameAlias.builder("Capacidad").build())
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable()));
    }

    @Override
    public Where<Competencia> getQuery() {
        return where.groupBy(iProperties);
    }

    public Where<Competencia> getQuery(int sesionAprendizajeId) {
        where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId));
        return getQuery();
    }

    public Where<Competencia> getQuery(int sesionAprendizajeId, int competenciaId) {
        where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId));
        if(competenciaId!=0)and(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable().eq(competenciaId));
        return getQuery();
    }


}
