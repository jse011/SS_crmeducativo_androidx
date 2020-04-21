package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos_Table;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.TipoNotaPresenter;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.domainUseCase.GetTipoEscalaEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.domainUseCase.GetTipoSelectorList;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.ui.CrearTipoNotaView;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoSelectorUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 16/02/2018.
 */

public class CrearTipoNotaPresenterImpl extends BasePresenterImpl<CrearTipoNotaView> implements CrearTipoNotaPresenter {

    private String TAG = CrearTipoNotaPresenterImpl.class.getSimpleName();
    private GetTipoSelectorList getTipoSelectorList;
    private GetTipoEscalaEvaluacionList getTipoEscalaEvaluacionList;

    private static final int SELECTOR_VALOR = 412;
    private static final int SELECTOR_ICONOS = 409;
    private static final int VALOR_NUMERICO = 2;
    private static final int SELECTOR_NUMERICO = 3;

    public CrearTipoNotaPresenterImpl(UseCaseHandler handler, Resources res, GetTipoSelectorList getTipoSelectorList, GetTipoEscalaEvaluacionList getTipoEscalaEvaluacionList) {
        super(handler, res);
        this.getTipoSelectorList = getTipoSelectorList;
        this.getTipoEscalaEvaluacionList = getTipoEscalaEvaluacionList;
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");

    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        if (view != null) {
            getTipoSelector();
            getTipoEscalaEvaluacion();
        }

    }

    private List<TipoEscalaEvaluacionUi> tipoEscalaEvaluacionUiList;

    private void getTipoEscalaEvaluacion() {
        handler.execute(getTipoEscalaEvaluacionList,
                new GetTipoEscalaEvaluacionList.RequestValues(idUsuario),
                new UseCase.UseCaseCallback<GetTipoEscalaEvaluacionList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoEscalaEvaluacionList.ResponseValue response) {
                        Log.d(TAG, "SuccessgetTipoEscalaEvaluacion :" + response.getTipoEscalaEvaluacionUiList().size());
                        tipoEscalaEvaluacionUiList = response.getTipoEscalaEvaluacionUiList();

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private List<TipoSelectorUi> tipoSelectorUiList;

    private void getTipoSelector() {
        handler.execute(getTipoSelectorList,
                new GetTipoSelectorList.RequestValues(idUsuario),
                new UseCase.UseCaseCallback<GetTipoSelectorList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoSelectorList.ResponseValue response) {
                        Log.d(TAG, "SuccessgetgetTipoSelector :" + response.getTipoSelectorUiList().size());
                        tipoSelectorUiList = response.getTipoSelectorUiList();


                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
    }

    int idUsuario;

    @Override
    public void setExtras(Bundle extras) {
        Log.d(TAG, "setExtras");
        //  String nombreAcceso = extras.getString("nombreAcceso");
        //  int idAcceso = extras.getInt("idAcceso", 0);
        //  int idPersona = extras.getInt("idPersona", 0);
        this.idUsuario = extras.getInt("usuarioId", 0);
        Log.d(TAG, "onCreate : " + idUsuario);
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
        Log.d(TAG, "onSingleItemSelected: " + singleItem);
        if (singleItem instanceof TipoSelectorUi) {
            tipoSelectorSelected = (TipoSelectorUi) singleItem;
            for (TipoSelectorUi tipoSelectorUi : tipoSelectorUiList) {
                if (tipoSelectorUi.getNombre().equals(tipoSelectorSelected.getNombre())) {
                    tipoSelectorSelected = tipoSelectorUi;
                    break;
                }
            }
            if (tipoSelectorSelected == null) {
                showImportantMessage(res.getString(R.string.unknown_error));
                return;
            }
            showTipoSelectorSelected(tipoSelectorSelected);
        } else if (singleItem instanceof TipoEscalaEvaluacionUi) {
            tipoEscalaEvaluacionSelected = (TipoEscalaEvaluacionUi) singleItem;
            for (TipoEscalaEvaluacionUi tipoSelectorUi : tipoEscalaEvaluacionUiList) {
                if (tipoSelectorUi.getNombre().equals(tipoEscalaEvaluacionSelected.getNombre())) {
                    tipoEscalaEvaluacionSelected = tipoSelectorUi;
                    break;
                }
            }
            if (tipoEscalaEvaluacionSelected == null) {
                showImportantMessage(res.getString(R.string.unknown_error));
                return;
            }
            showTipoEscalaEvaluacionSelected(tipoEscalaEvaluacionSelected);
        } else {
            Log.d(TAG, "onSingleItemSelected");
        }
    }

    @Override
    public void onCLickAcceptButtom() {

    }


    private TipoNotaUi tipoNotaUi;
    private TipoEscalaEvaluacionUi tipoEscalaEvaluacionSelected;
    private TipoSelectorUi tipoSelectorSelected;



    private void showTipoEscalaEvaluacionSelected(TipoEscalaEvaluacionUi tipoEscalaEvaluacionSelected) {
        if (view != null) {
            view.showTipoEscalaEvaluacionSelected(tipoEscalaEvaluacionSelected.getNombre());
        }
    }

    private void showTipoSelectorSelected(TipoSelectorUi tipoSelectorSelected) {
        if (view != null) {
            view.showTipoSelectorSelected(tipoSelectorSelected.getNombre());
            initViewValidation(tipoSelectorSelected);
        }
    }

    private void initViewValidation(TipoSelectorUi tipoSelectorSelected) {
        if (view != null) {
            switch (tipoSelectorSelected.getIdTipo()) {
                case SELECTOR_VALOR:
                    view.showTipoSelector();
                    break;
                case SELECTOR_ICONOS:
                    view.showSelectorIconos();
                    break;
                case VALOR_NUMERICO:
                    view.showValorNumerico();
                    break;
                case SELECTOR_NUMERICO:
                    view.showSelectorNumerico();
                    break;
                default:
                    break;
            }
            Log.d(TAG, "TIPOSELECTOR : " + tipoSelectorSelected.getNombre() + " TIPOID :  " + tipoSelectorSelected.getIdTipo() + "");
        }
    }




    @Override
    public void onCheckBox(boolean isTrue) {
        Log.d(TAG, "isTrue" + isTrue);
        tipoNotaUi = new TipoNotaUi();
        if (isTrue) {
            tipoNotaUi.setaBoolean(isTrue);
            if(view!=null)view.showValorMinMax();
        } else {
            tipoNotaUi.setaBoolean(isTrue);
            if(view!=null)view.hideValorMinMax();
        }
    }

    @Override
    public void onBnTipoSelectorClicked() {
        if (view != null) {
            List<Object> items = new ArrayList<>();
            items.addAll(tipoSelectorUiList);
            view.showListSingleChooser("Tipo Selector", items, -1);
        }
    }

    @Override
    public void onBnTipoEscalaEvaluacionClicked() {
        if (view != null) {
            List<Object> items = new ArrayList<>();
            items.addAll(tipoEscalaEvaluacionUiList);
            view.showListSingleChooser("Escala Evaluación", items, -1);
        }
    }


}
