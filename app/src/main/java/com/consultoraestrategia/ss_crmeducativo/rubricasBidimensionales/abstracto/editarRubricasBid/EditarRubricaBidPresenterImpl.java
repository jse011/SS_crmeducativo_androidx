package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.GuardarCambios;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.ObtenerDatosFormaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.ObtenerDatosNivelLogro;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.ObtenerDatosTipoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.TipoEvaluacionLista;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.TipoFormaLista;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase.TipoNotaLista;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNota;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 08/06/2018.
 */

public class EditarRubricaBidPresenterImpl extends BasePresenterImpl<EditarRubricaBidView> implements EditarRubricaBidPresenter {

    public static final String TAG_EDITAR_RUBRICA_BID = EditarRubricaBidPresenterImpl.class.getSimpleName();
    private RubBidUi rubBidUi;
    private TipoEvaluacionLista tipoEvaluacionLista;
    private TipoFormaLista tipoFormaLista;
    private TipoNotaLista tipoNotaLista;
    private ObtenerDatosFormaEvaluacion obtenerDatosFormaEvaluacion;
    private ObtenerDatosNivelLogro obtenerDatosNivelLogro;
    private ObtenerDatosTipoEvaluacion obtenerDatosTipoEvaluacion;
    private GuardarCambios guardarCambios;
    private GetTipoNota getTipoNotaGlobal;

    public EditarRubricaBidPresenterImpl(UseCaseHandler handler, Resources res, TipoEvaluacionLista tipoEvaluacionLista, TipoFormaLista tipoFormaLista,
                                         TipoNotaLista tipoNotaLista,
                                         ObtenerDatosFormaEvaluacion obtenerDatosFormaEvaluacion,
                                         ObtenerDatosNivelLogro obtenerDatosNivelLogro,
                                         ObtenerDatosTipoEvaluacion obtenerDatosTipoEvaluacion,
                                         GuardarCambios guardarCambios,
                                         GetTipoNota getTipoNotaGlobal
    ) {
        super(handler, res);
        this.tipoEvaluacionLista = tipoEvaluacionLista;
        this.tipoFormaLista = tipoFormaLista;
        this.tipoNotaLista = tipoNotaLista;
        this.obtenerDatosFormaEvaluacion = obtenerDatosFormaEvaluacion;
        this.obtenerDatosNivelLogro = obtenerDatosNivelLogro;
        this.obtenerDatosTipoEvaluacion = obtenerDatosTipoEvaluacion;
        this.guardarCambios = guardarCambios;
        this.getTipoNotaGlobal = getTipoNotaGlobal;
    }

    private TipoFormaUi tipoFormaSelected;
    private TipoEvaluacionUi tipoEvaluacionSelected;
    private int programaEducativoId;

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

