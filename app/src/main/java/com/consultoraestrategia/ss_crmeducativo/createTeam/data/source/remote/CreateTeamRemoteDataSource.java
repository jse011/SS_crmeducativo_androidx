package com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CreateTeamRemoteDataSource implements CreateTeamDataSource {



    public CreateTeamRemoteDataSource() {
    }

    @Override
    public void getPersonas(String cargaCursoId, String grupoEquipoId, int entidad, int georeferencia, GetPersonasCallback callback) {

    }

    @Override
    public void createTeam(Team team, CreateTeamCallBack callback) {

    }

    @Override
    public void deleteTeam(List<Team> team, TeamCallback callback) {

    }

    @Override
    public void getTeam(String teamId, int entidad, int georeferenciaId,Callback<Team> callback) {

    }

    @Override
    public List<DimensionUi> getDimensiones() {
        return null;
    }
}
