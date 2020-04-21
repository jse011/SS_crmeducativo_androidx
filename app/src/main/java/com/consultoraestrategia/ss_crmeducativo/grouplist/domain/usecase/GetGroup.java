package com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public class GetGroup extends UseCase<GetGroup.RequestValues, GetGroup.ResponseValue>{

    private GroupRepository repository;

    public GetGroup(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getGroup(requestValues.getId(), new GroupDataSource.GetGroupCallback() {
            @Override
            public void onGroupLoaded(Group group) {
                getUseCaseCallback().onSuccess(new ResponseValue(group));
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private final String id;

        public RequestValues(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private final Group group;

        public ResponseValue(Group group) {
            this.group = group;
        }

        public Group getGroup() {
            return group;
        }
    }
}
