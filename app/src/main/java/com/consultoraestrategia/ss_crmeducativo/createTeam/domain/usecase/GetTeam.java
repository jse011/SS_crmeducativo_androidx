package com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

public class GetTeam extends UseCase<GetTeam.RequestValues, GetTeam.ResponseValue> {
    private static final String TAG = "GetTeam";
    private CreateTeamRepository repository;

    public GetTeam(CreateTeamRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase: ");
        repository.getTeam(
                requestValues.getTeamId(), requestValues.getEntidadId(), requestValues.getGeoreferenciaId(),
                new CreateTeamDataSource.Callback<Team>() {
                    @Override
                    public void onSuccess(Team data) {
                        getUseCaseCallback().onSuccess(new ResponseValue(data));
                    }
                }
        );
    }


    public static final class RequestValues implements UseCase.RequestValues {
        private final String teamId;
        private final int entidadId;
        private final int georeferenciaId;

        public RequestValues(String teamId, int entidadId, int georeferenciaId) {
            this.teamId = teamId;
            this.entidadId = entidadId;
            this.georeferenciaId = georeferenciaId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }

        public String getTeamId() {
            return teamId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final Team team;

        public ResponseValue(Team team) {
            this.team = team;
        }

        public Team getTeam() {
            return team;
        }
    }
}
