package com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

import java.util.List;

public class EliminarGrupoList extends UseCase<EliminarGrupoList.RequestValues, EliminarGrupoList.ResponseValue> {
    private GroupRepository repository;

    public EliminarGrupoList(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        try {
            repository.elimarGrupoList(requestValues.getGroup().getId(), new GroupDataSource.Callback<String>() {
                @Override
                public void onLoad(boolean success, String item) {
                    if(success){
                        getUseCaseCallback().onSuccess(new ResponseValue());
                    }else {
                        getUseCaseCallback().onError();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            getUseCaseCallback().onError();
        }

    }

    public static class RequestValues implements UseCase.RequestValues{
        private Group group;

        public RequestValues(Group group) {
            this.group = group;
        }

        public Group getGroup() {
            return group;
        }
    }

    public static  class ResponseValue implements UseCase.ResponseValue{

    }
}
