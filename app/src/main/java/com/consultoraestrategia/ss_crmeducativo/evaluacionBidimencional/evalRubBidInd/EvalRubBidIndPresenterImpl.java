package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EscalaEvaluacionUI;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaTipoCompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.PublicarEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui.EvalRubBidIndView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.dialogComentario.EvalRubBidComPredView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.DeleteArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.DeleteComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetComentarioPred;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetPublicacionEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.SaveArchivoRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.SaveComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.UpdatePublicacionEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.UploadArchivo;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Created by @stevecampos on 28/02/2018.
 */

public class EvalRubBidIndPresenterImpl extends BaseFragmentPresenterImpl<EvalRubBidIndView> implements EvalRubBidIndPresenter {
    protected static final int CUSTOM_REQUEST_CODE = 532;
    public static final String TAG = EvalRubBidIndPresenterImpl.class.getSimpleName();
    private ColumnHeader evaluado;
    private List<ColumnHeader> evaluadoList;
    private RubBidUi rubricaBidimencional;
    private List<RubEvalProcUi> rubEvalProcUiList;
    private List<ValorTipoNotaUi> valorTipoNotaUiList;
    private List<RowHeader> rowHeaderList;
    private List<List<Cell>> cellsList;
    private List<ColumnHeader> columnHeaderList;
    private double puntoBase = 0;
    private double puntosActuales = 0;
    private double notaTransformada = 0;
    private TipoNotaUi tipoNotaUi;
    private ValorTipoNotaUi valorTipoNotaUiCabecera;
    private boolean tableViewModoAvanzado = false;
    private boolean isCalendarioVigente;
    private EvalRubBidComPredView evalRubBidComPredView;
    private GetComentarioPred getcomentarioPred;
    private SaveComentario saveComentario;
    private DeleteComentario deleteComentario;
    private EvalProcUi evalProcUiSelect;
    private GetArchivoComentario getArchivoComentario;
    private SaveArchivoRubro saveArchivoRubro;
    private DeleteArchivoComentario deleteArchivoComentario;
    private GetPublicacionEvaluacion getPublicacionEvaluacion;
    private UpdatePublicacionEvaluacion updatePublicacionEvaluacion;
    private boolean initResume = false;
    private PublicarEvaluacionUi publicarEvaluacionUi;
    private UploadArchivo uploadArchivo;
    private List<ArchivoUi> tareaArchivoUiList = new ArrayList<>();

    @Override
    protected String getTag() {
        return null;
    }

    public EvalRubBidIndPresenterImpl(UseCaseHandler handler, Resources res, GetComentarioPred getcomentarioPred,
                                      SaveComentario saveComentario,
                                      DeleteComentario deleteComentario,
                                      GetArchivoComentario getArchivoComentario,
                                      SaveArchivoRubro saveArchivoRubro,
                                      DeleteArchivoComentario deleteArchivoComentario,
                                      GetPublicacionEvaluacion getPublicacionEvaluacion,
                                      UpdatePublicacionEvaluacion updatePublicacionEvaluacion,
                                      UploadArchivo uploadArchivo) {
        super(handler, res);
        this.getcomentarioPred=getcomentarioPred;
        this.saveComentario=saveComentario;
        this.deleteComentario = deleteComentario;
        this.getArchivoComentario = getArchivoComentario;
        this.saveArchivoRubro = saveArchivoRubro;
        this.deleteArchivoComentario = deleteArchivoComentario;
        this.getPublicacionEvaluacion = getPublicacionEvaluacion;
        this.updatePublicacionEvaluacion = updatePublicacionEvaluacion;
        this.uploadArchivo = uploadArchivo;

    }
    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void attachView(EvalRubBidComPredView evalRubBidComPredView) {
        this.evalRubBidComPredView=evalRubBidComPredView;
    }

    @Override
    public void setAlumno(AlumnoProcesoUi alumnoProcesoUi, RubBidUi rubBidUi) {

    }

    @Override
    public void onFinalizar() {
        if (view == null) return;
        view.salirDialogo();
    }

    @Override
    public void onRetroceder() {
        changeEvaluado(-1, false);
    }

    @Override
    public void onAvanzar() {
        changeEvaluado(1, true);
    }

    @Override
    public void onSelectEvaluacion(ValorTipoNotaUi valorTipoNotaUi, TipoNotaUi tipoNotaUi) {
        valorTipoNotaUi.setValorNumerico(valorTipoNotaUi.getValorAsignado());


        tipoNotaUi.setSelectValorTipoNotaUi(valorTipoNotaUi);

        valorTipoNotaUi.setSelected(true);
        notaTransformada += calculoNota(valorTipoNotaUi, valorTipoNotaUi.getValorNumerico());
        calculoCabecera();
        EvalProcUi evalProcUiCabecera = evaluarRubroEvalProcesoCabecera();
        EvalProcUi evalProcUiDetalle = evaluarRubroEvalProceso(valorTipoNotaUi, evaluado, valorTipoNotaUi.getValorNumerico());
        if (evalProcUiCabecera == null || evalProcUiDetalle == null) return;
        if (view == null) return;
        List<EvalProcUi> evalProcUis = new ArrayList<>();
        evalProcUis.add(evalProcUiDetalle);
        callActividad(evaluado, evalProcUiCabecera, evalProcUis);
    }

    private void callActividad(ColumnHeader evaluado, EvalProcUi evalProcUiCabecera, List<EvalProcUi> evalProcUis) {
        if(view!=null)view.callActividad(evaluado, evalProcUiCabecera, evalProcUis);
        if(view!=null)view.notiftyDataBaseChange();
    }


