package com.consultoraestrategia.ss_crmeducativo.dao.tareasDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.raizlabs.android.dbflow.converter.SqlDateConverter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public class TareasDaoImpl extends BaseDaoImpl<TareasC, TareasC_Table> implements TareasDao {

    private static TareasDao mInstance;

    private TareasDaoImpl() {
    }


    public static TareasDao getInstance() {
        if (mInstance == null) {
            mInstance = new TareasDaoImpl();
        }
        return mInstance;
    }


    @Override
    protected Class<TareasC> getEntityClass() {
        return TareasC.class;
    }

    @Override
    protected Class<TareasC_Table> getTableclass() {
        return TareasC_Table.class;
    }


    @Override
    public List<TareasC> getTareasPorSesionAprendizaje(int sesionAprendizajeId) {
        return SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.sesionAprendizajeId.is(sesionAprendizajeId))
                .orderBy(TareasC_Table.fechaCreacion.asc())
                .and(TareasC_Table.estadoId.notIn(265))
                .queryList();
    }

    @Override
    public List<TareasC> getTareasPorUnidadAprendizaje(int unidadAprendizajeId) {
        return SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.unidadAprendizajeId.is(unidadAprendizajeId))
                .and(TareasC_Table.estadoId.notIn(265))
                //.and(TareasC_Table.sesionAprendizajeId.eq(0))
                .orderBy(TareasC_Table.fechaCreacion.asc())
                .queryList();
    }

    @Override
    public TareasC getTareaPorUnidadTituloUsuario(String titulo, String instruciones, int idUnidadAprendizaje, int idUsuario) {
        return SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.titulo.is(titulo))
                .and(TareasC_Table.instrucciones.is(instruciones))
                .and(TareasC_Table.unidadAprendizajeId.is(idUnidadAprendizaje))
                .and(TareasC_Table.usuarioCreacionId.is(idUsuario))
                .querySingle();
    }

    @Override
    public TareasC getTareaId(String id) {
        return SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.key.is(id))
                .querySingle();
    }
}
