package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.RubricasAbstractPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.RubricasView;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.EliminarRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.GetActualizasRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.EditarRubricasBidActivity;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import org.parceler.Parcels;

import java.util.List;

/**
 * Created by CCIE on 07/03/2018.
 */

public abstract class RubricasAbstractPresenterImpl implements RubricasAbstractPresenter {

    private static String RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG = RubricasAbstractPresenterImpl.class.getSimpleName();
    public static int REGISTRO_ERROR = 0;
    public static int REGISTRO_SUCCESS = 1;
    public static int REGISTRO_MESSAGE = 2;


    protected RubricasView view;
    protected UseCaseHandler handler;
    // protected GetRubricas getRubricasLista;
    protected GetActualizasRubricas getActualizasRubricas;
    protected int cargaCursoId;
    protected int programaEDucativoId;
    private EliminarRubricas eliminarRubricas;
    private PublicarTodasEvaluacion publicarTodasEvaluacion;

    public RubricasAbstractPresenterImpl(UseCaseHandler handler, GetActualizasRubricas getActualizasRubricas, EliminarRubricas eliminarRubricas, PublicarTodasEvaluacion publicarTodasEvaluacion) {
        this.handler = handler;
        this.getActualizasRubricas = getActualizasRubricas;
        this.eliminarRubricas = eliminarRubricas;
        this.publicarTodasEvaluacion = publicarTodasEvaluacion;

    }

