package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.MostrarIndicadoresPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.MostrarIndicadoresPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.adapter.IndicadoresAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.entidades.IndicadoresUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.useCase.MostrarIndicadores;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.local.CasoUsoLocalDataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kike on 10/05/2018.
 */

public class MostrarIndicadoresFragment extends DialogFragment implements MostrarIndicadoresView {

    Unbinder unbinder;
    MostrarIndicadoresPresenter presenter;
    @BindView(R.id.reciclador_indicadores)
    RecyclerView recyclerView;
    IndicadoresAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setupPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mostrar_indicadores, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        presenter.onCreateView();
        initAdapter();
        return view;
    }

    private void initAdapter() {
        adapter = new IndicadoresAdapter(new ArrayList<IndicadoresUi>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void setupPresenter() {

        CasoUsoRepository repository = new CasoUsoRepository(new CasoUsoLocalDataSource());
        presenter = new MostrarIndicadoresPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new MostrarIndicadores(repository));
        setPresenter(presenter);
       /* CasoUsoRepository LoginPreferentRepository = new CasoUsoRepository(new CasoUsoLocalDataSource());
        presenter = new MostrarCamposAccionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new MostrarCamposAccion(LoginPreferentRepository));
        setPresenter(presenter);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        /*this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));*/
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroyView();
    }

    @Override
    public void setPresenter(MostrarIndicadoresPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
        presenter.onExtras(getArguments());
    }

    @Override
    public void actualizarList(List<IndicadoresUi> indicadoresUiList) {
        adapter.refrescarLista(indicadoresUiList);
    }
}
