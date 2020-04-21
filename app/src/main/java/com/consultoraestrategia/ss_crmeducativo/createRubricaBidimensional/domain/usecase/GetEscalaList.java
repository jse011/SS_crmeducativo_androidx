package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;

import java.util.List;

/**
 * Created by @stevecampos on 18/02/2018.
 */

public class GetEscalaList extends UseCase<GetEscalaList.RequestValues, GetEscalaList.ResponseValue> {

    private static final String TAG = GetEscalaList.class.getSimpleName();
    private CreateRubBidRepository repository;

    public GetEscalaList(CreateRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        repository.getEscalaEvaluacionList(new CreateRubBidDataSource.Callback<EscalaEvaluacionUi>() {
            @Override
            public void onLoaded(List<EscalaEvaluacionUi> listLoaded) {
                Log.d(TAG, "onLoaded");
                getUseCaseCallback().onSuccess(new ResponseValue(listLoaded));
            }
        });
    }


    public static class RequestValues implements UseCase.RequestValues {

    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final List<EscalaEvaluacionUi> list;

        public ResponseValue(List<EscalaEvaluacionUi> list) {
            this.list = list;
        }

        public List<EscalaEvaluacionUi> getList() {
            return list;
        }
    }
}
