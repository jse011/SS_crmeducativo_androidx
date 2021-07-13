package com.consultoraestrategia.ss_crmeducativo.eventos.data.source.local;

import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.createTeam.entities.Person;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioListaUsuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato;
import com.consultoraestrategia.ss_crmeducativo.entities.Contrato_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad;
import com.consultoraestrategia.ss_crmeducativo.entities.DetalleContratoAcad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoAdjunto;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoAdjunto_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoPersona;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoPersona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.CalendarioEventoQuery;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TipoAdjuntoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposUi;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEEventos;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.util.YouTubeThumbnail;
import com.consultoraestrategia.ss_crmeducativo.util.YouTubeUrlParser;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EventoLocalDataSource implements EventosDataSource {
    private static final int EVENTO=526, ACTIVIDAD=528, CITA=530, TAREA=529, NOTICIA=527, AGENDA = 620 ;
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
                case AGENDA:
                    tiposEventosUi.setTipos(TiposUi.AGENDA);
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



            Where<Evento> eventoWhere = SQLite.select(
                    Utils.f_allcolumnTable(Evento_Table.ALL_COLUMN_PROPERTIES,
                            Evento_Table.key.withTable().as("eventoId"),
                            Calendario_Table.nombre.withTable().as("nombreCalendario"),
                            Calendario_Table.cargo.withTable(),
                            Calendario_Table.nUsuario.withTable(),
                            EventoAdjunto_Table.eventoAdjuntoId.withTable(),
                            EventoAdjunto_Table.titulo.withTable().as("adjuntoTitulo"),
                            EventoAdjunto_Table.tipoId.withTable().as("adjuntoTipoId"),
                            EventoAdjunto_Table.driveId.withTable().as("adjuntoDriveId"),
                            Calendario_Table.nFoto.withTable()))
                    .from(Evento.class)
                    .innerJoin(Calendario.class)
                    .on(Evento_Table.calendarioId.withTable()
                            .eq(Calendario_Table.calendarioId.withTable()))
                    .leftOuterJoin(EventoAdjunto.class)
                    .on(EventoAdjunto_Table.eventoId.withTable().eq(Evento_Table.eventoId.withTable()))
                    .where(Calendario_Table.usuarioId.withTable().eq(idUsuario))
                    .and(Calendario_Table.georeferenciaId.withTable().eq(idGeoreferencia))
                    .and(Calendario_Table.estado.withTable()
                            .notEq(Calendario.ESTADO_ELIMINADO))
                    .and(Evento_Table.estadoId.notEq(Evento.ESTADO_ELIMINADO))
                    .groupBy(Evento_Table.eventoId.withTable(), EventoAdjunto_Table.eventoAdjuntoId)
                    .orderBy(Evento_Table.fechaEvento.desc());

            Where<Evento> eventoWhereExterno = SQLite.select(Utils.f_allcolumnTable(Evento_Table.ALL_COLUMN_PROPERTIES,
                    Evento_Table.key.withTable().as("eventoId"),
                    Calendario_Table.nombre.withTable().as("nombreCalendario"),
                    Calendario_Table.cargo.withTable(),
                    Calendario_Table.nUsuario.withTable(),
                    EventoAdjunto_Table.eventoAdjuntoId.withTable(),
                    EventoAdjunto_Table.titulo.withTable().as("adjuntoTitulo"),
                    EventoAdjunto_Table.tipoId.withTable().as("adjuntoTipoId"),
                    EventoAdjunto_Table.driveId.withTable().as("adjuntoDriveId"),
                    Calendario_Table.nFoto.withTable()))
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
                    .leftOuterJoin(EventoAdjunto.class)
                    .on(EventoAdjunto_Table.eventoId.withTable().eq(Evento_Table.eventoId.withTable()))
                    .where(ListaUsuarioDetalle_Table.usuarioId.withTable().eq(idUsuario))
                    .and(Calendario_Table.estado.withTable()
                            .notEq(Calendario.ESTADO_ELIMINADO))
                    .and(Evento_Table.estadoId.notEq(Evento.ESTADO_ELIMINADO))
                    .and(Evento_Table.estadoPublicacion.withTable().eq(true))
                    .groupBy(Evento_Table.eventoId.withTable(), EventoAdjunto_Table.eventoAdjuntoId)
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

                    Date fecha1_ = new Date(o1.getTiposUi().getTipos() == TiposUi.AGENDA? o1.getFechaCreacion() : o1.getFechaPublicacion());
                    Calendar date1 = Calendar.getInstance();
                    date1.setTimeInMillis(o1.getTiposUi().getTipos() == TiposUi.AGENDA? o1.getFechaCreacion() : o1.getFechaPublicacion());
                    if(date1.get(Calendar.YEAR)<2000) fecha1_= new Date(o1.getFechaPublicacion());

                    Date fecha2_ = new Date(o2.getTiposUi().getTipos() == TiposUi.AGENDA? o2.getFechaCreacion() : o2.getFechaPublicacion());
                    Calendar date2 = Calendar.getInstance();
                    date2.setTimeInMillis(o2.getTiposUi().getTipos() == TiposUi.AGENDA? o2.getFechaCreacion() : o2.getFechaPublicacion());
                    if(date2.get(Calendar.YEAR)<2000) fecha2_ = new Date(o2.getFechaPublicacion());

                    return fecha2_.compareTo(fecha1_);
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
            eventosUi.setIdEvento(evento.getEventoId());
            int position = eventosUiList.indexOf(eventosUi);
            if(position==-1){

                eventosUi.setExterno(externo);
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
                eventosUi.setFechaPublicacion(evento.getFechaPublicacion());
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
                        case AGENDA:
                            tiposUi.setTipos(TiposUi.AGENDA);
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
                    eventosUi.setFotoEntidad(evento.getnFoto());
                }
                eventosUi.setPersona(Utils.capitalize(evento.getnUsuario()));

                eventosUi.setCargo(evento.getCargo());

            /*List<ListaUsuarioDetalle> listaUsuarioDetalles = SQLite.select()
                    .from(ListaUsuarioDetalle.class)
                    .innerJoin(ListaUsuario.class)
                    .on(ListaUsuarioDetalle_Table.listaUsuarioId.withTable()
                            .eq(ListaUsuario_Table.listaUsuarioId.withTable()))
                    .innerJoin(CalendarioListaUsuario.class)
                    .on(ListaUsuario_Table.listaUsuarioId.withTable()
                            .eq(CalendarioListaUsuario_Table.listaUsuarioId.withTable()))
                    .where(CalendarioListaUsuario_Table.calendarioId.withTable().eq(evento.getCalendarioId()))
                    .queryList();

            eventosUi.setCantidaEnviar(listaUsuarioDetalles.size());*/
                eventosUi.setPublicado(evento.isEstadoPublicacion());
                eventosUi.setAdjuntoUiPreviewList(new ArrayList<EventoAdjuntoUi>());
                eventosUi.setAdjuntoUiList(new ArrayList<EventoAdjuntoUi>());


                if(eventosUi.getFechaPublicacion()>912402000000L){
                    String fecha_ = "Publicado " + Utils.getFechaDiaMesAnho(eventosUi.getFechaPublicacion());
                    eventosUi.setNombreFechaPublicion(fecha_);
                }else{
                    eventosUi.setNombreFechaPublicion("");
                }
                eventosUiList.add(eventosUi);
            }else {
                eventosUi = eventosUiList.get(position);
            }

            EventoAdjuntoUi adjuntoUi = new EventoAdjuntoUi();
            adjuntoUi.setEventoAjuntoId(evento.eventoAdjuntoId);
            if(!TextUtils.isEmpty(adjuntoUi.getEventoAjuntoId())){
                adjuntoUi.setDriveId(evento.adjuntoDriveId);
                adjuntoUi.setTitulo(evento.adjuntoTitulo);
                switch (evento.adjuntoTipoId){
                    case Archivo.TIPO_VIDEO:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.VIDEO);
                        eventosUi.getAdjuntoUiPreviewList().add(adjuntoUi);
                        adjuntoUi.setVideo(true);
                        break;
                    case Archivo.TIPO_VINCULO:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.LINK);
                        eventosUi.getAdjuntoUiList().add(adjuntoUi);
                        break;
                    case Archivo.TIPO_DOCUMENTO:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.DOCUMENTO);
                        eventosUi.getAdjuntoUiList().add(adjuntoUi);
                        break;
                    case Archivo.TIPO_IMAGEN:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.IMAGEN);
                        eventosUi.getAdjuntoUiPreviewList().add(adjuntoUi);
                        break;
                    case Archivo.TIPO_AUDIO:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.AUDIO);
                        eventosUi.getAdjuntoUiList().add(adjuntoUi);
                        break;
                    case Archivo.TIPO_HOJA_CALCULO:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.HOJACALCULO);
                        eventosUi.getAdjuntoUiList().add(adjuntoUi);
                        break;
                    case Archivo.TIPO_DIAPOSITIVA:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.PRESENTACION);
                        eventosUi.getAdjuntoUiList().add(adjuntoUi);
                        break;
                    case Archivo.TIPO_PDF:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.PDF);
                        eventosUi.getAdjuntoUiList().add(adjuntoUi);
                        break;
                    case Archivo.TIPO_YOUTUBE:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.VIDEO);
                        eventosUi.getAdjuntoUiPreviewList().add(adjuntoUi);
                        adjuntoUi.setVideo(true);
                        break;
                    default:
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.OTROS);
                        eventosUi.getAdjuntoUiList().add(adjuntoUi);
                        break;
                }

                if(adjuntoUi.getTipoArchivo()==TipoAdjuntoUi.VIDEO){
                    String idYoutube = YouTubeUrlParser.getVideoId(adjuntoUi.getTitulo());
                    if(TextUtils.isEmpty(idYoutube)){
                        adjuntoUi.setImagePreview("https://drive.google.com/thumbnail?id="+adjuntoUi.getDriveId());
                    }else {
                        adjuntoUi.setTipoArchivo(TipoAdjuntoUi.YOUTUBE);
                        adjuntoUi.setImagePreview(YouTubeThumbnail.getUrlFromVideoId(idYoutube,YouTubeThumbnail.Quality.DEFAULT));
                        adjuntoUi.setYotubeId(idYoutube);
                    }
                }else if(adjuntoUi.getTipoArchivo()==TipoAdjuntoUi.YOUTUBE){
                    String idYoutube = YouTubeUrlParser.getVideoId(adjuntoUi.getTitulo());
                    adjuntoUi.setTipoArchivo(TipoAdjuntoUi.YOUTUBE);
                    adjuntoUi.setImagePreview(YouTubeThumbnail.getUrlFromVideoId(idYoutube,YouTubeThumbnail.Quality.DEFAULT));
                    adjuntoUi.setYotubeId(idYoutube);
                }else if(adjuntoUi.getTipoArchivo() == TipoAdjuntoUi.IMAGEN){
                    adjuntoUi.setImagePreview("https://drive.google.com/uc?id="+adjuntoUi.getDriveId());
                }
            }


        }

        for(EventosUi eventoUi : eventosUiList){
            if (eventoUi.getTiposUi().getTipos() == TiposUi.NOTICIA ||
                    eventoUi.getTiposUi().getTipos()  == TiposUi.EVENTOS||(eventoUi.getTiposUi().getTipos()  == TiposUi.AGENDA && !TextUtils.isEmpty(eventoUi.getImagen()))){
                if(!TextUtils.isEmpty(eventoUi.getImagen())){
                   EventoAdjuntoUi adjuntoUi = new EventoAdjuntoUi();
                    adjuntoUi.setImagePreview(eventoUi.getImagen());
                    adjuntoUi.setTipoArchivo(TipoAdjuntoUi.IMAGEN);
                    adjuntoUi.setTitulo(eventoUi.getTitulo());
                    eventoUi.getAdjuntoUiPreviewList().add(adjuntoUi);
                }
            }

            if (eventoUi.getTiposUi().getTipos()  == TiposUi.NOTICIA ||
                    eventoUi.getTiposUi().getTipos()  == TiposUi.EVENTOS||(eventoUi.getTiposUi().getTipos()  == TiposUi.AGENDA && !TextUtils.isEmpty(eventoUi.getImagen()))){

                if(TextUtils.isEmpty(eventoUi.getImagen()) && !eventoUi.getAdjuntoUiPreviewList().isEmpty()){
                    eventoUi.setImagen(eventoUi.getAdjuntoUiPreviewList().get(0).getImagePreview());
                }
            }
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


    public List<Object> getListaUsuarios2(String calendarioId, String idEvento) {
        List<Object> personaUiList = new ArrayList<>();
/*
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

        }*/


        Evento evento = SQLite.select()
                .from(Evento.class)
                .where(Evento_Table.eventoId.eq(idEvento))
                .querySingle();

        Calendario calendario = SQLite.select()
                .from(Calendario.class)
                .where(Calendario_Table.calendarioId.eq(calendarioId))
                .querySingle();

        if (evento!=null&&calendario!=null){

            List<Contrato> contratoList = new ArrayList<>();
            if(calendario.getCargaAcademicaId()>0){
                contratoList.addAll(SQLite.select(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                        .from(Contrato.class)
                        .innerJoin(DetalleContratoAcad.class)
                        .on(Contrato_Table.idContrato.withTable()
                                .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                        .where(DetalleContratoAcad_Table.cargaAcademicaId.eq(calendario.getCargaAcademicaId()))
                        .groupBy(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                        .queryList());
            }else {
                contratoList.addAll(SQLite.select(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                        .from(Contrato.class)
                        .innerJoin(DetalleContratoAcad.class)
                        .on(Contrato_Table.idContrato.withTable()
                                .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                        .where(DetalleContratoAcad_Table.cargaCursoId.eq(calendario.getCargaCursoId()))
                        .groupBy(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                        .queryList());
            }

            if (!evento.isEnvioPersonalizado()){

                for (Contrato contrato : contratoList){

                    Persona alumno = SQLite.select()
                            .from(Persona.class)
                            .where(Persona_Table.personaId.eq(contrato.getPersonaId()))
                            .querySingle();

                    Persona apoderado = SQLite.select()
                            .from(Persona.class)
                            .where(Persona_Table.personaId.eq(contrato.getApoderadoId()))
                            .querySingle();

                    PersonaUi alumnoUi = transformar(alumno);
                    PersonaUi apoderadoUi = transformar(apoderado);

                    if(calendario.getRolId()==5){

                        Tipos relacionApoderado = SQLite.select()
                                .from(Tipos.class)
                                .innerJoin(Relaciones.class)
                                .on(Tipos_Table.tipoId.withTable()
                                        .eq(Relaciones_Table.tipoId.withTable()))
                                .where(Relaciones_Table.personaPrincipalId.notEq(contrato.getApoderadoId()))
                                .and(Relaciones_Table.personaVinculadaId.notEq(contrato.getPersonaId()))
                                .querySingle();

                        String tipoApoderante = (relacionApoderado!=null?relacionApoderado.getNombre()+" de ":"Poderante de ") + alumnoUi.getNombre();
                        apoderadoUi.setDetalle(tipoApoderante);
                        personaUiList.add(apoderadoUi);

                        List<Relaciones> relacionesList = SQLite.select()
                                .from(Relaciones.class)
                                .where(Relaciones_Table.personaVinculadaId.eq(contrato.getPersonaId()))
                                .and(Relaciones_Table.activo.eq(true))
                                .and(Relaciones_Table.personaPrincipalId.notEq(contrato.getApoderadoId()))
                                .queryList();

                        for (Relaciones relaciones : relacionesList){
                            Persona familiar = SQLite.select()
                                    .from(Persona.class)
                                    .where(Persona_Table.personaId.eq(relaciones.getPersonaVinculadaId()))
                                    .querySingle();

                            Tipos tipos = SQLite.select()
                                    .from(Tipos.class)
                                    .where(Tipos_Table.tipoId.eq(relaciones.getTipoId()))
                                    .querySingle();

                            String relacion = tipos!=null?tipos.getNombre():"";
                            relacion = relacion + " " +apoderadoUi.getNombre();
                            PersonaUi personaUi = transformar(familiar);
                            personaUi.setDetalle(relacion);
                            personaUiList.add(personaUi);
                        }

                    }else {
                        //      Alumno
                        //      hijo
                        //String relacion = "Apoderado " + apoderadoUi.getNombre();
                        //alumnoUi.setDetalle(relacion);
                        personaUiList.add(alumnoUi);
                    }

                }
            }else {

                List<Persona> eventoPersonaList = SQLite.select()
                        .from(Persona.class)
                        .innerJoin(EventoPersona.class)
                        .on(Persona_Table.personaId.withTable()
                                .eq(EventoPersona_Table.personaId.withTable()))
                        .where(EventoPersona_Table.eventoId.eq(idEvento))
                        .and(EventoPersona_Table.estado.eq(true))
                        .queryList();

                for (Persona persona : eventoPersonaList){
                    boolean isAlumno = false;
                    Contrato contratoAlumno = null;
                    for (Contrato contrato : contratoList){
                        isAlumno = contrato.getPersonaId()==persona.getPersonaId();
                        if(isAlumno){
                            contratoAlumno = contrato;
                            break;
                        }
                    }
                    PersonaUi personaUi = new PersonaUi();
                    personaUi.setId(persona.getPersonaId());
                    String nombre = Utils.capitalize(persona.getFirstName()) + " " +  Utils.capitalize(persona.getApellidoPaterno()) + " " +   Utils.capitalize(persona.getApellidoMaterno());
                    personaUi.setNombre(nombre);
                    personaUi.setImagen(persona.getFoto());
                    if(isAlumno){
                        PersonaUi apoderadoUi = transformar(SQLite.select()
                                .from(Persona.class)
                                .where(Persona_Table.personaId.eq(contratoAlumno.getApoderadoId()))
                                .querySingle());

                       // String relacion = "Apoderado " + apoderadoUi.getNombre();
                       // personaUi.setDetalle(relacion);
                    }else{

                        Relaciones relacione = null;
                        for (Contrato contrato : contratoList){
                            relacione =  SQLite.select()
                                    .from(Relaciones.class)
                                    .where(Relaciones_Table.personaPrincipalId.eq(contrato.getPersonaId()))
                                    .and(Relaciones_Table.personaVinculadaId.eq(persona.getPersonaId()))
                                    .querySingle();
                            if(relacione!=null)break;
                        }

                        Tipos relacionApoderado = SQLite.select()
                                .from(Tipos.class)
                                .where(Tipos_Table.tipoId.eq(relacione!=null?relacione.getTipoId():0))
                                .querySingle();

                        PersonaUi alumnoUi = transformar(SQLite.select()
                                .from(Persona.class)
                                .where(Persona_Table.personaId.eq(relacione!=null?relacione.getPersonaPrincipalId():0))
                                .querySingle());

                        String tipoApoderante = (relacionApoderado!=null?relacionApoderado.getNombre()+" de ":"Apoderante de ") + alumnoUi.getNombre();
                        personaUi.setDetalle(tipoApoderante);
                    }

                    personaUiList.add(personaUi);
                }

            }
        }

        return personaUiList;
    }

    @Override
    public List<Object> getListaUsuarios(String calendarioId, String idEvento) {
        List<Object> personaUiList = new ArrayList<>();
/*
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

        }*/


        Evento evento = SQLite.select()
                .from(Evento.class)
                .where(Evento_Table.eventoId.eq(idEvento))
                .querySingle();

        Calendario calendario = SQLite.select()
                .from(Calendario.class)
                .where(Calendario_Table.calendarioId.eq(calendarioId))
                .querySingle();

        if (evento!=null&&calendario!=null){

            List<Contrato> contratoList = new ArrayList<>();
            if(calendario.getCargaAcademicaId()>0){
                contratoList.addAll(SQLite.select(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                        .from(Contrato.class)
                        .innerJoin(DetalleContratoAcad.class)
                        .on(Contrato_Table.idContrato.withTable()
                                .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                        .where(DetalleContratoAcad_Table.cargaAcademicaId.eq(calendario.getCargaAcademicaId()))
                        .groupBy(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                        .queryList());
            }else {
                contratoList.addAll(SQLite.select(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                        .from(Contrato.class)
                        .innerJoin(DetalleContratoAcad.class)
                        .on(Contrato_Table.idContrato.withTable()
                                .eq(DetalleContratoAcad_Table.idContrato.withTable()))
                        .where(DetalleContratoAcad_Table.cargaCursoId.eq(calendario.getCargaCursoId()))
                        .groupBy(Utils.f_allcolumnTable(Contrato_Table.ALL_COLUMN_PROPERTIES))
                        .queryList());
            }

            if (!evento.isEnvioPersonalizado()){

                for (Contrato contrato : contratoList){

                    Persona alumno = SQLite.select()
                            .from(Persona.class)
                            .where(Persona_Table.personaId.eq(contrato.getPersonaId()))
                            .querySingle();


                    PersonaUi alumnoUi = transformar(alumno);

                    if(calendario.getRolId()==5){
                        alumnoUi.setPadreSelected(true);
                    }else{
                        alumnoUi.setAlumnoSelected(true);
                    }
                    personaUiList.add(alumnoUi);
                }
            }else {

                List<Persona> eventoPersonaList = SQLite.select()
                        .from(Persona.class)
                        .innerJoin(EventoPersona.class)
                        .on(Persona_Table.personaId.withTable()
                                .eq(EventoPersona_Table.personaId.withTable()))
                        .where(EventoPersona_Table.eventoId.eq(idEvento))
                        .and(EventoPersona_Table.estado.eq(true))
                        .queryList();
                for (Contrato contrato : contratoList){

                    Persona alumno = SQLite.select()
                            .from(Persona.class)
                            .where(Persona_Table.personaId.eq(contrato.getPersonaId()))
                            .querySingle();


                    PersonaUi alumnoUi = transformar(alumno);

                    boolean alumnoSelected = false;
                    for (Persona persona : eventoPersonaList){
                      if(persona.getPersonaId() == contrato.getPersonaId()){
                          alumnoSelected = true;
                          break;
                      }
                    }

                    boolean padreSelected = false;
                    for (Persona persona : eventoPersonaList){
                        if(persona.getPersonaId() == contrato.getApoderadoId()){
                            padreSelected = true;
                            break;
                        }
                    }

                    alumnoUi.setAlumnoSelected(alumnoSelected);
                    alumnoUi.setPadreSelected(padreSelected);
                    personaUiList.add(alumnoUi);
                }


            }
        }

        return personaUiList;
    }


    private PersonaUi transformar(Persona persona){
        PersonaUi personaUi = new PersonaUi();
        if(persona!=null){
            personaUi.setId(persona.getPersonaId());
            String nombre = Utils.capitalize(persona.getFirstName()) + " " +  Utils.capitalize(persona.getApellidoPaterno()) + " " +   Utils.capitalize(persona.getApellidoMaterno());
            personaUi.setNombre(nombre);
            personaUi.setImagen(persona.getFoto());
        }else {
            personaUi.setNombre("Desconocido");
        }
        return personaUi;
    }


}
