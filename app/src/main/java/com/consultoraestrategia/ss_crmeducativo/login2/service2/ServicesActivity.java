package com.consultoraestrategia.ss_crmeducativo.login2.service2;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.work.WorkManager;

import com.airbnb.lottie.LottieAnimationView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.core2.listener.Core2Listener;
import com.consultoraestrategia.ss_crmeducativo.login2.adapter.AdapterServicioEnvio;
import com.consultoraestrategia.ss_crmeducativo.login2.adapter.AdapterServicioRecibir;
import com.consultoraestrategia.ss_crmeducativo.login2.adapter.AnioCalendarioAdapter;
import com.consultoraestrategia.ss_crmeducativo.login2.data.preferent.LoginPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.preferent.LoginPreferentRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.Firebase.GetListaCambiosFB;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetCalendarioPeridoList;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetListActualizar;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetListServicioEnvio;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetPlanificarSinck;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.SavePlanificarSinck;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.SaveDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CalendarioPeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;
import com.consultoraestrategia.ss_crmeducativo.login2.fastData.FastData;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckServiceFB;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicesActivity extends BaseActivity<ServicesView, ServicesPresenter> implements ServicesView, CompoundButton.OnCheckedChangeListener, AdapterServicioRecibir.ActualizarListener, AdapterServicioEnvio.EnviarListener, AnioCalendarioAdapter.Listener, Core2Listener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.switch_notificacion)
    Switch switchNotificacion;
    @BindView(R.id.txt_descripcion_notificacion)
    TextView txtDescripcionNotificacion;
    @BindView(R.id.switch_prog_envio)
    Switch switchProgEnvio;
    @BindView(R.id.txt_descripcion_prog_envio)
    TextView txtDescripcionProgEnvio;
    @BindView(R.id.rc_servicio_envio)
    RecyclerView rcServicioEnvio;
    @BindView(R.id.rc_servicio_actualizar)
    RecyclerView rcServicioActualizar;
    @BindView(R.id.root)
    CoordinatorLayout root;
    @BindView(R.id.img_actualizar_service)
    LottieAnimationView imgActualizarService;
    @BindView(R.id.txt_bimestre)
    TextView txtBimestre;
    @BindView(R.id.txt_titulo_eviar_cambios)
    TextView txtTituloEviarCambios;
    @BindView(R.id.cont_calendario)
    CardView contCalendario;
    @BindView(R.id.txt_actualizar)
    TextView txtActualizar;
    @BindView(R.id.cont_actualizar)
    CardView contActualizar;
    @BindView(R.id.crad_enviar_cambios)
    CardView cradEnviarCambios;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.card_prog_envio)
    CardView cardProgEnvio;
    @BindView(R.id.rc_anio_calendario)
    RecyclerView rcAnioCalendario;
    @BindView(R.id.cont_anio_acdemico)
    CardView contAnioAcdemico;
    @BindView(R.id.img_ping_restaurar)
    ImageView imgPingRestaurar;
    @BindView(R.id.fr_fondo_restaurar)
    FrameLayout frFondoRestaurar;
    @BindView(R.id.con_cabecera_actualizar)
    CardView conCabeceraActualizar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.textView157)
    TextView textView157;
    @BindView(R.id.textView158)
    View textView158;
    @BindView(R.id.textView161)
    TextView textView161;
    @BindView(R.id.textView162)
    View textView162;
    @BindView(R.id.txt_titulo_mantenimiento)
    TextView txtTituloMantenimiento;
    @BindView(R.id.txt_descripcion_mantenimiento)
    TextView txtDescripcionMantenimiento;
    @BindView(R.id.cardView3)
    CardView cardView3;
    @BindView(R.id.cont_pa)
    CardView contPa;
    @BindView(R.id.txt_titulo_pa)
    TextView txtTituloPa;
    @BindView(R.id.txt_descripcion_pa)
    TextView txtDescripcionPa;
    @BindView(R.id.txt_descripcion_2_pa)
    TextView txtDescripcion2Pa;
    @BindView(R.id.img_actualizar)
    ImageView imgActualizar;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private ObjectAnimator animationBtnRevisionDatos;
    private AnioCalendarioAdapter anioAcademicoAdapter;
    private ObjectAnimator animationImagePA;

    public static Intent start(Context context, int usuarioId, int empleadoId, int programaEducativoId, int cargaCursoId, int calendarioPeriodoId, int idGeoreferenciaId, int idEntidad, int silaboId, int idCurso, int cargaAcademica, int anioAcademicoId, boolean cursoComplejo) {
        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setCalendarioPeriodoId(calendarioPeriodoId);
        crmBundle.setCargaCursoId(cargaCursoId);
        crmBundle.setUsuarioId(usuarioId);
        crmBundle.setEmpleadoId(empleadoId);
        crmBundle.setProgramaEducativoId(programaEducativoId);
        crmBundle.setGeoreferenciaId(idGeoreferenciaId);
        crmBundle.setEntidadId(idEntidad);
        crmBundle.setSilaboEventoId(silaboId);
        crmBundle.setCursoId(idCurso);
        crmBundle.setCargaAcademicaId(cargaAcademica);
        crmBundle.setComplejo(cursoComplejo);
        crmBundle.setAnioAcademico(anioAcademicoId);
        Intent intent = new Intent(context, ServicesActivity.class);
        intent.putExtras(crmBundle.instanceBundle());
        return intent;

    }

    private AdapterServicioRecibir adapterActualizar;
    private AdapterServicioEnvio adapterEnvio;

    @Override
    protected String getTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ServicesPresenter getPresenter() {
        LoginDataRepository service2Repositorio = new LoginDataRepositoryImpl(ApiRetrofit.getInstance(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideCursoDao(), InjectorUtils.provideAlumnoDao());
        LoginPreferentRepository preferentRepository = new LoginPreferentRepositoryImpl(getApplicationContext());
        return new ServicesPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetListActualizar(service2Repositorio),
                new GetListServicioEnvio(service2Repositorio),
                new GetCalendarioPeriodo(service2Repositorio),
                new GetDatosServidor(service2Repositorio),
                new SaveDatosServidor(service2Repositorio),
                new SavePlanificarSinck(service2Repositorio),
                new GetPlanificarSinck(service2Repositorio),
                new GetCalendarioPeridoList(service2Repositorio),
                new GetListaCambiosFB(service2Repositorio, preferentRepository, this));
    }

    @Override
    protected ServicesView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);
        setupToolbar();
        setupCheckListener();
        setupAdapter();
        setupCore2();
        SynckServiceFB.start(this);
        // Iniciar la tarea asíncrona al revelar el indicador
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        presenter.onRefresh();
                    }
                }
        );
    }

    private void setupCore2() {
        Core2.getCore2(this).addCore2Listener(this);
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
            window.setStatusBarColor(Color.parseColor("#F2F2F2"));
        }
    }


    private void setupAdapter() {
        rcServicioActualizar.setLayoutManager(new LinearLayoutManager(this));
        adapterActualizar = new AdapterServicioRecibir(this);
        rcServicioActualizar.setAdapter(adapterActualizar);
        rcServicioActualizar.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator) rcServicioActualizar.getItemAnimator()).setSupportsChangeAnimations(false);

        rcServicioEnvio.setLayoutManager(new LinearLayoutManager(this));
        adapterEnvio = new AdapterServicioEnvio(this);
        rcServicioEnvio.setAdapter(adapterEnvio);
        rcServicioEnvio.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator) rcServicioEnvio.getItemAnimator()).setSupportsChangeAnimations(false);

        rcAnioCalendario.setLayoutManager(new LinearLayoutManager(this));
        anioAcademicoAdapter = new AnioCalendarioAdapter(this);
        rcAnioCalendario.setAdapter(anioAcademicoAdapter);
        rcAnioCalendario.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator) rcServicioEnvio.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void setupCheckListener() {
        switchNotificacion.setOnCheckedChangeListener(this);
        switchProgEnvio.setOnCheckedChangeListener(this);
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
    public void setDescripcionNotificacion(String descripcion) {
        txtDescripcionNotificacion.setText(descripcion);
    }

    @Override
    public void setDescripcionProgramarHorarioEnvio(String descripcion) {
        txtDescripcionProgEnvio.setText(descripcion);
    }

    @Override
    public void showListServicioEnvio(List<ServiceEnvioUi> serviceEnvioUiList) {
        if (cradEnviarCambios.getVisibility() != View.VISIBLE)
            cradEnviarCambios.setVisibility(View.VISIBLE);

        adapterEnvio.setList(serviceEnvioUiList);
    }

    @Override
    public void showListServicioActualizar() {
        imgPingRestaurar.animate().rotation(180);
        if (contActualizar.getVisibility() != View.VISIBLE)
            contActualizar.setVisibility(View.VISIBLE);
    }

    @Override
    public void girarBtnRevisionDatos() {
        if (animationBtnRevisionDatos == null) {
           // animationBtnRevisionDatos = ObjectAnimator.ofFloat(imgActualizarService, "rotation", 360f, 0.0f);
            //animationBtnRevisionDatos.setDuration(2500);
            //animationBtnRevisionDatos.setRepeatCount(ObjectAnimator.INFINITE);
            //animation.setRepeatMode(ObjectAnimator.RESTART);
            //animationBtnRevisionDatos.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        //animationBtnRevisionDatos.start();

    }

    @Override
    public void showNombreCalendario(String nombre) {
        txtBimestre.setText(nombre);
        contCalendario.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopGirarBtnRevisionDatos() {
       // if (animationBtnRevisionDatos != null) animationBtnRevisionDatos.end();
    }

    @Override
    public void showStartMensajeRevision() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Revisión de los datos");
        builder.setMessage(R.string.msg_start_rubro);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Log.d(TAG, "Presionos ACEPTAR");
                presenter.onSelectedStartRevisionDatos();
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        //Create AdapterExample
        builder.create().show();
    }

    @Override
    public void showMessage(CharSequence error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showStopMensajeRevision() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.msg_confirmacionTitlle);
        builder.setMessage(R.string.msg_stop_rubro);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Log.d(TAG, "Presionos ACEPTAR");
                presenter.onSelectedStoptRevisionDatos();
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        //Create AdapterExample
        builder.create().show();
    }

    @Override
    public void showItemDownloadProgress(ActualizarUi actualizarUi) {
        adapterActualizar.updateItem(actualizarUi);
    }

    @Override
    public void showItemUpaloadProgress(ActualizarUi actualizarUi) {
        adapterActualizar.updateItem(actualizarUi);
    }

    @Override
    public void updateListaActualizar(ActualizarUi actualizarUi) {
        adapterActualizar.updateItem(actualizarUi);
    }

    @Override
    public void notifyChangeDataBase() {
        Intent databack = new Intent();
        setResult(RESULT_OK, databack);
    }

    @Override
    public void hideNombreCalendario() {
        contCalendario.setVisibility(View.GONE);
    }

    @Override
    public void hideServicioActualizar() {
        imgPingRestaurar.animate().rotation(0);
        contActualizar.setVisibility(View.GONE);
    }

    @Override
    public void hideListServicioEnvio() {
        cradEnviarCambios.setVisibility(View.GONE);
    }

    @Override
    public void updateListaEnviar(ServiceEnvioUi serviceEnvioUi) {
        adapterEnvio.updateItem(serviceEnvioUi);
    }

    @Override
    public void removeListaEnviar(ServiceEnvioUi serviceEnvioUi) {
        adapterEnvio.removeItem(serviceEnvioUi);
    }

    @Override
    public void showMessageRevision() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.msg_confirmacionTitlle);
        builder.setMessage(R.string.msg_remplasar_data_rubro);
        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.onSelectedActualizarDatos();
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        //Create AdapterExample
        builder.create().show();
    }

    @Override
    public void scroll(int index, boolean animation) {
        //index Index of child View to scroll to
        //animation Determines whether to scroll with or without an animation

        final View child = ((ViewGroup) nestedScrollView.getChildAt(0)).getChildAt(index);
        final int distanceInPixels = Math.abs((int) child.getY());

        if (animation) {
            nestedScrollView.smoothScrollTo(0, distanceInPixels);
        } else {
            nestedScrollView.scrollTo(0, distanceInPixels);
        }
    }

    @Override
    public void showProgramaHorario(AlarmaUi alarmaUi) {
        AlertDialog alertDialog = createCustomDialog(alarmaUi);
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(null);
    }

    @Override
    public void changeSelectedProgramaHorario(boolean change) {
        //in some cases, it will prevent unwanted situations
        switchProgEnvio.setOnCheckedChangeListener(null);
        switchProgEnvio.setChecked(change);
        switchProgEnvio.setOnCheckedChangeListener(this);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showListAnioCalendario(List<CalendarioPeriodoUi> calendarioPeriodoUiList) {
        contAnioAcdemico.setVisibility(View.VISIBLE);
        anioAcademicoAdapter.setList(calendarioPeriodoUiList);
    }

    @Override
    public void hideListAnioCalendario() {
        contAnioAcdemico.setVisibility(View.GONE);
    }

    @Override
    public void showFastData(int usuarioId, int anioAcademicoId, int calendarioId, int programaEducativoId) {
        FastData.start(this, anioAcademicoId, usuarioId, calendarioId, programaEducativoId);
    }

    @Override
    public void setListServicioActualizar(List<ActualizarUi> actualizarUiList) {
        adapterActualizar.setList(actualizarUiList);
    }

    @Override
    public void setTitleMantenimiento(String titulo) {
        txtTituloMantenimiento.setText(titulo);
    }

    @Override
    public void showBtnMantenimiento() {
        //txtTituloMantenimiento.setVisibility(View.VISIBLE);
        txtDescripcionMantenimiento.setVisibility(View.VISIBLE);
        imgActualizarService.setAnimation("delete-animation.json");
        imgActualizarService.setRepeatCount(ValueAnimator.INFINITE);
        imgActualizarService.playAnimation();
        imgActualizarService.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBtnMantenimiento() {
        //txtTituloMantenimiento.setVisibility(View.GONE);
        imgActualizarService.cancelAnimation();
        txtDescripcionMantenimiento.setVisibility(View.GONE);
        imgActualizarService.setVisibility(View.GONE);
    }

    @Override
    public void showContentStarFirebase() {
        txtTituloPa.setVisibility(View.VISIBLE);
        contPa.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContentStarFirebase() {
        contPa.setVisibility(View.GONE);
        txtTituloPa.setVisibility(View.GONE);
    }

    @Override
    public void changeDescripcionStarFirebase(String descripcion1, String descripcion2) {
        txtDescripcionPa.setText(descripcion1);
        txtDescripcion2Pa.setText(descripcion2);
    }

    @Override
    public void girarImagePA() {
        if (animationImagePA == null) {
            animationImagePA = ObjectAnimator.ofFloat(imgActualizar, "rotation", 360f, 0.0f);
            animationImagePA.setDuration(2500);
            animationImagePA.setRepeatCount(ObjectAnimator.INFINITE);
            //animation.setRepeatMode(ObjectAnimator.RESTART);
            animationImagePA.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        if (!animationImagePA.isRunning()) animationImagePA.start();
    }

    @Override
    public void stopGirarImagePA() {
        if (animationImagePA != null) animationImagePA.end();
    }

    @Override
    public void starSynckFB() {
        SynckServiceFB.start(this);
    }

    @Override
    public void closeRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(false);
            }
        }, 2000);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switch_prog_envio:
                presenter.changeSwitchProgramaEnvio(isChecked);
                break;
            case R.id.switch_notificacion:
                presenter.changeSwitchNotifiacion(isChecked);
                break;
        }
    }

    @OnClick(R.id.img_actualizar_service)
    public void onViewClicked() {
        presenter.onClickRevisionDatos();
        txtDescripcionMantenimiento.setTextColor(ContextCompat.getColor(this,R.color.md_deep_purple_700));
    }

    @OnClick(R.id.txt_descripcion_mantenimiento)
    public void onTxtDescripcionMantenimientoClicked() {
        presenter.onClickRevisionDatos();
        txtDescripcionMantenimiento.setTextColor(ContextCompat.getColor(this,R.color.md_deep_purple_700));
    }

    @Override
    protected void onDestroy() {
        Core2.getCore2(this).removeCore2Listener(this);
        if (animationBtnRevisionDatos != null) animationBtnRevisionDatos.end();
        animationBtnRevisionDatos = null;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickActualizarItem(ActualizarUi actualizarUi) {
        presenter.onClickActualizarItem(actualizarUi);
    }

    @Override
    public void onClickEnviarItem(ServiceEnvioUi serviceEnvioUi) {
        presenter.onClickEnviarItem(serviceEnvioUi);
    }

    public AlertDialog createCustomDialog(AlarmaUi alarmaUi) {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_login_alarma_config, null);
        TextView btnGuardar = (TextView) v.findViewById(R.id.guardar);
        TextView btnCancel = (TextView) v.findViewById(R.id.cancel);
        final TextView horaminute = (TextView) v.findViewById(R.id.hourMinute);
        final TextView tiempo = (TextView) v.findViewById(R.id.tiempo);
        final TextView descripcion = (TextView) v.findViewById(R.id.txtdescriocion);
        TimePicker timePicker = (TimePicker) v.findViewById(R.id.timePicker);
        builder.setView(v);
        alertDialog = builder.create();
        Calendar calendar = Calendar.getInstance();
        if (alarmaUi != null) {
            horaminute.setText(String.valueOf(getHoraAmPm(alarmaUi.getHora()) + getMinuto(alarmaUi.getMinute())));
            tiempo.setText(alarmaUi.getTiempo());
            descripcion.setText(String.valueOf("Todos los dias"));

            calendar.set(Calendar.HOUR_OF_DAY, alarmaUi.getHora());
            calendar.set(Calendar.MINUTE, alarmaUi.getMinute());

        }

        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));

        timePicker.setIs24HourView(false);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                horaminute.setText(String.valueOf(hour + getMinuto(minute)));
                tiempo.setText(getAmPm(hour));
                descripcion.setText(String.valueOf("Todos los dias"));
                presenter.onSelectedHoraMinuteTimePicker(hour, minute);
            }
        });

        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Aceptar
                        if (presenter != null) presenter.onSelectedAceptarTimePicker();
                        if (alertDialog != null) alertDialog.dismiss();
                    }
                }
        );
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (presenter != null) presenter.onSelectedCancelarTimePicker();
                        if (alertDialog != null) alertDialog.dismiss();
                    }
                }
        );
        return alertDialog;
    }

    public int getHoraAmPm(int hora) {
        if (hora > 12) return hora - 12;
        else return hora;
    }

    public String getAmPm(int hora) {
        if (hora < 12) return "AM";
        else return "PM";
    }

    public String getMinuto(int minuto) {
        int cantidad = 0;
        int iTemp = minuto;
        while (iTemp > 0) {
            iTemp = iTemp / 10;
            cantidad++;
        }
        if (cantidad > 1) return ":" + minuto;
        else return ":0" + minuto;
    }


    @OnClick(R.id.card_prog_envio)
    public void onViewClickedCardProgEnvio() {
        presenter.onViewClickedCardProgEnvio();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTittle) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setTitle(messageTittle)
                .setMessage(message)
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
                        presenter.onCLickAcceptButtom();
                        dialogInterface.dismiss();
                        finish();
                    }
                });
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClickAnioCalendario(CalendarioPeriodoUi calendarioPeriodoUi) {
        presenter.onClicAnioCalendario(calendarioPeriodoUi);
    }

    @OnClick(R.id.con_cabecera_actualizar)
    public void onContRestaurarClicked() {
        presenter.onClickContRestaurar();
    }

    @Override
    public void onStart(Class<?> aClass) {

    }

    @Override
    public void onLoad(int count) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onFinishSimple() {

    }

    @Override
    public void onLoadFirebase(int count) {
        presenter.onLoadFirebase(count);
        Log.d(getTag(), "onLoadFirebase " + count);
    }

    @Override
    public void onFinishFirebase() {
        presenter.onFinishFirebase();
    }

    @OnClick(R.id.img_actualizar)
    public void onClickedImgActualizar() {
        presenter.onClickedImgActualizar();
    }
}
