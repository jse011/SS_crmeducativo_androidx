package com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.local;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.dao.curso.CursoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.grupoDeEquipo.GrupoDeEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio.ParametrosDisenioDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CursoCustom;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.CursoGrupoUi;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public class GroupLocalDataSource implements GroupDataSource {

    private static final String TAG = GroupLocalDataSource.class.getSimpleName();
    private GrupoDeEquipoDao grupoDeEquipoDao;
    private CursoDao cursoDao;
    private ParametrosDisenioDao parametrosDisenioDao;

    public GroupLocalDataSource(GrupoDeEquipoDao grupoDeEquipoDao, CursoDao cursoDao, ParametrosDisenioDao parametrosDisenioDao) {
        this.grupoDeEquipoDao = grupoDeEquipoDao;
        this.cursoDao = cursoDao;
        this.parametrosDisenioDao = parametrosDisenioDao;
    }


    @Override
    public void getGroups(int idCargaCurso,int idCargaAcademica ,Boolean tipo ,int idProgramaEducativo,GetGroupsCallback callback) {

        Log.d(TAG, "getGroups idCargaCurso: " + idCargaCurso);
        Log.d(TAG, "getGroups idCargaAcademica: " + idCargaAcademica);
        Log.d(TAG, "getGroups tipo: " + tipo);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(idCargaCurso);

        List<CursoCustom> cursoCustomList;
        List<Integer> listCursoId = new ArrayList<>();

        CargaAcademica cargaAcademica = SQLite.select()
                .from(CargaAcademica.class)
                .where(CargaAcademica_Table.cargaAcademicaId.eq(idCargaAcademica))
                .querySingle();

        int anioAcademicoId =  cargaAcademica!=null?cargaAcademica.getIdAnioAcademico(): 0;

        if(tipo) cursoCustomList = cursoDao.obtenerPorCargaAcademicaDocente(idProgramaEducativo, integerList, anioAcademicoId);
        else cursoCustomList = cursoDao.obtenerPorCargaCursos(integerList);
        for (CursoCustom cursoCustom : cursoCustomList)listCursoId.add(cursoCustom.getCargaCursoId());



        List<GrupoEquipoC> grupoDeEquipos;
        if (tipo) grupoDeEquipos = grupoDeEquipoDao.getGruposConEquiposEIntegrantesPorCargaCursoList(listCursoId);
        else grupoDeEquipos = grupoDeEquipoDao.getGruposConEquiposEIntegrantesPorCargaCursoList(integerList);

        List<Group> groupList = Group.transform(grupoDeEquipos);


        CursoCustom cursoCustom = null;
        for (CursoCustom itemCursoCustom: cursoCustomList) {
            if ( itemCursoCustom.getCargaCursoId() == idCargaCurso) {
                cursoCustom = itemCursoCustom;
                break;
            }
        }
        if(cursoCustom != null){
            cursoCustomList.remove(cursoCustom);
            cursoCustomList.add(0, cursoCustom);
        }


        List<Object> objectList= new ArrayList<>();

        for(CursoCustom itemCursoCustom: cursoCustomList) {

            CursoGrupoUi cursoGrupoUi = new CursoGrupoUi();
            List<Group> groups= new ArrayList<>();
            Log.d(TAG, "groupList "+ groupList.size());
            for (Group group : groupList) {
                if (Integer.parseInt(group.getCargaCursoId()) == itemCursoCustom.getCargaCursoId()) {
                    group.setCursoGrupoUi(cursoGrupoUi);
                    group.setChecked(false);
                    groups.add(group);
                }
            }

            cursoGrupoUi.setNombreCurso(itemCursoCustom.getNombre());
            cursoGrupoUi.setPeriodo(itemCursoCustom.getPeriodo());
            cursoGrupoUi.setSeccion(itemCursoCustom.getSeccion());
            cursoGrupoUi.setGrupos(groups);
            if(!groups.isEmpty()){
                ParametrosDisenio parametrosDisenio = parametrosDisenioDao.obtenerPorCargaCurso(itemCursoCustom.getCargaCursoId());
                if(parametrosDisenio != null){
                    cursoGrupoUi.setColor1(parametrosDisenio.getColor1());
                    cursoGrupoUi.setColor2(parametrosDisenio.getColor2());
                    cursoGrupoUi.setColor3(parametrosDisenio.getColor3());
                }
                objectList.add(cursoGrupoUi);
            }


            objectList.addAll(groups);
        }

        callback.onGroupsLoaded(objectList);
    }

    @Override
    public void elimarGrupoList(String grupoEquipoId, Callback<String> callback) {
        boolean result = grupoDeEquipoDao.elimarGrupoEquipo(grupoEquipoId);
        if(result){
            callback.onLoad(true, "Exito");
        }else {
            callback.onLoad(false, "Error");
        }
    }
    @Override
    public void getGroup(String id, GetGroupCallback callback) {

        GrupoEquipoC grupoEquipo = SQLite.select()
                .from(GrupoEquipoC.class)
                .where(GrupoEquipoC_Table.key.eq(id))
                .querySingle();
        List<EquipoC> equipos = SQLite.select()
                .from(EquipoC.class)
                .where(EquipoC_Table.grupoEquipoId.eq(id))
                .and(EquipoC_Table.estado.isNot(EquipoC.ESTADO_ELIMINADO))
                .queryList();


        List<Team> teamList = new ArrayList<>();
        for (EquipoC equipo:
                equipos) {
            List<EquipoIntegranteC> integrantes =
                    SQLite.select()
                            .from(EquipoIntegranteC.class)
                            .where(EquipoIntegranteC_Table.equipoId.eq(equipo.getKey()))
                            .queryList();

            List<Person> personList = new ArrayList<>();
            for (EquipoIntegranteC integrante:
                    integrantes) {
                Persona persona = SQLite.select()
                        .from(Persona.class)
                        .where(Persona_Table.personaId.eq(integrante.getAlumnoId()))
                        .querySingle();




                if (persona != null){
                    personList.add(new Person(persona.getPersonaId()+"", persona.getFirstName(), persona.getUrlPicture()));
                }
            }

            teamList.add(new Team(equipo.getKey(), String.valueOf(id), equipo.getNombre(), equipo.getOrden(), equipo.getUrlImagen(), personList));
        }

       callback.onGroupLoaded(Group.transform(grupoEquipo));
    }

    @Override
    public void saveSelecionGrupo(final String rubroEvaluacionId, final Group group, final SaveSelecionGrupoCallBack callBack) {
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                final int UNIDIMENCIONAL = 470, BIDIMENCIONAL = 471, BIDIMENCIONAL_DETALLE = 473;

                RubroEvaluacionProcesoC rubroEvaluacionProceso = SQLite.select()
                        .from(RubroEvaluacionProcesoC.class)
                        .where(RubroEvaluacionProcesoC_Table.key.is(rubroEvaluacionId))
                        .querySingle();
                if(rubroEvaluacionProceso == null) throw new Error("Rubro Evaluacion no Existe");

                rubroEvaluacionProceso.setSyncFlag(RubroEvaluacionProcesoC.FLAG_ADDED);
                rubroEvaluacionProceso.save();

                List<EquipoC> equipos = SQLite.select()
                        .from(EquipoC.class)
                        .where(EquipoC_Table.grupoEquipoId.is(group.getId()))
                        .and(EquipoC_Table.estado.isNot(EquipoC.ESTADO_ELIMINADO))
                        .queryList();

                for (EquipoC equipo : equipos) {
                    saveRubroBidimencional(rubroEvaluacionProceso.getKey(),equipo);
                    List<RubroEvalRNPFormulaC> rubrosEvaluacionProcesoList = SQLite.select()
                            .from(RubroEvalRNPFormulaC.class)
                            .where(RubroEvalRNPFormulaC_Table.rubroEvaluacionPrimId.eq(rubroEvaluacionProceso.getKey()))
                            .queryList();

                    for (RubroEvalRNPFormulaC itemRubrosEvaluacionProceso: rubrosEvaluacionProcesoList ){
                        saveRubroBidimencional(itemRubrosEvaluacionProceso.getRubroEvaluacionSecId(),equipo);
                    }
                }

            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                callBack.localSuccess(group, true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callBack.localSuccess(group, false);
            }
        }).build();

        transaction.execute();

    }
    private void saveRubroBidimencional(String rubroEvalProcesoId, EquipoC equipoC) {

        T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC rubroEvaluacionProcesoEquipo = new T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC();
        rubroEvaluacionProcesoEquipo.setEquipoId(equipoC.getKey());
        rubroEvaluacionProcesoEquipo.setRubroEvalProcesoId(rubroEvalProcesoId);
        rubroEvaluacionProcesoEquipo.setNombreEquipo(equipoC.getNombre());
        rubroEvaluacionProcesoEquipo.setSyncFlag(T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC.FLAG_ADDED);
        boolean success = rubroEvaluacionProcesoEquipo.save();
        if (!success)throw new Error("No se guardo el registro en la tabla SesionAprendizaje RelacionEquipo");


        EquipoEvaluacionProcesoC equipoEvaluacionProcesoC = new EquipoEvaluacionProcesoC();
        equipoEvaluacionProcesoC.setRubroEvalProcesoId(rubroEvalProcesoId);
        equipoEvaluacionProcesoC.setEquipoId(rubroEvaluacionProcesoEquipo.getKey());
        equipoEvaluacionProcesoC.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
        boolean successEvalProcesoEquipo = equipoEvaluacionProcesoC.save();
        if (!successEvalProcesoEquipo) throw new Error("equipo evaluacion proceso eval proceso para el grupo");


        List<EquipoIntegranteC> equipoIntegranteList = SQLite.select()
                .from(EquipoIntegranteC.class)
                .where(EquipoIntegranteC_Table.equipoId.is(equipoC.getKey()))
                .queryList();

        for (EquipoIntegranteC equipoIntegrante : equipoIntegranteList) {

            T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC evaluacionProcesoIntegrante = new T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC();
            evaluacionProcesoIntegrante.setRubroEvaluacionEquipoId(rubroEvaluacionProcesoEquipo.getKey());
            evaluacionProcesoIntegrante.setPersonaId(equipoIntegrante.getAlumnoId());
            evaluacionProcesoIntegrante.setSyncFlag(T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC.FLAG_ADDED);
            boolean successIntegrantes = evaluacionProcesoIntegrante.save();
            if (!successIntegrantes)throw new Error("No se guardo el registro en la tabla SesionAprendizajeRelacionEquipo");


            EvaluacionProcesoC evaluacionProceso = new EvaluacionProcesoC();
            evaluacionProceso.setRubroEvalProcesoId(rubroEvalProcesoId);
            evaluacionProceso.setAlumnoId(equipoIntegrante.getAlumnoId());
            evaluacionProceso.setEquipoId(rubroEvaluacionProcesoEquipo.getKey());
            evaluacionProceso.setSyncFlag(EvaluacionProcesoC.FLAG_ADDED);
            boolean successEvalProcesoAlumn = evaluacionProceso.save();
            if (!successEvalProcesoAlumn) throw new Error("evaluacion proceso para el alumno");
        }

    }
}
