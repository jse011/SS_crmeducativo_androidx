package com.consultoraestrategia.ss_crmeducativo.entities.modelViews;

import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.raizlabs.android.dbflow.sql.language.From;
import com.raizlabs.android.dbflow.sql.language.Where;

/**
 * Created by SCIEV on 9/04/2018.
 */

public class CalendarioPeriodoModel extends ModelViewAbstract<CalendarioPeriodo, CalendarioPeriodoModel> {

    public static CalendarioPeriodoModel SQLView(){
        return new CalendarioPeriodoModel();
    }

    @Override
    protected CalendarioPeriodoModel getFindInstance() {
        return this;
    }

    @Override
    From<CalendarioPeriodo> _from() {
        return new From<>(select,CalendarioPeriodo.class)
                .innerJoin(CalendarioAcademico.class)
                .on(CalendarioPeriodo_Table.calendarioAcademicoId.withTable().eq(CalendarioAcademico_Table.calendarioAcademicoId.withTable()))
                .innerJoin(ProgramasEducativo.class)
                .on(CalendarioAcademico_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(ProgramasEducativo_Table.programaEduId.withTable().eq(PlanEstudios_Table.programaEduId.withTable()))
                .innerJoin(PlanCursos.class)
                .on(PlanEstudios_Table.planEstudiosId.withTable().eq(PlanCursos_Table.planEstudiosId.withTable()))
                .innerJoin(Cursos.class)
                .on(PlanCursos_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(PlanCursos_Table.planCursoId.withTable().eq(CargaCursos_Table.planCursoId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(AnioAcademico_Table.idAnioAcademico.withTable().eq(CalendarioAcademico_Table.idAnioAcademico.withTable()));
    }

    @Override
    public Where<CalendarioPeriodo> getQuery() {
        return where;//.and(CalendarioAcademico_Table.estadoId.withTable().is(CalendarioAcademico.CALENDARIO_ACADEMICO_AUTORIZADO))
                //.and(AnioAcademico_Table.estadoId.withTable().in(AnioAcademico.ANIO_ACADEMICO_MATRICULA, AnioAcademico.ANIO_ACADEMICO_ACTIVO));
    }

    public Where<CalendarioPeriodo> getQuery(int cargaCursoId, int aniocademicoId) {
        where(CargaCursos_Table.cargaCursoId.withTable().is(cargaCursoId))
                .and(AnioAcademico_Table.idAnioAcademico.withTable().eq(aniocademicoId));
        return getQuery();
    }


}
