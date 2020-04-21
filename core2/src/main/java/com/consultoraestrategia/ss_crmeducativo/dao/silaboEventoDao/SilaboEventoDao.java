package com.consultoraestrategia.ss_crmeducativo.dao.silaboEventoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public interface SilaboEventoDao extends BaseIntegerDao<SilaboEvento> {
    SilaboEvento getByCargaCurso(int cargaCursoId);
}
