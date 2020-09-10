package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.consultoraestrategia.ss_crmeducativo.login2.service2.worker.SynckService;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.BaseTabFragmentView;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.CreateRubBidActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.grupal.ui.EvaluacionBimencionalGrupalActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.individual.ui.EvaluacionBimencionalActividad;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.RubricasAbstractPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.RubricasView;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter.RubBidAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.adapter.RubricaColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.listener.RubricaListener;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.utils.DecoracionLineaDivisoria;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by CCIE on 07/03/2018.
 */

public abstract class RubricasAbstractFragment<T extends Fragment, P extends RubricasAbstractPresenterImpl> extends Fragment implements RubricasView, RubricaListener, BaseTabFragmentView {

    private static String RUBRICAS_ABSTRACT_FRAGMENT_TAG = RubricasAbstractFragment.class.getSimpleName();

    public static final String TIPO_RUBRICA = "tipo_rubrica";
    public static final String RUBROEVALUACIONID = "CreateRubBidActivity.rubroEvaluacionId";
    public static final String COUNT_INDICADOR = "CreateRubBidActivity.countIndicador";
    public static final int ACTUALIZAR_RUBRICAS_BIDIMENSIONAL = 1, AGREGAR_RUBRICAS_BIDIMENSIONAL = 2;


