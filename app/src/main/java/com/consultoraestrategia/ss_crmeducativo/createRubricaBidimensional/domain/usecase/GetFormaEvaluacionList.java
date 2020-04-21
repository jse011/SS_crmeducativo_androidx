package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;

import java.util.List;

/**
 * Created by @stevecampos on 18/02/2018.
 */

public class GetFormaEvaluacionList extends UseCase<GetFormaEvaluacionList.RequestValues, GetFormaEvaluacionList.ResponseValue> {
    private static final String TAG = GetFormaEvaluacionList.class.getSimpleName();
    private CreateRubBidRepository repository;

    public GetFormaEvaluacionList(CreateRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        repository.getFormaEvaluacion(new CreateRubBidDataSource.Callback<TipoUi>() {
            @Override
            public void onLoaded(List<TipoUi> listLoaded) {
                Log.d(TAG, "onLoaded");
                getUseCaseCallback().onSuccess(new ResponseValue(listLoaded));
            }
        });
    }


    public static class RequestValues implements UseCase.RequestValues {

    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final List<TipoUi> list;

        public ResponseValue(List<TipoUi> list) {
            this.list = list;
        }

        public List<TipoUi> getList() {
            return list;
        }
    }
}
