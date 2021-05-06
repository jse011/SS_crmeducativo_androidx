package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.ui;


import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui.InfoComentarioDialog;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui.InfoRubroListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.FooterNotasAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.TableAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.ComentarioCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.EnptyCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.ImageCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.PublicarCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.SelectorNumericoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.TecladoNumericoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.TextoAvanzadoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.cell.TextoCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.row.AlumnoRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor.EvaluacionContainerActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EquipoEvaluacionProceso.LocalEquipoEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EquipoEvaluacionProceso.RemoteEquipoEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso.LocalEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso.RemoteEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.rubroEvaluacionProceso.LocalRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.rubroEvaluacionProceso.RemoteRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.local.RubroLocalDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.remote.RubroRemoteDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetAlumnoList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetIndicadorList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubroList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.PublicarEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveAlumnoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveListAlumnoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.BodyCellViewUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.TipoCompetencia;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.ui.EvaluacionGlobalFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.RegistroPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.RegistroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.RegistroView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.adapters.MenuLateralAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.adapters.TablaFixes.MyTableAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.dialogs.IndicadorDialog;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.listener.EvaluacionListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.listener.IndicadorListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.listener.RubroEvaluacionListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.listener.EvaluacionDialogListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.ui.EvaluacionTecladoNumericoFragment;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.listener.InfoRubroCallback;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.infoUsuario.InfoUsuarioFragment;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.listener.PresicionListener;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.PresicionDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.listener.FragmentLifecycle;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by irvinmarin on 23/02/2017.
 */

public class RegistroFragment extends Fragment implements RegistroView, EvaluacionListener, RubroEvaluacionListener, IndicadorListener, MyTableAdapter.NotasListener, FragmentLifecycle, EvaluacionDialogListener, InfoRubroCallback, PresicionListener, InfoRubroListener, ITableViewListener {