    private Unbinder unbinder;
    @BindView(R.id.reciclador)
    RecyclerView recicladorRubricas;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewMensaje)
    TextView textViewMensaje;
    @BindView(R.id.constraintLayoutRubrica)
    ConstraintLayout constraintLayoutRubrica;


    protected RubBidAdapter rubBidAdapter;

    protected abstract P obtenerPresenter();

    protected P presenter;


    public RubricasAbstractFragment() {

    }


    public static <T extends Fragment> T newInstance(Class<T> fragmentClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, java.lang.InstantiationException {
        Constructor<T> c = fragmentClass.getConstructor();
        return c.newInstance();
    }


    private void iniciaPresenter() {
        if (presenter == null) presenter = obtenerPresenter();
        setPresenter(presenter);
    }

    @Override
    public void setPresenter(RubricasAbstractPresenter presenter) {
        presenter.attachView(this);
        presenter.setExtras(getArguments());
        presenter.onCreate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_fragment_rubrica, container, false);
        unbinder = ButterKnife.bind(this, view);
//        presenter.onCreateView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniciaPresenter();
        presenter.onViewCreated();
        setupAdapterRubricas();
    }

    private void setupAdapterRubricas() {
        rubBidAdapter = new RubBidAdapter(new ArrayList<RubBidUi>(), this);
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        RubricaColumnCountProvider columnCountProvider = new RubricaColumnCountProvider(recicladorRubricas.getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recicladorRubricas.setLayoutManager(autoColumnGridLayoutManager);
        recicladorRubricas.setAdapter(rubBidAdapter);
        recicladorRubricas.addItemDecoration(new DecoracionLineaDivisoria(getResources().getDimensionPixelOffset(R.dimen.dimen_5dp), getResources().getInteger(R.integer.recyclerview_gridview)));
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

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetach();
    }


    @Override
    public void mostrarProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mostrarListaRubricas(List<RubBidUi> objectList) {
        Log.d(RubricasAbstractFragment.class.getSimpleName(), "mostrarListaRubricas");
        rubBidAdapter.setItems(objectList);
    }

    @Override
    public void notifyChangeRbrica() {
        rubBidAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(RubBidUi item) {
        presenter.onRubricaSelected(item);

    }

    @Override
    public void evaluacionRubricaBidimensional(RubBidUi rubBidUi, int cargaCursoId) {
        CRMBundle crmBundle = new CRMBundle(getArguments());
        crmBundle.setCalendarioActivo(rubBidUi.isDisabledEval());
        crmBundle.setCalendarioEditar(rubBidUi.isEditar());
        switch (rubBidUi.getForma()) {
            case INDIVIDUAL:
                EvaluacionBimencionalActividad.launchEvaluacionBimencionalActividadIndividual(getContext(),crmBundle.instanceBundle(), rubBidUi.getKey(), cargaCursoId);
                break;
            case GRUPAL:
                EvaluacionBimencionalGrupalActividad.launchEvalRubBidActivity(getContext(), crmBundle.instanceBundle(), rubBidUi.getKey(), cargaCursoId);
                break;
        }
    }

    @Override
    public void mostrarMensaje(String stringRes) {
        textViewMensaje.setVisibility(View.VISIBLE);
        textViewMensaje.setText(Html.fromHtml(stringRes));

    }

    @Override
    public void showTipoRubricaBidimensional() {
        //if (this instanceof RubricaSesionFragment)constraintLayoutRubrica.setPadding(0,0,0,0);
    }

    @Override
    public void ocultarMensaje() {
        textViewMensaje.setVisibility(View.GONE);
    }

    @Override
    public void actualizarVista(RubBidUi rubBidUi) {
        Log.d(RUBRICAS_ABSTRACT_FRAGMENT_TAG, "actualizarVista ");
        rubBidAdapter.agregarItem(rubBidUi);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        Log.d(RUBRICAS_ABSTRACT_FRAGMENT_TAG, "startActivityForResult ");
        presenter.onStartActivityForResult(intent, requestCode, options);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, requestCode, data);
        Log.d(RUBRICAS_ABSTRACT_FRAGMENT_TAG, "onActivityResult");
    }

    @Override
    public void mostrarMensajeSnack(String mensaje) {
        Snackbar.make(recicladorRubricas, mensaje, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onOpciones(final RubBidUi rubBidUi, View itemView) {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(getContext(), itemView);
        DroppyMenuPopup droppyMenu = droppyBuilder
                //.fromMenu(R.menu.droppy)
                .addMenuItem(new DroppyMenuItem("Editar"))
                .addMenuItem(new DroppyMenuItem("Eliminar"))
                .addMenuItem(new DroppyMenuItem("Publicar"))
                //.addMenuItem(new DroppyMenuItem("Mensaje Rubros"))
                //.addMenuItem(new DroppyMenuItem("Mensaje Rubros v2"))
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int positionMenu) {
                        Log.d("positionMenuRubrica:", String.valueOf(positionMenu));
                        presenter.onSelectOpcionMenuRubricas(rubBidUi, positionMenu);
                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call() {
                        Log.d("call:", "asdadasdads");
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
        droppyMenu.show();
    }

    public static final int EDITAR_RUBRICABID = 10;

    @Override
    public void iniciarEditarRubrica(RubBidUi rubBidUi) {
        Intent intent = CreateRubBidActivity.getStartCreateRubBidActivity(getActivity(),new CRMBundle(getArguments()), rubBidUi.getKey());
        startActivityForResult(intent, EDITAR_RUBRICABID);
    }

    @Override
    public void mostrarDialogEliminarRubrica(final RubBidUi rubBidUi, final int programaEducativoId) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Eliminar");
        builder.setMessage(R.string.msg_elimar_rubro);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Log.d(TAG, "Presionos ACEPTAR");
                presenter.onAceptEliminar(rubBidUi, programaEducativoId);
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        //Create AdapterExample
        builder.create().show();
    }

    @Override
    public void eliminarRubricaBid(RubBidUi rubBidUi) {
        rubBidAdapter.eliminarItem(rubBidUi);
    }

    @Override
    public void editarRubricaBid(RubBidUi rubBidUi) {
        rubBidAdapter.editarItem(rubBidUi);
    }

    @Override
    public void showDialogPublicarTodoEvaluaciones(final RubBidUi rubBidUi) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Publicar");
        builder.setMessage(R.string.msg_publicar_eval_rubro);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Log.d(TAG, "Presionos ACEPTAR");
                presenter.onClickBtnAceptarPublicarTodoEvaluaciones(rubBidUi);
                dialogInterface.cancel();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });
        //Create AdapterExample
        builder.create().show();
    }

    @Override
    public void succesChangePublicar(int programaEDucativoId, String key) {
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(getContext(), programaEDucativoId);
        SynckService.start(getContext(),programaEDucativoId);
    }

    public void comprobarActualizacionRubros() {
        RubBidAdapter adapter = (RubBidAdapter)recicladorRubricas.getAdapter();
        if(adapter!=null){
            List<RubBidUi> rubBidUiList = adapter.getItems();
            presenter.comprobarActualizacionRubros(rubBidUiList);
        }

    }

}

