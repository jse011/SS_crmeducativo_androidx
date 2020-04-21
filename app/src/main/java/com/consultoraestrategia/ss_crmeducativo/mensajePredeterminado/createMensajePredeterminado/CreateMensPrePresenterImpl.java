package com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado;

import android.content.res.Resources;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Tipos;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.domain.usecases.CreateMensajePredeterminadoUseCase;
import com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.ui.intitiesUI.MensajePredeterminadoUI;

import java.util.ArrayList;
import java.util.List;

import static com.consultoraestrategia.ss_crmeducativo.mensajePredeterminado.createMensajePredeterminado.ui.CreateMensPredeActivity.EXTRA_MENSAJE_PRED_UI;

/**
 * Created by irvinmarin on 09/08/2018.
 */

public class CreateMensPrePresenterImpl extends BasePresenterImpl<CreateMensPreView> implements CreateMensPrePresenter {


    private static final String TAG = CreateMensPrePresenterImpl.class.getSimpleName();
    private CreateMensajePredeterminadoUseCase createMensajePredeterminadoUseCase;

    public CreateMensPrePresenterImpl(UseCaseHandler handler, Resources res, CreateMensajePredeterminadoUseCase createMensajePredeterminadoUseCase) {
        super(handler, res);
        this.createMensajePredeterminadoUseCase = createMensajePredeterminadoUseCase;
    }


    private MensajePredeterminadoUI mensajePredeterminadoUI;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        mensajePredeterminadoUI = (MensajePredeterminadoUI) extras.getSerializable(EXTRA_MENSAJE_PRED_UI);
    }

    private int idObjetivoSelected;
    private int idAlcanceSelected;
    private String keyMensajePred = "";

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
        switch (btnSelected) {
            case 0:
                break;
            case 1:
                Tipos opcionAlcanseSelected = (Tipos) singleItem;
                idAlcanceSelected = opcionAlcanseSelected.getTipoId();
                view.setAlcanceSelected(opcionAlcanseSelected.getNombre());
                break;
            case 2:
                Tipos opcionObjetivoSelected = (Tipos) singleItem;
                idObjetivoSelected = opcionObjetivoSelected.getTipoId();
                view.setObjetivoSelected(opcionObjetivoSelected.getNombre());

                break;
        }
    }

    @Override
    public void onCLickAcceptButtom() {

    }


    @Override
    protected String getTag() {
        return TAG;
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();

        listAlcanceMensaje = new ArrayList<>();
        listObjetivoMensaje = new ArrayList<>();
        setupMensajetePred();
    }

    private void setupMensajetePred() {
        getListaAlcance();
        getListaObjetivos();
        if (mensajePredeterminadoUI != null) {
            if (view != null) {
                view.setDataToEditImputs(
                        mensajePredeterminadoUI.getAsuntoMensaje(),
                        mensajePredeterminadoUI.getCabeceraMensaje(),
                        mensajePredeterminadoUI.getPresentacionMensaje(),
                        mensajePredeterminadoUI.getCuerpoMensaje(),
                        mensajePredeterminadoUI.getDespedidaMensaje(),
                        mensajePredeterminadoUI.getTipoMensaje()
                );
                idObjetivoSelected = mensajePredeterminadoUI.getObjetivo();
                idAlcanceSelected = mensajePredeterminadoUI.getAlcance();
                keyMensajePred = mensajePredeterminadoUI.getKeyMensajePredeterminado();
            }
        }


    }

    private void getListaObjetivos() {
        listObjetivoMensaje = Tipos.getTipoByConcepto("Objetivo Mensaje");
        if (!listObjetivoMensaje.isEmpty()) {
            idObjetivoSelected = listObjetivoMensaje.get(0).getTipoId();
            view.setObjetivoSelected(listObjetivoMensaje.get(0).getNombre());
        }
    }

    private List<Tipos> listAlcanceMensaje = new ArrayList<>();
    private List<Tipos> listObjetivoMensaje = new ArrayList<>();

    private void getListaAlcance() {
        listAlcanceMensaje = Tipos.getTipoByConcepto("Alcance Mensaje");
        if (!listAlcanceMensaje.isEmpty()) {
            idAlcanceSelected = listAlcanceMensaje.get(0).getTipoId();
            view.setObjetivoSelected(listAlcanceMensaje.get(0).getNombre());
        }
    }

    private int btnSelected = 0;

    @Override
    public void onSomeViewClick(int idView) {
        switch (idView) {
            case R.id.btnSelectorAlcance:
                btnSelected = 1;
                List<Object> items = new ArrayList<>();
                if (!listAlcanceMensaje.isEmpty()) {
                    items.addAll(listAlcanceMensaje);
                    view.showListSingleChooser("Alcance Mensaje", items, 0);
                } else {
                    showMessage("Datos Tipos Faltantes Para Mensaje Predeterninado");
                }

                break;
            case R.id.btnSelectorObjetivo:
                btnSelected = 2;
                List<Object> items2 = new ArrayList<>();
                if (!listObjetivoMensaje.isEmpty()) {
                    items2.addAll(listObjetivoMensaje);
                    view.showListSingleChooser("Objetivo Mensaje", items2, 0);
                } else {
                    showMessage("Datos Tipos Faltantes Para Mensaje Predeterninado");
                }
                break;

            case R.id.menu_create:
                view.getImputs();
                break;
            case android.R.id.home:
                view.closeActivity();
                break;

        }
    }

    @Override
    public void setImputs(TextInputEditText txtAsuntoMensajePred, TextInputEditText txtPresentacionMensajePred,
                          TextInputEditText txtCabeceraMensajePred, TextInputEditText txtCuerpoMensajePred,
                          TextInputEditText txtDespedidaMensajePred) {
        errors = 0;
        validateImputs(txtAsuntoMensajePred);
        validateImputs(txtPresentacionMensajePred);
        validateImputs(txtCabeceraMensajePred);
        validateImputs(txtCuerpoMensajePred);
        validateImputs(txtDespedidaMensajePred);

        if (errors <= 4)
            createMensajePred(txtAsuntoMensajePred.getText().toString(),
                    txtPresentacionMensajePred.getText().toString(),
                    txtCabeceraMensajePred.getText().toString(),
                    txtCuerpoMensajePred.getText().toString(),
                    txtDespedidaMensajePred.getText().toString());
        if (errors >= 5) view.showImportantMessage(res.getString(R.string.mensaje_predeter_error));
    }

    private int errors;

    private void validateImputs(TextInputEditText txtImput) {
        if (txtImput.getText().toString().equals("")) {
            errors++;
        }
    }

    private void createMensajePred(String asunto, String presentacion, String cabecera, String cuerpo, String despedida) {

        if (asunto == null) asunto = "";
        if (presentacion == null) presentacion = "";
        if (cabecera == null) cabecera = "";
        if (cuerpo == null) cuerpo = "";
        if (despedida == null) despedida = "";


        handler.execute(createMensajePredeterminadoUseCase,
                new CreateMensajePredeterminadoUseCase.RequestValues(
                        asunto,
                        presentacion,
                        cabecera,
                        cuerpo,
                        despedida,
                        idAlcanceSelected,
                        idObjetivoSelected,
                        keyMensajePred
                ),
                new UseCase.UseCaseCallback<CreateMensajePredeterminadoUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(CreateMensajePredeterminadoUseCase.ResponseValue response) {
                        showFinalMessage("Mensaje Predeterminado Creado");
                    }

                    @Override
                    public void onError() {
                        showImportantMessage("Ha Ocurrido un error vuelve a intentarlo");
                    }
                });
    }
}
