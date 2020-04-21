package com.consultoraestrategia.ss_crmeducativo.dao.instrumentoObservada;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado;

import java.util.List;

/**
 * Created by SCIEV on 16/08/2018.
 */

public interface InstrumentoObservadaDao extends BaseDao<InstrumentoObservado> {
    List<InstrumentoObservado> getInstrumentoAlumno(int alumnoId, int entidadId, int georeferenciaId);
}
