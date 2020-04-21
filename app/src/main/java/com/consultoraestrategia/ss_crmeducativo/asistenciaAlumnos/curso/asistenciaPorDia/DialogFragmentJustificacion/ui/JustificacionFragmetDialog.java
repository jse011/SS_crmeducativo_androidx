package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaJustificaBundel;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.listener.JustificacionListener;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.presenter.JustificacionPresenteImpl;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.presenter.JustificacionPresenter;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.ListenerCreateJustificacion;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.ui.FragmentCreate;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.MyFragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.BaseRepositoriFragmento;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioListener;
import com.franmontiel.fullscreendialog.FullScreenDialogContent;
import com.franmontiel.fullscreendialog.FullScreenDialogController;


import java.util.List;

import butterknife.BindView;


public class JustificacionFragmetDialog extends BaseDialogFragment<JustificacionView, JustificacionPresenter, JustificacionListener> implements JustificacionView , View.OnClickListener, ListenerCreateJustificacion, RepositorioListener, FullScreenDialogContent {
    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String RESULT_FULL_NAME = "RESULT_FULL_NAME";

    @BindView(R.id.tab_justificacion)
    TabLayout tab_justificacion;
    @BindView(R.id.vp_justificacion)
    ViewPager vpjustificacion;
    @BindView(R.id.guardar)
    TextView guardar;
    @BindView(R.id.cancelar)
    TextView cancelar;

    private String TAG = JustificacionFragmetDialog.class.getSimpleName();
    private MyFragmentAdapter fragmentAdapter;

    public static JustificacionFragmetDialog newInstance(AsistenciaJustificaBundel asistenciaJustificaBundel) {
        JustificacionFragmetDialog fragment = new JustificacionFragmetDialog();
        fragment.setArguments(asistenciaJustificaBundel.getBundle());
        return fragment;
    }

    public JustificacionFragmetDialog() {
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected JustificacionPresenter getPresenter() {

    presenter= new JustificacionPresenteImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources());
        return presenter;
    }

    @Override
    protected JustificacionView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_justificacion_tabs, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapterViewPager();
        cancelar.setOnClickListener(this);
        guardar.setOnClickListener(this);

    }
    private void setupAdapterViewPager() {
        if(fragmentAdapter==null){
            fragmentAdapter = new MyFragmentAdapter(getChildFragmentManager());
            fragmentAdapter.addFragment(FragmentCreate.newInstance(getArguments()), "Justificación");
            RepositorioTBunble tBunble = new RepositorioTBunble();
            tBunble.setRepositorio(RepositorioUi.ARCHIVO_ASISTENICA);
            //tBunble.setColorCurso(colorCurso);
            fragmentAdapter.addFragment(BaseRepositoriFragmento.newInstance(tBunble), "Archivo");
            vpjustificacion.setOffscreenPageLimit(2);
            vpjustificacion.setAdapter(fragmentAdapter);
            tab_justificacion.setupWithViewPager(vpjustificacion);
            vpjustificacion.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    FragmentCreate fragmentCreate= getFragment(FragmentCreate.class);
                    if(fragmentCreate!=null)fragmentCreate.hideTeclado();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    private <T extends Fragment> T getFragment(Class<T> tClass) {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        for (Fragment fragment :
                fragments) {
            if (tClass.isInstance(fragment)) {
                return (T) fragment;
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guardar:
                    setDatosJustificacion();
                   // setFiles();
                    dismiss();
                break;
            case R.id.cancelar:
                dismiss();
                break;
        }
    }


    private void setDatosJustificacion() {
        FragmentCreate fragmentCreate = getFragment(FragmentCreate.class);
        if (fragmentCreate == null) return;
        AsistenciaJustificaBundel asistenciaJustificaBundel = fragmentCreate.getAsistenciaJustificacion();

        BaseRepositoriFragmento baseRepositoriFragmento = getFragment(BaseRepositoriFragmento.class);
        if (baseRepositoriFragmento == null) return;
        List<RepositorioFileUi> repositorioFileUiList = baseRepositoriFragmento.getListFiles();
        asistenciaJustificaBundel.setRepositorioFileUiList(repositorioFileUiList);

         listener.saveJustificacion(asistenciaJustificaBundel);
    }


    @Override
    public void closed() {
       dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onChangeList(List<RepositorioFileUi> repositorioFileUiList) {
        Log.d(TAG,"repositorioFileUiList" + repositorioFileUiList.size());
    }

    @Override
    public void onDialogCreated(FullScreenDialogController dialogController) {

    }

    @Override
    public boolean onConfirmClick(final FullScreenDialogController dialogController) {
        Bundle result = new Bundle();
        result.putString(RESULT_FULL_NAME, getArguments().getString(EXTRA_NAME) + " HI ");
        dialogController.confirm(result);
        return true;
    }

    @Override
    public boolean onDiscardClick(final FullScreenDialogController dialogController) {
        new AlertDialog.Builder(getContext())
                .setTitle("discard_confirmation_title")
                .setMessage("discard_confirmation_message")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogController.discard();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Nothing to do
                    }
                }).show();
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.WHITE));

    }

    public void changeList(List<RepositorioFileUi> value) {
        BaseRepositoriFragmento baseRepositoriFragmento = getFragment(BaseRepositoriFragmento.class);
        Log.d(TAG,"baseRepositoriFragmento " + (baseRepositoriFragmento==null) );
        if(baseRepositoriFragmento!=null)baseRepositoriFragmento.changeList(value);
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
