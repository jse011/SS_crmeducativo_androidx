package com.consultoraestrategia.ss_crmeducativo.calendarioPeriodo;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.AnioAcademico_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioAcademico;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodoDetalle;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.WebConfig;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosAnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import retrofit2.Call;

public class CalendarioPeridoService {
    private static CalendarioPeridoService mInstance;
    private ApiRetrofit apiRetrofit;
    private final static String TAG = CalendarioPeridoService.class.getSimpleName();
    private boolean executed = false;


    private CalendarioPeridoService() {
        this.apiRetrofit = ApiRetrofit.getInstance();
    }

    public static CalendarioPeridoService getInstance() {
        if (mInstance == null) {
            mInstance = new CalendarioPeridoService();
        }
        return mInstance;
    }

    public void execute(){
        AnioAcademico anioAcademico = SQLite.select()
                .from(AnioAcademico.class)
                .where(AnioAcademico_Table.toogle.eq(true))
                .querySingle();
        if(anioAcademico==null)return;

        Empleado empleado = SQLite.select(Empleado_Table.empleadoId.withTable())
                .from(Empleado.class)
                .innerJoin(SessionUser.class)
                .on(Empleado_Table.personaId.withTable()
                        .eq(SessionUser_Table.personaId.withTable()))
                .querySingle();

         if(empleado==null)return;


         if(executed){
             Log.d(TAG,"is execute CalendarioPeridoService");
             return;
         }

        Log.d(TAG,"run CalendarioPeridoService");

        executed = true;

        Call<RestApiResponse<BEDatosAnioAcademico>> responseCall = apiRetrofit.flst_getDatosCalendarioPeriodo(anioAcademico.getIdAnioAcademico(), empleado.getEmpleadoId());

        RetrofitCancel<BEDatosAnioAcademico> retrofitCancel = new RetrofitCancelImpl<>(responseCall);

        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDatosAnioAcademico>() {
            @Override
            public void onResponse(final BEDatosAnioAcademico response) {
                if(response == null){
                    Log.d(TAG,"response calendarioPeriodo null");
                    executed = false;
                }else {
                    Log.d(TAG,"response calendarioPeriodo true");

                    DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
                    Transaction transaction = database.beginTransactionAsync(new ITransaction() {
                        @Override
                        public void execute(DatabaseWrapper databaseWrapper) {
                            TransaccionUtils.fastStoreListInsert(CalendarioPeriodo.class, response.getCalendarioPeriodos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CalendarioPeriodoDetalle.class, response.getCalendarioPeriodoDetalles(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CargaCursoCalendarioPeriodo.class, response.getCargaCursoCalendarioPeriodo(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(Tipos.class, response.getTipos(), databaseWrapper, true);
                            TransaccionUtils.fastStoreListInsert(CalendarioAcademico.class, response.getCalendarioAcademico(), databaseWrapper, true);


                            TransaccionUtils.fastStoreListInsert(WebConfig.class, response.getWebConfigs(), databaseWrapper, true);

                            //

                        }
                    }).success(new Transaction.Success() {
                        @Override
                        public void onSuccess(@NonNull Transaction transaction) {
                            Log.d(TAG,"response calendarioPeriodo Transaction Success");
                            executed = false;
                        }
                    }).error(new Transaction.Error() {
                        @Override
                        public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                            error.printStackTrace();
                            Log.d(TAG,"response calendarioPeriodo Transaction Error");
                            executed = false;
                        }
                    }).build();

                    transaction.execute();


                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                executed = false;
                Log.d(TAG,"response calendarioPeriodo Transaction Failure");
            }
        });
    }
}
