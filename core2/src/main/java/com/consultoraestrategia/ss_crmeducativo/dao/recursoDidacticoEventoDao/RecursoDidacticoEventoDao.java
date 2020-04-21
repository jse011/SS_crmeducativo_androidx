package com.consultoraestrategia.ss_crmeducativo.dao.recursoDidacticoEventoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public interface RecursoDidacticoEventoDao extends BaseDao<RecursoDidacticoEventoC> {

    RecursoDidacticoEventoC getRecursoPorUnidad(String descripcion, String tituloRecurso, int idUnidadAprendizaje);
}
