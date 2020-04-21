package com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.services.uploadFile.UploadFile;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public class GetGroup extends UseCaseSincrono<GetGroup.RequestValues, GetGroup.ResponseValue> {

    private TeamListRepository repository;

    public GetGroup(TeamListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        String id = requestValues.getId();
        repository.getGroup(id, requestValues.getEntidadId(), requestValues.getGeoreferenciaId(), new TeamListDataSource.GetGroupCallback() {
            @Override
            public void onGroupLoaded(Group group) {
                callback.onResponse(true, new ResponseValue(group));

            }

            @Override
            public void onDataNotAvailable() {
                Log.d(getClass().getSimpleName(), "Error");
            }
        });
    }


    public static class RequestValues {
        private final String id;
        private final int entidadId;
        private final int georeferenciaId;

        public RequestValues(String id, int entidadId, int georeferenciaId) {
            this.id = id;
            this.entidadId = entidadId;
            this.georeferenciaId = georeferenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public String getId() {
            return id;
        }
    }

    public static class ResponseValue {
        private final Group group;

        public ResponseValue(Group group) {
            this.group = group;
        }

        public Group getGroup() {
            return group;
        }
    }


}
