package com.consultoraestrategia.ss_crmeducativo.grouplist;

import android.os.Handler;
import androidx.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase.EliminarGrupoList;
import com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase.GetGroup;
import com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase.GetGroups;
import com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase.SaveSeleccionGroup;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.CursoGrupoUi;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.TipoGrupoUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 15/09/2017.
 */

public class GroupListPresenterImpl implements GroupListPresenter {

    private static final String TAG = GroupListPresenterImpl.class.getSimpleName();
    private int GRUPO_DINAMICO=1, GRUPO_ESTATICO=2, GRUPO_UNICO_APRENDIZAJE=4, GRUPO_DIFERENTE_APRENDIZAJE=3;
    private GroupListView view;
    private UseCaseHandler useCaseHandler;
    private GetGroups getGroups;
    private EliminarGrupoList eliminarGrupoList;
    private Boolean tipoListaGrupos= false;
    private Boolean tipoItem= false;
    private Boolean filterButton = false;
    private GetGroup getGroup;
    private SaveSeleccionGroup saveSeleccionGroup;
    private List<Object>  cursoGrupoUiList = new ArrayList<>();

    public GroupListPresenterImpl(UseCaseHandler useCaseHandler, GetGroups getGroups, EliminarGrupoList eliminarGrupoList,GetGroup getGroup, SaveSeleccionGroup saveSeleccionGroup ) {
        Log.d(TAG, "GroupListPresenterIonFilterListGruposmpl");
        this.useCaseHandler = useCaseHandler;
        this.getGroups = getGroups;
        this.eliminarGrupoList = eliminarGrupoList;
        this.getGroup= getGroup;
        this.saveSeleccionGroup=saveSeleccionGroup;
    }


