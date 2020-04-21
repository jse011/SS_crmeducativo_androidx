package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;

public class GetAlarma {

    MainRepository mainRepository;

    public GetAlarma(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
    public  Response execute(){
        return  new Response(mainRepository.getHoraAlarma());
    }

    public class Response {
        AlarmaUi alarmaUi;

        public Response(AlarmaUi alarmaUi) {
            this.alarmaUi = alarmaUi;
        }

        public AlarmaUi getAlarmaUi() {
            return alarmaUi;
        }
    }
}
