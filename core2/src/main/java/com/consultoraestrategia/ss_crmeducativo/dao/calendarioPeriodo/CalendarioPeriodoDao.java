package com.consultoraestrategia.ss_crmeducativo.dao.calendarioPeriodo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.List;

/**
 * Created by SCIEV on 31/08/2018.
 */

public interface CalendarioPeriodoDao extends BaseIntegerDao<CalendarioPeriodo> {
    List<CalendarioPeriodo> getRubrosEvalProceso(List<String> rubroEvalProcesoKeyList);
    boolean isVigenteCalendarioPorFechaActual(int calendarioPeriodoId, int cargarCursoId, boolean isRubroResultado);
    boolean isVigenteCalendarioCursoPeriodo(int calendarioPeridoId, int cargaCursoId, boolean isRubroResultado, DatabaseWrapper databaseWrapper);
}
