package com.consultoraestrategia.ss_crmeducativo.dao.integrandeDeEquipo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.personaDao.PersonaDao;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by @stevecampos on 24/04/2018.
 */

public class IntegranteDeEquipoDaoImpl extends BaseDaoImpl<EquipoIntegranteC, EquipoIntegranteC_Table> implements IntegranteDeEquipoDao {


    private static IntegranteDeEquipoDao mInstance;
    private PersonaDao personaDao;

    private IntegranteDeEquipoDaoImpl(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }

    public static IntegranteDeEquipoDao getInstance(PersonaDao personaDao) {
        if (mInstance == null) {
            mInstance = new IntegranteDeEquipoDaoImpl(personaDao);
        }
        return mInstance;
    }

    @Override
    protected Class<EquipoIntegranteC> getEntityClass() {
        return EquipoIntegranteC.class;
    }

    @Override
    protected Class<EquipoIntegranteC_Table> getTableclass() {
        return EquipoIntegranteC_Table.class;
    }

    @Override
    public List<EquipoIntegranteC> getIntegrantesDeEquipo(String equipoId) {
        return getListWithQuery(
                EquipoIntegranteC_Table.equipoId.is(equipoId)
                //,whereStateisCreated()
        );
    }

    @Override
    public List<EquipoIntegranteC> getIntegrantesDeEquipoConSuPersona(String equipoId) {
        List<EquipoIntegranteC> integrantes = getIntegrantesDeEquipo(equipoId);
        for (EquipoIntegranteC integrante :
                integrantes) {
            Persona persona = personaDao.get(integrante.getAlumnoId());
            integrante.setPersona(persona);
        }
        return integrantes;
    }

    @Override
    public List<EquipoIntegranteC> getIntegrandesDelGrupo(String grupoEquipoId) {
        return SQLite
                .select(Utils.f_allcolumnTable(EquipoIntegranteC_Table.ALL_COLUMN_PROPERTIES))
                .from(EquipoIntegranteC.class)
                .innerJoin(EquipoC.class)
                .on(EquipoIntegranteC_Table.equipoId.withTable()
                        .eq(EquipoC_Table.key.withTable()))
                .where(EquipoC_Table.grupoEquipoId.eq(grupoEquipoId))
                //.and(EquipoIntegranteC_Table.state.withTable().eq(BaseEntity.STATE_CREATED))
                .and(whereStateisCreated())
                .and(EquipoC_Table.estado.withTable().notEq(EquipoC.ESTADO_ELIMINADO))
                .queryList();
    }

    @Override
    public void deleteIntegrantesDeEquipo(String equipoId) {
        SQLite
                .update(EquipoIntegranteC.class)
                .set(EquipoIntegranteC_Table.state.eq(BaseEntity.STATE_DELETED))
                .where(EquipoIntegranteC_Table.equipoId.eq(equipoId))
                .execute();
    }
}
