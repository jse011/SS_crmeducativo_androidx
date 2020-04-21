package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.CuentaCorrienteRepository;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetPersonaCallback;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class GetPersona extends UseCase<GetPersona.RequestValues, GetPersona.ResponseValue> {

    private static final String TAG = GetPersona.class.getSimpleName();
    private CuentaCorrienteRepository repository;

    public GetPersona(CuentaCorrienteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        int idAlumno = requestValues.getIdALumno();
        Log.d(TAG, "idAlumno : " + idAlumno);
        repository.getPersonas(idAlumno, new GetPersonaCallback() {
            @Override
            public void onPersonaLoaded(Persona persona) {

                getUseCaseCallback().onSuccess(new ResponseValue(persona));
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "ERROR : " + error);
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int idALumno;

        public RequestValues(int idALumno) {
            this.idALumno = idALumno;
        }

        public int getIdALumno() {
            return idALumno;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final Persona persona;

        public ResponseValue(Persona persona) {
            this.persona = persona;
        }

        public Persona getPersona() {
            return persona;
        }
    }
}
