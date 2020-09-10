package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.google.android.material.appbar.AppBarLayout;

import androidx.transition.Slide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseActivity;
import com.consultoraestrategia.ss_crmeducativo.base.viewpager.LifecycleImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.TableViewAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.SelectorIconosCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.SelectorValoresCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column.AlumnoColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column.GrupoColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.row.RubroRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.GrupoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.listener.EvalRubBidIndListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui.EvalRubBidIndFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.EvaluacionRubricaBidimencionalView;
import com.consultoraestrategia.ss_crmeducativo.grouplist.ui.ListaGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.infoUsuario.InfoUsuarioFragment;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.utils.Tutorial;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.recyclerview.RowHeaderRecyclerViewAdapter;
import com.evrencoskun.tableview.listener.ITableViewListener;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;


/**
 * Created by SCIEV on 8/03/2018.
 */

@SuppressLint("Registered")
public abstract class EvaluacionBimencionalAbstractActividad extends BaseActivity<EvaluacionRubricaBidimencionalView, EvaluacionRubricaBidimencionalPresenter> implements EvaluacionRubricaBidimencionalView, ITableViewListener, ListaGrupoFragment.RegistroGroupList, EvalRubBidIndListener, View.OnClickListener, LifecycleImpl.LifecycleListener {
    public final static String EXTRA_RUB_BID_ID = "EvaluacionBimencionalAbstractActividad.Rubrica_Id";
    public final static String EXTRA_CARGACURSO_ID = "EvaluacionBimencionalAbstractActividad.CargaCursoId";
    @BindView(R.id.edtRubro)
    TextView edtRubro;
    @BindView(R.id.table)
    TableView table;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.msg_actualizar)
    TextView msgActualizar;
    @BindView(R.id.frameLayoutGrupos)
    FrameLayout frameLayoutGrupos;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;


    private String TAG = EvaluacionBimencionalAbstractActividad.class.getSimpleName();
    private TableViewAdapter adapter;
    private ListaGrupoFragment groupListFragment;
    private EvalRubBidIndFragment fragment;
    private InfoUsuarioFragment infoUsuarioFragment;
    private SearchView searchView;
    private MenuItem search;
    private boolean initTutorial;
    private SpotsDialog alertDialog;
    private SpotsDialog alertDialogClose;

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    protected AppCompatActivity getActivity() {
        return null;
    }

    @Override
    protected EvaluacionRubricaBidimencionalPresenter getPresenter() {
        return null;
    }

    @Override
    protected EvaluacionRubricaBidimencionalView getBaseView() {
        return this;
    }

    @Override
    protected Bundle getExtrasInf() {
        return getIntent().getExtras();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_eval_rubriva_bidimencional);
        ButterKnife.bind(this);
        setupToolbar();
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
        alertDialog = new SpotsDialog(this, R.style.SpotsDialogRubro);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

        alertDialogClose = new SpotsDialog(this, R.style.SpotsDialogEvaluacionClose);
        alertDialogClose.setCanceledOnTouchOutside(false);
        alertDialogClose.setCancelable(false);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return table;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return progressBar;
    }

    @Override
    public void showTableview(final String titulo, final List<RowHeader> headerList, final List<ColumnHeader> rows, final List<List<Cell>> bodyList) {
        _showTableview(titulo, headerList, rows, bodyList);
    }

    private void _showTableview(final String titulo, List<RowHeader> headerList, List<ColumnHeader> rows, List<List<Cell>> bodyList) {
        adapter = new TableViewAdapter(getActivity(), this);
        table.setAdapter(adapter);
        table.setTableViewListener(this);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        table.setIgnoreSelectionColors(true);
        adapter.setAllItems(headerList, rows, bodyList);
        int row_header_width = (int) getResources().getDimension(R.dimen.table_firstheader_width_eval_session);
        int row_header_height = (int) getResources().getDimension(R.dimen.table_body_height_eval_session);
        adapter.setCornerView(titulo, row_header_width, row_header_height);
        /*if(!initTutorial){
            initTutorial = true;
            table.post(new Runnable() {
                @Override
                public void run() {
                    showTutorial(false);
                }
            });
        }*/

    }

    private void showTutorial(boolean preferents) {
        Tutorial.showTutorialEvaluacionAlumno(EvaluacionBimencionalAbstractActividad.this, table.getColumnHeaderRecyclerView(), table.getRowHeaderRecyclerView(), table.getCellRecyclerView(), search, preferents);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupChilFragmentCycleLife();
    }

    private void setupChilFragmentCycleLife() {
        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new LifecycleImpl(0, this), true);
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition, int p_nYPosition) {
        if (holder instanceof SelectorIconosCellViewHolder) {
            SelectorIconosCellViewHolder selectorIconosCellViewHolder = (SelectorIconosCellViewHolder) holder;
            if (presenter != null)
                presenter.onClickSelector(selectorIconosCellViewHolder.getEvalProcUi());
        }

        if (holder instanceof SelectorValoresCellViewHolder) {
            SelectorValoresCellViewHolder selectorValoresCellViewHolder = (SelectorValoresCellViewHolder) holder;
            if (presenter != null)
                presenter.onClickSelector(selectorValoresCellViewHolder.getEvalProcUi());
        }
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition) {
        if (holder instanceof RubroRowViewHolder) {
            RubroRowViewHolder rubroRowViewHolder = (RubroRowViewHolder) holder;
            onClickRubroEvaluacion(rubroRowViewHolder.getRubEvalProcUi());
        }
    }

    public void onClickRubroEvaluacion(RubEvalProcUi rubEvalProcUi) {
        if (presenter == null) return;
        presenter.onClickRubroEvaluacion(rubEvalProcUi);
    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition) {
    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder holder, int p_nYPosition) {
        if (holder instanceof AlumnoColumnViewHolder) {
            AlumnoColumnViewHolder alumnoColumnViewHolder = (AlumnoColumnViewHolder) holder;
            presenter.onClickAlumno(alumnoColumnViewHolder.getAlumnoProcesoUi());
        } else if (holder instanceof GrupoColumnViewHolder) {
            GrupoColumnViewHolder grupoColumnViewHolder = (GrupoColumnViewHolder) holder;
            presenter.onSelectGrupo(grupoColumnViewHolder.getGrupoProcesoUi());
        }
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder holder, int p_nYPosition) {

    }

    @Override
    public void showTeamList(int cargaCursoId, int cursoId, int idCargaAcademica, int rubroEvaluacionId) {

    }

    @Override
    public void showTeamList(int cargaCursoId, int cursoId, int idCargaAcademica, String rubroEvaluacionId) {
        Log.d(TAG, " showTeamList rubro " + rubroEvaluacionId);
        Bundle bundle = new Bundle();
        bundle.putInt("cargaCursoId", cargaCursoId);
        bundle.putInt("cursoId", cursoId);
        bundle.putInt("idCargaAcademica", idCargaAcademica);
        bundle.putString("rubroEvaluacionId", rubroEvaluacionId);
        getSupportFragmentManager(ListaGrupoFragment.class, bundle);

    }

    @Override
    public void onSaveGroupList() {
        Log.d(TAG, "onSaveGroupList");
        presenter.onChangeRubricaBidimencional();
        hideFrameLayoutGrupos();
    }

    @Override
    public void onCancelarGroupList() {

    }

    @Override
    public void startEvalBidInd() {
        FragmentManager manager = getSupportFragmentManager();
        if (fragment != null
                && fragment.getDialog() != null
                && fragment.getDialog().isShowing()
                && !fragment.isRemoving()) {
            //dialog is showing so do something
        } else {
            //dialog is not showing
            fragment = EvalRubBidIndFragment.newInstance(getIntent().getExtras());
            fragment.show(manager, EvalRubBidIndFragment.class.getSimpleName());
        }

    }

    @Override
    public void addRowRange(GrupoProcesoUi grupoProcesoUi, List<ColumnHeader> columnHeaders, List<List<Cell>> cellItems) {
        RowHeaderRecyclerViewAdapter rowHeaderRecyclerViewAdapter = (RowHeaderRecyclerViewAdapter) table.getRowHeaderRecyclerView().getAdapter();
        List<ColumnHeader> columnHeaderAdapterList = (List<ColumnHeader>) rowHeaderRecyclerViewAdapter.getItems();
        int columPositionStart = columnHeaderAdapterList.indexOf(grupoProcesoUi) + 1;

        int count = 0;
        for (ColumnHeader columnHeader : columnHeaders) {
            table.getAdapter().addRow(columPositionStart, columnHeader, cellItems.get(count));
            columPositionStart++;
            count++;
        }

        //adapter.addRowRange(columPositionStart, columnHeaders, cellItems);
    }

    @Override
    public void removeRowRange(GrupoProcesoUi grupoProcesoUi, List<ColumnHeader> columnHeaders, List<List<Cell>> CellItems) {
        RowHeaderRecyclerViewAdapter rowHeaderRecyclerViewAdapter = (RowHeaderRecyclerViewAdapter) table.getRowHeaderRecyclerView().getAdapter();
        List<ColumnHeader> columnHeaderAdapterList = (List<ColumnHeader>) rowHeaderRecyclerViewAdapter.getItems();
        for (ColumnHeader columnHeader : columnHeaders) {
            int position = columnHeaderAdapterList.indexOf(columnHeader);
            if (position != -1) table.getAdapter().removeRow(position);
        }

        //adapter.removeRowRange(columPositionStart,columnHeaders, CellItems);
    }

    @Override
    public void showTitle(String title) {
        edtRubro.setText(title);
    }

    public void saveEvaluacionVidimencional(ColumnHeader evaluado, EvalProcUi evalProcUi, List<EvalProcUi> evalProcUiList) {
        if (presenter == null) return;
        Log.d(TAG, "saveEvaluacionVidimencional" + evalProcUiList.size());
        presenter.onSaveEvaluacion(evalProcUi, evalProcUiList);

    }

    @Override
    public void tableNotifyDataSetChanged() {
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void changeCellItemRange(int posicion, List<Cell> cellList) {
        adapter.changeCellItemRange(posicion, cellList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_help:
                showTutorial(true);
                break;
            default:
                Log.d(getTag(), "is atras: " + id);
                presenter.recalcularMediaDesviacion();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            presenter.recalcularMediaDesviacion();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_rubrica, menu);

        search = menu.findItem(R.id.action_search);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search(searchView);
        return true;
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (presenter != null) presenter.onQueryTextSubmit(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (presenter != null) presenter.onQueryTextChange(newText);
                return true;
            }
        });
    }

    @Override
    public void showAlertDialogIndicador(IndicadorUi indicadorUi) {

        @SuppressLint("InflateParams")
        View view = getActivity().getLayoutInflater().inflate(R.layout.alert_dialog_indicador, null);
        AlertDialogViewHolder viewHolder = new AlertDialogViewHolder(view);
        viewHolder.bind(indicadorUi);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder.create().show();
    }


    @OnClick(R.id.msg_actualizar)
    public void onViewClicked() {
        presenter.onBtnActualizarRubro();
    }

    @Override
    public void showActividadImportacion(BEVariables beVariables) {
        ImportarActivity.launchImportarActivity(this, TipoImportacion.RUBROEVALUACION, beVariables);
    }

    @Override
    public void onClick(View view) {
        Log.d(getTag(), "Click");
        switch (view.getId()) {
            case R.id.root:
                presenter.onClikCornerTableView();
                break;
        }
    }

    @Override
    public void onChildsFragmentViewCreated() {

    }

    @Override
    public void onFragmentViewCreated(Fragment f, View view, Bundle savedInstanceState) {

    }

    @Override
    public void onFragmentResumed(Fragment f) {

    }

    @Override
    public void onFragmentViewDestroyed(Fragment f) {
        if (f instanceof EvalRubBidIndFragment) {
            presenter.onDestroyDialogEvalRubrica();
        }
    }

    @Override
    public void onFragmentActivityCreated(Fragment f, Bundle savedInstanceState) {
        if (f instanceof EvalRubBidIndFragment) {
            presenter.onAttachView((EvalRubBidIndFragment) f);
            presenter.onCreateDialogEvalRubrica();
        }
    }


    static class AlertDialogViewHolder implements View.OnClickListener, Runnable {
        @BindView(R.id.text_titulo)
        TextView textTitulo;
        @BindView(R.id.text_subtitulo)
        TextView textSubtitulo;
        @BindView(R.id.text_descripcion)
        TextView textDescripcion;
        @BindView(R.id.txt_campos_accion)
        TextView txtCamposAccion;
        @BindView(R.id.txt_competencia)
        TextView txtCompetencia;
        @BindView(R.id.txt_capacidad)
        TextView txtCapacidad;
        @BindView(R.id.textView64)
        TextView textView64;
        //
        @BindView(R.id.txt_vermas_desem)
        TextView txtVermasDesem;
        @BindView(R.id.txt_desempenio)
        TextView txtDesempenio;
        @BindView(R.id.conten_desempenio)
        ConstraintLayout contenDesempenio;
        private final static int MAXLINE = 4;
        private final static String[] VER = {"Ver menos", "Ver más"};

        AlertDialogViewHolder(View view) {
            ButterKnife.bind(this, view);
            txtVermasDesem.setOnClickListener(this);
            contenDesempenio.setOnClickListener(this);
        }

        public void bind(IndicadorUi indicadorUi) {
            textTitulo.setText(indicadorUi.getTitulo());
            textSubtitulo.setText(indicadorUi.getSubtitulo());
            if (!TextUtils.isEmpty(indicadorUi.getDescripcion())) {
                textDescripcion.setText(indicadorUi.getDescripcion());
            } else {
                textDescripcion.setText(R.string.activity_evalaucion_bidimencional_indicador_detalle);
            }

            txtCamposAccion.setText(indicadorUi.getCamposTemaicos());
            txtCompetencia.setText(indicadorUi.getCompetenciaDesc());
            txtCapacidad.setText(indicadorUi.getCapacidadDesc());
            txtDesempenio.setText(indicadorUi.getDesempenioDesc());
            txtDesempenio.post(this);
        }


        @Override
        public void onClick(View view) {
            if (txtDesempenio.isSelected()) {
                txtDesempenio.setSelected(false);
                txtVermasDesem.setText(VER[1]);
                txtDesempenio.setMaxLines(MAXLINE);
                txtDesempenio.setEllipsize(TextUtils.TruncateAt.END);
            } else {
                txtDesempenio.setSelected(true);
                txtVermasDesem.setText(VER[0]);
                txtDesempenio.setMaxLines(Integer.MAX_VALUE);
                txtDesempenio.setEllipsize(null);
            }
        }

        @Override
        public void run() {
            if (txtDesempenio.getLineCount() >= MAXLINE) {
                txtDesempenio.setMaxLines(MAXLINE);
                txtDesempenio.setEllipsize(TextUtils.TruncateAt.END);
                txtVermasDesem.setVisibility(View.VISIBLE);
            } else {
                txtVermasDesem.setVisibility(View.GONE);
                txtDesempenio.setMaxLines(Integer.MAX_VALUE);
                txtDesempenio.setEllipsize(null);
            }
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSincronization(int programaEducativoId, String rubBidId) {
        CallService.jobServiceExportarTipos(this, TipoExportacion.RUBROEVALUACION_FORMULA_ASOCIADOS);
        SimpleSyncIntenService.start(this, programaEducativoId);
        SynckService.start(this,programaEducativoId);
    }

    @Override
    public void showMsgActualizacion() {
        msgActualizar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMsgActualizacion() {
        msgActualizar.setVisibility(View.GONE);
    }

    @Override
    public void showinfoUsuario(AlumnoProcesoUi alumnoProcesoUi, String rubricaId) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap = alumnoProcesoUi.getBitmap();
        byte[] byteArray = null;
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byteArray = stream.toByteArray();
            int tamanio = byteArray.length / 1024;
            Log.d(getTag(), "tamanio: " + tamanio);
            if (tamanio > 70) byteArray = null;
        }
        FragmentManager manager = getSupportFragmentManager();
        infoUsuarioFragment = InfoUsuarioFragment.newInstance(byteArray, alumnoProcesoUi.getUrlProfile(), alumnoProcesoUi.getNombre(), alumnoProcesoUi.getId(), rubricaId, new CRMBundle(getIntent().getExtras()));

        infoUsuarioFragment.show(manager, InfoUsuarioFragment.class.getSimpleName());
    }

    @Override
    public void showInfoRubro(RubEvalProcUi rubEvalProcUi) {
        FragmentManager manager = getSupportFragmentManager();
        InfoRubroFragment fragment = InfoRubroFragment.newInstance(rubEvalProcUi.getId());
        fragment.show(manager, InfoRubroFragment.class.getSimpleName());
    }


    public <T extends Fragment> void getSupportFragmentManager(final Class<T> fragmentClass, Bundle bundle) {
        try {

            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //setExitTransition(new Slide(Gravity.START));
                //setEnterTransition(new Slide(Gravity.START));
                fragment.setEnterTransition(new Slide(Gravity.LEFT));
            }

            // Insert the fragment by replacing any existing fragment

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutGrupos, fragment).commit();
            fragmentTransaction.addToBackStack(null);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void showFrameLayoutGrupos() {
        edtRubro.setVisibility(View.GONE);
        table.setVisibility(View.GONE);
        frameLayoutGrupos.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFrameLayoutGrupos() {
        edtRubro.setVisibility(View.VISIBLE);
        table.setVisibility(View.VISIBLE);
        frameLayoutGrupos.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void cerrar() {
        finish();
    }

    @Override
    public void showCloseDialogProgress() {

        if (!((Activity) this).isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (alertDialogClose != null) alertDialogClose.show();
                }
            });
        }
    }

    @Override
    public void hideCloseDialogProgress() {
        Log.d(TAG, "hideDialogProgress");
        if (!((Activity) this).isFinishing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (alertDialogClose != null) alertDialogClose.dismiss();
                }
            });
        }


    }

    @Override
    public void showDialogProgress() {
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
        Log.d(TAG, "hideDialogProgress");
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
    public void hideRow(AlumnoProcesoUi alumnoProcesoUi) {
        RowHeaderRecyclerViewAdapter rowHeaderRecyclerViewAdapter = (RowHeaderRecyclerViewAdapter) table.getRowHeaderRecyclerView().getAdapter();
        List<ColumnHeader> columnHeaderList = (List<ColumnHeader>) rowHeaderRecyclerViewAdapter.getItems();
        int position = columnHeaderList.indexOf(alumnoProcesoUi);
        if (position != -1) table.hideRow(position);
    }

    @Override
    public void showRow(int posicion, AlumnoProcesoUi alumnoProcesoUi) {
        RowHeaderRecyclerViewAdapter rowHeaderRecyclerViewAdapter = (RowHeaderRecyclerViewAdapter) table.getRowHeaderRecyclerView().getAdapter();
        List<ColumnHeader> columnHeaderList = (List<ColumnHeader>) rowHeaderRecyclerViewAdapter.getItems();
        columnHeaderList.add(posicion, alumnoProcesoUi);
        //int position = columnHeaderList.indexOf(alumnoProcesoUi);*/
        table.showRow(posicion);
        //table.showAllHiddenRows();

    }


}
