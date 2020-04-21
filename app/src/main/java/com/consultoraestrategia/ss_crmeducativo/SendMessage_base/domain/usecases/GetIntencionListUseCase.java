package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.SendMessageBaseRepository;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetIntencionListCallback;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class GetIntencionListUseCase extends UseCase<GetIntencionListUseCase.RequestValues, GetIntencionListUseCase.ResponseValue> {

    private SendMessageBaseRepository repository;

    public GetIntencionListUseCase(SendMessageBaseRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getIntencionList(requestValues, new GetIntencionListCallback() {
            @Override
            public void onListLoaded(List<IntencionUI> intencionUIList) {
                getUseCaseCallback().onSuccess(new ResponseValue(intencionUIList));

            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();

            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues {

        public RequestValues() {
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<IntencionUI> intencionUIList;

        public ResponseValue(List<IntencionUI> intencionUIList) {
            this.intencionUIList = intencionUIList;
        }

        public List<IntencionUI> getIntencionUIList() {
            return intencionUIList;
        }
    }
}
