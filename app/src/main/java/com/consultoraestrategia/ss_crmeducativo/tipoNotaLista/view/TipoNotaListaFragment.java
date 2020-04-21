package com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.view;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera.ui.CrearRubroCabeceraActividad;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importarActividad.ui.ImportarActivity;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.TipoNotaListaPresenter;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.TipoNotaListaPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.TipoNotaListAdapter;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.adapters.holder.TipoNotaHolder;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.TipoNotaListRepository;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.data.source.local.TipoNotaListLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetTipoNotaList;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.domain.UseCase.GetValorTipoNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.listener.TipoNotaListaListener;
import com.consultoraestrategia.ss_crmeducativo.tipoNotaLista.listener.TipoNotaListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by SCIEV on 26/02/2018.
 */

public class TipoNotaListaFragment extends Fragment implements TipoNotaListaView, TipoNotaListener {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.rc_tipo_notas)
    RecyclerView rcTipoNotas;
    @BindView(R.id.btn_opcion)
    ImageView btnOpcion;
    @BindView(R.id.btn_refresh)
    ImageView btnRefresh;
    Unbinder unbinder1;
    private TipoNotaListAdapter adapter;
    private TipoNotaListaPresenterImpl presenter;
    private Unbinder unbinder;
    private TipoNotaListaListener listener;
    private static final String TAG = TipoNotaListaFragment.class.getSimpleName();
    public static final String ENUM_TIPO_TIPONOTAS = "tipos_TipoNotasUi";
    public static final String ENUM_PROGRAMA_EDUCATIVO_ID = "programaEducativoId";
    public static final String ENUM_COMPLEJO= "complejo";

    public static TipoNotaListaFragment newInstance(int programaEducativo, boolean complejo, TipoUi.Tipo... tipos) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ENUM_TIPO_TIPONOTAS, tipos);
        bundle.putInt(ENUM_PROGRAMA_EDUCATIVO_ID, programaEducativo);
        bundle.putBoolean(ENUM_COMPLEJO, complejo);
        TipoNotaListaFragment fragment = new TipoNotaListaFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    public static TipoNotaListaFragment newInstance(Bundle args, boolean complejo, TipoUi.Tipo... tipos) {
        int programaEDucativo= args.getInt(CrearRubroCabeceraActividad.INT_PROGRAMA_EDUCATIVO_ID,0);
        TipoNotaListaFragment fragment =  newInstance(programaEDucativo,complejo,tipos);
        Bundle bundle= fragment.getArguments();
        bundle.putAll(args);
        Log.d(TAG, "PROGRAAEDUCTIVO "+ programaEDucativo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_tipo_nota, container, false);
        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }

    private void setupPresenter() {
        TipoNotaListRepository repository = new TipoNotaListRepository(new TipoNotaListLocalDataSource(InjectorUtils.provideTipoNotaDao()));
        this.presenter = new TipoNotaListaPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GetTipoNotaList(repository),
                new GetValorTipoNotaPresicion()
        );
        setPresenter(presenter);
    }


    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach");
        super.onAttach(context);
        listener = (TipoNotaListaListener) getTargetFragment();
        if (listener != null) return;

        if (context instanceof TipoNotaListaListener) {
            listener = (TipoNotaListaListener) context;
        } else {
            throw new ClassCastException(
                    context.toString() + "must implement BaseFragmentListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setupAdapter();
        setupPresenter();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onResume");
        super.onStart();
        if (presenter != null) presenter.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        if (presenter != null) presenter.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        if (presenter != null) presenter.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        if (presenter != null) presenter.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null) presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        if (presenter != null) presenter.onDestroy();
    }


    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");
        listener = null;
        super.onDetach();
        if (presenter != null) presenter.onDetach();
    }


    private void setupAdapter() {
        this.adapter = new TipoNotaListAdapter(new ArrayList<TipoNotaUi>(), this);
        rcTipoNotas.setLayoutManager(new LinearLayoutManager(getContext()));
        rcTipoNotas.setAdapter(this.adapter);
    }

    @Override
    public void onShowList(List<TipoNotaUi> tipoNotaUiList) {
        Log.d(TAG, "count " + tipoNotaUiList.size());
        adapter.setTipoNotaUiList(tipoNotaUiList);
    }

    @Override
    public void succesTipoNota(int tipoNotaId, String nombre) {
        listener.onSuccessDialogTipoNotaList(tipoNotaId, nombre);
    }

    @Override
    public void succesTipoNota(String tipoNotaId, String nombre) {
        listener.onSuccessDialogTipoNotaList(tipoNotaId, nombre);
    }

    @Override
    public void updateTipoNota(TipoNotaUi tipoNotaUi) {
        adapter.updateTipoNotaUi(tipoNotaUi);
    }

    @Override
    public void showActivityImportarTipoNota(BEVariables beVariables) {
        ImportarActivity.launchImportarActivity(getContext(), TipoImportacion.TIPONOTA, beVariables);
    }

    @Override
    public void setPresenter(TipoNotaListaPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreateView();
        presenter.setExtras(getArguments());
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
    public void showMessage(CharSequence message) {

    }

    @Override
    public void showImportantMessage(CharSequence message) {

    }

    @Override
    public void showFinalMessage(CharSequence message) {

    }

    @Override
    public void showListSingleChooser(String title, List<Object> items, int positionSelected) {

    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }


    @Override
    public void onClickTipoNota(TipoNotaHolder tipoNotaHolder, TipoNotaUi tipoNotaUi) {
        presenter.onClickTipoNota(tipoNotaUi);
    }


    @OnClick({R.id.btn_opcion, R.id.btn_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_opcion:
                onClickOpcion(view);
                break;
            case R.id.btn_refresh:
                presenter.onClickRefresh();
                break;
        }
    }

    private void onClickOpcion(View view) {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(getContext(), view);
        DroppyMenuPopup droppyMenu = droppyBuilder
                //.fromMenu(R.menu.droppy)
                .addMenuItem(new DroppyMenuItem("Importar"))
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int positionMenu) {
                        Log.d("positionMenu:", String.valueOf(positionMenu));
                        switch (positionMenu){
                            case 0:
                                presenter.onClickImportar();
                                break;
                        }
                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call() {
                        Log.d(TAG, "onDismissCallback");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }
}
