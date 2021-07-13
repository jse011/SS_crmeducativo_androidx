package com.consultoraestrategia.ss_crmeducativo.eventos.data.source.remote;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Calendario;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoAdjunto;
import com.consultoraestrategia.ss_crmeducativo.entities.EventoPersona;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuario;
import com.consultoraestrategia.ss_crmeducativo.entities.ListaUsuarioDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.Relaciones;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosDataSource;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEEventos;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EventoRemoteDataSource implements EventosDataSource {
    private static final String TAG = EventoRemoteDataSource.class.getSimpleName();
    private UtilServidor utilServidor;
    private FirebaseDatabase firebaseDatabase;

    public EventoRemoteDataSource(UtilServidor utilServidor, FirebaseDatabase firebaseDatabase) {
        this.utilServidor = utilServidor;
        this.firebaseDatabase = firebaseDatabase;
    }

    @Override
    public boolean saveLike(EventosUi eventosUi) {
        Log.d(TAG, "saveLike ");
        EventosUi eventosUiCopy = new EventosUi();
        eventosUiCopy.setIdEvento(eventosUi.getIdEvento());
        eventosUiCopy.setLike(eventosUi.getLike());
        getLikesSaveCountLike(eventosUiCopy, new SucessCallback<EventosUi>() {
            @Override
            public void onLoad(boolean success, EventosUi item) {
                if(success){
                    int cantidad = item.getLikeCount();
                    if(item.getLike()){
                        cantidad++;
                    }else {
                        cantidad--;
                    }

                    Log.d(TAG, "cantidad " + cantidad);
                    firebaseDatabase.getReference()
                            .child("padre_mentor")
                            .child("icrmedu_padre")
                            .child("like")
                            .child(item.getIdEvento())
                            .setValue(String.valueOf(cantidad));

                }
            }
        });

        return true;
    }

    @Override
    public List<TiposEventosUi> getTipoEvento() {
        return null;
    }

    @Override
    public void getLikesSaveCountLike(final EventosUi request, final SucessCallback<EventosUi> callback) {
        Log.d(TAG, "getLikesSaveCountLike " + request.getTitulo());
        DatabaseReference reference = firebaseDatabase.getReference()
                .child("padre_mentor")
                .child("icrmedu_padre")
                .child("like")
                .child(request.getIdEvento());

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cantidad = dataSnapshot.getValue(String.class);
                int resulCantidad = cantidad==null ? 0: Integer.valueOf(cantidad);
                if(resulCantidad < 0)resulCantidad = 0;
                request.setLikeCount(resulCantidad);
                Log.d(TAG, "resulCantidad " +resulCantidad);
                callback.onLoad(true,request);
                Log.d(TAG, "onDataChange ");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled ");
                Log.d(TAG, "Error trying to get classified ad for update " +
                        ""+databaseError);
                callback.onLoad(false,request );
            }
        });
    }

    @Override
    public RetrofitCancel<BEEventos> sincronizarEventos(int idUsuario, int idGeoreferenciaId, final SucessCallback<List<Object>> listSucessCallback) {
        RetrofitCancel<BEEventos> retrofitCancel = null;

        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        apiRetrofit.changeSetTime(10, 15, 15, TimeUnit.SECONDS);

        retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.flst_ObtenerCalendarioEventoDocente(idUsuario, idGeoreferenciaId));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEEventos>() {
            @Override
            public void onResponse(final BEEventos response) {
                if(response==null){
                    listSucessCallback.onLoad(false,null);
                    Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: null");
                }else {


                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            List<Evento> eventoList = SQLite.select()
                                    .from(Evento.class)
                                    .queryList();

                            for (Evento evento : response.getEvento()!=null?response.getEvento():new ArrayList<Evento>()){
                                for (Evento eventoOld: eventoList){
                                    if(evento.getEventoId().equals(eventoOld.getEventoId())){
                                        evento.setLikeCount(eventoOld.getLikeCount());
                                        evento.setLike(eventoOld.isLike());
                                    }
                                }
                            }

                            TransaccionUtils.deleteTable(Calendario.class);
                            TransaccionUtils.deleteTable(Evento.class);
                            TransaccionUtils.deleteTable(CalendarioListaUsuario.class);
                            TransaccionUtils.deleteTable(ListaUsuario.class);
                            TransaccionUtils.deleteTable(ListaUsuarioDetalle.class);
                            TransaccionUtils.deleteTable(EventoPersona.class);
                            TransaccionUtils.deleteTable(EventoAdjunto.class);
                            SQLite.delete()
                                    .from(Tipos.class)
                                    .where(Tipos_Table.objeto.eq("T_CE_MOV_EVENTOS"))
                                    .and(Tipos_Table.concepto.eq("TipoEvento"))
                                    .execute();


                            TransaccionUtils.fastStoreListInsert(Calendario.class, response.getCalendario(), databaseWrapper, true);

                            TransaccionUtils.fastStoreListInsert(Evento.class, response.getEvento(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CalendarioListaUsuario.class, response.getCalendarioListaUsuario(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ListaUsuario.class, response.getListaUsuarios(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(ListaUsuarioDetalle.class, response.getListUsuarioDetalle(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Persona.class, response.getPersona(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Usuario.class, response.getUsuario(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Relaciones.class, response.getRelaciones(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EventoPersona.class, response.getEventoPersona(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(EventoAdjunto.class, response.getEventoAdjuntos(), databaseWrapper, true);

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            listSucessCallback.onLoad(true, null);
                            Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: true");
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            listSucessCallback.onLoad(false,null);
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
                listSucessCallback.onLoad(false,null);
            }
        });
        return retrofitCancel;
    }

    @Override
    public void getEventosColegio(int idUsuario, int idGeoreferencia, TiposEventosUi tiposEventosUi, SucessCallback<List<EventosUi>> listSucessCallback) {

    }

    @Override
    public RetrofitCancel deleteEvento(final EventosUi eventoUi, final SucessCallback<Boolean> callback) {
        RetrofitCancel<String> retrofitCancel = null;

        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        apiRetrofit.changeSetTime(10, 15, 15, TimeUnit.SECONDS);

      Evento evento = SQLite.select()
               .from(Evento.class)
               .where(Evento_Table.eventoId.eq(eventoUi.getIdEvento()))
               .querySingle();

       if(evento==null){
           callback.onLoad(false, false);
           return null;
       }

        String fileName = evento.getPathImagen();
        if(!TextUtils.isEmpty(fileName)){
            int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
            String file = fileName.substring(p + 1);
            evento.setPathImagen(file);
        }

        evento.setSyncFlag(BaseEntity.FLAG_UPDATED);
        evento.setEstadoId(Evento.ESTADO_ELIMINADO);
        evento.setEstadoPublicacion(false);

        retrofitCancel = new RetrofitCancelImpl<String>(apiRetrofit.flst_saveEvento(evento));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<String>() {
            @Override
            public void onResponse(final String response) {
                if(response==null){
                    callback.onLoad(false, false);
                    Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: null");
                }else {


                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {

                            Evento evento = SQLite.select()
                                    .from(Evento.class)
                                    .where(Evento_Table.eventoId.eq(eventoUi.getIdEvento()))
                                    .querySingle();
                            if(evento!=null) {
                                evento.setSyncFlag(BaseEntity.FLAG_UPDATED);
                                evento.setEstadoId(Evento.ESTADO_ELIMINADO);
                                evento.setEstadoPublicacion(false);
                                evento.save(databaseWrapper);
                            }

                            Log.d(TAG, "onResponse Url: "+response);
                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            callback.onLoad(true, true);
                            Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: true");
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            callback.onLoad(false, false);
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
                callback.onLoad(false, false);
            }
        });
        return retrofitCancel;
    }

    @Override
    public RetrofitCancel enviarEvento(final EventosUi eventoUi, final SucessCallback<Boolean> callback) {
        RetrofitCancel<String> retrofitCancel = null;

        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        apiRetrofit.changeSetTime(10, 15, 15, TimeUnit.SECONDS);

        final Evento evento = SQLite.select()
                .from(Evento.class)
                .where(Evento_Table.eventoId.eq(eventoUi.getIdEvento()))
                .querySingle();

        if(evento==null){
            callback.onLoad(false, false);
            return null;
        }

        String fileName = evento.getPathImagen();
        if(!TextUtils.isEmpty(fileName)){
            int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
            String file = fileName.substring(p + 1);
            evento.setPathImagen(file);
        }

        evento.setSyncFlag(BaseEntity.FLAG_UPDATED);
        evento.setEstadoId(Evento.ESTADO_ACTUALIZADO);
        evento.setEstadoPublicacion(eventoUi.getPublicado());

        retrofitCancel = new RetrofitCancelImpl<String>(apiRetrofit.flst_saveEvento(evento));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<String>() {
            @Override
            public void onResponse(final String response) {
                if(response==null){
                    callback.onLoad(false, false);
                    eventoUi.setLoading(false);
                    Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: null");
                }else {
                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            Evento evento = SQLite.select()
                                    .from(Evento.class)
                                    .where(Evento_Table.eventoId.eq(eventoUi.getIdEvento()))
                                    .querySingle();
                            if(evento!=null) {
                                evento.setSyncFlag(BaseEntity.FLAG_UPDATED);
                                evento.setEstadoId(Evento.ESTADO_ACTUALIZADO);
                                evento.setEstadoPublicacion(eventoUi.getPublicado());
                                evento.save(databaseWrapper);
                            }
                            Log.d(TAG, "onResponse Url: "+response);
                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            eventoUi.setPublicado(eventoUi.getPublicado());
                            eventoUi.setLoading(false);
                            callback.onLoad(true, true);

                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            eventoUi.setPublicado(!eventoUi.getPublicado());
                            eventoUi.setLoading(false);
                            callback.onLoad(false, false);
                            error.printStackTrace();
                            Log.d(TAG,"response save false");
                        }
                    }).build();

                    transaction.execute();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                eventoUi.setPublicado(!eventoUi.getPublicado());
                eventoUi.setLoading(false);
                t.printStackTrace();
                Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: error");
                callback.onLoad(false, false);
            }
        });
        return retrofitCancel;
    }

    @Override
    public List<Object> getListaUsuarios(String calendarioId, String idEvento) {
        return null;
    }

}