    @Override
    public void onDeSelectEvaluacion(ValorTipoNotaUi valorTipoNotaUi, ValorTipoNotaUi valorTipoNotaHold, TipoNotaUi tipoNotaUi) {
        valorTipoNotaUi.setValorNumerico(valorTipoNotaUi.getValorAsignado());


        tipoNotaUi.setSelectValorTipoNotaUi(valorTipoNotaUi);
        valorTipoNotaUi.setSelected(true);
        notaTransformada += calculoNota(valorTipoNotaUi, valorTipoNotaUi.getValorNumerico());
        valorTipoNotaHold.setSelected(false);
        notaTransformada -= calculoNota(valorTipoNotaHold, valorTipoNotaHold.getValorNumerico());
        calculoCabecera();
        EvalProcUi evalProcUiCabecera = evaluarRubroEvalProcesoCabecera();
        EvalProcUi evalProcUiDetalleRemove = evaluarRubroEvalProceso(valorTipoNotaHold, evaluado, valorTipoNotaHold.getValorNumerico());
        EvalProcUi evalProcUiDetalle = evaluarRubroEvalProceso(valorTipoNotaUi, evaluado, valorTipoNotaUi.getValorNumerico());
        if (evalProcUiCabecera == null || evalProcUiDetalle == null || evalProcUiDetalleRemove == null)
            return;
        if (view == null) return;
        List<EvalProcUi> evalProcUis = new ArrayList<>();
        evalProcUis.add(evalProcUiDetalle);
        evalProcUis.add(evalProcUiDetalleRemove);
       callActividad(evaluado, evalProcUiCabecera, evalProcUis);

        //valorTipoNotaHold.setValorNumerico(valorTipoNotaHold.getValorAsignado());
    }


