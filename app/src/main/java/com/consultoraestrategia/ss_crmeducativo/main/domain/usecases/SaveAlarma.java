package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;

public class SaveAlarma {

MainRepository mainRepository;

    public SaveAlarma(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public Response execute(Requests request){
        return  new Response(mainRepository.saveAlarma(request.getHora(), request.getMinuto()));
    }

    public static class Requests {
        int hora;
        int minuto;

        public Requests(int hora, int minuto) {
            this.hora = hora;
            this.minuto = minuto;
        }

        public int getHora() {
            return hora;
        }

        public int getMinuto() {
            return minuto;
        }
    }
    public class Response {
        boolean success;

        public Response(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
