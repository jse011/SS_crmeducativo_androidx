package com.consultoraestrategia.ss_crmeducativo.createTeam.data.source;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.DimensionUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public interface CreateTeamDataSource {

    interface Callback<T> {
        void onSuccess(T data);
    }

    interface GetPersonasCallback {
        void onPersonasLoaded(List<Person> personas);

        void onDataNotAvailable();
    }

    interface CreateTeamCallBack {
        void localSuccess(boolean success);

        void remoteSucess(boolean success);
    }

    interface TeamCallback {
        void onSuccess(Team team);

        void onError(String error);
    }

    void getPersonas(String cargaCursoId, String grupoEquipoId, int entidadId, int georeferenciaId,GetPersonasCallback callback);

    void createTeam(Team team, CreateTeamCallBack callback);

    void deleteTeam(List<Team> team, TeamCallback callback);

    void getTeam(String teamId, int entidadId, int georeferenciaId, Callback<Team> callback);
    List<DimensionUi> getDimensiones();

}
