package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;

public class CalcularMediaDesviacion {

   RubroRepository rubroRepository;

    public CalcularMediaDesviacion(RubroRepository rubroRepository) {
        this.rubroRepository = rubroRepository;
    }
    public Response execute(Requests request){
        return  new Response(rubroRepository.recalcularMediaDv(request.getIdRubro()));
    }

    public static class Requests
    {
        String idRubro;

        public Requests(String idRubro) {
            this.idRubro = idRubro;
        }

        public String getIdRubro() {
            return idRubro;
        }
    }

    public static class Response {
        boolean success;

        public Response(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
