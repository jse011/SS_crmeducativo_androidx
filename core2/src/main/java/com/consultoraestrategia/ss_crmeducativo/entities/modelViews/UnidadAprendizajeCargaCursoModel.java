package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 8/03/2018.
 */

public class UnidadAprendizajeCargaCursoModel extends ModelViewAbstract<UnidadAprendizaje,UnidadAprendizajeCargaCursoModel> {

    public static UnidadAprendizajeCargaCursoModel SQLView(){
        return new UnidadAprendizajeCargaCursoModel();
    }
    @Override
    protected UnidadAprendizajeCargaCursoModel getFindInstance() {
        return this;
    }

    @Override
    From<UnidadAprendizaje> _from() {
        return new From<>(select,UnidadAprendizaje.class)
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(SilaboEvento_Table.cargaCursoId.withTable().eq(CargaCursos_Table.cargaCursoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable()
                        .eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(Tipos.class)
                .on(Tipos_Table.tipoId.withTable()
                        .eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(CalendarioPeriodo_Table.tipoId.withTable()
                        .eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(CalendarioAcademico.class)
                .on(CalendarioAcademico_Table.calendarioAcademicoId.withTable()
                        .eq(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(SilaboEvento_Table.anioAcademicoId.withTable()
                        .eq(AnioAcademico_Table.idAnioAcademico.withTable()));

                /*return new From<>(select,UnidadAprendizaje.class)
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(SilaboEvento_Table.anioAcademicoId.withTable().eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .innerJoin(CalendarioAcademico.class)
                .on(AnioAcademico_Table.idAnioAcademico.withTable().eq(CalendarioAcademico_Table.idAnioAcademico.withTable()))
                .innerJoin(CalendarioPeriodo.class)
                .on(CalendarioAcademico_Table.calendarioAcademicoId.withTable().eq(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()))
                .innerJoin(Tipos.class)
                .on(CalendarioPeriodo_Table.tipoId.withTable().eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(Tipos_Table.tipoId.withTable().eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()))
                .innerJoin(ProgramasEducativo.class)
                .on(CalendarioAcademico_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(ProgramasEducativo_Table.programaEduId.withTable().eq(PlanEstudios_Table.programaEduId.withTable()))
                .innerJoin(PlanCursos.class)
                .on(PlanEstudios_Table.planEstudiosId.withTable().eq(PlanCursos_Table.planEstudiosId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(PlanCursos_Table.planCursoId.withTable().eq(CargaCursos_Table.planCursoId.withTable()));*/

    }

    @Override
    public Where<UnidadAprendizaje> getQuery() {
        return where.and(CalendarioAcademico_Table.estadoId.withTable().is(CalendarioAcademico.CALENDARIO_ACADEMICO_AUTORIZADO))
                //.and(AnioAcademico_Table.estadoId.withTable().in(AnioAcademico.ANIO_ACADEMICO_MATRICULA,AnioAcademico.ANIO_ACADEMICO_ACTIVO))
                .and(UnidadAprendizaje_Table.estadoId.withTable().notEq(UnidadAprendizaje.ESTADO_ELIMINADO))
                .groupBy(iProperties);
        /*where.and(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().is(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .and(CalendarioAcademico_Table.estadoId.withTable().is(CalendarioAcademico.CALENDARIO_ACADEMICO_AUTORIZADO))
                .and(AnioAcademico_Table.estadoId.withTable().in(AnioAcademico.ANIO_ACADEMICO_MATRICULA,AnioAcademico.ANIO_ACADEMICO_ACTIVO))
                .and(PlanCursos_Table.planCursoId.withTable().is(SilaboEvento_Table.planCursoId.withTable()));*/
    }

    public Where<UnidadAprendizaje> getQuery(int cargaCursoId, int calendarioPeriodId) {
        where(CargaCursos_Table.cargaCursoId.withTable().is(cargaCursoId))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodId));
        return getQuery();
    }

    public Where<UnidadAprendizaje> getQuery(int cargaCursoId, int calendarioPeriodId, int anioAcademicoId) {
        where(CargaCursos_Table.cargaCursoId.withTable().is(cargaCursoId))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().eq(calendarioPeriodId))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(anioAcademicoId));
        return getQuery();
    }

}
