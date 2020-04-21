package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;

import java.util.List;

public class ValidarUsuario {
    ComportamientoRepository comportamientoRepository;

    public ValidarUsuario(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }
    public Response execute(Request request){
        return  new Response(comportamientoRepository.validarUsuario(request.getTipo(), request.getAlumnoId(), request.getCargaACademicaId(), request.getGeoreferenciaId()));
    }
    public static class Request {
        private int georeferenciaId;
        DestinoUi.Tipo tipo;
        int alumnoId;
        int cargaACademicaId;

        public Request(DestinoUi.Tipo tipo, int alumnoId, int cargaACademicaId, int georeferenciaId) {
            this.tipo = tipo;
            this.alumnoId = alumnoId;
            this.cargaACademicaId = cargaACademicaId;
            this.georeferenciaId = georeferenciaId;
        }

        public int getAlumnoId() {
            return alumnoId;
        }

        public DestinoUi.Tipo getTipo() {
            return tipo;
        }

        public int getCargaACademicaId() {
            return cargaACademicaId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }
    }
    public class Response{
        List<Integer> usuarioIds;

        public Response(List<Integer> usuarioIds) {
            this.usuarioIds = usuarioIds;
        }

        public List<Integer> getUsuarioIds() {
            return usuarioIds;
        }
    }
}
