package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

public class ValidatePersonExistence extends UseCase<ValidatePersonExistence.RequestValues, ValidatePersonExistence.ResponseValue> {


    PersonalChatRepository repository;

    public ValidatePersonExistence(PersonalChatRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.validatePersonExistence(requestValues.getIdsender(), requestValues.isInternet(),new PersonalChatDataSource.SuccessCallback() {
            @Override
            public void onLoad(boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(success));
            }
        });

    }

    public static  class RequestValues implements UseCase.RequestValues{
        private int idsender;
        private boolean internet;


        public RequestValues(int idsender,boolean internet) {
            this.idsender = idsender;
            this.internet=internet;
        }


        public int getIdsender() {
            return idsender;
        }

        public boolean isInternet() {
            return internet;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{

        private boolean existenceSender;

        public ResponseValue(boolean existenceSender) {
            this.existenceSender = existenceSender;
        }

        public boolean isExistenceSender() {
            return existenceSender;
        }

    }

}
