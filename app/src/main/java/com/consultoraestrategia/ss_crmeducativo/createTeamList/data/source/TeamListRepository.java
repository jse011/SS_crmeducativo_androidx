package com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.local.TeamListLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.remote.TeamListRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import java.util.List;

/**
 * Created by @stevecampos on 20/09/2017.
 */

public class TeamListRepository implements TeamListDataSource {

    public static final String TAG = TeamListRepository.class.getSimpleName();
    private TeamListLocalDataSource localDataSource;
    private TeamListRemoteDataSource remoteDataSource;

    private static TeamListRepository INSTANCE = null;
    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    private boolean mCacheIsDirty = false;

    public static TeamListRepository getInstance(TeamListLocalDataSource localDataSource, TeamListRemoteDataSource remoteDataSource){
        if (INSTANCE == null){
            INSTANCE = new TeamListRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    private TeamListRepository(TeamListLocalDataSource localDataSource, TeamListRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void createGroup(Group group, CreateGroupCallback callback) {
        Log.d(TAG, "createGroup Group");
        localDataSource.createGroup(group, callback);
    }

    @Override
    public void createGroup(String cargaCursoId, CreateGroupCallback callback) {
        Log.d(TAG, "createGroup cargaCursoId");
        localDataSource.createGroup(cargaCursoId, callback);
    }

    /**
     * Gets grupoEquipo from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p>
     * Note: {@link GetGroupCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getGroup(String id, int entidadId, int georeferenciaId, GetGroupCallback callback) {
        Log.d(TAG, "getGroup");
        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            //remoteDataSource.getGroup(id, callback);
        } else {
            // Query the local storage if available. If not, query the network.
            localDataSource.getGroup(id, entidadId, georeferenciaId, callback);
        }
    }

    @Override
    public void getLastGroup(GetGroupCallback callback) {
        Log.d(TAG, "getLastGroup");
        localDataSource.getLastGroup(callback);
    }



    @Override
    public void getTeamList(int cargaCursoId,int cantidad,Group group,int tipo, int entidadId, int georeferenciaId, Callback<List<Team>> callback) {
       localDataSource.getTeamList(cargaCursoId,cantidad, group,tipo, entidadId, georeferenciaId, callback);
    }
}
