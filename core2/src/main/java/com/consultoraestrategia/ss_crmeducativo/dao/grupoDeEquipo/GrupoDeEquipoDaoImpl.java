package com.consultoraestrategia.ss_crmeducativo.dao.grupoDeEquipo;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.equipo.EquipoDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado;
import com.consultoraestrategia.ss_crmeducativo.entities.Empleado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Where;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 24/04/2018.
 */

public class GrupoDeEquipoDaoImpl extends BaseDaoImpl<GrupoEquipoC, GrupoEquipoC_Table> implements GrupoDeEquipoDao {

    private static GrupoDeEquipoDao mInstance;
    private EquipoDao equipoDao;
    String TAG=GrupoDeEquipoDaoImpl.class.getSimpleName();

    public GrupoDeEquipoDaoImpl(EquipoDao equipoDao) {
        this.equipoDao = equipoDao;
    }

    public static GrupoDeEquipoDao getInstance(EquipoDao equipoDao) {
        if (mInstance == null) {
            mInstance = new GrupoDeEquipoDaoImpl(equipoDao);
        }
        return mInstance;
    }

    @Override
    protected Class<GrupoEquipoC> getEntityClass() {
        return GrupoEquipoC.class;
    }

    @Override
    protected Class<GrupoEquipoC_Table> getTableclass() {
        return GrupoEquipoC_Table.class;
    }

    @Override
    public GrupoEquipoC getGrupoConEquiposEIntegrantes(String grupoId) {
        GrupoEquipoC grupoDeEquipos = get(grupoId);
        llenarGrupoConEquiposEIntegrantes(grupoDeEquipos);
        return grupoDeEquipos;
    }

    private void llenarGrupoConEquiposEIntegrantes(GrupoEquipoC grupoDeEquipos) {
        List<EquipoC> equipos = equipoDao.getEquiposConIntegrantes(grupoDeEquipos.getKey());
        grupoDeEquipos.setEquipos(equipos);
    }


    @Override
    public boolean elimarGrupoEquipo(String grupoEquipoId) {
        boolean result;
        DatabaseDefinition appDatabase = FlowManager.getDatabase(AppDatabase.class);
        DatabaseWrapper databaseWrapper = appDatabase.getWritableDatabase();
        try {
            databaseWrapper.beginTransaction();
            GrupoEquipoC grupoEquipoC = get(grupoEquipoId);
            grupoEquipoC.setEstado(GrupoEquipoC.ELIMINADO);
            grupoEquipoC.setSyncFlag(BaseEntity.FLAG_UPDATED);
            grupoEquipoC.save();
            databaseWrapper.setTransactionSuccessful();
            result = true;
        } catch (Exception error){
            error.printStackTrace();
            result = false;
        }finally {
            databaseWrapper.endTransaction();
        }
        return result;
    }

    @Override
    public List<GrupoEquipoC> getGruposConEquiposEIntegrantesPorCargaCursoList(List<Integer> listCargaCurso) {
        List<GrupoEquipoC> grupoEquiposList= new ArrayList<>();
        Empleado empleado = SQLite.select()
                    .from(Empleado.class)
                    .where(Empleado_Table.personaId.eq(SessionUser.getCurrentUser().getPersonaId()))
                    .querySingle();
            if(empleado!=null){
                Where<GrupoEquipoC> where = SQLite.select()
                        .from(getEntityClass())
                        .where(GrupoEquipoC_Table.finished.eq(true),
                                GrupoEquipoC_Table.estado.in(GrupoEquipoC.CREADO, GrupoEquipoC.ACTUALIZADO))
                        .and(GrupoEquipoC_Table.cargaCursoId.in(listCargaCurso))
                        .and(GrupoEquipoC_Table.docenteId.eq(empleado.getEmpleadoId()));
                        //.orderBy(GrupoEquipoC_Table.fechaCreacion.asc());

                grupoEquiposList=where.orderBy(GrupoEquipoC_Table.fechaCreacion.desc())
                        .queryList();

                for (GrupoEquipoC grupoDeEquipos : grupoEquiposList)llenarGrupoConEquiposEIntegrantes(grupoDeEquipos);

            }

        return grupoEquiposList;
    }



}
