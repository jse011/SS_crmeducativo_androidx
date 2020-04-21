package com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupRepository;

import java.util.List;

/**
 * Created by @stevecampos on 22/09/2017.
 */

public class GetGroups extends UseCase<GetGroups.RequestValues, GetGroups.ResponseValue> {

    private static final String TAG = GetGroups.class.getSimpleName();
    private GroupRepository repository;

    public GetGroups(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        int idCargaCurso = requestValues.getIdCargaCurso();
        int idCargaAcademica =requestValues.getIdCargaAcademica();
        Boolean tipo= requestValues.getTipo();
        int idProgramaEducativo = requestValues.getmIdProgramaEducativo();
        repository.getGroups(idCargaCurso,idCargaAcademica,tipo,idProgramaEducativo,new GroupDataSource.GetGroupsCallback() {

            @Override
            public void onGroupsLoaded(List<Object> cursoGrupoList) {
                getUseCaseCallback().onSuccess(new ResponseValue( cursoGrupoList));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues{
        private final int idCargaCurso;
        private final int idCargaAcademica;
        private Boolean tipo;
        private final int mIdProgramaEducativo;

        public RequestValues(int idCargaCurso, int idCargaAcademica, Boolean tipo, int mIdProgramaEducativo) {
            this.idCargaCurso = idCargaCurso;
            this.idCargaAcademica = idCargaAcademica;
            this.tipo = tipo;
            this.mIdProgramaEducativo = mIdProgramaEducativo;
        }


        public int getmIdProgramaEducativo() {
            return mIdProgramaEducativo;
        }

        public Boolean getTipo() {
            return tipo;
        }

        public int getIdCargaAcademica() {
            return idCargaAcademica;
        }

        public int getIdCargaCurso() {
            return idCargaCurso;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
            private final List<Object> cursoGrupoList;

        public ResponseValue(List<Object> cursoGrupoList) {
            this.cursoGrupoList = cursoGrupoList;
        }

        public List<Object> getCursoGrupoList() {
            return cursoGrupoList;
        }
    }

}
