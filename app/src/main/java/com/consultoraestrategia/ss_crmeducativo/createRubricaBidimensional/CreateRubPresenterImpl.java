package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional;

import android.content.res.Resources;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.cell.PesoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.cell.SelectorCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row.PesoRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row.SelectorIconoRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.tableView.holder.row.SelectorValorRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.CreateRubBid;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetCamposAccion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetCompetenciaRubrica;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetCompetencias;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetEscalaList;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetEstrategiaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetFormaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetFormaEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetNivelesTipoNotaRubrica;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTarea;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNota;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotaRubrica;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTipoNotas;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase.GetTituloRubrica;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.PrecisionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by @stevecampos on 26/01/2018.
 */

public class CreateRubPresenterImpl extends BasePresenterImpl<CreateRubBidView> implements CreateRubBidPresenter {
    public static final String TAG = CreateRubPresenterImpl.class.getSimpleName();

    private GetCompetencias getCompetencias;
    private GetTipoNotas getTipoNotas;
    private GetTipoNota getTipoNota;
    private GetEscalaList getEscalaList;
    private GetTipoEvaluacionList getTipoEvaluacionList;
    private GetFormaEvaluacionList getFormaEvaluacionList;
    private CreateRubBid createRubBid;
    private GetTarea getTarea;
    private GetCamposAccion getCamposAccion;
    private GetTipoNotaRubrica getTipoNotaRubrica;
    private GetFormaEvaluacion getFormaEvaluacion;
    private GetTipoEvaluacion getTipoEvaluacion;
    private GetCompetenciaRubrica getCompetenciaRubrica;
    private GetNivelesTipoNotaRubrica getNivelesTipoNotaRubrica;
    private GetTituloRubrica getTituloRubrica;
    private List<CampoAccionUi> campoAccionUiList;
    private List<CampoAccionUi> selectCampoAccionList = new ArrayList<>();
    private int idProgramaEducativo;
    private String tareaId;
    private TipoNotaUi tipoNotaUi;
    private IndicadorUi indicadorUi;
    private String rubricaKeyEdit;
    private String rubricaTitle;
    private boolean disabledNivelLogroRubro;
    private GetEstrategiaEvaluacion getEstrategiaEvaluacion;
    private List<EstrategiaEvalUi>estrategiaEvalUis = new ArrayList<>();
    private EstrategiaEvalUi estrategiaEvalUiSelected;
    private int estrategiaId;
    private String rubricaAlias;
    private boolean complejo = false;
    private GetTipoNotaDefault getTipoNotaDefault;
    private GetNotaPresicion getNotaPresicion;


