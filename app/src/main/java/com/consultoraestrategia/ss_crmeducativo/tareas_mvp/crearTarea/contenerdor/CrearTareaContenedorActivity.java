package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.contenerdor;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.mainGraficos.adapters.MyFragmentAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.BaseRepositoriFragmento;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.FragmentoTipo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioListener;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.bundle.CrearTareaBundle;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.listener.CrearTareaListener;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.ui.CrearTareasActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CrearTareaContenedorActivity extends AppCompatActivity implements CrearTareaListener, ViewPager.OnPageChangeListener, RepositorioListener {

    private static String colorCurso;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private MyFragmentAdapter fragmentAdapter;

    public static void launchActivityCrearTareas(Context context, CrearTareaBundle crearTareaBundle) {
        Intent intent = new Intent(context, CrearTareasActivity.class);
        colorCurso = crearTareaBundle.getColorCurso();
        intent.putExtras(crearTareaBundle.getBundle());
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_tarea_contenedor_activity);
        ButterKnife.bind(this);
        setupToolbar();
        setupViewPager();
        cambiarColor();
        bloqOrientation();
    }
    public void bloqOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void cambiarColor() {
        try {
            if (colorCurso!=null){
                Log.d("coloCUrso", colorCurso);
                tabs.setSelectedTabIndicatorColor(Color.parseColor(colorCurso));
                toolbar.setBackgroundColor(Color.parseColor(colorCurso));
            }else {
                tabs.setSelectedTabIndicatorColor(getApplicationContext().getResources().getColor(R.color.teal));
                toolbar.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.teal));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setupToolbar() {
        getSupportActionBar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager() {
        fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        //fragmentAdapter.addFragment(CrearTareasActivity.newInstance(new CrearTareaBundle(getIntent().getExtras())), "Tarea");
        RepositorioTBunble tBunble = new RepositorioTBunble();
        tBunble.setRepositorio(RepositorioUi.ARCHIVO);
        tBunble.setFragmentoTipo(FragmentoTipo.SUBIDA_DESCARGA_ARCHIVOS_VINCULOS);
        tBunble.setColorCurso(colorCurso);
        fragmentAdapter.addFragment(BaseRepositoriFragmento.newInstance(tBunble), "Archivos");
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tareas_menu, menu);
        return true;
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

    private void guardarTarea(int tipoId) {
        //if(crearTareaFragment!=null)crearTareaFragment.guardarTarea(tipoId,repositorioFileUiList);
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onChangeList(List<RepositorioFileUi> repositorioFileUiList) {
        Log.d(getClass().getSimpleName(),"onChangeList " + repositorioFileUiList.size());
    }

    @Override
    public void onBackPressed() {
        backPressed();
    }

    public void backPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView text= new TextView(this);
        text.setPadding(30,30,30,0);
        text.setText("¿Seguro que desea salir?");
        text.setGravity(Gravity.CENTER);
        builder.setView(text)
                .setTitle("Mensaje de confirmación")
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
                        finish();
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
