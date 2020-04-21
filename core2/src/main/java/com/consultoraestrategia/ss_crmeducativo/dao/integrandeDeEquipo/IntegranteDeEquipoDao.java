package com.consultoraestrategia.ss_crmeducativo.dao.integrandeDeEquipo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoIntegranteC;

import java.util.List;

/**
 * Created by @stevecampos on 24/04/2018.
 */

public interface IntegranteDeEquipoDao extends BaseDao<EquipoIntegranteC> {
    List<EquipoIntegranteC> getIntegrantesDeEquipo(String equipoId);
    List<EquipoIntegranteC> getIntegrantesDeEquipoConSuPersona(String equipoId);
    List<EquipoIntegranteC> getIntegrandesDelGrupo(String grupoEquipoId);
    void deleteIntegrantesDeEquipo(String equipoId);
}