    public CreateRubPresenterImpl(UseCaseHandler handler, Resources res, GetCompetencias getCompetencias, GetTipoNotas getTipoNotas, GetTipoNota getTipoNota, GetEscalaList getEscalaList, GetTipoEvaluacionList getTipoEvaluacionList, GetFormaEvaluacionList getFormaEvaluacion, CreateRubBid createRubBid, GetCamposAccion getCamposAccion,
                                  GetTipoNotaDefault getTipoNotaDefault, GetTarea getTarea,
                                  GetFormaEvaluacion getFormaEvaluacionSimple,
                                  GetTipoEvaluacion getTipoEvaluacion,
                                  GetTipoNotaRubrica getTipoNotaRubrica,
                                  GetCompetenciaRubrica getCompetenciaRubrica,
                                  GetNivelesTipoNotaRubrica getNivelesTipoNotaRubrica,
                                  GetTituloRubrica getTituloRubrica,
                                  GetEstrategiaEvaluacion getEstrategiaEvaluacion,
                                  GetNotaPresicion getNotaPresicion) {
        super(handler, res);
        this.getCompetencias = getCompetencias;
        this.getTipoNotas = getTipoNotas;
        this.getTipoNota = getTipoNota;
        this.getEscalaList = getEscalaList;
        this.getTipoEvaluacionList = getTipoEvaluacionList;
        this.getFormaEvaluacionList = getFormaEvaluacion;
        this.createRubBid = createRubBid;
        this.getCamposAccion = getCamposAccion;
        this.campoAccionUiList = new ArrayList<>();
        this.getTipoNotaDefault = getTipoNotaDefault;
        this.getTarea = getTarea;
        this.getFormaEvaluacion = getFormaEvaluacionSimple;
        this.getTipoEvaluacion = getTipoEvaluacion;
        this.getTipoNotaRubrica = getTipoNotaRubrica;
        this.getCompetenciaRubrica = getCompetenciaRubrica;
        this.getNivelesTipoNotaRubrica = getNivelesTipoNotaRubrica;
        this.getTituloRubrica = getTituloRubrica;
        this.getEstrategiaEvaluacion=getEstrategiaEvaluacion;
        this.getNotaPresicion = getNotaPresicion;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    public void onCampoTematicoListOk(List<IndicadorUi> indicadorList) {
        Log.d(TAG, "onCamposAccionSucces");
        showCampoAccionTitle(indicadorList);
    }

    private int sesionAprendizajeId;
    private int silaboEventoId;
    private int calendarioPeriodoId;
    private int rubroEvalResultadoId;
    private int rubroEvalAprendizajeId;
    private int cargaCursoId;
    private int cursoId;

    @Override
    public void onCreate() {
        super.onCreate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (extras != null) {
                    CRMBundle crmBundle = new CRMBundle(extras);
                    silaboEventoId = crmBundle.getSilaboEventoId();
                    calendarioPeriodoId = crmBundle.getCalendarioPeriodoId();
                    rubroEvalResultadoId = crmBundle.getRubroEvalResultadoId();
                    rubroEvalAprendizajeId = crmBundle.getRubroEvalResultadoId();
                    cargaCursoId = crmBundle.getCargaCursoId();
                    cursoId = crmBundle.getCursoId();
                    sesionAprendizajeId = crmBundle.getSesionAprendizajeId();
                    idProgramaEducativo = crmBundle.getProgramaEducativoId();
                    tareaId = crmBundle.getTareaId();
                    if (sesionAprendizajeId != 0) {
                        tipo = GetCompetencias.BY_SESSION;
                    }
                    rubricaKeyEdit = extras.getString(CreateRubBidActivity.RUBRICATAG, null);
                    Log.d(TAG, "sesionAprendizajeId: " + sesionAprendizajeId);
                    Log.d(TAG, "calendarioPeriodoId: " + calendarioPeriodoId);
                    Log.d(TAG, "cargaCursoId: " + cargaCursoId);
                    Log.d(TAG, "silaboEventoId: " + silaboEventoId);
                    Log.d(TAG, "idProgramaEducativo: " + idProgramaEducativo);
                    Log.d(TAG, "tareaId: " + tareaId);
                    Log.d(TAG, "rubricaKeyEdit: " + rubricaKeyEdit);
                    if(TextUtils.isEmpty(rubricaKeyEdit)){
                        getTipoNotaGlobalDefault();
                        //getEscalaList();
                        getTipoEvaluacionList();
                        getFormaEvaluacionList();
                        getCompetencias();
                        getTareaUi();
                        getEstrategiasEvaluacion();
                    }


                }
                if(!TextUtils.isEmpty(rubricaKeyEdit)){

                    getTipoEvaluacion(rubricaKeyEdit);
                    getFormaEvaluacion(rubricaKeyEdit);
                    getTipoNotaRubrica(rubricaKeyEdit);
                    getEstrategiasEvaluacion();
                    getTituloRubrica(rubricaKeyEdit);

                    //getCompetenciasRubrica(rubricaKeyEdit);
                    //getTipoNotaNivelesRubrica(rubricaKeyEdit);


                    getCompetenciasRubrica(rubricaKeyEdit);
                    getTipoNotaNivelesRubrica(rubricaKeyEdit);
                    List<IndicadorUi> indicadorList = getIndicadoresSelected(competenciaList);
                    if(indicadorList!=null){
                        showCampoAccionTitle(indicadorList);
                    }

                    showCompetenciaList(competenciaList);

                    //if(view!=null)view.disabledNivelLogroRubro();
                    disabledNivelLogroRubro = true;
                    if(view!=null)view.disabledFormaEvaluacion();
                    if(view!=null)view.disabledNivelLogroRubrica();
                    if(view!=null)view.disabledBusquedaCamposAccion();
                    if(view!=null)view.disabledSelectorIndicador();
                    if(view!=null)view.disabledSelectorCamposAccion();
                }

                habilitarCriterios();
            }
        },300);

    }


    private void habilitarCriterios() {
        complejo = !estrategiaEvalUis.isEmpty();
        if(complejo){
            if(view!=null)view.showEstrategiaInput();
        }
        else {
            if(view!=null)view.hideEstrategiaInput();
        }

    }

    private void getEstrategiasEvaluacion() {
        GetEstrategiaEvaluacion.Response response= getEstrategiaEvaluacion.execute(new GetEstrategiaEvaluacion.Request(cursoId));
        estrategiaEvalUis.clear();
        estrategiaEvalUis.addAll(response.getEstrategiaEvalUis());
        if(estrategiaId!=0){
            for(EstrategiaEvalUi estrategiaUi: estrategiaEvalUis){
                if(estrategiaUi.getEstrategiaId()==estrategiaId)
                    estrategiaEvalUiSelected=estrategiaUi;
            }
        }
        Log.e(TAG, "estrategiaEvalUis "+estrategiaEvalUis.size());
    }

    private void getTipoNotaNivelesRubrica(String rubricaKeyEdit) {
        tipoNotaUi = getNivelesTipoNotaRubrica.execute(rubricaKeyEdit);
        if(tipoNotaUi!=null){
            Log.e(TAG, "getTipoNotaNivelesRubrica onSuccess");
            showTipoNivelSelected(tipoNotaUi);
            handler.execute(
                    getTipoNota,
                    new GetTipoNota.RequestValues(tipoNotaUi.getId()),
                    new UseCase.UseCaseCallback<GetTipoNota.ResponseValue>() {
                        @Override
                        public void onSuccess(GetTipoNota.ResponseValue response) {
                            Log.e(TAG, "getTipoNota onSuccess");
                                tipoNivelSelected = response.getTipoNotaUi();
                                showTableview(true);
                        }

                        @Override
                        public void onError() {
                            Log.e(TAG, "getTipoNotas onError");
                            showImportantMessage(res.getString(R.string.createrubbid_error_getting_tiponota));
                        }
                    }

            );
        }
    }

    private void getCompetenciasRubrica(String rubricaKeyEdit) {
        showCompetenciaListChooser(getCompetenciaRubrica.execute(rubricaKeyEdit));
    }


    private void getTipoNotaRubrica(String rubricaKeyEdit) {
        tipoNotaSelected = getTipoNotaRubrica.execute(rubricaKeyEdit);
        if(tipoNotaSelected!=null){
            showTipoNotaSelected(tipoNotaSelected);
            getPrescion(tipoNotaSelected);
        }else {
            showImportantMessage(res.getString(R.string.unknown_error));
        }


    }

    private void getFormaEvaluacion(String rubricaKeyEdit) {
        TipoUi tipoUi = getFormaEvaluacion.execute(rubricaKeyEdit);
        if(tipoUi!=null){
            formaEvaluacionSelected = tipoUi;
            formaEvaluacionList = new ArrayList<>();
            formaEvaluacionList.add(tipoUi);
            defaultSelectFormaEvaluacion(formaEvaluacionList, 1);
        }else {
            showImportantMessage(res.getString(R.string.unknown_error));
        }

    }

    private void getTipoEvaluacion(String rubricaKeyEdit) {
        final TipoUi tipoUi = getTipoEvaluacion.execute(rubricaKeyEdit);
        if(tipoUi==null)  {
            showImportantMessage(res.getString(R.string.unknown_error));
            return;
        }

        handler.execute(
                getTipoEvaluacionList,
                new GetTipoEvaluacionList.RequestValues(),
                new UseCase.UseCaseCallback<GetTipoEvaluacionList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoEvaluacionList.ResponseValue response) {
                        tipoEvaluacionList = response.getList();
                        int posicion = tipoEvaluacionList.indexOf(tipoUi);
                        defaultSelectTipoEvaluacion(tipoEvaluacionList, posicion);
                    }

                    @Override
                    public void onError() {
                        showImportantMessage(res.getString(R.string.unknown_error));
                    }
                });
    }


    private void getTareaUi() {
        if (tareaId!=null) {
            handler.execute(
                    getTarea,
                    new GetTarea.RequestValues(tareaId),
                    new UseCase.UseCaseCallback<GetTarea.ResponseValue>() {
                        @Override
                        public void onSuccess(GetTarea.ResponseValue response) {
                            TareasUI tareasUI = response.getTareasUI();
                            if(tareasUI!=null){
                                if(complejo){
                                    if (view!=null)view.setSubTitulo(tareasUI.getTituloTarea());
                                }else {
                                    if (view!=null)view.setTitulo(tareasUI.getTituloTarea());
                                }
                            }
                        }

                        @Override
                        public void onError() {
                            showImportantMessage(res.getString(R.string.unknown_error));
                        }
                    }
            );
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getFormaEvaluacionList() {
        Log.d(TAG, "getFormaEvaluacion");
        handler.execute(
                getFormaEvaluacionList,
                new GetFormaEvaluacionList.RequestValues(),
                new UseCase.UseCaseCallback<GetFormaEvaluacionList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetFormaEvaluacionList.ResponseValue response) {
                        formaEvaluacionList = response.getList();
                        defaultSelectFormaEvaluacion(formaEvaluacionList, 0);
                    }

                    @Override
                    public void onError() {
                        showImportantMessage(res.getString(R.string.unknown_error));
                    }
                });
    }

    private List<TipoUi> formaEvaluacionList;

    private void showFormaEvaluacionList(List<TipoUi> formaEvaluacionList) {
        this.formaEvaluacionList = formaEvaluacionList;

        if (view != null) {
            //view.showFormaEvaluacionChooser(formaEvaluacionList);
            List<Object> items = new ArrayList<>();
            items.addAll(formaEvaluacionList);
            view.showListSingleChooser(res.getString(R.string.createrubbid_formaevaluacion_dialog_title), items, -1);
        }
    }

    private void getTipoEvaluacionList() {
        Log.d(TAG, "getTipoEvaluacionList");
        handler.execute(
                getTipoEvaluacionList,
                new GetTipoEvaluacionList.RequestValues(),
                new UseCase.UseCaseCallback<GetTipoEvaluacionList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoEvaluacionList.ResponseValue response) {
                        tipoEvaluacionList = response.getList();
                        defaultSelectTipoEvaluacion(tipoEvaluacionList, 0);
                    }

                    @Override
                    public void onError() {
                        showImportantMessage(res.getString(R.string.unknown_error));
                    }
                });
    }

    private List<TipoUi> tipoEvaluacionList;

    private void showTipoEvaluacionList(List<TipoUi> tipoEvaluacionList) {
        this.tipoEvaluacionList = tipoEvaluacionList;
        if (view != null) {
            //view.showTipoEvaluacionChooser(tipoEvaluacionList);
            List<Object> items = new ArrayList<>();
            items.addAll(tipoEvaluacionList);
            view.showListSingleChooser(res.getString(R.string.createrubbid_tipoevaluacion_dialog_title), items, -1);
        }
    }

    private void getEscalaList() {
        Log.d(TAG, "getEscalaList");
        handler.execute(
                getEscalaList,
                new GetEscalaList.RequestValues(),
                new UseCase.UseCaseCallback<GetEscalaList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetEscalaList.ResponseValue response) {
                        escalaEvaluacionList = response.getList();
                    }

                    @Override
                    public void onError() {
                        showImportantMessage(res.getString(R.string.unknown_error));
                    }
                });
    }

    private List<EscalaEvaluacionUi> escalaEvaluacionList;

    private void showEscalaEvaluacionList(List<EscalaEvaluacionUi> escalaEvaluacionUiList) {
        this.escalaEvaluacionList = escalaEvaluacionUiList;
        if (view != null) {
            //view.showEscalaChooser(escalaEvaluacionUiList);
            List<Object> items = new ArrayList<>();
            items.addAll(escalaEvaluacionUiList);
            view.showListSingleChooser(res.getString(R.string.createrubbid_escalaevaluacion_dialog_title), items, -1);
        }
    }

    private int tipo = GetCompetencias.BY_CARGA_CURSO_PERIODO;

    private void getCompetencias() {
        Log.d(TAG, "getCompetencias");
        if (!competenciaList.isEmpty()) return;
        Log.d(TAG, "getCompetencias idSesionAprendizaje: " + sesionAprendizajeId);
        handler.execute(
                getCompetencias,
                new GetCompetencias.RequestValues(tipo, sesionAprendizajeId, cursoId, cargaCursoId, calendarioPeriodoId),
                new UseCase.UseCaseCallback<GetCompetencias.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCompetencias.ResponseValue response) {
                        Log.d(TAG, "getIndicadores onSucess" + response.getCompetenciaList().size());
                        showCompetenciaListChooser(response.getCompetenciaList());
                    }

                    @Override
                    public void onError() {
                        Log.d(TAG, "onError");
                        showImportantMessage(res.getString(R.string.createrubbid_error_getting_competencias));
                    }
                }
        );
    }

    private void showCompetenciaListChooser(List<CompetenciaUi> competenciaList) {
        if (competenciaList==null||competenciaList.isEmpty()) {
            showImportantMessage(res.getString(R.string.createrubbid_error_competencias_empty));
            return;
        }
        this.competenciaList = competenciaList;
        getCamposAccionPadres(competenciaList);
        hideProgress();
        //showIndicadorDialogChooser(competenciaList);
    }

    private void showIndicadorDialogChooser(List<CompetenciaUi> competenciaList) {
        if (view != null) {
            view.showIndicadorChooser(competenciaList);
        }
    }

    private List<TipoNotaUi> tipoNotaList;



    private void showTipoNotaSelected(TipoNotaUi tipoNota) {
        Log.d(TAG, "showTipoNotaSelected");
        if (view != null) view.showTipoNotaSelected(tipoNota);
    }


    @Override
    public void onIndicadorListOk(List<CompetenciaUi> competenciaList) {
        showCampoAccionTitle(getIndicadoresSelected(competenciaList));
        //this.competenciaList = competenciaList;
        //getCamposAccionPadres(competenciaList);
        for (int i1 = 0; i1 < this.competenciaList.size(); i1 ++){
            CompetenciaUi competenciaUiAntes = this.competenciaList.get(i1);
            CompetenciaUi competenciaUiActual = competenciaList.get(i1);
            competenciaUiAntes.setSelected(competenciaUiActual.isSelected());

            for (int i2 = 0; i2 < competenciaUiAntes.getCapacidadList().size(); i2 ++){
                CapacidadUi capacidadUiAntes = competenciaUiAntes.getCapacidadList().get(i2);
                CapacidadUi capacidadUiActual = competenciaUiActual.getCapacidadList().get(i2);
                capacidadUiAntes.setSelected(capacidadUiActual.isSelected());

                for (int i3 = 0; i3 < capacidadUiAntes.getIndicadorList().size(); i3 ++){
                    IndicadorUi indicadorUiAntes = capacidadUiAntes.getIndicadorList().get(i3);
                    IndicadorUi indicadorUiActual = capacidadUiActual.getIndicadorList().get(i3);
                    indicadorUiAntes.setSelected(indicadorUiActual.isSelected());
                    indicadorUiAntes.setChecked(indicadorUiActual.isChecked());

                    for (int i4 = 0; i4 < indicadorUiAntes.getCampoAccionList().size(); i4 ++){
                        CampoAccionUi campoAccionUiAntes = indicadorUiAntes.getCampoAccionList().get(i4);
                        CampoAccionUi campoAccionUiActual = indicadorUiActual.getCampoAccionList().get(i4);
                        campoAccionUiAntes.setChecked(campoAccionUiActual.isChecked());

                    }

                }

            }
        }

        showCompetenciaList(competenciaList);
        showTableview(false);

    }

    private List<IndicadorUi> getIndicadoresSelected(List<CompetenciaUi> competenciaList) {
        List<IndicadorUi> indicadorListSelected = new ArrayList<>();
        if(competenciaList!=null){
            for (CompetenciaUi competencia :
                    competenciaList) {
                if (competencia == null) continue;
                for (CapacidadUi capacidad :
                        competencia.getCapacidadList()) {
                    if (capacidad == null) continue;
                    for (IndicadorUi indicador :
                            capacidad.getIndicadorList()) {
                        if (indicador == null) continue;
                        if (indicador.isChecked()) {
                            Log.d(TAG, "indicador selected title: " + indicador.getTitulo());
                            indicador.setCapacidadOwner(capacidad);
                            indicador.setCompetenciaOwner(competencia);
                            indicadorListSelected.add(indicador);
                            capacidad.setSelected(true);
                            competencia.setSelected(true);
                        } else {
                            for (CampoAccionUi itemCampoAccionUi : indicador.getCampoAccionList()) {
                                itemCampoAccionUi.setChecked(false);
                            }
                        }
                    }
                }
            }
        }
        return indicadorListSelected;
    }


    @Override
    public void onIndicadorListCancel() {
        Log.d(TAG, "onIndicadorListCancel");
    }


    private boolean tipoEvaluacionBtnClicked;
    private boolean formaEvaluacionBtnClicked;

    public void onBtnTipoEvaluacionClicked() {
        Log.d(TAG, "onBtnTipoEvaluacionClicked");
        tipoEvaluacionBtnClicked = true;
        formaEvaluacionBtnClicked = false;
        showTipoEvaluacionList(tipoEvaluacionList);
    }

    @Override
    public void onBtnFormaEvaluacionClicked() {
        Log.d(TAG, "onBtnFormaEvaluacionClicked");
        tipoEvaluacionBtnClicked = false;
        formaEvaluacionBtnClicked = true;
        showFormaEvaluacionList(formaEvaluacionList);
    }

    @Override
    public void onBtnTipoNotaClicked() {
        Log.d(TAG, "onBtnTipoNotaClicked");
        tipoNivelBtnClicked = false;
        tipoNotaBtnClicked = true;
        showTipoNotaSingleChooserCabecera();
    }

    private void showTipoNotaSingleChooserCabecera() {
        if (view != null) {
            view.hideProgress();
            view.showTipoNotaSingleChooserCabecera(res.getString(R.string.createrubbid_tiponota_dialog_title), null, -1, idProgramaEducativo);
        }
    }

    @Override
    public void onBtnEscalaClicked() {
        Log.d(TAG, "onBtnEscalaClicked");
        showEscalaEvaluacionList(escalaEvaluacionList);
    }

    @Override
    public void onBtnCompetenciaListClicked() {
        Log.d(TAG, "onBtnCompetenciaListClicked");
        if (competenciaList != null) {
            showIndicadorDialogChooser(competenciaList);
        }
    }


    @Override
    public void onBtnCampoAccionListClicked() {
        Log.d(TAG, "onBtnCampoAccionListClicked");
        showCampoAccionChooser(getIndicadoresSelected(competenciaList));
    }

    @Override
    public void onBtnBuscarCompetenciaListClicked() {
        showBuscarCampoAccion(campoAccionUiList);
    }

    @Override
    public void onBtnEstrategiaEvaluacionClicked() {

        if(view== null)return;
        int position=0;
        if(estrategiaEvalUiSelected!=null){
            position = estrategiaEvalUis.indexOf(estrategiaEvalUiSelected);
        }
        if(view!=null)view.showListSingleChooser("Estrategia de evaluación", new ArrayList<Object>(estrategiaEvalUis), position);

//        if(view!=null)view.showDialogEstrategiasEvaluacion(estrategiaEvalUis);
    }

    @Override
    public void onTextChangedEditarAlias(String texto) {
        if(complejo){
            String titulo = (TextUtils.isEmpty(texto) ?  "":  ""+texto+" " ) + (estrategiaEvalUiSelected==null ?  "": estrategiaEvalUiSelected.getEstrategia());
            if(view!=null)view.showTituloEstrategiaSelected(titulo);
        }
    }




    private void getTituloRubrica(String rubricaKeyEdit) {
      estrategiaEvalUiSelected = getTituloRubrica.execute(rubricaKeyEdit);
      this.estrategiaId=estrategiaEvalUiSelected.getEstrategiaId();
      this.rubricaTitle=estrategiaEvalUiSelected.getTituloRubro();
      this.rubricaAlias=estrategiaEvalUiSelected.getDescripcionRubro();

        if(view!=null)view.setTitulo(rubricaTitle);
        if(view!=null)view.setSubTitulo(rubricaAlias);
    }

    private void getCamposAccionPadres(List<CompetenciaUi> competenciaList){
        campoAccionUiList.clear();
        List<CampoAccionUi> campotematicoUipadresList = new ArrayList<>();
        for (CompetenciaUi itemCompetenciaUi : competenciaList) {
            for (CapacidadUi itemCapacidadUi : itemCompetenciaUi.getCapacidadList()) {
                for (IndicadorUi itemIndicadorUi : itemCapacidadUi.getIndicadorList()) {
                    for (CampoAccionUi itemCampoAccionUi : itemIndicadorUi.getCampoAccionList()) {

                        if(itemCampoAccionUi.getTipo() == CampoAccionUi.Tipo.PARENT){

                            int posicion = campotematicoUipadresList.indexOf(itemCampoAccionUi);
                            if(posicion == -1){
                                campotematicoUipadresList.add(itemCampoAccionUi);
                                itemCampoAccionUi.setCapacidadUi(new CapacidadUi());
                                itemCampoAccionUi.setCompetenciaUi(new CompetenciaUi());
                                itemCampoAccionUi.setIndicadorUi(new IndicadorUi());
                            }else {
                                CampoAccionUi campoAccionUi = campotematicoUipadresList.get(posicion);
                                List<CampoAccionUi> campotematicoUiList = campoAccionUi.getCampoAccionUiList();
                                if(campotematicoUiList == null){
                                    campotematicoUiList = new ArrayList<>();
                                }
                                campotematicoUiList.addAll(itemCampoAccionUi.getCampoAccionUiList());

                            }
                        }else if(itemCampoAccionUi.getTipo() == CampoAccionUi.Tipo.DEFAULD){

                            campotematicoUipadresList.add(itemCampoAccionUi);
                        }
                    }
                }
            }
        }
        Log.d(TAG, "campotematicoUipadresList "+ campotematicoUipadresList.size());
        campoAccionUiList.addAll(campotematicoUipadresList);
    }

    private void showCampoAccionChooser(List<IndicadorUi> indicadorList) {
        if (view != null) {
            view.showCampoAccionChooser( indicadorList);
        }
    }


    private void showTipoNotaSingleChooser() {
        if (view != null) {
            view.hideProgress();
            view.showListTipoNotaSingleChooser(res.getString(R.string.createrubbid_tiponota_dialog_title), null, -1, idProgramaEducativo);
        }
    }


    private List<CompetenciaUi> competenciaList = new ArrayList<>();


    private TipoNotaUi tipoNotaSelected;
    private TipoNotaUi tipoNivelSelected;
    private EscalaEvaluacionUi escalaSelected;
    private TipoUi tipoEvaluacionSelected;
    private TipoUi formaEvaluacionSelected;

    @Override
    public void onSingleItemSelected(Object itemSelected, int selectedPosition) {
     //   Log.d(TAG, "onSingleItemSelected: " + itemSelected);
        if (itemSelected instanceof TipoNotaUi) {
            showProgress();
            /*if (tipoNotaBtnClicked) {
                tipoNotaSelected = (TipoNotaUi) itemSelected;
                for (TipoNotaUi tipoNota :
                        tipoNotaList) {
                    if (tipoNota.getTitle().equals(tipoNotaSelected.getTitle())) {
                        tipoNotaSelected = tipoNota;
                        break;
                    }
                }

                if (tipoNotaSelected == null) {
                    showImportantMessage(res.getString(R.string.unknown_error));
                    return;
                }

                showTipoNotaSelected(tipoNotaSelected);

            } else*/
            /*if (tipoNivelBtnClicked) {
                tipoNivelSelected = (TipoNotaUi) itemSelected;
                for (TipoNotaUi tipoNota :
                        tipoNotaList) {
                    if (tipoNota.getTitle().equals(tipoNivelSelected.getTitle())) {
                        tipoNivelSelected = tipoNota;
                        break;
                    }
                }

                if (tipoNivelSelected == null) {
                    showImportantMessage(res.getString(R.string.unknown_error));
                    return;
                }
                showTipoNivelSelected(tipoNivelSelected);
                showTableview();
            }*/
        } else if (itemSelected instanceof EscalaEvaluacionUi) {
            escalaSelected = (EscalaEvaluacionUi) itemSelected;
            for (EscalaEvaluacionUi escalaEvaluacionUi :
                    escalaEvaluacionList) {
                if (escalaEvaluacionUi.getNombre().equals(escalaSelected.getNombre())) {
                    escalaSelected = escalaEvaluacionUi;
                    break;
                }
            }

            if (escalaSelected == null) {
                showImportantMessage(res.getString(R.string.unknown_error));
                return;
            }
            showEscalaSelected(escalaSelected);

        } else if (itemSelected instanceof TipoUi) {
            if (tipoEvaluacionBtnClicked) {
                tipoEvaluacionSelected = (TipoUi) itemSelected;
                for (TipoUi tipoUi :
                        tipoEvaluacionList) {
                    if (tipoUi.getTitle().equals(tipoEvaluacionSelected.getTitle())) {
                        tipoEvaluacionSelected = tipoUi;
                        break;
                    }
                }

                if (tipoEvaluacionSelected == null) {
                    showImportantMessage(res.getString(R.string.unknown_error));
                    return;
                }
                showTipoEvaluacionSelected(tipoEvaluacionSelected);
                showPreview();
            }


            if (formaEvaluacionBtnClicked) {
                formaEvaluacionSelected = (TipoUi) itemSelected;
                for (TipoUi tipoUi :
                        formaEvaluacionList) {
                    if (tipoUi.getTitle().equals(formaEvaluacionSelected.getTitle())) {
                        formaEvaluacionSelected = tipoUi;
                        break;
                    }
                }

                if (formaEvaluacionSelected == null) {
                    showImportantMessage(res.getString(R.string.unknown_error));
                    return;
                }
                showFormaEvaluacionSelected(formaEvaluacionSelected);
                showPreview();
            }

        }
        else if (itemSelected instanceof EstrategiaEvalUi) {
            EstrategiaEvalUi estrategiaEvalUi= (EstrategiaEvalUi)itemSelected;
            Log.d(TAG, "onSingleItemSelected "+ estrategiaEvalUi.getEstrategia()+ view);
            this.estrategiaEvalUiSelected= estrategiaEvalUi;
            String subtitulo = null;
            if(view!=null)subtitulo = view.getEdtAlias();
            String titulo = (TextUtils.isEmpty(subtitulo) ?  "":  ""+subtitulo+" " ) + estrategiaEvalUiSelected.getEstrategia();
            if(view!=null)view.showTituloEstrategiaSelected(titulo);
        }

        /*
        CompetenciaUi competenciaSelected = null;
        for (CompetenciaUi competencia :
                competenciaList) {
            if (competencia.getTitle().equals(singleItem)) {
                competenciaSelected = competencia;
                break;
            }
        }

        if (competenciaSelected == null) {
            showImportantMessage(res.getString(R.string.unknown_error));
            return;
        }*/

    }

    @Override
    public void onCLickAcceptButtom() {
            if (view!=null)view.onAcceptButtom();
    }

    private void showFormaEvaluacionSelected(TipoUi formaEvaluacionSelected) {
        if (view != null && formaEvaluacionSelected != null) {
            view.showFormaEvaluacionSelected(formaEvaluacionSelected.getTitle());
        }
    }

    private void showTipoEvaluacionSelected(TipoUi tipoEvaluacionSelected) {
        if (view != null && tipoEvaluacionSelected != null) {
            view.showTipoEvaluacionSelected(tipoEvaluacionSelected.getTitle());
        }
    }

    private void showEscalaSelected(EscalaEvaluacionUi escalaSelected) {
        if (view != null && escalaSelected != null) {
            view.showEscalaSelected(escalaSelected.getNombre());
        }
    }

    private void showTipoNivelSelected(TipoNotaUi tipoNota) {
        if (view != null) {
            view.showTipoNivelSelected(tipoNota);
            tipoNotaUi = tipoNota;
        }
    }

    public static final int HEADERlIST_SIZE = 10;
    public static final int ROWlIST_SIZE = 10;

    public static int[] getPercentParts(int total, int parts) {
        if (parts == 0) return null;
        int[] percentParts = new int[parts];
        if (parts == 1) {
            percentParts[0] = 100;
            return percentParts;
        }

        int averagePart = total / parts;
        int rest = (averagePart % 5);
        if (rest != 0) {
            averagePart = averagePart - rest;
        }
        int totalParts = averagePart * parts;
        if (totalParts == total) {
            for (int i = 0; i < parts; i++) {
                percentParts[i] = averagePart;
            }
            return percentParts;
        }

        int finalPart = averagePart + (total - totalParts);
        for (int i = 0; i < parts; i++) {
            if (i == (parts - 1)) {
                percentParts[i] = finalPart;
            } else {
                percentParts[i] = averagePart;
            }
        }
        return percentParts;
    }

    public static int[] getPercentPartsV2(int totalPeso, int cantidad) {
        if (cantidad == 0) return null;
        int[] percentParts = new int[cantidad];

        int subtotalPeso =  (int) totalPeso/cantidad;
        int diferencia = totalPeso - (subtotalPeso * cantidad);

        for (int i = 0; i < cantidad; i++) {
            percentParts[i]=subtotalPeso;
        }

        for (int i = 0; i < diferencia; i++) {
            percentParts[i]+=1;
        }

        return percentParts;
    }

    private List<ColumnHeader> headerList;
    private List<RowHeader> rowHeaderList;
    private List<List<Cell>> bodyList = new ArrayList<>();

    private void showTableview(boolean changeValorTipoNota) {
        List<Cell> columnHeaderList = new ArrayList<>();
        List<Cell> rowHeaderList = new ArrayList<>();
        List<List<Cell>> cells = new ArrayList<>();
        List<IndicadorUi> indicadorList = getIndicadoresSelected(competenciaList);
        List<ValorTipoNotaUi> valorTipoNotaList = new ArrayList<>();
        if (tipoNivelSelected != null) valorTipoNotaList = tipoNivelSelected.getValorTipoNotaList();
       //for(ValorTipoNotaUi v: valorTipoNotaList) Log.d(TAG, "criterioEvaluacionUi " +v.getTitle()+ " / "+ v.getIcono() +" / " +v.getTipoNotaUi().getTitle());
        int indicadorListSize = indicadorList.size();
        //if (indicadorListSize == 0) return;

        IndicadorUi indicadorUi = new IndicadorUi();
        indicadorUi.setTitulo("%");
        columnHeaderList.add(indicadorUi);
        columnHeaderList.addAll(valorTipoNotaList);
        rowHeaderList.addAll(indicadorList);
        int[] percentParts = getPercentPartsV2(100, indicadorListSize);

        int i = 0;
        for (IndicadorUi itemIndicadorUi : indicadorList) {
            List<Cell> cells1 = new ArrayList<>();
            int percentPart = 0;
            if(percentParts!=null && percentParts.length > i) percentPart=percentParts[i];
            itemIndicadorUi.setPeso(String.valueOf(percentPart));
            cells1.add(itemIndicadorUi);
            List<CriterioEvaluacionUi> criterioEvaluacionUiList = itemIndicadorUi.getCriterioEvaluacionUiList();
            if(rubricaKeyEdit!=null){
                for (CriterioEvaluacionUi criterioEvaluacionUi : criterioEvaluacionUiList){
                    for (ValorTipoNotaUi valorTipoNotaUi : valorTipoNotaList){
                        if(valorTipoNotaUi.getId().equals(criterioEvaluacionUi.getValorTipoNotaUi().getId())){
                            criterioEvaluacionUi.setValorTipoNotaUi(valorTipoNotaUi);
                            break;
                        }
                    }
                    if(criterioEvaluacionUi.getValorTipoNotaUi().getTipoNotaUi()!=null){
                        cells1.add(criterioEvaluacionUi);
                    }
                }

            }else {
                if(changeValorTipoNota||criterioEvaluacionUiList==null){
                    criterioEvaluacionUiList = new ArrayList<>();
                    for (ValorTipoNotaUi itemValorTipoNotaUi : valorTipoNotaList) {
                        CriterioEvaluacionUi criterioEvaluacionUi = new CriterioEvaluacionUi();
                        criterioEvaluacionUi.setKey(IdGenerator.generateId());
                        criterioEvaluacionUi.setIndicadorUi(itemIndicadorUi);
                        criterioEvaluacionUi.setValorTipoNotaUi(itemValorTipoNotaUi);
                        criterioEvaluacionUiList.add(criterioEvaluacionUi);
                        cells1.add(criterioEvaluacionUi);
                    }
                    itemIndicadorUi.setCriterioEvaluacionUiList(criterioEvaluacionUiList);
                }else {
                    cells1.addAll(criterioEvaluacionUiList);
                }
            }

            cells.add(cells1);
            i++;
        }


        try {
            this.headerList = (List<ColumnHeader>) (Object) columnHeaderList;
            this.rowHeaderList = (List<RowHeader>) (Object) rowHeaderList;
        } catch (Exception e) {
            limpiarTableView();
        }
        this.bodyList = cells;

        if (rowHeaderList.size() == 0) {
            //limpiarTableView();
        }
        setTableview(this.headerList, this.rowHeaderList, cells);
        showPreview();



    }

    private CriterioEvaluacionUi getInstanceCriterioEvaluacionUi(CriterioEvaluacionUi criterioEvaluacionUi) {
        CriterioEvaluacionUi result = null;
        try {
            for(List<Cell> cell: bodyList){
                List<CriterioEvaluacionUi> campoAccionUiList = (List<CriterioEvaluacionUi>)(Object) cell;
                int posicion = campoAccionUiList.indexOf(criterioEvaluacionUi);
                if(posicion!=-1){
                    result = campoAccionUiList.get(posicion);
                    break;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

    private void limpiarTableView() {
        this.headerList = new ArrayList<>();
        this.rowHeaderList = new ArrayList<>();
        this.bodyList = new ArrayList<>();

    }

    private void showCampoAccionTitle(List<IndicadorUi> indicadorList) {
        List<CampoAccionUi> campoAccionList = new ArrayList<>();
        Log.d(TAG, "indicadorList size: " + indicadorList.size());
        for (IndicadorUi indicador :
                indicadorList) {
            List<CampoAccionUi> campoAccionUiList = indicador.getCampoAccionList();
            Log.d(TAG, "campoAccionUiList  size: " + campoAccionUiList.size());
            for (CampoAccionUi campoAccion :
                    campoAccionUiList) {
                if (campoAccion.isChecked()) {
                    campoAccionList.add(campoAccion);
                }
            }
        }
        /*Log.d(TAG, "campoAccionList size: " + campoAccionList.size());
        StringBuilder campoAccionTitle = new StringBuilder();
        for (int i = 0; i < campoAccionList.size(); i++) {
            CampoAccionUi campoAccion = campoAccionList.get(i);
            if (i != 0) {
                campoAccionTitle.append(", ");
            }
            campoAccionTitle.append(campoAccion.getTitulo());
        }*/
        showCampoAccionList(campoAccionList);
        this.selectCampoAccionList = campoAccionList;
    }




    private void showCampoAccionList(List<CampoAccionUi> campoAccionList) {
        if(campoAccionList==null||campoAccionList.isEmpty()){
            if (view != null) view.hideCampoAccionList();
        }else {
            if (view != null) view.showCampoAccionList(campoAccionList);
        }


    }


    private void showCompetenciaList(List<CompetenciaUi> competenciaList) {

        HashSet<CompetenciaUi> competenciaUiHashSet = new LinkedHashSet<>();
        if(competenciaList!=null){
            for (CompetenciaUi competencia : competenciaList) {
                if (competencia == null) continue;
                for (CapacidadUi capacidad : competencia.getCapacidadList()) {
                    if (capacidad == null) continue;
                    for (IndicadorUi indicador : capacidad.getIndicadorList()) {
                        if (indicador == null) continue;
                        if (indicador.isChecked()||indicador.isSelected()) {
                            competenciaUiHashSet.add(competencia);
                        }
                    }
                }
            }
        }

        if(competenciaUiHashSet.isEmpty()){
            if (view != null) view.hideCompetenciaList();
        }else {
            if (view != null) view.showCompetenciaList(new ArrayList<CompetenciaUi>(competenciaUiHashSet));
        }

    }

    @Override
    public void onCampoTematicoCancel() {

    }

    private int countIndicadorList;

    @Override
    public void onBtnCreateClicked() {
        Log.d(TAG, "onBtnCreateClicked");
        //Validaciones
        rubricaTitle = getEdtRubrica();
        rubricaAlias = getEdtAlias();

        if(complejo){
            if(estrategiaEvalUiSelected==null){
                showImportantMessage("Seleccione un Tipo de Estrategia");
                return;
            }
        }else {
            if (TextUtils.isEmpty(rubricaTitle)) {
                showImportantMessage(res.getString(R.string.createrubbid_error_rubricatitle_empty));
                return;
            }
        }


        /*if (TextUtils.isEmpty(rubricaAlias)) {
            showImportantMessage(res.getString(R.string.createrubbid_error_rubricaalias_empty));
            return;
        }*/

        if (tipoEvaluacionSelected == null) {
            showImportantMessage(res.getString(R.string.createrubbid_error_tipoevaluacion_empty));
            return;
        }
        if (formaEvaluacionSelected == null) {
            showImportantMessage(res.getString(R.string.createrubbid_error_formaevaluacion_empty));
            return;
        }
        if (tipoNotaSelected == null) {
            showImportantMessage(res.getString(R.string.createrubbid_error_tiponota_empty));
            return;
        }
        if (selectCampoAccionList.size() == 0) {
            showImportantMessage(res.getString(R.string.createrubbid_error_campoaccion_empty));
            return;
        }

        if (tipoNivelSelected == null) {
            showImportantMessage(res.getString(R.string.createrubbid_error_tiponivel_empty));
            return;
        }

        List<IndicadorUi> indicadorListSelected = getIndicadoresSelected(competenciaList);

        if (indicadorListSelected.size() < 1) {
            showImportantMessage(res.getString(R.string.createrubbid_error_indicadores_notselected));
            return;
        }

        countIndicadorList = indicadorListSelected.size();

        List<ValorTipoNotaUi> valorTipoNotaList = tipoNivelSelected.getValorTipoNotaList();
        if (valorTipoNotaList.size() < 1) {
            showImportantMessage(res.getString(R.string.createrubbid_error_tiponivel_valortiponotalist_empty));
            return;
        }

        if (!validarPeso(this.bodyList)) {
            showImportantMessage(res.getString(R.string.createrubbid_error_peso_inidacadores));
            return;
        }

        createRubBid.execute(new CreateRubBid.RequestValues(
                rubricaKeyEdit,
                rubricaTitle,
                rubricaAlias,
                tipoEvaluacionSelected,
                formaEvaluacionSelected,
                tipoNotaSelected,
                escalaSelected,
                tipoNivelSelected,
                indicadorListSelected,
                valorTipoNotaList,
                bodyList,
                calendarioPeriodoId,
                silaboEventoId,
                0,
                cargaCursoId,
                cursoId,
                rubroEvalResultadoId,
                sesionAprendizajeId,
                selectCampoAccionList,
                tareaId,
                estrategiaEvalUiSelected==null ? 0 : estrategiaEvalUiSelected.getEstrategiaId()
        ), new UseCaseSincrono.Callback<CreateRubBid.ResponseValue>() {
            @Override
            public void onResponse(boolean success, CreateRubBid.ResponseValue value) {
                if(success){
                    //   showFinalMessage(res.getString(R.string.global_message_sucess));
                    if (view != null) view.onSucess(value.getRubroEvaluacionResultadoId(), countIndicadorList, idProgramaEducativo);
                    //showFinalMessage(res.getString(R.string.global_message_sucess));
                    //view.showMessage(res.getString(R.string.global_message_sucess));
                }else {
                    showImportantMessage(res.getString(R.string.unknown_error));
                }
            }
        });
    }


    @Override
    public void onSingleItemSelectedTipoNota(String tipoNotaId) {
        showProgress();
        if (!TextUtils.isEmpty(tipoNotaId)) {
            Log.d(getTag(), "tipoNotaId: " + tipoNotaId);
            getTipoNotaGlobal(tipoNotaId);
        } else {
            tipoNotaSelected = null;
            showImportantMessage(res.getString(R.string.unknown_error));
        }
    }

    @Override
    public void onSelectBuscarCamosAccion(List<CampoAccionUi> campoAccionUiListPadres) {
        //Este metodo funcion si la lista que retorna es la misma que se envia
        Log.d(getTag(), "campoAccionUiList size: " + campoAccionUiListPadres.size());
        List<CampoAccionUi> campoAccionUiListAntiguo = new ArrayList<>();
        for (CampoAccionUi campoAccionUi:this.campoAccionUiList){
            campoAccionUiListAntiguo.add(campoAccionUi);
            if(campoAccionUi.getCampoAccionUiList()!= null)campoAccionUiListAntiguo.addAll(campoAccionUi.getCampoAccionUiList());
        }

        List<CampoAccionUi> campoAccionUiListNuevo = new ArrayList<>();
        for (CampoAccionUi campoAccionUi:campoAccionUiListPadres){
            campoAccionUiListNuevo.add(campoAccionUi);
            if(campoAccionUi.getCampoAccionUiList()!= null)campoAccionUiListNuevo.addAll(campoAccionUi.getCampoAccionUiList());
        }

        for (int i = 0; i < campoAccionUiListAntiguo.size(); i ++){
            CampoAccionUi campoAccionUiUiAntes = campoAccionUiListAntiguo.get(i);
            CampoAccionUi campoAccionUiActual = campoAccionUiListNuevo.get(i);
            campoAccionUiUiAntes.setChecked(campoAccionUiActual.isChecked());
            actualizarSeleccionCampoAccion(campoAccionUiUiAntes);
        }


        showCampoAccionTitle(getIndicadoresSelected(competenciaList));
        showCompetenciaList(competenciaList);
        showTableview(false);


    }

    @Override
    public void onSelectAcetarEditarRubroDetalle(IndicadorUi indicadorUi) {
        List<IndicadorUi> indicadorList = getIndicadoresSelected(competenciaList);
        int poscion = indicadorList.indexOf(indicadorUi);
        if(poscion != -1){
            IndicadorUi instInidicador =  indicadorList.get(poscion);
            instInidicador.setTituloRubro(indicadorUi.getTituloRubro());
            instInidicador.setSubTituloRubro(indicadorUi.getSubTituloRubro());
            instInidicador.setCampoAccionList(indicadorUi.getCampoAccionList());
        }

        for(IndicadorUi itemIndicadorUi: indicadorList){
            if(itemIndicadorUi.getIndicadorId()!=indicadorUi.getIndicadorId())continue;
            if(itemIndicadorUi.getCriterioEvaluacionUiList()==null)continue;
            for(CriterioEvaluacionUi criterioEvaluacionUi: itemIndicadorUi.getCriterioEvaluacionUiList()){
                for (CriterioEvaluacionUi newCriterioEvaluacionUi: indicadorUi.getCriterioEvaluacionUiList()){
                    if(criterioEvaluacionUi.getKey().equals(newCriterioEvaluacionUi.getKey())){
                        criterioEvaluacionUi.setTitulo(newCriterioEvaluacionUi.getTitulo());
                        break;
                    }
                }
            }
        }


        showCampoAccionTitle(indicadorList);
        showTableview(false);
        showPreview();
    }

    @Override
    public void onSelectCancelarEditarRubroDetalle() {

    }

    @Override
    public void sendList(IndicadorUi indicadorUi) {
        List<IndicadorUi> indicadorList = getIndicadoresSelected(competenciaList);
        for(IndicadorUi itemIndicadorUi: indicadorList){
            if(itemIndicadorUi.getIndicadorId()!=indicadorUi.getIndicadorId())continue;
            if(itemIndicadorUi.getCriterioEvaluacionUiList()==null)continue;
            for(CriterioEvaluacionUi criterioEvaluacionUi: itemIndicadorUi.getCriterioEvaluacionUiList()){
                for (CriterioEvaluacionUi newCriterioEvaluacionUi: indicadorUi.getCriterioEvaluacionUiList()){
                    if(criterioEvaluacionUi.getKey().equals(newCriterioEvaluacionUi.getKey())){
                        criterioEvaluacionUi.setTitulo(newCriterioEvaluacionUi.getTitulo());
                        break;
                    }
                }
            }
        }

        setTableview(headerList, rowHeaderList, bodyList);
        showPreview();
    }

    @Override
    public void setEstrategiaSelected(EstrategiaEvalUi estrategiaEvalUiselected) {
        this.estrategiaEvalUiSelected=estrategiaEvalUiselected;
        if(view!=null)view.showTituloEstrategiaSelected(estrategiaEvalUiSelected.getEstrategia());
    }

    @Override
    public void onClikInfoTipoNota() {
        if(tipoNotaSelected!=null)if(view!=null)view.showDialogInfoTipoNota("Promedio de logro", tipoNotaSelected);
    }

    private void setTableview(List<ColumnHeader> headerList, List<RowHeader> rowHeaderList, List<List<Cell>> bodyList) {

        if(rowHeaderList.isEmpty()){
            if(view!=null)view.showTxtTableEmprty();
        }else {
            if(view!=null)view.hideTxtTableEmprty();
        }

        if(view!=null)view.showTableview(headerList, rowHeaderList, bodyList, this);

    }

    private void showPreview(){
        HashSet<Object> objectList = new LinkedHashSet<>();
        Date date = new Date();
        for (CompetenciaUi competenciaUi : competenciaList){
            CompetenciaUi competenciaUiPreview = new CompetenciaUi();
            competenciaUiPreview.setId(competenciaUi.getId());
            competenciaUiPreview.setTipoId(competenciaUi.getTipoId());
            competenciaUiPreview.setTitle(competenciaUi.getTitle());
            for (CapacidadUi capacidadUi: competenciaUi.getCapacidadList()){
                CapacidadUi capacidadUiPreview = new CapacidadUi();
                capacidadUiPreview.setId(capacidadUi.getId());
                capacidadUiPreview.setTitle(capacidadUi.getTitle());
                long cantidad = capacidadUi.getCantidadRubros();
                List<IndicadorUi> indicadorUiListPreview = new ArrayList<>();
                for (IndicadorUi indicadorUi: capacidadUi.getIndicadorList()){
                    if(indicadorUi.isChecked()){
                        cantidad++;
                        objectList.add(competenciaUiPreview);
                        objectList.add(capacidadUiPreview);
                        IndicadorUi indicadorUiPreview = new IndicadorUi();
                        indicadorUiPreview.setIndicadorId(indicadorUi.getIndicadorId());
                        indicadorUiPreview.setTituloRubrica(getEdtRubrica());
                        indicadorUiPreview.setTituloRubro(indicadorUi.getTituloRubro());
                        indicadorUiPreview.setCompetenciaOwner(competenciaUiPreview);
                        indicadorUiPreview.setCapacidadOwner(capacidadUiPreview);
                        indicadorUiPreview.setFecha(Utils.f_fecha_letras(date.getTime()));
                        indicadorUiPreview.setUrl(indicadorUi.getUrl());
                        indicadorUiPreview.setTitulo(indicadorUi.getTitulo());
                        indicadorUiPreview.setMedia("0.0");
                        indicadorUiPreview.setDesviacionE("0.0");
                        if(tipoEvaluacionSelected!=null){
                            indicadorUiPreview.setTipoEvaluacion(tipoEvaluacionSelected.getTitle());
                        }else {
                            indicadorUiPreview.setTipoEvaluacion("?");
                        }
                        if(formaEvaluacionSelected!=null){
                            indicadorUiPreview.setFormEvaluacion(formaEvaluacionSelected.getTitle());
                        }else {
                            indicadorUiPreview.setFormEvaluacion("?");
                        }
                        if(!TextUtils.isEmpty(tareaId)){
                            indicadorUiPreview.setOrigen("Tarea");
                        } else if(sesionAprendizajeId==0){
                            indicadorUiPreview.setOrigen("Área");
                        }else {
                            indicadorUiPreview.setOrigen("Sesión");
                        }

                        indicadorUiPreview.setPosicion(String.valueOf(cantidad));
                        Log.d(TAG, "indicadorUi.getCampoAccionList(): " + indicadorUi.getCampoAccionList());
                        HashSet<CampoAccionUi>campoAccionUis = new LinkedHashSet<>();
                        for (CampoAccionUi campoAccionUi: indicadorUi.getCampoAccionList()){
                            if(campoAccionUi.isChecked()){
                                CampoAccionUi padre = campoAccionUi.getPadre();
                                if(padre!=null)campoAccionUis.add(padre);
                                campoAccionUis.add(campoAccionUi);
                            }
                        }
                        indicadorUiPreview.setCampoAccionList(new ArrayList<CampoAccionUi>(campoAccionUis));
                        indicadorUiListPreview.add(indicadorUiPreview);
                    }

                }
                capacidadUiPreview.setCantidadRubros(cantidad);
                capacidadUiPreview.setIndicadorList(indicadorUiListPreview);
            }

        }
        Log.d(TAG, "showPreview"+ objectList.size());
        if(view!=null)view.showPreview(new ArrayList<>(objectList));
    }

    private void actualizarSeleccionCampoAccion(CampoAccionUi campoAccionUi) {
        CompetenciaUi competenciaUi = campoAccionUi.getCompetenciaUi();
        CapacidadUi capacidadUi = campoAccionUi.getCapacidadUi();
        IndicadorUi indicadorUi = campoAccionUi.getIndicadorUi();

        if (campoAccionUi.isChecked()) {
            competenciaUi.setSelected(true);
            capacidadUi.setSelected(true);
            indicadorUi.setChecked(true);
        } else {
            int countSelectCampoAccion = 0;
            for (CampoAccionUi itemCampoAccionUi2 : indicadorUi.getCampoAccionList()) {
                if (itemCampoAccionUi2.isChecked()) ++countSelectCampoAccion;
            }
            if (countSelectCampoAccion == 0) indicadorUi.setChecked(false);

            int countSelectIndicadorUi = 0;
            for (IndicadorUi itemIndicadorUi : capacidadUi.getIndicadorList()) {
                if (itemIndicadorUi.isChecked()) ++countSelectIndicadorUi;
            }
            if (countSelectIndicadorUi == 0) capacidadUi.setSelected(false);


            int countSelectCapacidadUi = 0;
            for (CapacidadUi itemCapacidadUi : competenciaUi.getCapacidadList()) {
                if (itemCapacidadUi.isSelected()) ++countSelectCapacidadUi;
            }
            if (countSelectCapacidadUi == 0) competenciaUi.setSelected(false);

        }
    }


    private String getEdtRubrica() {
        if (view != null) return view.getEdtRubrica();

        return null;
    }

    private String getEdtAlias() {
        if (view != null) {
            return view.getEdtAlias();
        }
        return null;
    }

    private boolean tipoNotaBtnClicked;
    private boolean tipoNivelBtnClicked;

    @Override
    public void onBtnTipoNivelClicked() {
        Log.d(TAG, "onBtnTipoNivelClicked");
        tipoNivelBtnClicked = true;
        tipoNotaBtnClicked = false;
        showTipoNivelChooser(tipoNotaList);
    }

    @Override
    public void onTableTitleClicked() {
        Log.d(TAG, "onTableTitleClicked");
        if (competenciaList != null) {
            showIndicadorDialogChooser(competenciaList);
        }
    }

    @Override
    public void onBtnDetalleCampoAccionListClicked() {
        Log.d(TAG, "onBtnCampoAccionListClicked");
        showCampoAccionChooser(getIndicadoresSelected(competenciaList));
    }

    @Override
    public void onBtnDetalleCompetenciaListClicked() {
        Log.d(TAG, "onBtnCompetenciaListClicked");
        if (competenciaList != null) {
            showIndicadorDialogChooser(competenciaList);
        }
    }

    @Override
    public void onBtnDetalleBuscarCampoAccionListClicked() {
        showBuscarCampoAccion(campoAccionUiList);
    }


    private void showBuscarCampoAccion(List<CampoAccionUi> campoAccionUiList) {

        Log.d(TAG, "showBuscarCampoAccion " + " campoAccionUiList "+ campoAccionUiList.size());
        if(view!=null)view.showBuscarCampoAccion(campoAccionUiList);

    }

    private void showTipoNivelChooser(List<TipoNotaUi> tipoNivelList) {
        if (view!=null)view.showListTipoNotaSingleChooser(res.getString(R.string.fragment_create_rubbid_title_tiponivel), null, -1, idProgramaEducativo);
    }

    private void updateTableItem(int x, int y, Cell item) {
        Log.d(TAG, "updateTableItem");
        if (view != null) view.updateCellItem(x, y, item);
    }




    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder p_jCellView, int x, int y) {
        if (view == null) return;
        if (p_jCellView instanceof SelectorCellViewHolder) {
            SelectorCellViewHolder selectorCellViewHolder = (SelectorCellViewHolder) p_jCellView;
            Log.d("onCellItemCLick", selectorCellViewHolder.getCriterioEvaluacionUi().toString());
            List<IndicadorUi> indicadorSelectedList = getIndicadoresSelected(competenciaList);
            if (y >= indicadorSelectedList.size()) return;
            indicadorUi = indicadorSelectedList.get(y);
            List<Cell> list = new ArrayList<>();
            list = bodyList.get(y);
            boolean disabledCampoAccion = TextUtils.isEmpty(rubricaKeyEdit);
            showEditarRubroDetalle(indicadorUi, tipoNotaUi, list, disabledCampoAccion);
        } else if (p_jCellView instanceof PesoCellViewHolder) {
            PesoCellViewHolder pesoCellViewHolder = (PesoCellViewHolder) p_jCellView;
            if(TextUtils.isEmpty(rubricaKeyEdit))view.showCriterioEvaluacionItemDialog(pesoCellViewHolder.getIndicadorUi(), x, y);
        }
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int x) {
        Log.d(TAG, "onColumnHeaderClicked x: " + x);
        if(p_jColumnHeaderView instanceof PesoRowViewHolder){
            if(!disabledNivelLogroRubro) if(view!=null)view.onBtnTipoNivelClicked();
        }else if(p_jColumnHeaderView instanceof SelectorIconoRowViewHolder ||p_jColumnHeaderView instanceof SelectorValorRowViewHolder){
            if(tipoNotaSelected!=null)if(view!=null)view.showDialogInfoTipoNota("Nivel de logro", tipoNotaSelected);
        }
    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int x) {
        Log.d(TAG, "onColumnHeaderLongPressed x: " + x);
    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int y) {
        Log.d(TAG, "onRowHeaderClicked y: " + y);
        List<IndicadorUi> indicadorSelectedList = getIndicadoresSelected(competenciaList);
        if (y >= indicadorSelectedList.size()) return;
        indicadorUi = indicadorSelectedList.get(y);
        List<Cell> list = new ArrayList<>();
        list = bodyList.get(y);
        boolean disabledCampoAccion = TextUtils.isEmpty(rubricaKeyEdit);
        showEditarRubroDetalle(indicadorUi, tipoNotaUi, list, disabledCampoAccion);
    }

    private void showEditarRubroDetalle(IndicadorUi indicador, TipoNotaUi tipoNota, List<Cell> cellList, boolean disabledCampoAccion) {
        if(view!=null)view.showEditarRubroDetalle(indicador, tipoNota, cellList,disabledCampoAccion);
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int y) {
        Log.d(TAG, "onRowHeaderLongPressed y: " + y);

    }

    @Override
    public void onBtnNegativeClicked() {
        Log.d(TAG, "onBtnNegativeClicked");
    }

    @Override
    public void onBtnPostiveCriterioEvalaucion(CriterioEvaluacionUi criterioEvaluacionUi, int x, int y) {
        updateTableItem(x, y, criterioEvaluacionUi);
    }

    private void defaultSelectTipoEvaluacion(List<TipoUi> tipoEvaluacionList, int poscion) {
        if (view == null) return;
        try {
            if (tipoEvaluacionSelected == null) {
                tipoEvaluacionSelected = tipoEvaluacionList.get(poscion > tipoEvaluacionList.size()|| poscion==-1 ? 0 :poscion );
            }
            showTipoEvaluacionSelected(tipoEvaluacionSelected);
        } catch (Exception ignored) {
        }

    }

    private void defaultSelectFormaEvaluacion(List<TipoUi> formaEvaluacionList, int poscion) {
        if (view == null) return;
        try {
            if (formaEvaluacionSelected == null) {
                formaEvaluacionSelected = formaEvaluacionList.get(poscion > formaEvaluacionList.size()|| poscion==-1 ? 0 :poscion );
            }
            showFormaEvaluacionSelected(formaEvaluacionSelected);
        } catch (Exception ignored) {
        }

    }

    @Override
    public void onBtnNegativeClickedPesoIndicadorCell() {
        Log.d(TAG, "onBtnNegativeClickedPesoIndicadorCell");
    }

    @Override
    public void onBtnPostivePesoIndicadorCell(IndicadorUi indicadorUi, int x, int y) {
        updateTableItem(x, y, indicadorUi);
    }

    public boolean validarPeso(List<List<Cell>> bodyList) {
        try {
            int porcentaje = 0;
            for (List<Cell> cellList : bodyList) {
                Cell cell = cellList.get(0);
                IndicadorUi indicadorUi = (IndicadorUi) cell;
                porcentaje += Integer.parseInt(indicadorUi.getPeso());
                if(Integer.parseInt(indicadorUi.getPeso())==0)return false;
            }
            Log.d(TAG, "Peso: " + porcentaje);
            if (porcentaje == 100) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.d(TAG, "Exception: " + e);
            return false;
        }

    }



    private void getTipoNotaGlobalDefault(){
        List<TipoNotaUi.Tipo> tipoList = new ArrayList<>();
        tipoList.add(TipoNotaUi.Tipo.SELECTOR_ICONOS);
        tipoList.add(TipoNotaUi.Tipo.SELECTOR_VALORES);
        TipoNotaUi tipoNotaUi = getTipoNotaDefault.executeUseCase(new GetTipoNotaDefault.RequestValues(idProgramaEducativo,tipoList));

        if(tipoNotaUi!=null){
            getPrescion(tipoNotaUi);
            showTipoNotaSelected(tipoNotaUi);
            showTipoNivelSelected(tipoNotaUi);
            Log.e(TAG, "getTipoNota onSuccess");

            tipoNotaSelected = tipoNotaUi;
            tipoNivelSelected = tipoNotaUi;
            showTableview(false);
            if (tipoNotaBtnClicked) {
                tipoNotaSelected = tipoNotaUi;
                showTableview(false);
            } else if (tipoNivelBtnClicked) {
                tipoNivelSelected = tipoNotaUi;
                showTableview(true);
            }

            //getTipoNota(tipoNotaUi.getId(),inicio);
        }else {
            showImportantMessage(res.getString(R.string.createrubbid_error_getting_tiponota));
        }

    }

    private void getPrescion(TipoNotaUi tipoNotaUi) {
        if(tipoNotaUi.getValorTipoNotaList()!=null)
            for (ValorTipoNotaUi valorTipoNotaUi : tipoNotaUi.getValorTipoNotaList()){

                List<PrecisionUi> precisionUis = getNotaPresicion.execute(new GetNotaPresicion.RequestValues(
                        valorTipoNotaUi.getColor(),
                        (int)valorTipoNotaUi.getLimiteSuperior(),
                        (int)valorTipoNotaUi.getLimiteInferior(),
                        valorTipoNotaUi.isIncluidoLSuperior(),
                        valorTipoNotaUi.isIncluidoLInferior()));

                valorTipoNotaUi.setPrecisionList(precisionUis);
            }
    }


    private void getTipoNotaGlobal(String tipoNotaId){

        handler.execute(
                getTipoNota,
                new GetTipoNota.RequestValues(tipoNotaId),
                new UseCase.UseCaseCallback<GetTipoNota.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoNota.ResponseValue response) {
                        Log.e(TAG, "getTipoNota onSuccess");
                        getPrescion(response.getTipoNotaUi());
                        if (tipoNotaBtnClicked) {
                            showTipoNotaSelected(response.getTipoNotaUi());
                            tipoNotaSelected = response.getTipoNotaUi();
                            showTableview(false);
                        } else if (tipoNivelBtnClicked) {
                            showTipoNivelSelected(response.getTipoNotaUi());
                            tipoNivelSelected = response.getTipoNotaUi();
                            showTableview(true);
                        }
                    }

                    @Override
                    public void onError() {
                        Log.e(TAG, "getTipoNotas onError");
                        showImportantMessage(res.getString(R.string.createrubbid_error_getting_tiponota));
                    }
                }

        );

    }

    @Override
    public void onBackPressed() {
        if(view!=null)view.showFinalMessageAceptCancel("¿Seguro que quiere salir?", res.getString(com.consultoraestrategia.ss_crmeducativo.core2.R.string.msg_confirmacionTitlle));
    }



}
