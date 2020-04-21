package com.consultoraestrategia.ss_crmeducativo.grouplist;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.TipoGrupoUi;

import java.util.List;

/**
 * Created by @stevecampos on 15/09/2017.
 */

public interface GroupListView extends BaseView<GroupListPresenter> {
    void showGroupList(List<Object> cursoGrupos, Boolean tipoitem, String cargacursoId);

    void showProgress();

    void hideProgress();

    void showMessage(int message);

    void showTextEmpty();

    void hideTextEmpty();

    void updateList(Object object);


    void launchCreateTeamListActivity(String idCargaCurso, String idCurso, String groupoEquipoId, int tipoGrupo, Boolean tipoitem, int tipoCreacionGrupoId, int entidadId, int gepreferenciaId, int programaEducativoId);

    void onExportarGrupoList(int programaEducativoId);
    void onFilterListGrupos();

    void salirActualizar();
    void showDialogEspera();

    void showButtonFilter();
    void hideButtonFilter();


    void hideRecyclerView();
    void showRecyclerView();

    void initFabButtom(Boolean tipoList);

    void showTextEmptyTipoTwo();

    void showEmpty();

    void clearGroupList();

    void showListFilterChooser(String seleccione, TipoGrupoUi[] values);
}
