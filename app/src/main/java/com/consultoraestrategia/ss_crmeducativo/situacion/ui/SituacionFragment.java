package com.consultoraestrategia.ss_crmeducativo.situacion.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.BaseTabFragmentView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.situacion.SituacionPresenter;
import com.consultoraestrategia.ss_crmeducativo.situacion.SituacionPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.situacion.SituacionView;
import com.consultoraestrategia.ss_crmeducativo.situacion.adapter.SituacionAdapter;
import com.consultoraestrategia.ss_crmeducativo.situacion.adapter.SituacionColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.situacion.entity.SituacionUI;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.SituacionRepository;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.local.SituacionLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.situacion.source.remote.SituacionRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.situacion.usecase.GetSituacionListUI;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class SituacionFragment extends Fragment implements SituacionView, BaseTabFragmentView {

    private static final String TAG = SituacionFragment.class.getSimpleName();

    public static String ID_ENTIDAD = "idEntidad";
    public static String ID_CARGA_CURSO = "idCargaCurso";
    public static String ID_UNIDAD_APRENDIZAJE = "idUnidadAprendizaje";

    SituacionPresenter presenter;
    @BindView(R.id.rvSituacion)
    RecyclerView rvSituacion;
    Unbinder unbinder;
    @BindView(R.id.txtmensaje)
    TextView txtmensaje;

    public static SituacionFragment newInstance( int idUnidadAprendizaje, int idCargaCurso, int entidadId) {
        Log.d(TAG, "newInstance");
        SituacionFragment fragment = new SituacionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ID_ENTIDAD, entidadId);
        bundle.putInt(ID_CARGA_CURSO, idCargaCurso);
        bundle.putInt(ID_UNIDAD_APRENDIZAJE, idUnidadAprendizaje);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewPadre = inflater.inflate(R.layout.tab_item_situacion, container, false);
        unbinder = ButterKnife.bind(this, viewPadre);
        setupPresenter();
        setupRVSituacion();
        presenter.setExtras(getArguments());
        return viewPadre;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    SituacionAdapter situacionAdapter;

    private void setupRVSituacion() {
        situacionAdapter = new SituacionAdapter(new ArrayList<SituacionUI>());
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        SituacionColumnCountProvider columnCountProvider = new SituacionColumnCountProvider(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        rvSituacion.setLayoutManager(autoColumnGridLayoutManager);
        rvSituacion.setAdapter(situacionAdapter);
        rvSituacion.setHasFixedSize(true);


    }


    private void setupPresenter() {
        presenter = new SituacionPresenterImpl(
                new UseCaseHandler(
                        new UseCaseThreadPoolScheduler()),
                new GetSituacionListUI(new SituacionRepository(
                        new SituacionLocalDataSource(InjectorUtils.provideUnidadAprendizajeDao()),
                        new SituacionRemoteDataSource()))
        );

        setPresenter(presenter);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void setPresenter(SituacionPresenter presenter) {
        if (presenter == null) return;
        presenter.attachView(this);
        presenter.onCreate();

    }

    @Override
    public void showProgress() {
        txtmensaje.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if(txtmensaje!=null)txtmensaje.setVisibility(View.GONE);
    }

    @Override
    public void showMsjLong(String msaje) {
        Toast.makeText(getActivity(), msaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void hideMessage() {

    }

    @Override
    public void showSituacionUIList(List<SituacionUI> list) {
        situacionAdapter.setSituacionUIList(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResumeFragment() {
        presenter.onResumeFragment();
    }
}