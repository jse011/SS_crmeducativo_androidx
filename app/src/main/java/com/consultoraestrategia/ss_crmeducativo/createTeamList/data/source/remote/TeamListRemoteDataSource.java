package com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.remote;

import android.content.Context;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 20/09/2017.
 */

public class TeamListRemoteDataSource implements TeamListDataSource {

    private static final String TAG = TeamListRemoteDataSource.class.getSimpleName();
    Context context;

    public TeamListRemoteDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void createGroup(Group group, CreateGroupCallback callback) {
        Log.d(TAG, "createGroup: ");

    }

    @Override
    public void createGroup(String cargaCursoId, CreateGroupCallback callback) {

    }

    @Override
    public void getGroup(String id, int entidadId, int georeferenciaId, GetGroupCallback callback) {
        callback.onDataNotAvailable();
    }

    @Override
    public void getLastGroup(GetGroupCallback callback) {
        callback.onDataNotAvailable();
    }



    @Override
    public void getTeamList(int cargaCursoId, int cantidad,Group group,int tipo, int entidadId, int georeferenciaId, Callback<List<Team>> callback) {

    }
}
