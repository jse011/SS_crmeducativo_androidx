package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.ListaIndicadoresPresenter;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.ListaIndicadoresPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.ListaIndicadoresView;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.adapter.IndicadoreAdapter;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.ListaIndicadoresRepository;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.local.ListaIndicadoresLocalSource;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.remote.ListaIndicadoresRemoteSource;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase.GetCompetencia;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase.GetIndicadorSesionList;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.domain.usecase.GetIndicadorSilavoList;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CampotematicoListener;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CrearRubroListaIndicadoresListener;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ListaIndicadoresFragment extends DialogFragment implements ListaIndicadoresView, CampotematicoListener {


    @BindView(R.id.rc_indicadores)
    RecyclerView rcIndicadores;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.img_close)
    Button imgClose;
    @BindView(R.id.cons_indicadores)
    ConstraintLayout consIndicadores;
    private static String TAG = ListaIndicadoresFragment.class.getSimpleName();
    public static final String INT_SESION_APRENDIZAJE_ID = "SesionAprenizajeId";
    public static final String INT_SILAVO_EVENTO_ID = "SilavoEvento";
    public static final String INT_NIVEL = "NivelEvalaucion";
    public static final String INT_COMPETNCIA_ID = "competenciaId";
    public static final String INT_CALENDARIO_PERIDOD_ID = "calendarioPeriodoId";
    public static final String INT_INDICADOR_ID = "IndicadorId";
    public static final String CAMPOS_TEMATICOS_IDS = "CamposTematicosIds";
    @BindView(R.id.btn_aceptar)
    Button btnAceptar;

    private ListaIndicadoresPresenter presenter;
    private IndicadoreAdapter indicadoreAdapter;
    private Unbinder unbinder;
    private CrearRubroListaIndicadoresListener listener;
    private int indicadorID;
    private ArrayList<Integer> camposTematicosIds;


    public enum Id {SESION_APRENDIZAJE_ID, SILAVO_EVENTO_ID}


    public static ListaIndicadoresFragment newInstance(Id tipo, int Id, int nivel, int competenciaId, int calendarioPeridoId) {

        ListaIndicadoresFragment fragment = new ListaIndicadoresFragment();
        Bundle bundle = new Bundle();
        switch (tipo) {
            case SESION_APRENDIZAJE_ID:
                bundle.putInt(INT_SESION_APRENDIZAJE_ID, Id);
                break;
            case SILAVO_EVENTO_ID:
                bundle.putInt(INT_SILAVO_EVENTO_ID, Id);
                bundle.putInt(INT_CALENDARIO_PERIDOD_ID, calendarioPeridoId);
                break;
        }
        bundle.putInt(INT_NIVEL, nivel);
        bundle.putInt(INT_COMPETNCIA_ID, competenciaId);

        fragment.setArguments(bundle);
        return fragment;
    }

    public static ListaIndicadoresFragment newInstanceCamposTematicos(Id tipo, int id, int nivel, int competenciaId, int calendarioPeridoId, int indicadorId, ArrayList<Integer> CamposIds) {
        ListaIndicadoresFragment fragment = newInstance(tipo, id, nivel, competenciaId, calendarioPeridoId);
        Bundle bundle = fragment.getArguments();
        bundle.putInt(INT_INDICADOR_ID, indicadorId);
        bundle.putIntegerArrayList(CAMPOS_TEMATICOS_IDS, CamposIds);
        fragment.setArguments(bundle);
        return fragment;
    }

    //region ciclo de vida

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();

        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        }
        View view = inflater.inflate(R.layout.fragment_crear_rubro_list_inidcadores, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);

        setupListener();
        setupPresenter();
        setupAdapter();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    private void setupListener() {
        listener = (CrearRubroListaIndicadoresListener) getTargetFragment();
        if (listener != null) return;
        if (getContext() instanceof CrearRubroListaIndicadoresListener) {
            listener = (CrearRubroListaIndicadoresListener) getContext();
        } else {
            throw new ClassCastException(getContext().toString()
                    + " must implement CrearRubroListaIndicadoresListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.85), (int) (size.y * 0.85));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing

        super.onResume();

        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //endregion ciclo de vida

    private void setupPresenter() {
        presenter = new ListaIndicadoresPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GetIndicadorSesionList(ListaIndicadoresRepository.getInstance(
                        new ListaIndicadoresLocalSource(),
                        new ListaIndicadoresRemoteSource()
                )),
                new GetIndicadorSilavoList(ListaIndicadoresRepository.getInstance(
                        new ListaIndicadoresLocalSource(),
                        new ListaIndicadoresRemoteSource()
                )),
                new GetCompetencia(ListaIndicadoresRepository.getInstance(
                        new ListaIndicadoresLocalSource(),
                        new ListaIndicadoresRemoteSource()
                ))
        );
        presenter.setExtras(getArguments());
        setPresenter(presenter);
    }

    private void setupAdapter() {

        indicadoreAdapter = new IndicadoreAdapter(new ArrayList<CompetenciaUi>(), this);
        indicadoreAdapter.setRecyclerView(rcIndicadores);
        rcIndicadores.setAdapter(indicadoreAdapter);
        StickyHeaderLayoutManager layoutManager = new StickyHeaderLayoutManager();
        rcIndicadores.setLayoutManager(layoutManager);
        rcIndicadores.setNestedScrollingEnabled(false);

    }


    @Override
    public void setPresenter(ListaIndicadoresPresenter presenter) {
        presenter.attachView(this);
        presenter.onStart();
    }

    @Override
    public void onCampotematicoSelect(CompetenciaUi competenciaUi, IndicadorUi indicadorUi, CampotematicoUi campotematicoUi) {
        Log.d(TAG, "onCampotematicoSelect 1 " + competenciaUi.getId());
        presenter.onSelectCampotematico(competenciaUi, indicadorUi, campotematicoUi);
    }

    @Override
    public void onClickIndicador(CompetenciaUi competencia, IndicadorUi indicadorUi) {
        presenter.onClickIndicador(competencia,indicadorUi);
    }

    //region view
    @Override
    public void hideDialog() {
        dismiss();
    }

    @Override
    public void showListInidicadores(List<CompetenciaUi> competenciaUiList) {

        rcIndicadores.setVisibility(View.VISIBLE);
        indicadoreAdapter.setIndicador(competenciaUiList);

    }


    @Override
    public void hideListInidicadores() {
        rcIndicadores.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void retornarIndicadorCampotematico(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds) {
        Log.d(TAG, "retornarIndicadorCampotematico " + competenciaId);
        listener.onSelectIndicadorCampotematicovoid(competenciaId, indicadorId, campotematicoIds);
    }

    @Override
    public void showMensaje(int mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCompetencia(final CompetenciaUi competenciaUi, final CapacidadUi capacidadUi) {

    }

    @Override
    public void actualizarComptenciaLista() {
        indicadoreAdapter.notifyAllChildChanged(rcIndicadores);
    }

    //endregion view

    //region presenter

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroyView();
    }

    @OnClick({R.id.img_close, R.id.btn_aceptar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                dismiss();
                listener.onSalirListaIndicadores();
                break;
            case R.id.btn_aceptar:

                presenter.onSeleccionarIndicador();
                break;
        }
    }



    //endregion presenter
}
