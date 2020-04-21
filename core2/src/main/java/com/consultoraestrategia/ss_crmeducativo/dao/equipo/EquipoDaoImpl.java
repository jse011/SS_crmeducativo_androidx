package com.consultoraestrategia.ss_crmeducativo.dao.equipo;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.integrandeDeEquipo.IntegranteDeEquipoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

/**
 * Created by @stevecampos on 24/04/2018.
 */

public class EquipoDaoImpl extends BaseDaoImpl<EquipoC, EquipoC_Table> implements EquipoDao {

    private static final String TAG = EquipoDaoImpl.class.getSimpleName();
    private static EquipoDao mInstance;
    private IntegranteDeEquipoDao integranteDeEquipoDao;

    public EquipoDaoImpl(IntegranteDeEquipoDao integranteDeEquipoDao) {
        this.integranteDeEquipoDao = integranteDeEquipoDao;
    }

    public static EquipoDao getInstance(IntegranteDeEquipoDao integranteDeEquipoDao) {
        if (mInstance == null) {
            mInstance = new EquipoDaoImpl(integranteDeEquipoDao);
        }
        return mInstance;
    }

    @Override
    protected Class<EquipoC> getEntityClass() {
        return EquipoC.class;
    }

    @Override
    protected Class<EquipoC_Table> getTableclass() {
        return EquipoC_Table.class;
    }

    @Override
    public List<EquipoC> getEquiposDeGrupo(String grupo) {
        return getListWithQuery(
                EquipoC_Table.grupoEquipoId.is(grupo),
                EquipoC_Table.estado.in(EquipoC.CREADO,EquipoC.ACTUALIZADO)
        );
    }

    @Override
    public List<EquipoC> getEquiposConIntegrantes(String grupoId) {
        List<EquipoC> equipos = getEquiposDeGrupo(grupoId);
        for (EquipoC equipo :
                equipos) {
            String equipoId = equipo.getKey();
            List<EquipoIntegranteC> integrantes = integranteDeEquipoDao.getIntegrantesDeEquipoConSuPersona(equipoId);
            //Log.d(TAG, "integrantes name: " + equipo.getNombre());
            //Log.d(TAG, "integrantes size: " + integrantes.size());
            equipo.setIntegrantes(integrantes);
        }
        return equipos;
    }

    @Override
    public EquipoC getEquipoConIntegrantes(String equipoId) {
        EquipoC equipo = get(equipoId);
        if(equipo!=null) equipo.setIntegrantes(integranteDeEquipoDao.getIntegrantesDeEquipo(equipoId));
        return equipo;
    }

    @Override
    public EquipoC getEquipoConIntegrantesConSuPersona(String equipoId) {
        EquipoC equipo = get(equipoId);
        equipo.setIntegrantes(integranteDeEquipoDao.getIntegrantesDeEquipoConSuPersona(equipoId));
        return equipo;
    }

    @Override
    public void deleteEquipoConIntegrantes(final EquipoC equipo, final Callback<Boolean> callback) {
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                equipo.setEstado(EquipoC.ELIMINADO);
                equipo.setSyncFlag(BaseEntity.FLAG_UPDATED);
                equipo.save();

                /*
                List<EquipoIntegranteC> integrantes = equipo.getIntegrantes();
                for (EquipoIntegranteC integrante :
                        integrantes) {
                    boolean succes = integrante.delete();
                    if (!succes) {
                        throw new Error("Error eliminando integrante!");
                    }
                }*/
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                Log.d(TAG, "deleteTeam Transaction success: " + true);
                callback.onSuccess(true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                Log.d(TAG, "deleteTeam Transaction error: " + error);
                callback.onError(error);
            }
        })
                .build();
        transaction.execute();
    }

    @Override
    public void deleteEquipoConIntegrantes(final List<EquipoC> equipos, final Callback<Boolean> callback) {
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                for (EquipoC equipoC : equipos){
                    equipoC.setEstado(EquipoC.ESTADO_ELIMINADO);
                    boolean success = equipoC.update();
                    if (!success) {
                        throw new Error("Error Deleting equipo!");
                    }

                    List<EquipoIntegranteC> integrantes = equipoC.getIntegrantes();
                    for (EquipoIntegranteC integrante :
                            integrantes) {
                        boolean succes = integrante.delete();
                        if (!succes) {
                            throw new Error("Error eliminando integrante!");
                        }
                    }
                }


            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                Log.d(TAG, "deleteTeam Transaction success: " + true);
                callback.onSuccess(true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                Log.d(TAG, "deleteTeam Transaction error: " + error);
                callback.onError(error);
            }
        })
                .build();
        transaction.execute();
    }

    @Override
    public void createEquipoConIntegrantes(final EquipoC equipo, final Callback<Boolean> callback) {
        Log.d(TAG, "createEquipoConIntegrantes: ");
        final DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        final Transaction transaction = database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                boolean success = equipo.save();
                if (!success) {
                    throw new Error("Error guardando equipo!!!");
                }

                integranteDeEquipoDao.deleteIntegrantesDeEquipo(equipo.getKey());


                List<EquipoIntegranteC> members = equipo.getIntegrantes();
                for (EquipoIntegranteC integrante :
                        members) {
                    //Guardar los integrantes que pertenecen al equipo
                    boolean integrantesaved = integrante.save();
                    if (!integrantesaved) {
                        throw new Error("Error guardando equipo!!!");
                    }
                }
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                Log.d(TAG, "createTeam transaction success: " + true);
                callback.onSuccess(true);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                Log.d(TAG, "createTeam transaction error: " + error);
                callback.onError(error);
            }
        })
                .build();
        transaction.execute(); // execute
    }
}
