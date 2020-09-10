package com.consultoraestrategia.ss_crmeducativo.main.data.source.remote;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.Aula;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaAcademica_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocenteDet;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Cursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CursosDetHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleHorario;
import com.consultoraestrategia.ss_crmeducativo.entities.Dia;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Hora;
import com.consultoraestrategia.ss_crmeducativo.entities.Horario;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioDia;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioHora;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;
import com.consultoraestrategia.ss_crmeducativo.entities.NivelAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametroConfiguracion;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.Periodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanCursos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios;
import com.consultoraestrategia.ss_crmeducativo.entities.PlanEstudios_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo;
import com.consultoraestrategia.ss_crmeducativo.entities.ProgramasEducativo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas;
import com.consultoraestrategia.ss_crmeducativo.entities.Seccion;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetAccesosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetCursosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetHijosListCallback;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.callbacks.GetUsuarioCallback;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosAnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosInicioSesion;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class RemoteDataSource implements MainDataSource {


    private final  static String TAG = RemoteDataSource.class.getSimpleName();

    @Override
    public boolean succesData() {
        return false;
    }

    @Override
    public void uploadFile(PersonaUi request, SucessCallback<String> stringSucessCallback) {

    }

    @Override
    public void savePathPersona(PersonaUi personaUi) {

    }

    @Override
    public void getAccesosUIList(int idUsuario, int hijo, GetAccesosListCallback callback) {

    }


    @Override
    public void getHijosUIList(int idUsuario, int idHijo, GetHijosListCallback callback) {

    }

    @Override
    public void getCursosUIList(int idUsuario, int idPrograma, int idAnioAcademico, GetCursosListCallback callback) {

    }


    @Override
    public void getUsuarioUI(GetUsuarioCallback callback) {

    }

    @Override
    public void getPeriodoList(int anioAcademico, int programaEducativoId, SucessCallback<List<PeriodoUi>> sucessCallback) {

    }

    @Override
    public void getGradosList(int idPrograma, int idUsuario, int idAnioAcademico, SucessCallback<List<GradoUi>> sucessCallback) {

    }

    @Override
    public boolean saveAlarma(int hora, int minute) {
        return false;
    }

    @Override
    public AlarmaUi getHoraAlarma() {
        return null;
    }

    @Override
    public List<AnioAcademicoUi> getListAnioAcademico(int usuarioId) {
        return null;
    }

    @Override
    public List<ProgramaEduactivosUI> getListProgramaEducativo(int anioCademico, int usuarioId) {
        return null;
    }

    @Override
    public RetrofitCancel getDatosInicioSesion(final int empleadoId, final int anioId, final SucessCallback<Boolean> callback) {
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(20,30,30, TimeUnit.SECONDS);
        RetrofitCancel<BEDatosAnioAcademico> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_getDatosAnioAcademico(empleadoId, anioId));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosAnioAcademico>() {
            @Override
            public void onResponse(final BEDatosAnioAcademico response) {
                if(response == null){
                    callback.onLoad(false, null);
                    Log.d(TAG,"response usuario null");
                }else {
                    Log.d(TAG,"response usuario true");

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<Integer> cargaCursoIdLis = new ArrayList<>();
                            List<Integer> cargaCursoDocenteIdList = new ArrayList<>();
                            List<Integer> cargaAcademicaIdList = new ArrayList<>();
                            List<Integer> planCursoIdList = new ArrayList<>();
                            List<Integer> planEstudioIdList = new ArrayList<>();
                            List<Integer> programaEducativoIdList = new ArrayList<>();
                            List<Integer> silaboEventoIdList = new ArrayList<>();
                            List<Integer> cargaCursoCalendarioPeriodoIdList = new ArrayList<>();
                            List<Integer> calendarioPeriodoIdList = new ArrayList<>();
                            List<Integer> calendarioAcademicoIdList = new ArrayList<>();

                            //#region Carga CUrso
                            List<CargaCursoDocente> cargaCursosComplejoList = SQLite.select(Utils.f_allcolumnTable(CargaCursoDocente_Table.ALL_COLUMN_PROPERTIES))
                                    .from(CargaCursoDocente.class)
                                    .innerJoin(CargaCursos.class)
                                    .on(CargaCursoDocente_Table.cargaCursoId.withTable()
                                            .eq(CargaCursos_Table.cargaCursoId.withTable()))
                                    .innerJoin(CargaAcademica.class)
                                    .on(CargaCursos_Table.cargaAcademicaId.withTable()
                                            .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                                    .where(CargaCursoDocente_Table.docenteId.withTable().eq(empleadoId))
                                    .and(CargaCursos_Table.complejo.withTable().eq(1))
                                    .and(CargaAcademica_Table.idAnioAcademico.withTable().eq(anioId))
                                    .queryList(databaseWrapper);

                            for (CargaCursoDocente cargaCursoDocente : cargaCursosComplejoList) {
                                cargaCursoDocenteIdList.add(cargaCursoDocente.getCargaCursoDocenteId());
                                cargaCursoIdLis.add(cargaCursoDocente.getCargaCursoId());
                            }

                            List<CargaCursos> cargaCursosList = SQLite.select()
                                    .from(CargaCursos.class)
                                    .where(CargaCursos_Table.cargaCursoId.in(cargaCursoIdLis))
                                    .queryList(databaseWrapper);

                            cargaCursosList.addAll(SQLite.select(Utils.f_allcolumnTable(CargaCursos_Table.ALL_COLUMN_PROPERTIES))
                                    .from(CargaCursos.class)
                                    .innerJoin(CargaAcademica.class)
                                    .on(CargaCursos_Table.cargaAcademicaId.withTable()
                                            .eq(CargaAcademica_Table.cargaAcademicaId.withTable()))
                                    .where(CargaCursos_Table.empleadoId.withTable().eq(empleadoId))
                                    .and(CargaCursos_Table.complejo.withTable().eq(0))
                                    .and(CargaAcademica_Table.idAnioAcademico.withTable().eq(anioId))
                                    .queryList(databaseWrapper));

                            for (CargaCursos cargaCursos : cargaCursosList) {
                                cargaCursoIdLis.add(cargaCursos.getCargaCursoId());
                                cargaAcademicaIdList.add(cargaCursos.getCargaAcademicaId());
                                planCursoIdList.add(cargaCursos.getPlanCursoId());
                            }

                            List<CargaAcademica> cargaAcademicaList = SQLite.select()
                                    .from(CargaAcademica.class)
                                    .where(CargaAcademica_Table.cargaAcademicaId.in(cargaAcademicaIdList))
                                    .queryList(databaseWrapper);

                            for (CargaAcademica cargaAcademica : cargaAcademicaList) {
                                planEstudioIdList.add(cargaAcademica.getIdPlanEstudio());
                            }

                            //#endregion

                            //region Plan Estudio
                            List<PlanEstudios> planEstudiosList = SQLite.select()
                                    .from(PlanEstudios.class)
                                    .where(PlanEstudios_Table.planEstudiosId.in(planEstudioIdList))
                                    .queryList();
                            for (PlanEstudios planEstudios : planEstudiosList) {
                                programaEducativoIdList.add(planEstudios.getProgramaEduId());
                            }
                            //endregion Plan Estudio

                            //region silabo
                            List<SilaboEvento> silaboEventoList = SQLite.select()
                                    .from(SilaboEvento.class)
                                    .where(SilaboEvento_Table.anioAcademicoId.eq(anioId))
                                    .and(SilaboEvento_Table.docenteId.eq(empleadoId))
                                    .queryList();

                            silaboEventoList.addAll(SQLite.select()
                                    .from(SilaboEvento.class)
                                    .where(SilaboEvento_Table.cargaCursoId.in(cargaCursoIdLis))
                                    .queryList());

                            for (SilaboEvento silaboEvento : silaboEventoList) {
                                silaboEventoIdList.add(silaboEvento.getSilaboEventoId());
                            }
                            //endregion

                            List<CargaCursoCalendarioPeriodo> cargaCursoCalendarioPeriodoList = SQLite.select()
                                    .from(CargaCursoCalendarioPeriodo.class)
                                    .where(CargaCursoCalendarioPeriodo_Table.cargaCursoId.in(cargaCursoIdLis))
                                    .queryList();

                            for (CargaCursoCalendarioPeriodo cargaCursoCalendarioPeriodo : cargaCursoCalendarioPeriodoList) {
                                cargaCursoCalendarioPeriodoIdList.add(cargaCursoCalendarioPeriodo.getCargaCursoCalendarioPeriodoId());
                            }

                            //#region Calendario
                            List<CalendarioPeriodo> calendarioPeriodoList = SQLite.select()
                                    .from(CalendarioPeriodo.class)
                                    .innerJoin(CalendarioAcademico.class)
                                    .on(CalendarioAcademico_Table.calendarioAcademicoId.withTable().eq(CalendarioPeriodo_Table.calendarioAcademicoId.withTable()))
                                    .where(CalendarioAcademico_Table.idAnioAcademico.withTable().eq(anioId))
                                    .queryList();

                            for (CalendarioPeriodo calendarioPeriodo : calendarioPeriodoList) {
                                calendarioPeriodoIdList.add(calendarioPeriodo.getCalendarioPeriodoId());
                                calendarioAcademicoIdList.add(calendarioPeriodo.getCalendarioAcademicoId());
                            }

                            //#endregion

                            Log.d(TAG, "cargaCursoIdLis: " + cargaCursoIdLis.size());
                            Log.d(TAG, "cargaCursoDocenteIdList: " + cargaCursoDocenteIdList);
                            Log.d(TAG, "cargaAcademicaIdList: " + cargaAcademicaIdList);
                            Log.d(TAG, "planCursoIdList: " + planCursoIdList);
                            Log.d(TAG, "planEstudioIdList: " + planEstudioIdList);
                            Log.d(TAG, "programaEducativoIdList: " + programaEducativoIdList);
                            Log.d(TAG, "silaboEventoIdList: " + silaboEventoIdList);
                            Log.d(TAG, "cargaCursoCalendarioPeriodoIdList: " + cargaCursoCalendarioPeriodoIdList);
                            Log.d(TAG, "calendarioAcademicoIdList: " + calendarioAcademicoIdList);

                            TransaccionUtils.deleteTable(CargaCursos.class, CargaCursos_Table.cargaCursoId.in(cargaCursoIdLis));
                            TransaccionUtils.deleteTable(CargaCursoDocente.class, CargaCursoDocente_Table.cargaCursoDocenteId.in(cargaCursoDocenteIdList));
                            TransaccionUtils.deleteTable(CargaAcademica.class, CargaAcademica_Table.cargaAcademicaId.in(cargaAcademicaIdList));
                            TransaccionUtils.deleteTable(PlanCursos.class, PlanCursos_Table.planCursoId.in(planCursoIdList));
                            TransaccionUtils.deleteTable(PlanEstudios.class, PlanEstudios_Table.planEstudiosId.in(planEstudioIdList));
                            TransaccionUtils.deleteTable(ProgramasEducativo.class, ProgramasEducativo_Table.programaEduId.in(programaEducativoIdList));
                            TransaccionUtils.deleteTable(SilaboEvento.class, SilaboEvento_Table.silaboEventoId.in(silaboEventoIdList));
                            TransaccionUtils.deleteTable(CargaCursoCalendarioPeriodo.class, CargaCursoCalendarioPeriodo_Table.cargaCursoCalendarioPeriodoId.in(cargaCursoCalendarioPeriodoIdList));
                            TransaccionUtils.deleteTable(CalendarioPeriodo.class, CalendarioPeriodo_Table.calendarioPeriodoId.in(calendarioPeriodoIdList));
                            TransaccionUtils.deleteTable(CalendarioAcademico.class, CalendarioAcademico_Table.calendarioAcademicoId.in(calendarioAcademicoIdList));

                            /*TransaccionUtils.fastStoreListInsert(AnioAcademico.class, response.getAnioAcademicos(), databaseWrapper, true);*/

                            TransaccionUtils.fastStoreListInsert(Aula.class, response.getAulas(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaAcademica.class, response.getCargasAcademicas(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoDocente.class, response.getCargaCursoDocente(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoDocenteDet.class, response.getCargaCursoDocenteDet(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursos.class, response.getCargaCursos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Cursos.class, response.getCursos(), databaseWrapper, true);
                            //TransaccionUtils.fastStoreListInsert(Empleado.class, response.getEmpleados(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(NivelAcademico.class, response.getNivelesAcademicos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ParametrosDisenio.class, response.getParametrosDisenio(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Periodo.class, response.getPeriodos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(PlanCursos.class, response.getPlanCursos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(PlanEstudios.class, response.getPlanEstudios(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ProgramasEducativo.class, response.getProgramasEducativos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Seccion.class, response.getSecciones(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(SilaboEvento.class, response.getSilaboEvento(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CalendarioPeriodo.class, response.getCalendarioPeriodos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CalendarioPeriodoDetalle.class, response.getCalendarioPeriodoDetalles(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoCalendarioPeriodo.class, response.getCargaCursoCalendarioPeriodo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CalendarioAcademico.class, response.getCalendarioAcademico(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Rutas.class, response.getRutas(), databaseWrapper, true);

                            TransaccionUtils.fastStoreListInsert(Hora.class, response.getHora(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(HorarioPrograma.class, response.getHorarioPrograma(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(HorarioHora.class, response.getHorarioHora(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(DetalleHorario.class, response.getDetalleHorario(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Dia.class, response.getDia(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(HorarioDia.class, response.getHorarioDia(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CursosDetHorario.class, response.getCursosDetHorario(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Horario.class, response.getHorario(), databaseWrapper, true);


                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callback.onLoad(true, true);
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            callback.onLoad(false, false);
                        }
                    }).build();

                    transaction.execute();


                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onLoad(false,false);
                t.printStackTrace();
                Log.d(TAG,"onFailure ");
            }
        });

        return retrofitCancel;
    }

    @Override
    public RetrofitCancel updateListAnioAcademico(int usuarioId, final SucessCallback<Boolean> callback) {
        final ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,10,10, TimeUnit.SECONDS);
        RetrofitCancel<BEDatosInicioSesion> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_getDatosInicioSesion(usuarioId));

        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosInicioSesion>() {

            @Override
            public void onResponse(final BEDatosInicioSesion beDatosInicioSesion) {
                if(beDatosInicioSesion == null){
                    callback.onLoad(false, null);
                    Log.d(TAG,"response update anio academico usuario null");
                }else {
                    Log.d(TAG,"response update anio academico true");


                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            TransaccionUtils.fastStoreListInsert(Empleado.class, beDatosInicioSesion.getEmpleados(), databaseWrapper, true);
                            TransaccionUtils.deleteTable(databaseWrapper, AnioAcademico.class);
                            TransaccionUtils.fastStoreListInsert(AnioAcademico.class, beDatosInicioSesion.getAnioAcademicos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ParametroConfiguracion.class, beDatosInicioSesion.getParametroConfiguracion(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callback.onLoad(true, true);

                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            callback.onLoad(false, false);
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onLoad(false,null);
                t.printStackTrace();
                Log.d(TAG,"update anio academico onFailure ");
            }
        });
        return retrofitCancel;
    }

    @Override
    public RetrofitCancel updatePersona(PersonaUi personaUi, SucessCallback<Boolean> callback) {
        final ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,10,10, TimeUnit.SECONDS);
        RetrofitCancel<Integer> retrofitCancel = null;
        Persona persona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.eq(personaUi.getPersonaId()))
                .querySingle();

        if(persona!=null){
            persona.setCorreo(personaUi.getCorreo());
            persona.setCelular(personaUi.getCelular());
            List<Persona> personaList = new ArrayList<>();
            personaList.add(persona);
            retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.fupd_SimplePersonas(personaList));
            retrofitCancel.enqueue(new RetrofitCancel.Callback<Integer>() {
                @Override
                public void onResponse(Integer response) {
                    if(response == null){
                        callback.onLoad(false, null);
                        Log.d(TAG,"response peronsa null");
                    }else {
                        Log.d(TAG,"response peronsa true");

                        persona.save();
                        callback.onLoad(true, null);

                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    callback.onLoad(false, null);
                }
            });
        }else {
            callback.onLoad(false, null);
        }


        return retrofitCancel;
    }

}