    @BindView(R.id.rec_Menu)
    RecyclerView rec_Menu;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.tablefixheaders)
    LinearLayout tablefixheaders;
    private static final String TAG = RegistroFragment.class.getSimpleName();
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtInidicador)
    public TextView txtInidicador;
    @BindView(R.id.contentVacio)
    LinearLayout contentVacio;
    @BindView(R.id.root)
    CardView root;
    @BindView(R.id.txtVacio)
    TextView txtVacio;
    @BindView(R.id.img_competencia)
    ImageView imgCompetencia;
    @BindView(R.id.rc_footer_nota)
    RecyclerView rcFooterNota;
    @BindView(R.id.table)
    TableView table;
    @BindView(R.id.btn_eye)
    ImageView btnEye;
    @BindView(R.id.btn_footer)
    ImageView btnFooter;
    @BindView(R.id.btn_clear)
    ImageView btnClear;
    @BindView(R.id.constraintLayout11)
    ConstraintLayout constraintLayout11;
    @BindView(R.id.textView163)
    TextView textView163;
    @BindView(R.id.textDescripcion)
    TextView textDescripcion;
    @BindView(R.id.textView80)
    TextView textView80;
    @BindView(R.id.frameLayoutGrupos)
    FrameLayout frameLayoutGrupos;
    Unbinder unbinder;
    private MenuLateralAdapter menuLateralAdapter;
    private RegistroPresenter presenter;
    private IndicadorDialog indicadorDialog;
    //private MyTableAdapter adapter;
    private EvaluacionGlobalFragment evaluacionSelectorNotas;
    private EvaluacionTecladoNumericoFragment evaluacionTecladoNumerico;
    private InfoRubroFragment infoRubroFragment;
    private InfoUsuarioFragment infoUsuarioFragment;
    private FooterNotasAdapter footerNotasAdapter;
    private boolean initTutorial;
    private TableAdapter tableAdapter;

    public static RegistroFragment newInstance() {
        return new RegistroFragment();
    }

    //region ciclo de vida del fragmento


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_registro, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();

        setupFloatButton();
        setupFooterNota();
    }

    private void setupFooterNota() {
        rcFooterNota.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        footerNotasAdapter = new FooterNotasAdapter(new ArrayList<NotaUi>(), RubroEvaluacionUi.TipoNota.TEXTO);
        rcFooterNota.setAdapter(footerNotasAdapter);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    //endregion ciclo de vida del fragmento

    private void setupPresenter() {

        RubroRepository rubroRepository = RubroRepository.getInstance(
                new RubroLocalDataSourse(InjectorUtils.provideRubroEvaluacionDao(),
                        InjectorUtils.provideTipoNotaDao(),
                        InjectorUtils.provideIndicadorDao(), InjectorUtils.provideEvaluacionProcesoDao()),
                new RubroRemoteDataSourse(),
                new LocalRubroEvaluacionProceso(),
                new RemoteRubroEvaluacionProceso(),
                new LocalEquipoEvaluacionProceso(),
                new RemoteEquipoEvaluacionProceso(),
                new LocalEvaluacionProceso(),
                new RemoteEvaluacionProceso(new RestAPI())
        );

        presenter = new RegistroPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GetRubroList(rubroRepository),
                new GetAlumnoList(rubroRepository),
                new GetIndicadorList(rubroRepository),
                new SaveAlumnoEvaluacion(rubroRepository),
                new GetRubro(rubroRepository),
                new SaveListAlumnoEvaluacion(rubroRepository),
                new PublicarEvaluacion(rubroRepository));
        presenter.setExtras(getArguments());

        setPresenter(presenter);
    }

    @Override
    public void setPresenter(RegistroPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }

    private void setupAdapter() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        /*evaluacionAdapter = new AlumnoEvaluacionAdapter(new ArrayList<AlumnosEvaluacionUi>(), this);
        evaluacionAdapter.setRecyclerView(rvasistencia);
        rvasistencia.setAdapter(evaluacionAdapter);
        rvasistencia.setLayoutManager(linearLayoutManager);
        rvasistencia.setHasFixedSize(true);*/

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        menuLateralAdapter = new MenuLateralAdapter(this, new ArrayList<RubroEvaluacionUi>());
        menuLateralAdapter.setRecyclerView(rec_Menu);
        rec_Menu.setAdapter(menuLateralAdapter);
        rec_Menu.setLayoutManager(linearLayoutManager2);
        rec_Menu.setHasFixedSize(true);
    }

    private void setupFloatButton() {
        //region float buton OnTouchListener
       /* floatingActionButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        //presenter.actionDowButtonFloat(MotionEvent.ACTION_DOWN, event.getRawX(), event.getRawY(), floatingActionButton.getX(), floatingActionButton.getY());
                        break;

                    case MotionEvent.ACTION_MOVE:
                        //presenter.actionMoveButtonFloat(MotionEvent.ACTION_MOVE, event.getRawX(), event.getRawY());
                        break;

                    case MotionEvent.ACTION_UP:
                        presenter.actionUpButtonFloat();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });*/
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.actionUpButtonFloat();
            }
        });
        //endregion float buton OnTouchListener
    }


    //region  presenter
    @Override
    public void onEvaluacionSelect(NotaUi evaluacion) {
        Log.d(TAG, "onEvaluacionSelect");

        presenter.onClickEvaluacion(evaluacion);
    }

    @Override
    public void onEvaluacionLongSelect(NotaUi evaluacion) {
        Log.d(TAG, "onEvaluacionLongSelect");
        presenter.onClickLongEvaluacion(evaluacion);
    }

    @Override
    public void onItemSelectRubro(RubroEvaluacionUi rubroUi) {
        Log.d(TAG, "onItemSelectRubro");
        presenter.onItemSelectRubroEval(rubroUi);
    }

    public void onIndicadorSelect(IndicadorUi indicadorUi) {
        Log.d(TAG, "onIndicadorSelect");
        presenter.onClickItemIndicador(indicadorUi);
    }

    @Override
    public void onClickNota(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi) {

        Log.d(TAG, "onClickNota");
        presenter.onSelectAlumnoEvaluacion(alumnosEvaluacionUi, notaUi);
    }

    @Override
    public void onClickSelectorNota(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi) {
        Log.d(TAG, "onClickSelectorNota");
        presenter.onSelectAlumnoEvaluacionSelectorNota(alumnosEvaluacionUi, notaUi);
    }

    @Override
    public void onClickTecladoNumerico(AlumnosEvaluacionUi alumnosEvaluacionUi, NotaUi notaUi) {
        Log.d(TAG, "onClickTecladoNumerico");
        presenter.onSelectAlumnoEvaluacionTecladoNumerico(alumnosEvaluacionUi, notaUi);
    }

    @Override
    public void onClickAlumno(AlumnosEvaluacionUi item) {
        presenter.onClickAlumno(item);
    }

    @Override
    public void onLongClickNota(AlumnosEvaluacionUi item, NotaUi nota) {
        Log.d(TAG, "onLongClickNota");
        presenter.onLongClickNota(item, nota);
    }

    @Override
    public void onClickBtnPublicar(AlumnosEvaluacionUi item, OptionPublicar optionPublicar) {
        presenter.onClickBtnPublicar(item, optionPublicar);
    }

    @Override
    public void onClickBtnComentario(AlumnosEvaluacionUi item, OptionComentario comentario) {
        presenter.onClickBtnComentario(item, comentario);
    }

    //endregion presenter

    //region view
    //region Progress
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgess() {
        progressBar.setVisibility(View.GONE);
    }
    //endregion Progress

    //region ButtonFloat
    @Override
    public void moverButtonfloat(float posicionX, float posicionY) {
        floatingActionButton.setX(posicionX);
        floatingActionButton.setY(posicionY);
    }

    //endregion ButtonFloat

    //region EvaluacionAdapter

    @Override
    public void showEvaluacion(final List<AlumnosEvaluacionUi> evaluados, List<NotaUi> notaUis, RubroEvaluacionUi rubroEvaluacionUi, String indicador, String titulo, boolean disabledEval) {
        txtTitle.setText(titulo);
        tablefixheaders.setVisibility(View.VISIBLE);

        List<List<NotaUi>> lists = new ArrayList<>();
        for (AlumnosEvaluacionUi alumnosEvaluacionUi : evaluados) {
            lists.add(alumnosEvaluacionUi.getNotaUis());
        }
        showTableview(new ArrayList<BodyCellViewUi>(evaluados), notaUis, lists, disabledEval, false);

        //adapter = new MyTableAdapter(getContext());
        //adapter.setListener(this, disabledEval);
        //BaseTableAdapter baseTableAdapter = adapter.getInstance("", evaluados, notaUis);
        //adapter.setTypeBody(rubroEvaluacionUi.getTipoNota());
        //txtDescripcion.setText(rubroEvaluacionUi.getTitulo());
        txtInidicador.setText(indicador);
        // tablefixheaders.setAdapter(baseTableAdapter);
        Drawable drawable = seleccionarTipoComptencia(rubroEvaluacionUi.getTipoCompetencia());
        if (drawable != null) imgCompetencia.setImageDrawable(drawable);


    }

    private Drawable seleccionarTipoComptencia(TipoCompetencia tipoCompetencia) {
        Drawable drawable = null;
        switch (tipoCompetencia) {
            case COMPETENCIA_BASE:
                drawable = AppCompatResources.getDrawable(getContext(), R.drawable.ic_base);
                break;
            case COMPETENCIA_ENFQ:
                drawable = AppCompatResources.getDrawable(getContext(), R.drawable.ic_enfoque_1);
                break;
            case COMPETENCIA_TRANS:
                drawable = AppCompatResources.getDrawable(getContext(), R.drawable.ic_transversal);
                break;
        }
        return drawable;
    }
    //endregion EvaluadoAdapter

    //region Rubro Evaluacion
    @Override
    public void addRubro(RubroEvaluacionUi rubro) {
        menuLateralAdapter.addRubro(rubro);
    }

    @Override
    public void updateRubro(final RubroEvaluacionUi rubro) {
        menuLateralAdapter.updateRubro(rubro);
    }

    @Override
    public void deleteRubro(RubroEvaluacionUi rubro) {
        menuLateralAdapter.deleteRubro(rubro);
    }

    @Override
    public void setRubro(final List<RubroEvaluacionUi> rubros) {
        menuLateralAdapter.setRubro(rubros);
    }
    //endregion Rubro Evaluacion

    @Override
    public void showDialogAgregarRubro(int sesionAprendizajeId, int indicadorId) {

    }

    @Override
    public void showDialogInidicador(List<IndicadorUi> indicadorUis) {
        FragmentManager manager = getFragmentManager();
        indicadorDialog = IndicadorDialog.newInstance(indicadorUis);
        indicadorDialog.setTargetFragment(RegistroFragment.this, 200);
        indicadorDialog.show(manager, "IndicadorDialog");
    }

    @Override
    public void hideDialogInidicador() {
        indicadorDialog.dismiss();
    }

    @Override
    public void changeNotaEvaluacion() {
        tableAdapter.notifyDataSetChanged();
        Log.d(TAG, "changeNotaEvaluacion: ");
    }

    @Override
    public void showContentVacio(int message) {
        txtVacio.setText(message);
        contentVacio.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContentVacio() {
        contentVacio.setVisibility(View.GONE);
    }

    @Override
    public void showDilogEvaluacionSelectorNotas(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUis) {
        FragmentManager manager = getFragmentManager();
        evaluacionSelectorNotas = EvaluacionGlobalFragment.newInstance(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUi, selectorNumericoUi, alumnosEvaluacionUis);
        evaluacionSelectorNotas.setTargetFragment(RegistroFragment.this, 200);
        evaluacionSelectorNotas.show(manager, "EvaluacionGlobalFragment");
    }

    @Override
    public void showDilogEvaluacionTecladoNumerico(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUis) {
        FragmentManager manager = getFragmentManager();
        evaluacionTecladoNumerico = EvaluacionTecladoNumericoFragment.newInstance(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUi, selectorNumericoUi, new ArrayList<Object>(alumnosEvaluacionUis));
        evaluacionTecladoNumerico.setTargetFragment(RegistroFragment.this, 200);
        evaluacionTecladoNumerico.show(manager, "EvaluacionTecladoNumericoFragment");
    }

    @Override
    public void changeTable() {
        tableAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShowDialogInfoRubro(String rubroEvaluacionId) {
        infoRubroFragment = InfoRubroFragment.newInstance(rubroEvaluacionId);
        infoRubroFragment.setTargetFragment(RegistroFragment.this, 200);
        infoRubroFragment.show(getFragmentManager(), InfoRubroFragment.class.getSimpleName());
    }

    @Override
    public void onShowInfoUsuario(AlumnosEvaluacionUi item) {
        Bundle bundle = null;
        if (getActivity() != null) bundle = getActivity().getIntent().getExtras();
        String titulo = item.getName() + " " + item.getLastName();
        infoUsuarioFragment = InfoUsuarioFragment.newInstance(null, item.getFotoPerfil(), titulo, item.getId(), "", new CRMBundle(bundle));
        infoUsuarioFragment.show(getFragmentManager(), InfoRubroFragment.class.getSimpleName());
    }

    @Override
    public void onShowPresionEvaluacion(double nota, String rubroEvaluacionId, String valorTipoNotaId, String color, boolean validar) {

        if (validar) {
            PresicionDialogFragment presicionDialogFragment = PresicionDialogFragment.newInstance(nota, rubroEvaluacionId, valorTipoNotaId, color);
            presicionDialogFragment.setTargetFragment(RegistroFragment.this, 200);
            presicionDialogFragment.show(getFragmentManager(), InfoRubroFragment.class.getSimpleName());
        } else {
            Toast.makeText(getContext(), "Presicion Unica",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showListarRubros(List<NotaUi> notaUiList, RubroEvaluacionUi.TipoNota tipo) {
        footerNotasAdapter.setTipo(tipo);
        footerNotasAdapter.set(notaUiList);
    }

    @Override
    public void showFooter() {
        rcFooterNota.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFooter() {
        rcFooterNota.setVisibility(View.GONE);
    }

    @Override
    public void changeTableAvanzado() {
        tableAdapter.changeTableAvanzado();
    }

    @Override
    public void changeTableSimple() {
        tableAdapter.changeTableSimple();
    }

    @Override
    public void cleanAllList() {
        presenter.setAlumnosList();
    }

    @Override
    public void updateAlumnoEvaluacion(List<AlumnosEvaluacionUi> alumnosEvaluacionUis) {
        footerNotasAdapter.updateAlumnoEvaluacion(alumnosEvaluacionUis);
    }

    @Override
    public void showTutorial() {
        if (getActivity() instanceof EvaluacionContainerActivity) {
            Log.d(TAG, "initTutorial");
            EvaluacionContainerActivity evaluacionContainerActivity = (EvaluacionContainerActivity) getActivity();
            evaluacionContainerActivity.initTutorial();
        }
    }

    @Override
    public void showInfoComentario(String evaluacionId) {
        InfoComentarioDialog infoComentarioDialog = InfoComentarioDialog.newInstance(evaluacionId);
        infoComentarioDialog.setTargetFragment(RegistroFragment.this, 200);
        infoComentarioDialog.show(getParentFragmentManager(), "InfoRubroFragments");
    }

    @Override
    public void notiftyDataBaseChange() {
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void showBtnFooter() {
        btnFooter.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBtnFooter() {
        btnFooter.setVisibility(View.GONE);
    }

    @Override
    public void changeSwiteOn() {
        btnFooter.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_footer));
    }

    @Override
    public void changeSwiteOff() {
        btnFooter.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_document_footer));
    }

    @Override
    public void changeEyeSimple() {
        btnEye.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_eeye));
    }

    @Override
    public void changerEyeFocus() {
        btnEye.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_vision));
    }

    @Override
    public void showBtnClean(boolean isCalendarioVigente) {
        if (isCalendarioVigente)btnClear.setVisibility(View.VISIBLE);
            else btnClear.setVisibility(View.GONE);
    }


    @Override
    public void onInidicadorSelect(IndicadorUi indicadorUi) {
        onIndicadorSelect(indicadorUi);
    }

    //endregion view

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPauseFragment() {
        Log.d(TAG, "onPauseFragment()");
    }

    @Override
    public void onResumeFragment() {
        onChangeCrearRubro();
        Log.d(TAG, "onResumeFragment()");

    }

    @Override
    public void onResumeFragment(Bundle bundle) {

    }

    public void onChangeCrearRubro() {
        if (presenter != null) {
            presenter.onChangeRubro();
            Log.d(TAG, "presenter(not null)");
        }
    }

    @Override
    public void hideEvaluacion() {
        presenter.changeEvaluacion();
    }

    @OnClick(R.id.txtInidicador)
    public void onViewClicked() {
        presenter.onClickIndicador();
    }

    public void updateTableView(FiltroTableUi filtroTableUi) {
        presenter.updateTableView(filtroTableUi);
    }

    @OnClick(R.id.txtTitle)
    public void onClikCornerTableView() {
        if (getActivity() instanceof EvaluacionContainerActivity) {
            EvaluacionContainerActivity evaluacionContainerActivity = (EvaluacionContainerActivity) getActivity();
            evaluacionContainerActivity.onClikCornerTableView();
        }
        presenter.onClikCornerTableView();
    }

    @Override
    public void onSuccessNota(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId) {
        presenter.onSelectPrecicionEvaluacion(notaAnterior, notaActual, valorTipoNotaId, rubroEvaluacionId);
    }

    @Override
    public void cerrarDialogComentario(String evaluacionProcesoId) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
    }

    private void showTableview(final List<BodyCellViewUi> rows, final List<NotaUi> columnList, final List<List<NotaUi>> bodyList, final boolean disabledEval, final boolean diseniotablaAlterno) {
        Log.d(TAG, "showTableview " + rows.size() + "," + columnList.size() + "," + bodyList.size());
        if (tableAdapter == null) {
            tableAdapter = new TableAdapter(getContext());
        }

        table.setAdapter(tableAdapter);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(false);
        table.setIgnoreSelectionColors(true);

        if (!disabledEval) {
            table.setTableViewListener(this);
        }
        tableAdapter.setDiseniotablaAlterno(diseniotablaAlterno);
        tableAdapter.setAllItems(columnList, rows, bodyList);
        /*if (!initTutorial) {
            initTutorial = true;
            table.post(new Runnable() {
                @Override
                public void run() {
                    initTutorial(false);
                }
            });
        }*/

    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

        if (cellView instanceof SelectorNumericoCellViewHolder) {
            SelectorNumericoCellViewHolder selectorNumericoCellViewHolder = (SelectorNumericoCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) selectorNumericoCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            if (alumnosEvaluacionUi != null && alumnosEvaluacionUi.isVigente())
                presenter.onSelectAlumnoEvaluacionSelectorNota(alumnosEvaluacionUi, notaUi);
        } else if (cellView instanceof TecladoNumericoCellViewHolder) {
            TecladoNumericoCellViewHolder tecladoNumericoCellViewHolder = (TecladoNumericoCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) tecladoNumericoCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            if (alumnosEvaluacionUi != null && alumnosEvaluacionUi.isVigente())
                presenter.onSelectAlumnoEvaluacionTecladoNumerico(alumnosEvaluacionUi, notaUi);
        } else if (cellView instanceof PublicarCellViewHolder) {
            PublicarCellViewHolder publicarCellViewHolder = (PublicarCellViewHolder) cellView;
            OptionPublicar optionPublicar = (OptionPublicar) publicarCellViewHolder.getOptionPublicar();
            publicarCellViewHolder.clickView(optionPublicar);
            if (!optionPublicar.isVigente()) return;
            presenter.onClickBtnPublicar(null, optionPublicar);
        } else if (cellView instanceof ComentarioCellViewHolder) {
            ComentarioCellViewHolder comentarioCellViewHolder = (ComentarioCellViewHolder) cellView;
            OptionComentario optionComentario = (OptionComentario) comentarioCellViewHolder.getOptionComentario();
            if (!optionComentario.isVigente()) return;
            presenter.onClickBtnComentario(null, optionComentario);
        } else if (cellView instanceof EnptyCellViewHolder) {

        } else if (cellView instanceof TextoCellViewHolder) {
            TextoCellViewHolder textoCellViewHolder = (TextoCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) textoCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            if (alumnosEvaluacionUi != null && alumnosEvaluacionUi.isVigente()) {

                TextoCellViewHolder textoCellViewHolderHolderHold = (TextoCellViewHolder) alumnosEvaluacionUi.getAbstractViewHolder();
                if (textoCellViewHolderHolderHold != null) {
                    if (notaUi.equals(textoCellViewHolderHolderHold.getNotaUi())) {
                        presenter.onLongClickNota(alumnosEvaluacionUi, notaUi);
                        return;
                    }

                    textoCellViewHolder.clickViewHold(textoCellViewHolderHolderHold);
                }

                textoCellViewHolder.clickView(notaUi);

                presenter.onSelectAlumnoEvaluacion(alumnosEvaluacionUi, notaUi);
            }
        } else if (cellView instanceof TextoAvanzadoCellViewHolder) {
            TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolder = (TextoAvanzadoCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) textoAvanzadoCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            if (alumnosEvaluacionUi != null && alumnosEvaluacionUi.isVigente()) {

                TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolderHold = (TextoAvanzadoCellViewHolder) alumnosEvaluacionUi.getAbstractViewHolder();
                if (textoAvanzadoCellViewHolderHold != null) {
                    if (notaUi.equals(textoAvanzadoCellViewHolderHold.getNotaUi())) {
                        presenter.onLongClickNota(alumnosEvaluacionUi, notaUi);
                        return;
                    }

                    textoAvanzadoCellViewHolder.clickViewHold(textoAvanzadoCellViewHolderHold);
                }

                textoAvanzadoCellViewHolder.clickView(notaUi);

                presenter.onSelectAlumnoEvaluacion(alumnosEvaluacionUi, notaUi);
            }
        } else if (cellView instanceof ImageCellViewHolder) {
            ImageCellViewHolder imageCellViewHolder = (ImageCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) imageCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            if (alumnosEvaluacionUi != null && alumnosEvaluacionUi.isVigente()) {
                ImageCellViewHolder imageCellViewHolderHold = (ImageCellViewHolder) alumnosEvaluacionUi.getAbstractViewHolder();

                if (imageCellViewHolderHold != null) {
                    if (notaUi.equals(imageCellViewHolderHold.getNotaUi())) {
                        presenter.onLongClickNota(alumnosEvaluacionUi, notaUi);
                        return;
                    }

                    imageCellViewHolder.clickViewHold(imageCellViewHolderHold);
                }

                imageCellViewHolder.clickView(notaUi);


                presenter.onSelectAlumnoEvaluacion(alumnosEvaluacionUi, notaUi);
            }
        }

    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        if (cellView instanceof ImageCellViewHolder) {
            ImageCellViewHolder imageCellViewHolder = (ImageCellViewHolder) cellView;
            NotaUi notaUi = imageCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            if (alumnosEvaluacionUi != null && alumnosEvaluacionUi.isVigente()) {
                ImageCellViewHolder imageCellViewHolderHold = (ImageCellViewHolder) alumnosEvaluacionUi.getAbstractViewHolder();
                //if(!nota.equals(bodyCellViewGroup.getNotaUi()))return;
                if (imageCellViewHolderHold != null) {
                    imageCellViewHolder.clickViewHold(imageCellViewHolderHold);
                }

                imageCellViewHolder.clickView(notaUi);

                presenter.onSelectAlumnoEvaluacion(alumnosEvaluacionUi, notaUi);
            }


        } else if (cellView instanceof TextoCellViewHolder) {
            TextoCellViewHolder textoCellViewHolder = (TextoCellViewHolder) cellView;
            NotaUi notaUi = textoCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            if (alumnosEvaluacionUi != null && alumnosEvaluacionUi.isVigente()) {
                TextoCellViewHolder textoCellViewHolderHold = (TextoCellViewHolder) alumnosEvaluacionUi.getAbstractViewHolder();
                //if(!nota.equals(bodyCellViewGroup.getNotaUi()))return;
                if (textoCellViewHolderHold != null) {
                    textoCellViewHolder.clickViewHold(textoCellViewHolderHold);
                }
                textoCellViewHolder.clickView(notaUi);

                presenter.onSelectAlumnoEvaluacion(alumnosEvaluacionUi, notaUi);
            }


        } else if (cellView instanceof TextoAvanzadoCellViewHolder) {
            TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolder = (TextoAvanzadoCellViewHolder) cellView;
            NotaUi notaUi = textoAvanzadoCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            if (alumnosEvaluacionUi != null && alumnosEvaluacionUi.isVigente()) {
                TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolderHold = (TextoAvanzadoCellViewHolder) alumnosEvaluacionUi.getAbstractViewHolder();
                //if(!nota.equals(bodyCellViewGroup.getNotaUi()))return;
                if (textoAvanzadoCellViewHolderHold != null) {
                    textoAvanzadoCellViewHolder.clickViewHold(textoAvanzadoCellViewHolderHold);
                }
                textoAvanzadoCellViewHolder.clickView(notaUi);

                presenter.onSelectAlumnoEvaluacion(alumnosEvaluacionUi, notaUi);
            }


        }

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        if (rowHeaderView instanceof AlumnoRowViewHolder) {
            AlumnoRowViewHolder alumnoRowViewHolder = (AlumnoRowViewHolder) rowHeaderView;
            presenter.onClickAlumno(alumnoRowViewHolder.getAlumnosEvaluacionUi());
        }
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @OnClick({R.id.btn_eye, R.id.btn_footer, R.id.btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_eye:
                presenter.onClickEye();
                break;
            case R.id.btn_footer:
                presenter.onClickFooter();
                break;
            case R.id.btn_clear:
                confimationOutDialog();
                break;
        }
    }

    private void confimationOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        TextView text = new TextView(getActivity());
        text.setPadding(30, 30, 30, 0);
        text.setText("¿Seguro que desea limpiar todas las notas?");
        text.setGravity(Gravity.CENTER);
        builder.setView(text)
                .setTitle("Mensaje de Confirmación")
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
                        dialogInterface.dismiss();
                        presenter.onClickClear();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
