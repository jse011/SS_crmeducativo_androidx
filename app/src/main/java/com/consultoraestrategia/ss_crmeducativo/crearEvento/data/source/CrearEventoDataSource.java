package com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoEventoUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public interface CrearEventoDataSource {

    interface Callback{
        void load(boolean success);
    }
    EventoUi getEventoDocente(String eventoId);

    List<TipoCalendarioUi> getListTipoCalendario(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId);

    List<TipoEventoUi> getTipoEventos();

    RetrofitCancel saveEvento(EventoUi eventoUi, Callback callback);
}
