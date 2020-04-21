package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class SaveEvento implements UseCaseLoginSincrono<EventoUi, Boolean> {
    private CrearEventoRepository repository;

    public SaveEvento(CrearEventoRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrofitCancel execute(EventoUi eventoUi, final Callback<Boolean> callback) {
        return repository.saveEvento(eventoUi, new CrearEventoDataSource.Callback() {
            @Override
            public void load(boolean success) {
                callback.onResponse(success, success);
            }
        });
    }
}
