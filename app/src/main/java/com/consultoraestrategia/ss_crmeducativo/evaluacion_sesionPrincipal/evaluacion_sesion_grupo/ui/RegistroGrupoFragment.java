package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.ui;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.transition.Slide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.adapter.table.adapter.row.GrupoRowViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EquipoEvaluacionProceso.LocalEquipoEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EquipoEvaluacionProceso.RemoteEquipoEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso.LocalEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.EvaluacionProceso.RemoteEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.rubroEvaluacionProceso.LocalRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.exportar.rubroEvaluacionProceso.RemoteRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.local.RubroLocalDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.remote.RubroRemoteDataSourse;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetAlumnoGrupoList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetIndicadorList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetNotaEvaluacionList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.GetRubroList;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.PublicarEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase.SaveGrupoEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.BodyCellViewUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.SelectorNumericoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.TipoCompetencia;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.ui.EvaluacionGlobalFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.RegistroPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.RegistroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.RegistroView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.adapters.MenuLateralAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.adapters.TablaFixes.MyTableAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.dialogs.IndicadorDialog;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.listener.EvaluacionListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.listener.IndicadorListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.listener.RubroEvaluacionListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.listener.EvaluacionDialogListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.ui.EvaluacionTecladoNumericoFragment;
import com.consultoraestrategia.ss_crmeducativo.grouplist.ui.ListaGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.listener.InfoRubroCallback;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.infoUsuario.InfoUsuarioFragment;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.listener.PresicionListener;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.PresicionDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.listener.FragmentLifecycle;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.recyclerview.RowHeaderRecyclerViewAdapter;
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

public class RegistroGrupoFragment extends Fragment implements RegistroView, EvaluacionListener, RubroEvaluacionListener, IndicadorListener, MyTableAdapter.NotasListener, FragmentLifecycle, EvaluacionDialogListener, InfoRubroCallback, PresicionListener, InfoRubroListener, ITableViewListener {

    private static final String TAG = RegistroGrupoFragment.class.getSimpleName();

