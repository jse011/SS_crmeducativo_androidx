package com.consultoraestrategia.ss_crmeducativo.dao.cargaCursoDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CargaCursos;

import java.util.List;

public interface CargaCursoDao extends BaseIntegerDao<CargaCursos> {
    List<CargaCursos> obtenerPorEmpleado(int empleadoId);
}
