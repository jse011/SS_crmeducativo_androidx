package com.consultoraestrategia.ss_crmeducativo.main.ui;

import android.animation.ValueAnimator;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.transition.Slide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.BuildConfig;
import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.ui.AsistenciaCursoFragment;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.ui.CambiarFotoAlumnoActivity;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.camera.CameraReconocimientoActivity;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.listaAlumnos.ListaAlumnosActivity;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.core2.listener.Core2Listener;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.eventos.EventosActivty;
import com.consultoraestrategia.ss_crmeducativo.lib.AppDatabase;
import com.consultoraestrategia.ss_crmeducativo.lib.GlideImageLoader;
import com.consultoraestrategia.ss_crmeducativo.lib.ImageLoader;
import com.consultoraestrategia.ss_crmeducativo.login2.fastData.FastData;
import com.consultoraestrategia.ss_crmeducativo.login2.principal.Login2Activity;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.ServicesActivity;
import com.consultoraestrategia.ss_crmeducativo.main.MainPresenter;
import com.consultoraestrategia.ss_crmeducativo.main.MainPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.main.MainView;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.CursosAdapter;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.MenuAdapter;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.RecyclerViewAnimator;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.decoration.EdgeDecorator;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.CursosViewHolder;
import com.consultoraestrategia.ss_crmeducativo.main.adapters.holders.GradoHolder;
import com.consultoraestrategia.ss_crmeducativo.main.changePerfil.ChangePerfilFragment;
import com.consultoraestrategia.ss_crmeducativo.main.changePerfil.ChangePerfilView;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.local.LocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.data.source.remote.RemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.main.dialogAlarma.AlertReceiver;
import com.consultoraestrategia.ss_crmeducativo.main.dialogProgress.ProgressDialog;
import com.consultoraestrategia.ss_crmeducativo.main.dialogProgress.ProgressDialogView;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.ChangeDataBaseDocenteMentor;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetAccesosUIList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetAlarma;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetAnioAcademicoList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetCursosUIList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetDatosServidorLocal;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetGradosList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetHijosUIList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetPeriodosList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetProgramasEdcativosList;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.GetUsuarioUI;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.SaveAlarma;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.SuccesData;
import com.consultoraestrategia.ss_crmeducativo.main.domain.usecases.UpadateListAnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ConfiguracionUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.CursosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.GradoUi;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioAccesoUI;
import com.consultoraestrategia.ss_crmeducativo.main.entities.UsuarioRolGeoReferenciaUi;
import com.consultoraestrategia.ss_crmeducativo.main.listeners.CursosListener;
import com.consultoraestrategia.ss_crmeducativo.main.listeners.MenuListener;
import com.consultoraestrategia.ss_crmeducativo.main.seleccionarAnio.SeleccionAnioAcademico;
import com.consultoraestrategia.ss_crmeducativo.main.seleccionarAnio.SeleccionarAnioAcademicoView;
import com.consultoraestrategia.ss_crmeducativo.programahorario.complejo.ui.ProgramaHorarioComplejoActivity;
import com.consultoraestrategia.ss_crmeducativo.programahorario.simple.ui.ProgramaHorarioSimpleActivity;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.ComplejoSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.splashAppMessenger.ScremSplash;
import com.consultoraestrategia.ss_crmeducativo.syncJobs.MyJobService;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.listener.PeriodoListener;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.view.adapters.PeriodoAdapter;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.TipoNotaActivity;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.LifeCycleFragment;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView, MenuListener, CursosListener, Core2Listener, PeriodoListener, BaseFragmentListener, LifeCycleFragment.LifecycleListener {
    public static final int CHANGE_DATABASE_SERVICE2 = 48152;// comunicar que se  modifico la base datos
    GlideImageLoader imageLoader;

    private final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rvListaClases)
    RecyclerView rvListaClases;
    @BindView(R.id.txtOpcionSelected)
    TextInputEditText txtOpcionSelected;
    @BindView(R.id.content_editText)
    TextInputLayout contentEditText;
    @BindView(R.id.btnSelector)
    ImageButton btnSelector;
    @BindView(R.id.contFragment)
    ConstraintLayout contFragment;
    @BindView(R.id.contendoPrincipal)
    CoordinatorLayout contendoPrincipal;
    @BindView(R.id.imgProfileUser)
    ImageView imgProfileUser;
    @BindView(R.id.nav_bar_fondo)
    ImageView navBarFondo;
    @BindView(R.id.nav_bar_barra)
    TextView navBarBarra;
    @BindView(R.id.nav_bar_letra_profile)
    TextView navBarLetraProfile;
    @BindView(R.id.nav_bar_content_profile)
    ConstraintLayout navBarContentProfile;

    @BindView(R.id.nav_bar_texto_persona)
    TextView navBarTextoPersona;
    @BindView(R.id.nav_bar_rc_menu)
    RecyclerView navBarRcMenu;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_bar_imagen_profile)
    ImageView navBarImagenProfile;
    @BindView(R.id.toolbarprogress)
    ProgressBar toolbarprogress;
    @BindView(R.id.recyclerPeriodo)
    RecyclerView recyclerPeriodo;
    @BindView(R.id.frameCalendar)
    FrameLayout frameCalendar;
    @BindView(R.id.rc_sec_grad)
    RecyclerView rcSecGrad;
    @BindView(R.id.conten_asistencia)
    View contenAsistencia;
    @BindView(R.id.img_menu_entidad)
    ImageView imgMenuEntidad;
    @BindView(R.id.msj_vacio)
    TextView msjVacio;
    @BindView(R.id.progresBar)
    ProgressBar progresBar2;
    CursosAdapter cursosAdapter;
    @BindView(R.id.nav_bar_txt_georeferencia)
    TextView navBarTxtGeoreferencia;
    @BindView(R.id.nav_bar_txt_entidad)
    TextView navBarTxtEntidad;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.animationView)
    LottieAnimationView animationView;
    @BindView(R.id.progress_init)
    ConstraintLayout progressInit;
    @BindView(R.id.picker_text_view)
    TextView pickerTextView;
    @BindView(R.id.date_picker_arrow)
    ImageView datePickerArrow;
    @BindView(R.id.date_picker_button)
    ConstraintLayout datePickerButton;
    @BindView(R.id.textView40)
    TextView textView40;
    @BindView(R.id.icon_animed)
    LottieAnimationView iconAnimed;

    private PeriodoAdapter periodoAdapter;
    private GradoHolder olHolder;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private MenuAdapter menuAdapter;


    public static final String EXTRA_USUARIO_ID = "EXTRA_USUARIO_ID";
    private Menu menu;
    private EdgeDecorator edgeDecorator;
    private String SHOWCASE_ID = "1";
    private ProgressDialog progressDialog;

    public static Intent launchMainActivity(Context context, int idUsuario) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_USUARIO_ID, idUsuario);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        desbloqOrientation();
        setupAnimed();
    }

    private void setupAnimed() {
        iconAnimed.setAnimation("happy_chat_2.json");
        iconAnimed.setRepeatCount(ValueAnimator.INFINITE);
        iconAnimed.playAnimation();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_final);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    signInFirebaseAnonimous();
                }
                // ...
            }
        };
        getAppVersion();
        setupGlideImageLoader();
        setupToolbar();
        setupTabMenu();
        setupRecyclerProgramas();
        setupRecyclerAccesos();
        setupRecyclerCursos();
        subscribeToUser();
        setupCore2();
        setupRecyclerPeriodos();
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new LifeCycleFragment(this), true);
    }

    private void setupRecyclerCursos() {
        cursosAdapter = new CursosAdapter(new ArrayList<Object>(), this, imageLoader);
        rvListaClases.setLayoutManager(new LinearLayoutManager(this));
        rvListaClases.setHasFixedSize(true);
        rvListaClases.setAdapter(cursosAdapter);
    }


    private void setupToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.md_white_1000));
        }

        Glide.with(this)
                .load(R.drawable.menu_view_fondo_1)
                .apply(Utils.getGlideRequestOptionsSimple().centerCrop())
                .into(navBarFondo);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle mToogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mToogle.setHomeAsUpIndicator(R.drawable.ic_menu_main);
        drawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();

        final PorterDuffColorFilter colorFilter
                = new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.md_light_blue_400), PorterDuff.Mode.MULTIPLY);

        for (int i = 0; i < toolbar.getChildCount(); i++) {
            final View v = toolbar.getChildAt(i);

            //Step 1 : Changing the color of back button (or open drawer button).
            if (v instanceof ImageButton) {
                //Action Bar back button
                ((ImageButton) v).getDrawable().setColorFilter(colorFilter);
            }
        }

        //toolbar.setNavigationIcon(R.drawable.ic_menu_main);
