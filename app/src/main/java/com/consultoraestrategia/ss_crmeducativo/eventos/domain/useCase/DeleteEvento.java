package com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class DeleteEvento implements UseCaseLoginSincrono<EventosUi, Boolean> {
    private EventosRepository repository;

    public DeleteEvento(EventosRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrofitCancel execute(EventosUi eventoUi, final Callback<Boolean> callback) {
        return repository.deleteEvento(eventoUi, new EventosDataSource.SucessCallback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {
                callback.onResponse(success, success);
            }

        });
    }
}