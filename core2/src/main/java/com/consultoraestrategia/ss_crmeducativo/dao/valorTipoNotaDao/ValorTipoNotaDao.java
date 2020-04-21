package com.consultoraestrategia.ss_crmeducativo.dao.valorTipoNotaDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.ValorTipoNotaC;

import java.util.List;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public interface ValorTipoNotaDao extends BaseDao<ValorTipoNotaC> {
    List<ValorTipoNotaC> getListPorTipoNotaId(String tipoNotaId);
    List<ValorTipoNotaC> getListValorTipoNotaAsistencia();
    List<ValorTipoNotaC> getListPorRubros(List<String> rubroEvaluacionKeyList);

    ValorTipoNotaC  getValorTipoNotaPorTipoNota(String valorTipoNotaTipoId, int estadoValorTipoNota);
    ValorTipoNotaC  getValorTipoNotaPorId(String valorTipoNotaTipoId);
}
