package com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi;

import java.util.List;

public class GetEventosColegio extends UseCase<GetEventosColegio.RequestValues, GetEventosColegio.ResponseValues> {

    private EventosRepository eventosRepository;

    public GetEventosColegio(EventosRepository eventosRepository) {
        this.eventosRepository = eventosRepository;
    }

    @Override
    protected void executeUseCase(GetEventosColegio.RequestValues requestValues) {
        eventosRepository.getEventosColegio(requestValues.getIdUsuario(), requestValues.getIdGeoreferencia(),requestValues.getTiposEventosUi(),
             new EventosDataSource.SucessCallback<List<EventosUi>>() {
            @Override
            public void onLoad(boolean success, List<EventosUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private int idUsuario;
        private int idGeoreferencia;
        private TiposEventosUi tiposEventosUi;

        public RequestValues(int idUsuario, int idGeoreferencia, TiposEventosUi tiposEventosUi) {
            this.idUsuario = idUsuario;
            this.idGeoreferencia = idGeoreferencia;
            this.tiposEventosUi = tiposEventosUi;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public int getIdGeoreferencia() {
            return idGeoreferencia;
        }

        public TiposEventosUi getTiposEventosUi() {
            return tiposEventosUi;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValue {

        private List<EventosUi> list;

        public ResponseValues(List<EventosUi> list) {
            this.list = list;
        }

        public List<EventosUi> getList() {
            return list;
        }

        public void setList(List<EventosUi> list) {
            this.list = list;
        }
    }
}