package com.consultoraestrategia.ss_crmeducativo.createTeam.data.source;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.local.CreateTeamLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.remote.CreateTeamRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CreateTeamRepository implements CreateTeamDataSource {

    public static final String TAG = CreateTeamRepository.class.getSimpleName();

    public static CreateTeamRepository INSTANCE = null;
    private CreateTeamLocalDataSource localDataSource;
    private CreateTeamRemoteDataSource remoteDataSource;

    public static CreateTeamRepository getInstance(CreateTeamLocalDataSource localDataSource, CreateTeamRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CreateTeamRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    private CreateTeamRepository(CreateTeamLocalDataSource localDataSource, CreateTeamRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getPersonas(String cargaCursoId, String grupoEquipoId, int entidadId, int georeferenciaId,GetPersonasCallback callback) {
        Log.d(TAG, "getPersonas: ");
        localDataSource.getPersonas(cargaCursoId, grupoEquipoId, entidadId, georeferenciaId,callback);
    }

    @Override
    public void createTeam(Team team, CreateTeamCallBack callback) {
        Log.d(TAG, "createTeam");
        localDataSource.createTeam(team, callback);
    }

    @Override
    public void deleteTeam(List<Team> team, TeamCallback callback) {
        localDataSource.deleteTeam(team, callback);
        remoteDataSource.deleteTeam(team, callback);
    }

    @Override
    public void getTeam(String teamId, int entidadId, int georeferenciaId,Callback<Team> callback) {
        Log.d(TAG, "getTeam: " + teamId);
        localDataSource.getTeam(teamId, entidadId, georeferenciaId,callback);
    }

    @Override
    public List<DimensionUi> getDimensiones() {
        return localDataSource.getDimensiones();
    }

}
