package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.entities.MensajePredeterminado;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.domain.usecases.GetMensajePredeterninadoUIListUseCase;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.ArrayList;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.DialogMensajePredeterminado.EXTRA_ID_INTENCION;

/**
 * Created by irvinmarin on 09/08/2018.
 */

public class MensajePredeterminadoPresenterImpl implements MensajePredeterminadoPresenter {

    private static final String TAG = MensajePredeterminadoPresenterImpl.class.getSimpleName();
    private MensajePredeterminadoView view;
    private UseCaseHandler useCaseHandler;
    private GetMensajePredeterninadoUIListUseCase getMensajePredeterninadoUIListUseCase;


    public MensajePredeterminadoPresenterImpl(UseCaseHandler useCaseHandler, GetMensajePredeterninadoUIListUseCase getMensajePredeterninadoUIListUseCase) {
        this.useCaseHandler = useCaseHandler;
        this.getMensajePredeterninadoUIListUseCase = getMensajePredeterninadoUIListUseCase;
    }

    @Override
    public void attachView(MensajePredeterminadoView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    private List<String> tiposMensajePredeterninadoList;

    private void findListTiposMensaje() {
        tiposMensajePredeterninadoList = new ArrayList<>();
        tiposMensajePredeterninadoList.add("Positivos");
        tiposMensajePredeterninadoList.add("Advertencias");
        tiposMensajePredeterninadoList.add("Negativos");
        tiposMensajePredeterninadoList.add("Eliminados");
        validateTipoMensajePositionSelected(0);
    }


    @Override
    public void getListTiposMensajePredeterninado() {
        view.showListSingleChooser("Tipo Mensaje", tiposMensajePredeterninadoList, 0);
    }

    @Override
    public void onClickEditarMensajePred(MensajePredeterminadoUI mensajePredeterminadoUI) {
        if (view != null)
            view.showEditMensajePredActivity(mensajePredeterminadoUI, idIntencionSelected);
    }

    @Override
    public void deleteLogicMensajePred(MensajePredeterminadoUI mensajePredeterminadoUI) {
        saveChangeMensajePred(mensajePredeterminadoUI.getKeyMensajePredeterminado(), 327, MensajePredeterminado.FLAG_DELETED);
        if (view != null) {
            view.removeItemMensajePredRV(mensajePredeterminadoUI);
        }
    }

    private void saveChangeMensajePred(String mensajePredeterminadoKey, int estadoId, int flag) {
        MensajePredeterminado mensajePredeterminado = MensajePredeterminado.getMesnajeById(mensajePredeterminadoKey);
        mensajePredeterminado.setSyncFlag(flag);
        mensajePredeterminado.setEstadoId(estadoId);
        mensajePredeterminado.save();
    }

    @Override
    public void onFabAddClicked() {
        view.showCreateMensajePredActivity(idIntencionSelected);
    }

    @Override
    public void restoreItemMensajePred(MensajePredeterminadoUI mensajePredeterminadoUI) {
        saveChangeMensajePred(mensajePredeterminadoUI.getKeyMensajePredeterminado(), 326, MensajePredeterminado.FLAG_UPDATED);
        onResume();
    }

    private void getListaMensajes(int idObjetiveMessage) {

        useCaseHandler.execute(getMensajePredeterninadoUIListUseCase,
                new GetMensajePredeterninadoUIListUseCase.RequestValues(idObjetiveMessage),
                new UseCase.UseCaseCallback<GetMensajePredeterninadoUIListUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetMensajePredeterninadoUIListUseCase.ResponseValue response) {
                        view.showListaMensajesPredeterminador(response.getMensajePredeterminadoUIList());
                    }

                    @Override
                    public void onError() {
                        view.showMessage("Opción Sin Datos ");
                        view.showListaMensajesPredeterminador(new ArrayList<MensajePredeterminadoUI>());

                    }
                });


    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        findListTiposMensaje();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    private int idIntencionSelected;

    @Override
    public void setExtras(Bundle arguments) {
        idIntencionSelected = arguments.getInt(EXTRA_ID_INTENCION);
    }

    @Override
    public void onSingleItemSelected(String objectSelected, int selectedPosition) {
        validateTipoMensajePositionSelected(selectedPosition);
    }

    private void validateTipoMensajePositionSelected(int selectedPosition) {
        int idObjetivoMensaje = 0;
        String objectSelected = "";
        switch (selectedPosition) {
            case 0:
                idObjetivoMensaje = 508;
                objectSelected = "Positivos";
                break;
            case 1:
                idObjetivoMensaje = 509;
                objectSelected = "Advertencias";

                break;
            case 2:
                idObjetivoMensaje = 510;
                objectSelected = "Negativos";
                break;
            case 3:
                idObjetivoMensaje = 327;
                objectSelected = "Eliminados";
                break;
        }
        getListaMensajes(idObjetivoMensaje);
        view.setOpcionSelected(objectSelected);
    }
}
