package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.CreateRubBidActivity;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.RubricasAbstractFragment;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.local.RubricaBidLocal;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.EliminarRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.GetActualizasRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.listener.RefrescarListener;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.remote.RubricaBidRemote;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.domain.useCase.GetRubricaBidLista;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.ui.SendMessageRubroActivity;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.List;

/**
 * Created by CCIE on 07/03/2018.
 */

public class RubricaBidFragment extends RubricasAbstractFragment<RubricaBidFragment, RubricaBidPresenterImpl> {
    public static final String RUBRICA_FRAGMENT_TAG = RubricaBidFragment.class.getSimpleName();


    public static final int SELECT_RUBRIC_FRAGMENT_AGREGAR = 111;
    private RefrescarListener refrescarListener;

    public RubricaBidFragment() {
    }

    public static RubricaBidFragment newInstance(Bundle args) {
        RubricaBidFragment fragment = new RubricaBidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RubricaBidPresenterImpl obtenerPresenter() {
        com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.RubricaBidRepository repository = new com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.RubricaBidRepository(new com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.data.source.local.RubricaBidLocal(
                InjectorUtils.provideRubroEvalRNPFormulaDao(),
                InjectorUtils.provideCalendarioPeriodo()),
                new RubricaBidRemote());
        RubricaBidRepository actualizarRubricaBidRepository = new RubricaBidRepository(new RubricaBidLocal(
                new RubroEvalRNPFormulaDaoImpl(),
                new RubroProcesoDaoImpl(new RubroEvalRNPFormulaDaoImpl()), InjectorUtils.provideTareaRubroEvaluacionProcesoDao(), InjectorUtils.provideCalendarioPeriodo()
        ));
        return new RubricaBidPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetActualizasRubricas(actualizarRubricaBidRepository),
                new EliminarRubricas(actualizarRubricaBidRepository),
                new PublicarTodasEvaluacion(actualizarRubricaBidRepository),
                new GetRubricaBidLista(repository));
    }

    public int getCalendarioPeriodoId() {
        if (presenter == null) return 0;
        return presenter.getIdCalendarioPeriodoID();
    }


    public void onParentFabClicked() {
        if (presenter != null) presenter.onParentFabClicked();
    }


    @Override
    public void launchCreateRubBidActivity(String idCargaCurso, String idCurso, int idCalendarioPeriodo, int idProgramaEducativo) {
        //CreateRubBidActivity.launchCreateRubBidActivity(getActivity(), idCargaCurso, idCurso, idCalendarioPeriodo, idProgramaEducativo);
        CRMBundle crmBundle = new CRMBundle(getArguments());
        crmBundle.setCargaCursoId(Integer.valueOf(idCargaCurso));
        crmBundle.setCursoId(Integer.valueOf(idCurso));
        crmBundle.setCalendarioPeriodoId(idCalendarioPeriodo);
        crmBundle.setProgramaEducativoId(idProgramaEducativo);
        Intent intent = CreateRubBidActivity.getStartCreateRubBidActivity(getActivity(),crmBundle);
        startActivityForResult(intent, SELECT_RUBRIC_FRAGMENT_AGREGAR);
    }

    @Override
    public void succesDelete(int programaEducativoId, String key) {
        if(refrescarListener!=null)refrescarListener.succesDelete();
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
    }

    @Override
    public void showSendMessage(List<Persona> personaList, int cargaCursoId, String rubBidUi, int tipoLayoutMensaje, int programaEducativoId) {
        SendMessageRubroActivity.launchSendMessageRubroActivity(getContext(), personaList, cargaCursoId, rubBidUi,tipoLayoutMensaje, programaEducativoId);

    }


    public void onActualizarRubricaFragment(Intent intent) {
        presenter.onActualizarRubricaFragment(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //if(refrescarListener!=null)refrescarListener = (RefrescarListener) context;
            if (context instanceof RefrescarListener){
                refrescarListener = (RefrescarListener) context;
            }else {
                throw new ClassCastException(
                        context.toString() + "must implement RefrescarListener");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(RUBRICA_FRAGMENT_TAG, "ImplementarloActivity : " + e.toString());

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        refrescarListener = null;
    }


    @Override
    public void onResumeFragment() {
        //presenter.onResumeFragment();
    }

    public void onActualizarRubricaPeriodo(String idCalendarioPeriodo, boolean status) {

        CRMBundle crmBundle= new CRMBundle(getArguments());
        crmBundle.setCalendarioPeriodoId(Integer.parseInt(idCalendarioPeriodo));
        crmBundle.setCalendarioActivo(status);
        if(getArguments()!=null)
        getArguments().putAll(crmBundle.instanceBundle());
        presenter.onResumeFragment(idCalendarioPeriodo);
    }




}
