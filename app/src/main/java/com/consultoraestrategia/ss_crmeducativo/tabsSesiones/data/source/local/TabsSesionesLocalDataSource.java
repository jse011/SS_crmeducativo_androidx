package com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.SesionAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO;
import com.consultoraestrategia.ss_crmeducativo.entities.T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.DatosEsencialesTabsSesion;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.TabSesionesDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.entities.DatosEnsencialesUI;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by SCIEV on 25/01/2018.
 */

public class TabsSesionesLocalDataSource implements TabSesionesDataSource{

    private String TAG = TabsSesionesLocalDataSource.class.getSimpleName();

    @Override
    public DatosEnsencialesUI getDatosEsenciales(int sesionAprendizajeId, int cursoId) {


        /*DatosEsencialesTabsSesion datosEsencialesTabsSesions = SQLite.select()
                .from(SesionAprendizajeUi.class)
                .innerJoin(UnidadAprendizaje.class)
                .on(SesionAprendizaje_Table.unidadAprendizajeId.withTable().eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .innerJoin(SilaboEvento.class)
                .on(UnidadAprendizaje_Table.silaboEventoId.withTable().eq(SilaboEvento_Table.silaboEventoId.withTable()))
                .innerJoin(AnioAcademico.class)
                .on(SilaboEvento_Table.anioAcademicoId.withTable().eq(AnioAcademico_Table.idAnioAcademico.withTable()))
                .innerJoin(CalendarioAcademico.class)
                .on(AnioAcademico_Table.idAnioAcademico.withTable().eq(CalendarioAcademico_Table.idAnioAcademico.withTable()))
                .innerJoin(CalendarioPeriodoUi.class)
                .on(CalendarioAcademico_Table.calendarioAcademicoId.withTable().eq(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()))
                .innerJoin(Tipos.class)
                .on(CalendarioPeriodo_Table.tipoId.withTable().eq(Tipos_Table.tipoId.withTable()))
                .innerJoin(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO.class)
                .on(Tipos_Table.tipoId.withTable().eq(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.tipoid.withTable()))
                .innerJoin(RubroEvaluacionResultado.class)
                .on(SilaboEvento_Table.silaboEventoId.withTable().eq(RubroEvaluacionResultado_Table.silaboEventoId.withTable()))
                .innerJoin(ProgramasEducativo.class)
                .on(CalendarioAcademico_Table.programaEduId.withTable().eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(ProgramasEducativo_Table.programaEduId.withTable().eq(PlanEstudios_Table.programaEduId.withTable()))
                .innerJoin(PlanCursos.class)
                .on(PlanEstudios_Table.planEstudiosId.withTable().eq(PlanCursos_Table.planEstudiosId.withTable()))
                .innerJoin(Cursos.class)
                .on(PlanCursos_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                .where(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().is(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .and(CalendarioPeriodo_Table.calendarioPeriodoId.withTable().is(RubroEvaluacionResultado_Table.calendarioPeriodoId.withTable()))
                .and(PlanCursos_Table.cursoId.withTable().is(curosId))
                .and(CalendarioAcademico_Table.estadoId.withTable().notIn(213))
                .and(SesionAprendizaje_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .and(AnioAcademico_Table.estadoId.withTable().in(192,193))
                .orderBy(RubroEvaluacionResultado_Table.nivel.withTable().desc())
                .queryCustomSingle(DatosEsencialesTabsSesion.class);*/


        DatosEsencialesTabsSesion datosEsencialesTabsSesions = SQLite.select()
                .from(SesionAprendizaje.class)
                .innerJoin(UnidadAprendizaje.class)
                .on(SesionAprendizaje_Table.unidadAprendizajeId.withTable().eq(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
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
                .innerJoin(Cursos.class)
                .on(PlanCursos_Table.cursoId.withTable().eq(Cursos_Table.cursoId.withTable()))
                .where(T_GC_REL_UNIDAD_APREN_EVENTO_TIPO_Table.unidadaprendizajeId.withTable().is(UnidadAprendizaje_Table.unidadAprendizajeId.withTable()))
                .and(PlanCursos_Table.cursoId.withTable().is(cursoId))
                .and(CalendarioAcademico_Table.estadoId.withTable().notIn(213))
                .and(SesionAprendizaje_Table.sesionAprendizajeId.withTable().is(sesionAprendizajeId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(AnioAcademico_Table.estadoId.withTable().in(192,193))
                .queryCustomSingle(DatosEsencialesTabsSesion.class);




        DatosEnsencialesUI datosEnsencialesUI = new DatosEnsencialesUI();
        if(datosEsencialesTabsSesions != null){
            datosEnsencialesUI.setSilavoEventoId(datosEsencialesTabsSesions.getSilaboEventoId());;
            datosEnsencialesUI.setCalendarioPeriodoId(datosEsencialesTabsSesions.getCalendarioPeriodoId());
            datosEnsencialesUI.setRubroEvalaucionResultadoId(datosEsencialesTabsSesions.getRubroEvalResultadoId());
            datosEnsencialesUI.setNivelEvaluacion(3);
            datosEnsencialesUI.setProgramaEducativoId(datosEsencialesTabsSesions.getProgramaEduId());
        }

        return datosEnsencialesUI;
    }
}
