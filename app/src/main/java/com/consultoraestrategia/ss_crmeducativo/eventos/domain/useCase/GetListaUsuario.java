package com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository;

import java.util.List;

public class GetListaUsuario {

    private EventosRepository repository;

    public GetListaUsuario(EventosRepository repository) {
        this.repository = repository;
    }


    public List<Object> execute(String calendarioId, String idEvento){
        return repository.getListaUsuarios(calendarioId, idEvento);
    }
    public class Request{
        private String calendarioId;

        public Request(String calendarioId) {
            this.calendarioId = calendarioId;
        }

        public String getCalendarioId() {
            return calendarioId;
        }
    }
}
