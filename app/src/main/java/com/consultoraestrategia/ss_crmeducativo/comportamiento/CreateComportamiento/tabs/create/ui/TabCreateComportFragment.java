package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.card.MaterialCardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragment;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters.AdapterTipos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters.TipoPadresHolder;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters.TiposHolder;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.presenter.TabCreateComportPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.presenter.TabCreateComportPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.dialog.ListaComportamientoDialog;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.dialog.ListaComportamientoView;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.adapters.AdapterDestinatarios;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.ui.DialogCreareComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetAlumnos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetDestino;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetTipos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetUsuarioUiDestinos;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.ValidarUsuario;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.UsuarioUi;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.SelectDateFragment;
import com.consultoraestrategia.ss_crmeducativo.util.SelectTimeFragment;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class TabCreateComportFragment extends BaseFragment<TabCreateComportView, TabCreateComportPresenter, ListenerTabCreateComport> implements TabCreateComportView, ListenerTabCreateComport, LifecycleImpl.LifecycleListener, View.OnClickListener, ListenerDestinosComport, SelectDateFragment.OnDateSelectClickListener {


    @BindView(R.id.txtdescriocion)
    EditText descripcion;
    @BindView(R.id.completeAlumno)
    AutoCompleteTextView completeAlumno;

    @BindView(R.id.viewGroup)
    LinearLayout viewGroup;
    ArrayList<String> nombres;
    AdapterTipos adapterTipos;
    @BindView(R.id.fecha)
    EditText fecha;
    DatePickerDialog datePickerDialog;
    @BindView(R.id.circleImageView4)
    CircleImageView img_picture;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.apellidos)
    TextView apellidos;
    @BindView(R.id.periodoseccion)
    TextView periodoseccion;
    @BindView(R.id.container)
    ConstraintLayout container;
    TiposHolder holder;
    TipoPadresHolder holderPadre;
    @BindView(R.id.btncalendar)
    ImageView btncalendar;
    @BindView(R.id.recyclerTipos)
    RecyclerView recyclerTipos;
    @BindView(R.id.txt_nombre_tipo_comentario)
    TextView txtNombreTipoComentario;
    @BindView(R.id.txt_descripcion_tipo_comentario)
    TextView txtDescripcionTipoComentario;
    @BindView(R.id.cont_tipo_comentario)
    ConstraintLayout contTipoComentario;
    @BindView(R.id.constraintLayout10)
    CardView constraintLayout10;
    @BindView(R.id.txt_peso_comportamiento)
    TextView txtPesoComportamiento;
    @BindView(R.id.textView134)
    TextView textView134;
    @BindView(R.id.txt_hora)
    EditText txtHora;
    private ListaComportamientoDialog listaComportamientoDialog;

    @BindView(R.id.checkPadre)
    CheckBox checkPadre;
    @BindView(R.id.checkApoderado)
    CheckBox checkApoderado;
    @BindView(R.id.checkTutor)
    CheckBox checkTutor;
    @BindView(R.id.recyclerUsuarios)
    RecyclerView recyclerUsuarios;



    public static TabCreateComportFragment newInstance(Bundle bundle, String coloParametroDisenio) {
        TabCreateComportFragment fragment = new TabCreateComportFragment();
        bundle.putString(DialogCreareComportamiento.COLOR_PARAMETRO_DISENIO, coloParametroDisenio);
        fragment.setArguments(bundle);
        return fragment;
    }

    private String TAG = TabCreateComportFragment.class.getSimpleName();

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected TabCreateComportPresenter getPresenter() {
        ComportamientoRepository comportamientoRepository = new ComportamientoRepository(new ComportamientoDataLocalSource(
                InjectorUtils.provideComportamientoDao(), InjectorUtils.providePersonaDao(), InjectorUtils.provideCursoDao(),
                InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideAlumnoDao()
        ));
        presenter = new TabCreateComportPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetAlumnos(comportamientoRepository), new GetTipos(comportamientoRepository), new GetComportamiento(comportamientoRepository),
                new GetUsuarioUiDestinos(comportamientoRepository),
                new GetDestino(comportamientoRepository),
                new ValidarUsuario(comportamientoRepository));
        return presenter;
    }

    @Override
    protected TabCreateComportView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_create_comportamiento, container, false);
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
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btncalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickCalendar();
            }
        });
        setupChilFragmentCycleLife();
        initadapter();
        checkApoderado.setOnClickListener(this);
        checkPadre.setOnClickListener(this);
        checkTutor.setOnClickListener(this);

    }

    private void initadapter() {
        Log.d(TAG, "initadapter");


    }

    private void setupChilFragmentCycleLife() {
        getChildFragmentManager().registerFragmentLifecycleCallbacks(new LifecycleImpl(0, this), true);
    }

    private void initDatePicker(final long time) {
        SelectDateFragment selectDate = new SelectDateFragment(this);
        selectDate.show(getFragmentManager(), "datePicker");
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
    protected Integer[] getEditTextList() {
        return new Integer[]{
                R.id.txtdescriocion
        };
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClickTipoMerito(TipoUi tipoUi) {
        presenter.onClickTipoMerito(tipoUi);
    }

    @Override
    public void seletedTipo(TipoUi tipoUi, RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public void setViewHolder(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof TiposHolder) holder = (TiposHolder) viewHolder;
        else holderPadre = (TipoPadresHolder) viewHolder;
    }

    @Override
    public void setAutoCompleteList(ArrayList<AlumnoUi> nombres) {
        ArrayAdapter<AlumnoUi> adapter = new ArrayAdapter<AlumnoUi>
                (getContext(), android.R.layout.simple_dropdown_item_1line, nombres);
        completeAlumno.setAdapter(adapter);
        completeAlumno.setThreshold(1);
        completeAlumno.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlumnoUi selected = (AlumnoUi) parent.getAdapter().getItem(position);
                presenter.selectedAlumno(selected);
            }
        });
    }

    @Override
    public void showListTipos(List<TipoUi> tipoUis) {

    }

    @Override
    public void setDatosC(ComportamientoUi comportamientoUi) {
        descripcion.setText(comportamientoUi.getDescripcion());
        completeAlumno.setText(comportamientoUi.getAlumnoUi().getNombre());
        completeAlumno.setEnabled(false);
    }

    @Override
    public void setDatosAlumno(AlumnoUi alumno) {
        container.setVisibility(View.VISIBLE);
        String url = alumno.getUrlProfile();
        if (!TextUtils.isEmpty(url)) {
            Glide.with(getContext())
                    .load(url)
                    .apply(Utils.getGlideRequestOptions())
                    .into(img_picture);
        }
        nombre.setText(alumno.getNombre());
        apellidos.setText(alumno.getLastName());
        periodoseccion.setText(alumno.getCursoUi().getPeriodo() + " " + alumno.getCursoUi().getSeccion());
        //container.setBackgroundColor(getResources().get(R.color.md_grey_200));
    }

    @Override
    public void setColorParametroDisenio(String colorDisenio, long fechaA) {
        try {
            txtHora.setText(Utils.getFechaHora(fechaA));
            fecha.setText(Utils.f_fecha_letras(fechaA));
            //contentdate.setBackgroundColor(Color.parseColor(colorDisenio));
        } catch (Exception e) {
            e.getStackTrace();
        }
        //int d = R.style.DialogAppTheme;
    }

    @Override
    public void showTiposPadres(List<TipoUi> tipoUis) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerTipos.setLayoutManager(layoutManager);
        adapterTipos = new AdapterTipos(tipoUis, this);
        recyclerTipos.setAdapter(adapterTipos);
        recyclerTipos.setHasFixedSize(true);
        recyclerTipos.setNestedScrollingEnabled(false);
    }

    @Override
    public void clearEditText() {
        completeAlumno.getText().clear();
        descripcion.getText().clear();
    }

    @Override
    public void setDatosArchivo(List<ArchivoUi> archivoUiList) {
        DialogCreareComportamiento dialogCreareComportamiento = getFragment(DialogCreareComportamiento.class);
        Log.d(TAG, "DialogCreareComportamiento is null: " + (dialogCreareComportamiento == null));
        if (dialogCreareComportamiento != null)
            dialogCreareComportamiento.changeList(archivoUiList);

    }

    @Override
    public void setError() {
        //completeAlumno.setError("");

    }

    @Override
    public void showDialogComportamientoTipo() {
        listaComportamientoDialog = new ListaComportamientoDialog();
        listaComportamientoDialog.show(getChildFragmentManager(), ListaComportamientoDialog.class.getSimpleName());
    }

    @Override
    public void showTipoComportamiento(TipoComportamientoUi tipoComportamientoUi) {
        contTipoComentario.setVisibility(View.VISIBLE);
        txtNombreTipoComentario.setText(tipoComportamientoUi.getTitulo());
        txtDescripcionTipoComentario.setText(tipoComportamientoUi.getDescripcion());
        txtPesoComportamiento.setText(String.valueOf(tipoComportamientoUi.getCantidad()));
    }

    @Override
    public void hideDialogComportamientoTipo() {
        if (listaComportamientoDialog != null) listaComportamientoDialog.dismiss();
    }

    @Override
    public void showHoraComportamiento(final long fechaseleted) {
        SelectTimeFragment selectTime = new SelectTimeFragment(new SelectTimeFragment.OnTimeSelectClickListener() {
            @Override
            public void onClickHourSelect(int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(fechaseleted);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                txtHora.setText(Utils.getFechaHora(calendar.getTimeInMillis()));
                presenter.setFecha(calendar.getTimeInMillis());
            }
        });

        selectTime.show(getChildFragmentManager(), "timePicker");


    }

    @Override
    public void showCalendarComportamineto(long fechaseleted) {
        initDatePicker(fechaseleted);
    }


    @Override
    public AlumnoUi getAlumno() {
        return getAlumnoSeleted() ;
    }

    @Override
    public void updateUsuario(UsuarioUi usuarioUi) {

    }

    public void setSelectedTutor(boolean selectedTutor) {
        if (selectedTutor) checkTutor.setChecked(true);
        else checkTutor.setChecked(false);
    }

    @Override
    public void setSelectedApoderado(boolean selected) {
        if (selected) checkApoderado.setChecked(true);
        else checkApoderado.setChecked(false);
    }

    @Override
    public void setSelectedPadre(boolean selected) {
        if (selected) checkPadre.setChecked(true);
        else checkPadre.setChecked(false);
    }

    public ComportamientoUi getDatos() {
        String des = String.valueOf(descripcion.getText());
        return presenter.saveComportamiento(des);
    }

    public AlumnoUi getAlumnoSeleted() {
        return presenter.getAlumnoSelected();
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


    @Override
    public void onChildsFragmentViewCreated() {

    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {
        if (f instanceof ListaComportamientoDialog) {
            presenter.attachView(((ListaComportamientoDialog) f));
        }
    }

    @Override
    public void onFragmentResumed(Fragment f) {
        if (f instanceof ListaComportamientoDialog) presenter.onResumedListaComportamiento();
    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        if (f instanceof ListaComportamientoDialog) presenter.onDestroyViewListaComportamiento();
    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if (f instanceof ListaComportamientoView) {
            ((ListaComportamientoView) f).onAttach(presenter);
        }
    }

    @OnClick(R.id.btn_clock)
    public void onViewClicked() {
        presenter.onClickClock();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkPadre:
                presenter.selectedCheckPadre();
                break;
            case R.id.checkApoderado:
                presenter.selectedCheckApoderado();
                break;
            case R.id.checkTutor:
                presenter.selectedCheckTutor();
                break;
        }
    }

    @Override
    public void seletedUsuario(UsuarioUi usuarioUi) {
        presenter.seletedUsuario(usuarioUi);
    }

    public DestinoUi getDestinatarios() {
        return presenter.getDestinatarios();
    }


    @Override
    public void onClickFechaSelect(String fecha, String dateSelect) {

    }

    @Override
    public void onClickFechaSelect(long timeInMillis) {
        Log.d(TAG, "onClickFechaSelect ");
        presenter.setFecha(timeInMillis);
        fecha.setText(Utils.f_fecha_letras(timeInMillis));
    }
}
