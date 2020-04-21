package com.consultoraestrategia.ss_crmeducativo.dao.grupoDeEquipo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.GrupoEquipoC;

import java.util.List;

/**
 * Created by @stevecampos on 24/04/2018.
 */

public interface GrupoDeEquipoDao extends BaseDao<GrupoEquipoC> {
    GrupoEquipoC getGrupoConEquiposEIntegrantes(String id);
    List<GrupoEquipoC> getGruposConEquiposEIntegrantesPorCargaCursoList(List<Integer> listCargaCurso);
    boolean elimarGrupoEquipo(String grupoEquipoId);
}
