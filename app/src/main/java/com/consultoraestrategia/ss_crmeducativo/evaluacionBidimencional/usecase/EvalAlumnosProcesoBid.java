package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 25/02/2018.
 */

public class EvalAlumnosProcesoBid extends UseCaseSincrono<EvalAlumnosProcesoBid.RequestValues, EvalAlumnosProcesoBid.ResponseValue> {

    public static final String TAG = EvalAlumnosProcesoBid.class.getSimpleName();
    private EvalRubBidRepository repository;

    public EvalAlumnosProcesoBid(EvalRubBidRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        Log.d(TAG, "executeUseCase");
        EvalProcUi evalProcUi = requestValues.getEvalProcUi();
        List<EvalProcUi> evalProcUiListCopy = new ArrayList<>();
        List<EvalProcUi> evalProcUiList = requestValues.getEvalProcUiList();
        for (EvalProcUi item: evalProcUiList){
            evalProcUiListCopy.add(item.copy());
        }
        repository.evalAlumnosConProceso(new RequestValues(evalProcUi.copy(),evalProcUiListCopy), new EvalRubBidDataSource.Callback<EvalProcUi>() {
            @Override
            public void onLoaded(EvalProcUi object) {
                if(object != null){
                    callback.onResponse(true,new ResponseValue(object) );
                }else {
                   callback.onResponse(false, null);
                }
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private EvalProcUi evalProcUi;
        private final List<EvalProcUi> evalProcUiList;

        public RequestValues(EvalProcUi evalProcUi, List<EvalProcUi> evalProcUiList) {
            this.evalProcUi = evalProcUi;
            this.evalProcUiList = evalProcUiList;
        }

        public EvalProcUi getEvalProcUi() {
            return evalProcUi;
        }

        public List<EvalProcUi> getEvalProcUiList() {
            return evalProcUiList;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final EvalProcUi item;

        public ResponseValue(EvalProcUi item) {
            this.item = item;
        }

        public EvalProcUi getItem() {
            return item;
        }
    }
}
