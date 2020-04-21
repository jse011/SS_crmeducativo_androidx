package com.consultoraestrategia.ss_crmeducativo.dao.curso;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.ArrayList;
import java.util.List;

public class CursoDaoImpl extends BaseIntegerDaoImpl<Cursos, Cursos_Table> implements CursoDao {

    private static CursoDaoImpl mInstance;
    private String TAG = CursoDaoImpl.class.getSimpleName();

    private CursoDaoImpl() {
    }

    public static CursoDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new CursoDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return Cursos_Table.cursoId;
    }

    @Override
    protected Class<Cursos> getEntityClass() {
        return Cursos.class;
    }

    @Override
    protected Class<Cursos_Table> getTableclass() {
        return Cursos_Table.class;
    }

    @Override
    public List<CursoCustom> obtenerPorProgramaEducativo(int programaEducativoId, int anioAcademicoId) {


        return SQLite.select(
                Cursos_Table.cursoId.withTable(),
                Cursos_Table.alias.withTable(),
                Cursos_Table.color.withTable(),
                Cursos_Table.descripcion.withTable(),
                Cursos_Table.entidadId.withTable(),
                Cursos_Table.estadoId.withTable(),
                Cursos_Table.nivelAcadId.withTable(),
                Cursos_Table.nombre.withTable(),
                Cursos_Table.tipoCursoId.withTable(),
                CargaAcademica_Table.cargaAcademicaId.withTable(),
                CargaAcademica_Table.seccionId.withTable(),
                CargaAcademica_Table.periodoId.withTable(),
                CargaAcademica_Table.aulaId.withTable(),
                CargaAcademica_Table.idPlanEstudio.withTable(),
                CargaAcademica_Table.idPlanEstudioVersion.withTable(),
                CargaAcademica_Table.idAnioAcademico.withTable(),
                Seccion_Table.nombre.withTable().as("seccion"),
                Periodo_Table.alias.withTable().as("periodo"))
                .from(Cursos.class)
                .innerJoin(PlanCursos.class)
                .on(Cursos_Table.cursoId.withTable()
                        .eq(PlanCursos_Table.cursoId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(PlanCursos_Table.planEstudiosId.withTable()
                        .eq(PlanEstudios_Table.planEstudiosId.withTable()))
                .innerJoin(ProgramasEducativo.class)
                .on(PlanEstudios_Table.programaEduId.withTable()
                        .eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(PlanEstudios_Table.planEstudiosId.withTable()
                        .eq(CargaCursos_Table.idPlanEstudio.withTable()))
                .innerJoin(CargaAcademica.class)
                .on(CargaCursos_Table.cargaAcademicaId.withTable()
                        .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                .innerJoin(Seccion.class)
                .on(Seccion_Table.seccionId.withTable()
                        .eq(CargaAcademica_Table.seccionId.withTable()))
                .innerJoin(Periodo.class)
                .on(Periodo_Table.periodoId.withTable()
                        .eq(CargaAcademica_Table.periodoId.withTable()))
                .where(PlanEstudios_Table.planEstudiosId.withTable()
                        .eq(CargaAcademica_Table.idPlanEstudio.withTable()))
                .and(CargaAcademica_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                //.and(CargaCursos_Table.estado.withTable().eq(1))
                //7: Creado, 11 = eliminado, 33: actyalizado, 78 = autorizado, 109 : termindado
                .queryCustomList(CursoCustom.class);



    }

    @Override
    public List<CursoCustom> obtenerPorCargaCurso(List<Integer> cargacursoIdList, int programaEducativoId, int anioAcademicoId) {
        Log.d(TAG,"programa" + programaEducativoId+ "/" +cargacursoIdList.size());
        for (Integer integer : cargacursoIdList)Log.d(TAG, "PROGRAMA" + integer.toString());
        return SQLite.select(
                Cursos_Table.cursoId.withTable(),
                Cursos_Table.alias.withTable(),
                Cursos_Table.color.withTable(),
                Cursos_Table.descripcion.withTable(),
                Cursos_Table.entidadId.withTable(),
                Cursos_Table.estadoId.withTable(),
                Cursos_Table.nivelAcadId.withTable(),
                Cursos_Table.nombre.withTable(),
                Cursos_Table.tipoCursoId.withTable(),
                CargaCursos_Table.cargaCursoId.withTable(),
                CargaAcademica_Table.cargaAcademicaId.withTable(),
                CargaAcademica_Table.seccionId.withTable(),
                CargaAcademica_Table.periodoId.withTable(),
                CargaAcademica_Table.aulaId.withTable(),
                CargaAcademica_Table.idPlanEstudio.withTable(),
                CargaAcademica_Table.idPlanEstudioVersion.withTable(),
                CargaAcademica_Table.idAnioAcademico.withTable(),
                Seccion_Table.nombre.withTable().as("seccion"),
                Periodo_Table.alias.withTable().as("periodo"))
                .from(Cursos.class)
                .innerJoin(PlanCursos.class)
                .on(Cursos_Table.cursoId.withTable()
                        .eq(PlanCursos_Table.cursoId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(PlanCursos_Table.planEstudiosId.withTable()
                        .eq(PlanEstudios_Table.planEstudiosId.withTable()))
                .innerJoin(ProgramasEducativo.class)
                .on(PlanEstudios_Table.programaEduId.withTable()
                        .eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(PlanCursos_Table.planCursoId.withTable()
                        .eq(CargaCursos_Table.planCursoId.withTable()))


                //#region Detalle Curso
                .innerJoin(CargaAcademica.class)
                .on(CargaCursos_Table.cargaAcademicaId.withTable()
                        .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                .innerJoin(Seccion.class)
                .on(Seccion_Table.seccionId.withTable()
                        .eq(CargaAcademica_Table.seccionId.withTable()))
                .innerJoin(Periodo.class)
                .on(Periodo_Table.periodoId.withTable()
                        .eq(CargaAcademica_Table.periodoId.withTable()))
                //#endregion Detalle


                .where(PlanEstudios_Table.planEstudiosId.withTable()
                        .eq(CargaAcademica_Table.idPlanEstudio.withTable()))
                .and(CargaCursos_Table.cargaCursoId.withTable().in(cargacursoIdList))
                .and(ProgramasEducativo_Table.programaEduId.withTable().eq(programaEducativoId))
                //7: Creado, 11 = eliminado, 33: actyalizado, 78 = autorizado, 109 : termindado
                .and(CargaAcademica_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                .queryCustomList(CursoCustom.class);
    }


    @Override
    public List<CursoCustom> obtenerPorCargaCursos(List<Integer> cargaCursoIdList) {
        return SQLite.select(
                Cursos_Table.cursoId.withTable(),
                Cursos_Table.alias.withTable(),
                Cursos_Table.color.withTable(),
                Cursos_Table.descripcion.withTable(),
                Cursos_Table.entidadId.withTable(),
                Cursos_Table.estadoId.withTable(),
                Cursos_Table.nivelAcadId.withTable(),
                Cursos_Table.nombre.withTable(),
                Cursos_Table.tipoCursoId.withTable(),
                CargaCursos_Table.cargaCursoId.withTable(),
                CargaCursos_Table.complejo.withTable(),
                CargaCursos_Table.empleadoId.withTable(),
                CargaAcademica_Table.cargaAcademicaId.withTable(),
                CargaAcademica_Table.seccionId.withTable(),
                CargaAcademica_Table.periodoId.withTable(),
                CargaAcademica_Table.aulaId.withTable(),
                CargaAcademica_Table.idPlanEstudio.withTable(),
                CargaAcademica_Table.idPlanEstudioVersion.withTable(),
                CargaAcademica_Table.idAnioAcademico.withTable(),
                Seccion_Table.nombre.withTable().as("seccion"),
                Periodo_Table.alias.withTable().as("periodo"))
                .from(Cursos.class)
                .innerJoin(PlanCursos.class)
                .on(Cursos_Table.cursoId.withTable()
                        .eq(PlanCursos_Table.cursoId.withTable()))
                .innerJoin(PlanEstudios.class)
                .on(PlanCursos_Table.planEstudiosId.withTable()
                        .eq(PlanEstudios_Table.planEstudiosId.withTable()))
                .innerJoin(ProgramasEducativo.class)
                .on(PlanEstudios_Table.programaEduId.withTable()
                        .eq(ProgramasEducativo_Table.programaEduId.withTable()))
                .innerJoin(CargaCursos.class)
                .on(PlanCursos_Table.planCursoId.withTable()
                        .eq(CargaCursos_Table.planCursoId.withTable()))


                //#region Detalle Curso
                .innerJoin(CargaAcademica.class)
                .on(CargaCursos_Table.cargaAcademicaId.withTable()
                        .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                .innerJoin(Seccion.class)
                .on(Seccion_Table.seccionId.withTable()
                        .eq(CargaAcademica_Table.seccionId.withTable()))
                .innerJoin(Periodo.class)
                .on(Periodo_Table.periodoId.withTable()
                        .eq(CargaAcademica_Table.periodoId.withTable()))
                //#endregion Detalle


                .where(PlanEstudios_Table.planEstudiosId.withTable()
                        .eq(CargaAcademica_Table.idPlanEstudio.withTable()))
                .and(CargaCursos_Table.cargaCursoId.withTable().in(cargaCursoIdList))
                //7: Creado, 11 = eliminado, 33: actyalizado, 78 = autorizado, 109 : termindado
                .queryCustomList(CursoCustom.class);
    }


    @Override
    public List<CursoCustom> obtenerPorCargaAcademicaDocente(int idProgramaEducativo, List<Integer> integerList, int anioAcademicoId) {

        Empleado empleado = SQLite.select()
                .from(Empleado.class)
                .where(Empleado_Table.personaId.eq(SessionUser.getCurrentUser().getPersonaId()))
                .querySingle();


        List<CursoCustom> cursoAcademico = obtenerPorCargaCursos(integerList);
        CursoCustom cargaCursosActual = new CursoCustom();
        for (CursoCustom cursoCustom: cursoAcademico){
            cargaCursosActual.setPeriodoId(cursoCustom.getPeriodoId());
            cargaCursosActual.setSeccionId(cursoCustom.getSeccionId());
        }
        List<Integer> cargaCursoIdList = new ArrayList<>();
        if(empleado!=null){

            List<CargaCursos> cargaCursosList = SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                    .from(CargaCursos.class)
                    .innerJoin(CargaCursoDocente.class)
                    .on(CargaCursoDocente_Table.cargaCursoId.withTable()
                            .eq(CargaCursos_Table.cargaCursoId.withTable()))
                    .innerJoin(CargaAcademica.class)
                    .on(CargaCursos_Table.cargaAcademicaId.withTable()
                            .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                    .where(CargaCursoDocente_Table.docenteId.withTable().eq(empleado.getEmpleadoId()))
                    .and(CargaCursos_Table.complejo.withTable().eq(1))
                    .and(CargaAcademica_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                    .queryList();


            cargaCursosList.addAll(SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                    .from(CargaCursos.class)
                    .innerJoin(CargaAcademica.class)
                    .on(CargaCursos_Table.cargaAcademicaId.withTable()
                            .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                    .where(CargaCursos_Table.empleadoId.withTable().eq(empleado.getEmpleadoId()))
                    .and(CargaCursos_Table.complejo.withTable().eq(0))
                    .and(CargaAcademica_Table.idAnioAcademico.withTable().eq(anioAcademicoId))
                    .queryList());


            for (CargaCursos cargaCursos: cargaCursosList)
                cargaCursoIdList.add(cargaCursos.getCargaCursoId());

        }
        List<CursoCustom>cursoCustomList = new ArrayList<>();
        List<CursoCustom> listCursoCurstom = obtenerPorCargaCursos(cargaCursoIdList);
        for (CursoCustom cursoCustom : listCursoCurstom)
            if (cursoCustom.getPeriodoId()==cargaCursosActual.getPeriodoId() && cursoCustom.getSeccionId()==cargaCursosActual.getSeccionId())
                cursoCustomList.add(cursoCustom);

        return cursoCustomList;
    }

}