    @Override
    public void attachView(GroupListView view) {
        Log.d(TAG, "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart() {
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
        view = null;
    }

    private void getGroups() {

        Log.d(TAG, "getGroups tipo " + tipoListaGrupos+ " mIdCargaCurso  " + mIdCargaCurso + " mIdCargaAcademica "  +mIdCargaAcademica + "mIdProgramaEducativo" + mIdProgramaEducativo);
        cursoGrupoUiList.clear();
        if(view!=null)view.showProgress();
        useCaseHandler.execute(
                getGroups,
                new GetGroups.RequestValues(mIdCargaCurso, mIdCargaAcademica, tipoListaGrupos, mIdProgramaEducativo),
                new UseCase.UseCaseCallback<GetGroups.ResponseValue>() {
                    @Override
                    public void onSuccess(GetGroups.ResponseValue response) {
                        Log.d(TAG, "getGroups onSuccess" + response.getCursoGrupoList().size());
                        for (Object o : response.getCursoGrupoList())if (o instanceof CursoGrupoUi){
                            CursoGrupoUi cursoGrupoUi = (CursoGrupoUi) o;
                        }
                        cursoGrupoUiList = response.getCursoGrupoList();
                        showGroups(cursoGrupoUiList);
                        if(view!=null)view.hideProgress();
                    }

                    @Override
                    public void onError() {
                        showMessage(R.string.group_msg_error_loading_groups);
                    }
                }

        );
    }

    private void showMessage(@StringRes int message) {
        if (view != null) {
            view.showMessage(message);
        }
    }

    private void showGroups(List<Object> cursoGrupoList) {

        Log.d(TAG, "showGroupsTnamanio" + cursoGrupoList.size());
        Log.d(TAG, "showGroups");
        if (view != null) {
            List<Object> lista = new ArrayList<>();
            int posicion = 0;
            for (Object o : cursoGrupoList){
                if(o instanceof CursoGrupoUi){
                    CursoGrupoUi cursoGrupo = (CursoGrupoUi)o;
                    if(cursoGrupo.getGrupos().size()>0){
                        for(Group group: cursoGrupo.getGrupos()){
                            for (Team team: group.getTeams()){
                                if(posicion > 10)posicion=0;
                                refactorizarTeam(team, posicion);
                                posicion ++;
                            }

                        }
                        lista.add(cursoGrupo);
                    }
                }else {
                    lista.add(o);
                }

            }

            if (cursoGrupoList.size() > 0) {
                Log.d(TAG, "showGroups size: " + tipoItem);
                if(view!=null){
                    view.showRecyclerView();
                    view.showGroupList(lista, tipoItem, String.valueOf(mIdCargaCurso));
                    view.hideTextEmpty();
                }
            } else {

                if(filterButton){
                    if(view!=null)view.showEmpty();
                }else{
                    if(view!=null)view.showTextEmptyTipoTwo();
                }

                if(view!=null)view.clearGroupList();

            }

        }
    }

    private void refactorizarTeam(Team team, int posicion){
        String etiqueta = team.getName();
        if(!TextUtils.isEmpty(etiqueta)){
            try {
                etiqueta = String.valueOf(etiqueta.charAt(0)).toUpperCase() + String.valueOf(etiqueta.charAt(1)).toUpperCase();
            }catch (Exception e){
                etiqueta = String.valueOf(etiqueta.charAt(0)).toUpperCase();;
            }
        }

        team.setEtiqueta(etiqueta);

        if(posicion > 10)posicion = 0;

        switch (posicion){
            case 0:
                team.setColorFondo(R.color.md_teal_A700);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 1:
                team.setColorFondo(R.color.md_green_500);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 2:
                team.setColorFondo(R.color.md_blue_500);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 3:
                team.setColorFondo(R.color.md_purple_300);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 4:
                team.setColorFondo(R.color.md_blue_grey_600);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 5:
                team.setColorFondo(R.color.md_yellow_A200);
                team.setColorTexto(R.color.md_black_1000);
                break;
            case 6:
                team.setColorFondo(R.color.md_amber_900);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 7:
                team.setColorFondo(R.color.md_red_500);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 8:
                team.setColorFondo(R.color.md_grey_400);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 9:
                team.setColorFondo(R.color.md_grey_600);
                team.setColorTexto(R.color.md_white_1000);
                break;
            case 10:
                team.setColorFondo(R.color.md_pink_A400);
                team.setColorTexto(R.color.md_white_1000);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
    }


    private int mIdCargaCurso;
    private int mIdCurso;
    private int mIdCargaAcademica;
    private String mIdRubroEvaluacion;
    private int mIdProgramaEducativo;
    private int mIdEntidad;
    private int mIdGeoreferencia;


    @Override
    public void setExtras(int mIdCargaCurso, int mIdCurso, int idCargaAcademica, String mIdRubroEvaluacion, int mIdProgramaEducativo, int mIdEntidad, int mIdGeoreferencia) {
        this.mIdCargaCurso = mIdCargaCurso;
        this.mIdCurso = mIdCurso;
        this.mIdCargaAcademica =  idCargaAcademica;
        this.mIdRubroEvaluacion= mIdRubroEvaluacion;
        this.mIdProgramaEducativo = mIdProgramaEducativo;
        this.mIdEntidad = mIdEntidad;
        this.mIdGeoreferencia = mIdGeoreferencia;

        if(mIdRubroEvaluacion!= null){
            tipoItem=true;
            tipoListaGrupos=false;
            filterButton = true;
        }

    }



    @Override
    public void onGroupSelected(Group group) {
        Log.d(TAG, "onGroupSelected " );
        launchCreateTeamListActivity(String.valueOf(mIdCargaCurso), String.valueOf(mIdCurso), group.getId(),2, group.getTipoId(), mIdEntidad, mIdGeoreferencia, mIdProgramaEducativo);
    }

    @Override
    public void onResumeFragment() {
        getGroups();
        if (view!=null)view.initFabButtom(tipoListaGrupos);
        onViewFilterFragment();
        Log.d(TAG,"onResumeFragment");
    }

    @Override
    public void onChecketGrupo(Group group) {
        if(view == null)return;

        if(this.group != null && !group.equals(this.group)){
            this.group.setChecked(false);
            if(view!=null)view.updateList(this.group);
        }

        if(group.isChecked()){
            group.setChecked(false);
        }else{
            group.setChecked(true);
        }

        this.group = group;
        if(view!=null)view.updateList(group);
    }

    @Override
    public void onClickEliminarListaGrupos(Group group) {
        eliminarGrupoList(group);
    }

    @Override
    public void onParentFabClickedCrearGrupo(boolean isAprendizajeColegio) {
        Log.d(TAG,"onParentFabClickedCrearGrupo");
        if (isAprendizajeColegio)
            view.showListFilterChooser("Seleccione", TipoGrupoUi.values());
        else {
                TipoGrupoUi[] tipoGrupoUis = new TipoGrupoUi[2];
                System.arraycopy(TipoGrupoUi.values(), 0, tipoGrupoUis, 0, 2);
                view.showListFilterChooser("Seleccione", tipoGrupoUis);
        }


    }

    @Override
    public void onItemClickTipoGrupo(TipoGrupoUi tipoGrupoUi) {
        int tipoGrupoId = 0;
        int valorGrupo = 0;
        switch (tipoGrupoUi){
            case DINAMICO:
                tipoGrupoId = 449;
                valorGrupo = GRUPO_DINAMICO;
                break;
            case ESTATICO:
                tipoGrupoId = 446;
                valorGrupo = GRUPO_ESTATICO;
                break;
            case APRENDIZAJE_DIFERENTE:
                valorGrupo = GRUPO_DIFERENTE_APRENDIZAJE;
                break;
            case APRENDIZAJE_UNICO:
                valorGrupo = GRUPO_UNICO_APRENDIZAJE;
                break;
        }
        launchCreateTeamListActivity(String.valueOf(mIdCargaCurso), String.valueOf(mIdCurso), null, valorGrupo, tipoGrupoId, mIdEntidad, mIdGeoreferencia, mIdProgramaEducativo);
    }

    @Override
    public void onFilterListGrupos() {
        if(tipoListaGrupos)this.tipoListaGrupos=false;
        else this.tipoListaGrupos=true;
        getGroups();

    }

    @Override
    public void onClickSaveGrupoSesion() { if(group != null){
            if(group.isChecked()){
                Log.d(TAG, "group.isChecked()");
                showDialogEspera();
                saveGrupoSesion();
            }else{
                showMessage(R.string.sesion_evaluacion_grupo_message_grupo_no_asignado);
            }
        }else{
            showMessage(R.string.sesion_evaluacion_grupo_message_grupo_no_asignado);
        }
    }

    @Override
    public void onViewCreated() {
        if(view!=null)view.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(view!=null)view.hideProgress();
                getGroups();
            }
        }, 1000);

