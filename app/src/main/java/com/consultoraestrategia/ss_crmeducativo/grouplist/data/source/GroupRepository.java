package com.consultoraestrategia.ss_crmeducativo.grouplist.data.source;

import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.local.GroupLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.remote.GroupRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public class GroupRepository implements GroupDataSource{

    private GroupLocalDataSource localDataSource;
    private GroupRemoteDataSource remoteDataSource;

    private static GroupRepository INSTACE = null;

    public static GroupRepository getInstance(GroupLocalDataSource localDataSource, GroupRemoteDataSource remoteDataSource){
        if (INSTACE == null){
            INSTACE = new GroupRepository(localDataSource, remoteDataSource);
        }
        return INSTACE;
    }


    private GroupRepository(GroupLocalDataSource localDataSource, GroupRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getGroups(int idCargaCurso, int idCargaAcademica,Boolean tipo,int idProgramaEducativo,GetGroupsCallback callback) {
        localDataSource.getGroups(idCargaCurso, idCargaAcademica,tipo,idProgramaEducativo,callback);
    }

    @Override
    public void elimarGrupoList(String grupoEquipoId, Callback<String> callback) {
        localDataSource.elimarGrupoList(grupoEquipoId, callback);
    }

    @Override
    public void getGroup(String id, GetGroupCallback callback) {
        localDataSource.getGroup(id,callback);
    }

    @Override
    public void saveSelecionGrupo(String rubroEvaluacionId, Group group, SaveSelecionGrupoCallBack callBack) {
        localDataSource.saveSelecionGrupo(rubroEvaluacionId,group,callBack);
    }
}
