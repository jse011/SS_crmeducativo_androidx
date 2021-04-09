package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.dialogKeyBoard.DialogkeyBoardView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.listener.EditarRubroDetalleCallBack;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.ui.EditarRubroDetalleFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.ui.EditarRubroDetalleView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.tableview.model.Cell;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CampoAccionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;

import java.util.ArrayList;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.ui.EditarRubroDetalleFragment.ARG_INDICADOR;
import static com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.editarRubroDetalle.ui.EditarRubroDetalleFragment.ARG_TIPONOTA;


/**
 * Created by @stevecampos on 28/02/2018.
 */

public class EditarRubroDetallePresenterImpl extends BaseFragmentPresenterImpl<EditarRubroDetalleView> implements EditarRubroDetallePresenter {

    private EditarRubroDetalleCallBack callBack;
    private IndicadorUi indicadorUi;
    private TipoNotaUi tipoNotaUi;
    private int lineasTextoDesempenio;
    private static final int maxLinTexDesempenio = 2;
    private boolean verMasDesempenio;
    private String editableTitulo;
    private String editableSubTitulo;
    private List<CampoAccionUi> campoAccionUiList;
    private List<Cell> cellList;
    private boolean disabledCampoAccion;
    private CriterioEvaluacionUi criterioEvaluacionUiSelected;
    private DialogkeyBoardView dialogkeyBoardView;

    public EditarRubroDetallePresenterImpl(UseCaseHandler handler, Resources res) {
        super(handler, res);
    }

    @Override
    public void attachView(EditarRubroDetalleView view) {
        super.attachView(view);
        this.callBack = view;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    protected String getTag() {
        return EditarRubroDetallePresenterImpl.class.getSimpleName();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        indicadorUi = (IndicadorUi)extras.getSerializable(ARG_INDICADOR);
        tipoNotaUi = (TipoNotaUi) extras.getSerializable(ARG_TIPONOTA);
        cellList = (List<Cell>) extras.getSerializable(EditarRubroDetalleFragment.ARG_LISTDETALLE);
        this.disabledCampoAccion = extras.getBoolean(EditarRubroDetalleFragment.ARG_DISABLEDCAMPOACCION);

    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        inicializar();
    }

    private void inicializar() {
        campoAccionUiList = new ArrayList<>();

        if(indicadorUi!=null){

            /*if (indicadorUi.getTituloRubro()==null || TextUtils.isEmpty(indicadorUi.getTituloRubro().trim())) {
                editableTitulo = TextUtils.isEmpty(indicadorUi.getAlias())? indicadorUi.getTitulo() : indicadorUi.getAlias();
                indicadorUi.setTituloRubro(editableTitulo);
            }else {
                editableTitulo = indicadorUi.getTituloRubro();
            }*/

            editableTitulo = TextUtils.isEmpty(indicadorUi.getAlias())? indicadorUi.getTitulo() : indicadorUi.getAlias();
            if(view!=null)view.setTituloRubro(editableTitulo);
            editableSubTitulo = indicadorUi.getSubTituloRubro();

            if (view!=null)view.showIndicador(indicadorUi);
            if (view!=null)view.showCompetencia(indicadorUi.getCompetenciaOwner());
            if (view!=null)view.showCapacidad(indicadorUi.getCapacidadOwner());

            if(indicadorUi.getCriterioEvaluacionUiList()!=null||!indicadorUi.getCampoAccionList().isEmpty()){
                if (view!=null)view.showCriterioEvaluacion(indicadorUi.getCriterioEvaluacionUiList());
            }else {
                if (view!=null)view.hideCriterioEvaluacion();
            }

            try {
                campoAccionUiList.addAll(indicadorUi.getCampoAccionList());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (view!=null)view.showCampoAccionList(new ArrayList<>(campoAccionUiList));

        if(tipoNotaUi==null){
            if (view!=null)view.showTipoNota(tipoNotaUi, cellList);
        }

        if(disabledCampoAccion){
            if (view!=null)view.disabledCampoAccion();
        }else {
            if (view!=null)view.enabledCampoAccion();
        }

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
            if(view==null)return;
            if (view!=null)view.formatMaximizarTextDesmepenio();
            if (view!=null)view.changeTextoVerMasDesempenio("Ver menos");
        }
    }

    @Override
    public void onCampoTematicoCancel() {

        if(callBack != null)callBack.onClickCancelarEditarRubroDetalle();
    }

    @Override
    public void onCampoTematicoListOk() {
        if(editableTitulo == null ||editableTitulo.trim().isEmpty()){
            if(view !=null)view.showMessage("Título vacio");
            return;
        }
        boolean vacioCampoAccion=true;
        if(campoAccionUiList!=null)for (CampoAccionUi campoAccionUi: campoAccionUiList)if(campoAccionUi.isChecked())vacioCampoAccion=false;
        if(vacioCampoAccion){
            if(view !=null)view.showMessage("Seleccione un campo de acción");
            return;
        }

        indicadorUi.setTituloRubro((TextUtils.isEmpty(editableSubTitulo) ? "": editableSubTitulo + " ") + editableTitulo);
        indicadorUi.setSubTituloRubro(editableSubTitulo);


        if (callBack != null)callBack.onClickAcetarEditarRubroDetalle(indicadorUi);
    }


    @Override
    public void onTextChangedTitulo(String titulo) {
        editableTitulo = titulo;
    }

    @Override
    public void onTextChangedSubTitulo(String subTitulo) {
        editableSubTitulo = subTitulo;
        if(view!=null)view.setTituloRubro((TextUtils.isEmpty(subTitulo) ? "": subTitulo + " ") + editableTitulo);
    }

    @Override
    public void onClickCriterioEvaluacion(CriterioEvaluacionUi criterioEvaluacionUi) {
        this.criterioEvaluacionUiSelected = criterioEvaluacionUi;
        if(view!=null)view.showDialogEditCriterio(criterioEvaluacionUi);
    }

    @Override
    public void onClickAceptarDialogkeyboard(String contenido) {
        if(criterioEvaluacionUiSelected!=null)criterioEvaluacionUiSelected.setTitulo(contenido);
        if(indicadorUi.getCriterioEvaluacionUiList()!=null||!indicadorUi.getCampoAccionList().isEmpty()){
            if (view!=null)view.showCriterioEvaluacion(indicadorUi.getCriterioEvaluacionUiList());
        }else {
            if (view!=null)view.hideCriterioEvaluacion();
        }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialogkeyBoardView = null;
        callBack = null;
    }
}

