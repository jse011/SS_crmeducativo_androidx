package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;

import java.util.List;

/**
 * Created by @stevecampos on 23/02/2018.
 */

public class GetAlumnoConProc extends UseCase<GetAlumnoConProc.RequestValues, GetAlumnoConProc.ResponseValue>{
    public static final String TAG = GetAlumnoConProc.class.getSimpleName();
    private EvalRubBidRepository repository;

    public GetAlumnoConProc(EvalRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        try {
            repository.getAlumnosConNotasProceso(requestValues, new EvalRubBidDataSource.GetAlumnConProcLisTCallback() {
                @Override
                public void onLoaded(RubBidUi rubBidUi, List<ColumnHeader> columnHeaderList, List<List<Cell>> cellsList, List<GrupoProcesoUi> grupoProcesoUis) {
                    getUseCaseCallback().onSuccess(new ResponseValue(rubBidUi,columnHeaderList, cellsList,grupoProcesoUis));
                }
            });
        }catch (Exception e){
           e.printStackTrace();
           getUseCaseCallback().onError();
        }
    }

    public static class RequestValues implements UseCase.RequestValues {
        private final String rubBidId;
        private final int cursoId;
        private final int cargaCursoId;
        private final int entidadId;
        private final int georeferenciaId;

        public RequestValues(String rubBidId, int cursoId, int cargaCursoId, int entidadId, int georeferenciaId) {
            this.rubBidId = rubBidId;
            this.cursoId = cursoId;
            this.cargaCursoId = cargaCursoId;
            this.entidadId = entidadId;
            this.georeferenciaId = georeferenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public String getRubBidId() {
            return rubBidId;
        }

        public int getCursoId() {
            return cursoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private final RubBidUi rubBidUi;
        private final List<ColumnHeader> alumnoProcesoUis;
        private final List<List<Cell>> cellList;
        private final List<GrupoProcesoUi> grupoProcesoUis;

        public ResponseValue(RubBidUi rubBidUi, List<ColumnHeader> alumnoProcesoUis, List<List<Cell>> cellList, List<GrupoProcesoUi> grupoProcesoUis) {
            this.rubBidUi = rubBidUi;
            this.alumnoProcesoUis = alumnoProcesoUis;
            this.cellList = cellList;
            this.grupoProcesoUis = grupoProcesoUis;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
        }

        public List<ColumnHeader> getAlumnoProcesoUis() {
            return alumnoProcesoUis;
        }

        public List<List<Cell>> getCellList() {
            return cellList;
        }

        public List<GrupoProcesoUi> getGrupoProcesoUis() {
            return grupoProcesoUis;
        }
    }
}
