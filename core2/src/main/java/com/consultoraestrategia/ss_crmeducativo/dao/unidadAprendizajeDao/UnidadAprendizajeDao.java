package com.consultoraestrategia.ss_crmeducativo.dao.unidadAprendizajeDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public interface UnidadAprendizajeDao extends BaseDao<UnidadAprendizaje> {
    UnidadAprendizaje getUnidadAprendizajePorId(int idUnidadAprendizaje);
    UnidadAprendizaje getUnidadAprendizajePorSesionId(int sesionAprendizajeId);
    String getCantidadAlumnosEvaluadosTarea(String rubroEvalProcesoId);
}
