package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera.ui.CrearRubroCabeceraActividad;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.dialogKeyBoard.DialogkeyBoardView;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetCapacidad;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetCriterioEval;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetEstrategiasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetFormaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetInidicador;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetTarea;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetTipoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetTipoNota;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.GetTipoNotaDefault;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase.SaveRubro;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ColorCondicionalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CriterioEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EstrategiaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.FormaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.PrecisionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.RubroProcesoWrapper;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.ui.CrearRubroView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by SCIEV on 11/10/2017.
 */

public class CrearRubroPresenterImpl extends BasePresenterImpl<CrearRubroView> implements CrearRubroPresenter {
    private String TAG = "CrearRubroPresenterImpl";
    private CrearRubroView view;
    private UseCaseHandler handler;
    private SaveRubro saveRubro;
    private GetTipoNota getTipoNota;
    private GetCriterioEval getcriterioEval;
    private GetInidicador getInidicador;
    private GetTipoEvaluacion getTipoEvaluacion;
    private CrearRubroEvalUi crearRubroEvalUi;
    private GetFormaEvaluacion getFormaEvaluacion;
    private int sesionAprendizajeId;
    private int indicadorId;
    private int silavoEventoId;
    private int capacidadId;
    private int calendarioPeriodoId;
    private int color_picker;
    private static final int COLOR_TEXTO = 0, COLOR_FONDO = 1;
    private ColorCondicionalUi colorCondUiSelectPiker;
    private ArrayList<Integer> campotematicoIds = new ArrayList<>();
    private int nivel;
    private List<TipoEvaluacionUi> tipoEvaluacionList = new ArrayList<>();
    private List<FormaEvaluacionUi> formaEvaluacionUiList;
    private int idProgramaEducativo;
    private IndicadorUi indicador;
    private String tareaId;
    private GetTarea getTarea;
    private RubroProcesoWrapper rubroProcesoUi;
    private GetEstrategiasEvaluacion getEstrategiasEvaluacion;
    private List<EstrategiaUi>estrategiaEvalUis = new ArrayList<>();
    private int cursoId;
    private EstrategiaUi estrategiaUiSelected;
    private GetNotaPresicion getNotaPresicion;
    private GetTipoNotaDefault getTipoNotaDefault;
    private GetCapacidad getCompetencia;
    private int lineasTextoDesempenio;
    private static final int maxLinTexDesempenio = 2;

    //atributo temporal;
    private boolean habilitado = false;
    private String subtitulo;
    private boolean verMasDesempenio;
    private CriterioEvalUi criterioEvalUiSelected;
    private DialogkeyBoardView dialogkeyBoardView;
    private List<ValorTipoNotaUi> valorTipoNOtaCriterios;

    public CrearRubroPresenterImpl(UseCaseHandler handler, Resources res, SaveRubro saveRubro, GetCriterioEval getcriterioEval, GetInidicador getInidicador, GetTipoEvaluacion getTipoEvaluacion, GetFormaEvaluacion getFormaEvaluacion, GetTipoNota getTipoNota, GetTarea getTarea, GetEstrategiasEvaluacion getEstrategiasEvaluacion, GetNotaPresicion getNotaPresicion, GetTipoNotaDefault getTipoNotaDefault, GetCapacidad getCompetencia) {
        super(handler, res);
        this.handler = handler;
        this.saveRubro = saveRubro;
        this.getcriterioEval = getcriterioEval;
        this.getInidicador = getInidicador;
        this.getTipoEvaluacion = getTipoEvaluacion;
        this.getFormaEvaluacion = getFormaEvaluacion;
        this.crearRubroEvalUi = new CrearRubroEvalUi();
        this.getTipoNota = getTipoNota;
        this.getTarea = getTarea;
        this.getEstrategiasEvaluacion=getEstrategiasEvaluacion;
        this.getNotaPresicion = getNotaPresicion;
        this.getTipoNotaDefault = getTipoNotaDefault;
        this.getCompetencia = getCompetencia;
    }
    Bundle extras;

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void attachView(CrearRubroView view) {
        this.view = view;
    }

    //region ciclo de vida
    @Override
    public void onCreate() {
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }

    //endregion ciclo de vida

