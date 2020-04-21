package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
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
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.adapter.ArchivoComentarioColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.FragmentoTipo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemUpdateListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.CloneImagenCompress;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.ConvertirPathRepositorioUpload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.GetUrlRepositorioArchivo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorio;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.CreateTareaPresenter;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.CreateTareaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.CreateTareaView;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.bundle.CrearTareaBundle;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.repositorioComentario.RepositorioArchivoTareaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.repositorioComentario.RepositorioArchivoViewImpl;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.CreateTareaRepository;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.local.CreateTareaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.remote.CreateTareaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.CrearTareaUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.EliminarRecursoUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpRepository;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.local.TareasLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.remote.RemoteMvpDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.MoverArchivosAlaCarpetaTarea;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.SelectDateFragment;
import com.consultoraestrategia.ss_crmeducativo.util.SelectTimeFragment;
import com.iceteck.silicompressorr.SiliCompressor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by irvinmarin on 24/11/2017.
 */

public class CrearTareasActivity extends BaseActivity<CreateTareaView, CreateTareaPresenter> implements CreateTareaView, SelectDateFragment.OnDateSelectClickListener, SelectTimeFragment.OnTimeSelectClickListener, RepositorioItemListener, RepositorioItemUpdateListener {
    public static final String TAG = CrearTareasActivity.class.getSimpleName();
    @BindView(R.id.txtdescriocion)
    EditText descripcion;
    @BindView(R.id.txtTitulo)
    EditText txtTitulo;
    @BindView(R.id.btnSelectFecha)
    ImageButton btnSelectFecha;
    @BindView(R.id.btnSelectHora)
    ImageButton btnSelectHora;
    @BindView(R.id.txt_fecha)
    TextView txtFecha;
    @BindView(R.id.txt_hora)
    TextView txtHora;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rc_materiales)
    RecyclerView rcMateriales;
    @BindView(R.id.img_close_fecha)
    ImageView imgCloseFecha;
    @BindView(R.id.img_close_hora)
    ImageView imgCloseHora;
    private RepositorioArchivoViewImpl repositorioViewImpl;
    private RepositorioAdapter repositorioAdapter;
    private RepositorioArchivoTareaPresenterImpl baseRepositorioPresenter;

    public static void launchActivityCrearTareas(Context context, CrearTareaBundle crearTareaBundle) {
        Intent intent = new Intent(context, CrearTareasActivity.class);
        intent.putExtras(crearTareaBundle.getBundle());
        context.startActivity(intent);
    }

    @Override
    protected String getTag() {
        return CrearTareasActivity.class.getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public CreateTareaPresenter getPresenter() {
        RepositorioRepository repositorioRepository = new RepositorioRepository(new RepositorioLocalDataSource(),
                new RepositorioPreferentsDataSource(),
                new RepositorioRemoteDataSource(ApiRetrofit.getInstance()));

        baseRepositorioPresenter = new RepositorioArchivoTareaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new DowloadImageUseCase(repositorioRepository),
                new ConvertirPathRepositorioUpload(),
                new UploadRepositorio(repositorioRepository),
                new GetUrlRepositorioArchivo(repositorioRepository),
                new UpdateRepositorio(repositorioRepository, SiliCompressor.with(this)),
                new CloneImagenCompress(SiliCompressor.with(this), this),
                new MoverArchivosAlaCarpetaTarea(TareasMvpRepository.getInstace(
                        new TareasLocalDataSource(),
                        new RemoteMvpDataSource(this))));

        CreateTareaRepository repository = new CreateTareaRepository(
                new CreateTareaLocalDataSource(
                        InjectorUtils.provideTareasDao(),
                        InjectorUtils.provideRecursoDidacticoEventoDao(),
                        InjectorUtils.provideTareasRecursoDao()
                ),
                new CreateTareaRemoteDataSource());
//
        return new CreateTareaPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new CrearTareaUseCase(repository),
                new EliminarRecursoUseCase(repository),
                new MoverArchivosAlaCarpetaTarea(TareasMvpRepository.getInstace(
                        new TareasLocalDataSource(),
                        new RemoteMvpDataSource(this))
        ));
