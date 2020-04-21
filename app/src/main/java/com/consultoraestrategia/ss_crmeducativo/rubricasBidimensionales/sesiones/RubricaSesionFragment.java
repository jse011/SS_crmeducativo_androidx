package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula.RubroEvalRNPFormulaDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso.RubroProcesoDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.RubricasAbstractFragment;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.local.RubricaBidLocal;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.EliminarRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.GetActualizasRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.listener.RefrescarListener;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.RubricaSesionRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.local.RubricaSesionLocal;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.data.source.remote.RubricaSesionRemote;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.domain.useCase.GetRubricaSesionLista;
import com.consultoraestrategia.ss_crmeducativo.sendMessage.sendMessageRubro.ui.SendMessageRubroActivity;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.listener.FragmentLifecycle;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.List;

/**
 * Created by CCIE on 08/03/2018.
 */

public class RubricaSesionFragment extends RubricasAbstractFragment<RubricaSesionFragment, RubricaSesionPresenterImpl> implements FragmentLifecycle {


    public static final String TAG = RubricaSesionFragment.class.getSimpleName();

    RefrescarListener refrescarListener;

    public static RubricaSesionFragment newInstance(Bundle args) {
        RubricaSesionFragment fragment = new RubricaSesionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RubricaSesionPresenterImpl obtenerPresenter() {
        RubricaSesionRepository rubricaSesionRepository = new RubricaSesionRepository(
                new RubricaSesionLocal(InjectorUtils.provideCalendarioPeriodo(),
                        InjectorUtils.provideRubroEvalRNPFormulaDao()),
                new RubricaSesionRemote());
        RubricaBidRepository actualizarRubricaBidRepository = new RubricaBidRepository(new RubricaBidLocal(
                new RubroEvalRNPFormulaDaoImpl(), new RubroProcesoDaoImpl(new RubroEvalRNPFormulaDaoImpl()), InjectorUtils.provideTareaRubroEvaluacionProcesoDao(), InjectorUtils.provideCalendarioPeriodo()
        ));
        return new RubricaSesionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetActualizasRubricas(actualizarRubricaBidRepository),
                new EliminarRubricas(actualizarRubricaBidRepository),
                new PublicarTodasEvaluacion(actualizarRubricaBidRepository),
                new GetRubricaSesionLista(rubricaSesionRepository),
                new GetCalendarioPeriodo(actualizarRubricaBidRepository));
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {

    }
    public void  onResumen(int idperiodo){
        Log.d(TAG,"onResumen " + idperiodo);
        presenter.onResumeFragment(String.valueOf(idperiodo));
    }

    @Override
    public void onResumeFragment(Bundle bundle) {
        //if(bundle == null)return;
        if (presenter == null) return;
        presenter.actualizarItem(bundle);
    }


    @Override
    public void launchCreateRubBidActivity(String idCargaCurso, String idCurso, int  idCalendarioPeriodo, int idProgramaEducativo) {

    }

    @Override
    public void succesDelete(int programaEducativoId, String key) {
        if(refrescarListener!=null)refrescarListener.succesDelete();
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void showSendMessage(List<Persona> personaList, int cargaCursoId, String rubroId, int tipoLayoutMensaje, int programaEducativoId) {
        SendMessageRubroActivity.launchSendMessageRubroActivity(getContext(), personaList, cargaCursoId, rubroId, tipoLayoutMensaje, programaEducativoId);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            refrescarListener = (RefrescarListener) context;
        } catch (Exception e) {
            Log.d(TAG, "ImplementarloActivity : " + e.toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        refrescarListener = null;
    }


}