    @Override
    public void setExtras(Bundle extras) {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "setExtras");
    }

    @Override
    public void attachView(RubricasView view) {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "attachView");
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onCreate");
    }

    @Override
    public void onStart() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onBackPressed");
    }

    @Override
    public void onCreateView() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onCreateView");
    }

    @Override
    public void onViewCreated() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onViewCreated");
    }

    @Override
    public void onDestroyView() {
        view = null;
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onDetach");
    }


    protected abstract void getRubricasLista(ListCallback<RubBidUi> listCallback);

    @Override
    public void onClickBtnAceptarPublicarTodoEvaluaciones(RubBidUi rubBidUi) {
        boolean success = publicarTodasEvaluacion.execute(rubBidUi.getKey());
        if(success){
            rubBidUi.setPublicarEval(RubBidUi.PublicarEval.TODOS);
            if(view!=null)view.editarRubricaBid(rubBidUi);
            if (view!=null)view.succesChangePublicar(programaEDucativoId, rubBidUi.getKey());
        }
    }

    public interface ListCallback<T> {
        void onListLoaded(List<T> items);
    }

    protected void getRubricaBidAbstractList() {
        if (view!=null)view.showTipoRubricaBidimensional();
        getRubricasLista(new ListCallback<RubBidUi>() {
            @Override
            public void onListLoaded(List<RubBidUi> items) {

              /*  view.mostrarListaRubricas(items);
                view.ocultarProgressBar();*/
            }
        });

    }

    @Override
    public void onAttach() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onAttach");
    }

    @Override
    public void onActivityCreated() {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onActivityCreated");
    }


    @Override
    public void actualizarItem(Bundle bundle) {
        // if (bundle == null) return;
        int tipo = bundle.getInt(RubricasAbstractFragment.TIPO_RUBRICA);
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "tipo :" + tipo);
        switch (tipo) {
            case RubricasAbstractFragment.AGREGAR_RUBRICAS_BIDIMENSIONAL:
                int rubroEvaluacionId = bundle.getInt(RubricasAbstractFragment.RUBROEVALUACIONID);
                int countIndicador = bundle.getInt(RubricasAbstractFragment.COUNT_INDICADOR);
                //if (view != null) view.actualizarListaRubrica();
                getActualizacionRubricasBid(rubroEvaluacionId, countIndicador);
                Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "countIndicador : " + countIndicador + "");
                Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "rubroEvaluacionId : " + rubroEvaluacionId + "");
                break;

        }
    }

    @Override
    public void onActualizarRubricaFragment(Intent intent) {
        Bundle bundle = intent.getExtras();
        int tipo = -1;
        if(bundle!=null){
            tipo = bundle.getInt(RubricasAbstractFragment.TIPO_RUBRICA);
        }

        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "tipo :" + tipo);
        switch (tipo) {
            case RubricasAbstractFragment.AGREGAR_RUBRICAS_BIDIMENSIONAL:
                int rubroEvaluacionId = bundle.getInt(RubricasAbstractFragment.RUBROEVALUACIONID);
                int countIndicador = bundle.getInt(RubricasAbstractFragment.COUNT_INDICADOR);
                //   if (view != null) view.actualizarListaRubrica();
                getActualizacionRubricasBid(rubroEvaluacionId, countIndicador);
                Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "countIndicador : " + countIndicador + "");
                Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "rubroEvaluacionId : " + rubroEvaluacionId + "");
                break;
        }
    }

    public void getActualizacionRubricasBid(int rubroEvaluacionId, int countIndicador) {
        handler.execute(getActualizasRubricas,
                new GetActualizasRubricas.RequestValues(rubroEvaluacionId, countIndicador),
                new UseCase.UseCaseCallback<GetActualizasRubricas.ResponseValue>() {
                    @Override
                    public void onSuccess(GetActualizasRubricas.ResponseValue response) {
                        if (view != null) {
                            Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, " LLENO");
                            view.actualizarVista(response.getRubBidUi());
                        }
                        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "getActualizacion : " + response.getRubBidUi().getTitle());
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    @Override
    public void onSelectOpcionMenuRubricas(final RubBidUi rubBidUi, int posicionMenu) {
        //PersonaDao personaDao = InjectorUtils.providePersonaDao();
        //List<PersonaContratoQuery> personaList = personaDao.getAlumnosDeCargaCurso(cargaCursoId);
        switch (posicionMenu) {
            case 0:
                if (view != null) view.iniciarEditarRubrica(rubBidUi);
                break;
            case 1:
                if (view != null) view.mostrarDialogEliminarRubrica(rubBidUi, programaEDucativoId);
                break;
            case 2:
                if (view != null)
                    view.showDialogPublicarTodoEvaluaciones(rubBidUi);
                break;
            case 3:
                if (view != null)
                    //view.showSendMessage(personaList, cargaCursoId, rubBidUi.getKey(), 1);
                break;
        }
    }

    @Override
    public void onAceptEliminar(final RubBidUi rubBidUi, final int programaEducativoId) {
        //Iniciando caso de Uso de Eliminar
        handler.execute(eliminarRubricas,
                new EliminarRubricas.ResquestValues(rubBidUi),
                new UseCase.UseCaseCallback<EliminarRubricas.ResponseValue>() {
                    @Override
                    public void onSuccess(EliminarRubricas.ResponseValue response) {
                        switch (response.getEstado()) {
                            case 0://error
                                mostrarMensajeError("Algo sucedio con nuestro servidor");
                                break;
                            case 1://success
                                mostrarMensajeError("Eliminado Correctamente!!");
                                eliminarRubricaBid(rubBidUi);
                                if (view != null) view.succesDelete(programaEducativoId, rubBidUi.getKey());
                                break;
                            case 2://mensaje
                                mostrarMensajeError(response.getMensaje());
                                break;
                            default:
                                break;

                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void eliminarRubricaBid(RubBidUi rubBidUi) {
        if (view != null) {
            view.eliminarRubricaBid(rubBidUi);
        }
    }

    private void mostrarMensajeError(String mensaje) {
        if (view != null) {
            view.mostrarMensajeSnack(mensaje);
        }
    }

    public RubricasAbstractPresenterImpl() {
        super();
    }

    @Override
    public void onStartActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onStartActivityForResult");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "onActivityResult");
        if (data == null) return;
        if (resultCode == RubricasAbstractFragment.EDITAR_RUBRICABID) {
            Bundle extras = data.getExtras();
            if(extras!=null){
                RubBidUi rubBidUi = Parcels.unwrap(extras.getParcelable(EditarRubricasBidActivity.RUBRICA_BID));
                actualizarItemRubricaBid(rubBidUi);
            }
            Log.d(RUBRICAS_ABASTRACT_PRESENTER_IMPL_TAG, "resultCode");
        }


    }

    private void actualizarItemRubricaBid(RubBidUi rubBidUi) {
        if (view != null) {
            view.editarRubricaBid(rubBidUi);
        }
    }

}
