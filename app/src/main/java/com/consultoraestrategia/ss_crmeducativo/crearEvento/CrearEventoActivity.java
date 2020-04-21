package com.consultoraestrategia.ss_crmeducativo.crearEvento;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.local.CrearEventoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.remoto.CrearEventoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetFile64;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetTipoCalendario;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.GetTipoEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase.SaveEvento;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario.SeleccionarCalendarioDialog;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario.SeleccionarCalendarioView;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.util.LifeCycleFragment;
import com.consultoraestrategia.ss_crmeducativo.util.SelectDateFragment;
import com.consultoraestrategia.ss_crmeducativo.util.SelectTimeFragment;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.Orientation;

public class CrearEventoActivity extends BaseActivity<CrearEventoView, CrearEventoPresenter> implements CrearEventoView, SelectDateFragment.OnDateSelectClickListener, SelectTimeFragment.OnTimeSelectClickListener, LifeCycleFragment.LifecycleListener {
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
    private SpotsDialog alertDialog;

    public static void start(Context context, String eventoId, int usuarioId, int georeferenciaId, int empleadoId, int anioAcademicoId, int entidadId) {
        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setUsuarioId(usuarioId);
        crmBundle.setGeoreferenciaId(georeferenciaId);
        crmBundle.setAnioAcademico(anioAcademicoId);
        crmBundle.setEmpleadoId(empleadoId);
        crmBundle.setEntidadId(entidadId);
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
        CrearEventoRepository repository = new CrearEventoRepository(new CrearEventoLocalDataSource(), new CrearEventoRemoteDataSource(UtilServidor.getInstance()));
        return new CrearEventoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetEvento(repository),
                new GetTipoCalendario(repository),
                new GetTipoEvento(repository),
                new SaveEvento(repository),
                new GetFile64());
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
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.getInstance()
                //.setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppThemeLibrary)
                //.setActivityTitle("Selección de multimedia")
                .enableVideoPicker(false)
                .enableCameraSupport(true)
                .showGifs(true)
                .showFolderView(true)
                //.enableSelectAll(false)
                .enableImagePicker(true)
                .setMaxCount(1)
                //.setCameraPlaceholder(R.drawable.custom_camera)
                .withOrientation(Orientation.UNSPECIFIED);
        filePickerBuilder.pickPhoto(this, CUSTOM_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(getClass().getSimpleName(),"onActivityResult: "+ requestCode +" / "+ resultCode);
        ArrayList<String> photoPaths = new ArrayList<>();;
        switch (requestCode) {
            case CUSTOM_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
                }
                break;
        }

        presenter.onSalirSelectPiket(photoPaths);
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
}