//        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_menu));

    }

    private void setupRecyclerPeriodos() {
        recyclerPeriodo.setVisibility(View.VISIBLE);
        periodoAdapter = new PeriodoAdapter(new ArrayList<PeriodoUi>(), this);
        recyclerPeriodo.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerPeriodo.setHasFixedSize(true);
        recyclerPeriodo.setAdapter(periodoAdapter);
    }

    private void setupCore2() {
        Core2.getCore2(this).addCore2Listener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE_DATABASE_SERVICE2 && resultCode == AppCompatActivity.RESULT_OK) {
            presenter.onChangeDatabseDesdeService2();
        }

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void subscribeToUser() {
        SessionUser user = SessionUser.getCurrentUser();
        if (user != null) {
            Log.d(TAG, "subscribeToUser: " + user.toString());
            String userId = String.valueOf(user.getUserId());
            FirebaseMessaging.getInstance().subscribeToTopic(userId);
        }
    }

    private void getAppVersion() {
        FirebaseDatabase.getInstance().getReference()
                .child("APP_VERSION").child("CRME").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: " + dataSnapshot);
                if (dataSnapshot != null) {
                    String version = dataSnapshot.getValue(String.class);

                    if (version != null && !version.equals(BuildConfig.VERSION_NAME)) {
                        showImportantMessage(getString(R.string.global_app_version_incompatible));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "databaseError: " + databaseError);
            }
        });
    }

    @Override
    protected String getTag() {
        return TAG;
    }


    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected MainPresenter getPresenter() {

        MainRepository mainRepository = new MainRepository(new LocalDataSource(InjectorUtils.provideSessionUserDao(), InjectorUtils.providePersonaDao(), InjectorUtils.provideCalendarioPeriodo()), new RemoteDataSource());

        return new MainPresenterImpl(
                new UseCaseHandler(
                        new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetProgramasEdcativosList(mainRepository),
                new GetAccesosUIList(mainRepository),
                new GetCursosUIList(mainRepository),
                new GetUsuarioUI(mainRepository),
                new GetHijosUIList(mainRepository),
                new GetPeriodosList(mainRepository),
                new GetGradosList(mainRepository),
                new SaveAlarma(mainRepository),
                new GetAlarma(mainRepository),
                new ChangeDataBaseDocenteMentor(new ServiceLocalDataRepositoryImpl(InjectorUtils.provideSessionUserDao())),
                new GetAnioAcademicoList(mainRepository),
                new GetDatosServidorLocal(mainRepository),
                new SuccesData(mainRepository),
                new UpadateListAnioAcademico(mainRepository)
        );

    }

    @Override
    protected MainView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }


    @Override
    protected ViewGroup getRootLayout() {
        return toolbar;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progresBar2;
    }

    public void showImportantMessageNonCancelable(CharSequence message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.global_btn_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (presenter != null) {
                    presenter.onBackPressed();
                }
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void signInFirebaseAnonimous() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInAnonymously", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }

    @Override
    public void subscribe(String celular) {
        if (TextUtils.isEmpty(celular)) return;
        String nroValidate = celular.replace("+", "");
        String userOficial;
        try {
            if (celular.length() <= 9) {
                userOficial = "user_official_messages_51" + nroValidate + "";
                FirebaseMessaging.getInstance().subscribeToTopic(userOficial);
            } else {
                userOficial = "user_official_messages_" + nroValidate + "";
                FirebaseMessaging.getInstance().subscribeToTopic(userOficial);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setProgramasVacio(String s) {

    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void startActivityTipoNota(UsuarioAccesoUI usuarioAccesoUI) {
        TipoNotaActivity.startActivity(this, usuarioAccesoUI);
    }


    @Override
    public void setAnioSelectedText(String text) {
        Log.d(TAG, "setAnioSelectedText");
        txtOpcionSelected.setText(text);
//        txtOpcionSelected.setHint(R.string.anio_academico);
        contentEditText.setHint(getResources().getString(R.string.anio_academico));
        String anio = "Año Acad. " + text;
        pickerTextView.setText(anio);

    }

    @Override
    public void showHorarioAnioAcademico(int programaEducativoId, int empleadoId, int anioAcademicoId) {
        //HorarioActivity.launchHorarioActivity(this, cargaCursosList, anioAcademicoSelected, programaEducativoId);
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setProgramaEducativoId(programaEducativoId);
        crmBundle.setEmpleadoId(empleadoId);
        crmBundle.setAnioAcademico(anioAcademicoId);
        ProgramaHorarioComplejoActivity.launchHorarioActivity(this, crmBundle);
    }

    @Override
    public void showHorarioCargaCurso(int programaEducativoId, int empleadoId, int cargaCursoId, int anioAcademicoId) {
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setProgramaEducativoId(programaEducativoId);
        crmBundle.setEmpleadoId(empleadoId);
        crmBundle.setCargaCursoId(cargaCursoId);
        crmBundle.setAnioAcademico(anioAcademicoId);
        ProgramaHorarioSimpleActivity.launchHorarioActivity(this, crmBundle);
    }

    @Override
    public void showAsistenciaGradoSeccion(int usuarioId, int programaEducativoId) {

    }

    private void setupGlideImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }


    private void setupRecyclerAccesos() {
        navBarRcMenu.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new MenuAdapter(new ArrayList<Object>(), this);
        navBarRcMenu.setAdapter(menuAdapter);
    }

    private void setupRecyclerProgramas() {
        navBarRcMenu.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new MenuAdapter(new ArrayList<Object>(), this);
        navBarRcMenu.setAdapter(menuAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            presenter.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_final_menu, menu);
        this.menu = menu;
        presenter.onCreateOptionsMenu();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_asistencia_grado) {
            presenter.onClickAsistenciaGradoSeccion();
        }
        if (id == R.id.action_cursos) {
            presenter.onClickListCursos();
        }
        if (id == R.id.menu_refrescar) {
            presenter.onClickRefrescar();
            return true;
        }

        if (id == R.id.action_horario) {
            presenter.onClickActionHorario();
            //CrearTipoNotaActivity.starIntentCrearTipoNota(this,CrearTipoNotaActivity.class,idUsuario);
            return true;
        }
        if (id == R.id.action_salir) {
            presenter.onCerrarSesionClicked();
            return true;
        }
        if (id == R.id.action_settings) {
            dispatchJob();
            return true;
        }
        if (id == R.id.update_calendario) {
            presenter.updateCalendarioPeriodo();
            return true;
        }
        if (id == R.id.action_agenda) {
            presenter.onClickActionAgenta();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dispatchJob() {
        Log.d(TAG, "dispatchJob");
        // Create a new dispatcher using the Google Play driver.
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job job = createJob(dispatcher);
        dispatcher.schedule(job);
    }

    private Job createJob(FirebaseJobDispatcher dispatcher) {
        Log.d(TAG, "createJob");
        Bundle myExtrasBundle = new Bundle();
        myExtrasBundle.putString("some_key", "some_value");

        return dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(MyJobService.class)
                // uniquely identifies the job
                .setTag("my-unique-tag")
                // one-off job
                .setRecurring(false)
                // don't persist past a device reboot
                .setLifetime(Lifetime.FOREVER)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(30, 60))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                // constraints that need to be satisfied for the job to run
                .setConstraints(
                        Constraint.ON_ANY_NETWORK
                )
                .setExtras(myExtrasBundle)
                .build();
    }


    @Override
    public void showImgProfileUser(String UrlUser) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        imageLoader.loadWithCircular(navBarImagenProfile, UrlUser, options, new ImageLoader.CallBack() {
            @Override
            public void onSucces(Bitmap bitmap) {
                navBarImagenProfile.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void showImgProfileBackgroundUser(String UrlBackgroundProfile) {

    }

    @Override
    public void showImgHijo(String UrlImgHijo) {

    }

    @Override
    public void hideImgHijo() {

    }

    @Override
    public void showNombreUsuario(String fullNameUser) {
        navBarTextoPersona.setText(fullNameUser);
    }

    @Override
    public void showLetraPerfil(String letra) {
        navBarLetraProfile.setText(letra);
    }

    @Override
    public void showEmailUsuario(String emailUser) {

    }

    @Override
    public void showMenuList(List<Object> programaEduactivosUIList) {
        menuAdapter.setLista(programaEduactivosUIList);
    }

    @Override
    public void changeProgramaEducativoList() {
        menuAdapter.notifyDataSetChanged();
    }

    @Override
    public void showActualProgramaEdu(ProgramaEduactivosUI programasEducativo) {

    }


    @Override
    public void setCursosList(List<CursosUI> objectstUIList, boolean efectoLista) {
        if (efectoLista) {
            cursosAdapter.setmAnimator(new RecyclerViewAnimator(rvListaClases));
        } else {
            cursosAdapter.setmAnimator(null);
        }
        if (edgeDecorator == null) {
            edgeDecorator = new EdgeDecorator(8);
        } else {
            rvListaClases.removeItemDecoration(edgeDecorator);
        }

        rvListaClases.addItemDecoration(edgeDecorator);

        cursosAdapter.setCursosUIList(objectstUIList);

    }

    @Override
    public void starActivityLogin(String error) {
        Intent intent = new Intent(this, Login2Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("error", error);
        startActivity(intent);
    }


    @Override
    public void starActivityTabs(Class tabClase, CursosUI cursosUI, int programaEducativoId, int georeferenciaId, int empleadoId, int entidadId, int anioAcademicoId, int idUsuario, boolean complejo) {
        Log.d(TAG, "cursosUI : " + cursosUI.toString());
        String[] csoData = new String[]{cursosUI.getNombreCurso(), cursosUI.getGradoSeccion(), cursosUI.getNombreDocente()};
        Intent intent = new Intent(this, tabClase);
        intent.putExtra("csoData", csoData);
        intent.putExtra("fondo", cursosUI.getBackgroundSolidColor());
        intent.putExtra("idCargaCurso", cursosUI.getCargaCurso());
        intent.putExtra("cursoId", cursosUI.getIdCurso());
        intent.putExtra("backgroudResource", cursosUI.getUrlBackgroundItem());
        intent.putExtra("idProgramaEducativo", programaEducativoId);
        intent.putExtra("parametrodisenioid", cursosUI.getParametroDisenioId());
        CRMBundle crmBundle = new CRMBundle();
        Log.d(TAG, "CARGA ACADEMICA " + cursosUI.getCargaAcademicaId());
        crmBundle.setCargaAcademicaId(cursosUI.getCargaAcademicaId());
        crmBundle.setGeoreferenciaId(georeferenciaId);
        crmBundle.setEmpleadoId(empleadoId);
        crmBundle.setEntidadId(entidadId);
        crmBundle.setSilaboEventoId(cursosUI.getSilaboId());
        crmBundle.setGrupoAcademicoId(cursosUI.getGrupoAcademicoId());
        crmBundle.setPeriodAcademicoId(cursosUI.getPeriodoAcademicoId());
        crmBundle.setAnioAcademico(anioAcademicoId);
        crmBundle.setCursoId(cursosUI.getIdCurso());
        crmBundle.setUsuarioId(idUsuario);
        crmBundle.setComplejo(complejo);
        intent.putExtras(crmBundle.instanceBundle());

        /*if (cursosUI instanceof CursosViewHolder.CursosUiRecurso) {
            CursosViewHolder.CursosUiRecurso cursosUiRecurso = (CursosViewHolder.CursosUiRecurso) cursosUI;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = cursosUiRecurso.getBitmap();
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                byte[] byteArray = stream.toByteArray();
                int tamanio = byteArray.length / 1024;
                Log.d(getTag(), "tamanio: " + tamanio);
                if (tamanio < 55) {
                    intent.putExtra("imagenByte", byteArray);
                }
            }
        }*/
        cursosAdapter.enabledCLick(cursosUI);
        startActivity(intent);

    }

    @Override
    public void showDialogListHijos(List<Persona> personaList) {


    }

    private void showmsj(String s) {

    }


    @Override
    public void onCursoSelected(CursosViewHolder.CursosUiRecurso cursosUiRecurso) {
        presenter.validateRol(cursosUiRecurso);
    }

    @Override
    public void onHorarioCursoSelected(CursosUI cursosUI, int cargaCurso) {
        presenter.onSelectHorarioCurso(cursosUI, cargaCurso);
    }


    @OnClick({R.id.imgProfileUser, R.id.btnSelector, R.id.date_picker_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgProfileUser:
//               view.getContext().startActivity(new Intent(view.getContext(), TabsTareasActivity.class));
                break;
            case R.id.btnSelector:
                presenter.onBtnSelectorAnioCLicked();
                break;
            case R.id.date_picker_button:
                presenter.onBtnSelectorAnioCLicked();
                break;
        }
    }


    private void setupTabMenu() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        presenter.onIbtnProgramaClicked();
                        break;
                    case 1:
                        presenter.onClickBtnAcceso();
                        break;
                    case 2:
                        presenter.onClickBtnConfiguracion();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onMenuSelected(Object o) {
        if (o instanceof ProgramaEduactivosUI) {
            ProgramaEduactivosUI programaEduactivosUI = (ProgramaEduactivosUI) o;
            presenter.onProgramaEducativoUIClicked(programaEduactivosUI);
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (o instanceof UsuarioAccesoUI) {
            UsuarioAccesoUI usuarioAccesoUI = (UsuarioAccesoUI) o;
            presenter.onAccesoSelected(usuarioAccesoUI);
        } else if (o instanceof ConfiguracionUi) {
            ConfiguracionUi configuracionUi = (ConfiguracionUi) o;
            presenter.onClickConfiguracion(configuracionUi);
        }
    }


    @Override
    public void cerrarSesion() {
        try {
            //SessionUser.getCurrentUser().delete();
            FlowManager.getDatabase(AppDatabase.class).reset(getApplicationContext());
            FlowManager.init(new FlowConfig.Builder(getApplicationContext()).build());

            //Process.killProcess(Process.myPid());
            //activity.sendFinish();
            finishAffinity();

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void borrarCache() {


    }

    @Override
    public void mostrarDialogoCerrarSesion(List<String> tableChange, boolean isUpdateTable) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(R.string.msg_dilogo_cerrar_sesion_titulo);
        if (isUpdateTable) {
            builder.setMessage(R.string.msg_dilogo_cerrar_sesion_descripcion_two);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "Presionos ACEPTAR");
                    presenter.onClickDialogoCerrarSesion();
                    dialog.cancel();

                }
            });
        } else {
            builder.setMessage(R.string.msg_dilogo_cerrar_sesion_descripcion);
            builder.setNeutralButton("Actualizar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.onClickRefrescar();
                }
            });
        }


        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //Create AdapterExample
        builder.create().show();
    }

    @Override
    public void showDialogInformacion(String empresa, String app, String desarrolladores) {
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            createDialogInformacion(app, info.versionName, empresa, desarrolladores).show();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void progressUpdateColor(int color) {
        if (menu != null) {
            MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
            if (menuItem != null) {
                Drawable drawable = menuItem.getIcon();
                if (drawable != null) {
                    drawable.mutate();
                    drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                }
            }
        }

    }

    @Override
    public void progressUpdateSuccess() {
        if (menu != null) {
            MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
            if (menuItem != null) {
                menuItem.setIcon(R.drawable.ic_refresh_menu);
            }
        }
    }

    @Override
    public void progressUpdateError() {
        if (menu != null) {
            MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
            if (menuItem != null) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_problema_de_sincronizacion);
                if (drawable != null) {
                    drawable.mutate();
                    drawable.setColorFilter(ContextCompat.getColor(this, R.color.md_red_A200), PorterDuff.Mode.SRC_IN);
                }
                menuItem.setIcon(drawable);
            }
        }
    }

    @Override
    public void mostrarDialogoBorrarMemoriaCahe() {

        showDialogoDesicion(R.string.msg_dilogo_borrar_cache_titulo,
                R.string.msg_dilogo_borrar_cache_descripcion,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "Presionos ACEPTAR");
                        presenter.onClickDialogoBorrarCahe();
                        dialog.cancel();
                    }
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }

    @Override
    public void showImportarCalendario(BEVariables beVariables) {
        ImportarActivity.launchImportarActivity(this, TipoImportacion.CALENDARIO_PERIODO, beVariables);
    }

    @Override
    public void viewActivityImportLogin(BEVariables beVariables) {
        ImportarActivity.launchImportarActivity(this, TipoImportacion.CONTACTOS, beVariables);
    }

    @Override
    public void callSynckService(int programaEducativoId) {
        ComplejoSyncIntenService.start(this, programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getApplicationContext());
    }

    @Override
    public void changePeriodo(PeriodoUi oldSelected, PeriodoUi periodoSelected) {
        periodoAdapter.togglePeriodo(oldSelected, periodoSelected);
    }


    @Override
    public void showPeriodoList(List<PeriodoUi> periodoList) {
        periodoAdapter.setColor("#0088cc");
        periodoAdapter.setPeriodoList(periodoList);
    }

    @Override
    public void setGradosList(List<Object> objectUIList, CRMBundle crmBundle) {
        //rvListaClases.setVisibility(View.VISIBLE);
        contenAsistencia.setVisibility(View.VISIBLE);
        int numberOfColumns = 2;
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcSecGrad.setLayoutManager(layoutManager);
        cursosAdapter = new CursosAdapter(objectUIList, this, imageLoader);
        cursosAdapter.setmAnimator(null);
        rcSecGrad.setHasFixedSize(true);
        rcSecGrad.setAdapter(cursosAdapter);

        getSupportFragmentManager(crmBundle);
        //getSupportFragmentManager().beginTransaction().add(R.id.frameCalendar, fragmentAsistenciaCA).commit();

    }

    @Override
    public void showContenAsistencia() {
        menu.findItem(R.id.action_cursos).setVisible(true);
        contenAsistencia.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideContenAsistencia() {
        menu.findItem(R.id.action_asistencia_grado).setVisible(true);
        contenAsistencia.setVisibility(View.GONE);
    }

    @Override
    public void showContenCurso() {
        menu.findItem(R.id.action_cursos).setVisible(false);
        contFragment.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideContenCurso() {
        menu.findItem(R.id.action_asistencia_grado).setVisible(false);
        contFragment.setVisibility(View.GONE);
    }


    public void showDialogoDesicion(int titulo, int mensaje, final DialogInterface.OnClickListener positivelistener, final DialogInterface.OnClickListener negativevelistener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", positivelistener).
                setNegativeButton("Cancelar", negativevelistener);
        //Create AdapterExample
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        Core2.getCore2(this).removeCore2Listener(this);
        menu = null;
        super.onDestroy();
    }


    @Override
    public void onStart(Class<?> aClass) {
        if (aClass.equals(getClass())) {
            if (toolbarprogress != null) toolbarprogress.setVisibility(View.GONE);
            if (menu != null) {
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);
            }
        }
    }

    @Override
    public void onLoad(int count) {
        try {
            if (toolbarprogress.getVisibility() == View.GONE) {
                toolbarprogress.setVisibility(View.VISIBLE);
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFinish() {
        try {
            if (toolbarprogress.getVisibility() == View.VISIBLE) {
                toolbarprogress.setVisibility(View.GONE);
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);
                presenter.onChangeFull(true);
            }
            presenter.onFinishSynck();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinishSimple() {
        try {
            if (toolbarprogress.getVisibility() == View.VISIBLE) {
                toolbarprogress.setVisibility(View.GONE);
                MenuItem menuItem = menu.findItem(R.id.menu_refrescar);
                menuItem.setVisible(true);
                //presenter.onChangeFull(true);
            }
            presenter.onFinishSynck();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {
        Log.d(TAG, "onPeriodoSelected " + periodoUi.getTipoName());
        presenter.onPeriodoSelected(periodoUi);
    }

    public <T extends Fragment> void getSupportFragmentManager(CRMBundle crmBundle) {
        try {
            AsistenciaCursoFragment fragment = AsistenciaCursoFragment.newInstance(crmBundle);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fragment.setEnterTransition(new Slide(Gravity.START));
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameCalendar, fragment).commit();
            fragmentTransaction.addToBackStack(null);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void onGradoSelected(GradoUi gradoUi, RecyclerView.ViewHolder holderSelected) {

        GradoHolder h = (GradoHolder) holderSelected;
        if (h.equals(olHolder)) {
            h.deseleccionar();
            olHolder.deseleccionar();
            olHolder = null;
        } else {
            if (olHolder != null) {
                olHolder.deseleccionar();
            }
            h.seleccionar();
            presenter.onSelectedGrado(gradoUi);
            olHolder = h;
        }
    }

    @Override
    public void onSelectedViewHolder(RecyclerView.ViewHolder holder) {
        this.olHolder = (GradoHolder) holder;
    }

    @Override
    public void onClickTutoriaCursoSelected(CursosUI cursosUI, int cargaCursoId) {
        presenter.onClickTutoriaCursoSelected(cursosUI, cargaCursoId);
    }

    @Override
    public void onClickReconocimientoCursoSelected(CursosViewHolder.CursosUiRecurso cursosUiRecurso, int cargaCurso) {
        presenter.onClickReconocimientoCursoSelected(cursosUiRecurso, cargaCurso);
    }


    @Override
    public void updateCalendar(CRMBundle crmbundle) {
        AsistenciaCursoFragment fragment = getFragment(AsistenciaCursoFragment.class);
        if (fragment != null) fragment.updateCalendar(crmbundle);
    }


    @Override
    protected void onResume() {
        Log.d(TAG, "onResume f");
        super.onResume();

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
    public void mostrarDialogoConfigAlarma() {
        drawerLayout.closeDrawer(GravityCompat.START);
        AlarmaUi alarmaUi = presenter.getAlarma();
        createCustomDialog(alarmaUi).show();

    }


    public AlertDialog createDialogInformacion(String nombre, String version, String empresa, String desarrolladores) {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_informacion_app, null);
        TextView app = (TextView) v.findViewById(R.id.nombreapp);
        TextView versionapp = (TextView) v.findViewById(R.id.version);
        TextView empresaapp = (TextView) v.findViewById(R.id.empresa);
        TextView desarrolladoresapp = (TextView) v.findViewById(R.id.desarrolladores);
        builder.setView(v);
        alertDialog = builder.create();
        app.setText(nombre);
        versionapp.setText(version);
        empresaapp.setText(empresa);
        desarrolladoresapp.setText(desarrolladores);
        return alertDialog;
    }

    public AlertDialog createCustomDialog(AlarmaUi alarmaUi) {
        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_alarma_config, null);
        TextView btnGuardar = (TextView) v.findViewById(R.id.guardar);
        TextView btnCancel = (TextView) v.findViewById(R.id.cancel);
        final TextView horaminute = (TextView) v.findViewById(R.id.hourMinute);
        final TextView tiempo = (TextView) v.findViewById(R.id.tiempo);
        final TextView descripcion = (TextView) v.findViewById(R.id.txtdescriocion);
        TimePicker timePicker = (TimePicker) v.findViewById(R.id.timePicker);
        builder.setView(v);
        alertDialog = builder.create();
        if (alarmaUi != null) {
            horaminute.setText(String.valueOf(alarmaUi.getHora() + getMinuto(alarmaUi.getMinute())));
            tiempo.setText(alarmaUi.getTiempo());
            descripcion.setText(String.valueOf("Todos los dias"));

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmaUi.getHora());
            calendar.set(Calendar.MINUTE, alarmaUi.getMinute());
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        }


        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                horaminute.setText(String.valueOf(hour + getMinuto(minute)));
                tiempo.setText(getAmPm(hour));
                descripcion.setText(String.valueOf("Todos los dias"));
                presenter.selectedHoraMinuteAlarma(hour, minute);
            }
        });
        btnGuardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Aceptar
                        if (presenter != null) presenter.aceptarHora();
                        if (alertDialog != null) alertDialog.dismiss();
                    }
                }
        );
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (alertDialog != null) alertDialog.dismiss();
                    }
                }
        );
        return alertDialog;
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

    @Override
    public void startAlarm(Calendar c, int programaEducativoId) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("programaEducativoId", programaEducativoId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        if (alarmManager != null)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

    }

    @Override
    public void setNombreEntidad(String nombreEntidad, String nombreGeoreferencia) {
        navBarTxtEntidad.setText(nombreEntidad);
        navBarTxtGeoreferencia.setText(nombreGeoreferencia);
    }

    @Override
    public void showBtnEntidadSelect() {
        imgMenuEntidad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBtnEntidadSelect() {
        imgMenuEntidad.setVisibility(View.GONE);
    }


    @OnClick(R.id.img_menu_entidad)
    public void onViewClickedMenuEntidad() {
        presenter.onClickedMenuEntidad();
    }

    @Override
    public void showPopListEntidad(final List<UsuarioRolGeoReferenciaUi> usuarioRolGeoReferenciaUis) {
        //Log.d(getTag(),"cantidad: " + programaEducativoUiList.size());
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(this, imgMenuEntidad);
        droppyBuilder.triggerOnAnchorClick(false);
        // Add normal items (text only)
        for (UsuarioRolGeoReferenciaUi usuarioRolGeoReferenciaUi : usuarioRolGeoReferenciaUis) {
            String item = usuarioRolGeoReferenciaUi.getNombreGeoreferencia() + "\n" + usuarioRolGeoReferenciaUi.getNombreEntidad();
            droppyBuilder.addMenuItem(new DroppyMenuItem(item, R.drawable.ic_programa_educativo));
        }
        ///droppyBuilder.addMenuItem(new DroppyMenuItem("Secundaria", R.drawable.ic_programa_educativo));
        // Set Callback handler
        droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int posicion) {
                try {
                    presenter.onSelectedEntidad(usuarioRolGeoReferenciaUis.get(posicion));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        droppyBuilder.build().show();
    }

    @Override
    public void showMensaje() {
        msjVacio.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMensaje() {
        msjVacio.setVisibility(View.GONE);
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
    public void showMessage(CharSequence error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageNotConnection() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(com.consultoraestrategia.ss_crmeducativo.core2.R.layout.dialog_not_connection);
        dialog.show();

    }

    @Override
    public void showCambiarFotoAlumnoActivity(int idCargaCurso, int georeferenciaId, int entidadId) {
        Intent intent = new Intent(this, CambiarFotoAlumnoActivity.class);
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setCargaCursoId(idCargaCurso);
        crmBundle.setGeoreferenciaId(georeferenciaId);
        crmBundle.setEntidadId(entidadId);
        intent.putExtras(crmBundle.instanceBundle());
        startActivity(intent);
    }

    @Override
    public void showActivityService2(int idUsuario, int empleadoId, int anioAcademicoIdFinal, int idPrograma, int georeferenciaId, int entidadId) {
        Intent intent = ServicesActivity.start(this, idUsuario, empleadoId, idPrograma, 0, 0, georeferenciaId, entidadId, 0, 0,0 , anioAcademicoIdFinal, false );
        startActivityForResult(intent, MainActivity.CHANGE_DATABASE_SERVICE2);
    }

    @Override
    public void hideProgressInit() {

        if (progressDialog != null) {
            progressInit.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 1500);
        }

    }

    @Override
    public void showProgressInit() {
        if (progressDialog != null
                && progressDialog.getDialog() != null
                && progressDialog.getDialog().isShowing()
                && !progressDialog.isRemoving()) {
            //dialog is showing so do something
        } else {
            //dialog is not showing
            progressDialog = new ProgressDialog();
            progressDialog.show(getSupportFragmentManager(), "ProgressDialog");
        }

       /* if (progressInit.getVisibility() == View.GONE) {
            animationView.setAnimation("constructionsite.json");
            animationView.setRepeatCount(ValueAnimator.INFINITE);
            progressInit.setVisibility(View.VISIBLE);
            animationView.playAnimation();
            progressInit.setAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_left_in));
        }*/
    }

    @Override
    public void showDialogFastData(int anioAcademicoIdFinal, int idUsuario) {
        Log.d(TAG, "showDialogFastData");
        FastData.start(this, anioAcademicoIdFinal, idUsuario, 0, 0);
    }

    @Override
    public void showSeleccionAnioAcademico() {
        SeleccionAnioAcademico seleccionAnioAcademico = SeleccionAnioAcademico.newInstance("Hola");
        seleccionAnioAcademico.show(getSupportFragmentManager(), "SeleccionAnioAcademico");
    }

    @Override
    public void showPerfil() {
        ChangePerfilFragment changePerfilFragment = new ChangePerfilFragment();
        changePerfilFragment.show(getSupportFragmentManager(), "ChangePerfilFragment");
    }

    @Override
    public void showCamaraReconocimientos(int idUsuario) {
        Intent intent = new Intent(this, CameraReconocimientoActivity.class);
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setUsuarioId(idUsuario);
        intent.putExtras(crmBundle.instanceBundle());
        startActivity(intent);
    }

    @Override
    public void showActivityAgenda(int idUsuario, int georeferenciaId, int empleadoId, int anioAcademicoIdFinal, int entidadId) {
        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setUsuarioId(idUsuario);
        crmBundle.setGeoreferenciaId(georeferenciaId);
        crmBundle.setEmpleadoId(empleadoId);
        crmBundle.setAnioAcademico(anioAcademicoIdFinal);
        crmBundle.setEntidadId(entidadId);
        Intent intent = new Intent(this, EventosActivty.class);
        intent.putExtras(crmBundle.instanceBundle());
        startActivity(intent);
    }

    @Override
    public void subscribeToTopic(int personaId) {
        FirebaseMessaging.getInstance().subscribeToTopic("persona_"+personaId);
        Log.d(TAG,"persona_"+personaId);
    }

    @Override
    public void showReconocimientoActivity(int cargaCursoId, int georeferenciaId, int entidadId) {
        Intent intent = new Intent(this, ListaAlumnosActivity.class);
        CRMBundle crmBundle = new CRMBundle(getIntent().getExtras());
        crmBundle.setCargaCursoId(cargaCursoId);
        crmBundle.setGeoreferenciaId(georeferenciaId);
        crmBundle.setEntidadId(entidadId);
        intent.putExtras(crmBundle.instanceBundle());
        startActivity(intent);
    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        if (f instanceof ProgressDialogView) {
            presenter.onProgressDialogViewDestroyed();
        }

        if (f instanceof SeleccionarAnioAcademicoView) {
            presenter.onSeleccionarAnioAcademicoViewDestroyed();
        }

        if (f instanceof ChangePerfilView) {
            presenter.onChangePerfilViewDestroyed();
        }
    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if (f instanceof ProgressDialogView) {
            presenter.attachView((ProgressDialogView) f);
            ((ProgressDialogView) f).setPresenter(presenter);
        }

        if (f instanceof SeleccionarAnioAcademicoView) {
            presenter.attachView((SeleccionarAnioAcademicoView) f);
            ((SeleccionarAnioAcademicoView) f).setPresenter(presenter);
        }

        if (f instanceof ChangePerfilView) {
            presenter.attachView(((ChangePerfilView) f));
            ((ChangePerfilView) f).setPresenter(presenter);
        }
    }

    @OnClick(R.id.btn_chat)
    public void onViewClicked() {
        startActivity(new Intent(this, ScremSplash.class));
    }
}
