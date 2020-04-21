package com.consultoraestrategia.ss_crmeducativo.eventos.data.source.local;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioListaUsuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CalendarioEventoQuery;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposUi;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEEventos;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EventoLocalDataSource implements EventosDataSource {
    private static final int EVENTO=526, ACTIVIDAD=528, CITA=530, TAREA=529, NOTICIA=527;
    @Override
    public boolean saveLike(EventosUi eventosUi) {
        boolean status = false;
        try {

            SQLite.update(Evento.class)
                    .set(Evento_Table.like.eq(eventosUi.getLike()),
                            Evento_Table.likeCount.eq(eventosUi.getLikeCount()))
                    .where(Evento_Table.eventoId.eq(eventosUi.getIdEvento()))
                    .execute();
            status = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public List<TiposEventosUi> getTipoEvento() {
        List<TiposEventosUi> tiposEventosUiList = new ArrayList<>();
        List<Tipos> tiposList = SQLite.select()
                .from(Tipos.class)
                .where(Tipos_Table.objeto.eq("T_CE_MOV_EVENTOS"))
                .and(Tipos_Table.concepto.eq("TipoEvento"))
                .queryList();

        for (Tipos tipos: tiposList) {
            TiposEventosUi tiposEventosUi = new TiposEventosUi();
            switch (tipos.getTipoId()){
                case TAREA:
                    tiposEventosUi.setTipos(TiposUi.TAREAS);
                    break;
                case EVENTO:
                    tiposEventosUi.setTipos(TiposUi.EVENTOS);
                    break;
                case ACTIVIDAD:
                    tiposEventosUi.setTipos(TiposUi.ACTIVIDADES);
                    break;
                case CITA:
                    tiposEventosUi.setTipos(TiposUi.CITAS);
                    break;
                case NOTICIA:
                    tiposEventosUi.setTipos(TiposUi.NOTICIA);
                    break;
                default:
                    tiposEventosUi.setTipos(TiposUi.DEFAULT);
                    break;
            }
            tiposEventosUi.setId(tipos.getTipoId());
            tiposEventosUi.setNombre(tipos.getNombre());
            tiposEventosUi.setToogle(false);
            tiposEventosUiList.add(tiposEventosUi);
        }

        return tiposEventosUiList;
    }

    @Override
    public void getLikesSaveCountLike(EventosUi request, SucessCallback<EventosUi> callback) {
        try {

            SQLite.update(Evento.class)
                    .set(Evento_Table.likeCount.eq(request.getLikeCount()))
                    .where(Evento_Table.eventoId.eq(request.getIdEvento()))
                    .execute();
            callback.onLoad(true, request);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, request);
        }
    }

    @Override
    public RetrofitCancel<BEEventos> sincronizarEventos(int idUsuario, int idGeoreferenciaId, SucessCallback<List<Object>> listSucessCallback) {
        return null;
    }

    @Override
    public void getEventosColegio(int idUsuario, int idGeoreferencia, TiposEventosUi tiposEventosUi, SucessCallback<List<EventosUi>> listSucessCallback) {
        Log.d("getEventosColegio", "idGeoreferencia: "+idGeoreferencia);
        Log.d("getEventosColegio", "idUsuario: "+idUsuario);

        try{



            Where<Evento> eventoWhere = SQLite.select(Utils.f_allcolumnTable(Evento_Table.ALL_COLUMN_PROPERTIES,Evento_Table.key.withTable().as("eventoId"),Calendario_Table.nombre.withTable().as("nombreCalendario"), Calendario_Table.cargo.withTable(), Calendario_Table.nUsuario.withTable()))
                    .from(Evento.class)
                    .innerJoin(Calendario.class)
                    .on(Evento_Table.calendarioId.withTable()
                            .eq(Calendario_Table.calendarioId.withTable()))
                    .where(Calendario_Table.usuarioId.withTable().eq(idUsuario))
                    .and(Calendario_Table.georeferenciaId.withTable().eq(idGeoreferencia))
                    .and(Calendario_Table.estado.withTable()
                            .notEq(Calendario.ESTADO_ELIMINADO))
                    .and(Evento_Table.estadoId.notEq(Evento.ESTADO_ELIMINADO))
                    .orderBy(Evento_Table.fechaEvento.desc());

            Where<Evento> eventoWhereExterno = SQLite.select(Utils.f_allcolumnTable(Evento_Table.ALL_COLUMN_PROPERTIES,Evento_Table.key.withTable().as("eventoId"),Calendario_Table.nombre.withTable().as("nombreCalendario"), Calendario_Table.cargo.withTable(), Calendario_Table.nUsuario.withTable()))
                    .from(Evento.class)
                    .innerJoin(Calendario.class)
                    .on(Evento_Table.calendarioId.withTable()
                            .eq(Calendario_Table.calendarioId.withTable()))
                    .innerJoin(CalendarioListaUsuario.class)
                    .on(CalendarioListaUsuario_Table.calendarioId.withTable()
                            .eq(Calendario_Table.calendarioId.withTable()))
                    .innerJoin(ListaUsuario.class)
                    .on(ListaUsuario_Table.listaUsuarioId.withTable()
                            .eq(CalendarioListaUsuario_Table.listaUsuarioId.withTable()))
                    .innerJoin(ListaUsuarioDetalle.class)
                    .on(ListaUsuarioDetalle_Table.listaUsuarioId.withTable()
                            .eq(ListaUsuario_Table.listaUsuarioId.withTable()))
                    .where(ListaUsuarioDetalle_Table.usuarioId.withTable().eq(idUsuario))
                    .and(Calendario_Table.estado.withTable()
                            .notEq(Calendario.ESTADO_ELIMINADO))
                    .and(Evento_Table.estadoId.notEq(Evento.ESTADO_ELIMINADO))
                    .and(Evento_Table.estadoPublicacion.withTable().eq(true))
                    .orderBy(Evento_Table.fechaEvento.desc());

            if(tiposEventosUi==null){
                tiposEventosUi = new TiposEventosUi(TiposUi.TODOS);
            }

            if (tiposEventosUi.getTipos() != TiposUi.TODOS) {
                eventoWhere.and(Evento_Table.tipoEventoId.withTable().eq(tiposEventosUi.getId()));
                eventoWhereExterno.and(Evento_Table.tipoEventoId.withTable().eq(tiposEventosUi.getId()));
            }

            List<CalendarioEventoQuery> eventoList = eventoWhere
                    .queryCustomList(CalendarioEventoQuery.class);
            List<CalendarioEventoQuery> eventoListExterno = eventoWhereExterno
                    .queryCustomList(CalendarioEventoQuery.class);


            List<EventosUi> eventosUiList = new ArrayList<>();

            eventosUiList.addAll(getEventos(eventoList, false));
            eventosUiList.addAll(getEventos(eventoListExterno, true));


            Collections.sort(eventosUiList, new Comparator<EventosUi>() {
                @Override
                public int compare(EventosUi o1, EventosUi o2) {
                    Calendar calendar1 = Calendar.getInstance();
                    Calendar calendar2 = Calendar.getInstance();
                    calendar1.setTimeInMillis(o1.getFechaCreacion());
                    calendar2.setTimeInMillis(o2.getFechaCreacion());
                    Log.d("getEventosColegio", "calendar2.compareTo(calendar1) :  " +calendar2.compareTo(calendar1));
                    return calendar2.compareTo(calendar1);
                }
            });

            listSucessCallback.onLoad(true, eventosUiList);
        }catch (Exception e){
            e.printStackTrace();
            listSucessCallback.onLoad(false, new ArrayList<EventosUi>());
        }
    }

    private List<EventosUi> getEventos(List<CalendarioEventoQuery> eventoList, boolean externo){
        List<EventosUi> eventosUiList = new ArrayList<>();

        for (CalendarioEventoQuery evento: eventoList){
            EventosUi eventosUi = new EventosUi();
            eventosUi.setExterno(externo);
            eventosUi.setIdEvento(evento.getEventoId());
            eventosUi.setLikeCount(evento.getLikeCount());
            eventosUi.setTitulo(evento.getTitulo());
            eventosUi.setDescripcion(evento.getDescripcion());
            eventosUi.setCalendarioId(evento.getCalendarioId());
            eventosUi.setFechaCreacion(evento.getFechaCreacion());
            eventosUi.setLike(evento.isLike());
            eventosUi.setNombreCalendario(evento.getNombreCalendario());

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(evento.getFechaEvento());
            List<Integer> list = Utils.changeHourMinuto(evento.getHoraEvento());
            calendar.set(Calendar.HOUR_OF_DAY, list.get(0));
            calendar.set(Calendar.MINUTE, list.get(1));
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND, 0);
            eventosUi.setFechaEvento(calendar.getTimeInMillis());

            eventosUi.setImagen(evento.getPathImagen());

            Tipos tipos = SQLite.select()
                    .from(Tipos.class)
                    .where(Tipos_Table.tipoId.withTable().eq(evento.getTipoEventoId()))
                    .querySingle();
            TiposEventosUi tiposUi = new TiposEventosUi();
            if (tipos!=null){

                tiposUi.setNombre(tipos.getNombre());
                tiposUi.setTipos(TiposUi.DEFAULT);
                switch (tipos.getTipoId()){
                    case EVENTO:
                        tiposUi.setTipos(TiposUi.EVENTOS);
                        break;
                    case TAREA:
                        tiposUi.setTipos(TiposUi.TAREAS);
                        break;
                    case CITA:
                        tiposUi.setTipos(TiposUi.CITAS);
                        break;
                    case ACTIVIDAD:
                        tiposUi.setTipos(TiposUi.ACTIVIDADES);
                        break;
                    case NOTICIA:
                        tiposUi.setTipos(TiposUi.NOTICIA);
                        break;
                }

            }else {
                tiposUi.setNombre("desconocido");
                tiposUi.setTipos(TiposUi.EVENTOS);
            }
            eventosUi.setTiposUi(tiposUi);

            Entidad entidad = SQLite.select()
                    .from(Entidad.class)
                    .where(Entidad_Table.entidadId.withTable().eq(evento.entidadId))
                    .querySingle();

            if (entidad!=null){
                eventosUi.setNombreEntidad(entidad.getNombre());
                eventosUi.setFotoEntidad(entidad.getFoto());
            }
            eventosUi.setPersona(evento.getnUsuario());
            Log.d("evento", "evento: "+evento.getCargo());
            eventosUi.setCargo(evento.getCargo());

            List<ListaUsuarioDetalle> listaUsuarioDetalles = SQLite.select()
                    .from(ListaUsuarioDetalle.class)
                    .innerJoin(ListaUsuario.class)
                    .on(ListaUsuarioDetalle_Table.listaUsuarioId.withTable()
                            .eq(ListaUsuario_Table.listaUsuarioId.withTable()))
                    .innerJoin(CalendarioListaUsuario.class)
                    .on(ListaUsuario_Table.listaUsuarioId.withTable()
                            .eq(CalendarioListaUsuario_Table.listaUsuarioId.withTable()))
                    .where(CalendarioListaUsuario_Table.calendarioId.withTable().eq(evento.getCalendarioId()))
                    .queryList();

            eventosUi.setCantidaEnviar(listaUsuarioDetalles.size());
            eventosUi.setPublicado(evento.isEstadoPublicacion());

            eventosUiList.add(eventosUi);
        }

        return eventosUiList;
    }

    @Override
    public RetrofitCancel deleteEvento(EventosUi eventoUi, SucessCallback<Boolean> booleanSucessCallback) {
        return null;
    }

    @Override
    public RetrofitCancel enviarEvento(EventosUi eventoUi, SucessCallback<Boolean> booleanSucessCallback) {
        return null;
    }

    @Override
    public List<Object> getListaUsuarios(String calendarioId) {


        List<ListaUsuario> listaUsuarios = SQLite.select(Utils.f_allcolumnTable(ListaUsuario_Table.ALL_COLUMN_PROPERTIES))
                .from(ListaUsuario.class)
                .innerJoin(CalendarioListaUsuario.class)
                .on(ListaUsuario_Table.listaUsuarioId.withTable()
                        .eq(CalendarioListaUsuario_Table.listaUsuarioId.withTable()))
                .where(CalendarioListaUsuario_Table.calendarioId.withTable().eq(calendarioId))
                .queryList();

        List<Object> personaUiList = new ArrayList<>();
        for (ListaUsuario listaUsuario: listaUsuarios){
            personaUiList.add(listaUsuario.getNombre());

            List<Persona> usuarioList = SQLite.select()
                    .from(Persona.class)
                    .innerJoin(Usuario.class)
                    .on(Persona_Table.personaId.withTable()
                            .eq(Usuario_Table.personaId.withTable()))
                    .innerJoin(ListaUsuarioDetalle.class)
                    .on(Usuario_Table.usuarioId.withTable()
                            .eq(ListaUsuarioDetalle_Table.usuarioId.withTable()))
                    .where(ListaUsuarioDetalle_Table.listaUsuarioId.withTable().eq(listaUsuario.getListaUsuarioId()))
                    .orderBy(Persona_Table.nombres.asc())
                    .queryList();

            for (Persona persona: usuarioList){
                PersonaUi personaUi = new PersonaUi();
                personaUi.setId(persona.getPersonaId());
                String nombre = Utils.capitalize(persona.getFirstName()) + " " +  Utils.capitalize(persona.getApellidoPaterno()) + " " +   Utils.capitalize(persona.getApellidoMaterno());
                personaUi.setNombre(nombre);
                personaUi.setImagen(persona.getFoto());
                personaUiList.add(personaUi);
            }

        }

        Log.d("getListaUsuarios", "Size: " + personaUiList.size());


        return personaUiList;
    }


}
