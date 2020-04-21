package com.consultoraestrategia.ss_crmeducativo.dao.equipo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoC;

import java.util.List;

/**
 * Created by @stevecampos on 24/04/2018.
 */

public interface EquipoDao extends BaseDao<EquipoC> {
    List<EquipoC> getEquiposDeGrupo(String grupoId);
    List<EquipoC> getEquiposConIntegrantes(String grupoId);
    EquipoC getEquipoConIntegrantes(String equipoId);
    EquipoC getEquipoConIntegrantesConSuPersona(String equipoId);
    void deleteEquipoConIntegrantes(EquipoC equipo, BaseDaoImpl.Callback<Boolean> result);
    void deleteEquipoConIntegrantes(List<EquipoC> equipos, BaseDaoImpl.Callback<Boolean> result);
    void createEquipoConIntegrantes(EquipoC equipo, BaseDaoImpl.Callback<Boolean> result);

}