    @BindView(R.id.rec_Menu)
    RecyclerView rec_Menu;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.root)
    CardView root;
    @BindView(R.id.table)
    TableView table;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.textDescripcion)
    TextView txtDescripcion;
    @BindView(R.id.txtInidicador)
    public TextView txtInidicador;
    @BindView(R.id.contentVacio)
    LinearLayout contentVacio;
    @BindView(R.id.txtVacio)
    TextView txtVacio;
    @BindView(R.id.img_competencia)
    ImageView imgCompetencia;
    @BindView(R.id.rc_footer_nota)
    RecyclerView rcFooterNota;
    @BindView(R.id.frameLayoutGrupos)
    FrameLayout frameLayoutGrupos;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.tablefixheaders)
    LinearLayout tablefixheaders;
    @BindView(R.id.btn_eye)
    ImageView btnEye;
    @BindView(R.id.btn_footer)
    ImageView btnFooter;
    @BindView(R.id.btn_clear)
    ImageView btnClear;
    private Unbinder unbinder;
    private MenuLateralAdapter menuLateralAdapter;
    private RegistroPresenter presenter;
    private IndicadorDialog indicadorDialog;
    public static final int NOTAIMAGEN = 0, NOTATEXTO = 1;
    private EvaluacionTecladoNumericoFragment evaluacionTecladoNumerico;
    private InfoRubroFragment infoRubroFragment;
    private InfoUsuarioFragment infoAlumnoFragment;
    private FooterNotasAdapter footerNotasAdapter;
    private boolean initTutorial;
    private TableAdapter tableAdapter;
    private EvaluacionGlobalFragment evaluacionSelectorNotas;


    public static RegistroGrupoFragment newInstance() {
        RegistroGrupoFragment fragment = new RegistroGrupoFragment();
        return fragment;
    }

    //region ciclo de vida del fragmento
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
        setupPresenter();
        setupFloatButton();
        setupHeaderTable();
        setupFooterNota();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
                new GetAlumnoGrupoList(rubroRepository),
                new GetIndicadorList(rubroRepository),
                new SaveGrupoEvaluacion(rubroRepository),
                new GetNotaEvaluacionList(rubroRepository),
                new GetRubro(rubroRepository),
                new PublicarEvaluacion(rubroRepository)
        );
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

    private void setupHeaderTable() {
        txtTitle.setText("Grupos ");
    }


    //region  presenter
    @Override
    public void onEvaluacionSelect(NotaUi evaluacion) {
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
    public void onClickNota(Object itemEvaluacionUi, NotaUi notaUi) {
        Log.d(TAG, "onClickNota");
        //presenter.onSelectAlumnoEvaluacion(itemEvaluacionUi, notaUi);
    }

    @Override
    public void onClickAlumnoGrupo(Object itemEvaluacionUi) {
        Log.d(TAG, "onClickAlumnoGrupo");
        presenter.onSelectAlumnoGrupo(itemEvaluacionUi);
    }

    @Override
    public void onClickTecladoNumerico(Object item, NotaUi nota) {
        //presenter.onSelectAlumnoEvaluacionTecladoNumerico(item, nota);
    }

    @Override
    public void onLongClickNota(Object item, NotaUi nota) {
        //presenter.onLongClickNota(item, nota);
    }

    @Override
    public void onClickBtnPublicar(Object item, OptionPublicar optionPublicar) {
        //presenter.onClickBtnPublicar(item, optionPublicar);
    }

    @Override
    public void onClickBtnComentario(Object item, OptionComentario optionComentario) {
        //presenter.onClickBtnComentario(item, optionComentario);
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
    public void showEvaluacion(final List<Object> evaluados, List<NotaUi> notaUis, RubroEvaluacionUi rubroEvaluacionUi, String indicador, boolean disabledEval) {
        Log.d(TAG, "showEvaluacion");
        if (evaluados.isEmpty() || notaUis.isEmpty()) {
            tablefixheaders.setVisibility(View.GONE);
        } else {
            tablefixheaders.setVisibility(View.VISIBLE);
        }

        txtInidicador.setText(indicador);
        Drawable drawable = seleccionarTipoComptencia(rubroEvaluacionUi.getTipoCompetencia());
        if (drawable != null) imgCompetencia.setImageDrawable(drawable);

        List<List<NotaUi>> lists = new ArrayList<>();
        List<BodyCellViewUi> bodyCellViewUiList = new ArrayList<>();
        for (Object o : evaluados) {
            if (o instanceof AlumnosEvaluacionUi) {
                AlumnosEvaluacionUi alumnosEvaluacionUi = (AlumnosEvaluacionUi) o;
                lists.add(alumnosEvaluacionUi.getNotaUis());
                bodyCellViewUiList.add(alumnosEvaluacionUi);
            } else if (o instanceof GrupoEvaluacionUi) {
                GrupoEvaluacionUi grupoEvaluacionUi = (GrupoEvaluacionUi) o;
                lists.add(grupoEvaluacionUi.getNotaUis());
                bodyCellViewUiList.add(grupoEvaluacionUi);
            }

        }

        showTableview(bodyCellViewUiList, notaUis, lists, disabledEval, false);
        /*if(!initTutorial){
            initTutorial = true;
            tablefixheaders.post(new Runnable() {
                @Override
                public void run() {
                    if(getActivity() instanceof EvaluacionContainerActivity){
                        EvaluacionContainerActivity evaluacionContainerActivity = (EvaluacionContainerActivity)getActivity();
                        evaluacionContainerActivity.initTutorial();
                    }
                }
            });
        }*/

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
        indicadorDialog.setTargetFragment(RegistroGrupoFragment.this, 200);
        indicadorDialog.show(manager, "IndicadorDialog");
    }

    @Override
    public void showDilogEvaluacionTecladoNumerico(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUis) {
        FragmentManager manager = getFragmentManager();
        evaluacionTecladoNumerico = EvaluacionTecladoNumericoFragment.newInstance(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUi, selectorNumericoUi, new ArrayList<Object>(alumnosEvaluacionUis));
        evaluacionTecladoNumerico.setTargetFragment(RegistroGrupoFragment.this, 200);
        evaluacionTecladoNumerico.show(manager, "EvaluacionTecladoNumericoFragment");
    }

    @Override
    public void showDilogEvaluacionTecladoNumerico(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GrupoEvaluacionUi grupoEvaluacionUi, SelectorNumericoUi selectorNumericoUi, List<GrupoEvaluacionUi> gruposEvaluacion) {
        FragmentManager manager = getFragmentManager();
        evaluacionTecladoNumerico = EvaluacionTecladoNumericoFragment.newInstance(sesionAprendizajeId, rubroEvaluacionUi, grupoEvaluacionUi, selectorNumericoUi, new ArrayList<Object>(gruposEvaluacion));
        evaluacionTecladoNumerico.setTargetFragment(RegistroGrupoFragment.this, 200);
        evaluacionTecladoNumerico.show(manager, "EvaluacionTecladoNumericoFragment");
    }

    @Override
    public void changeTable() {
        tableAdapter.notifyDataSetChanged();
    }

    @Override
    public void onShowInfoRubro(String rubroEvaluacionProcesoId) {
        infoRubroFragment = InfoRubroFragment.newInstance(rubroEvaluacionProcesoId);
        infoRubroFragment.setTargetFragment(RegistroGrupoFragment.this, 200);
        infoRubroFragment.show(getFragmentManager(), InfoRubroFragment.class.getSimpleName());
    }

    @Override
    public void showDilogInfoUsuario(AlumnosEvaluacionUi alumnosEvaluacionUi) {
        Bundle bundle = null;
        if (getActivity() != null) bundle = getActivity().getIntent().getExtras();
        String titulo = alumnosEvaluacionUi.getName() + " " + alumnosEvaluacionUi.getLastName();
        infoAlumnoFragment = InfoUsuarioFragment.newInstance(null, alumnosEvaluacionUi.getFotoPerfil(), titulo, alumnosEvaluacionUi.getId(), "", new CRMBundle(bundle));
        infoAlumnoFragment.show(getFragmentManager(), InfoRubroFragment.class.getSimpleName());
    }

    @Override
    public void onShowPresionEvaluacion(double nota, String rubroEvaluacionId, String valorTipoNotaId, String color, boolean validar) {

        if (validar) {
            PresicionDialogFragment presicionDialogFragment = PresicionDialogFragment.newInstance(nota, rubroEvaluacionId, valorTipoNotaId, color);
            presicionDialogFragment.setTargetFragment(RegistroGrupoFragment.this, 200);
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
    public void showTeamList(int cargaCursoId, int cursoId, int rubroEvaluacionId) {
        Log.d(TAG, "showTeamList");

    }

    @Override
    public void showTeamList(int cargaCursoId, int cursoId, String rubroEvaluacionId, int cargaAcademicaId) {
        Log.d(TAG, "changeNotaEvaluacion: ");

        Bundle bundle = new Bundle();
        bundle.putInt("cargaCursoId", cargaCursoId);
        bundle.putInt("cursoId", cursoId);
        bundle.putString("rubroEvaluacionId", rubroEvaluacionId);
        bundle.putInt("idCargaAcademica", cargaAcademicaId);

        getSupportFragmentManager(ListaGrupoFragment.class, bundle);
       /*   FragmentManager manager = getFragmentManager();
      groupListFragment = GroupListFragmentDialog.newInstance(cargaCursoId, cursoId, rubroEvaluacionId);
        groupListFragment.setTargetFragment(RegistroGrupoFragment.this, 200);
        groupListFragment.show(manager, "GroupListFragment");*/
    }

    @Override
    public void showContentVacio(int message) {
       /* txtVacio.setText(message);
        contentVacio.setVisibility(View.VISIBLE);
        tablefixheaders.setVisibility(View.GONE);*/
    }

    @Override
    public void hideContentVacio() {
       /* contentVacio.setVisibility(View.GONE);
        tablefixheaders.setVisibility(View.VISIBLE);*/
    }


    @Override
    public void onInidicadorSelect(IndicadorUi indicadorUi) {
        onIndicadorSelect(indicadorUi);
    }

    //endregion view

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroyView();
    }


    public void onSaveGroupList() {
        Log.d(TAG, "onSaveGroupList()");
        presenter.onChangeAlumnoList();
        hideFrameGroupsList();
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

    @OnClick({R.id.txtTitle, R.id.txtInidicador})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtTitle:
                break;
            case R.id.txtInidicador:
                presenter.onClickIndicador();
                break;
        }
    }

    @Override
    public void onSuccessNota(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId) {
        presenter.onSelectPrecicionEvaluacion(notaAnterior, notaActual, valorTipoNotaId, rubroEvaluacionId);
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
    public void showFrameGroupsList() {
        Log.d(TAG, "showFrameGroupsList");

        constraintLayout.setVisibility(View.GONE);
        frameLayoutGrupos.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFrameGroupsList() {
        Log.d(TAG, "hideFrameGroupsList");
        constraintLayout.setVisibility(View.VISIBLE);
        frameLayoutGrupos.setVisibility(View.GONE);
    }

    @Override
    public void clearAllList() {
        presenter.setCleanList();
    }

    @Override
    public void showInfoComentario(String evaluacionId) {
        InfoComentarioDialog infoComentarioDialog = InfoComentarioDialog.newInstance(evaluacionId);
        infoComentarioDialog.setTargetFragment(RegistroGrupoFragment.this, 200);
        infoComentarioDialog.show(getFragmentManager(), "InfoRubroFragments");
    }

    @Override
    public void notifyChangeDataBase() {
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void showDilogEvaluacionSelectorNotas(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, GrupoEvaluacionUi grupoEvaluacionUiSelected, SelectorNumericoUi selectorNumericoUi, List<GrupoEvaluacionUi> gruposEvaluacion) {

    }

    @Override
    public void showDilogEvaluacionSelectorNotas(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, AlumnosEvaluacionUi alumnosEvaluacionUiSelected, SelectorNumericoUi selectorNumericoUi, List<AlumnosEvaluacionUi> alumnosEvaluacionUis) {
        FragmentManager manager = getFragmentManager();
        evaluacionSelectorNotas = EvaluacionGlobalFragment.newInstance(sesionAprendizajeId, rubroEvaluacionUi, alumnosEvaluacionUiSelected, selectorNumericoUi, alumnosEvaluacionUis);
        evaluacionSelectorNotas.setTargetFragment(RegistroGrupoFragment.this, 200);
        evaluacionSelectorNotas.show(manager, "EvaluacionGlobalFragment");
    }

    @Override
    public void removeRowRange(GrupoEvaluacionUi grupoEvaluacionUi, List<AlumnosEvaluacionUi> columnHeaders, List<List<NotaUi>> cellList) {
        RowHeaderRecyclerViewAdapter rowHeaderRecyclerViewAdapter = (RowHeaderRecyclerViewAdapter) table.getRowHeaderRecyclerView().getAdapter();
        List<BodyCellViewUi> columnHeaderAdapterList = (List<BodyCellViewUi>) rowHeaderRecyclerViewAdapter.getItems();
        for (BodyCellViewUi columnHeader : columnHeaders) {
            int position = columnHeaderAdapterList.indexOf(columnHeader);
            if (position != -1) table.getAdapter().removeRow(position);
        }
    }

    @Override
    public void addRowRange(GrupoEvaluacionUi grupoEvaluacionUi, List<AlumnosEvaluacionUi> columnHeaders, List<List<NotaUi>> cellList) {
        RowHeaderRecyclerViewAdapter rowHeaderRecyclerViewAdapter = (RowHeaderRecyclerViewAdapter) table.getRowHeaderRecyclerView().getAdapter();
        List<BodyCellViewUi> columnHeaderAdapterList = (List<BodyCellViewUi>) rowHeaderRecyclerViewAdapter.getItems();
        int columPositionStart = columnHeaderAdapterList.indexOf(grupoEvaluacionUi) + 1;

        int count = 0;
        for (BodyCellViewUi columnHeader : columnHeaders) {
            table.getAdapter().addRow(columPositionStart, columnHeader, cellList.get(count));
            columPositionStart++;
            count++;
        }
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
        if (isCalendarioVigente) btnClear.setVisibility(View.VISIBLE);
        else btnClear.setVisibility(View.GONE);
    }


    private void setupFooterNota() {
        rcFooterNota.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        footerNotasAdapter = new FooterNotasAdapter(new ArrayList<NotaUi>(), RubroEvaluacionUi.TipoNota.TEXTO);
        rcFooterNota.setAdapter(footerNotasAdapter);

    }

    @Override
    public void showFooter() {
        rcFooterNota.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFooter() {
        rcFooterNota.setVisibility(View.GONE);
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

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayoutGrupos, fragment).commit();
            fragmentTransaction.addToBackStack(null);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void cerrarDialogComentario(String evaluacionProcesoId) {

    }

    public void updateTableView(FiltroTableUi filtroTableUi) {

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
            presenter.onSelectAlumnoEvaluacionSelectorNota(notaUi);
        } else if (cellView instanceof TecladoNumericoCellViewHolder) {
            TecladoNumericoCellViewHolder tecladoNumericoCellViewHolder = (TecladoNumericoCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) tecladoNumericoCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();
            presenter.onSelectAlumnoEvaluacionTecladoNumerico(notaUi);
        } else if (cellView instanceof PublicarCellViewHolder) {
            PublicarCellViewHolder publicarCellViewHolder = (PublicarCellViewHolder) cellView;
            OptionPublicar optionPublicar = (OptionPublicar) publicarCellViewHolder.getOptionPublicar();
            publicarCellViewHolder.clickView(optionPublicar);
            if (!optionPublicar.isVigente()) return;
            presenter.onClickBtnPublicar(optionPublicar);
        } else if (cellView instanceof ComentarioCellViewHolder) {
            ComentarioCellViewHolder comentarioCellViewHolder = (ComentarioCellViewHolder) cellView;
            OptionComentario optionComentario = (OptionComentario) comentarioCellViewHolder.getOptionComentario();
            if (!optionComentario.isVigente()) return;
            presenter.onClickBtnComentario(optionComentario);
        } else if (cellView instanceof EnptyCellViewHolder) {

        } else if (cellView instanceof TextoCellViewHolder) {
            TextoCellViewHolder textoCellViewHolder = (TextoCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) textoCellViewHolder.getNotaUi();

            TextoCellViewHolder textoCellViewHolderHolderHold = null;
            AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
            GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();
            if (alumnosEvaluacionUiSelected != null)
                textoCellViewHolderHolderHold = (TextoCellViewHolder) alumnosEvaluacionUiSelected.getAbstractViewHolder();
            if (grupoEvaluacionUiSelected != null)
                textoCellViewHolderHolderHold = (TextoCellViewHolder) grupoEvaluacionUiSelected.getAbstractViewHolder();

            if (textoCellViewHolderHolderHold != null) {
                if (notaUi.equals(textoCellViewHolderHolderHold.getNotaUi())) {
                    presenter.onLongClickNota(notaUi);
                    return;
                }

                textoCellViewHolder.clickViewHold(textoCellViewHolderHolderHold);
            }

            textoCellViewHolder.clickView(notaUi);
            presenter.onSelectAlumnoEvaluacion(notaUi);

        } else if (cellView instanceof TextoAvanzadoCellViewHolder) {
            TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolder = (TextoAvanzadoCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) textoAvanzadoCellViewHolder.getNotaUi();

            TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolderHold = null;
            AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
            GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();
            if (alumnosEvaluacionUiSelected != null)
                textoAvanzadoCellViewHolderHold = (TextoAvanzadoCellViewHolder) alumnosEvaluacionUiSelected.getAbstractViewHolder();
            if (grupoEvaluacionUiSelected != null)
                textoAvanzadoCellViewHolderHold = (TextoAvanzadoCellViewHolder) grupoEvaluacionUiSelected.getAbstractViewHolder();

            if (textoAvanzadoCellViewHolderHold != null) {
                if (notaUi.equals(textoAvanzadoCellViewHolderHold.getNotaUi())) {
                    presenter.onLongClickNota(notaUi);
                    return;
                }

                textoAvanzadoCellViewHolder.clickViewHold(textoAvanzadoCellViewHolderHold);
            }

            textoAvanzadoCellViewHolder.clickView(notaUi);
            presenter.onSelectAlumnoEvaluacion(notaUi);
        } else if (cellView instanceof ImageCellViewHolder) {
            ImageCellViewHolder imageCellViewHolder = (ImageCellViewHolder) cellView;
            NotaUi notaUi = (NotaUi) imageCellViewHolder.getNotaUi();

            ImageCellViewHolder imageCellViewHolderHold = null;
            AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
            GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();
            if (alumnosEvaluacionUiSelected != null)
                imageCellViewHolderHold = (ImageCellViewHolder) alumnosEvaluacionUiSelected.getAbstractViewHolder();
            if (grupoEvaluacionUiSelected != null)
                imageCellViewHolderHold = (ImageCellViewHolder) grupoEvaluacionUiSelected.getAbstractViewHolder();

            if (imageCellViewHolderHold != null) {
                if (notaUi.equals(imageCellViewHolderHold.getNotaUi())) {
                    presenter.onLongClickNota(notaUi);
                    return;
                }

                imageCellViewHolder.clickViewHold(imageCellViewHolderHold);
            }

            imageCellViewHolder.clickView(notaUi);
            presenter.onSelectAlumnoEvaluacion(notaUi);
        }

    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        if (cellView instanceof ImageCellViewHolder) {
            ImageCellViewHolder imageCellViewHolder = (ImageCellViewHolder) cellView;
            NotaUi notaUi = imageCellViewHolder.getNotaUi();
            AlumnosEvaluacionUi alumnosEvaluacionUi = notaUi.getAlumnoEvaluacion();

            ImageCellViewHolder imageCellViewHolderHold = null;
            AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
            GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();
            if (alumnosEvaluacionUiSelected != null)
                imageCellViewHolderHold = (ImageCellViewHolder) alumnosEvaluacionUiSelected.getAbstractViewHolder();
            if (grupoEvaluacionUiSelected != null)
                imageCellViewHolderHold = (ImageCellViewHolder) grupoEvaluacionUiSelected.getAbstractViewHolder();

            //if(!nota.equals(bodyCellViewGroup.getNotaUi()))return;
            if (imageCellViewHolderHold != null) {
                imageCellViewHolder.clickViewHold(imageCellViewHolderHold);
            }

            imageCellViewHolder.clickView(notaUi);
            presenter.onSelectAlumnoEvaluacion(notaUi);

        } else if (cellView instanceof TextoCellViewHolder) {
            TextoCellViewHolder textoCellViewHolder = (TextoCellViewHolder) cellView;
            NotaUi notaUi = textoCellViewHolder.getNotaUi();

            TextoCellViewHolder textoCellViewHolderHold = null;
            AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
            GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();
            if (alumnosEvaluacionUiSelected != null)
                textoCellViewHolderHold = (TextoCellViewHolder) alumnosEvaluacionUiSelected.getAbstractViewHolder();
            if (grupoEvaluacionUiSelected != null)
                textoCellViewHolderHold = (TextoCellViewHolder) grupoEvaluacionUiSelected.getAbstractViewHolder();

            //if(!nota.equals(bodyCellViewGroup.getNotaUi()))return;
            if (textoCellViewHolderHold != null) {
                textoCellViewHolder.clickViewHold(textoCellViewHolderHold);
            }
            textoCellViewHolder.clickView(notaUi);

            presenter.onSelectAlumnoEvaluacion(notaUi);


        } else if (cellView instanceof TextoAvanzadoCellViewHolder) {
            TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolder = (TextoAvanzadoCellViewHolder) cellView;
            NotaUi notaUi = textoAvanzadoCellViewHolder.getNotaUi();

            TextoAvanzadoCellViewHolder textoAvanzadoCellViewHolderHold = null;
            AlumnosEvaluacionUi alumnosEvaluacionUiSelected = notaUi.getAlumnoEvaluacion();
            GrupoEvaluacionUi grupoEvaluacionUiSelected = notaUi.getGrupoEvaluacion();
            if (alumnosEvaluacionUiSelected != null)
                textoAvanzadoCellViewHolderHold = (TextoAvanzadoCellViewHolder) alumnosEvaluacionUiSelected.getAbstractViewHolder();
            if (grupoEvaluacionUiSelected != null)
                textoAvanzadoCellViewHolderHold = (TextoAvanzadoCellViewHolder) grupoEvaluacionUiSelected.getAbstractViewHolder();

            //if(!nota.equals(bodyCellViewGroup.getNotaUi()))return;
            if (textoAvanzadoCellViewHolderHold != null) {
                textoAvanzadoCellViewHolder.clickViewHold(textoAvanzadoCellViewHolderHold);
            }
            textoAvanzadoCellViewHolder.clickView(notaUi);

            presenter.onSelectAlumnoEvaluacion(notaUi);


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
            presenter.onSelectAlumnoGrupo(alumnoRowViewHolder.getAlumnosEvaluacionUi());
        } else if (rowHeaderView instanceof GrupoRowViewHolder) {
            GrupoRowViewHolder grupoRowViewHolder = (GrupoRowViewHolder) rowHeaderView;
            presenter.onSelectAlumnoGrupo(grupoRowViewHolder.getGrupoEvaluacionUi());
        }
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @OnClick({R.id.btn_eye, R.id.btn_footer, R.id.btn_clear})
    public void onViewClickedCabecera(View view) {
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
                        presenter.setCleanList();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