//        return null;
    }

    @Override
    protected CreateTareaView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.crear_tarea_activity);
        ButterKnife.bind(this);
        setupToolbar();
        setupAdapter();
    }

    private void setupAdapter() {
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(this, OrientationHelper.VERTICAL, false);
        ArchivoComentarioColumnCountProvider columnCountProvider = new ArchivoComentarioColumnCountProvider(this);
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        rcMateriales.setLayoutManager(autoColumnGridLayoutManager);
        repositorioAdapter = new RepositorioAdapter(this, this, rcMateriales, true);
        rcMateriales.setAdapter(repositorioAdapter);
        rcMateriales.setHasFixedSize(true);
        rcMateriales.setNestedScrollingEnabled(false);
    }

    private void setupToolbar() {
        getSupportActionBar();
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repositorioViewImpl = new RepositorioArchivoViewImpl(this, presenter);
        setRepositorioAchivoView(repositorioViewImpl);
        repositorioViewImpl.setPresenter(baseRepositorioPresenter);
        baseRepositorioPresenter.onViewCreated();
        RepositorioTBunble tBunble = new RepositorioTBunble();
        tBunble.setRepositorio(RepositorioUi.ARCHIVO);
        tBunble.setFragmentoTipo(FragmentoTipo.SUBIDA_DESCARGA_ARCHIVOS_VINCULOS);
        //tBunble.setColorCurso();
        baseRepositorioPresenter.setExtras(tBunble.getBundle());
    }

    private void setRepositorioAchivoView(RepositorioArchivoViewImpl repositorioViewImpl) {
        this.repositorioViewImpl = repositorioViewImpl;
        this.repositorioViewImpl.setAdapterArchivo(repositorioAdapter);
    }


    /*@Override
    protected void setContentView() {
        op = 0;
        setContentView(R.layout.crear_tarea_activity);
        ButterKnife.bind(this);rvRecursosUI
    }*/

    @Override
    public void onResume() {
        super.onResume();
        baseRepositorioPresenter.onResume();
        //getSupportActionBar();
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupRvRecurso(null);
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
    public void showMsjs(String msj) {
        Log.d(TAG, "showMsjs ");
        Snackbar.make(descripcion.getRootView(), msj, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void showListArchivos(List<RepositorioFileUi> value) {
        Log.d(TAG, "showListArchivos " + value.size());
        baseRepositorioPresenter.changeList(value);
    }

    @Override
    public void setFecha(String fecha) {
        txtFecha.setText(fecha);
    }

    @Override
    public void showTextEmpty() {
        if (!TextUtils.isEmpty(txtTitulo.getText())) {
            if (!TextUtils.isEmpty(descripcion.getText())) {
            } else Toast.makeText(this, "Ingrese las Instrucciones", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Ingrese el Titulo", Toast.LENGTH_SHORT).show();

    }


    private <T extends Fragment> T getFragment(Class<T> tClass) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment :
                fragments) {
            if (tClass.isInstance(fragment)) {
                return (T) fragment;
            }
        }
        return null;
    }


    @Override
    public void limpiarInputs(TareasUI tareasUI) {
        Log.d(TAG, "limpiarInputs ");
        finish();
    }


    @Override
    public void onClickHourSelect(int hourOfDay, int minute) {
        Log.d(TAG, "onClickHourSelect ");
        //txtHoraEntrega.setText(Utils.changeTime12Hour(hourOfDay, minute));
        presenter.onChangeTime(hourOfDay, minute);
    }

    private int error = 0;



    @Override
    public void setEnableInputs(boolean habilitar) {
        Log.d(TAG, "setEnableInputs ");
        //txtUrlVinculo.setEnabled(habilitar);
        btnSelectFecha.setEnabled(habilitar);
        btnSelectHora.setEnabled(habilitar);
//        btnPublicar.setEnabled(habilitar);
        descripcion.setEnabled(habilitar);
        txtTitulo.setEnabled(habilitar);
    }

    @Override
    public void setDataFields(TareasUI tareasUI) {
        Log.d(TAG, "setDataFields ");
        Log.d(TAG, "recursos : " + tareasUI.getRecursosUIList().toString());
        txtTitulo.setText(tareasUI.getTituloTarea());
        descripcion.setText(tareasUI.getDescripcion());
    }


    private void setupRvRecurso(TareasUI tareasUI) {
        Log.d(TAG, "setupRvRecurso ");
        /*recursosTareaAdapter = new RecursosTareaAdapter(new ArrayList<RecursosUI>(), tareasUI, 1, this);
        rvRecursosUI.setLayoutManager(new LinearLayoutManager(this()));
        rvRecursosUI.setAdapter(recursosTareaAdapter);*/
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


    @Override
    public void guardarTarea(int estadoTareaCicked, List<RepositorioFileUi> recursosUIList) {
        Log.d(TAG, "guardarTarea ");

        error = presenter.validateField(txtTitulo);
        error = presenter.validateField(descripcion);
        //error = presenter.validateField(txtFechaEntrega);
        //error = presenter.validateField(txtHoraEntrega);
        presenter.correctImputs(error,
                txtTitulo.getText().toString(),
                descripcion.getText().toString(),
                estadoTareaCicked,
                recursosUIList);
        showTextEmpty();
    }

    @Override
    public void selectDate() {
        Log.d(TAG, "selectDate ");
        SelectDateFragment selectDate = new SelectDateFragment(this);
        selectDate.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void selectTime() {
        Log.d(TAG, "selectTime ");
        SelectTimeFragment selectTime = new SelectTimeFragment(this);
        selectTime.show(getSupportFragmentManager(), "timePicker");
    }


    @Override
    public void actualiceTarea(int programaEducativoId) {
        CallService.jobServiceExportarTipos(this, TipoExportacion.TAREA);
        SimpleSyncIntenService.start(this, programaEducativoId);
    }

    @Override
    public void notyDataBaseChange() {
        CMRE.saveNotifyChangeDataBase(this);
    }

    @Override
    public void setHora(String hora) {
        txtHora.setText(hora);
    }

    @Override
    public void showBtnCloseFecha() {
        imgCloseFecha.setVisibility(View.VISIBLE);
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


    @Override
    public void onDestroy() {
        presenter.onDestroy();
        baseRepositorioPresenter.onDestroyView();
        super.onDestroy();
    }

    @Override
    public void onClickFechaSelect(String fecha, String dateSelect) {

    }

    @Override
    public void onClickFechaSelect(long timeInMillis) {
        Log.d(TAG, "onClickFechaSelect ");
        presenter.onClikSaveFecha(timeInMillis);
        //txtFechaEntrega.setText(Utils.f_fecha_letras(timeInMillis));
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            backPressed();
            return true;
        }
        if (id == R.id.action_publicar) {
            guardarTarea(264);
            return true;
        }
        if (id == R.id.action_guardar) {
            guardarTarea(263);
            return true;
        }
//
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tareas_menu, menu);
        return true;
    }

    private void guardarTarea(int tipoId) {
        guardarTarea(tipoId, baseRepositorioPresenter.getListFiles());
    }

    public void backPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView text = new TextView(this);
        text.setPadding(30, 30, 30, 0);
        //text.setText();
        builder.setMessage("¿Seguro que desea salir?")
                .setTitle("Mensaje de confirmación")
                .setCancelable(false)
                .setNegativeButton(R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onClickDownload(RepositorioFileUi repositorioFileUi) {
        repositorioViewImpl.onClickDownload(repositorioFileUi);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        repositorioViewImpl.onClickClose(repositorioFileUi);
    }

    @Override
    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        repositorioViewImpl.onClickCheck(repositorioFileUi);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        repositorioViewImpl.onClickArchivo(repositorioFileUi);
    }

    @Override
    public void onClickUpload(UpdateRepositorioFileUi updateRepositorioFileUi) {
        repositorioViewImpl.onClickUpload(updateRepositorioFileUi);
    }

    @Override
    public void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi) {
        repositorioViewImpl.onClickRemover(updateRepositorioFileUi);
    }

    @Override
    public void onClickClose(UpdateRepositorioFileUi updateRepositorioFileUi) {
        repositorioViewImpl.onClickClose(updateRepositorioFileUi);
    }

    @Override
    public void onClickArchivo(UpdateRepositorioFileUi updateRepositorioFileUi) {
        repositorioViewImpl.onClickArchivo(updateRepositorioFileUi);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        repositorioViewImpl.onActivityResult(requestCode, resultCode, data);

    }

    @OnClick({R.id.img_vinculo, R.id.img_imagen, R.id.img_documento})
    public void onViewClickedMateriales(View view) {
        switch (view.getId()) {
            case R.id.img_vinculo:
                baseRepositorioPresenter.onClickAddVinculo();
                break;
            case R.id.img_imagen:
                baseRepositorioPresenter.onClickAddMultimedia();
                break;
            case R.id.img_documento:
                baseRepositorioPresenter.onClickAddFile();
                break;
        }
        hideTeclado(root);
    }

    public void hideTeclado(View view){

        if(view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); i++){
                hideTeclado(viewGroup.getChildAt(i));
            }
        }else if(view instanceof TextView){
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
}
