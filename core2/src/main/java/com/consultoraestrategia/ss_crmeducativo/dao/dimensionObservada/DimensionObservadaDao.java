package com.consultoraestrategia.ss_crmeducativo.dao.dimensionObservada;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.DimensionObservada;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.entities.InstrumentoObservado;

import java.util.List;

/**
 * Created by SCIEV on 16/08/2018.
 */

public interface DimensionObservadaDao extends BaseDao<DimensionObservada> {
    List<DimensionObservada> getTwoMaxDimensionesAlumno(int personaId, int entidadId);
    List<DimensionObservada> getDimensionesAlumno(int alumnoId, int entidadId, int georeferenciaId);
    List<DimensionObservada> getDimensionesAlumnoInstrumento(int alumnoId, int entidadId, int georeferenciaId,String instrumentoId);
    InstrumentoEvaluacion getDimensionColegio(int entidadId, int georeferenciaId);
}
