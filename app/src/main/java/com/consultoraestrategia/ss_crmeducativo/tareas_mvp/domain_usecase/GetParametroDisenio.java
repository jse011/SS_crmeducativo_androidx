package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase;


import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpRepository;

public class GetParametroDisenio extends UseCaseSincrono<GetParametroDisenio.RequestValues, GetParametroDisenio.ResponseValue> {

    private TareasMvpRepository repository;

    public GetParametroDisenio(TareasMvpRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RequestValues request, final Callback<ResponseValue> callback) {
        repository.getParametroDisenio(request.parametroDisenioId,
                new TareasMvpDataSource.CallbackTareas() {
                    @Override
                    public void onParametroDisenio(ParametroDisenioUi parametroDisenioUi, int status) {
                        if(parametroDisenioUi!=null){
                            callback.onResponse(true, new ResponseValue(parametroDisenioUi));
                        }else {
                            callback.onResponse(false, null);
                        }

                    }
                });
    }


    public static final class RequestValues implements UseCase.RequestValues{

        int parametroDisenioId;

        public RequestValues(int parametroDisenioId) {
            this.parametroDisenioId = parametroDisenioId;
        }

        public int getParametroDisenioId() {
            return parametroDisenioId;
        }

        public void setParametroDisenioId(int parametroDisenioId) {
            this.parametroDisenioId = parametroDisenioId;
        }
    }


    public static final class ResponseValue implements UseCase.ResponseValue{


        ParametroDisenioUi parametroDisenioUi;

        public ResponseValue(ParametroDisenioUi parametroDisenioUi) {
            this.parametroDisenioUi = parametroDisenioUi;
        }

        public ParametroDisenioUi getParametroDisenioUi() {
            return parametroDisenioUi;
        }

        public void setParametroDisenioUi(ParametroDisenioUi parametroDisenioUi) {
            this.parametroDisenioUi = parametroDisenioUi;
        }
    }
}
