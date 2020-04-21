package com.consultoraestrategia.ss_crmeducativo.dao.cargaCursoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursoDocente_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

public class CargaCursoDaoImpl extends BaseIntegerDaoImpl<CargaCursos, CargaCursos_Table> implements CargaCursoDao {


    private static CargaCursoDaoImpl mInstance;

    private CargaCursoDaoImpl() {
    }

    public static CargaCursoDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new CargaCursoDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return CargaCursos_Table.cargaCursoId;
    }

    @Override
    protected Class<CargaCursos> getEntityClass() {
        return CargaCursos.class;
    }

    @Override
    protected Class<CargaCursos_Table> getTableclass() {
        return CargaCursos_Table.class;
    }

    @Override
    public List<CargaCursos> obtenerPorEmpleado(int empleadoId) {
        List<CargaCursos> cargaCursosList = SQLite.select()
                .from(CargaCursos.class)
                .where(CargaCursos_Table.empleadoId.is(empleadoId))
                .and(CargaCursos_Table.complejo.eq(0))
                .queryList();

        cargaCursosList.addAll(SQLite.select()
                .from(CargaCursos.class)
                .innerJoin(CargaCursoDocente.class)
                .on(CargaCursos_Table.cargaCursoId.withTable()
                        .eq(CargaCursoDocente_Table.cargaCursoId.withTable()))
                .where(CargaCursoDocente_Table.docenteId.is(empleadoId))
                .and(CargaCursos_Table.complejo.eq(1))
                .queryList());

        return cargaCursosList;
    }
}
