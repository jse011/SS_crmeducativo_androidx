package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.PresenterAsistenciaCurso;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.PresenterAsistenciaCursoImpl;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.adapter.AsistenciaCursoListaAdapter;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.ui.AsistenciaActivity;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaBundle;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.dialogAsistencia.AsistenciaDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetFechaAsistenciaBimentreAgrupadas;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.listener.AsistenciaCursoListener;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AsistenciaCursoFragment extends BaseFragment<ViewAsistenciaCurso, PresenterAsistenciaCurso, AsistenciaCursoListener> implements ViewAsistenciaCurso, AsistenciaCursoListener {

    @BindView(R.id.progressBar5)
    ProgressBar progressBar5;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.rc_asistenica_alumnos)
    RecyclerView rcAsistenicaAlumnos;

    public static final String TAG = AsistenciaCursoFragment.class.getSimpleName();
    public AsistenciaCursoListaAdapter adapterAsistencia;
    @BindView(R.id.txtvacio)
    TextView txtvacio;

    public static AsistenciaCursoFragment newInstance(CRMBundle crmBundle) {
        AsistenciaCursoFragment fragment = new AsistenciaCursoFragment();
        fragment.setArguments(crmBundle.instanceBundle());
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CRMBundle crmBundle = new CRMBundle(getArguments());
        setupAdapter(crmBundle);
    }

    public void setupAdapter(CRMBundle crmBundle) {
        rcAsistenicaAlumnos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterAsistencia = new AsistenciaCursoListaAdapter(new ArrayList<Object>(), this, crmBundle.getCalendarioPeriodoId());
        rcAsistenicaAlumnos.setAdapter(adapterAsistencia);
        rcAsistenicaAlumnos.setHasFixedSize(true);
    }

    @Override
    protected String getLogTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected PresenterAsistenciaCurso getPresenter() {
        return new PresenterAsistenciaCursoImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetFechaAsistenciaBimentreAgrupadas(
                        new AsistenciaAlumnoRepository(
                                new AsistenciaAlumnoLocalDataSource(InjectorUtils.provideAsistenciaSesionAlumnoDao(),
                                        InjectorUtils.provideTipoNotaDao(), InjectorUtils.provideValorTipoNotaDao(),InjectorUtils.provideParametrosDisenioDao())
                        )));
    }

    @Override
    protected ViewAsistenciaCurso getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_asistencia_curso, container, false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar5;
    }

    @Override
    public void onResumenFragment(String idCalendarioPeriodo) {
        presenter.onResumenFragment(idCalendarioPeriodo);
    }

    @Override
    public void showListAsistenciaCursos(List<Object> asistenciaUiList, int calendarioPeriodoId) {
        if (asistenciaUiList.size()>0){
            adapterAsistencia.setList(asistenciaUiList, calendarioPeriodoId);
            rcAsistenicaAlumnos.scrollToPosition(asistenciaUiList.size() - 1);
            showListFull();
        }else showListEmpty();
    }

    private void showListFull(){
        txtvacio.setVisibility(View.GONE);
        //rcAsistenicaAlumnos.setVisibility(View.VISIBLE);
    }

    private void showListEmpty(){
        txtvacio.setVisibility(View.VISIBLE);
        //rcAsistenicaAlumnos.setVisibility(View.GONE);
    }

    @Override
    public void showAsistenciaActivity(AsistenciaUi asistenciaUi) {
        Intent intent = AsistenciaActivity.getAsistenciaActivity(getContext(), new AsistenciaBundle(asistenciaUi));
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    public void onParentFabClicked() {
//        CRMBundle bundle = new CRMBundle(getArguments());
//        FragmentManager manager = getFragmentManager();
//        CalendarioCursoFragment calendarioCursoFragment = CalendarioCursoFragment.newInstance(bundle.getCargaCursoId(), bundle.getGeoreferenciaId(),bundle.getCalendarioPeriodoId(),bundle.getCalendarioPeriodoId(), bundle.getEmpleadoId());
//        calendarioCursoFragment.setTargetFragment(AsistenciaCursoFragment.this, 200);
//        calendarioCursoFragment.show(manager, "CalendarioCursoFragment");
    }

    @Override
    public void showAsistenciaCursoItemDialog(FechaAsistenciaUi fechaAsistenciaUi) {
        AsistenciaUi asistenciaUi = new AsistenciaUi();
        CRMBundle bundle = presenter.getCrmBundle();
        asistenciaUi.setIdCargaCurso(bundle.getCargaCursoId());
        asistenciaUi.setIdCalendarioPeriodo(bundle.getCalendarioPeriodoId());
        asistenciaUi.setIdDocente(bundle.getEmpleadoId());
        asistenciaUi.setIdGeoreferencia(bundle.getGeoreferenciaId());
        asistenciaUi.setIdProgramaEducativo(bundle.getProgramaEducativoId());
        asistenciaUi.setFecha(fechaAsistenciaUi.getFechaAsistencia());
        asistenciaUi.setIdCargaAcademica(bundle.getCargaAcademicaId());
        asistenciaUi.setColor(fechaAsistenciaUi.getParametroDisenioUi().getColor1());
        AsistenciaDialogFragment fragment = AsistenciaDialogFragment.newInstance(new AsistenciaBundle(asistenciaUi));
        fragment.show(getFragmentManager(), AsistenciaDialogFragment.class.getSimpleName());
    }

    @Override
    public void onClickAsistencia(FechaAsistenciaUi fechaAsistenciaUi) {
        presenter.onClickAsistencia(fechaAsistenciaUi);
    }

    public void updateCalendar(CRMBundle crmBundle) {
        showListFull();
        presenter.setExtras(crmBundle.instanceBundle());
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
