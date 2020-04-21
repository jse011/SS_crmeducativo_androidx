package com.consultoraestrategia.ss_crmeducativo.createTeam.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamDataSource;
import com.consultoraestrategia.ss_crmeducativo.createTeam.data.source.CreateTeamRepository;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class GetPersonList extends UseCase<GetPersonList.RequestValues, GetPersonList.ResponseValue> {

    private static final String TAG = "GetPersonList";
    private CreateTeamRepository repository;

    public GetPersonList(CreateTeamRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase: ");
        String cargaCursoId = requestValues.getCargaCursoId();
        String grupoEquipoId = requestValues.getGrupoEquipoId();
        repository.getPersonas(cargaCursoId, grupoEquipoId, requestValues.getEntidadId(), requestValues.getGeoreferenciaId() ,new CreateTeamDataSource.GetPersonasCallback() {
            @Override
            public void onPersonasLoaded(List<Person> personas) {
                getUseCaseCallback().onSuccess(new ResponseValue(personas));
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onSuccess(new ResponseValue(new ArrayList<Person>()));
            }
        });
    }



    public static final class RequestValues implements UseCase.RequestValues {
        private final String cargaCursoId;
        private final String grupoEquipoId;
        private final int entidadId;
        private final int georeferenciaId;

        public RequestValues(String cargaCursoId, String grupoEquipoId, int entidadId, int georeferenciaId) {
            this.cargaCursoId = cargaCursoId;
            this.grupoEquipoId = grupoEquipoId;
            this.entidadId = entidadId;
            this.georeferenciaId = georeferenciaId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }

        public String getCargaCursoId() {
            return cargaCursoId;
        }

        public String getGrupoEquipoId() {
            return grupoEquipoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final List<Person> personList;

        public ResponseValue(List<Person> personList) {
            this.personList = personList;
        }

        public List<Person> getPersonList() {
            return personList;
        }
    }
}
