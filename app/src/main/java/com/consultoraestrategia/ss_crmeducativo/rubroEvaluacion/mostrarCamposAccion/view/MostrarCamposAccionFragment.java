package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.view;

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
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.MostrarCamposAccionPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.MostrarCamposAccionPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.adapter.IndicadoresCamposAccionAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.MostrarCamposAccion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.local.CasoUsoLocalDataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by kike on 08/05/2018.
 */

public class MostrarCamposAccionFragment extends DialogFragment implements MostrarCamposAccionView {

    public static final String RUBROPROCESO_KEY = "rubroProcesoKey";
    @BindView(R.id.reciclador_campos)
    RecyclerView recyclerView;
   /* @BindView(R.id.btn_aceptar)
    Button buttonOk;*/

    Unbinder unbinder;
    MostrarCamposAccionPresenter presenter;
    //CamposAccionAdapter camposAccionAdapter;
    IndicadoresCamposAccionAdapter indicadoresCamposAccionAdapter;


    public static MostrarCamposAccionFragment newInstance(String rubroProcesoKey) {
        MostrarCamposAccionFragment fragment = new MostrarCamposAccionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(RUBROPROCESO_KEY, rubroProcesoKey);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
        // Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setupPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mostrar_campos_accion, container, false);
        unbinder = ButterKnife.bind(this, view);
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        presenter.onCreateView();
        initAdapter();
        return view;
    }

    private void initAdapter() {
        indicadoresCamposAccionAdapter = new IndicadoresCamposAccionAdapter(new ArrayList<IndicadoresCamposAccionUi>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(indicadoresCamposAccionAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void setupPresenter() {
        CasoUsoRepository repository = new CasoUsoRepository(new CasoUsoLocalDataSource());
        presenter = new MostrarCamposAccionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new MostrarCamposAccion(repository));
        setPresenter(presenter);
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
    public void setPresenter(MostrarCamposAccionPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
        presenter.onExtras(getArguments());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroyView();
    }

    @Override
    public void actualizarLista(List<IndicadoresCamposAccionUi> camposAccionUiList) {
        indicadoresCamposAccionAdapter.refrescarLista(camposAccionUiList);
    }

    @OnClick(R.id.btn_aceptar)
    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.btn_aceptar:
                getDialog().dismiss();
                break;
            default:
                break;
        }
    }
}
