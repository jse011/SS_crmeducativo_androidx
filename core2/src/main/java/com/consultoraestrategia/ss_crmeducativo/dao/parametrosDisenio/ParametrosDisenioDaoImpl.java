package com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import java.util.List;

public class ParametrosDisenioDaoImpl extends BaseIntegerDaoImpl<ParametrosDisenio, ParametrosDisenio_Table> implements ParametrosDisenioDao {
    private static ParametrosDisenioDaoImpl mInstance;

    private ParametrosDisenioDaoImpl() {
    }

    public static ParametrosDisenioDaoImpl getInstance() {
        if (mInstance == null) {
            mInstance = new ParametrosDisenioDaoImpl();
        }
        return mInstance;
    }

    @Override
    protected Property<Integer> getPrimaryKeyProperty() {
        return ParametrosDisenio_Table.parametroDisenioId;
    }

    @Override
    protected Class<ParametrosDisenio> getEntityClass() {
        return ParametrosDisenio.class;
    }

    @Override
    protected Class<ParametrosDisenio_Table> getTableclass() {
        return ParametrosDisenio_Table.class;
    }

    @Override
    public ParametrosDisenio obtenerDefalut() {
        return SQLite.select()
                .from(ParametrosDisenio.class)
                .where(ParametrosDisenio_Table.nombre.eq("default"))
                .querySingle();
    }

    @Override
    public ParametrosDisenio obtenerPorCargaCurso(int cargaCursoId) {
        ParametrosDisenio parametrosDisenio = SQLite.select()
                .from(ParametrosDisenio.class)
                .innerJoin(SilaboEvento.class)
                .on(ParametrosDisenio_Table.parametroDisenioId.withTable()
                        .eq(SilaboEvento_Table.parametroDisenioId.withTable()))
                .where(SilaboEvento_Table.cargaCursoId.withTable()
                        .eq(cargaCursoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(ParametrosDisenio_Table.estado.withTable().eq(true))
                .querySingle();


        if(parametrosDisenio==null)parametrosDisenio = SQLite.select()
                .from(ParametrosDisenio.class)
                .where(ParametrosDisenio_Table.nombre.eq("default"))
                .querySingle();

        return parametrosDisenio;
    }

    @Override
    public ParametrosDisenio obtenerSilaboEvento(int silaboEventoId) {

        ParametrosDisenio parametrosDisenio = SQLite.select()
                .from(ParametrosDisenio.class)
                .innerJoin(SilaboEvento.class)
                .on(ParametrosDisenio_Table.parametroDisenioId.withTable()
                        .eq(SilaboEvento_Table.parametroDisenioId.withTable()))
                .where(SilaboEvento_Table.silaboEventoId.withTable()
                        .eq(silaboEventoId))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(ParametrosDisenio_Table.estado.withTable().eq(true))
                .querySingle();


        if(parametrosDisenio==null)parametrosDisenio = SQLite.select()
                .from(ParametrosDisenio.class)
                .where(ParametrosDisenio_Table.nombre.eq("default"))
                .querySingle();


        return parametrosDisenio;
    }

    @Override
    public List<ParametrosDisenio> obtenerListaPorCargaCurso(List<Integer> cargaCursoIdList) {
        return SQLite.select()
                .from(ParametrosDisenio.class)
                .innerJoin(SilaboEvento.class)
                .on(ParametrosDisenio_Table.parametroDisenioId.withTable()
                        .eq(SilaboEvento_Table.parametroDisenioId.withTable()))
                .where(SilaboEvento_Table.cargaCursoId.withTable()
                        .in(cargaCursoIdList))
                .and(SilaboEvento_Table.estadoId.withTable().notEq(SilaboEvento.ESTADO_CREADO))
                .and(ParametrosDisenio_Table.estado.withTable().eq(true))
                .queryList();
    }
}
