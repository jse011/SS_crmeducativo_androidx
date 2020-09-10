package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class SaveEvento {
    private CrearEventoRepository repository;

    public SaveEvento(CrearEventoRepository repository) {
        this.repository = repository;
    }

    public RetrofitCancel execute(EventoUi eventoUi, boolean publicar, final UseCaseLoginSincrono.Callback<Boolean> callback) {
        return repository.saveEvento(eventoUi, publicar, new CrearEventoDataSource.Callback() {
            @Override
            public void load(boolean success) {
                callback.onResponse(success, success);
            }
        });
    }
}
