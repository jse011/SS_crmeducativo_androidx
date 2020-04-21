package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.ui.JustificacionFragmetDialog;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaJustificaBundel;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.listener.JustificacionListener;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.presenter.CreateJusticicacionPresenter;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.tabs.fragmentCreate.presenter.CreatejustificacionPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.JustificacionUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetArchivoAsistenciaList;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetTipoJustificacion;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class FragmentCreate extends BaseFragment<FragmentCreateView, CreateJusticicacionPresenter, JustificacionListener> implements FragmentCreateView, View.OnClickListener {

   String TAG= FragmentCreate.class.getSimpleName();

    @BindView(R.id.img_justificacion_alum)
    CircleImageView img_justificacion_alum;
    @BindView(R.id.razon)
    EditText razon;

    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.apellidos)
    TextView apellidos;
    @BindView(R.id.btnTipoJ)
    ImageView btnTipoJ;

    @BindView(R.id.fecha)
    TextView fecha;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.tipoJustificacion)
    EditText edttipo;

    public static FragmentCreate newInstance(Bundle bundle) {
        FragmentCreate fragment = new FragmentCreate();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected CreateJusticicacionPresenter getPresenter() {
        AsistenciaAlumnoRepository repository = new AsistenciaAlumnoRepository(
                new AsistenciaAlumnoLocalDataSource(InjectorUtils.provideAsistenciaSesionAlumnoDao(),
                        InjectorUtils.provideTipoNotaDao(),
                        InjectorUtils.provideValorTipoNotaDao(),InjectorUtils.provideParametrosDisenioDao()));
        presenter= new CreatejustificacionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources(),
                new GetTipoJustificacion(repository),
                new GetArchivoAsistenciaList(repository));
        return presenter;
    }

    @Override
    protected FragmentCreateView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        View view=inflater.inflate(R.layout.fragment_justificacion, container, false);
        return view;
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        btnTipoJ.setOnClickListener(this);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTipoJ:
                presenter.showTiposJustificacion();
                break;
        }
    }


    @Override
    public void setTipoJustificacion(TipoUi tipoJustificacion) {
        edttipo.setText(tipoJustificacion.getNombre());
    }

    @Override
    public void showListArchivos(List<RepositorioFileUi> value) {
        JustificacionFragmetDialog baseRepositoriFragmento = getFragment(JustificacionFragmetDialog.class);
        Log.d(TAG,"baseRepositoriFragmento " + (baseRepositoriFragmento==null) );
        if(baseRepositoriFragmento!=null)baseRepositoriFragmento.changeList(value);
    }

    @Override
    public void setDatos(AlumnosUi alumnosUi, long fechaA, JustificacionUi justificacionUi) {
        String url = alumnosUi.getUrlProfile();
        if (!TextUtils.isEmpty(url)) {
            try {
                Glide.with(img_justificacion_alum.getContext())
                        .load(url)
                        .apply(Utils.getGlideRequestOptions())
                        .into(img_justificacion_alum);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        nombre.setText(alumnosUi.getNombre());
        apellidos.setText(alumnosUi.getLastName());
        fecha.setText(Utils.f_fecha_letras_2(fechaA));
        if (justificacionUi != null)razon.setText(justificacionUi.getRazon());
    }
    @Override
    protected Integer[] getEditTextList() {
        return new Integer[]{
                R.id.razon
        };
    }
    public AsistenciaJustificaBundel getAsistenciaJustificacion(){
        return presenter.saveJustificacion(String.valueOf(razon.getText()));
   }

    private <T extends Fragment> T getFragment(Class<T> tClass) {
        List<Fragment> fragments = getActivity().getSupportFragmentManager().getFragments();
        for (Fragment fragment :
                fragments) {
            if (tClass.isInstance(fragment)) {
                return (T) fragment;
            }
        }
        return null;
    }


    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
