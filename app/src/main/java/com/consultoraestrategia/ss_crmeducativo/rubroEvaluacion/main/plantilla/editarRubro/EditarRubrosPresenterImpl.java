package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.GuardarCambios;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerDatosFormaEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerDatosNivelLogro;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerDatosTipoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerTipoFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.ObtenerValorRedondeo;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.TipoEvaluacionLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.TipoFormaLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase.TipoNotaLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNota;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class EditarRubrosPresenterImpl extends BasePresenterImpl<EditarRubrosView> implements EditarRubrosPresenter {

    public static final String TAG = EditarRubrosPresenterImpl.class.getSimpleName();
    private final ObtenerTipoFormula obtenerTipoFormula;
    private final ObtenerValorRedondeo obtenerValorRedondeo;

    private TipoEvaluacionLista tipoEvaluacionLista;
    private TipoFormaLista tipoFormaLista;
    private TipoNotaLista tipoNotaLista;
    private ObtenerDatosFormaEvaluacion obtenerDatosFormaEvaluacion;
    private ObtenerDatosNivelLogro obtenerDatosNivelLogro;
    private ObtenerDatosTipoEvaluacion obtenerDatosTipoEvaluacion;
    private GuardarCambios guardarCambios;
    private GetTipoNota getTipoNotaGlobal;
    private int programaEducatId;

    public EditarRubrosPresenterImpl(UseCaseHandler handler, Resources res, TipoEvaluacionLista tipoEvaluacionLista, TipoFormaLista tipoFormaLista,
                                         TipoNotaLista tipoNotaLista,
                                         ObtenerDatosFormaEvaluacion obtenerDatosFormaEvaluacion,
                                         ObtenerDatosNivelLogro obtenerDatosNivelLogro,
                                         ObtenerDatosTipoEvaluacion obtenerDatosTipoEvaluacion,
                                         GuardarCambios guardarCambios,
                                     GetTipoNota getTipoNotaGlobal,
                                     ObtenerTipoFormula obtenerTipoFormula,
                                     ObtenerValorRedondeo obtenerValorRedondeo
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
        this.obtenerTipoFormula = obtenerTipoFormula;
        this.obtenerValorRedondeo = obtenerValorRedondeo;
    }

    @Override
    protected String getTag() {
        return null;
    }

    private TipoFormaUi tipoFormaSelected;
    private TipoEvaluacionUi tipoEvaluacionSelected;
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
            Log.d(TAG, "Que hacces papu?");
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



    CapacidadUi capacidadUi;
    RubroProcesoUi rubroProcesoUi;

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        capacidadUi = Parcels.unwrap(extras.getParcelable(FragmentAbstract.EXTRA_CAPACIDAD));
        rubroProcesoUi = Parcels.unwrap(extras.getParcelable(FragmentAbstract.EXTRA_RUBRO));
        programaEducatId = extras.getInt(FragmentAbstract.EXTRA_PROGRAMA_EDUCATIVO);
        Log.d(TAG,"rubros : "+ rubroProcesoUi.getSubTitulo()+ "/    "+ rubroProcesoUi.getTitulo());
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

    private void initView() {
        if (view != null) {
            view.mostrarTitulos(rubroProcesoUi.getTitulo(), rubroProcesoUi.getSubTitulo());
        }
    }


    private List<TipoNotaUi> tipoNotaUiList;

    private void initCasoUsoTipoNota() {
        handler.execute(tipoNotaLista,
                new TipoNotaLista.RequestValues(rubroProcesoUi),
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
                new TipoFormaLista.RequestValues(rubroProcesoUi),
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
                new TipoEvaluacionLista.RequestValues(rubroProcesoUi),
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

    private void obtenerDatosRub() {
        initObtenerRegistroFormaEvaluacion(rubroProcesoUi);
        getTipoNotaGlobalDefault(rubroProcesoUi);
        iniObtenerRegistroTipoEvaluacion(rubroProcesoUi);
        initObtenerTipoFormula(rubroProcesoUi);
        initObtenerRegistroValorFormula(rubroProcesoUi);
        tipoFormula();
    }

    private void tipoFormula() {
        if (rubroProcesoUi.getTipoFormulaId() > 0){
            if(view!=null)view.changeRubroFormula();
        }else {
            if(view!=null)view.changeRubSimple();
        }

    }

    private String keyTipoNotaId;

    private void initObtenerRegistroValorFormula(RubroProcesoUi rubroProcesoUi) {
        handler.execute(obtenerValorRedondeo,
                new ObtenerValorRedondeo.RequestValues(rubroProcesoUi),
                new UseCase.UseCaseCallback<ObtenerValorRedondeo.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerValorRedondeo.ResponseValue response) {
                        mostrarCajaTextoValorFormula(response.getNombre());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void mostrarCajaTextoValorFormula(String nombre) {
        if (view != null) {
            view.mostrarCajaTextoValorFormula(nombre);
        }
    }


    private void initObtenerTipoFormula(RubroProcesoUi rubroProcesoUi) {
        handler.execute(obtenerTipoFormula,
                new ObtenerTipoFormula.RequestValues(rubroProcesoUi),
                new UseCase.UseCaseCallback<ObtenerTipoFormula.ResponseValue>() {
                    @Override
                    public void onSuccess(ObtenerTipoFormula.ResponseValue response) {
                        mostrarCajaTextoTipoFormula(response.getNombre());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void mostrarCajaTextoTipoFormula(String nombre) {
        if (view != null) {
            view.mostrarCajaTextoTipoFormula(nombre);
        }
    }

    private void mostrarCajaTextoTipoNota(String nombreTipoNota) {
        if (view != null) {
            view.mostrarCajaTextoTipoNota(nombreTipoNota);
        }
    }

    private String keyTipoFormaEvaluacion;

    private void initObtenerRegistroFormaEvaluacion(RubroProcesoUi rubroProcesoUi) {
        handler.execute(obtenerDatosFormaEvaluacion,
                new ObtenerDatosFormaEvaluacion.RequestValues(rubroProcesoUi),
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


    private String keyTipoEvaluacionId;

    private void iniObtenerRegistroTipoEvaluacion(RubroProcesoUi rubroProcesoUi) {
        handler.execute(obtenerDatosTipoEvaluacion,
                new ObtenerDatosTipoEvaluacion.RequestValues(rubroProcesoUi),
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
    @Override
    public void onTipoEvaluacionClicked() {
        if (view != null) {
            List<Object> items = new ArrayList<>();
            items.addAll(tipoEvaluacionUiList);
            view.showListSingleChooser("Tipo Evaluación ", items, -1);
        }
    }

    @Override
    public void onTipoFormaEvaluacionClicked() {
        if (view != null) {
            List<Object> items = new ArrayList<>();
            items.addAll(tipoFormaEvaluacionUiList);
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
    private boolean isValideString(String name) {
        return name != null && !name.isEmpty() && !name.trim().isEmpty();
    }
    private void mostrarMensaje(String mostrarMensaje) {
        if (view != null) {
            view.mostrarMensaje(mostrarMensaje);
        }
    }


    private void initCasoUsoGuardarCambios(String titulo, String alias) {
        handler.execute(guardarCambios,
                new GuardarCambios.RequestValues(capacidadUi,rubroProcesoUi, titulo, alias, keyTipoEvaluacionId, keyTipoFormaEvaluacion),
                new UseCase.UseCaseCallback<GuardarCambios.ResponseValue>() {
                    @Override
                    public void onSuccess(GuardarCambios.ResponseValue response) {
                        if (view != null) {
                            view.mostrarMensaje(res.getString(R.string.datos_actualizado_correctamente));
                            view.onSuccessMensaje(response.getCapacidadUi(),response.getRubroProcesoUi(), programaEducatId);

                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void getTipoNotaGlobalDefault(RubroProcesoUi rubroProcesoUi) {
        handler.execute(getTipoNotaGlobal,
                new GetTipoNota.RequestValues(rubroProcesoUi.getTipoNotaId()),
                new UseCase.UseCaseCallback<GetTipoNota.ResponseValue>() {
                    @Override
                    public void onSuccess(GetTipoNota.ResponseValue response) {
                        Log.e(getTag(), "getTipoNota onSuccess");
                        showTipoNotaSelected(response.getTipoNotaUi());
                        keyTipoNotaId = response.getTipoNotaUi().getId();

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
