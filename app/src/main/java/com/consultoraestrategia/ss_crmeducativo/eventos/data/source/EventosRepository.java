package com.consultoraestrategia.ss_crmeducativo.eventos.data.source;

import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.local.EventoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.remote.EventoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEEventos;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public class EventosRepository implements EventosDataSource {
    private EventoLocalDataSource localDataSource;
    private EventoRemoteDataSource remoteDataSource;

    public EventosRepository(EventoLocalDataSource localDataSource, EventoRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public boolean saveLike(EventosUi eventosUi) {
        remoteDataSource.saveLike(eventosUi);
        return localDataSource.saveLike(eventosUi);
    }

    @Override
    public List<TiposEventosUi> getTipoEvento() {
        return localDataSource.getTipoEvento();
    }

    @Override
    public void getLikesSaveCountLike(final EventosUi request, final SucessCallback<EventosUi> callback) {
        remoteDataSource.getLikesSaveCountLike(request, new SucessCallback<EventosUi>() {
            @Override
            public void onLoad(boolean success, EventosUi item) {
                if(success){
                    localDataSource.getLikesSaveCountLike(item, callback);
                }else {
                    callback.onLoad(false, request);
                }
            }
        });
    }

    @Override
    public RetrofitCancel<BEEventos> sincronizarEventos(int idUsuario, int idGeoreferenciaId, SucessCallback<List<Object>> listSucessCallback) {
        return remoteDataSource.sincronizarEventos(idUsuario, idGeoreferenciaId, listSucessCallback);
    }

    @Override
    public void getEventosColegio(int idUsuario, int idGeoreferencia, TiposEventosUi tiposEventosUi, SucessCallback<List<EventosUi>> listSucessCallback) {
        localDataSource.getEventosColegio(idUsuario, idGeoreferencia, tiposEventosUi, listSucessCallback);
    }

    @Override
    public RetrofitCancel deleteEvento(EventosUi eventoUi, SucessCallback<Boolean> booleanSucessCallback) {
        return remoteDataSource.deleteEvento(eventoUi, booleanSucessCallback);
    }

    @Override
    public RetrofitCancel enviarEvento(EventosUi eventoUi, SucessCallback<Boolean> booleanSucessCallback) {
        return remoteDataSource.enviarEvento(eventoUi, booleanSucessCallback);
    }

    @Override
    public List<Object> getListaUsuarios(String calendarioId) {
        return localDataSource.getListaUsuarios(calendarioId);
    }


}
