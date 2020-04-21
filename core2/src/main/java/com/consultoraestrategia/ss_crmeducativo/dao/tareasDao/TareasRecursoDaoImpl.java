package com.consultoraestrategia.ss_crmeducativo.dao.tareasDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC_Table;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class TareasRecursoDaoImpl extends BaseDaoImpl<TareasRecursosC, TareasRecursosC_Table> implements TareasRecursoDao {

    private static TareasRecursoDao mInstance;

    private TareasRecursoDaoImpl() {
    }


    public static TareasRecursoDao getInstance() {
        if (mInstance == null) {
            mInstance = new TareasRecursoDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<TareasRecursosC> getEntityClass() {
        return TareasRecursosC.class;
    }

    @Override
    protected Class<TareasRecursosC_Table> getTableclass() {
        return TareasRecursosC_Table.class;
    }

}
