package com.consultoraestrategia.ss_crmeducativo.dao.silaboEventoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public class SilaboEventoDaoImpl extends BaseIntegerDaoImpl<SilaboEvento, SilaboEvento_Table> implements SilaboEventoDao {


    private static SilaboEventoDao mInstance;

    private SilaboEventoDaoImpl() {
    }

    public static SilaboEventoDao getInstance() {
        if (mInstance == null) {
            mInstance = new SilaboEventoDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return SilaboEvento_Table.silaboEventoId;
    }

    @Override
    protected Class<SilaboEvento> getEntityClass() {
        return SilaboEvento.class;
    }

    @Override
    protected Class<SilaboEvento_Table> getTableclass() {
        return SilaboEvento_Table.class;
    }

    @Override
    public SilaboEvento getByCargaCurso(int cargaCursoId) {
        return SQLite.select(Utils.f_allcolumnTable(SilaboEvento_Table.ALL_COLUMN_PROPERTIES))
                .from(SilaboEvento.class)
                .where(SilaboEvento_Table.cargaCursoId.eq(cargaCursoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .querySingle();
    }
}
