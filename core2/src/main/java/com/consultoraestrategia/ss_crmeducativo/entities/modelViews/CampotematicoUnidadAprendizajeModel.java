package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 7/03/2018.
 */

public class CampotematicoUnidadAprendizajeModel extends ModelViewAbstract<CampoTematico, CampotematicoUnidadAprendizajeModel> {
    public static CampotematicoUnidadAprendizajeModel SQLView() {
        return new CampotematicoUnidadAprendizajeModel();
    }
 String TAG= CampotematicoUnidadAprendizajeModel.class.getSimpleName();
    private CampotematicoUnidadAprendizajeModel() {
    }

    @Override
    protected CampotematicoUnidadAprendizajeModel getFindInstance() {
        return this;
    }

    @Override
    From<CampoTematico> _from() {
        return new From<>(select ,CampoTematico.class)
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.campoTematicoIcd.withTable()
                        .eq(CampoTematico_Table.campoTematicoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_CAMPO_TEMATICO_Table.unidadCompetenciaDesempenioIcdId.withTable()
                        .eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaDesempenioIcdId.withTable()))
                .innerJoin(CompetenciaUnidad.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable()
                        .eq(CompetenciaUnidad_Table.unidadCompetenciaId.withTable()))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable()
                        .eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .innerJoin(DesempenioIcd.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.desempenioIcdId.withTable()
                        .eq(DesempenioIcd_Table.desempenioIcdId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()
                        .eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable()))
                .innerJoin(Tipos.class)
                .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()
                        .eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(Tipos_Table.tipoId.withTable()
                        .eq(CalendarioPeriodo_Table.tipoId.withTable()));
    }

    @Override
    public Where<CampoTematico> getQuery() {
        return where.and(UnidadAprendizaje_Table.estadoId.withTable().notEq(UnidadAprendizaje.ESTADO_ELIMINADO))
                .groupBy(iProperties);
    }

    public Where<CampoTematico> getQuery(int silaboEventoId, int desempenioIcdId, int calendarioPeriodoId) {
        Log.d(TAG, "desempenioIcdId" + desempenioIcdId+ " silaboEventoId "+ silaboEventoId + " calendarioPeriodoId: "+ calendarioPeriodoId);

        where(DesempenioIcd_Table.desempenioIcdId.withTable().eq(desempenioIcdId))
                 .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodoId))
                .and(SilaboEvento_Table.silaboEventoId.withTable().eq(silaboEventoId))
        .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO));
        return getQuery();
    }

}
