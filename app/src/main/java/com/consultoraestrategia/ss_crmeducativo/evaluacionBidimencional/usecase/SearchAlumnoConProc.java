package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;

import java.util.List;

/**
 * Created by @stevecampos on 23/02/2018.
 */

public class SearchAlumnoConProc extends UseCase<SearchAlumnoConProc.RequestValues, SearchAlumnoConProc.ResponseValue>{
    public static final String TAG = SearchAlumnoConProc.class.getSimpleName();
    private EvalRubBidRepository repository;

    public SearchAlumnoConProc(EvalRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
            repository.searchAlumnosConNotasProceso(requestValues.getFiltroTableUi(), requestValues.getRubBidId(), requestValues.getCargaCursoId(), requestValues.getEntidadId(), requestValues.getGeoreferenciaId(),new EvalRubBidDataSource.GetTableCallback() {
                @Override
                public void onLoaded(boolean success, RubBidUi rubBidUi, List<ColumnHeader> columnHeaderList, List<List<Cell>> cellsList, List<GrupoProcesoUi> grupoProcesoUis) {
                    if(success){
                        getUseCaseCallback().onSuccess(new ResponseValue(rubBidUi, columnHeaderList, cellsList, grupoProcesoUis));
                    }else {
                        getUseCaseCallback().onError();
                    }
                }
            });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private final String rubBidId;
        private final int cargaCursoId;
        private final FiltroTableUi filtroTableUi;
        private final int entidadId;
        private final int georeferenciaId;

        public RequestValues(String rubBidId, int cargaCursoId, FiltroTableUi filtroTableUi, int entidadId, int georeferenciaId) {
            this.rubBidId = rubBidId;
            this.cargaCursoId = cargaCursoId;
            this.filtroTableUi = filtroTableUi;
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

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public FiltroTableUi getFiltroTableUi() {
            return filtroTableUi;
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
