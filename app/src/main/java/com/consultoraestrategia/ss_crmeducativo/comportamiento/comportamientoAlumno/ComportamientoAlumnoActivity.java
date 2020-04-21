package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.presenter.ComportamientoAlumnoPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.presenter.ComportamientoAlumnoPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui.FragmentComportAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetCurso;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.CursoUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.adapter.MyFragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ComportamientoAlumnoActivity extends BaseActivity<ComportamientoAlumnoview, ComportamientoAlumnoPresenter> implements ComportamientoAlumnoview, BaseFragmentListener, RepositorioListener {
    /*
        @BindView(R.id.progress)
        ProgressBar progressBar;*/
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_comportamiento)
    TabLayout tab_comportamiento;
    @BindView(R.id.vp_comportamiento)
    ViewPager vp_comportamiento;

    public static final String ARG_ALUMNO_ID = "alumnoId";
    @BindView(R.id.img_picture)
    CircleImageView imgPicture;
    @BindView(R.id.nombre)
    TextView nombre;
    @BindView(R.id.apellido)
    TextView apellido;
    @BindView(R.id.seccion)
    TextView seccion;
    @BindView(R.id.periodo)
    TextView periodo;
    @BindView(R.id.appBarLayout4)
    AppBarLayout appBarLayout4;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.fondo)
    ImageView fondo;

    @BindView(R.id.content)
    ConstraintLayout content;
    private Menu menu;

    public static Intent lauchActivity(Context context, CRMBundle bundle, int alumnoId) {
        Intent intent = new Intent(context, ComportamientoAlumnoActivity.class);
        Bundle args = new Bundle();
        args.putInt(ARG_ALUMNO_ID, alumnoId);
        args.putAll(bundle.instanceBundle());
        intent.putExtras(args);
        return intent;
    }

    String TAG = ComportamientoAlumnoActivity.class.getSimpleName();

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected ComportamientoAlumnoPresenter getPresenter() {
        presenter = new ComportamientoAlumnoPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetCurso(new ComportamientoRepository(new ComportamientoDataLocalSource(InjectorUtils.provideComportamientoDao(), InjectorUtils.providePersonaDao(),
                        InjectorUtils.provideCursoDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideAlumnoDao())))
        );
        return presenter;
    }

    @Override
    protected ComportamientoAlumnoview getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {

    }

    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    public void setupToolbar() {
        Log.d(TAG, "setupToolbar");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            appBarLayout4.setTransitionName("view");
        }
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comportamiento_alumno);
        ButterKnife.bind(this);
        setupToolbar();
        initViewPager();
        initFondo();
    }

    private void initFondo() {
        Calendar calendar = Calendar.getInstance();
        int recuros;
        switch (calendar.get(Calendar.MONTH)){
            case 0:
                recuros = R.drawable.january;
                break;
            case 1:
                recuros = R.drawable.february;
                break;
            case 2:
                recuros =  R.drawable.march;
                break;
            case 3:
                recuros = R.drawable.april;
                break;
            case 4:
                recuros = R.drawable.may;
                break;
            case 5:
                recuros = R.drawable.january;
                break;
            case 6:
                recuros =  R.drawable.july;
                break;
            case 7:
                recuros =  R.drawable.august;
                break;
            case 8:
                recuros = R.drawable.september;
                break;
            case 9:
                recuros =  R.drawable.october;
                break;
            case 10:
                recuros = R.drawable.november;
                break;
            case 11:
                recuros = R.drawable.december;
                break;
            default:
                recuros =  R.drawable.april;
                break;

        }
        Glide.with(this)
                .load(recuros)
                .into(fondo);
    }

    private void initViewPager() {
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(FragmentComportAlumnoC.newInstance(getIntent().getExtras()), "");
        //  fragmentAdapter.addFragment(FragmentComportAlumnoR.newInstance(getIntent().getExtras()), "Recibidos");
        vp_comportamiento.setOffscreenPageLimit(2);
        vp_comportamiento.setAdapter(fragmentAdapter);
        tab_comportamiento.setupWithViewPager(vp_comportamiento);

        appBarLayout4.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                    toolbarText.setVisibility(View.GONE);
                }

                if (scrollRange + verticalOffset <= 79) {
                    isShow = true;
                    toolbarText.setVisibility(View.VISIBLE);

                } else if (isShow) {
                    isShow = false;
                    toolbarText.setVisibility(View.GONE);

                }

                Log.d(TAG,"scrollRange + verticalOffset: " + scrollRange + verticalOffset);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vacio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDatos(CursoUi cursoUi) {

        String url = cursoUi.getAlumnoUi().getUrlProfile();
        if (!TextUtils.isEmpty(url)) {
            Glide.with(getApplicationContext())
                    .load(url)
                    .apply(Utils.getGlideRequestOptions())
                    .into(imgPicture);
        }
        nombre.setText(cursoUi.getAlumnoUi().getNombre());
        apellido.setText(cursoUi.getAlumnoUi().getLastName());
        seccion.setText(cursoUi.getSeccion());
        periodo.setText(cursoUi.getPeriodo());
        try {
            appBarLayout4.setBackgroundColor(Color.parseColor(cursoUi.getColor1()));
            tab_comportamiento.setSelectedTabIndicatorColor(Color.parseColor(cursoUi.getColor1()));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume activity");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop activity");
        super.onStop();

    }


    @Override
    public void onChangeList(List<RepositorioFileUi> repositorioFileUiList) {

    }
}
