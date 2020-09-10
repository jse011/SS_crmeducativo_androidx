package com.consultoraestrategia.ss_crmeducativo.api.retrofit.parametros;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoPersona;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParametrosEventos extends ApiRetrofit.Parameters {
    @SerializedName("vobj_Eventos")
    private Evento evento;
    @SerializedName("vobj_Calendario")
    private Calendario calendario;
    @SerializedName("vlst_EventoPersona")
    private List<EventoPersona> eventoPersonaList;

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public List<EventoPersona> getEventoPersonaList() {
        return eventoPersonaList;
    }

    public void setEventoPersonaList(List<EventoPersona> eventoPersonaList) {
        this.eventoPersonaList = eventoPersonaList;
    }
}
