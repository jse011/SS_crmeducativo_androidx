package com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

/**
 * Created by @stevecampos on 20/09/2017.
 */

public class GetAutoincrementedId extends UseCase<GetAutoincrementedId.RequestValues, GetAutoincrementedId.ResponseValue>{

    private TeamListRepository repository;

    public GetAutoincrementedId(TeamListRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getLastGroup(new TeamListDataSource.GetGroupCallback() {
            @Override
            public void onGroupLoaded(Group group) {

                int id = 0;
                if (group != null){
                    id = Integer.parseInt(group.getId());
                }
                getUseCaseCallback().onSuccess(new ResponseValue(++id));
            }

            @Override
            public void onDataNotAvailable() {
                int id = 0;
                getUseCaseCallback().onSuccess(new ResponseValue(++id));
            }
        });
    }



    public static final class RequestValues implements UseCase.RequestValues {
        public RequestValues() {
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final int autoIncrementedId;

        public ResponseValue(int autoIncrementedId) {
            this.autoIncrementedId = autoIncrementedId;
        }

        public int getAutoIncrementedId() {
            return autoIncrementedId;
        }
    }
}