    @Override
    public void onBackPressed() {
        cerraDialog();
    }

    @Override
    public void onClickCancelar() {
        cerraDialog();
    }

    @Override
    public void onClickAceptar(String titulo, String subtitulo, String valorDefecto, List<ValorTipoNotaUi> valorTipoNotaUiList) {
        if (view != null) {

            if (!habilitado && titulo.equals("")) {
                view.errorTitulo("Ingrese Título");
            }else if(habilitado && estrategiaUiSelected==null){
                view.errorTitulo("Seleccione un Tipo de Estrategia");
            } else if (crearRubroEvalUi.getTipoEvaluacionUi().getId() == 0) {
                view.mensajeToast("Seleccione una Tipo de Evaluación");
            } else if (TextUtils.isEmpty(crearRubroEvalUi.getTipoNotaUi().getId())) {
                view.mensajeToast("Seleccione un Tipo de Nota");
            } else if (crearRubroEvalUi.getFormaEvaluacionUi().getId() == 0) {
                view.mensajeToast("Seleccione una Forma de Evaluación");
            }else if(indicadorId == 0){
                view.mensajeToast("Seleccione un indicador");
            } else {


                crearRubroEvalUi.setTitulo(titulo);
                crearRubroEvalUi.setSubtitulo(subtitulo);
                crearRubroEvalUi.setValorDefecto(valorDefecto);
                crearRubroEvalUi.getTipoNotaUi().setValorTipoNotaUis(valorTipoNotaUiList);
                crearRubroEvalUi.setColorCondicionalUis(view.abstraerColoreCondicionales());
                crearRubroEvalUi.setSesionAprendizajeId(sesionAprendizajeId);
                crearRubroEvalUi.setSilavoEventoId(silavoEventoId);
                crearRubroEvalUi.setCalendarioPeriodoId(calendarioPeriodoId);
                crearRubroEvalUi.setCompetenciaId(capacidadId);
                crearRubroEvalUi.setIndicadorId(indicadorId);
                crearRubroEvalUi.setCampotematicoIds(campotematicoIds);
                crearRubroEvalUi.setTareaId(tareaId);
                if(estrategiaUiSelected!=null)crearRubroEvalUi.setEstrategiaEvalId(estrategiaUiSelected.getEstrategiaId());
                saveRubro();
            }
        }
    }

    @Override
    public void onSelectTipoNota(TipoNotaUi tipoNota) {
        /*this.crearRubroEvalUi.setTipoNotaUi(tipoNota);
        hideCriterioEval();
        if(tipoNota == null) return;
        if(tipoNota.getTipoId() != 0){
            showProgressCriterioEval();
            GetCriterioEval();
        }*/
    }

    @Override
    public void onClickItemCriterioEval(CriterioEvalUi criterioEvalUi, List<ValorTipoNotaUi> valorTipoNotaUi) {
        this.criterioEvalUiSelected = criterioEvalUi;
        if (view != null)view.showDialogKeyBoard(criterioEvalUi);
    }

    @Override
    public void editCriterioEval(List<ValorTipoNotaUi> valorTipoNotaUi) {
        showCriterioEval(valorTipoNotaUi);
    }

    @Override
    public void onClickSaveCriterioEval(ValorTipoNotaUi valorTipoNotaUi) {
        if (view != null) {
            int posicion = view.updateCriterio(valorTipoNotaUi);
            if (posicion == -1) {
                view.addCriterio(valorTipoNotaUi);
            }
            view.hideDialogCriterioEval();
        }
    }

    @Override
    public void onClickCloseCriterioEval() {
        if (view != null)view.hideDialogCriterioEval();
    }

