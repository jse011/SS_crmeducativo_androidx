package com.consultoraestrategia.ss_crmeducativo.dao.parametrosDisenio;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.ParametrosDisenio;

import java.util.List;

public interface ParametrosDisenioDao extends BaseIntegerDao<ParametrosDisenio> {
    ParametrosDisenio obtenerDefalut();
    ParametrosDisenio obtenerPorCargaCurso(int cargaCursoId);
    List<ParametrosDisenio> obtenerListaPorCargaCurso(List<Integer> cargaCursoIdList);
    ParametrosDisenio obtenerSilaboEvento(int silaboEventoId);
}
