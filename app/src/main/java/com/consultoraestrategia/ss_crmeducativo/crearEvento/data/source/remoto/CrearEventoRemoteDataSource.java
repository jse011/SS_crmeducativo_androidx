package com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.remoto;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoEventoUi;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento;
import com.consultoraestrategia.ss_crmeducativo.entities.Evento_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

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
    public RetrofitCancel saveEvento(EventoUi eventoUi, final Callback callback) {

        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        apiRetrofit.changeSetTime(10, 15, 15, TimeUnit.SECONDS);

        final Evento evento= new Evento();
        //#region recuperar la foto, el usuario creacion y fecha de creación
        if(!TextUtils.isEmpty(eventoUi.getId())){
            Evento eventoEdit = SQLite.select()
                    .from(Evento.class)
                    .where(Evento_Table.eventoId.eq(eventoUi.getId()))
                    .querySingle();

            if(eventoEdit!=null){
                evento.setFechaCreacion(eventoEdit.getFechaCreacion());
                evento.setUsuarioAccionId(eventoEdit.getUsuarioAccionId());
                evento.setPathImagen(eventoEdit.getPathImagen());
                evento.setEstadoPublicacion(eventoEdit.getEstadoPublicacion());
                evento.setLikeCount(eventoEdit.getLikeCount());
                evento.setLike(eventoEdit.isLike());
            }
        }
        //#endregion
        evento.setSyncFlag(TextUtils.isEmpty(eventoUi.getId())? BaseEntity.FLAG_ADDED :BaseEntity.FLAG_UPDATED);
        evento.setEventoId(TextUtils.isEmpty(eventoUi.getId())? IdGenerator.generateId() :eventoUi.getId());
        evento.setKey(evento.getEventoId());
        evento.setTitulo(eventoUi.getNombre());
        evento.setDescripcion(eventoUi.getDescripcion());
        evento.setCalendarioId(eventoUi.getCalendarioId());
        evento.setTipoEventoId(eventoUi.getTipoEventoId());

        evento.setFechaEvento(eventoUi.getFecha());

        evento.setHoraEvento(eventoUi.getHora());

        evento.setGeoreferenciaId(eventoUi.getEntidadId());
        evento.setEntidadId(eventoUi.getGeoreferencia());
        evento.setEstadoId(TextUtils.isEmpty(eventoUi.getId())?Evento.ESTADO_CREADO:Evento.ESTADO_ACTUALIZADO);

        String fileName = evento.getPathImagen();
        if(!TextUtils.isEmpty(fileName)){
            int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
            String file = fileName.substring(p + 1);
            evento.setPathImagen(file);
        }

        if(eventoUi.getTipoEventoId()==NOTICIA||
                eventoUi.getTipoEventoId()==EVENTO){

            if(!TextUtils.isEmpty(eventoUi.getFoto())){
                evento.setNewPathImagen(evento.getEventoId()+"_Cod_"+new Date().getTime() +".jpg");
                evento.setImage64(eventoUi.getFoto());
            }

        }else {
            evento.setNewPathImagen("");
            evento.setImage64(null);
        }


        RetrofitCancel<String> retrofitCancel = new RetrofitCancelImpl<String>(apiRetrofit.flst_saveEvento(evento));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<String>() {
            @Override
            public void onResponse(final String response) {
                if(response==null){
                    callback.load(false);
                    Log.d(TAG, "getDatosLogin table: "+TAG+" onResponse: null");
                }else {
                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            evento.setPathImagen(response);
                            evento.save(databaseWrapper);
                            Log.d(TAG, "onResponse Url: "+response);
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
