package com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import java.util.List;

/**
 * Created by @stevecampos on 5/10/2017.
 */

public class DeleteTeam extends UseCase<DeleteTeam.RequestValues, DeleteTeam.ResponseValue>{

    private CreateTeamRepository repository;

    public DeleteTeam(CreateTeamRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        List<Team> team = requestValues.getTeams();
        repository.deleteTeam(team, new CreateTeamDataSource.TeamCallback() {
            @Override
            public void onSuccess(Team team) {
                getUseCaseCallback().onSuccess(new ResponseValue(team));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final List<Team> teams;

        public RequestValues(List<Team> teams) {
            this.teams = teams;
        }

        public List<Team> getTeams() {
            return teams;
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
