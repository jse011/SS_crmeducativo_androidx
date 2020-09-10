package com.consultoraestrategia.ss_crmeducativo.crearEvento;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.CompoundButtonCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.adapter.AlumnoAdapter;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.local.CrearEventoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.remoto.CrearEventoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetAlumnoCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetAlumnoCargaCurso;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetFile64;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetNombreCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetNombreCargaCurso;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetTipoCalendario;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetTipoEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.SaveEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario.SeleccionarCalendarioDialog;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario.SeleccionarCalendarioView;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.KeyboardUtils;
import com.consultoraestrategia.ss_crmeducativo.util.LifeCycleFragment;
import com.consultoraestrategia.ss_crmeducativo.util.SelectDateFragment;
import com.consultoraestrategia.ss_crmeducativo.util.SelectTimeFragment;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.ContentUriUtils;

public class CrearEventoActivity extends BaseActivity<CrearEventoView, CrearEventoPresenter> implements CrearEventoView, SelectDateFragment.OnDateSelectClickListener, SelectTimeFragment.OnTimeSelectClickListener, LifeCycleFragment.LifecycleListener, AlumnoAdapter.Listener, NestedScrollView.OnScrollChangeListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.edtAlias)
    EditText edtAlias;
    @BindView(R.id.edit_nombre_evento)
    EditText editNombreEvento;
    @BindView(R.id.constraintLayout14)
    ConstraintLayout constraintLayout14;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.btnSelectFecha)
    ImageButton btnSelectFecha;
    @BindView(R.id.btnSelectHora)
    ImageButton btnSelectHora;
    @BindView(R.id.img_close_fecha)
    ImageView imgCloseFecha;
    @BindView(R.id.img_close_hora)
    ImageView imgCloseHora;
    @BindView(R.id.txt_fecha)
    TextView txtFecha;
    @BindView(R.id.txt_hora)
    TextView txtHora;
    @BindView(R.id.edtTipoEvaluacion)
    TextInputEditText edtTipoEvaluacion;
    @BindView(R.id.tilTipoEvaluacion)
    TextInputLayout tilTipoEvaluacion;
    @BindView(R.id.edtFormaEvaluacion)
    TextInputEditText edtFormaEvaluacion;
    @BindView(R.id.tilFormaEvaluacion)
    TextInputLayout tilFormaEvaluacion;
    @BindView(R.id.txtOffline)
    TextView txtOffline;
    @BindView(R.id.btn_imagen)
    ImageView btnImagen;
    @BindView(R.id.img_preview)
    ImageView imgPreview;
    @BindView(R.id.conten_insertar)
    ConstraintLayout contenInsertar;
    @BindView(R.id.lay_placeholder)
    ConstraintLayout layPlaceholder;


    private final static int CUSTOM_REQUEST_CODE = 543;
    @BindView(R.id.img_spin)
    ImageView imgSpin;
    @BindView(R.id.bottom_sheet)
    LinearLayout bottomSheet;
    @BindView(R.id.card_view2)
    CardView cardView2;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.rc_agendas)
    RecyclerView rcAgendas;
    @BindView(R.id.check_enviar_padres)
    CheckBox checkEnviarPadres;
    @BindView(R.id.conten_enviar_padres)
    LinearLayout contenEnviarPadres;
    @BindView(R.id.check_enviar_alumno)
    CheckBox checkEnviarAlumno;
    @BindView(R.id.conten_enviar_alumnos)
    LinearLayout contenEnviarAlumnos;
    @BindView(R.id.editTextTextPersonName)
    EditText editTextTextPersonName;
    @BindView(R.id.scroll_alumnos)
    NestedScrollView scrollAlumnos;
    @BindView(R.id.txt_etiqueta)
    TextView txtEtiqueta;
    @BindView(R.id.cont_search)
    LinearLayout contSearch;
    @BindView(R.id.btn_comentario_private)
    ConstraintLayout btnComentarioPrivate;
    @BindView(R.id.txt_nombres_particpantes)
    TextView txtNombresParticpantes;
    @BindView(R.id.img_close_img)
    ImageView imgCloseImg;


    private SpotsDialog alertDialog;
    private BottomSheetBehavior<LinearLayout> sheetBehavior;
    private MenuItem itemCrear;
    private AlumnoAdapter adapter;
    private boolean scrollPrevent;

    public static void start(Context context, String eventoId, int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId, int entidadId, int tutorCargaAcademicaId, int cargaCursoId) {
        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setUsuarioId(usuarioId);
        crmBundle.setGeoreferenciaId(georeferenciaId);
        crmBundle.setAnioAcademico(anioAcademicoId);
        crmBundle.setEmpleadoId(empleadoId);
        crmBundle.setEntidadId(entidadId);
        crmBundle.setCargaCursoId(cargaCursoId);
        crmBundle.setCargaAcademicaId(tutorCargaAcademicaId);
        Bundle bundle = crmBundle.instanceBundle();
        bundle.putString("eventoId", eventoId);

        Intent intent = new Intent(context, CrearEventoActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    @Override
    protected String getTag() {
        return "CrearEventoActivityTAG";
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected CrearEventoPresenter getPresenter() {
        CrearEventoRepository repository = new CrearEventoRepository(new CrearEventoLocalDataSource(InjectorUtils.provideParametrosDisenioDao()), new CrearEventoRemoteDataSource(UtilServidor.getInstance()));
        return new CrearEventoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetEvento(repository),
                new GetTipoCalendario(repository),
                new GetTipoEvento(repository),
                new SaveEvento(repository),
                new GetFile64(),
                new GetAlumnoCargaAcademica(repository),
                new GetNombreCargaAcademica(repository),
                new GetNombreCargaCurso(repository),
                new GetAlumnoCargaCurso(repository));
    }

    @Override
    protected CrearEventoView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_crear_evento);
        ButterKnife.bind(this);
        setupToolbar();
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new LifeCycleFragment(this), true);
        setupDialogProgress();
        setupBoothonSheet();
        setupAdaper();
        setupCheckBox();
        setupSearch();
        setupScrollView();
    }

    private void setupScrollView() {

        scrollAlumnos.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    //Log.i(getTag(), "Scroll DOWN");
                    if (!scrollPrevent) KeyboardUtils.hideKeyboard(CrearEventoActivity.this);
                }
                if (scrollY < oldScrollY) {
                    //Log.i(TAG, "Scroll UP");
                    if (!scrollPrevent) KeyboardUtils.hideKeyboard(CrearEventoActivity.this);
                }

                if (scrollY == 0) {
                    //Log.i(TAG, "TOP SCROLL");
                }

                if (scrollY == (v.getMeasuredHeight() - v.getChildAt(0).getMeasuredHeight())) {
                    //Log.i(TAG, "BOTTOM SCROLL");
                }
                scrollPrevent = false;
            }
        });
    }

    private void setupSearch() {
        editTextTextPersonName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence search, int start, int before, int count) {
                adapter.getFilter().filter(search.toString());
                presenter.onChangeSearch(search.toString());
                scrollTopAlumnos();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // adapter.getFilter().filter(s.toString());
            }
        });
    }

    private void scrollTopAlumnos() {
        scrollPrevent = true;
        scrollAlumnos.scrollTo(0, 0);
    }

    private void setupCheckBox() {
        int states[][] = {{android.R.attr.state_checked}, {}};
        int colors[] = {Color.parseColor("#0A2D5C"), Color.parseColor("#0A2D5C")};
        CompoundButtonCompat.setButtonTintList(checkEnviarAlumno, new ColorStateList(states, colors));
        CompoundButtonCompat.setButtonTintList(checkEnviarPadres, new ColorStateList(states, colors));
    }

    private void setupAdaper() {
        adapter = new AlumnoAdapter(this);
        rcAgendas.setAdapter(adapter);
        rcAgendas.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) rcAgendas.getItemAnimator()).setSupportsChangeAnimations(false);
        rcAgendas.setHasFixedSize(true);
        rcAgendas.setNestedScrollingEnabled(false);

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#FAFAFA"));
        }
    }

    private void setupDialogProgress() {
        alertDialog = new SpotsDialog(this, R.style.SpotsDialogEvento);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupBoothonSheet() {
        btnComentarioPrivate.setVisibility(View.VISIBLE);
        contSearch.setVisibility(View.GONE);
        scrollAlumnos.setVisibility(View.INVISIBLE);
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            double SLIDEOFFSETHIDEN = 0.5f;

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d(getTag(), "STATE_HIDDEN");
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        KeyboardUtils.hideKeyboard(getActivity());
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d(getTag(), "Close Sheet");
                        //toolbar.setVisibility(View.GONE);
                        KeyboardUtils.hideKeyboard(getActivity());

                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d(getTag(), "Expand Sheet");
                        //toolbar.setVisibility(View.VISIBLE);

                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:

                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset == 0) {
                    imgSpin.setRotation(0);
                    cardView.setRadius(Utils.convertDpToPixel(16, CrearEventoActivity.this));
                    cardView2.setRadius(Utils.convertDpToPixel(16, CrearEventoActivity.this));

                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    if (itemCrear != null) {
                        //itemCrear.setVisible(true);
                    }
                    btnComentarioPrivate.setVisibility(View.VISIBLE);
                    contSearch.setVisibility(View.GONE);
                    scrollAlumnos.setVisibility(View.INVISIBLE);
                } else if (slideOffset == 1) {
                    cardView.setRadius(Utils.convertDpToPixel(0, CrearEventoActivity.this));
                    cardView2.setRadius(Utils.convertDpToPixel(0, CrearEventoActivity.this));
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    if (itemCrear != null) {
                        //itemCrear.setVisible(false);
                    }
                    imgSpin.setRotation(180);
                    btnComentarioPrivate.setVisibility(View.GONE);
                    contSearch.setVisibility(View.VISIBLE);
                    scrollAlumnos.setVisibility(View.VISIBLE);
                } else if (slideOffset > 0.1) {
                    btnComentarioPrivate.setVisibility(View.GONE);
                    contSearch.setVisibility(View.VISIBLE);
                    scrollAlumnos.setVisibility(View.VISIBLE);
                }
                //0.5 >= 0.8

            }
        });

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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_evento, menu);
        itemCrear = menu.findItem(R.id.action_create);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_create:
                if (presenter != null)
                    presenter.onBtnCreateClicked(editNombreEvento.getText().toString(), edtAlias.getText().toString());
                break;
            case R.id.action_enviar:
                if (presenter != null)
                    presenter.onBtnPublicarClicked(editNombreEvento.getText().toString(), edtAlias.getText().toString());
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectDate() {
        Log.d(getTag(), "selectDate ");
        SelectDateFragment selectDate = new SelectDateFragment(this);
        selectDate.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void selectTime() {
        Log.d(getTag(), "selectTime ");
        SelectTimeFragment selectTime = new SelectTimeFragment(this);
        selectTime.show(getSupportFragmentManager(), "timePicker");
    }

    @OnClick({R.id.btnSelectFecha, R.id.btnSelectHora, R.id.txt_hora, R.id.txt_fecha, R.id.img_close_fecha, R.id.img_close_hora})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btnSelectFecha:
                presenter.btnSelectDate();
                break;
            case R.id.btnSelectHora:
                presenter.btnSelectTime();
                break;
            case R.id.txt_fecha:
                presenter.btnSelectDate();
                break;
            case R.id.txt_hora:
                presenter.btnSelectTime();
                break;
            case R.id.img_close_fecha:
                presenter.btnCloseFecha();
                break;
            case R.id.img_close_hora:
                presenter.btnCloseHora();
                break;
        }
        hideTeclado(root);

    }

    public void hideTeclado(View view) {

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                hideTeclado(viewGroup.getChildAt(i));
            }
        } else if (view instanceof TextView) {
            hideSoftKeyboard(view);
        }

    }

    /**
     * Hide keyboard while focus is moved
     */
    private void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                        0);
                view.clearFocus();
            }
        }
    }

    @Override
    public void onClickFechaSelect(String fecha, String dateSelect) {

    }

    @Override
    public void onClickFechaSelect(long timeInMillis) {
        Log.d(getTag(), "onClickFechaSelect ");
        presenter.onClikSaveFecha(timeInMillis);
        //txtFechaEntrega.setText(Utils.f_fecha_letras(timeInMillis));
    }

    @Override
    public void onClickHourSelect(int hourOfDay, int minute) {
        Log.d(getTag(), "onClickHourSelect ");
        //txtHoraEntrega.setText(Utils.changeTime12Hour(hourOfDay, minute));
        presenter.onChangeTime(hourOfDay, minute);
    }

    @Override
    public void setHora(String hora) {
        txtHora.setText(hora);
    }

    @Override
    public void setNombre(String nombre) {
        editNombreEvento.setText(nombre);
    }

    @Override
    public void setDescripcion(String descripcion) {
        edtAlias.setText(descripcion);
    }

    @Override
    public void setTipoEvento(String nombre) {
        edtTipoEvaluacion.setText(nombre);
    }

    @Override
    public void setTipoCalendario(String nombre) {
        edtFormaEvaluacion.setText(nombre);
    }

    @Override
    public void showDialogSearchCalendario() {
        SeleccionarCalendarioDialog seleccionarCalendarioDialog = new SeleccionarCalendarioDialog();
        seleccionarCalendarioDialog.show(getSupportFragmentManager(), "SeleccionarCalendarioDialog");
    }

    @Override
    public void showBtnCloseFecha() {
        imgCloseFecha.setVisibility(View.VISIBLE);
    }

    @Override
    public void setFecha(String fecha) {
        txtFecha.setText(fecha);
    }

    @Override
    public void hideBtnCloseFecha() {
        imgCloseFecha.setVisibility(View.GONE);
    }

    @Override
    public void showBtnCloseHora() {
        imgCloseHora.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBtnCloseHora() {
        imgCloseHora.setVisibility(View.GONE);
    }

    @OnClick({R.id.btn_tipo_evento, R.id.btn_tipo_calendario})
    public void onViewClickedTipo(View view) {
        switch (view.getId()) {
            case R.id.btn_tipo_evento:
                presenter.onClickTipoEvento();
                break;
            case R.id.btn_tipo_calendario:
                presenter.onClickTipoCalendario();
                break;
        }
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        if (f instanceof SeleccionarCalendarioView) {
            presenter.onSeleccionarCalendarioViewDestroyed();
        }

    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if (f instanceof SeleccionarCalendarioView) {
            presenter.attachView((SeleccionarCalendarioView) f);
            ((SeleccionarCalendarioView) f).onAttach(presenter);
        }

    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null)
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void showOffline() {
        txtOffline.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOffline() {
        txtOffline.setVisibility(View.GONE);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void viewsDisabled() {
        enabledView(false, root);
    }

    @Override
    public void viewsEnabled() {
        enabledView(true, root);
    }

    @Override
    public void showContenPreview() {
        contenInsertar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContenPreview() {
        contenInsertar.setVisibility(View.GONE);
    }

    @Override
    public void hideImage() {
        imgCloseImg.setVisibility(View.GONE);
        imgPreview.setImageDrawable(null);
        imgPreview.setVisibility(View.GONE);
        layPlaceholder.setVisibility(View.VISIBLE);
    }

    @Override
    public void showImage(String path) {
        Glide.with(imgPreview)
                .load(path)
                .apply(Utils.getGlideRequestOptionsSimple())
                .into(imgPreview);
        imgPreview.setVisibility(View.VISIBLE);
        layPlaceholder.setVisibility(View.GONE);
        imgCloseImg.setVisibility(View.VISIBLE);
    }

    private void enabledView(boolean enabled, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                enabledView(enabled, viewGroup.getChildAt(i));
            }
        } else {
            view.setEnabled(enabled);
        }
    }

    @OnClick({R.id.btn_imagen, R.id.txt_insertar_imagen, R.id.lay_placeholder})
    public void onBtnImagenClicked(View view) {
        Log.d(getClass().getSimpleName(), "showPickPhoto");
        ArrayList<String> stringList = new ArrayList<>();
        //for (UpdateRepositorioFileUi recursoUploadFile : photoPaths)stringList.add(recursoUploadFile.getPath());
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.Companion.getInstance()
                //.setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppThemeLibrary)
                //.setActivityTitle("Selección de multimedia")
                .enableVideoPicker(false)
                .enableCameraSupport(true)
                .showGifs(true)
                .showFolderView(true)
                //.enableSelectAll(false)
                .enableImagePicker(true)
                .setMaxCount(1);
        //.setCameraPlaceholder(R.drawable.custom_camera)
        //.withOrientation(Orientation.UNSPECIFIED);
        filePickerBuilder.pickPhoto(this, CUSTOM_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(getClass().getSimpleName(), "onActivityResult: " + requestCode + " / " + resultCode);
        ArrayList<Uri> photoPaths = new ArrayList<>();
        switch (requestCode) {
            case CUSTOM_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths.addAll(data.<Uri>getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
                }
                break;
        }
        ArrayList<String> photoPaths2 = new ArrayList<>();
        for (Uri uri : photoPaths) {
            try {
                photoPaths2.add(ContentUriUtils.INSTANCE.getFilePath(this, uri));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        presenter.onSalirSelectPiket(photoPaths2);


    }

    @Override
    public void showDialogProgress() {
        Log.d(getTag(), "showDialogProgress");
        if (!((Activity) this).isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (alertDialog != null) alertDialog.show();
                }
            });
        }
    }

    @Override
    public void hideDialogProgress() {
        Log.d(getTag(), "hideDialogProgress");
        if (!((Activity) this).isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (alertDialog != null) alertDialog.dismiss();
                }
            });
        }


    }

    @Override
    public void showListaAlumnos(List<AlumnoUi> alumnoUiList) {
        adapter.setList(alumnoUiList, false);
    }

    @Override
    public void updateAlumno(AlumnoUi alumnoUi) {
        adapter.update(alumnoUi);
    }

    @Override
    public void setCheckboxAllAlumnos(boolean checkbox) {
        checkEnviarAlumno.setChecked(checkbox);
    }

    @Override
    public void setCheckboxAllPadres(boolean checkbox) {
        checkEnviarPadres.setChecked(checkbox);
    }

    @Override
    public void showAllCheck() {
        contenEnviarAlumnos.setVisibility(View.VISIBLE);
        contenEnviarPadres.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAllCheck() {
        contenEnviarAlumnos.setVisibility(View.GONE);
        contenEnviarPadres.setVisibility(View.GONE);
    }

    @Override
    public void setEquiteLista(String nombre) {
        txtEtiqueta.setText(nombre);
    }

    @Override
    public void setNombresAlumnos(String nombre) {
        txtNombresParticpantes.setText(nombre);
    }

    @Override
    public void panelUpAlumnos() {
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @OnClick(R.id.img_spin)
    public void onImgSpinClicked() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }


    @OnClick(R.id.bt_add_calendario)
    public void onbtnAddCalendarioClicked() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    @OnClick(R.id.txt_nombres_particpantes)
    public void onbtnAddTxtNombresParticpantesClicked() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    @OnClick(R.id.txt_titulo_tu_trabajo)
    public void onbtnTxtTituloTuTrabajoClicked() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }


    @Override
    public void onClickItem(AlumnoUi alumnoUi) {
        presenter.onClickItem(alumnoUi);
    }

    @Override
    public void onChangePadres(AlumnoUi alumnoUi) {
        presenter.onChangePadres(alumnoUi);
    }

    @Override
    public void onChangeAlumno(AlumnoUi alumnoUi) {
        presenter.onChangeAlumno(alumnoUi);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.conten_enviar_padres, R.id.conten_enviar_alumnos})
    public void onCheckedClicked(View view) {
        switch (view.getId()) {
            case R.id.conten_enviar_padres:
                presenter.onClickAllPadres();
                break;
            case R.id.conten_enviar_alumnos:
                presenter.onClickAllAlumnos();
                break;
        }
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }

    @Override
    public void showMessage(CharSequence error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.img_close_img)
    public void onViewClicked() {
        presenter.onClickCloseImage();
    }
}
