package com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;

import java.util.List;

/**
 * Created by @stevecampos on 19/04/2018.
 */

public interface CampoTematicoDao extends BaseIntegerDao<CampoTematico> {
    List<CampoTematico> getCamposTematicos(int silaboEventoId, int indicadorId);
    List<CampoTematico> getCamposTematicosPorIndicadorYSesionAprendizaje(int indicadorId, int sesionAprendizajeId);
    List<CampoTematico> getCampotematicoRubro(String rubroEvalProcesoId);
}
