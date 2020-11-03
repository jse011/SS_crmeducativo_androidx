package com.consultoraestrategia.ss_crmeducativo.createTeamList;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.CreateTeam;
import com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase.DeleteTeam;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase.CreateGroup;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase.GenerateTeamList;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase.GetAutoincrementedId;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase.GetGroup;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.ui.CreateTeamListActivity;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 20/09/2017.
 */

public class CreateTeamListPresenterImpl implements CreateTeamListPresenter {
    private static final String TAG = CreateTeamListPresenterImpl.class.getSimpleName();

    private CreateTeamListView view;
    private final UseCaseHandler useCaseHandler;
    private final GetAutoincrementedId getAutoincrementedId;
    private final GenerateTeamList generateTeamList;
    private final CreateGroup createGroup;
    private final GetGroup getGroup;
    private final DeleteTeam deleteTeam;
    private final CreateTeam createTeam;
    private final Resources resources;
    private int tipoId;
    private int programaEducativoId;
    private List<Team> deleteTeamUis;
    private long f_CreacionServidor;
    private long f_CreacionLocal;
    private boolean tipoDinamico;
    private int tipoCreacionGrupo;
    private final int GRUPO_DINAMICO=1, GRUPO_ESTATICO=2, GRUPO_UNICO_APRENDIZAJE=4, GRUPO_DIFERENTE_APRENDIZAJE=3;

    public CreateTeamListPresenterImpl(UseCaseHandler useCaseHandler, GetAutoincrementedId getAutoincrementedId, CreateGroup createGroup, GetGroup getGroup, DeleteTeam deleteTeam, CreateTeam createTeam, Resources resources, GenerateTeamList generateTeamList) {
        this.useCaseHandler = useCaseHandler;
        this.getAutoincrementedId = getAutoincrementedId;
        this.createGroup = createGroup;
        this.getGroup = getGroup;
        this.deleteTeam = deleteTeam;
        this.createTeam = createTeam;
        this.resources = resources;
        this.deleteTeamUis = new ArrayList<>();
        this.generateTeamList = generateTeamList;
    }