        if (singleItem instanceof TipoFormaUi) {
            tipoFormaSelected = (TipoFormaUi) singleItem;
            for (TipoFormaUi tipoFormulaUi : tipoFormaEvaluacionUiList) {
                if (tipoFormulaUi.getNombre().equals(tipoFormaSelected.getNombre())) {
                    tipoFormaSelected = tipoFormulaUi;
                    break;
                }
            }
            if (tipoFormaSelected == null) {
                showImportantMessage(res.getString(R.string.unknown_error));
                return;
            }
            mostrarTipoSeleccionForma(tipoFormaSelected);
        } else if (singleItem instanceof TipoEvaluacionUi) {

            tipoEvaluacionSelected = (TipoEvaluacionUi) singleItem;
            for (TipoEvaluacionUi tipoEvaluacionUi : tipoEvaluacionUiList) {
                if (tipoEvaluacionUi.getNombre().equals(tipoEvaluacionSelected.getNombre())) {
                    tipoEvaluacionSelected = tipoEvaluacionUi;
                    break;
                }
            }
            if (tipoEvaluacionSelected == null) {
                showImportantMessage(res.getString(R.string.unknown_error));
                return;
            }
            mostrarTipoSeleccionEvaluacion(tipoEvaluacionSelected);
        } else {
            Log.d(TAG_EDITAR_RUBRICA_BID, "Que hacces papu?");
        }
    }

    @Override
    public void onCLickAcceptButtom() {

    }

    private void mostrarTipoSeleccionEvaluacion(TipoEvaluacionUi tipoEvaluacionSelected) {
        this.keyTipoEvaluacionId = String.valueOf(tipoEvaluacionSelected.getId());
        if (view != null) view.mostrarTipoEvaluacion(tipoEvaluacionSelected.getNombre());
    }

    private void mostrarTipoSeleccionForma(TipoFormaUi tipoFormaSelected) {
        this.keyTipoFormaEvaluacion = String.valueOf(tipoFormaSelected.getId());
        if (view != null) view.mostrarTipoFormaSelected(tipoFormaSelected.getNombre());
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        obtenerDatosRub();
        initCasoUsoTipoEvaluacion();
        initCasoUsoTipoForma();
        initCasoUsoTipoNota();
        initView();
    }

    private void obtenerDatosRub() {
        initObtenerRegistroFormaEvaluacion(rubBidUi);
        initObtenerRegistroTipoNota(rubBidUi);
        iniObtenerRegistroTipoEvaluacion(rubBidUi);
    }

    private String keyTipoEvaluacionId;

    private void iniObtenerRegistroTipoEvaluacion(RubBidUi rubBidUi) {
        handler.execute(obtenerDatosTipoEvaluacion,
                new ObtenerDatosTipoEvaluacion.RequestValues(rubBidUi),
                new UseCase.UseCaseCallback<ObtenerDatosTipoEvaluacion.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerDatosTipoEvaluacion.ResponseValue response) {
                        keyTipoEvaluacionId = response.getKey();
                        mostrarCajaTextoTipoEvaluacion(response.getNombre());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void mostrarCajaTextoTipoEvaluacion(String nombreTipoEvaluacion) {
        if (view != null) {
            view.mostrarCajaTextoTipoEvaluacion(nombreTipoEvaluacion);
        }
    }

    private String keyTipoNotaId;

    private void initObtenerRegistroTipoNota(RubBidUi rubBidUi) {
        handler.execute(obtenerDatosNivelLogro,
                new ObtenerDatosNivelLogro.RequestValues(rubBidUi),
                new UseCase.UseCaseCallback<ObtenerDatosNivelLogro.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerDatosNivelLogro.ResponseValue response) {
                        keyTipoNotaId = response.getKey();
                        getTipoNotaGlobalDefault(keyTipoNotaId);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void mostrarCajaTextoTipoNota(String nombreTipoNota) {
        if (view != null) {
            view.mostrarCajaTextoTipoNota(nombreTipoNota);
        }
    }

    private String keyTipoFormaEvaluacion;

    private void initObtenerRegistroFormaEvaluacion(RubBidUi rubBidUi) {
        handler.execute(obtenerDatosFormaEvaluacion,
                new ObtenerDatosFormaEvaluacion.RequestValues(rubBidUi),
                new UseCase.UseCaseCallback<ObtenerDatosFormaEvaluacion.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerDatosFormaEvaluacion.ResponseValue response) {
                        keyTipoFormaEvaluacion = response.getKey();
                        mostrarCajaTextoTipoFormaEvaluacion(response.getNombre());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void mostrarCajaTextoTipoFormaEvaluacion(String nombreTipoEvaluacion) {
        if (view != null) {
            view.mostrarCajaTextoTipoFormaEvaluacion(nombreTipoEvaluacion);
        }
    }

    private List<TipoNotaUi> tipoNotaUiList;

    private void initCasoUsoTipoNota() {
        handler.execute(tipoNotaLista,
                new TipoNotaLista.RequestValues(rubBidUi),
                new UseCase.UseCaseCallback<TipoNotaLista.ResponseValue>() {
                    @Override
                    public void onSuccess(TipoNotaLista.ResponseValue response) {
                        if (response.getTipoNotaUiList() != null) {
                            tipoNotaUiList = response.getTipoNotaUiList();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private List<TipoFormaUi> tipoFormaEvaluacionUiList;

    private void initCasoUsoTipoForma() {
        handler.execute(tipoFormaLista,
                new TipoFormaLista.RequestValues(rubBidUi),
                new UseCase.UseCaseCallback<TipoFormaLista.ResponseValue>() {
                    @Override
                    public void onSuccess(TipoFormaLista.ResponseValue response) {
                        if (response.getTipoNotaUiList() != null) {
                            tipoFormaEvaluacionUiList = response.getTipoNotaUiList();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private List<TipoEvaluacionUi> tipoEvaluacionUiList;

    private void initCasoUsoTipoEvaluacion() {
        handler.execute(tipoEvaluacionLista,
                new TipoEvaluacionLista.RequestValues(rubBidUi),
                new UseCase.UseCaseCallback<TipoEvaluacionLista.ResponseValue>() {
                    @Override
                    public void onSuccess(TipoEvaluacionLista.ResponseValue response) {
                        if (response.getTipoNotaUiList() != null) {
                            tipoEvaluacionUiList = response.getTipoNotaUiList();
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    protected String getTag() {
        return TAG_EDITAR_RUBRICA_BID;
    }

    @Override
    public void setExtras(Bundle extras) {
        if (extras == null) return;
        this.rubBidUi = Parcels.unwrap(extras.getParcelable(EditarRubricasBidActivity.RUBRICA_BID));
        this.programaEducativoId = extras.getInt(EditarRubricasBidActivity.EXTRAS_PROGRAMA_EDUCATIVO_ID);

    }

    private void initView() {
        if (view != null) {
            view.mostrarTitulos(rubBidUi.getTitle(), rubBidUi.getAlias());
        }
    }

    @Override
    public void onTipoEvaluacionClicked() {
        if (view != null) {
            List<Object> items = new ArrayList<>();
            if(tipoEvaluacionUiList!=null)items.addAll(tipoEvaluacionUiList);
            view.showListSingleChooser("Tipo Evaluación ", items, -1);
        }
    }

    @Override
    public void onTipoFormaEvaluacionClicked() {
        if (view != null) {
            List<Object> items = new ArrayList<>();
            if(tipoEvaluacionUiList!=null)items.addAll(tipoFormaEvaluacionUiList);
            view.showListSingleChooser("Tipo Evaluación ", items, -1);
        }
    }

    @Override
    public void onGuardarCambios(String titulo, String alias) {
        if (!isValideString(titulo)) {
            mostrarMensaje(res.getString(R.string.datos_correctamente_validar));
            return;
        }
        /*if (!isValideString(alias)) {
            mostrarMensaje(res.getString(R.string.datos_correctamente_validar));
            return;
        }*/
        if (!isValideString(keyTipoEvaluacionId)) {
            mostrarMensaje(res.getString(R.string.datos_correctamente_validar));
            return;
        }
        if (!isValideString(keyTipoFormaEvaluacion)) {
            mostrarMensaje(res.getString(R.string.datos_correctamente_validar));
            return;
        }
        initCasoUsoGuardarCambios(titulo, alias);
    }

    private void initCasoUsoGuardarCambios(String titulo, String alias) {
        handler.execute(guardarCambios,
                new GuardarCambios.RequestValues(rubBidUi, titulo, alias, keyTipoEvaluacionId, keyTipoFormaEvaluacion),
                new UseCase.UseCaseCallback<GuardarCambios.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarCambios.ResponseValue response) {
                        if (view != null) {
                            view.mostrarMensaje(res.getString(R.string.datos_actualizado_correctamente));
                            view.onSuccessMensaje(response.getRubBidUi(), programaEducativoId);

                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    private boolean isValideString(String name) {
        return name != null && !name.isEmpty() && !name.trim().isEmpty();
    }

    private void mostrarMensaje(String mostrarMensaje) {
        if (view != null) {
            view.mostrarMensaje(mostrarMensaje);
        }
    }

    private void getTipoNotaGlobalDefault(String keyTipoNotaId) {

        handler.execute(getTipoNotaGlobal,
                new GetTipoNota.RequestValues(keyTipoNotaId),
                new UseCase.UseCaseCallback<GetTipoNota.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoNota.ResponseValue response) {
                        Log.e(getTag(), "getTipoNota onSuccess");
                        showTipoNotaSelected(response.getTipoNotaUi());

                    }

                    @Override
                    public void onError() {
                        Log.e(getTag(), "getTipoNotas onError");
                        showImportantMessage(res.getString(R.string.createrubbid_error_getting_tiponota));
                    }
                });
    }

    private void showTipoNotaSelected(com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi tipoNotaUi) {
        if (view != null) view.showTipoNotaSelected(tipoNotaUi);
    }
}
