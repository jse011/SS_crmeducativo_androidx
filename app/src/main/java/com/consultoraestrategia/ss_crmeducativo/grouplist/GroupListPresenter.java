package com.consultoraestrategia.ss_crmeducativo.grouplist;

import com.consultoraestrategia.ss_crmeducativo.base.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.TipoGrupoUi;

/**
 * Created by @stevecampos on 15/09/2017.
 */

public interface GroupListPresenter extends BasePresenter <GroupListView>{

    void setExtras(int mIdCargaCurso, int mIdCurso, int idCargaAcademica, String idRubroEvaluacion, int mIdProgramaEducativo, int mIdEntidad, int mIdGeoreferencia);

    void onGroupSelected(Group group);

    void onResumeFragment();

    void onChecketGrupo(Group group);
    void onClickEliminarListaGrupos(Group group);
    void onFilterListGrupos();
    void onClickSaveGrupoSesion();
    void onViewCreated();

    void onViewFilterFragment();

    void onParentFabClickedCrearGrupo(boolean isAprendizajeColegio);

    void onItemClickTipoGrupo(TipoGrupoUi tipoGrupoUi);
}
