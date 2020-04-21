package com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public class SincronizarEventos implements UseCaseLoginSincrono<SincronizarEventos.Request, Object> {

    private EventosRepository repository;

    public SincronizarEventos(EventosRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrofitCancel execute(Request request, final Callback<Object> callback) {
        return repository.sincronizarEventos(request.getUsuarioId(), request.getGeoreferenciaId(),new EventosDataSource.SucessCallback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                callback.onResponse(success, null);
            }
        });
    }

    public static class Request {
        private int usuarioId;
        private int georeferenciaId;

        public Request(int usuarioId, int georeferenciaId) {
            this.usuarioId = usuarioId;
            this.georeferenciaId = georeferenciaId;
        }

        public int getUsuarioId() {
            return usuarioId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }
    }
}