    public List<EvalProcUi> getEvaluacionProceso(ColumnHeader columnHeader) {
        List<EvalProcUi> evalProcUiList = new ArrayList<>();
        if (evaluado instanceof AlumnoProcesoUi) {
            AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi) columnHeader;
            evalProcUiList = alumnoProcesoUi.getEvalProcList();
        } else if (evaluado instanceof GrupoProcesoUi) {
            GrupoProcesoUi grupoProcesoUi = (GrupoProcesoUi) columnHeader;
            evalProcUiList = grupoProcesoUi.getEvalProcList();
        }
        return evalProcUiList;
    }

    @Override
    public void onDeSelectEvaluacion(ValorTipoNotaUi valorTipoNotaUi, TipoNotaUi tipoNotaUi) {
        //valorTipoNotaUi.setValorNumerico(valorTipoNotaUi.getValorAsignado());
        tipoNotaUi.setSelectValorTipoNotaUi(null);

        valorTipoNotaUi.setSelected(false);
        Log.d(TAG, "ValorNumerico: " + valorTipoNotaUi.getValorNumerico());
        Log.d(TAG, "ValorAsignado: " + valorTipoNotaUi.getValorAsignado());
        notaTransformada -= calculoNota(valorTipoNotaUi, valorTipoNotaUi.getValorNumerico());
        calculoCabecera();
        EvalProcUi evalProcUiCabecera = evaluarRubroEvalProcesoCabecera();
        if (evalProcUiCabecera == null) return;
        if (view == null) return;
        List<EvalProcUi> evalProcUis = new ArrayList<>();
        EvalProcUi evalProcUiDetalleRemove = evaluarRubroEvalProceso(valorTipoNotaUi, evaluado, valorTipoNotaUi.getValorNumerico());
        evalProcUis.add(evalProcUiDetalleRemove);
        callActividad(evaluado, evalProcUiCabecera, evalProcUis);
    }

    @Override
    public void onClickRubroEvaluacion(RubEvalProcUi rubEvalProcUi) {
        if (view == null) return;
        view.showAlertDialog(rubEvalProcUi);
    }

    @Override
    public void onClickBtnChange() {
        if (!tableViewModoAvanzado) {
            showTableModoAvanzado();
            changerEyeFocus();
            tableViewModoAvanzado = true;
        } else {
            showTableModoSimple();
            changeEyeSimple();
            tableViewModoAvanzado = false;
        }
    }

    @Override
    public void onLongEvaluacion(TipoNotaUi tipoNotaUi, ValorTipoNotaUi valorTipoNotaUi) {
        EvalProcUi evalProcUiDetalle = evaluarRubroEvalProceso(valorTipoNotaUi, evaluado, valorTipoNotaUi.getValorNumerico());
        if (view != null)
            view.onShowDialogPresicion(evalProcUiDetalle.getNota(), evalProcUiDetalle.getRubEvalProcId(), valorTipoNotaUi.getId(), valorTipoNotaUi.getEstilo().getColor(), valorTipoNotaUi.isValidar());
    }

    @Override
    public void onSelectPrecicionEvaluacion(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId) {
        Log.d(TAG, "notaAnterior: " + notaAnterior + ",notaActual: " + notaActual + ",valorTipoNotaId:" + valorTipoNotaId + ",rubroEvaluacionId:" + rubroEvaluacionId);
        try {
            List<EvalProcUi> evalProcUiList = new ArrayList<>();
            if (evaluado instanceof AlumnoProcesoUi) {
                AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi) evaluado;
                evalProcUiList = alumnoProcesoUi.getEvalProcList();
            } else if (evaluado instanceof GrupoProcesoUi) {
                GrupoProcesoUi grupoProcesoUi = (GrupoProcesoUi) evaluado;
                evalProcUiList = grupoProcesoUi.getEvalProcList();
            }

            RubEvalProcUi rubEvalProcUi = new RubEvalProcUi();
            rubEvalProcUi.setId(rubroEvaluacionId);
            int poscion = rubEvalProcUiList.indexOf(rubEvalProcUi);
            rubEvalProcUi = rubEvalProcUiList.get(poscion);
            EvalProcUi evalProcUi = evalProcUiList.get(poscion);
            TipoNotaUi tipoNotaUi = rubEvalProcUi.getTipoNotaUi();
            ValorTipoNotaUi valorTipoNotaUi = null;
            for (ValorTipoNotaUi itemValorTipoNotaUi : tipoNotaUi.getValorTipoNotaList()) {
                if (itemValorTipoNotaUi.getId().equals(valorTipoNotaId)) {
                    valorTipoNotaUi = itemValorTipoNotaUi;
                    break;
                }
            }
            notaTransformada -= calculoNota(valorTipoNotaUi, notaAnterior);
            notaTransformada += calculoNota(valorTipoNotaUi, notaActual);
            calculoCabecera();
            EvalProcUi evalProcUiCabecera = evaluarRubroEvalProcesoCabecera();
            EvalProcUi evalProcUiDetalle = evaluarRubroEvalProceso(valorTipoNotaUi, evaluado, notaActual);
            if (evalProcUiCabecera == null || evalProcUiDetalle == null) return;
            evalProcUi.setNota(evalProcUiDetalle.getNota());
            paintCell();
            if (view == null) return;
            List<EvalProcUi> evalProcUis = new ArrayList<>();
            evalProcUis.add(evalProcUiDetalle);
            callActividad(evaluado, evalProcUiCabecera, evalProcUis);
            if(view!=null)view.notiftyDataBaseChange();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showDialogMsgPred() {

        Log.d(TAG,"showDialogMsgPred");
         if(view!=null)view.showDialogMsgPred();
    }



    public EvalProcUi evaluarRubroEvalProceso(ValorTipoNotaUi valorTipoNotaUi, ColumnHeader evaluado, double nota) {

        TipoNotaUi tipoNotaUi = valorTipoNotaUi.getTipoNotaUi();
        if (tipoNotaUi == null) return null;
        if (tipoNotaUi.getRubEvalProcUi() == null) return null;

        RubEvalProcUi rubEvalProcUi = tipoNotaUi.getRubEvalProcUi();
        EvalProcUi evalProcUi = new EvalProcUi();
        evalProcUi.setRubEvalProcId(rubEvalProcUi.getId());

        List<EvalProcUi> evalProcUiList = getEvaluacionProceso(evaluado);
        int posicion = evalProcUiList.indexOf(evalProcUi);
        if (posicion != -1) {
            evalProcUi = evalProcUiList.get(posicion);
        }


        evalProcUi.setNota(nota);
        evalProcUi.setValorTipoNotaId(valorTipoNotaUi.getId());
        EvalProcUi.Tipo tipo = EvalProcUi.Tipo.SELECTOR_VALORES;
        String escala = null;
        switch (tipoNotaUi.getTipo()) {
            case SELECTOR_ICONOS:
                escala = valorTipoNotaUi.getIcono();
                tipo = EvalProcUi.Tipo.SELECTOR_ICONOS;
                break;
            case SELECTOR_VALORES:
                escala = valorTipoNotaUi.getTitulo();
                tipo = EvalProcUi.Tipo.SELECTOR_VALORES;
                break;
        }

        evalProcUi.setEscala(escala);
        evalProcUi.setTipo(tipo);

        if (evaluado instanceof AlumnoProcesoUi) {
            AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi) evaluado;
            evalProcUi.setAlumnoId(alumnoProcesoUi.getId());
            evalProcUi.setEquipoId(alumnoProcesoUi.getEquipoId());
            evalProcUi.setFormaEvaluar(EvalProcUi.FormaEvaluar.INDIVIDUAL);

            NotaUi notaUi = alumnoProcesoUi.getNotaUi();
            notaUi.setNota(notaTransformada);

        } else if (evaluado instanceof GrupoProcesoUi) {
            GrupoProcesoUi grupoProcesoUi = (GrupoProcesoUi) evaluado;
            Log.d(TAG, grupoProcesoUi.toString());
            evalProcUi.setEquipoId(grupoProcesoUi.getId());
            evalProcUi.setAlumnoId(0);
            evalProcUi.setFormaEvaluar(EvalProcUi.FormaEvaluar.GRUPAL);
            NotaUi notaUi = grupoProcesoUi.getNotaUi();
            notaUi.setNota(notaTransformada);

            List<List<Cell>> cellsList = grupoProcesoUi.getCellList();
            for (List<Cell> itemcellList : cellsList) {
                for (Cell itemcell : itemcellList) {
                    if (itemcell instanceof EvalProcUi) {
                        EvalProcUi evalProcUidetalle = (EvalProcUi) itemcell;
                        if(evalProcUidetalle.isAlumnoActivo()){
                            if (TextUtils.isEmpty(evalProcUidetalle.getRubEvalProcId())) continue;
                            if (evalProcUidetalle.getRubEvalProcId().equals(evalProcUi.getRubEvalProcId())) {
                                if (valorTipoNotaUi.isSelected()) {
                                    evalProcUidetalle.setNota(nota);
                                    evalProcUidetalle.setEscala(escala);
                                    evalProcUidetalle.setTipo(tipo);
                                    evalProcUidetalle.setValorTipoNotaId(valorTipoNotaUi.getId());
                                    evalProcUidetalle.setEstado(true);

                                } else {
                                    evalProcUidetalle.setNota(0);
                                    evalProcUidetalle.setEscala(null);
                                    evalProcUidetalle.setValorTipoNotaId(null);
                                    evalProcUidetalle.setEstado(false);
                                }
                            }
                        }

                    } else if (itemcell instanceof NotaUi) {

                        NotaUi notaUiDetalle = (NotaUi) itemcell;
                        if(notaUiDetalle.isAlumnoActivo())
                        notaUiDetalle.setNota(notaTransformada);
                    }
                }
            }
        }

        if (valorTipoNotaUi.isSelected()) {
            evalProcUi.setEstado(true);
        } else {
            evalProcUi.setEstado(false);
            evalProcUi.setValorTipoNotaId(null);
            evalProcUi.setNota(0);
            evalProcUi.setEscala(null);
        }


        return evalProcUi;
    }

    public EvalProcUi evaluarRubroEvalProcesoCabecera() {

        if (tipoNotaUi == null) return null;
        if (rubricaBidimencional == null) return null;
        if (valorTipoNotaUiCabecera == null) return null;

        EvalProcUi evalProcUi = new EvalProcUi();
        evalProcUi.setRubEvalProcId(rubricaBidimencional.getId());
        evalProcUi.setNota(notaTransformada);
        evalProcUi.setValorTipoNotaId((valorTipoNotaUiCabecera.getId()));
        switch (tipoNotaUi.getTipo()) {
            case SELECTOR_ICONOS:
                evalProcUi.setEscala(valorTipoNotaUiCabecera.getIcono());
                evalProcUi.setTipo(EvalProcUi.Tipo.SELECTOR_ICONOS);
                break;
            case SELECTOR_VALORES:
                evalProcUi.setEscala(valorTipoNotaUiCabecera.getTitulo());
                evalProcUi.setTipo(EvalProcUi.Tipo.SELECTOR_VALORES);
                break;
        }

        if (evaluado instanceof AlumnoProcesoUi) {
            AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi) evaluado;
                evalProcUi.setAlumnoId(alumnoProcesoUi.getId());
                evalProcUi.setFormaEvaluar(EvalProcUi.FormaEvaluar.INDIVIDUAL);
                evalProcUi.setEquipoId(alumnoProcesoUi.getEquipoId());

        } else if (evaluado instanceof GrupoProcesoUi) {
            Log.d(TAG, "evaluarRubroEvalProcesoCabecera GrupoProcesoUi");
            GrupoProcesoUi grupoProcesoUi = (GrupoProcesoUi) evaluado;
            evalProcUi.setEquipoId(grupoProcesoUi.getId());
            evalProcUi.setFormaEvaluar(EvalProcUi.FormaEvaluar.GRUPAL);
        }
        Log.d(TAG, evalProcUi.toString());

        evalProcUi.setEstado(true);
        return evalProcUi;
    }

    private void calculoCabecera() {

        if (this.tipoNotaUi == null) return;
        EscalaEvaluacionUI escalaEvaluacionUI = this.tipoNotaUi.getEscalaEvaluacionUi();
        if (escalaEvaluacionUI == null) return;
        correcionNotaEscalaMinima(escalaEvaluacionUI);
        puntosActuales = transformacionInvariante(escalaEvaluacionUI, escalaEvaluacionUI.getValorMinimo(), escalaEvaluacionUI.getValorMaximo(), notaTransformada, 0, puntoBase);
        showPuntos(String.valueOf(Math.round(puntosActuales) + "/" + Math.round(puntoBase)));

        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        double percent = puntosActuales / puntoBase;
        showDesempenio(percentInstance.format(percent));
        NumberFormat numberFormat = NumberFormat.getNumberInstance();

        showNota(Utils.formatearDecimales(notaTransformada,2));

        if (tipoNotaUi == null) return;
        List<ValorTipoNotaUi> valorTipoNotaUiList = tipoNotaUi.getValorTipoNotaList();
        Log.d(TAG, "Intervalo: " + tipoNotaUi.isIntervalo());
        valorTipoNotaUiCabecera = calcularLogro(notaTransformada, escalaEvaluacionUI, valorTipoNotaUiList, tipoNotaUi.isIntervalo());
        if (valorTipoNotaUiCabecera != null) {
            showLogro(valorTipoNotaUiCabecera.getAlias());
        } else {
            showLogro("");
        }

    }

    private void correcionNotaEscalaMinima(EscalaEvaluacionUI escalaEvaluacionUI) {

        if (notaTransformada == -0) {
            notaTransformada = 0;
        }

        if (escalaEvaluacionUI.getValorMinimo() > notaTransformada) {
            notaTransformada = escalaEvaluacionUI.getValorMinimo();
        }

    }

    private double transformacionInvariante(EscalaEvaluacionUI escalaEvaluacionUI, double valorMinimo, double valorMaximo, double nota, double valorMinimoDos, double valorMaximoDos) {

        if (escalaEvaluacionUI == null) return escalaEvaluacionUI.getValorMinimo();
        if (nota <= escalaEvaluacionUI.getValorMinimo()) {
            nota = escalaEvaluacionUI.getValorMinimo();
        }

        return Utils.transformacionInvariante(valorMinimo, valorMaximo, nota, valorMinimoDos, valorMaximoDos);
    }

    private void resetCalculoCabecera() {
        puntoBase = 0;
        puntosActuales = 0;
        notaTransformada = 0;
        showPuntos("0/0");
        showNota(0);
        showDesempenio("0");

        if (this.tipoNotaUi == null) return;
        EscalaEvaluacionUI escalaEvaluacionUI = this.tipoNotaUi.getEscalaEvaluacionUi();
        if (escalaEvaluacionUI == null) return;
        if (notaTransformada <= escalaEvaluacionUI.getValorMinimo()) {
            notaTransformada = escalaEvaluacionUI.getValorMinimo();
        }
    }

    private double calculoNota(ValorTipoNotaUi valorTipoNotaUi, double nota) {
        TipoNotaUi tipoNotaUi = valorTipoNotaUi.getTipoNotaUi();
        if (tipoNotaUi == null) return 0;
        RubEvalProcUi rubEvalProcUi = tipoNotaUi.getRubEvalProcUi();
        if (rubEvalProcUi == null) return 0;
        EscalaEvaluacionUI escalaEvaluacionUIOrigen = tipoNotaUi.getEscalaEvaluacionUi();
        if (this.tipoNotaUi == null) return 0;
        EscalaEvaluacionUI escalaEvaluacionUITransformada = this.tipoNotaUi.getEscalaEvaluacionUi();
        if (escalaEvaluacionUITransformada == null) return 0;
        notaTransformada = transformacionInvariante(escalaEvaluacionUIOrigen, escalaEvaluacionUIOrigen.getValorMinimo(), escalaEvaluacionUIOrigen.getValorMaximo(), puntosActuales, escalaEvaluacionUITransformada.getValorMinimo(), escalaEvaluacionUITransformada.getValorMaximo());
        double notaTransformada = transformacionInvariante(escalaEvaluacionUIOrigen, escalaEvaluacionUIOrigen.getValorMinimo(), escalaEvaluacionUIOrigen.getValorMaximo(), nota, escalaEvaluacionUITransformada.getValorMinimo(), escalaEvaluacionUITransformada.getValorMaximo());

        double peso = Utils.formatearDecimales(Utils.transformacionInvariante(0, 100, rubEvalProcUi.getPeso(), 0, 1), 4);
        //return transformacionInvariante(escalaEvaluacionUITransformada, escalaEvaluacionUIOrigen.getValorMinimo(), escalaEvaluacionUIOrigen.getValorMaximo(), (notaTransformada*peso),0,1);
        notaTransformada = Utils.formatearDecimales(notaTransformada * peso, 4);

        if (notaTransformada == -0) {
            notaTransformada = 0;
        }
        return notaTransformada;
    }

    private ValorTipoNotaUi calcularLogro(double notaTransformada,EscalaEvaluacionUI escalaEvaluacionUI,  List<ValorTipoNotaUi> valorTipoNotaCabeceraList, boolean intervalo) {
        BigDecimal bd = new BigDecimal(notaTransformada);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        notaTransformada = bd.doubleValue();

        Log.d(TAG, "notaTransformada: " + notaTransformada);
        if (notaTransformada == -0) {
            Log.d(TAG, "notaTransformada con cero Negqativo: " + notaTransformada);
            notaTransformada = 0;
        }

        ValorTipoNotaUi valorTipoNotaCabeceraActual = null;
        if (intervalo) {

            for (ValorTipoNotaUi valorTipoNotaCabecera : valorTipoNotaCabeceraList) {

                if (getvalor(valorTipoNotaCabecera, notaTransformada)) {
                    valorTipoNotaCabeceraActual = valorTipoNotaCabecera;
                    break;
                }
            }
        } else {

            int notaEntera = (int)Math.round(notaTransformada);

            if( escalaEvaluacionUI !=null && valorTipoNotaCabeceraList.size()==2){
                int valorsuperio = (int)escalaEvaluacionUI.getValorMaximo();
                int valorinferio = (int)escalaEvaluacionUI.getValorMinimo();
                int valorintermedio = (valorsuperio + valorinferio) / 2;

                List<ValorTipoNotaUi> valorTipoNotaUis = new ArrayList<>(valorTipoNotaCabeceraList);

                Collections.sort(valorTipoNotaUis, new Comparator<ValorTipoNotaUi>() {
                    @Override
                    public int compare(ValorTipoNotaUi o1, ValorTipoNotaUi o2) {
                        return Double.compare(o1.getValorNumerico(), o2.getValorNumerico());
                    }
                });

                Log.d(TAG, "valorintermedio: " + valorintermedio + " >=  notaEntera " + notaEntera);
                if(valorintermedio >= notaEntera){
                    valorTipoNotaCabeceraActual = valorTipoNotaUis.get(0);
                }else {
                    valorTipoNotaCabeceraActual = valorTipoNotaUis.get(1);
                }

            }else{
                for (ValorTipoNotaUi valorTipoNotaCabecera : valorTipoNotaCabeceraList) {
                    Log.d(TAG, "notaEntera: " + notaEntera + " == getValorNumerico " + valorTipoNotaCabecera.getValorNumerico());
                    if (valorTipoNotaCabecera.getValorNumerico() == notaEntera) {
                        valorTipoNotaCabeceraActual = valorTipoNotaCabecera;
                        break;
                    }
                }
            }

            ValorTipoNotaUi menorvalorTipoNotaUi = null;
            for (ValorTipoNotaUi valorTipoNotaCabecera : valorTipoNotaCabeceraList) {
                if(menorvalorTipoNotaUi==null) menorvalorTipoNotaUi = valorTipoNotaCabecera;
                else if(menorvalorTipoNotaUi.getValorNumerico()>valorTipoNotaCabecera.getValorNumerico())menorvalorTipoNotaUi = valorTipoNotaCabecera;
            }

            if(notaEntera==0&&menorvalorTipoNotaUi!=null){
                valorTipoNotaCabeceraActual = menorvalorTipoNotaUi;
            }
        }
        return valorTipoNotaCabeceraActual;
    }

    private boolean getvalor(ValorTipoNotaUi valorTipoNotaUi, double nota) {

        boolean valorInferior = false;
        boolean valorSuperior = false;

        if (valorTipoNotaUi.isIncluidoLSuperior()) {
            //rango.append("[ ");
            if (valorTipoNotaUi.getLimiteSuperior() >= nota) {
                valorSuperior = true;
            }

        } else {
            //rango.append("< ");
            if (valorTipoNotaUi.getLimiteSuperior() > nota) {
                valorSuperior = true;
            }
        }
        if (valorTipoNotaUi.isIncluidoLInferior()) {
            // rango.append(" ]");
            if (valorTipoNotaUi.getLimiteInferior() <= nota) {
                valorInferior = true;
            }
        } else {
            // rango.append(" >");20
            if (valorTipoNotaUi.getLimiteInferior() < nota) {
                valorInferior = true;
            }

        }
        Log.d(TAG,"valorTipoNotaUi.isIncluidoLSuperior() : "+ valorTipoNotaUi.isIncluidoLSuperior()+ " valorTipoNotaUi.isIncluidoLInferior():"+ valorTipoNotaUi.isIncluidoLInferior());
        Log.d(TAG,"valorTipoNotaUi: "+ valorTipoNotaUi.getAlias() + "valorInferior : "+ valorInferior+ " valorSuperior:"+ valorSuperior);

        return valorInferior && valorSuperior;

    }


    public void changeEvaluado(int posicion, boolean avanzar) {
        try {
            int cantidadLista = evaluadoList.size();
            int poscionEvaluado = evaluadoList.indexOf(evaluado);
            changeContador(poscionEvaluado, cantidadLista);
            evaluado = evaluadoList.get(poscionEvaluado + posicion);
            if(evaluado instanceof AlumnoProcesoUi){
                AlumnoProcesoUi alumnoProcesoUi= (AlumnoProcesoUi)evaluado;
                if(!alumnoProcesoUi.isVigente()){
                   int position= poscionEvaluado + posicion;
                   if(avanzar)position++;
                   else position--;
                   evaluado= evaluadoList.get(position);
                }
            }
            showColumnHeaderActual();
            paintCell();
            changeComentario();
            changeRubtoArchivo();
            changePublicacion();

        } catch (Exception e) {
            e.printStackTrace();
            if (view == null) return;
            changeContador(0, 0);
            view.salirDialogo();
            view.callActividadTableNotifyDataSetChanged();
        }
    }

    private void changePublicacion() {
        Log.d(TAG,"changePublicacion " );
        if(rubricaBidimencional!=null){
            publicarEvaluacionUi = getPublicacionEvaluacion.execute(rubricaBidimencional.getId(), evalProcUiSelect.getAlumnoId());
            if (evaluado instanceof AlumnoProcesoUi) {
               if(view!=null)view.showPublicar();
            }else {
                if(view!=null)view.hidePublicar();
            }
            if(view!=null)view.setPublicar(publicarEvaluacionUi);
        }
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle = new CRMBundle(extras);
        isCalendarioVigente = crmBundle.isCalendarioActivo();
        Log.d(TAG,"isCalendarioVigente " + isCalendarioVigente);
    }

    @Override
    public void onResume() {
        super.onResume();
        showColumnHeaderActual();
        paintTable();
        showTableModoSimple();
        changeContador(evaluado, evaluadoList);
        changeComentario();
        changePublicacion();
        if(!initResume){
            changeRubtoArchivo();
        }
        initResume = true;
        Log.d(TAG,"ResumeJse Resumen");
        showBtnClean();
    }

    private void showBtnClean(){
        if (view!=null)view.showBtnClean(isCalendarioVigente);
    }


    private void showTableModoAvanzado() {
        String titulo = "Indicadores";
        if (view != null&&rubricaBidimencional!=null){
            view.showTableModoAvanzado(titulo, columnHeaderList, rowHeaderList, cellsList, !isCalendarioVigente);
        }
    }

    private void showTableModoSimple() {
        Log.d(TAG, "showTableModoSimple "+(view==null));
        Log.d(TAG, "rubricaBidimencional "+(rubricaBidimencional==null));
        String titulo = "Indicadores";
        if (view != null&&rubricaBidimencional!=null){
            view.showTableModoSimple(titulo, columnHeaderList, rowHeaderList, cellsList, !isCalendarioVigente);
        }
    }


    private void paintTable() {
        columnHeaderList = new ArrayList<>();
        rowHeaderList = new ArrayList<>();
        cellsList = new ArrayList<>();

        NotaUi notaUi = new NotaUi();
        notaUi.setNombre("%");
        notaUi.setNota(100);

        rowHeaderList.add(notaUi);
        for (ValorTipoNotaUi valorTipoNotaUi : valorTipoNotaUiList) {
            valorTipoNotaUi.setIntervalo(setRangoNota(valorTipoNotaUi));
        }
        rowHeaderList.addAll(valorTipoNotaUiList);
        columnHeaderList.addAll(rubEvalProcUiList);
        cellsList = paintCell(rubricaBidimencional, rubEvalProcUiList);

    }

    private void paintCell() {
        cellsList = paintCell(rubricaBidimencional, rubEvalProcUiList);
        if (view == null) return;
        view.updateCellTableview(cellsList);
    }

    private List<List<Cell>> paintCell(RubBidUi rubBidUi, List<RubEvalProcUi> rubEvalProcUiList) {
        resetCalculoCabecera();
        List<EvalProcUi> evalProcUiList = new ArrayList<>();
        if (evaluado instanceof AlumnoProcesoUi) {
            AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi) evaluado;
            evalProcUiList = alumnoProcesoUi.getEvalProcList();
        } else if (evaluado instanceof GrupoProcesoUi) {
            GrupoProcesoUi grupoProcesoUi = (GrupoProcesoUi) evaluado;
            evalProcUiList = grupoProcesoUi.getEvalProcList();
        }

        int poscion = 0;
        List<List<Cell>> cellsList = new ArrayList<>();
        for (RubEvalProcUi itemRubEvalProcUi : rubEvalProcUiList) {
            List<Cell> cells = new ArrayList<>();

            NotaTipoCompetenciaUi notaUiDetalle = new NotaTipoCompetenciaUi();
            notaUiDetalle.setId(itemRubEvalProcUi.getId());
            notaUiDetalle.setNota(itemRubEvalProcUi.getPeso());
            notaUiDetalle.setTipoCompenciaEnum(itemRubEvalProcUi.getTipoCompenciaEnum());
            notaUiDetalle.setTipoIndicadorUi(itemRubEvalProcUi.getTipoIndicadorUi());
            notaUiDetalle.setUrl(itemRubEvalProcUi.getUrl());
            Log.d(TAG, itemRubEvalProcUi.getTipoCompenciaEnum().toString());
            cells.add(notaUiDetalle);

            TipoNotaUi tipoNotaUi = itemRubEvalProcUi.getTipoNotaUi();
            tipoNotaUi.setSelectValorTipoNotaUi(null);
            if (tipoNotaUi == null) continue;

              evalProcUiSelect = new EvalProcUi();
            try {
                evalProcUiSelect = evalProcUiList.get(poscion);
            } catch (Exception ignored) {
            }

            for (ValorTipoNotaUi itemValorTipoNota : tipoNotaUi.getValorTipoNotaList()) {
                cells.add(itemValorTipoNota);
                itemValorTipoNota.setSelected(false);
                itemValorTipoNota.setValorNumerico(itemValorTipoNota.getValorAsignado());
                try {
                    ValorTipoNotaUi valorTipoNotaUi = new ValorTipoNotaUi();
                    valorTipoNotaUi.setId(String.valueOf(evalProcUiSelect.getValorTipoNotaId()));
                    Log.d(TAG, "ValorTipoNotaUi: " + valorTipoNotaUi.toString());
                    if (itemValorTipoNota.equals(valorTipoNotaUi)) {
                        itemValorTipoNota.setSelected(true);
                        tipoNotaUi.setSelectValorTipoNotaUi(itemValorTipoNota);
                        notaTransformada += calculoNota(itemValorTipoNota, evalProcUiSelect.getNota());
                        puntosActuales += evalProcUiSelect.getNota();
                        Log.d(TAG, "itemValorTipoNota2: " + itemValorTipoNota.getValorNumerico() + " evalProcUiSelect: " + evalProcUiSelect.getNota());
                        if (evalProcUiSelect.getValorTipoNotaId() != null)
                            itemValorTipoNota.setValorNumerico(evalProcUiSelect.getNota());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            poscion++;
            cellsList.add(cells);
            EscalaEvaluacionUI escalaEvaluacionUI = tipoNotaUi.getEscalaEvaluacionUi();
            if (escalaEvaluacionUI != null) {
                puntoBase += escalaEvaluacionUI.getValorMaximo() - escalaEvaluacionUI.getValorMinimo();
            } else {
                puntoBase += 0;
            }
        }
        calculoCabecera();
        return cellsList;
    }

    private void showColumnHeaderActual() {

        if (evaluado == null) return;
        if (evaluado instanceof AlumnoProcesoUi) {
            AlumnoProcesoUi alumnoActualUi = (AlumnoProcesoUi) evaluado;
            if (view != null && alumnoActualUi != null) {
                view.showTabComentario();
                view.showAlumnoNombre(alumnoActualUi.getNombre(), alumnoActualUi.getApellidos());
                view.showAlumnoProfile(alumnoActualUi.getUrlProfile());
            }
        } else if (evaluado instanceof GrupoProcesoUi) {

            GrupoProcesoUi grupoProcesoUi = (GrupoProcesoUi) evaluado;
            if (view != null && grupoProcesoUi != null) {
                view.hideTabComentario();
                view.showAlumnoNombre("Grupo", grupoProcesoUi.getNombre());
                view.showAlumnoProfile(grupoProcesoUi.getUrlProfile());
            }
        }

    }

    private void showNota(double nota) {
        if (view != null) {
            view.showNota(nota);
        }
    }

    private void showDesempenio(String desempenio) {
        if (view != null) {
            view.showDesempenio(desempenio);
        }
    }

    private void showLogro(String logro) {
        Log.d(TAG, "showLogro");
        if (view != null) {
            view.showLogro(logro);
        }
    }

    private void showPuntos(String puntos) {
        if (view != null) {
            view.showPuntos(puntos);
        }
    }

    private ValorTipoNotaUi getValorTipoNotaMax() {
        if (valorTipoNotaUiList == null || valorTipoNotaUiList.isEmpty()) return null;
        ValorTipoNotaUi valorTipoNotaMax = valorTipoNotaUiList.get(0);

        for (ValorTipoNotaUi valorTipoNota :
                valorTipoNotaUiList) {
            if (valorTipoNota.getLimiteSuperior() > valorTipoNotaMax.getLimiteSuperior()) {
                valorTipoNotaMax = valorTipoNota;
            }
        }
        return valorTipoNotaMax;
    }

    private ValorTipoNotaUi getValorTipoNotaMin() {
        if (valorTipoNotaUiList == null || valorTipoNotaUiList.isEmpty()) return null;
        ValorTipoNotaUi valorTipoNotaMin = valorTipoNotaUiList.get(0);
        for (ValorTipoNotaUi valorTipoNota :
                valorTipoNotaUiList) {
            if (valorTipoNota.getLimiteInferior() < valorTipoNotaMin.getLimiteSuperior()) {
                valorTipoNotaMin = valorTipoNota;
            }
        }
        return valorTipoNotaMin;
    }

    private void changeContador(int posicion, int cantidadLista) {
        if (view == null) return;
        view.changeContadorPagina(posicion, cantidadLista);
    }

    private void changeContador(ColumnHeader evaluado, List<ColumnHeader> evaluadoList) {
        try {
            int cantidadLista = evaluadoList.size();
            int poscionEvaluado = evaluadoList.indexOf(evaluado);
            changeContador(poscionEvaluado, cantidadLista);
        } catch (Exception e) {
            changeContador(0, 0);
        }
    }

    @Override
    public void onDestroyView() {
        if (view != null) view.callActividadTableNotifyDataSetChanged();
        super.onDestroyView();
    }

    private String setRangoNota(ValorTipoNotaUi valorNotaAsignado) {
        StringBuilder rango = new StringBuilder();

        if (valorNotaAsignado.isIncluidoLSuperior()) {
            rango.append("[ ");
        } else {
            rango.append("< ");
        }
        rango.append(String.format(Locale.US,"%.1f", valorNotaAsignado.getLimiteSuperior()));
        rango.append(" - ");
        rango.append(String.format(Locale.US,"%.1f", valorNotaAsignado.getLimiteInferior()));
        if (valorNotaAsignado.isIncluidoLInferior()) {
            rango.append(" ]");
        } else {
            rango.append(" >");
        }

        return rango.toString();
    }

    void changeEyeSimple() {
        if (view != null) view.changeEyeSimple();
    }

    void changerEyeFocus() {
        if (view != null) view.changerEyeFocus();
    }




    private void changeRubtoArchivo() {
        if(rubricaBidimencional!=null){
            if(view!=null)view.showListComentariosArchivos(getArchivoComentario.execute(new GetArchivoComentario.Response(rubricaBidimencional.getId(), evalProcUiSelect.getAlumnoId())));
        }
    }

    private void changeComentario(){
        if(rubricaBidimencional!=null){
            GetComentarioPred.Response response=getcomentarioPred.execute(new GetComentarioPred.Requests(rubricaBidimencional.getId(),evalProcUiSelect.getAlumnoId(), 0));
            if (evaluado instanceof AlumnoProcesoUi) {
                AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi)evaluado;
                if(response.getMensajeUiList().isEmpty()){
                    alumnoProcesoUi.setComentario(false);
                }else {
                    alumnoProcesoUi.setComentario(true);
                }
            }
            if(view!=null)view.showListComentarios(response.getMensajeUiList());
        }
    }

    @Override
    public void saveComentario(String comentario) {
        Log.d(TAG,"Cometario :" + comentario);
        MensajeUi mensajeUi= new MensajeUi();
        mensajeUi.setId(IdGenerator.generateId());
        mensajeUi.setDescripcion(comentario);
        mensajeUi.setAlumnoId(evalProcUiSelect.getAlumnoId());
        mensajeUi.setRubroEvaluacionId(rubricaBidimencional.getId());
        if(view!=null)view.clearTextInpuComentario();
        if(view!=null)view.hideBtnSendComentario();
        SaveComentario.Response response= saveComentario.execute(new SaveComentario.Requests(mensajeUi));
        if(response.isSuccess()) changeComentario();
    }

    @Override
    public void setData(ColumnHeader columnHeader, RubBidUi rubBidUi, List<ColumnHeader> columnHeaders) {
        if (evaluado == null) {
            evaluado = columnHeader;
        }
        evaluadoList = columnHeaders;
        rubricaBidimencional =rubBidUi;
        if (rubricaBidimencional != null) {
            rubEvalProcUiList = rubricaBidimencional.getRubroEvalProcesoList();
            valorTipoNotaUiList = rubricaBidimencional.getValorTipoNotaUiList();
            tipoNotaUi = rubricaBidimencional.getTipoNota();
        }
    }

    @Override
    public void onTextChangedEditComentario(String toString) {
        if(toString.length()>0){
            if(view!=null)view.showBtnSendComentario();
        }else {
            if(view!=null)view.hideBtnSendComentario();
        }
    }

    @Override
    public void onClickComentarioNormal(MensajeUi mensajeUi) {
        DeleteComentario.Response response = deleteComentario.execute(new DeleteComentario.Requests(mensajeUi));
        if(response.isSuccess()) changeComentario();
    }

    @Override
    public void onClickBtnClear() {
        if (view != null&&rubricaBidimencional!=null){
            view.cleanCellTableview(columnHeaderList);
        }
    }

    @Override
    public void btnPublicar() {
        Log.d(TAG,"changePublicacion " );
        if(publicarEvaluacionUi!=null){
            if(!publicarEvaluacionUi.isSelected()){
                publicarEvaluacionUi.setSelected(true);
            }else {
                publicarEvaluacionUi.setSelected(false);
            }

            if(view!=null)view.notiftyDataBaseChange();
            updatePublicacionEvaluacion.execute(publicarEvaluacionUi);

            if (evaluado instanceof AlumnoProcesoUi) {
                AlumnoProcesoUi alumnoProcesoUi = (AlumnoProcesoUi) evaluado;
                NotaUi notaUi = alumnoProcesoUi.getNotaUi();
                notaUi.setPublicar(publicarEvaluacionUi.isSelected());
            }

            changePublicacion();
        }
    }

    @Override
    public void onClickCamera() {
        if(view!=null)view.showCamera();
    }

    @Override
    public void onClickGalery() {
        if(view!=null)view.showGalery();
    }

    @Override
    public void onUpdload(Map<Uri, String> photoPaths) {

        for (Map.Entry<Uri, String> entry : photoPaths.entrySet()) {
            String fileName = entry.getValue();
            ArchivoUi archivoUi = new ArchivoUi();
            archivoUi.setId(IdGenerator.generateId());
            archivoUi.setAlumnoId(evalProcUiSelect.getAlumnoId());
            archivoUi.setRubroEvaluacionId(rubricaBidimencional.getId());
            archivoUi.setNombre(fileName);
            archivoUi.setUri(entry.getKey());

            uploadArchivo.execute(archivoUi, new UploadArchivo.CallbackProgress<ArchivoUi>() {
                @Override
                public void onProgress(int count, ArchivoUi item) {
                    item.setProgress(count);
                    if(view!=null)view.updateTareaArchivo(item);
                }

                @Override
                public void onLoad(boolean success, ArchivoUi item) {
                    if(success){
                        if(view!=null)view.updateTareaArchivo(item);
                    }else {
                        tareaArchivoUiList.remove(item);
                        if(view!=null)view.removeTareaArchivo(item);
                    }
                }

                @Override
                public void onPreLoad(ArchivoUi item) {
                    tareaArchivoUiList.add(item);
                    if(view!=null)view.addTareaArchivo(item);
                }
            });
        }

    }


    @Override
    public void removerComentarioArchivo(ArchivoUi archivoComentarioUi) {
        archivoComentarioUi.setCancel(true);
        deleteArchivoComentario.execute(new DeleteArchivoComentario.Requests(archivoComentarioUi));
        tareaArchivoUiList.remove(archivoComentarioUi);
        if(view!=null)view.removeTareaArchivo(archivoComentarioUi);
    }

}

