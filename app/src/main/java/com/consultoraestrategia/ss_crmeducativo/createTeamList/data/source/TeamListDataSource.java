package com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import java.util.List;

/**
 * Created by @stevecampos on 20/09/2017.
 */

public interface TeamListDataSource {

    void createGroup(Group group, CreateGroupCallback callback);
    void createGroup(String cargaCursoId, CreateGroupCallback callback);

    interface CreateGroupCallback {
        void onSucess(Group group);
        void onError(String error);
    }

    interface GetGroupCallback {
        void onGroupLoaded(Group group);
        void onDataNotAvailable();
    }

    interface Callback<T> {
        void onLoad(boolean estado, T item);
    }

    void getGroup(String id, int entidadId, int georeferenciaId, GetGroupCallback callback);
    void getLastGroup(GetGroupCallback callback);
    void getTeamList(int cargaCursoId, int cantidad,Group group, int tipo, int entidadId, int georeferenciaId, Callback <List<Team> >callback);

}