    @Override
    public void setExtras(Bundle extras) {
        this.extras=extras;
        //EDITAR
        rubroProcesoUi =  RubroProcesoWrapper.getBundle(extras);
        //CREAR
        tareaId = extras.getString(CrearRubroCabeceraActividad.STR_TAREA_ID, null);
        sesionAprendizajeId = extras.getInt(CrearRubroCabeceraActividad.INT_SESION_APRENDIZAJE_ID,0);
        silavoEventoId = extras.getInt(CrearRubroCabeceraActividad.INT_SILAVO_EVENTO_ID,0);
        calendarioPeriodoId = extras.getInt(CrearRubroCabeceraActividad.INT_CALENDARIO_PERIODO_ID,0);
        capacidadId = extras.getInt(CrearRubroCabeceraActividad.INT_COMPETENCIA_ID,0);
        indicadorId = extras.getInt(CrearRubroCabeceraActividad.INT_INDICADOR_ID,0);
        campotematicoIds = extras.getIntegerArrayList(CrearRubroCabeceraActividad.ARRAYLIST_CAMPOTEMATICO_ID);
        idProgramaEducativo = extras.getInt(CrearRubroCabeceraActividad.INT_PROGRAMA_EDUCATIVO_ID,0);
        cursoId= extras.getInt(CrearRubroCabeceraActividad.CURSO_ID, 0);
        Log.d(TAG,  " / idProgramaEducativo " + idProgramaEducativo);
        Log.d(TAG,  " / indicadorId " + indicadorId);
        Log.d(TAG," / capacidadId " + capacidadId);
        Log.d(TAG,  " / silavoEventoId " + silavoEventoId);
        Log.d(TAG, " / sesionAprendizajeId " + sesionAprendizajeId);
        Log.d(TAG,  " / calendarioPeriodoId " + calendarioPeriodoId);
        Log.d(TAG,  " / cursoId " + cursoId);
        iniciar();


    }

    private void setDatos() {
        Log.d(TAG, "existe el rubroproceso");
        if(view!=null) view.setDatosRubro(rubroProcesoUi.getTitulo(), rubroProcesoUi.getSubtitulo());
    }



    private void iniciar(){

        if(indicadorId != 0) {
            if (view!=null)view.changeStateIndicador();
            if(view!=null)view.showVistaPrevia();
        }else {
            if(view!=null)view.hideVistaPrevia();
        }
        GetInidcador();
        getCompetencia();
        hideCriterioEval();
        GetTipoEvaluacion();
        GetFormaEvaluacion();
        GetTareas();
        getEstrategias();
        getTipoNotaDefault();
        getCompetencia();
        if(rubroProcesoUi.getRubroId()!=null){
            crearRubroEvalUi.setKey(rubroProcesoUi.getRubroId());
            subtitulo = rubroProcesoUi.getSubtitulo();
            setDatos();
        }
        habilitar();

    }

    private void getTipoNotaDefault() {
        TipoNotaUi tipoNotaUi = getTipoNotaDefault.executeUseCase(new GetTipoNotaDefault.RequestValues(idProgramaEducativo));
        if(tipoNotaUi!=null){
            getPrescion(tipoNotaUi);
            crearRubroEvalUi.setTipoNotaUi(tipoNotaUi);
            if(view!=null)view.showNivelLogro(tipoNotaUi);
            if (tipoNotaUi.getTipoId() != 0) {
                showProgressCriterioEval();
                GetCriterioEval();
            }
        }else {
            showImportantMessage(res.getString(R.string.unknown_error));
        }
    }

    private void habilitar() {
        habilitado = !estrategiaEvalUis.isEmpty();
        if(habilitado){
            if(view!=null)view.showInputEstrategia();
        } else{
            if(view!=null)view.hideInputEstrategia();
        }
    }

    private void getEstrategias() {
        GetEstrategiasEvaluacion.Response response= getEstrategiasEvaluacion.execute(new GetEstrategiasEvaluacion.Request(cursoId));
        estrategiaEvalUis.clear();
        estrategiaEvalUis.addAll(response.getEstrategiaEvalUis());
        if(rubroProcesoUi!=null){
            for(EstrategiaUi estrategiaUi:estrategiaEvalUis){
                if(estrategiaUi.getEstrategiaId()==rubroProcesoUi.getEstrategiaEvalId())
                    this.estrategiaUiSelected= estrategiaUi;
            }
        }
        Log.d(TAG, "estrategiaEvalUis "+estrategiaEvalUis.size());
    }

