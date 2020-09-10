package com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.remoto;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AgendaUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoEventoUi;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoPersona;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEEventos;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CrearEventoRemoteDataSource implements CrearEventoDataSource {
    private static final int EVENTO=526, ACTIVIDAD=528, CITA=530, TAREA=529, NOTICIA=527;
    private UtilServidor utilServidor;
    private String TAG = "CrearEventoRemoteDataSource";

    public CrearEventoRemoteDataSource(UtilServidor utilServidor) {
        this.utilServidor = utilServidor;
    }

    @Override
    public List<AgendaUi> getListAgenda(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId) {
        return null;
    }

    @Override
    public List<AlumnoUi> getAlumnosCargaAcademica(int cargaAcademicaId, int empleadoId, String enventoId) {
        return null;
    }

    @Override
    public String getNombreCargaAcademica(int cargaAcademicaId) {
        return null;
    }

    @Override
    public String getNombreCargaCurso(int cargaCursoId) {
        return null;
    }

    @Override
    public List<AlumnoUi> getAlumnosCargaCurso(int cargaCursoId, int empleadoId, String enventoId) {
        return null;
    }

    @Override
    public EventoUi getEventoDocente(String eventoId) {
        return null;
    }

    @Override
    public List<TipoCalendarioUi> getListTipoCalendario(int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId) {
        return null;
    }

    @Override
    public List<TipoEventoUi> getTipoEventos() {
        return null;
    }

    @Override
    public RetrofitCancel saveEvento(EventoUi eventoUi, boolean publicar, final Callback callback) {

        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        apiRetrofit.changeSetTime(10, 15, 15, TimeUnit.SECONDS);

        Evento evento = SQLite.select()
                .from(Evento.class)
                .where(Evento_Table.eventoId.eq(eventoUi.getId()))
                .querySingle();

        if(evento==null){
            evento = new Evento();
            evento.setEventoId(IdGenerator.generateId());
            evento.setKey(evento.getEventoId());
            evento.setEstadoId(Evento.ESTADO_CREADO);
            evento.setSyncFlag(BaseEntity.FLAG_ADDED );
        }else {
            String fileName = evento.getPathImagen();
            if(!TextUtils.isEmpty(fileName)){
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                String file = fileName.substring(p + 1);
                evento.setPathImagen(file);
            }
            evento.setEstadoId(Evento.ESTADO_ACTUALIZADO);
            evento.setSyncFlag(BaseEntity.FLAG_UPDATED );

        }
        //#endregion

        if(publicar){
            evento.setEstadoPublicacion(publicar);
        }

        evento.setTitulo(eventoUi.getNombre());
        evento.setDescripcion(eventoUi.getDescripcion());
        //evento.setCalendarioId(eventoUi.getCalendarioId());
        //evento.setTipoEventoId(eventoUi.getTipoEventoId());
        evento.setFechaEvento(eventoUi.getFecha());
        evento.setHoraEvento(eventoUi.getHora());
        evento.setGeoreferenciaId(eventoUi.getGeoreferencia());
        evento.setEntidadId(eventoUi.getEntidadId());


        if(!TextUtils.isEmpty(eventoUi.getFoto())){
            evento.setNewPathImagen(evento.getEventoId()+"_Cod_"+new Date().getTime() +".jpg");
            evento.setImage64(eventoUi.getFoto());
        }else {
            evento.setNewPathImagen("");
            evento.setImage64(null);
        }

        Calendario calendario = new Calendario();
        calendario.setCargaCursoId(eventoUi.getCargaCursoId());
        calendario.setCargaAcademicaId(eventoUi.getCargaAcademicaId());
        calendario.setGeoreferenciaId(evento.getGeoreferenciaId());
        calendario.setEntidadId(evento.getEntidadId());

        SessionUser sessionUser = SessionUser.getCurrentUser();
        Persona persona = SQLite.select()
                .from(Persona.class)
                .where(Persona_Table.personaId.eq(sessionUser!=null?sessionUser.getUserId():0))
                .querySingle();
        String nombreDocente = "";
        if(persona!=null){
            nombreDocente = persona.getNombres() +" " + persona.getApellidoPaterno() + " " + persona.getApellidoMaterno();
        }
        calendario.setnUsuario(nombreDocente);
        List<EventoPersona>  eventoPersonaList = new ArrayList<>();

        int countEnvioPadres = 0;
        int countEnvioAlumnos = 0;
        int countEnvioAmbos = 0;
        for (AlumnoUi alumnoUi : eventoUi.getListEnvio()){
            if(alumnoUi.isEnviarAlumno() ||alumnoUi.isEnviarPadre()){
                EventoPersona eventoUsuario = new EventoPersona();
                eventoUsuario.setPersonaId(alumnoUi.getId());
                eventoUsuario.setEventoId(evento.getKey());
                if(alumnoUi.isEnviarAlumno() &&alumnoUi.isEnviarPadre()){
                    eventoUsuario.setRolId(0);//se envia ambos
                    countEnvioAmbos ++;
                }else if(alumnoUi.isEnviarAlumno()){
                    eventoUsuario.setRolId(6);
                    countEnvioAlumnos ++;
                }else {
                    eventoUsuario.setRolId(5);
                    countEnvioPadres ++;
                }
                eventoUsuario.setApoderadoId(alumnoUi.getApoderadoId());
                eventoPersonaList.add(eventoUsuario);
            }
        }

        if(countEnvioAmbos > 0){
            calendario.setRolId(0);
            calendario.setNombre(eventoUi.getNombreCalendario());
            evento.setEnvioPersonalizado(true);
        }else if(countEnvioAlumnos>0){
            calendario.setRolId(6);
            calendario.setNombre(Utils.capitalize(eventoUi.getNombreCalendario())+ " - Alumnos");
            evento.setEnvioPersonalizado(countEnvioAlumnos!=eventoUi.getListEnvio().size());
        }else {
            calendario.setRolId(5);
            calendario.setNombre(Utils.capitalize(eventoUi.getNombreCalendario())+ " - Padres");
            evento.setEnvioPersonalizado(countEnvioPadres!=eventoUi.getListEnvio().size());
        }

        if(!evento.isEnvioPersonalizado()){
            eventoPersonaList.clear();
        }


        RetrofitCancel<BEEventos> retrofitCancel = new RetrofitCancelImpl<BEEventos>(apiRetrofit.flst_saveEvento2(evento, calendario, eventoPersonaList));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEEventos>() {
            @Override
            public void onResponse(final BEEventos response) {
                if(response==null){
                    callback.load(false);
                    Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: null");
                }else {
                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            String eventoId = response.getEvento()!=null&&!response.getEvento().isEmpty()?response.getEvento().get(0).getEventoId():"";
                            List<Evento> eventoList = SQLite.select()
                                    .from(Evento.class)
                                    .where(Evento_Table.eventoId.eq(eventoId))
                                    .queryList();
                            for (Evento evento : response.getEvento()!=null?response.getEvento():new ArrayList<Evento>()){
                                for (Evento eventoOld: eventoList){
                                    if(evento.getEventoId().equals(eventoOld.getEventoId())){
                                        evento.setLikeCount(eventoOld.getLikeCount());
                                        evento.setLike(eventoOld.isLike());
                                    }
                                }
                            }

                            TransaccionUtils.fastStoreListInsert(Calendario.class, response.getCalendario(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Evento.class, response.getEvento(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Persona.class, response.getPersona(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Relaciones.class, response.getRelaciones(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EventoPersona.class, response.getEventoPersona(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callback.load(true);
                            Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: true");
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            callback.load(false);
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                        }
                    }).build();

                    transaction.execute();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: error");
                callback.load(false);
            }
        });
        return retrofitCancel;
    }
}
