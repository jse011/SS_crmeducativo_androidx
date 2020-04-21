package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;

import java.util.List;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public interface TiposDao extends BaseIntegerDao<Tipos> {
    List<Tipos> getFormaEvaluacionList();
}
