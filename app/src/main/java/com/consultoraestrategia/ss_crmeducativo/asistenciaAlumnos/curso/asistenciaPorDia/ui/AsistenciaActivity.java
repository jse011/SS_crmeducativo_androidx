package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.transition.Slide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.AsistenciaPresenter;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.AsistenciaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.ui.JustificacionFragmetDialog;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.DialogFragmentJustificacion.listener.JustificacionListener;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.adapter.CellAsistenciaAlumnoHolder;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.adapter.TableAdapterAsistenciaAlumno;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaBundle;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.bundle.AsistenciaJustificaBundel;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.GetAsistenciaDiariaList;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase.SaveAsistencia;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioListener;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.franmontiel.fullscreendialog.FullScreenDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class AsistenciaActivity extends BaseActivity<AsistenciaView,AsistenciaPresenter> implements AsistenciaView , ITableViewListener, BaseFragmentListener, JustificacionListener, FullScreenDialogFragment.OnConfirmListener,
        FullScreenDialogFragment.OnDiscardListener, RepositorioListener {

    @BindView(R.id.tableAsistencia)
    FrameLayout tableAsistencia;
    @BindView(R.id.texto)
    TextView texto;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.msg_asistencia)
    TextView msg_asistencia;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;

    public static String color;

    TableAdapterAsistenciaAlumno tableAdapterAsistenciaAlumno;
    JustificacionFragmetDialog justificacionFragmetDialog;
    final String dialogTag = "dialog";
    public static final String ARG_ASISTENCIA_UI = "AsistenciaActivity.Asistencia";
    private TableView tableView;
    private FullScreenDialogFragment dialogFragment;


    public static Intent getAsistenciaActivity(Context context, AsistenciaBundle asistenciaBundle) {
        color = asistenciaBundle.getColor();
        Intent intent = new Intent(context, AsistenciaActivity.class);
        intent.putExtras(asistenciaBundle.getBundle());
        return intent;
    }

    private String TAG= AsistenciaActivity.class.getSimpleName();
    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FullScreenDialogFragment dialogFragment;
        if (savedInstanceState != null) {
            dialogFragment =
                    (FullScreenDialogFragment) getSupportFragmentManager().findFragmentByTag(dialogTag);
            if (dialogFragment != null) {
                dialogFragment.setOnConfirmListener(this);
                dialogFragment.setOnDiscardListener(this);
            }
        }
    }

    @Override
    protected AsistenciaPresenter getPresenter() {

        AsistenciaAlumnoRepository repository = new AsistenciaAlumnoRepository(
                new AsistenciaAlumnoLocalDataSource(
                        InjectorUtils.provideAsistenciaSesionAlumnoDao(),
                        InjectorUtils.provideTipoNotaDao(),
                        InjectorUtils.provideValorTipoNotaDao(),
                        InjectorUtils.provideParametrosDisenioDao()));

        presenter = new AsistenciaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetAsistenciaDiariaList(repository),
                new SaveAsistencia(repository));

        return presenter;
    }

    @Override
    protected AsistenciaView getBaseView() {
        return this;
    }


    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        Log.d(TAG, "setContentView");
        setContentView(R.layout.activity_asistencia_alumnos);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        createTableView();
        cambiarColor();
    }

    private void cambiarColor() {
        try{
            if(toolbar!=null)toolbar.setBackgroundColor(Color.parseColor(color));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void createTableView() {
        TableView.Values values = new TableView.Values();
        values.setmColumnHeaderHeight(getResources().getDimension(R.dimen.table_header_corner_evaluacion_resultado_height));
        values.setmRowHeaderWidth(getResources().getDimension(R.dimen.table_header_corner_asistenicia_width));
        tableView = new TableView(getContext(),values);
        tableAdapterAsistenciaAlumno = new TableAdapterAsistenciaAlumno(getContext());
        tableView.setAdapter(tableAdapterAsistenciaAlumno);
        tableView.setHasFixedWidth(false);
        tableView.setIgnoreSelectionColors(true);
        tableView.setShowHorizontalSeparators(true);
        tableView.setShowVerticalSeparators(true);
        tableAsistencia.addView(tableView);
        tableView.setTableViewListener(AsistenciaActivity.this);

    }

    @Override
    protected ViewGroup getRootLayout() {
        return toolbar;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_asistencia_alumno, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                presenter.saveAsistencias();
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showTableAsistenciaAlumno(List<List<ValorTipoNotaUi>> cellsAsistenciaList, List<ValorTipoNotaUi> ColumnsValorList, List<AlumnosUi> rowsAlumnosList, String color) {
        int row_header_width = (int) getContext().getResources().getDimension(R.dimen.table_header_corner_asistenicia_width);
        int row_header_height = (int) getContext().getResources().getDimension(R.dimen.table_header_corner_evaluacion_resultado_height);
        tableAdapterAsistenciaAlumno.setAllItems(ColumnsValorList, rowsAlumnosList,cellsAsistenciaList);
        tableAdapterAsistenciaAlumno.setCornerView(rowsAlumnosList.size(),row_header_width,row_header_height, color);
    }

    @Override
    public void showEmptyView() {
        texto.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyNota() {
        msg_asistencia.setVisibility(View.VISIBLE);
    }

    @Override
    public void sincronizar(int programaEducativoId) {
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        CMRE.saveNotifyChangeDataBase(this);
        finish();
    }

    @Override
    public void showDialogJustificacion(AsistenciaJustificaBundel asistenciaJustificaBundel) {
        /*dialogFragment = new FullScreenDialogFragment.Builder(this)
                .setTitle("Justificacion")
                .setConfirmButton("Aceptar")
                .setOnConfirmListener(this)
                .setOnDiscardListener(this)
                .setContent(JustificacionFragmetDialog.class, asistenciaJustificaBundel.getBundle())
                .build();

        dialogFragment.show(getSupportFragmentManager(), dialogTag);*/

        FragmentManager manager2 = getSupportFragmentManager();
        justificacionFragmetDialog = JustificacionFragmetDialog.newInstance(asistenciaJustificaBundel);
        justificacionFragmetDialog.show(manager2, JustificacionFragmetDialog.class.getSimpleName());
    }


    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        if(cellView instanceof CellAsistenciaAlumnoHolder){
            CellAsistenciaAlumnoHolder holder=(CellAsistenciaAlumnoHolder)cellView;
            ValorTipoNotaUi valorTipoNotaU= holder.getValorTipoNotaUi();
            AsistenciaUi asistenciaUi= valorTipoNotaU.getAsistenciaUi();
            CellAsistenciaAlumnoHolder oldholder= (CellAsistenciaAlumnoHolder) asistenciaUi.getViewHolder();
            if(oldholder!=null && holder.equals(oldholder)){
                holder.deseleccionar();
                oldholder.deseleccionar();
                asistenciaUi.setViewHolder(null);
                presenter.valorUnSelected(asistenciaUi);
            }else {

                if(valorTipoNotaU.isEnabled()){
                    if(oldholder!=null)oldholder.deseleccionar();
                    holder.seleccionar();
                    asistenciaUi.setViewHolder(holder);
                    presenter.valorSelected(asistenciaUi,valorTipoNotaU);
                }
            }

            }
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void saveJustificacion(AsistenciaJustificaBundel asistenciaJustificaBundel) {
        Log.d(TAG, asistenciaJustificaBundel.toString());
        presenter.saveJustificacion(asistenciaJustificaBundel);

    }

    public <T extends Fragment> void getSupportFragmentManager(final Class<T> fragmentClass) {
                try {
                    Fragment fragment = (Fragment) fragmentClass.newInstance();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        fragment.setEnterTransition(new Slide(Gravity.LEFT));
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_justificacion, fragment).commit();
                    fragmentTransaction.addToBackStack(null);

                } catch (Exception e) {
                    e.printStackTrace();

                }
    }

    @Override
    public void onConfirm(@Nullable Bundle result) {

    }

    @Override
    public void onDiscard() {

    }

    @Override
    public void onBackPressed() {
        if (dialogFragment != null && dialogFragment.isAdded()) {
            dialogFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onChangeList(List<RepositorioFileUi> repositorioFileUiList) {

    }
}
