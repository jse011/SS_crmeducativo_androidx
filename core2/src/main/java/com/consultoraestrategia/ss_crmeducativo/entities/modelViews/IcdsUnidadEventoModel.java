package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;

import java.util.List;

/**
 * Created by SCIEV on 7/03/2018.
 */

public class IcdsUnidadEventoModel extends ModelViewAbstract<Icds,IcdsUnidadEventoModel> {
    public static IcdsUnidadEventoModel SQLView(){
        return new IcdsUnidadEventoModel();
    }

    private IcdsUnidadEventoModel() {
    }

    @Override
    protected IcdsUnidadEventoModel getFindInstance() {
        return this;
    }

    @Override
    From<Icds> _from() {
        return new From<>(select, Icds.class)
                .innerJoin(DesempenioIcd.class)
                .on(Icds_Table.icdId.withTable()
                        .eq(DesempenioIcd_Table.icdId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD.class)
                .on(DesempenioIcd_Table.desempenioIcdId.withTable()
                        .eq(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.desempenioIcdId.withTable()))
                .innerJoin(CompetenciaUnidad.class)
                .on(T_GC_REL_UNIDAD_EVENTO_COMPETENCIA_DESEMPENIO_ICD_Table.unidadCompetenciaId.withTable()
                        .eq(CompetenciaUnidad_Table.unidadCompetenciaId.withTable()))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable()
                        .eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
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
    public Where<Icds> getQuery() {
        return where.and(UnidadAprendizaje_Table.estadoId.withTable().notEq(UnidadAprendizaje.ESTADO_ELIMINADO))
        .groupBy(iProperties);
    }

    public Where<Icds> getQuery(int silaboEventoId) {
        where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO));
        return getQuery();
    }

    public Where<Icds> getQuery(int silaboEventoId,int competenciaId) {
        where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
        .and(CompetenciaUnidad_Table.competenciaId.withTable().is(competenciaId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO));
        return getQuery();
    }

    public Where<Icds> getQuery(int silaboEventoId,int competenciaId,int calendarioPeriodo) {
        where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
        .and(CompetenciaUnidad_Table.competenciaId.withTable().is(competenciaId))
        .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodo))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO));
        return getQuery();
    }

    public Where<Icds> getQuery(int silaboEventoId, List<Integer> competenciaIdList, int calendarioPeriodo) {
        where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
                .and(CompetenciaUnidad_Table.competenciaId.withTable().in(competenciaIdList))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodo))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO));
        return getQuery();
    }

    public Where<Icds> getQuery(int silaboEventoId,int competenciaId,int calendarioPeriodo,IProperty[] iProperties) {
        where(SilaboEvento_Table.silaboEventoId.withTable().is(silaboEventoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(CompetenciaUnidad_Table.competenciaId.withTable().is(competenciaId))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodo));
        where.and(UnidadAprendizaje_Table.estadoId.withTable().notEq(UnidadAprendizaje.ESTADO_ELIMINADO));
        return where.groupBy(iProperties);
    }
}
