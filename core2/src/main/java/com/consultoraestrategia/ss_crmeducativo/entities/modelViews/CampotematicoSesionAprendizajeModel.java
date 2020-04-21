package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoCompetenciaDesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionEventoDesempenioIcdCampotematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.List;

/**
 * Created by SCIEV on 7/03/2018.
 */

public class CampotematicoSesionAprendizajeModel extends ModelViewAbstract<CampoTematico, CampotematicoSesionAprendizajeModel> {

    public static CampotematicoSesionAprendizajeModel SQLView(){
        return new CampotematicoSesionAprendizajeModel();
    }

    private CampotematicoSesionAprendizajeModel() {
    }

    @Override
    protected CampotematicoSesionAprendizajeModel getFindInstance() {
        return this;
    }

    @Override
    From<CampoTematico> _from() {
        return new From<>(select ,CampoTematico.class)
                .innerJoin(SesionEventoDesempenioIcdCampotematico.class)
                .on(CampoTematico_Table.campoTematicoId.withTable()
                        .eq(SesionEventoDesempenioIcdCampotematico_Table.campoTematicoId.withTable()))
                .innerJoin(SesionEventoCompetenciaDesempenioIcd.class)
                .on(SesionEventoDesempenioIcdCampotematico_Table.sesionCompetenciaDesempenioIcdId.withTable()
                        .eq(SesionEventoCompetenciaDesempenioIcd_Table.sesionCompetenciaDesempenioIcdId.withTable()))
                .innerJoin(DesempenioIcd.class)
                .on(SesionEventoCompetenciaDesempenioIcd_Table.desempenioIcdId.withTable()
                        .is(DesempenioIcd_Table.desempenioIcdId.withTable()))
                .innerJoin(T_GC_REL_COMPETENCIA_SESION_EVENTO.class)
                .on(SesionEventoCompetenciaDesempenioIcd_Table.sesionCompetenciaId.withTable()
                        .eq(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionCompetenciaId.withTable()));
    }

    @Override
    public Where<CampoTematico> getQuery() {
        return where;
    }

    public Where<CampoTematico> getQuery(int desempenioIcdId, int sesionAprendizajeId) {
        where(DesempenioIcd_Table.desempenioIcdId.withTable().is(desempenioIcdId))
                .and(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId));
        return getQuery();
    }
    public Where<CampoTematico> getQuery(List<Integer> desempenioIcdIdLis) {
        where(DesempenioIcd_Table.desempenioIcdId.withTable().in(desempenioIcdIdLis));
                //.and(T_GC_REL_COMPETENCIA_SESION_EVENTO_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId));
        return getQuery();
    }


}
