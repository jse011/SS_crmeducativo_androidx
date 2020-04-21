package com.consultoraestrategia.ss_crmeducativo.dao.sessionUser;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;

/**
 * Created by Jse on 27/12/2018.
 */

public interface SessionUserDao extends BaseIntegerDao<SessionUser> {
    SessionUser getCurrentUser();
    SessionUser loginUser(String parameterUserName, String parameterPassword);
    SessionUser loginUserSimple(String parameterUserName, String parameterPassword);
    boolean guardarUsuario(int usuarioId, int personaId, String usuario, String password);
    boolean changeFechaServidor(long fechaServidor);
}
