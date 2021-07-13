package com.consultoraestrategia.ss_crmeducativo.model.docentementor;

import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoAdjunto;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoPersona;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;

import java.util.List;

public class BEEventos {
    private List<Evento> evento;
    private List<Calendario> calendario;
    private List<CalendarioListaUsuario> calendarioListaUsuario;
    private List<ListaUsuario> listaUsuarios;
    private List<ListaUsuarioDetalle> listUsuarioDetalle;
    private List<Tipos> tipos;
    public List<Usuario> usuario;
    public List<Persona> persona;
    private List<Relaciones> relaciones;
    private List<EventoPersona> eventoPersona;
    private List<EventoAdjunto> eventoAdjuntos;
    public List<Evento> getEvento() {
        return evento;
    }

    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }

    public List<Calendario> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<Calendario> calendario) {
        this.calendario = calendario;
    }

    public List<CalendarioListaUsuario> getCalendarioListaUsuario() {
        return calendarioListaUsuario;
    }

    public void setCalendarioListaUsuario(List<CalendarioListaUsuario> calendarioListaUsuario) {
        this.calendarioListaUsuario = calendarioListaUsuario;
    }

    public List<ListaUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<ListaUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<ListaUsuarioDetalle> getListUsuarioDetalle() {
        return listUsuarioDetalle;
    }

    public void setListUsuarioDetalle(List<ListaUsuarioDetalle> listUsuarioDetalle) {
        this.listUsuarioDetalle = listUsuarioDetalle;
    }

    public List<Tipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipos> tipos) {
        this.tipos = tipos;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    public List<Persona> getPersona() {
        return persona;
    }

    public void setPersona(List<Persona> persona) {
        this.persona = persona;
    }

    public List<Relaciones> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(List<Relaciones> relaciones) {
        this.relaciones = relaciones;
    }

    public List<EventoPersona> getEventoPersona() {
        return eventoPersona;
    }

    public void setEventoPersona(List<EventoPersona> eventoPersona) {
        this.eventoPersona = eventoPersona;
    }

    public List<EventoAdjunto> getEventoAdjuntos() {
        return eventoAdjuntos;
    }

    public void setEventoAdjuntos(List<EventoAdjunto> eventoAdjuntos) {
        this.eventoAdjuntos = eventoAdjuntos;
    }
}
