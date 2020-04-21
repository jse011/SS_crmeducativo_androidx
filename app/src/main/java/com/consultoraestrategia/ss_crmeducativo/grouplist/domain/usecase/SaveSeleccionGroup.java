package com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public class SaveSeleccionGroup extends UseCase<SaveSeleccionGroup.RequestValues, SaveSeleccionGroup.ResponseValue>{

    private GroupRepository repository;

    public SaveSeleccionGroup(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.saveSelecionGrupo(requestValues.getSesionAprendizajeId(),requestValues.getGroup(), new GroupDataSource.SaveSelecionGrupoCallBack() {
            @Override
            public void localSuccess(Group group, boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(group,success));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String sesionAprendizajeId;
        private Group group;

        public RequestValues(String sesionAprendizajeId, Group group) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.group = group;
        }

        public String getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public Group getGroup() {
            return group;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private Group group;
        private boolean success;

        public ResponseValue(Group group, boolean success) {
            this.group = group;
            this.success = success;
        }

        public Group getGroup() {
            return group;
        }


        public boolean isSuccess() {
            return success;
        }
    }
}
