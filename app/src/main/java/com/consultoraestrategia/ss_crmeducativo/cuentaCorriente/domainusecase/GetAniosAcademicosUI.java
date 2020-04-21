package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.CuentaCorrienteRepository;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetAniosAcademicosUICallback;


import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class GetAniosAcademicosUI extends UseCase<GetAniosAcademicosUI.RequestValues, GetAniosAcademicosUI.ResponseValue> {

    private static final String TAG = GetAniosAcademicosUI.class.getSimpleName();
    private CuentaCorrienteRepository repository;

    public GetAniosAcademicosUI(CuentaCorrienteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        int idAlumno = requestValues.getIdALumno();
        repository.getAniosAcademicosList(idAlumno, new GetAniosAcademicosUICallback() {
            @Override
            public void onAniosAcademicosUILoaded(List<String> anioAcademicoList) {
                getUseCaseCallback()
                        .onSuccess(new ResponseValue(anioAcademicoList));

            }

            @Override
            public void onError(String error) {

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

        private final List<String> anioAcademicoList;

        public ResponseValue(List<String> anioAcademicoList) {
            this.anioAcademicoList = anioAcademicoList;
        }

        public List<String> getAnioAcademicoList() {
            return anioAcademicoList;
        }
    }
}
