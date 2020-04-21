package com.consultoraestrategia.ss_crmeducativo.eventos.data.source;

import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEEventos;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public interface EventosDataSource {
    boolean saveLike(EventosUi eventosUi);

    List<TiposEventosUi> getTipoEvento();
    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }
    void getLikesSaveCountLike(EventosUi request, EventosRepository.SucessCallback<EventosUi> callback );
    RetrofitCancel<BEEventos> sincronizarEventos(int idUsuario, int idGeoreferenciaId, SucessCallback<List<Object>> listSucessCallback);
    void getEventosColegio(int idUsuario, int idGeoreferencia, TiposEventosUi tiposEventosUi, SucessCallback<List<EventosUi>> listSucessCallback);
    RetrofitCancel deleteEvento(EventosUi eventoUi, SucessCallback<Boolean> booleanSucessCallback);
    RetrofitCancel enviarEvento(EventosUi eventoUi, SucessCallback<Boolean> booleanSucessCallback);
    List<Object> getListaUsuarios(String calendarioId);
}
