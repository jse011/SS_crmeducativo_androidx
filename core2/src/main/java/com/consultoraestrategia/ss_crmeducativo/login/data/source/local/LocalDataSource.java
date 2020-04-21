package com.consultoraestrategia.ss_crmeducativo.login.data.source.local;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AdminService;
import com.consultoraestrategia.ss_crmeducativo.entities.Entidad;
import com.consultoraestrategia.ss_crmeducativo.entities.Georeferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.PersonaGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.entities.Rol;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.Usuario;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioAcceso;
import com.consultoraestrategia.ss_crmeducativo.entities.UsuarioRolGeoreferencia;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.ImportCallback;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.callbacks.LoginCallback;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.TransaccionUtils;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class LocalDataSource implements LoginDataSource {

    private static final String TAG = LocalDataSource.class.getSimpleName();
    private final SessionUserDao sessionUserDao;


    public LocalDataSource(SessionUserDao sessionUserDao) {
        this.sessionUserDao = sessionUserDao;
    }


    @Override
    public void loginUser(String parameterUserName, String parameterPassword, LoginCallback callback) {
        Log.d(TAG, "loginUser");
        callback.onSuccess(sessionUserDao.loginUser(parameterUserName, parameterPassword));
    }

    @Override
    public void importData(int idUsuario, ImportCallback callback) {

    }

    @Override
    public void loginUserSimple(String parameterUserName, String parameterPassword, LoginCallback callback) {
        callback.onSuccess(sessionUserDao.loginUserSimple(parameterUserName, parameterPassword));

    }

    @Override
    public void obtenerPersonaUsuario(String parameterUserName, Callback<PersonaUi> callback) {

    }

    @Override
    public void loginUserSimple(int usuarioExternoId, Callback<Usuario> callback) {

    }

    @Override
    public void loginUserSimple(String usuario, String password, Callback<Usuario> callback) {

    }

    @Override
    public void guardarUsuario(final Usuario usuario, final Callback<Usuario> callback) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        appDatabase.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {

                SQLite.delete()
                        .from(Entidad.class)
                        .execute();
                SQLite.delete()
                        .from(Georeferencia.class)
                        .execute();
                SQLite.delete()
                        .from(Rol.class)
                        .execute();
                SQLite.delete()
                        .from(UsuarioRolGeoreferencia.class)
                        .execute();
                SQLite.delete()
                        .from(PersonaGeoreferencia.class)
                        .execute();
                SQLite.delete()
                        .from(UsuarioAcceso.class)
                        .execute();
                SQLite.delete()
                        .from(SessionUser.class)
                        .execute();



                TransaccionUtils.fastStoreListSave(Entidad.class, usuario.getEntidades(), databaseWrapper, true);
                TransaccionUtils.fastStoreListSave(Georeferencia.class, usuario.getGeoreferencias(),databaseWrapper, true);
                TransaccionUtils.fastStoreListSave(Rol.class, usuario.getRoles(),databaseWrapper, true);
                TransaccionUtils.fastStoreListSave(UsuarioRolGeoreferencia.class, usuario.getUsuarioRolGeoreferencias(),databaseWrapper,true);
                TransaccionUtils.fastStoreListSave(PersonaGeoreferencia.class, usuario.getPersonaGeoreferencias(),databaseWrapper,true);
                TransaccionUtils.fastStoreListSave(UsuarioAcceso.class, usuario.getAccesos(),databaseWrapper,false);
                sessionUserDao.guardarUsuario(usuario.getUsuarioId(),usuario.getPersonaId(),usuario.getUsuario(), usuario.getPassword());
            }

        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(@NonNull Transaction transaction) {
                callback.onLoad(true, usuario);
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                callback.onLoad(false, usuario);
            }
        }).build().execute();
    }

    @Override
    public void  recuperarPassword(int usuario, Callback<String> callback) {

    }

    @Override
    public void obtenerAdminService(int opcion, String usuario, String passwordd, String correo, String numeroDocumento, String urlServidor, boolean states,Callback<AdminService> callback) {

    }

    public boolean saveSessionUser(SessionUser user) {
        return user.save();
    }

    private <T extends BaseModel> void fastStoreList(Class<T> clazz, List<T> list) {
        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        if (list != null && !list.isEmpty()) {
            FastStoreModelTransaction fastStoreModelTransaction = FastStoreModelTransaction
                    .insertBuilder(FlowManager.getModelAdapter(clazz))
                    .addAll(list)
                    .build();
            database.beginTransactionAsync(fastStoreModelTransaction).build().execute();
        }
    }
}
