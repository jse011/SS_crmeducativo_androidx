package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class UpdatePersonaServidor  {
    private MainRepository mainRepository;

    public UpdatePersonaServidor(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public RetrofitCancel execute(PersonaUi personaUi, Callback callback ){
        return mainRepository.updatePersona(personaUi, new MainDataSource.SucessCallback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                    if(success){
                        callback.onSuccess();
                    }   else {
                        callback.onError();
                    }
            }
        });
    }

    public interface Callback{
        void onSuccess();
        void onError();
    }
}
