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
import com.raizlabs.android.dbflow.sql.language.property.IProperty;

/**
 * Created by SCIEV on 7/03/2018.
 */

public class IcdsSesionAprendizajeModel extends ModelViewAbstract<Icds,IcdsSesionAprendizajeModel> {

    public static IcdsSesionAprendizajeModel SQLView(){
        return new IcdsSesionAprendizajeModel();
    }
    private IcdsSesionAprendizajeModel() {
        super();
    }

    @Override
    protected IcdsSesionAprendizajeModel getFindInstance() {
        return this;
    }

    @Override
    From<Icds> _from() {
        return new From<>(select, Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(Icds_Table.icdId.withTable()
                        .eq(DesempenioIcd_Table.icdId.withTable()))
                .innerJoin(SesionEventoCompetenciaDesempenioIcd.class)
                .on(DesempenioIcd_Table.desempenioIcdId.withTable()
                        .eq(SesionEventoCompetenciaDesempenioIcd_Table.desempenioIcdId.withTable()))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(SesionEventoCompetenciaDesempenioIcd_Table.sesionCompetenciaId.withTable()
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionCompetenciaId.withTable()));
    }

    @Override
    public Where<Icds> getQuery() {
        return where.groupBy(iProperties);
    }

    public Where<Icds> getQuery(int competenciaId, int SesionaprendizajeId) {
        where(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable().is(competenciaId))
                .and(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(SesionaprendizajeId));
        return getQuery();
    }

    public Where<Icds> getQuery(int competenciaId, int SesionaprendizajeId, IProperty[] iProperties) {
        where (T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(SesionaprendizajeId))
        .and(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.competenciaId.withTable().is(competenciaId));
        return where.groupBy(iProperties);
    }


}
