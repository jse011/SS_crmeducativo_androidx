package com.consultoraestrategia.ss_crmeducativo.grouplist.data.source;

import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

import java.util.List;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public interface GroupDataSource {
    interface GetGroupsCallback{
        void onGroupsLoaded(List<Object> cursoGrupoList);
        void onError(String error);
    }

    interface Callback<T>{
        void onLoad(boolean success, T item);
    }

    void getGroups(int idCargaCurso,int idCargaAcademica ,Boolean tipo,int idProgramaEducativo,GetGroupsCallback callback);
    void elimarGrupoList(String grupoEquipoId, Callback<String> callback);
    interface GetGroupCallback {
        void onGroupLoaded(Group group);
        void onDataNotAvailable();
    }


    interface SaveSelecionGrupoCallBack {
        void localSuccess(Group group, boolean success);
    }

    void getGroup(String id, GetGroupCallback callback);
    void saveSelecionGrupo(String rubroEvaluacionId, Group group, SaveSelecionGrupoCallBack callBack);
}
