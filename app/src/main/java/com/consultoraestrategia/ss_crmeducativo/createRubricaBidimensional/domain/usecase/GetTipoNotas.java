package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;

import java.util.List;

/**
 * Created by @stevecampos on 29/01/2018.
 */

public class GetTipoNotas extends UseCase<GetTipoNotas.RequestValues, GetTipoNotas.ResponseValue> {

    private static final String TAG = GetTipoNotas.class.getSimpleName();
    private CreateRubBidRepository repository;

    public GetTipoNotas(CreateRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        repository.getTipoNotas(new CreateRubBidDataSource.Callback<TipoNotaUi>() {
            @Override
            public void onLoaded(List<TipoNotaUi> listLoaded) {
                getUseCaseCallback().onSuccess(new ResponseValue(listLoaded));
            }
        }, requestValues);
    }

    public static class RequestValues implements UseCase.RequestValues {
        private int programaEducativoId;
        private Integer[] tiposIds;

        public RequestValues(int programaEducativoId, Integer... ids) {
            this.programaEducativoId = programaEducativoId;
            this.tiposIds = ids;
        }

        public int getProgramaEducativoId() {
            return programaEducativoId;
        }

        public Integer[] getTiposIds() {
            return tiposIds;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final List<TipoNotaUi> tipoNotaList;

        public ResponseValue(List<TipoNotaUi> tipoNotaList) {
            this.tipoNotaList = tipoNotaList;
        }

        public List<TipoNotaUi> getTipoNotaList() {
            return tipoNotaList;
        }
    }
}
