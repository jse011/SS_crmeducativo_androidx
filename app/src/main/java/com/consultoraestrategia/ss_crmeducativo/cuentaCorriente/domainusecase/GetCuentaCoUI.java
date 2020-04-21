package com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.domainusecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.CuentaCorrienteRepository;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.datasource.callbacks.GetCuentasCoUICallback;
import com.consultoraestrategia.ss_crmeducativo.cuentaCorriente.entities.CuentaCoUI;


import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class GetCuentaCoUI extends UseCase<GetCuentaCoUI.RequestValues, GetCuentaCoUI.ResponseValue> {

    private static final String TAG = GetCuentaCoUI.class.getSimpleName();
    private CuentaCorrienteRepository repository;

    public GetCuentaCoUI(CuentaCorrienteRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        int idAlumno = requestValues.getIdALumno();
        repository.getCuentaCorrienteList(idAlumno, requestValues.getAnioSelected(), new GetCuentasCoUICallback() {


            @Override
            public void onCuentasCoUILoaded(double restante, double totalCredito, double totalDebito, List<CuentaCoUI> cuentaCoUIList) {
                getUseCaseCallback().onSuccess(new ResponseValue(restante, totalDebito, totalCredito, cuentaCoUIList));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();

            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int idALumno;
        private final String anioSelected;

        public RequestValues(int idALumno, String anioSelected) {
            this.idALumno = idALumno;
            this.anioSelected = anioSelected;
        }

        public String getAnioSelected() {
            return anioSelected;
        }

        public int getIdALumno() {
            return idALumno;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final double restante;
        private final double totalCredito;
        private final double totalDebito;
        private final List<CuentaCoUI> cuentaCoUIList;

        public ResponseValue(double restante, double totalCredito, double totalDebito, List<CuentaCoUI> cuentaCoUIList) {
            this.restante = restante;
            this.totalCredito = totalCredito;
            this.totalDebito = totalDebito;
            this.cuentaCoUIList = cuentaCoUIList;
        }

        public List<CuentaCoUI> getCuentaCoUIList() {
            return cuentaCoUIList;
        }

        public double getRestante() {
            return restante;
        }

        public double getTotalCredito() {
            return totalCredito;
        }

        public double getTotalDebito() {
            return totalDebito;
        }
    }
}
