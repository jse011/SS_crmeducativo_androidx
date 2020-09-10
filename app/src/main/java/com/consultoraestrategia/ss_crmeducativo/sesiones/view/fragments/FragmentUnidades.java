package com.consultoraestrategia.ss_crmeducativo.sesiones.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.BaseTabFragmentView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.FragmentUnidadesView;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase.SaveSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase.UpdateToogleUnidad;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.presenter.UnidadesPresenter;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesRepository;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase.GetUnidadesList;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.presenter.UnidadesPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.AdapterUnidades;
import com.consultoraestrategia.ss_crmeducativo.sesiones.view.adapters.SesionColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.activities.TabsCursoDocenteActivity;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.view.TabsSesionesActivityV2;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.view.UnidadActivity;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irvinmarin on 23/02/2017.
 */


public class FragmentUnidades extends Fragment implements AdapterUnidades.UnidadesListener, BaseTabFragmentView, FragmentUnidadesView {

    private String TAG = FragmentUnidades.class.getSimpleName();

    private boolean changeUnidades;
    Context context;
    @BindView(R.id.mensaje)
    TextView mensaje;
    @BindView(R.id.rv_Unidades)
    RecyclerView rvUnidades;
    @BindView(R.id.progressBar6)
    ProgressBar progressBar;


    UnidadesPresenter presenter;
    AdapterUnidades listAdapter;

    public static FragmentUnidades newInstance() {
        FragmentUnidades fragment = new FragmentUnidades();
        return fragment;
    }

    public static FragmentUnidades newInstance(Bundle args) {
        FragmentUnidades fragment = new FragmentUnidades();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_unidades, container, false);

        ButterKnife.bind(this, view);
        context = getContext();
        initPresentador();
        return view;
    }

    private void initPresentador() {
        UnidadesRepository unidadesRepository = new UnidadesRepository(new UnidadesLocalDataSource(InjectorUtils.provideRubroProcesoDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideCalendarioPeriodo()));
        presenter= new UnidadesPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), new GetUnidadesList(unidadesRepository), new UpdateToogleUnidad(unidadesRepository), new SaveSesionAprendizaje(unidadesRepository));
        setPresenter(presenter);
     }

    @Override
    public void setPresenter(UnidadesPresenter presenter) {
        presenter.attachView(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setExtras(getArguments());
        setUnidadesAprendizaje();
        presenter.onViewCreated();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }


    @Override
    public void onClickSesionListener(View view, SesionAprendizajeUi sesionAprendizaje ) {
        presenter.onClickSesion(sesionAprendizaje);
    }

    @Override
    public void onChangeSesionListener() {
        changeUnidades = true;
    }

    @Override
    public void SaveSesionAprendizaje(SesionAprendizajeUi SesionAprendizajeUi) {
        presenter.saveSesionAprendizaje(SesionAprendizajeUi);
    }

    @Override
    public void onClickAprendizaje(UnidadAprendizajeUi unidad) {
        presenter.startUnidadDetalle(unidad);
    }

    @Override
    public void onClickVerMas(UnidadAprendizajeUi unidadAprendizaje) {
        presenter.onClickVerMas(unidadAprendizaje);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        CRMBundle crmBundle = new CRMBundle(getArguments());
        if (changeUnidades){
            CallService.jobServiceExportarTipos(getContext(), TipoExportacion.SESIONES);
            SimpleSyncIntenService.start(getContext(), crmBundle.getProgramaEducativoId());
            SynckService.start(getContext(),crmBundle.getProgramaEducativoId());
        }
    }

    private void setUnidadesAprendizaje(){

        int mint_backgroudColor = ContextCompat.getColor(context, R.color.colorPrimary);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        rvUnidades.setLayoutManager(linearLayoutManager);
        rvUnidades.setHasFixedSize(true);
        ((SimpleItemAnimator) rvUnidades.getItemAnimator()).setSupportsChangeAnimations(false);
        listAdapter = new AdapterUnidades(new ArrayList<UnidadAprendizajeUi>(), this, mint_backgroudColor);
        rvUnidades.setAdapter(listAdapter);
    }


    @Override
    public void onResumeFragment() {
        //presenter.onResumeFragment();
    }


    @Override
    public void showUnidadesList(List<UnidadAprendizajeUi> unidadesList, int programaEducativoId) {
       listAdapter.setUnidadesList(unidadesList, programaEducativoId);
    }



    @Override
    public void clearUnidades() {
        listAdapter.clearUnidades();
    }

    @Override
    public void actualizarUnidadesPeriodo(String idCalendarioPeriodo, boolean status) {
        CRMBundle crmBundle= new CRMBundle(getArguments());
        crmBundle.setCalendarioPeriodoId(Integer.parseInt(idCalendarioPeriodo));
        crmBundle.setCalendarioActivo(status);
        if(getArguments()!=null)
            getArguments().putAll(crmBundle.instanceBundle());
        presenter.onResumeFragment(idCalendarioPeriodo);
    }

    @Override
    public void showTextVaciounidades() {
        mensaje.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTextVaciounidades() {
        mensaje.setVisibility(View.GONE);

    }

    @Override
    public void showTabSesiones(int parametroDisenioId, int personaId, int idCargaCurso, int cursoId, int cargaAdemicaId, int backgroudColor, SesionAprendizajeUi sesionAprendizaje, int entidadId, int georeferenciaId) {
        Intent intent = TabsSesionesActivityV2.createTabSesionesIntent(getContext(), TabsSesionesActivityV2.class,getArguments(), sesionAprendizaje, personaId, backgroudColor, idCargaCurso, cursoId, cargaAdemicaId, parametroDisenioId, entidadId, georeferenciaId);
        if( getActivity()!=null)getActivity().startActivityForResult(intent, TabsCursoDocenteActivity.CHANGE_DATABASE_TABSESION);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void startActivityUnidadDetalle(final UnidadParametros unidadParametros) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), UnidadActivity.class);
                intent.putExtras(unidadParametros.getBundle());
                startActivity(intent);
            }
        },300);

    }

    @Override
    public void updateItem(UnidadAprendizajeUi unidadAprendizajeUi) {
        listAdapter.updateItem(unidadAprendizajeUi);
    }

    @Override
    public int getColumnasSesionesList() {
        return SesionColumnCountProvider.columnsForWidth(getContext(), rvUnidades.getWidth());
    }


    public void changeOrientationUnidad() {
        rvUnidades.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(presenter!=null)presenter.onConfigurationChanged();
            }
        },1000);

    }
}
