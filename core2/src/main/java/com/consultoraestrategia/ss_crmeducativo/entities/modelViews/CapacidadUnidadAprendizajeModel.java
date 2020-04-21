package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.entities.CompetenciaUnidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_COMPETENCIA_SESION_EVENTO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.List;

/**
 * Created by SCIEV on 13/03/2018.
 */

public class CapacidadUnidadAprendizajeModel extends ModelViewAbstract<Competencia, CapacidadUnidadAprendizajeModel> {
    public static CapacidadUnidadAprendizajeModel SQLView(){
        return new CapacidadUnidadAprendizajeModel();
    }
    @Override
    protected CapacidadUnidadAprendizajeModel getFindInstance() {
        return this;
    }

    @Override
    From<Competencia> _from() {
        return new From<>(select ,Competencia.class)
                .innerJoin(Competencia.class).as("Competecia_Padre")
                .on(Competencia_Table.superCompetenciaId.withTable()
                        .eq(Competencia_Table.competenciaId.withTable(NameAlias.builder("Competecia_Padre").build())))
                .innerJoin(CompetenciaUnidad.class)
                .on(CompetenciaUnidad_Table.competenciaId.withTable()
                        .eq(Competencia_Table.competenciaId.withTable()))
                .innerJoin(UnidadAprendizaje.class)
                .on(CompetenciaUnidad_Table.unidadAprendizajeId.withTable().eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable()
                        .eq(SilaboEvento_Table.silaboEventoId.withTable()))
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
    public Where<Competencia> getQuery() {
        return where.and(UnidadAprendizaje_Table.estadoId.withTable().notEq(UnidadAprendizaje.ESTADO_ELIMINADO))
                .groupBy(iProperties);
    }

    public Where<Competencia> getQuery(int silvoEvetnoId,int calendarioPerioId, int CompetenciaId) {
        where(SilaboEvento_Table.silaboEventoId.withTable().is(silvoEvetnoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().is(calendarioPerioId))
                .and(Competencia_Table.superCompetenciaId.withTable().eq(CompetenciaId));
        return  getQuery();
    }

    public Where<Competencia> getQuery(int silvoEvetnoId,int calendarioPerioId, List<Integer> CompetenciaIdList) {
        where(SilaboEvento_Table.silaboEventoId.withTable().is(silvoEvetnoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().is(calendarioPerioId))
                .and(Competencia_Table.superCompetenciaId.withTable().in(CompetenciaIdList));
        return  getQuery();
    }

    public Where<Competencia> getQuery(int silvoEvetnoId,int calendarioPerioId) {
        where(SilaboEvento_Table.silaboEventoId.withTable().is(silvoEvetnoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().is(calendarioPerioId));
        return  getQuery();
    }


}
