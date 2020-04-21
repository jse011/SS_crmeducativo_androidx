package com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase;


import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;

public class GetLikeSaveCountLike extends UseCaseSincrono<EventosUi, EventosUi> {

    private EventosRepository repository;

    public GetLikeSaveCountLike(EventosRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(EventosUi request, final Callback<EventosUi> callback) {
        repository.getLikesSaveCountLike(request, new EventosDataSource.SucessCallback<EventosUi>() {
            @Override
            public void onLoad(boolean success, EventosUi item) {
                callback.onResponse(success, item);
            }
        });
    }

}