    @Override
    public void attachView(CreateTeamListView view) {
        Log.d(TAG, "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        if(view==null)return;
        checkGroup();
        if(grupoId!=null)
        switch (tipoCreacionGrupo){
            case GRUPO_ESTATICO:
                view.showFabCrearRubro();
                view.hideImputCrearRubroDinamico();
                break;
            case GRUPO_UNICO_APRENDIZAJE:
                  view.hideDinamicoAndEstatico();
                  GenerateTeam(0);
                break;
            default:
                view.hideFabCrearRubro();
                view.showImputCrearRubroDinamico();
                break;

        }

    }

    private void GenerateTeam(final int cantidadGrupos) {
     Log.d(TAG, "GenerateTeam " + group.getId()+ " tipo creacicion "+ tipoCreacionGrupo);
                useCaseHandler.execute(generateTeamList,
                        new GenerateTeamList.RequestValues(cantidadGrupos, Integer.valueOf(cargaCursoId), group, tipoCreacionGrupo, entidadId, georeferenciaId),
                        new UseCase.UseCaseCallback<GenerateTeamList.ResponseValue>() {
                            @Override
                            public void onSuccess(final GenerateTeamList.ResponseValue responseGenerate) {
                                Log.d(TAG ,"GenerateTeam on success");
                                group.setTeams(responseGenerate.getTeamList());
                                showGroup(group);
                            }

                            @Override
                            public void onError() {
                                group.setTeams(new ArrayList<Team>());
                                showGroup(group);
                                if(tipoCreacionGrupo==GRUPO_DINAMICO)showMessage(R.string.teamlist_msg_invalid_listcantidad);
                                if(tipoCreacionGrupo==GRUPO_DIFERENTE_APRENDIZAJE) showMessage(R.string.msg_empty_estilos_apre_generar);
                            }
                        });




    }


    @Override
    public void onStart() {
        if(view!=null)view.clearFocus();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");


    }

    private String cargaCursoId;
    private String cursoId;
    private String grupoId;
    private Boolean tipoItem;
    private int tipoCreacionGrupoId;
    private int entidadId;
    private int georeferenciaId;

    @Override
    public void setExtras(Bundle extras) {
        Log.d(TAG, "setExtras");
        cargaCursoId = extras.getString(CreateTeamListActivity.EXTRA_CARGA_CURSO_ID);
        cursoId = extras.getString(CreateTeamListActivity.EXTRA_CURSO_ID);
        grupoId = extras.getString(CreateTeamListActivity.EXTRA_GRUPO_EQUIPO_ID, null);
        tipoCreacionGrupo = extras.getInt(CreateTeamListActivity.EXTRA_TIPO_CREACION_GRUPO, 0);
        tipoItem = extras.getBoolean(CreateTeamListActivity.EXTRA_TIPO_ITEM, false);
        tipoCreacionGrupoId = extras.getInt(CreateTeamListActivity.EXTRA_TIPOGRUPO_ID);
        entidadId = extras.getInt(CreateTeamListActivity.EXTRA_ENTIDAD_ID, 0);
        georeferenciaId = extras.getInt(CreateTeamListActivity.EXTRA_GEOREFERENCIA_ID, 0);
        programaEducativoId = extras.getInt(CreateTeamListActivity.EXTRA_PROGRAMA_EDUCATIVO_ID, 0);
    }

    private void checkGroup() {
        if (TextUtils.isEmpty(grupoId)) {
            initGroup();
        } else {
            getGroup(null);
        }
    }

    private void initGroup() {
        saveGroup(new Group(cargaCursoId, false), true);
    }


    private void saveGroup(Group groups, final boolean newGroup) {
        Log.d(TAG, "saveGroup");
        createGroup.execute(new CreateGroup.RequestValues(groups, newGroup), new UseCaseSincrono.Callback<CreateGroup.ResponseValue>() {
            @Override
            public void onResponse(boolean success, CreateGroup.ResponseValue response) {
                Log.d(TAG, "onSuccess  saveGroup");
                group = response.getGroup();
                String error = response.getError();
                if (!TextUtils.isEmpty(error)) {
                    showMessage(R.string.activity_createteamlist_error_alumnos_sin_grupo);
                    return;
                }
                grupoId = group.getId();
                if (!newGroup) {
                    if(deleteTeamUis.size()==0)if(view!=null)view.sync(programaEducativoId);
                    else deleteTeam();
                } else {
                    getGroup(null);
                }
            }
        });

    }


    private Group group;

    private void getGroup(UseCase.UseCaseCallback<GetGroup.ResponseValue> callback) {
        Log.d(TAG, "getGroup "+ grupoId);
        if (grupoId != null) {
            getGroup.execute(new GetGroup.RequestValues(grupoId, entidadId, georeferenciaId), new UseCaseSincrono.Callback<GetGroup.ResponseValue>() {
                @Override
                public void onResponse(boolean success, GetGroup.ResponseValue response) {
                    Log.d(TAG, "success"+ success);
                    group = response.getGroup();
                    if (tipoItem) view.hidecontenedorconfig();
                    else view.showcontenedorconfig();

                    showGroup(group);
                }
            });
        }
    }


    private void showGroup(Group group) {
        showTeamListName(group.getTitle());
        showTeams(group.getTeams());
    }

    private void showTeamListName(String name) {
        if (view != null) {
            if (!TextUtils.isEmpty(name)) {
                view.showTeamListName(name);
            }
        }
    }

    private void showTeams(List<Team> teams) {
        if (view != null) {
            switch (tipoCreacionGrupo){
                case GRUPO_UNICO_APRENDIZAJE:
                    view.showTextEmptyTeamsEstiloApred(teams);
                    break;
                case GRUPO_DINAMICO:
                    view.showTextEmptyTeamsDimamico(teams);
                    break;
                 default:
                     view.showTeams(teams);
            }
        }
    }


    @Override
    public void createTeamList(String nameList) {
        Log.d(TAG, "createTeamList");

        Log.d(TAG, "nameList");

        if (TextUtils.isEmpty(nameList)) {
            showMessage(R.string.teamlist_msg_invalid_listname);
            return;
        }

        List<Team> teamList = view.getTeamList();
        int teamListSize = teamList.size();
        if (teamListSize <= 0) {
            showMessage(R.string.teamlist_msg_invalid_teamcount);
            return;
        }

        group.setTitle(nameList);
        group.setTeams(teamList);
        group.setCargaCursoId(cargaCursoId);
        group.setTipoId(tipoCreacionGrupoId);
        group.setFinished(true);


        saveGroup(group, false);

        //comprovarTeams();

    }


    private void showMessage(@StringRes int message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    @Override
    public void createTeam() {
        if (view != null) {
            int orden = view.getTeamListSize() + 1;
            Log.d(TAG, "cargaCursoId: " + cargaCursoId + ", grupoId, " + grupoId + ", orden: " + orden);
            view.launchCreateTeamActivity(cargaCursoId, grupoId, orden, null, group, entidadId, georeferenciaId);
        }

    }


    private void showMessageTeamRemoved(Team team) {
        if (view != null) {
            view.showTeamRemoveMessage(resources.getString(R.string.activity_teamlist_teamremoved), team);
        }
    }

    private void removeTeam(Team team) {
        if (view != null) {
            view.removeTeam(team);
        }
    }

    private void deleteTeam() {
        useCaseHandler.execute(
                deleteTeam,
                new DeleteTeam.RequestValues(deleteTeamUis),
                new UseCase.UseCaseCallback<DeleteTeam.ResponseValue>() {
                    @Override
                    public void onSuccess(DeleteTeam.ResponseValue response) {
                        if(view!=null)view.sync(programaEducativoId);
                    }

                    @Override
                    public void onError() {

                        //addTeam(team);
                    }
                });
    }

    private void createTeam(final Team team) {
        Log.d(TAG, "createTeam");
        useCaseHandler.execute(
                createTeam,
                new CreateTeam.RequestValues(team),
                new UseCase.UseCaseCallback<CreateTeam.ResponseValue>() {
                    @Override
                    public void onSuccess(CreateTeam.ResponseValue response) {
                        boolean success = response.isSucess();
                        Log.d(TAG, "createTeam success: " + success);

                        if (response.isSucess()) {
                            Log.d(TAG, "team restore: " + team.toString());
                            addTeam(team);
                        } else {
                            showMessage(R.string.activity_createteamlist_error_restoring_team);
                        }
                    }

                    @Override
                    public void onError() {
                        showMessage(R.string.activity_createteamlist_error_restoring_team);
                    }
                }
        );
    }

    private void addTeam(Team team) {
        if (view != null) {
            view.addTeam(team);
        }
    }

    @Override
    public void onSwipeTeam(Team team) {

        Log.d(TAG, "deleteTeam onSucess");
        this.deleteTeamUis.add(team);

        int position= group.getTeams().indexOf(team);
        if(position!=-1)group.getTeams().remove(team);

        removeTeam(team);
        showMessageTeamRemoved(team);
        //
    }

    @Override
    public void onRestoreTeam(Team team) {
        Log.d(TAG, "onRestoreTeam");
        createTeam(team);
    }

    @Override
    public void onTeamSelected(Team team) {
        Log.d(TAG, "onTeamSelected: ");
        if (view != null) {
            if (tipoItem != true)
                view.launchCreateTeamActivity(cargaCursoId, grupoId, team.getOrden(), team, null, entidadId, georeferenciaId);
        }
    }

    @Override
    public void onSaveTeam(Team mTeam) {
        try {
            if (TextUtils.isEmpty(mTeam.getId())) {
                mTeam.setId(IdGenerator.generateId());
            }
            mTeam.setGroup(group);

            List<Team> teams = group.getTeams();
            int posicion = teams.indexOf(mTeam);
            if (posicion != -1) {
                teams.set(posicion, mTeam);
            } else {
                teams.add(mTeam);
            }

            if (view != null) view.updateTeam(mTeam);
        } catch (Exception e) {
            Log.d(TAG, "Error Actualizar Team: " + e);
        }


        /*
        useCaseHandler.execute(
                createTeam,
                new CreateTeam.RequestValues(team),
                new UseCase.UseCaseCallback<CreateTeam.ResponseValue>() {
                    @Override
                    public void onSuccess(CreateTeam.ResponseValue response) {
                        boolean success = response.isSucess();
                        if (success) {
                            getGroup();
                        } else {
                            showError(R.string.unknown_error);
                        }
                    }

                    @Override
                    public void onError() {
                        showError(R.string.unknown_error);
                    }
                }
        );*/
    }

    private void showError(@StringRes int error) {
        if (view != null) {
            view.showError(error);
        }
    }

    @Override
    public void onBtnActualizarEquipo() {
        BEVariables beVariables = new BEVariables();
        beVariables.setGrupoEquipoId(grupoId);
        if (view != null) view.showActividadImportacion(beVariables);
    }

    @Override
    public void onSuccesUpdateGrupo() {
        checkGroup();
    }

    @Override
    public void onCantidadGruposChanged(CharSequence s) {

        try {
            int cantidadGrupos = Integer.valueOf(s.toString());
                if (cantidadGrupos < 1) {
                    //view.clearFocus();
                    showMessage(R.string.teamlist_msg_invalid_listcantidad);
                    group.setTeams(new ArrayList<Team>());
                    showGroup(group);
                }else if(cantidadGrupos==1){
                    group.setTeams(new ArrayList<Team>());
                    showGroup(group);
                }else {
                    //ramdom
                    GenerateTeam(cantidadGrupos);
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu() {
        if (TextUtils.isEmpty(grupoId)) {
            if(view!=null)view.changeMenuItemCrear();
        } else {
            if(view!=null)view.changeMenuItemEditar();
        }
    }


    private void comprobarFechaRubroEvaluacion() {
        Log.d(TAG, "comprobarFechaGrupoEquipo: " + f_CreacionServidor + " > " + f_CreacionLocal);
        if (f_CreacionServidor > f_CreacionLocal) {
            visibleMsgActualizacion();
        } else {
            ocultarMsgActualizacion();
        }
    }

    private void visibleMsgActualizacion() {
        if (view != null) view.showMsgActualizacion();
    }

    private void ocultarMsgActualizacion() {
        if (view != null) view.hideMsgActualizacion();
    }
}
