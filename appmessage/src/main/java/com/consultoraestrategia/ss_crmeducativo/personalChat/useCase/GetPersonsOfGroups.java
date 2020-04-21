package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;
import com.raizlabs.android.dbflow.sql.language.Operator;

import java.util.List;

public class GetPersonsOfGroups extends UseCase<GetPersonsOfGroups.RequestValues, GetPersonsOfGroups.ResponseValue> {

    PersonalChatRepository personalChatRepository;

    public GetPersonsOfGroups(PersonalChatRepository personalChatRepository) {
        this.personalChatRepository = personalChatRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        personalChatRepository.getPersonsOfGroup(requestValues.getIdSender(), requestValues.getIdReceiver(), requestValues.getType(), requestValues.getTypePerson(),new PersonalChatDataSource.Callback<List<Integer>>() {
            @Override
            public void onLoad(boolean success, List<Integer> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues {
        private int idSender;
        private String idReceiver;
        private SendDataChatBundle.TypeGroup type;
        private SendDataChatBundle.TypePerson   typePerson;



        public RequestValues(String idReceiver,int idSender,SendDataChatBundle.TypeGroup type,SendDataChatBundle.TypePerson   typePerson ) {
            this.idReceiver = idReceiver;
            this.idSender=idSender;
            this.type=type;
            this.typePerson=typePerson;

        }

        public SendDataChatBundle.TypePerson  getTypePerson() {
            return typePerson;
        }

        public SendDataChatBundle.TypeGroup getType() {
            return type;
        }

        public int getIdSender() {
            return idSender;
        }

        public String getIdReceiver() {
            return idReceiver;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<Integer>personsId;

        public ResponseValue(List<Integer> personsId) {
            this.personsId = personsId;
        }

        public List<Integer> getPersonsId() {
            return personsId;
        }
    }
}