        if (view!=null)view.initFabButtom(tipoListaGrupos);
    }



    private void launchCreateTeamListActivity(String mIdCargaCurso, String mIdCurso, String grupoEquipoId, int tipoGrupo, int tipoCreacionGrupoId, int entidadId, int georeferenciaId, int mIdProgramaEducativo) {
        if (view != null)
            view.launchCreateTeamListActivity(mIdCargaCurso, mIdCurso, grupoEquipoId,tipoGrupo, tipoItem, tipoCreacionGrupoId, entidadId, georeferenciaId, mIdProgramaEducativo);
    }

    private void eliminarGrupoList(Group group){
        useCaseHandler.execute(eliminarGrupoList, new EliminarGrupoList.RequestValues(group),
                new UseCase.UseCaseCallback<EliminarGrupoList.ResponseValue>() {
                    @Override
                    public void onSuccess(EliminarGrupoList.ResponseValue response) {
                        if(view!=null)view.onExportarGrupoList(mIdProgramaEducativo);
                        getGroups();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
    private void showDialogEspera(){
        if(view!=null)view.showDialogEspera();
    }

    private Group group;

    private void saveGrupoSesion(){

        useCaseHandler.execute(
                saveSeleccionGroup,
                new SaveSeleccionGroup.RequestValues(mIdRubroEvaluacion, group),
                new UseCase.UseCaseCallback<SaveSeleccionGroup.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveSeleccionGroup.ResponseValue response) {
                        if(view != null){
                            if(response.isSuccess()){
                                Log.d(TAG, "saveGrupoSesion");
                                view.salirActualizar();
                            }else{
                                showMessage(R.string.sesion_evaluacion_agreagar_grupo_message_error_al_guardar_datos);
                            }

                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    @Override
    public void onViewFilterFragment() {
        if (filterButton){
            if(view!=null)view.showButtonFilter();
        } else {
            if(view!=null)view.hideButtonFilter();
        }
    }


}
