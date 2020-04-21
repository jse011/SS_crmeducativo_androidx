package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.presenter.CreateComportamientoPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.presenter.CreateComportamientoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.TabCreateComportFragment;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.files.TabArchivosComportFragment;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui.FragmentComportAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetParametroDisenio;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.SaveComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.ui.ComportamientoFragment;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.MyFragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.FragmentoTipo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class DialogCreareComportamiento extends BaseDialogFragment<CreateComportamientoView, CreateComportamientoPresenter, ListenerCreateComportamiento> implements CreateComportamientoView, ListenerCreateComportamiento, View.OnClickListener {

    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tab_comport_create)
    TabLayout tabComportCreate;
    @BindView(R.id.vp_comport_create)
    ViewPager vpComportCreate;
    @BindView(R.id.viewGroup)
    LinearLayout viewGroup;
    private String TAG = DialogCreareComportamiento.class.getSimpleName();

    public final static String ID_COMPORTAMIENTO = "idComportamiento";
    public static String ID_ALUMNOID = "personaId";
    public static String COLOR_PARAMETRO_DISENIO = "colorParametroDisenio";
    private String colorParametroDisenio;

    public static DialogCreareComportamiento newInstance(CRMBundle crmBundle, String idcomportamiento) {
        DialogCreareComportamiento fragment = new DialogCreareComportamiento();
        Bundle args = new Bundle(crmBundle.instanceBundle());
        args.putString(ID_COMPORTAMIENTO, idcomportamiento);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogCreareComportamiento newInstanceAlumno(CRMBundle crmBundle, int alumnoId) {
        DialogCreareComportamiento fragment = new DialogCreareComportamiento();
        Bundle args = new Bundle(crmBundle.instanceBundle());
        args.putInt(ID_ALUMNOID, alumnoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected CreateComportamientoPresenter getPresenter() {
        ComportamientoRepository comportamientoRepository = new ComportamientoRepository(new ComportamientoDataLocalSource(
                InjectorUtils.provideComportamientoDao(), InjectorUtils.providePersonaDao(), InjectorUtils.provideCursoDao(),
                InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideAlumnoDao()
        ));
        presenter = new CreateComportamientoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources()
                , new SaveComportamiento(comportamientoRepository)
                , new GetParametroDisenio(comportamientoRepository));
        return presenter;
    }

    @Override
    protected CreateComportamientoView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.dialog_create_comportamiento, container, false);
        return view;
    }

    @Override
    protected ViewGroup getRootLayout() {
        return viewGroup;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getDialog() != null && getDialog().getWindow() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getDialog().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getDialog().getWindow().setStatusBarColor(Color.parseColor("#FAFAFA"));
            }
        }



        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back.setOnClickListener(this);
        save.setOnClickListener(this);
    }

    private void initViewPager() {
        final MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getChildFragmentManager());
        fragmentAdapter.addFragment(TabCreateComportFragment.newInstance(getArguments(), colorParametroDisenio), "Caso");
        //fragmentAdapter.addFragment(TabDestinosComportFragment.newInstance(new CRMBundle(getArguments()), getArguments().getString(ID_COMPORTAMIENTO)), "Destinatarios");
        RepositorioTBunble tBunble = new RepositorioTBunble();
        tBunble.setRepositorio(RepositorioUi.ARCHIVO_COMPORTAMIENTO);
        tBunble.setFragmentoTipo(FragmentoTipo.SUBIDA_DESCARGA__ARCHIVOS);
        tBunble.setColorCurso(colorParametroDisenio);
        fragmentAdapter.addFragment(TabArchivosComportFragment.newInstance(tBunble), "Evidencias");
        vpComportCreate.setOffscreenPageLimit(3);
        vpComportCreate.setAdapter(fragmentAdapter);
        tabComportCreate.setupWithViewPager(vpComportCreate);
        vpComportCreate.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabCreateComportFragment tabCreateComportFragment = getFragment(TabCreateComportFragment.class);
                if (tabCreateComportFragment != null) tabCreateComportFragment.hideTeclado();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    private <T extends Fragment> T getFragmentManager(Class<T> tClass) {
        List<Fragment> fragments = getFragmentManager().getFragments();
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
        switch (v.getId()) {
            case R.id.save:
                TabArchivosComportFragment baseRepositoriFragmento = getFragment(TabArchivosComportFragment.class);
                TabCreateComportFragment tabCreateComportFragment = getFragment(TabCreateComportFragment.class);

                if (baseRepositoriFragmento == null||tabCreateComportFragment==null) return;
                List<RepositorioFileUi> repositorioFileUiList = baseRepositoriFragmento.getListFiles();

                ComportamientoUi comportamientoUi = tabCreateComportFragment.getDatos();
                DestinoUi destinoUi = tabCreateComportFragment.getDestinatarios();

                presenter.saveComportamiento(comportamientoUi, destinoUi, repositorioFileUiList);
                break;
            case R.id.back:
                confimationOutDialog();
        }
    }

    private void confimationOutDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView text= new TextView(getActivity());
        text.setPadding(30,30,30,0);
        text.setText("¿Seguro que desea salir de la ventana?");
        text.setGravity(Gravity.CENTER);
        builder.setView(text)
                .setTitle("Mensaje de Confirmacion")
                .setCancelable(false)
                .setNegativeButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }
    @Override
    public void onResumenSave() {
        ComportamientoFragment fragment = getFragmentManager(ComportamientoFragment.class);
        if (fragment != null) fragment.onResume();
        dismiss();
    }

    @Override
    public void onResumenEdit() {
        FragmentComportAlumnoC fragment = getFragmentManager(FragmentComportAlumnoC.class);
        if (fragment != null) fragment.onResume();
        dismiss();
    }

    @Override
    public void sincronizar(int programaEducativoId) {
        Log.d(TAG,"sincronizar" );
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void setParametroDisenio(ParametroDisenioUi parametroDisenioUi) {
        initViewPager();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    public boolean onBackPressed() {
        return false;
    }



    public void changeList(List<ArchivoUi> archivoUiList) {
        TabArchivosComportFragment tabArchivosComportFragment = getFragment(TabArchivosComportFragment.class);
        if(tabArchivosComportFragment!=null)tabArchivosComportFragment.changeList(new ArrayList<RepositorioFileUi>(archivoUiList));
    }


}
