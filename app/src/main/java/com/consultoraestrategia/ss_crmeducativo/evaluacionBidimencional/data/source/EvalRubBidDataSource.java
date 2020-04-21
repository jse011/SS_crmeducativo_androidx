package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.PublicarEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.DeleteAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.EvalAlumnosProcesoBid;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetAlumnoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetGrupoConProc;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetIndicadorRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetRubBid;

import java.util.List;

/**
 * Created by @stevecampos on 23/02/2018.
 */

public interface EvalRubBidDataSource {

    boolean deleteComentario(MensajeUi mensajeUi);

    boolean deleteArchivoComentario(ArchivoComentarioUi archivoComentarioUi);

    PublicarEvaluacionUi  getPublicacionEvaluacion(String rubroEvaluacionId, int personaId);

    void updatePublicacionEvaluacion(PublicarEvaluacionUi publicarEvaluacionUi);

    void onUpdateEvaluacionFormula(UpdateEvaluacionFormulaCallback callback);

    interface UpdateEvaluacionFormulaCallback {
        void onPreLoad();
        void onLoaded(boolean success);
    }

    public interface Callback<T> {
        void onLoaded(T object);
    }

    public interface ListCallback<T> {
        void onLoaded(List<T> items);
    }

    public interface TwoListCallback<T, T1> {
        void onLoaded(List<T> items, List<T1> items1);
    }

    public interface GetAlumnConProcLisTCallback {
        void onLoaded(RubBidUi rubBidUi,List<ColumnHeader> columnHeaderList, List<List<Cell>> cellsList, List<GrupoProcesoUi> grupoProcesoUis);
    }

    interface GetTableCallback {
        void onLoaded(boolean success, RubBidUi rubBidUi,List<ColumnHeader> columnHeaderList, List<List<Cell>> cellsList, List<GrupoProcesoUi> grupoProcesoUis);
    }



    void getRubBid(GetRubBid.RequestValues requestValues, Callback<RubBidUi> callback);
    void getGrupoConNotasProceso(GetGrupoConProc.RequestValues requestValues, GetAlumnConProcLisTCallback callback);
    void evalAlumnosConProceso(EvalAlumnosProcesoBid.RequestValues requestValues, Callback<EvalProcUi> callback);
    void deleteEvalAlumnosConProceso(DeleteAlumnosProcesoBid.RequestValues requestValues, Callback<Boolean> callback);
    void getAlumnosConNotasProceso(GetAlumnoConProc.RequestValues requestValues, GetAlumnConProcLisTCallback callback);
    void getIndicador(GetIndicadorRubro.RequestValues requestValues, Callback<IndicadorUi> callback);
    void searchAlumnosConNotasProceso(FiltroTableUi filtroTableUi, String rubroBidemencionalId, int cargaCursoId, int entidadId, int georeferenciaId, GetTableCallback callback);
    boolean calculaMediaDesviacion(RubBidUi idrubrica);
    List<MensajeUi> getComentarioPred(EvalProcUi evalProcUi);
    List<MensajeUi> getComentarios(String rubricaBidimencionalId, int alumnoId);
    boolean saveComentario( MensajeUi mensajeUi);
    List<ArchivoComentarioUi> getArchivoComentarioList(String rubroEvaluacionId, int personaId);
    boolean saveComentarioArchivo(ArchivoComentarioUi archivoComentarioUi);

}