    private void GetTareas() {
        if (tareaId!=null) {
            handler.execute(
                    getTarea,
                    new GetTarea.RequestValues(tareaId),
                    new UseCase.UseCaseCallback<GetTarea.ResponseValue>() {
                        @Override
                        public void onSuccess(GetTarea.ResponseValue response) {
                            TareasUI tareasUI = response.getTareasUI();
                            if(tareasUI!=null){
                                if(habilitado){
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
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
        if (singleItem instanceof TipoEvaluacionUi) {
            TipoEvaluacionUi tipoEvaluacionUi = (TipoEvaluacionUi) singleItem;
            crearRubroEvalUi.setTipoEvaluacionUi(tipoEvaluacionUi);
            if (view == null) return;
            view.showInputTipoEvaluacion(tipoEvaluacionUi.getNombre());
        } else if (singleItem instanceof FormaEvaluacionUi) {
            FormaEvaluacionUi formaEvaluacionUi = (FormaEvaluacionUi) singleItem;
            crearRubroEvalUi.setFormaEvaluacionUi(formaEvaluacionUi);
            if (view == null) return;
            view.showInputFormaEvaluacion(formaEvaluacionUi.getNombre());
        }
        else if (singleItem instanceof EstrategiaUi)
        {

            EstrategiaUi estrategiaUi= (EstrategiaUi)singleItem;
            Log.d(TAG, "onSingleItemSelected "+ estrategiaUi.getEstrategia());
            estrategiaUiSelected=estrategiaUi;
            if (view == null) return;
            String titulo = (TextUtils.isEmpty(subtitulo) ?  "":  ""+subtitulo+" " ) + estrategiaUiSelected.getEstrategia();
            view.showTituloEstrategia(titulo);
        }
    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    public void onSelectTipoEvaluacion(TipoEvaluacionUi tipoEvaluacionUi) {
        //crearRubroEvalUi.setTipoEvaluacionUi(tipoEvaluacionUi);
    }

    @Override
    public void onClickAddColorCondicional() {
        if (view != null) {
            ColorCondicionalUi colorCondicionalUi = new ColorCondicionalUi();
            view.showColorCondicionalDialog(colorCondicionalUi);
        }
    }

    @Override
    public void onClickItemColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        if (view != null)view.showColorCondicionalDialog(colorCondicionalUi);
    }

    @Override
    public void onClickSaveItemColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        if (view != null) {
            int posicion = view.updateColorCondicional(colorCondicionalUi);
            if (posicion == -1) {
                view.addColorCondicional(colorCondicionalUi);
            }
            view.hideColorCondicionalDialog();
        }
    }

    @Override
    public void onClickCloseItemColorCondicional() {
        if (view != null)view.hideColorCondicionalDialog();
    }

    @Override
    public void onClickItemCheckDesdeColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        if (view != null) {

            if (colorCondicionalUi.isSelectDesde()) {
                colorCondicionalUi.setSelectDesde(false);
            } else {
                List<ColorCondicionalUi> colorCondicionalUis = view.abstraerColoreCondicionales();

                int posicion = colorCondicionalUis.indexOf(colorCondicionalUi);

                if (posicion != colorCondicionalUis.size() - 1) {
                    ColorCondicionalUi posterior = colorCondicionalUis.get(posicion + 1);
                    posterior.setSelectHasta(false);
                    view.updateColorCondicional(posterior);
                }

                if (posicion > 0) {
                    ColorCondicionalUi anterior = colorCondicionalUis.get(posicion - 1);
                    if (anterior.isSelectHasta()) {
                        anterior.setSelectHasta(false);
                        view.updateColorCondicional(anterior);
                    }
                }

                colorCondicionalUi.setSelectDesde(true);
            }
            view.updateColorCondicional(colorCondicionalUi);

        }
    }

    @Override
    public void onClickItemCheckHastaColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        if (view != null) {

            if (colorCondicionalUi.isSelectHasta()) {
                colorCondicionalUi.setSelectHasta(false);
            } else {
                List<ColorCondicionalUi> colorCondicionalUis = view.abstraerColoreCondicionales();

                int posicion = colorCondicionalUis.indexOf(colorCondicionalUi);

                if (posicion != colorCondicionalUis.size() - 1) {
                    ColorCondicionalUi posterior = colorCondicionalUis.get(posicion + 1);
                    posterior.setSelectDesde(false);
                    view.updateColorCondicional(posterior);
                }
                if (posicion > 1) {
                    ColorCondicionalUi anterior = colorCondicionalUis.get(posicion - 1);
                    if (anterior.isSelectDesde()) {
                        anterior.setSelectDesde(false);
                        view.updateColorCondicional(anterior);
                    }
                }

                colorCondicionalUi.setSelectHasta(true);
            }
            view.updateColorCondicional(colorCondicionalUi);

        }
    }

    @Override
    public void onClickItemEliminarColorCondicional(ColorCondicionalUi colorCondicionalUi) {
        if (view != null)view.deleteColorCondicional(colorCondicionalUi);
    }

    @Override
    public void onClickColorTexto(ColorCondicionalUi colorCondicionalUi) {
        if (view != null) {
            view.showColorPikerDialog("Seleccionar Color Texto", colorCondicionalUi.getColorTexto());
            color_picker = COLOR_TEXTO;
            colorCondUiSelectPiker = colorCondicionalUi;
        }
    }

    @Override
    public void onClickColorFondo(ColorCondicionalUi colorCondicionalUi) {
        if (view != null) {
            view.showColorPikerDialog("Seleccionar Color Fondo", colorCondicionalUi.getColorFondo());
            color_picker = COLOR_FONDO;
            colorCondUiSelectPiker = colorCondicionalUi;
        }
    }

    @Override
    public void onSelectColor(int color) {
        if (view != null) {
            switch (color_picker) {
                case COLOR_TEXTO:
                    colorCondUiSelectPiker.setColorTexto(color);
                    view.updateColorCondicional(colorCondUiSelectPiker);
                    break;
                case COLOR_FONDO:
                    colorCondUiSelectPiker.setColorFondo(color);
                    view.updateColorCondicional(colorCondUiSelectPiker);
                    break;
            }
        }

    }

    @Override
    public void onSelectFormaEvaluacion(FormaEvaluacionUi formaEvaluacionUi) {
        this.crearRubroEvalUi.setFormaEvaluacionUi(formaEvaluacionUi);
    }

    @Override
    public void onSelectTipoNota(String tipoNotaId) {
        hideCriterioEval();
        GetTipoNota(tipoNotaId);
    }

    @Override
    public void onBtnTipoEvaluacionClicked() {
        showTipoEvaluacionList(tipoEvaluacionList);
    }

    @Override
    public void onClickFormaEvaluacion() {
        showFromaEvaluacionList(formaEvaluacionUiList);
    }

    @Override
    public void onClickIndicador() {

        if(view!=null){
           // view.onSetCamposTematicos(indicadorId, campotematicoIds);
            int competenciaIdP;
            if(extras.getInt(CrearRubroCabeceraActividad.INT_COMPETENCIA_ID)!=0)competenciaIdP= capacidadId;
            else competenciaIdP=0;

            view.showListaIndicadoresFragment(silavoEventoId, sesionAprendizajeId, nivel, competenciaIdP, calendarioPeriodoId, indicadorId,campotematicoIds);
        }

    }

    @Override
    public void onChangeIndicadorCampotematico(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds) {
        this.capacidadId = competenciaId;
        this.indicadorId = indicadorId;
        this.campotematicoIds = campotematicoIds;
        Log.d("CambiarIndixcador", "as");
        if (view!=null)view.changeStateIndicador();
        getCompetencia();
        GetInidcador();
    }

    private void getCompetencia() {
        CapacidadUi capacidadUi = getCompetencia.execute(capacidadId);
        String nombreCapacidad =  (capacidadUi==null?"":capacidadUi.getNombre());
        String nombreCompetencia = "";
        if(capacidadUi!=null){
            CompetenciaUi competenciaUi = capacidadUi.getCompetencia();
            nombreCompetencia =  (competenciaUi==null?"":competenciaUi.getNombre());
        }


        if(view!=null)view.setCapacidad(nombreCapacidad);
        if(view!=null)view.setCompetencia(nombreCompetencia);
    }

    @Override
    public void onClickEstrategiaEval() {
        if (view == null) return;
        List<Object> items = new ArrayList<>();
        items.addAll(estrategiaEvalUis);
        int posicion = 0;
        if (estrategiaUiSelected != null) {
            posicion = items.indexOf(estrategiaUiSelected);
        }
        if (view!=null)view.showListSingleChooser("Estrategia de Evaluación", items, posicion);
    }

    @Override
    public void onTextChangedSubtitulo(String texto) {
        this.subtitulo = texto;
        if(habilitado){
            String titulo = (TextUtils.isEmpty(texto) ?  "":  ""+texto+" " ) + (estrategiaUiSelected==null ?  "": estrategiaUiSelected.getEstrategia());
            if(view!=null)view.showTituloEstrategia(titulo);
        }

    }

    @Override
    public void onSingleItemSelectedTipoNota(String tipoNotaId) {
        if (!TextUtils.isEmpty(tipoNotaId)) {
            Log.d(getTag(), "tipoNotaId: " + tipoNotaId);
            GetTipoNota(tipoNotaId);
        } else {
            showImportantMessage(res.getString(R.string.unknown_error));
        }
    }

    @Override
    public void onClickNivel() {
        if(view!=null)view.showActivityNivelLogro(idProgramaEducativo);
    }

    @Override
    public void onClickInfoTipoNota() {
        if(crearRubroEvalUi.getTipoNotaUi()!=null)if(view!=null)view.showDialogInfoTipoNota("Nivel de logro", crearRubroEvalUi.getTipoNotaUi());
    }

    @Override
    public void postCantidadLineasDesempenio(int lineCount) {
        this.lineasTextoDesempenio = lineCount;
        if(lineCount >= (maxLinTexDesempenio + 1)){
            if(view!=null)view.enabledVerMas(maxLinTexDesempenio);
        }
    }

    @Override
    public void onClickVerMasDesempenio() {
        if(verMasDesempenio){
            verMasDesempenio = false;
            if (view!=null)view.formatMinimizarTextDesmepenio(maxLinTexDesempenio);
            if (view!=null)view.changeTextoVerMasDesempenio("Ver más");
        }else {
            verMasDesempenio = true;
            if (view!=null)view.formatMaximizarTextDesmepenio();
            if (view!=null)view.changeTextoVerMasDesempenio("Ver menos");
        }
    }

    @Override
    public void onClickAceptarDialogkeyboard(String contenido) {
        if(criterioEvalUiSelected!=null)criterioEvalUiSelected.setDescripcion(contenido);

        if(valorTipoNOtaCriterios!=null)if(view!=null)view.setCriterios(valorTipoNOtaCriterios);

        if(dialogkeyBoardView!=null)dialogkeyBoardView.dialogDissmit();
    }

    @Override
    public void onCreateDialogKeyBoard(DialogkeyBoardView view) {
        this.dialogkeyBoardView = view;
    }

    @Override
    public void onDismissDialogkeyboard() {
        dialogkeyBoardView = null;
    }


    private void showTipoEvaluacionList(List<TipoEvaluacionUi> tipoEvaluacionList) {
        if (view == null) return;
        List<Object> items = new ArrayList<>();
        items.addAll(tipoEvaluacionList);

        int posicion = -1;
        TipoEvaluacionUi tipoEvaluacionUi = crearRubroEvalUi.getTipoEvaluacionUi();
        if (tipoEvaluacionUi != null) {
            posicion = items.indexOf(tipoEvaluacionUi);
        }
        if (view!=null)view.showListSingleChooser(res.getString(R.string.createrubbid_tipoevaluacion_dialog_title), items, posicion);

    }

    private void showFromaEvaluacionList(List<FormaEvaluacionUi> formaEvaluacionUis) {
        if (view == null) return;
        List<Object> items = new ArrayList<>();
        items.addAll(formaEvaluacionUis);

        int posicion = -1;
        FormaEvaluacionUi formaEvaluacionUi = crearRubroEvalUi.getFormaEvaluacionUi();
        if (formaEvaluacionUi != null) {
            posicion = items.indexOf(formaEvaluacionUi);
        }
        if (view!=null)view.showListSingleChooser(res.getString(R.string.createrubbid_formaevaluacion_dialog_title), items, posicion);

    }


    private void startWait(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showNotaTipoList(List<TipoNotaUi> tipoNotas) {
        if (view != null && tipoNotas != null)view.showSpinnerNotaTipo(tipoNotas);
    }

    private void showNotaTipoEvaluacion(List<TipoEvaluacionUi> tipoEvaluacionUis) {
        if (view != null)view.showSpinnerTipoEvaluacion(tipoEvaluacionUis);
    }

    private void cerraDialog() {
        if (view != null) {
            view.hideDialog();
        }
    }

    private void onSusessSaveRubro(String rubroEvaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi) {
        if (view != null)view.listenerSaveRubroSuccess(rubroEvaluacionProcesoId, crearRubroEvalUi, idProgramaEducativo);
    }

    private void showCriterioEval(List<ValorTipoNotaUi> valorTipoNotaUis) {
        if (view != null) {
            //view.hideProgressCriterioEval();
            if(valorTipoNotaUis.isEmpty())return;
            view.setCriterios(valorTipoNotaUis);
            view.showCriterioEval();
        }
    }

    private void hideCriterioEval() {
        if (view != null) {
            view.hideCriterioEval();
            //view.hideProgressCriterioEval();
        }
    }

    private void showProgressCriterioEval() {
        //if (view != null)view.showProgressCriterioEval();
    }

    private void showTitulo(IndicadorUi indicadorUi) {
        if (view != null)view.setTituloIndcador(indicadorUi, indicadorUi.getTipoIndicadorUi());
    }

    private void showFormaEvaluacion(List<FormaEvaluacionUi> formaEvaluacionUiList) {
        if (view !=null)view.showSpinnerFormaEvaluacion(formaEvaluacionUiList);
    }

    //region Abstraccion de Datos
    public void GetTipoNota(final String tipoNotaId) {
        Log.d(TAG, "getTipoNota");
        handler.execute(getTipoNota,
                new GetTipoNota.RequestValues(tipoNotaId),
                new UseCase.UseCaseCallback<GetTipoNota.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoNota.ResponseValue response) {
                        TipoNotaUi tipoNotaUi = response.getTipoNota();
                        crearRubroEvalUi.setTipoNotaUi(tipoNotaUi);
                        if (tipoNotaUi != null){
                            getPrescion(tipoNotaUi);
                            if(view!=null)view.showNivelLogro(tipoNotaUi);
                            if(tipoNotaUi.getTipoNota() != TipoNotaUi.TipoNota.VALOR_NUMERICO ||
                                    tipoNotaUi.getTipoNota() != TipoNotaUi.TipoNota.SELECTOR_NUMERICO){
                                Log.d(TAG, "tipoNotaUi");
                                if (tipoNotaUi.getTipoId() != 0) {
                                    showProgressCriterioEval();
                                    GetCriterioEval();
                                }

                            }
                        }




                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void getPrescion(TipoNotaUi tipoNotaUi) {
        if(tipoNotaUi.getValorTipoNotaUis()!=null)
            for (ValorTipoNotaUi valorTipoNotaUi : tipoNotaUi.getValorTipoNotaUis()){

                List<PrecisionUi> precisionUis = getNotaPresicion.execute(new GetNotaPresicion.RequestValues(
                        valorTipoNotaUi.getColor(),
                        (int)valorTipoNotaUi.getLimiteSuperior(),
                        (int)valorTipoNotaUi.getLimiteInferior(),
                        valorTipoNotaUi.getIncluidoLSuperior(),
                        valorTipoNotaUi.getIncluidoLInferior()));

                valorTipoNotaUi.setPrecisionList(precisionUis);
            }
    }

    private void showTecladoNumerico(EscalaEvaluacionUi escalaEvaluacionUi) {
        if(view!=null)view.showTecladoNumerico(escalaEvaluacionUi);
    }

    private void hideTecladoNumerico(){
        if(view!=null)view.hideTecladoNumerico();
    }

    public void GetCriterioEval() {

        handler.execute(
                getcriterioEval,
                new GetCriterioEval.RequestValues(crearRubroEvalUi),
                new UseCase.UseCaseCallback<GetCriterioEval.ResponseValue>() {
                    @Override
                    public void onSuccess(GetCriterioEval.ResponseValue response) {
                       valorTipoNOtaCriterios = response.getValorTipoNotaUis();
                        showCriterioEval(valorTipoNOtaCriterios);
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    public void saveRubro() {

        handler.execute(
                saveRubro,
                new SaveRubro.RequestValues(crearRubroEvalUi),
                new UseCase.UseCaseCallback<SaveRubro.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveRubro.ResponseValue response) {
                        onSusessSaveRubro(response.getRubroEvlauacionProcesoId(), crearRubroEvalUi);
                        cerraDialog();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    public void GetInidcador() {
        handler.execute(
                getInidicador,
                new GetInidicador.RequestValues(indicadorId, campotematicoIds),
                new UseCase.UseCaseCallback<GetInidicador.ResponseValue>() {
                    @Override
                    public void onSuccess(GetInidicador.ResponseValue response) {
                        indicador = response.getIndicadorUi();
                        showTitulo(indicador);
                        if(indicador == null || indicador.getCamposAccionUiList() == null)return;
                        showCamposAcion(indicador.getCamposAccionUiList());
                        if(view!=null)view.showVistaPrevia();
                    }

                    @Override
                    public void onError() {

                    }
                }

        );
    }

    private void showCamposAcion(List<CamposAccionUi> camposAccionUiList) {
        if(view!=null)view.showCampoAcionList(camposAccionUiList);
    }



    public void GetTipoEvaluacion() {
        handler.execute(
                getTipoEvaluacion,
                new GetTipoEvaluacion.RequestValues(),
                new UseCase.UseCaseCallback<GetTipoEvaluacion.ResponseValue>() {

                    @Override
                    public void onSuccess(GetTipoEvaluacion.ResponseValue response) {
                        tipoEvaluacionList = response.getTipoEvaluacionUis();
                        int position=0;
                        if(rubroProcesoUi!=null){
                            for(TipoEvaluacionUi tipoEvaluacionUi: tipoEvaluacionList){
                                if(tipoEvaluacionUi.getId()==rubroProcesoUi.getTipoEvaluacionId())
                                    position= tipoEvaluacionList.indexOf(tipoEvaluacionUi);
                            }
                        }
                        defaultSelectTipoEvaluacion(tipoEvaluacionList, position);
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    public void GetFormaEvaluacion() {
        handler.execute(getFormaEvaluacion,
                new GetFormaEvaluacion.RequestValues(),
                new UseCase.UseCaseCallback<GetFormaEvaluacion.ResponseValue>() {
                    @Override
                    public void onSuccess(GetFormaEvaluacion.ResponseValue response) {
                        formaEvaluacionUiList = response.getFormaEvaluacionUiList();
                        int position=0;
                        if(rubroProcesoUi!=null) {
                            for (FormaEvaluacionUi formaEvaluacionUi : formaEvaluacionUiList){
                                if (formaEvaluacionUi.getId() == rubroProcesoUi.getFormaEvaluacionId())
                                    position= formaEvaluacionUiList.indexOf(formaEvaluacionUi);}
                        }
                        defaultSelectFormaEvaluacion(formaEvaluacionUiList, position);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    //endregion Abstraccion de Datos

    private void defaultSelectFormaEvaluacion(List<FormaEvaluacionUi> formaEvaluacionUiList, int posicion) {
        if (view == null) return;
        try {
            FormaEvaluacionUi formaEvaluacionUi = formaEvaluacionUiList.get(posicion);
            crearRubroEvalUi.setFormaEvaluacionUi(formaEvaluacionUi);
            view.showInputFormaEvaluacion(formaEvaluacionUi.getNombre());
        } catch (Exception ignored) {
        }
    }

    private void defaultSelectTipoEvaluacion(List<TipoEvaluacionUi> tipoEvaluacionList, int posicion) {
        if (view == null) return;
        try {
            TipoEvaluacionUi tipoEvaluacionUi = tipoEvaluacionList.get(posicion);
            crearRubroEvalUi.setTipoEvaluacionUi(tipoEvaluacionUi);
            view.showInputTipoEvaluacion(tipoEvaluacionUi.getNombre());
        } catch (Exception ignored) {
        }
    }
}
