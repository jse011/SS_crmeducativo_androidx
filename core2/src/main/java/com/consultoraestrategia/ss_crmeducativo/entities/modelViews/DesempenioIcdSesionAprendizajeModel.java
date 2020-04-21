package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 7/03/2018.
 */

public class DesempenioIcdSesionAprendizajeModel extends ModelViewAbstract<DesempenioIcd,DesempenioIcdSesionAprendizajeModel> {

    public static DesempenioIcdSesionAprendizajeModel SQLView(){
        return new DesempenioIcdSesionAprendizajeModel();
    }
    private DesempenioIcdSesionAprendizajeModel() {
        super();
    }

    @Override
    protected DesempenioIcdSesionAprendizajeModel getFindInstance() {
        return this;
    }

    @Override
    From<DesempenioIcd> _from() {
        return new From<>(select, DesempenioIcd.class)
                .innerJoin(SesionEventoCompetenciaDesempenioIcd.class)
                .on(DesempenioIcd_Table.desempenioIcdId.withTable()
                        .eq(SesionEventoCompetenciaDesempenioIcd_Table.desempenioIcdId.withTable()))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(SesionEventoCompetenciaDesempenioIcd_Table.sesionCompetenciaId.withTable()
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionCompetenciaId.withTable()));
    }

    @Override
    public Where<DesempenioIcd> getQuery() {
        return where.groupBy(iProperties);
    }

    public Where<DesempenioIcd> getQuery(int competenciaId, int SesionaprendizajeId) {
        where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable().is(competenciaId))
                .and(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(SesionaprendizajeId));
        return getQuery();
    }
}
