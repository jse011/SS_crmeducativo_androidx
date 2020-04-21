package com.consultoraestrategia.ss_crmeducativo.dao.indicadorDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Icds;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.IndicadorQuery;

import java.util.List;

/**
 * Created by @stevecampos on 19/04/2018.
 */

public interface IndicadorDao extends BaseIntegerDao<Icds> {
    List<Icds> getIndicadores(int silaboEventoId, int capacidadId);

    List<Icds> getIndicadoresConCampoTematico(int silaboEventoId, int capacidadId);

    List<Icds> getIndicadorePorCapacidadYSesionAprendizaje(int capacidadId, int sesionAprendizajeId);

    Icds getIcdsporDesempenioIcd(int desempenioIcdId);

    List<IndicadorQuery> getIcdsporRubroProceso(List<String> rubroEvalProcesoKeyList);
}
