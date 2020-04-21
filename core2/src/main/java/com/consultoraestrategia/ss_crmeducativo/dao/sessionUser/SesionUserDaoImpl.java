package com.consultoraestrategia.ss_crmeducativo.dao.sessionUser;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser_Table;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.language.property.Property;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.Date;

/**
 * Created by Jse on 27/12/2018.
 */

public class SesionUserDaoImpl extends BaseIntegerDaoImpl<SessionUser, SessionUser_Table> implements SessionUserDao {


    private static SesionUserDaoImpl mInstance;

    private SesionUserDaoImpl() {
    }

    public static SesionUserDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new SesionUserDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return SessionUser_Table.userId;
    }

    @Override
    protected Class<SessionUser> getEntityClass() {
        return SessionUser.class;
    }

    @Override
    protected Class<SessionUser_Table> getTableclass() {
        return SessionUser_Table.class;
    }

    @Override
    public SessionUser getCurrentUser() {
        SessionUser sessionUser = null;
        try {
            sessionUser = SQLite.select()
                    .from(SessionUser.class)
                    .where(SessionUser_Table.state.is(SessionUser.STATE_ACTIVE))
                    .querySingle();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sessionUser;
    }
    @Override
    public SessionUser loginUser(String parameterUserName, String parameterPassword) {
        return SQLite.select()
                .from(SessionUser.class)
                .where(SessionUser_Table.username.is(parameterUserName))
                .and(SessionUser_Table.passwordEncrypted.is(parameterPassword))
                .querySingle();
    }

    @Override
    public SessionUser loginUserSimple(String parameterUserName, String parameterPassword) {
        SessionUser sessionUser = SQLite.select()
                .from(SessionUser.class)
                .where(SessionUser_Table.username.is(parameterUserName))
                .and(SessionUser_Table.passwordEncrypted.is(parameterPassword))
                .querySingle();
        if(sessionUser != null){
            sessionUser.setTimestampLogin(new Date().getTime());
            sessionUser.setTimestampLastSeen(new Date().getTime());
            sessionUser.setState(SessionUser.STATE_ACTIVE);
            sessionUser.save();
        }
        return sessionUser;

    }

    @Override
    public boolean guardarUsuario(int usuarioId, int personaId, String usuario, String password) {
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            SQLite.update(SessionUser.class)
                    .set(SessionUser_Table.state.eq(SessionUser.STATE_INACTIVE))
                    .where(SessionUser_Table.userId.notEq(usuarioId))
                    .execute();

            SessionUser sessionUser = new SessionUser();
            sessionUser.setUserId(usuarioId);
            sessionUser.setPersonaId(personaId);
            sessionUser.setUsername(usuario);
            sessionUser.setPasswordEncrypted(password);//FIX ME!!!
            sessionUser.setName("");//FIX ME!!!
            sessionUser.setUrlPicture("");//FIX ME!!!F
            sessionUser.setTimestampLogin(new Date().getTime());
            sessionUser.setTimestampLastSeen(new Date().getTime());
            sessionUser.setState(SessionUser.STATE_ACTIVE);
            sessionUser.save();

            databaseWrapper.setTransactionSuccessful();
            databaseWrapper.endTransaction();
            return true;
        } catch (Exception e){
            databaseWrapper.endTransaction();
            return false;
        }
    }

    @Override
    public boolean changeFechaServidor(long fechaServidor) {
        SessionUser sessionUser = getCurrentUser();
        sessionUser.setFechaServidor(fechaServidor);
        Log.d(SessionUser.class.getSimpleName(), "fechaServidor: " + fechaServidor);
        return sessionUser.save();
    }
}
