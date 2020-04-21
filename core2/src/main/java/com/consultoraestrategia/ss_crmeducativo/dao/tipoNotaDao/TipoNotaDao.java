package com.consultoraestrategia.ss_crmeducativo.dao.tipoNotaDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.TipoNotaC;

import java.util.List;

/**
 * Created by @stevecampos on 17/04/2018.
 */

public interface TipoNotaDao extends BaseDao<TipoNotaC> {

    TipoNotaC getValorNumerico();

    TipoNotaC getSelectorNumerico();

    TipoNotaC getSelectorValores();

    TipoNotaC getSelectorIconos();

    TipoNotaC getMyTipoNota(String key);

    List<TipoNotaC> getTipoNotaConValores();

    List<TipoNotaC> getTipoNotaListSelectores();

    TipoNotaC getTipoNotaConValores(String tipoNotaId);

    List<TipoNotaC> getListPorRubros(List<String> rubroEvaluacionKeyList);

    List<TipoNotaC> getListPorRubrosEscala(List<String> rubroEvalProcesoKeyList);
    TipoNotaC getValorAsistencia(int programaEducativoId);

    boolean  validarTipoNota(String valorTipoNotaId);

    boolean  validarTipoNotas(String tipoNotaId);

    List<TipoNotaC> getTipoNotaList(int programaEducativoId, List<Integer> tipoIdList );

}
