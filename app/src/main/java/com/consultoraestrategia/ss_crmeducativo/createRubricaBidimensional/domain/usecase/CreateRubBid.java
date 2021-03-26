package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorNombreUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @stevecampos on 20/02/2018.
 */

public class CreateRubBid extends UseCaseSincrono<CreateRubBid.RequestValues, CreateRubBid.ResponseValue> {

    private static final String TAG = CreateRubBid.class.getSimpleName();
    private CreateRubBidRepository repository;

    public CreateRubBid(CreateRubBidRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(RequestValues request, final Callback<ResponseValue> callback) {
        try {
            for (List<Cell> cellList : request.getBodyList()){
                List<Cell> cellDelte = new ArrayList<>();
                for (Cell cell : cellList){
                        if(cell instanceof IndicadorNombreUi){
                            cellDelte.add(cell);
                        }
                }
                cellList.removeAll(cellDelte);
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        Log.d(TAG, "requestValues");
        repository.createRubBid(request, new CreateRubBidDataSource.SaveCallback() {

            @Override
            public void onSuccess(String rubroEvaluacionId) {
                callback.onResponse(true, new ResponseValue(rubroEvaluacionId));
            }

            @Override
            public void onError() {
                Log.d(TAG, "onError");
                callback.onResponse(false, null);
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private final String rubricaTitle;
        private final String rubricaAlias;
        private final TipoUi tipoEvaluacionSelected;
        private final TipoUi formaEvaluacionSelected;
        private final TipoNotaUi tipoNotaSelected;
        private final EscalaEvaluacionUi escalaSelected;
        private final TipoNotaUi tipoNivelSelected;
        private final List<IndicadorUi> indicadorListSelected;
        private final List<ValorTipoNotaUi> valorTipoNotaList;
        private final List<List<Cell>> bodyList;
        private final int calendarioPeriodoId;
        private final int silaboEventoId;
        private final int nivel;
        private final int cargaCursoId;
        private final int cursoId;
        private final int rubroEvalResultadoId;
        private final int sesionAprendizajeId;
        private final List<CampoAccionUi> selectCampoAccionList;
        private final String tareaId;
        private final String rubricaKeyEdit;
        private int idEstrategiaEvaluacion;

        public RequestValues(String rubricaKeyEdit, String rubricaTitle, String rubricaAlias, TipoUi tipoEvaluacionSelected, TipoUi formaEvaluacionSelected, TipoNotaUi tipoNotaSelected, EscalaEvaluacionUi escalaSelected, TipoNotaUi tipoNivelSelected, List<IndicadorUi> indicadorListSelected, List<ValorTipoNotaUi> valorTipoNotaList, List<List<Cell>> bodyList, int calendarioPeriodoId, int silaboEventoId, int nivel, int cargaCursoId, int cursoId, int rubroEvalResultadoId, int sesionAprendizajeId, List<CampoAccionUi> selectCampoAccionList, String tareaId, int idEstrategiaEvaluacion) {
            this.rubricaTitle = rubricaTitle;
            this.rubricaAlias = rubricaAlias;
            this.tipoEvaluacionSelected = tipoEvaluacionSelected;
            this.formaEvaluacionSelected = formaEvaluacionSelected;
            this.tipoNotaSelected = tipoNotaSelected;
            this.escalaSelected = escalaSelected;
            this.tipoNivelSelected = tipoNivelSelected;
            this.indicadorListSelected = indicadorListSelected;
            this.valorTipoNotaList = valorTipoNotaList;
            this.bodyList = bodyList;
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.silaboEventoId = silaboEventoId;
            this.nivel = nivel;
            this.cargaCursoId = cargaCursoId;
            this.cursoId = cursoId;
            this.rubroEvalResultadoId = rubroEvalResultadoId;
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.selectCampoAccionList = selectCampoAccionList;
            this.tareaId = tareaId;
            this.rubricaKeyEdit = rubricaKeyEdit;
            this.idEstrategiaEvaluacion=idEstrategiaEvaluacion;
        }

        public String getRubricaTitle() {
            return rubricaTitle;
        }

        public String getRubricaAlias() {
            return rubricaAlias;
        }

        public TipoUi getTipoEvaluacionSelected() {
            return tipoEvaluacionSelected;
        }

        public TipoUi getFormaEvaluacionSelected() {
            return formaEvaluacionSelected;
        }

        public TipoNotaUi getTipoNotaSelected() {
            return tipoNotaSelected;
        }

        public EscalaEvaluacionUi getEscalaSelected() {
            return escalaSelected;
        }

        public TipoNotaUi getTipoNivelSelected() {
            return tipoNivelSelected;
        }

        public List<IndicadorUi> getIndicadorListSelected() {
            return indicadorListSelected;
        }

        public List<ValorTipoNotaUi> getValorTipoNotaList() {
            return valorTipoNotaList;
        }

        public List<List<Cell>> getBodyList() {
            return bodyList;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getSilaboEventoId() {
            return silaboEventoId;
        }

        public int getNivel() {
            return nivel;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCursoId() {
            return cursoId;
        }

        public int getRubroEvalResultadoId() {
            return rubroEvalResultadoId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public List<CampoAccionUi> getSelectCampoAccionList() {
            return selectCampoAccionList;
        }

        public String getTareaId() {
            return tareaId;
        }

        public String getRubricaKeyEdit() {
            return rubricaKeyEdit;
        }

        public int getIdEstrategiaEvaluacion() {
            return idEstrategiaEvaluacion;
        }

        public void setIdEstrategiaEvaluacion(int idEstrategiaEvaluacion) {
            this.idEstrategiaEvaluacion = idEstrategiaEvaluacion;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private String rubroEvaluacionResultadoId;

        public ResponseValue(String rubroEvaluacionResultadoId) {
            this.rubroEvaluacionResultadoId = rubroEvaluacionResultadoId;
        }

        public String getRubroEvaluacionResultadoId() {
            return rubroEvaluacionResultadoId;
        }
    }
}
