package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.fragments;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.CentProcesoPresenter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.RegistroCentProcesamientoView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter.CabeceraTableRegEvalApadter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter.PeriodoAdapter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.adapter.TableRegEvalAdapter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.MatrizResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.PeriodoUi;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.adapter.recyclerview.CellRecyclerView;
import com.evrencoskun.tableview.listener.scroll.HorizontalRecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegistroCentProcesamiento extends Fragment implements RegistroCentProcesamientoView, PeriodoAdapter.Callback {

    @BindView(R.id.tb_evaluacion)
    TableView tbEvaluacion;
    @BindView(R.id.rc_cabecera)
    CellRecyclerView rcCabecera;
    @BindView(R.id.rc_cabecera1)
    CellRecyclerView rcCabecera1;
    @BindView(R.id.recyclerPeriodo)
    RecyclerView recyclerPeriodo;
    @BindView(R.id.corner1)
    CardView corner1;
    @BindView(R.id.corner2)
    View corner2;
    @BindView(R.id.corner3)
    View corner3;
    @BindView(R.id.corner)
    View corner;
    @BindView(R.id.progressBar9)
    ProgressBar progressBar9;
    @BindView(R.id.btn_generador)
    ImageView btnGenerador;
    @BindView(R.id.btn_cerrar)
    ImageView btnCerrar;
    @BindView(R.id.txt_titulo_competencia)
    TextView txtTituloCompetencia;
    @BindView(R.id.cat_lottie)
    LottieAnimationView catLottie;
    @BindView(R.id.conten_banner_error)
    ConstraintLayout contenBannerError;
    @BindView(R.id.banner_error)
    ImageView bannerError;
    @BindView(R.id.conten_principal)
    ConstraintLayout contenPrincipal;


    private Unbinder unbinder;
    private TableRegEvalAdapter adapter;
    private CabeceraTableRegEvalApadter adapterCabecera;
    private CabeceraTableRegEvalApadter adapterCabeceraTitulo;
    private PeriodoAdapter periodoAdapter;
    private CentProcesoPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This callback will only be called when MyFragment is at least Started.
        /*OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro_cent_procesamiento, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupCalendario();

    }

    private void setupCalendario() {
        periodoAdapter = new PeriodoAdapter(this);
        recyclerPeriodo.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPeriodo.setAdapter(periodoAdapter);

    }

    private void setupCabecera(String color1, String color2, String color3) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcCabecera.setLayoutManager(linearLayoutManager);
        adapterCabecera = new CabeceraTableRegEvalApadter();
        adapterCabecera.setTema(color1, color2, color3);
        rcCabecera.setAdapter(adapterCabecera);

    }

    private void setupCabecera1(String color1, String color2, String color3) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rcCabecera1.setLayoutManager(linearLayoutManager);
        adapterCabeceraTitulo = new CabeceraTableRegEvalApadter();
        adapterCabeceraTitulo.setTema(color1, color2, color3);
        rcCabecera1.setAdapter(adapterCabeceraTitulo);

    }

    private void setupTableView(String color1, String color2, String color3) {
        adapter = new TableRegEvalAdapter(getContext());
        tbEvaluacion.setAdapter(adapter);
        //tbEvaluacion.setIgnoreSelectionColors(true);
        tbEvaluacion.setHasFixedWidth(true);
        //tbEvaluacion.setIgnoreSelectionColors(true);
        tbEvaluacion.setShowHorizontalSeparators(true);
        tbEvaluacion.setShowVerticalSeparators(true);
        adapter.setTema(color1, color2, color3);
    }

    @OnClick({R.id.btn_tutorial, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tutorial:
                Navigation.findNavController(view).navigate(R.id.action_registo_evaluacion_to_contenido_tutorial);
                break;
            case R.id.btn_back:
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void setListMatrizResultado(MatrizResultadoUi matrizResultadoUi) {
        adapterCabeceraTitulo.setList(matrizResultadoUi.getCabeceraList());
        adapterCabecera.setList(matrizResultadoUi.getCompetenciaList());

        adapter.setAllItems(matrizResultadoUi.getCapacidadList(), matrizResultadoUi.getAlumnoEvalList(), matrizResultadoUi.getEvaluacionListList());

    }

    @Override
    public void setupTable(String color1, String color2, String color3) {
        try {
            corner1.setCardBackgroundColor(Color.parseColor(color1));
            corner2.setBackgroundColor(Color.parseColor(color1));
            corner3.setBackgroundColor(Color.parseColor(color1));
            changeColorHorizontalItemDecoration(color3);
            changeColorVerticalItemDecoration(color3);
        }catch (Exception e){
            e.printStackTrace();
        }

        setupTableView(color1, color2, color3);
        setupCabecera(color1, color2, color3);
        setupCabecera1(color1, color2, color3);
        //setupCabecera2();
        // AlignmentManager.join(AligningRecyclerView.ALIGN_ORIENTATION_HORIZONTAL, tbEvaluacion.getColumnHeaderRecyclerView(), tbEvaluacion.getCellRecyclerView(), rcCabecera, rcCabecera2);
        tbEvaluacion.getHorizontalRecyclerViewListener().setRecyclerViewColumnAdd(rcCabecera);
        tbEvaluacion.getHorizontalRecyclerViewListener().setRecyclerViewColumnAdd2(rcCabecera1);
        rcCabecera.addOnItemTouchListener( tbEvaluacion.getHorizontalRecyclerViewListener());
        rcCabecera.addItemDecoration(tbEvaluacion.getHorizontalItemDecoration());
        rcCabecera1.addOnItemTouchListener( tbEvaluacion.getHorizontalRecyclerViewListener());
        rcCabecera1.addItemDecoration(tbEvaluacion.getHorizontalItemDecoration());
    }

    @Override
    public void setupList(List<PeriodoUi> periodoUiList, String color2) {
        periodoAdapter.setColor(color2);
        periodoAdapter.setPeriodoList(periodoUiList);

    }

    @Override
    public void setPresenter(CentProcesoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onToogle(PeriodoUi oldPeriodoUi, PeriodoUi selectedPeriodoUi) {
        periodoAdapter.togglePeriodo(oldPeriodoUi, selectedPeriodoUi);
    }

    @Override
    public void clearListMatrizResultado() {
        adapterCabeceraTitulo.setList(new ArrayList<>());
        adapterCabecera.setList(new ArrayList<>());
        adapter.setAllItems(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Override
    public void showProgress() {
        progressBar9.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar9.setVisibility(View.GONE);
    }

    @Override
    public void showCorner() {
        corner.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCorner() {
        corner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void bloqueoBotones() {
        btnGenerador.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_icono_procesar_bloqueado));
        btnGenerador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickGenerador();
            }
        });
        btnCerrar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_icono_cerrar_cursos_bloqueo));
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickBloqueo();
            }
        });
    }

    @Override
    public void desbloqueoBotones() {
        btnGenerador.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_icono_procesar));
        btnGenerador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickGenerador();
            }
        });
        btnCerrar.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_icono_cerrar_cursos));
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickBloqueo();
            }
        });
    }

    @Override
    public void notifyChange() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setTituloCompetencia(String titulo) {
        txtTituloCompetencia.setText(titulo);
    }

    @Override
    public void showErrorSinInternet() {
        catLottie.setAnimation("loader_cat.json");
        catLottie.setRepeatCount(ValueAnimator.INFINITE);
        catLottie.playAnimation();
        contenBannerError.setVisibility(View.VISIBLE);
        contenPrincipal.setVisibility(View.GONE);
        bannerError.setImageDrawable(ContextCompat.getDrawable(bannerError.getContext(), R.drawable.ic_icono_sin_conexion));
    }

    @Override
    public void showErrorInterno() {
        catLottie.setAnimation("loader_cat.json");
        catLottie.setRepeatCount(ValueAnimator.INFINITE);
        catLottie.playAnimation();
        contenBannerError.setVisibility(View.VISIBLE);
        contenPrincipal.setVisibility(View.GONE);
        bannerError.setImageDrawable(ContextCompat.getDrawable(bannerError.getContext(), R.drawable.ic_icono_error_interno));
    }

    @Override
    public void hideError() {
        contenBannerError.setVisibility(View.GONE);
        contenPrincipal.setVisibility(View.VISIBLE);
        catLottie.cancelAnimation();
    }

    private void changeColorHorizontalItemDecoration(String color3){
        DividerItemDecoration dividerItemDecoration = tbEvaluacion.getHorizontalItemDecoration();
        Drawable divider = ContextCompat.getDrawable(getContext(), com.evrencoskun.tableview.R.drawable.cell_line_divider);
        divider.mutate().setColorFilter(Color.parseColor(color3), PorterDuff.Mode.SRC_ATOP);
        dividerItemDecoration.setDrawable(divider);
    }
    private void changeColorVerticalItemDecoration(String color3){
        DividerItemDecoration dividerItemDecoration = tbEvaluacion.getVerticalItemDecoration();
        Drawable divider = ContextCompat.getDrawable(getContext(), com.evrencoskun.tableview.R.drawable.cell_line_divider);
        divider.mutate().setColorFilter(Color.parseColor(color3), PorterDuff.Mode.SRC_ATOP);
        dividerItemDecoration.setDrawable(divider);
    }

    @Override
    public void onClick(PeriodoUi periodoUi) {
        presenter.onClickCalendario(periodoUi);
    }
}
