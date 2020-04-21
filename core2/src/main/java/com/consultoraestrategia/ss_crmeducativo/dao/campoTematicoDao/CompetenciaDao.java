package com.consultoraestrategia.ss_crmeducativo.dao.campoTematicoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public interface CompetenciaDao extends BaseIntegerDao<Competencia> {
    List<Competencia> getCompetencias(int silaboEventoId, int calendarioPeriodoId);

    List<Competencia> getCompetenciasHastaCampoTematico(int silaboEventoId, int calendarioPeriodoId);

    List<Competencia> getCapacidades(int silaboEventoId, int calendarioPeriodoId, int competenciaId);

    List<Competencia> getCompetenciasPorSesionAprendizaje(int sesionAprendizajeId);

    List<Competencia> getCapacidadesPorCompetenciaYSesion(int competenciaParentId, int sesionAprendizajeId);

    Competencia obtenerCompenciaPorCapacidad(int capacidadId);

    Competencia getCompetenciaRubro(String rubroEvalProcesoId);

    TreeMap<Integer, Competencia> getCompetenciaRubro(List<String> rubroEvalProcesoKeyList);
}
