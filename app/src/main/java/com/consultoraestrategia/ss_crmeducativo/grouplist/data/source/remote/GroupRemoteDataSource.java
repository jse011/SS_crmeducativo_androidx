package com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public class GroupRemoteDataSource implements GroupDataSource {

    public GroupRemoteDataSource() {
    }


    @Override
    public void getGroups(int idCargaCurso, int idCargaAcademica, Boolean tipo, int idProgramaEducativo,GetGroupsCallback callback) {

    }

    @Override
    public void elimarGrupoList(String grupoEquipoId, Callback<String> callback) {

    }

    @Override
    public void getGroup(String id, GetGroupCallback callback) {

    }

    @Override
    public void saveSelecionGrupo(String rubroEvaluacionId, Group group, SaveSelecionGrupoCallBack callBack) {

    }
}
