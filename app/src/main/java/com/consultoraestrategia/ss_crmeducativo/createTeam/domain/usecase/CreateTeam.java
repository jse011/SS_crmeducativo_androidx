package com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamRepository;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Team;

import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CreateTeam extends UseCase<CreateTeam.RequestValues, CreateTeam.ResponseValue>{

    private CreateTeamRepository repository;

    public CreateTeam(CreateTeamRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.createTeam(requestValues.getTeam(), new CreateTeamDataSource.CreateTeamCallBack() {
            @Override
            public void localSuccess(boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(success));
            }

            @Override
            public void remoteSucess(boolean success) {

            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final Team team;

        public RequestValues(Team team) {
            this.team = team;
        }

        public Team getTeam() {
            return team;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final boolean sucess;

        public ResponseValue(boolean sucess) {
            this.sucess = sucess;
        }

        public boolean isSucess() {
            return sucess;
        }
    }
}
