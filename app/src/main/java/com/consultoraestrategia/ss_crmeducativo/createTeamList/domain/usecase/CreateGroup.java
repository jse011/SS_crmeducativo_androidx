package com.consultoraestrategia.ss_crmeducativo.createTeamList.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamRepository;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.data.source.TeamListRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public class CreateGroup extends UseCaseSincrono<CreateGroup.RequestValues, CreateGroup.ResponseValue> {

    private static final String TAG = CreateGroup.class.getSimpleName();
    private TeamListRepository repository;

    public CreateGroup(TeamListRepository repository) {
        this.repository = repository;
    }



    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        Log.d(TAG, "executeUseCase");
        final Group group = requestValues.getGroup();
        boolean newGroup = requestValues.isNewGroup();
        Log.d(TAG, "newGroup: " + newGroup);
        if (newGroup) {
            repository.createGroup(group.getCargaCursoId(), new TeamListDataSource.CreateGroupCallback() {
                @Override
                public void onSucess(Group group) {
                    Log.d(TAG, "onSucess: " + group.getId());
                    callback.onResponse(true,new ResponseValue(group, null) );

                }

                @Override
                public void onError(String error) {
                    Log.d(TAG, "onError: " + error);
                }
            });
        } else {
            repository.createGroup(group, new TeamListDataSource.CreateGroupCallback() {
                @Override
                public void onSucess(Group group) {
                    callback.onResponse(true,new ResponseValue(group, null) );
                }

                @Override
                public void onError(String error) {
                    Log.d(TAG, "onSucess: createGroup" + group.toString());
                    Log.d(TAG, "onError: " + error);
                    callback.onResponse(false,new ResponseValue(group, error) );
                }
            });
        }
    }

    public static final class RequestValues  {
        private final Group group;
        private final boolean newGroup;

        public RequestValues(Group group, boolean newGroup) {
            this.group = group;
            this.newGroup = newGroup;
        }

        public Group getGroup() {
            return group;
        }

        public boolean isNewGroup() {
            return newGroup;
        }
    }

    public static final class ResponseValue  {
        private final Group group;
        private final String error;

        public ResponseValue(Group group, String error) {
            this.group = group;
            this.error = error;
        }

        public Group getGroup() {
            return group;
        }

        public String getError() {
            return error;
        }
    }
}
