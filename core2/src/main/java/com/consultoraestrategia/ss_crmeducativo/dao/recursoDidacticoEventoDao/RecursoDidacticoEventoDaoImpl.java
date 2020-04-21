package com.consultoraestrategia.ss_crmeducativo.dao.recursoDidacticoEventoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class RecursoDidacticoEventoDaoImpl extends BaseDaoImpl<RecursoDidacticoEventoC, RecursoDidacticoEventoC_Table> implements RecursoDidacticoEventoDao {

    private static RecursoDidacticoEventoDao mInstance;

    private RecursoDidacticoEventoDaoImpl() {
    }


    public static RecursoDidacticoEventoDao getInstance() {
        if (mInstance == null) {
            mInstance = new RecursoDidacticoEventoDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<RecursoDidacticoEventoC> getEntityClass() {
        return RecursoDidacticoEventoC.class;
    }

    @Override
    protected Class<RecursoDidacticoEventoC_Table> getTableclass() {
        return RecursoDidacticoEventoC_Table.class;
    }

    @Override
    public RecursoDidacticoEventoC getRecursoPorUnidad(String descripcion, String tituloRecurso, int idUnidadAprendizaje) {
        return SQLite.select()
                .from(RecursoDidacticoEventoC.class)
                .where(RecursoDidacticoEventoC_Table.descripcion.is(descripcion))
                .and(RecursoDidacticoEventoC_Table.titulo.is(tituloRecurso))
                .and(RecursoDidacticoEventoC_Table.unidadAprendizajeId.is(idUnidadAprendizaje))
                .and(RecursoDidacticoEventoC_Table.estado.eq(1))
                .querySingle();
    }
}
