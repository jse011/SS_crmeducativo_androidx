package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.cabecera.ui.CrearRubroCabeceraActividad;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.RubroProcesoWrapper;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.CrearRubroListener;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.ui.CrearRubrosFragment;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.BaseTabFragmentView;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener.CrearRubroListaIndicadoresListener;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.ui.ListaIndicadoresFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.CompetenciaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.PeriodoAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.CrearRubroFormulaActivity;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroEvaluacionResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.view.EvaluacionFormulaActivity;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.PeriodoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroProcesoListaListener;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.AbstractPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.FragmentAbstractPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.EditarRubrosActivity;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view.FragmentRubroEvalProcesoLista;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.view.MostrarCamposAccionFragment;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import org.parceler.Parcels;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

import static android.app.Activity.RESULT_OK;

/**
 * Created by SCIEV on 6/02/2018.
 */

public abstract class FragmentAbstract<T extends Fragment, P extends AbstractPresenterImpl> extends Fragment implements FragmentAbstractView, RubroEvaluacionProcesoListener, CrearRubroListaIndicadoresListener, CrearRubroListener, PeriodoListener, BaseTabFragmentView {

    public static final String EXTRA_PROGRAMA_EDUCATIVO = "programaEducativoId";
    protected String TAG = FragmentAbstract.class.getSimpleName();

    public static final String TIPO = "tipo";
    public static final int TIPO_UPDATE_COMPETENCIA = 0, TIPO_UPDATE_RUBRO_EVALUACION = 1, TIPO_ADD_RUBRO_EVALUACION = 2, TIPO_ADD_RUBRO_EVALUACION_FORMULA = 3, TIPO_UPDATE_RUBRO_EVALUACION_FORMULA = 4, TIPO_SELECCIONAR_RUBRO = 25;
    public static final String LIST_COMPETENCIAS = "competenciasId";
    public static final String INT_RUBROEVALUACION_ID = "rubroEvaluacionAprendizajeId";

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @BindView(R.id.rc_rubro_evaluacion_resultado)
    protected RecyclerView rcRubroEvaluacionResultado;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.txt_mensaje)
    TextView txtMensaje;
    @BindView(R.id.imagen_carga)
    ImageView imagenCarga;
    @BindView(R.id.txtOffline)
    TextView txtOffline;
    @BindView(R.id.txtError)
    TextView txtError;
    @BindView(R.id.contentVacio)
    ConstraintLayout contentVacio;
    @BindView(R.id.constraintLayoutRubroProceso)
    ConstraintLayout constraintLayoutRubroProceso;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.img_calcular)
    ImageView imgCalcular;
    @BindView(R.id.btn_calcular)
    View btnCalcular;
    @BindView(R.id.txt_calcular)
    TextView txtCalcular;
    @BindView(R.id.viewGroup)
    LinearLayout viewGroup;
    @BindView(R.id.card_view)
    CardView cardView;
    private Unbinder unbinder;
    protected CompetenciaAdapter adapter;
    protected P presenter;
    private ListaIndicadoresFragment listaIndicadoresFragment;
    private CrearRubrosFragment crearRubrosFragment;
    protected PeriodoAdapter periodoAdapter;
    protected RubroProcesoListaListener refrescarListener;
    private SpotsDialog alertDialog;

    public FragmentAbstract() {

    }

    protected abstract P getPresenter();

    public static <T extends Fragment> T newInstance(Class<T> fragmentClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, java.lang.InstantiationException {
        Constructor<T> c = fragmentClass.getConstructor();
        return c.newInstance();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_rubro_evaluacion_proceso, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        setupAdapter();
        setupDialogProgress();
        presenter.onViewCreated();
    }

    private void setupDialogProgress() {
        alertDialog = new SpotsDialog(getContext(), R.style.SpotsDialogRegistro);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter == null) return;
        presenter.onResume();
    }

    @Override
    public void setPresenter(FragmentAbstractPresenter presenter) {
        presenter.attachView(this);
        presenter.setExtras(getArguments());
        presenter.onCreate();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter == null) return;
        presenter.onDestroyView();
        unbinder.unbind();
    }

    private void initPresenter() {
        if (presenter == null) presenter = getPresenter();//getPresenter();
        setPresenter(presenter);
    }


    @Override
    public void showRubroEvaluacionProceso(List<Object> items) {
        adapter.setItems(items);
    }

    @Override
    public void showTipoRubroProceso() {
        if (this instanceof FragmentRubroEvalProcesoLista)
            constraintLayoutRubroProceso.setPadding(0, 0, 0, 0);
    }

    @Override
    public void showProgres() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgres() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onClickAddRubroEvaluacionCapacidad(CapacidadUi capacidadUi) {
        Log.d(TAG, "onClickAddRubroEvaluacionCapacidad");
        presenter.onClickAddRubroEvaluacionCapacidad(capacidadUi);
    }

    @Override
    public void onClickCapacidad(CapacidadUi capacidadUi) {
        Log.d(TAG, "onClickCapacidad");
        presenter._onClickCapacidad(capacidadUi);
    }

//    @Override
//    public void updateCompetencia(Object o) {
//        adapter.updateItem(o);
//    }

//    @Override
//    public void addRubroProceso(Object o, RubroProcesoUi rubroProcesoUi) {
//        adapter.addRubroProceso(o, rubroProcesoUi);
//        hideProgres();
//    }

    @Override
    public void onClickRubroEvaluacion(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
        Log.d(TAG, "onClickRubroEvaluacion");
        presenter.onClickRubroEvaluacion(capacidadUi, rubroProcesoUi);

    }

    @Override
    public void onClickLongClickEvaluacion(RubroProcesoUi rubroProcesoUi) {
        presenter.onClickLongClickRubroEvaluacion(rubroProcesoUi);
    }

    @Override
    public void showListaIndicadoressSesionAprendizaje(int sesionAprendizajeId, int nivel, int competencia) {
        FragmentManager manager = getFragmentManager();
        listaIndicadoresFragment = ListaIndicadoresFragment.newInstance(ListaIndicadoresFragment.Id.SESION_APRENDIZAJE_ID, sesionAprendizajeId, nivel, competencia, 0);
        listaIndicadoresFragment.setTargetFragment(this, 200);
        listaIndicadoresFragment.show(manager, "ListaIndicadoresFragment");
    }

    @Override
    public void showListaIndicadoressSilavoEvento(int SilavoEventoId, int nivel, int competencia) {
        FragmentManager manager = getFragmentManager();
        listaIndicadoresFragment = ListaIndicadoresFragment.newInstance(ListaIndicadoresFragment.Id.SILAVO_EVENTO_ID, SilavoEventoId, nivel, competencia, 0);
        listaIndicadoresFragment.setTargetFragment(this, 200);
        listaIndicadoresFragment.show(manager, "ListaIndicadoresFragment");
    }

    @Override
    public void hideListaIndicadores() {
        listaIndicadoresFragment.hideDialog();
    }

    @Override
    public void onSelectIndicadorCampotematicovoid(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds) {
        presenter.onSelectDialogListIndicadorCampotematico(competenciaId, indicadorId, campotematicoIds);
    }

    @Override
    public void onSalirListaIndicadores() {
        presenter.onSalirDialogListIndicadorCampotematico();
    }

    @Override
    public void showCrearRubro(int sesionAprendizajeId, int silavoEventoId, int calendarioPeriodoId, int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds, int programaEducativoId, int cursoId) {
        Intent intent = CrearRubroCabeceraActividad.launchCrearRubroEvaluacionActivity(getContext(), sesionAprendizajeId, silavoEventoId, calendarioPeriodoId, competenciaId, indicadorId, campotematicoIds, programaEducativoId, null, cursoId);
        startActivityForResult(intent, CrearRubroCabeceraActividad.REQUESTCODE);
    }

    @Override
    public void onSaveRubroEvaluacionProcesoSuccess(String rubroEvaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi) {
        presenter.onSucessDialogCrearRubroEvaluacionProceso(rubroEvaluacionProcesoId, crearRubroEvalUi);
    }

    @Override
    public void hideCrearRubro() {
        presenter.onSalirDialogCrearRubroEvaluacionProceso();
    }


    @Override
    public void onPeriodoSelected(PeriodoUi periodoUi) {
        presenter.onPeriodoSelected(periodoUi);
    }


    @Override
    public void listIndicadoresFragment(int id, int nivel, CapacidadUi capacidadUi, int calendarioPeriodoId) {
        FragmentManager manager = getFragmentManager();
        listaIndicadoresFragment = ListaIndicadoresFragment.newInstance(ListaIndicadoresFragment.Id.SILAVO_EVENTO_ID, id, nivel, capacidadUi.getId(), calendarioPeriodoId);
        Log.d(TAG, "listIndicadoresFragment " + capacidadUi.getId());
        listaIndicadoresFragment.setTargetFragment(this, 200);
        listaIndicadoresFragment.show(manager, "ListaIndicadoresFragment");
    }


    @Override
    public void initDialogTypeFormula(List<RubroProcesoUi> procesoUiList, CapacidadUi capacidadUi, int programaEducativoId, int cargaCursoId) {
        Log.d(TAG, "initDialogTypeFormula : " + programaEducativoId);
        Intent intent = new Intent(getActivity(), CrearRubroFormulaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("listaRubros", Parcels.wrap(procesoUiList));
        bundle.putParcelable("rubroEvaluacionResul", Parcels.wrap(capacidadUi));
        bundle.putInt("programaEduId", programaEducativoId);
        bundle.putInt("cargaCursoId", cargaCursoId);
        intent.putExtras(bundle);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        Log.d(TAG, "onActivityResult1 " + requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult2 " + requestCode);
        if (data == null) return;
        Bundle bundle = data.getExtras();
        if (bundle == null) return;
        Log.d(TAG, "onActivityResult " + requestCode);
        Log.d(TAG, "resultCode " + resultCode);
        Log.d(TAG, "bundle " + bundle.toString());
        if (resultCode == RESULT_OK) {
            if (presenter == null) return;
            presenter.onActivityResult(bundle);
        }
    }

    @Override
    public void onClickRubrosAsociados(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi) {
        Log.d(TAG, "onClickRubrosAsociados");
        presenter.onClickRubrosAsociados(rubroProcesoUi, capacidadUi);
    }

    @Override
    public void onClickAceptarDialogFormula(CapacidadUi capacidadUi, RubroProcesoUi rubroEvaluacionProcesoUi) {
        presenter.onAgregarRubroFormula(capacidadUi, rubroEvaluacionProcesoUi);
        Log.d(TAG, "onClickAceptarDialogFormula : " + capacidadUi.getTitle() + "rubroEvaluacionProc : " + rubroEvaluacionProcesoUi.getTitulo());
    }

    @Override
    public void onOpciones(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi, View view) {
        presenter.onSelectOptions(capacidadUi, rubroProcesoUi, view);
    }

    @Override
    public void showDialogDelete(final CapacidadUi capacidadUi, final RubroProcesoUi procesoUi, final int programaEducativoId) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Eliminar");
        builder.setMessage(R.string.msg_elimar_rubro);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "Presionos ACEPTAR");
                presenter.onSelectDelete(capacidadUi, procesoUi, programaEducativoId);
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
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageCancel(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void deleteRubro(CapacidadUi capacidadUi, RubroProcesoUi rubroEvaluacionProcesoUi) {
//        adapter.deleteRubroProceso(capacidadUi, rubroEvaluacionProcesoUi);
//        initServiceUpdate();
//    }

    @Override
    public void initServiceUpdate(int programaEducativoId) {
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    public static final String ARG_ID_CARGA_CURSO = "idCargaCurso";
    public static final String ARG_ID_CURSO = "cursoId";

    @Override
    public void initViewTipoRubros(RubroEvaluacionResultadoUi rubroEvaluacionResultadoUi, RubroProcesoUi rubroProcesoUi, int mIdCargaCurso, int mIdCurso) {
        //Intent intent = new Intent(getActivity(), com.consultoraestrategia.ss_crmeducativo.evaluacionproceso.ContainerActivity.class);
        Intent intent = new Intent(getActivity(), EvaluacionFormulaActivity.class);
        intent.putExtra(ARG_ID_CARGA_CURSO, mIdCargaCurso);
        intent.putExtra(ARG_ID_CURSO, mIdCurso);
        //intent.putExtra("procesoUi", rubroEvaluacionProcesoUi.getTitle());
        // intent.putExtra("resultadoUi", rubroEvaluacionResultadoUi.getTitulo());

       /* List<RubroEvaluacionProcesoUi> rubroEvaluacionProcesoUis = new ArrayList<>();
        rubroEvaluacionProcesoUis.add(rubroEvaluacionProcesoUi);*/
        intent.putExtras(getArguments());
        intent.putExtra(EvaluacionFormulaActivity.ARG_RUBRO_PROCE, Parcels.wrap(rubroProcesoUi));
        // intent.putExtra(EvaluacionProcesoPresenterImpl.ARG_RUBRO_RESUL, Parcels.wrap(rubroEvaluacionResultadoUi));
        startActivity(intent);

        /*List<RubroEvaluacionProcesoUi> rubroEvaluacionProcesoUis = new ArrayList<>();
        rubroEvaluacionProcesoUis.add(rubroEvaluacionProcesoUi);
        rubroEvaluacionProcesoUis.add(rubroEvaluacionProcesoUi);
        /*try {
            EvaluacionFormulaActivity.launchEvaluacionFormulaActivity(getActivity(), getActivity().getIntent().getExtras(), rubroEvaluacionProcesoUi);
        } catch (Exception ex) {
            showMessage(getResources().getString(R.string.unknown_error));
        }*/
    }

//    public void updateRubroProceso(Object o, RubroProcesoUi rubroProcesoUi) {
//        adapter.updateRubroProceso(o, rubroProcesoUi);
//    }


//    @Override
//    public void refreshList(Object o) {
//        adapter.refreshList(o);
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            refrescarListener = (RubroProcesoListaListener) context;
        } catch (Exception e) {
            Log.d(TAG, "ImplementarloActivity : " + e.toString());

        }
    }

    @Override
    public void onClickCamposTematicos(RubroProcesoUi rubroProcesoUi) {
        Log.d(TAG, "onClickCamposTematicos : " + rubroProcesoUi.getKey());
        presenter.onBtnCamposTematicos(rubroProcesoUi);
    }

    @Override
    public void onShowListSingleChooser(String dialogTitle, List<Object> items, int positionSelected) {
        showListSingleChooser(dialogTitle, items, positionSelected);
    }

    @Override
    public void goToCamposFragment(String key) {
        FragmentManager manager = getFragmentManager();
        MostrarCamposAccionFragment mostrarCamposAccionFragment = MostrarCamposAccionFragment.newInstance(key);
        mostrarCamposAccionFragment.show(manager, "FragmentCamposAccion");
    }


    @Override
    public void initOpcionesRubroFormula(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi, View view) {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(getContext(), view);

        DroppyMenuItem editar = new DroppyMenuItem("Editar");
        editar.setId(0);
        DroppyMenuItem eliminar = new DroppyMenuItem("Eliminar");
        eliminar.setId(1);
        DroppyMenuItem anclar = new DroppyMenuItem("Anclar");
        anclar.setId(2);

        DroppyMenuPopup.Builder builder = droppyBuilder;

        builder
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {
                        Log.d("positionMenu:", String.valueOf(id));
                        presenter.onSelectOpcionesRubroFormula(capacidadUi, id, rubroProcesoUi);
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
                .setYOffset(5);

        boolean anclaExistente = false;

        for (RubroProcesoUi procesoUi : capacidadUi.getRubroProcesoUis()) {
            if (procesoUi.isTipoAncla()) {
                anclaExistente = true;
            }
        }
        if (!rubroProcesoUi.isTipoAncla() && !anclaExistente && capacidadUi.isCalendarioAnclar()) {
            builder.addMenuItem(anclar);
        }

        if (capacidadUi.isCalendarioEditar()) {
            builder
                    //.fromMenu(R.menu.droppy)
                    .addMenuItem(editar)
                    .addMenuItem(eliminar);
        }

        DroppyMenuPopup droppyMenu = builder.build();
        droppyMenu.show();

    }

    @Override
    public void initOpcionesRubroRubrica(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi, View view) {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(getContext(), view);
        DroppyMenuPopup droppyMenu = droppyBuilder
                //.fromMenu(R.menu.droppy)
                // .addMenuItem(new DroppyMenuItem("Editar"))
                .addMenuItem(new DroppyMenuItem("Eliminar"))
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int positionMenu) {
                        Log.d("positionMenu:", String.valueOf(positionMenu));
                        // presenter.onSelectOptions(capacidadUi, positionMenu, rubroProcesoUi);
                        presenter.onSelectOpcionesRubroRubrica(capacidadUi, positionMenu, rubroProcesoUi);
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

    @Override
    public void initOpcionesRubroNormal(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi, View view) {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(getContext(), view);
        DroppyMenuPopup droppyMenu = droppyBuilder
                //.fromMenu(R.menu.droppy)
                .addMenuItem(new DroppyMenuItem("Editar"))
                .addMenuItem(new DroppyMenuItem("Eliminar"))
                .addMenuItem(new DroppyMenuItem("Publicar"))
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int positionMenu) {
                        Log.d("positionMenu:", String.valueOf(positionMenu));
                        presenter.onSelectOpcionesRubroNormal(capacidadUi, positionMenu, rubroProcesoUi);
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

    @Override
    public void showDialogPublicar(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi, final int programaEducativoId) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Publicar");
        builder.setMessage(R.string.msg_publicar_eval_rubro);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Log.d(TAG, "Presionos ACEPTAR");
                presenter.onClickBtnAceptarPublicarTodoEvaluaciones(capacidadUi, rubroProcesoUi, programaEducativoId);
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
    public void mostrarDialogoAnclar(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Anclar");
        builder.setMessage("Estas seguro que desea Anclar ?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "Presionos ACEPTAR");
                presenter.onAceptarDialogAnclar(capacidadUi, rubroProcesoUi);
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
    public void showDialogDesanclar(final CapacidadUi capacidadUi) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle("Anclar");
        builder.setMessage("Estas seguro que desea Desanclar ?");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "Presionos ACEPTAR");
                presenter.onAceptarDialogDesanAnclar(capacidadUi);
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

    /**
     * Crea un Diálogo con una lista de selección simple
     */
    public void showListSingleChooser(String dialogTitle, final List<Object> items, int positionSelected) {
        if (items.isEmpty()) return;
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        int size = items.size();
        final CharSequence[] singleItems = new CharSequence[size];

        for (int i = 0; i < size; i++) {
            singleItems[i] = items.get(i).toString();
        }

        if (positionSelected >= items.size()) {
            positionSelected = -1;
        }

        builder.setTitle(dialogTitle)
                .setItems(singleItems, new DialogInterface.OnClickListener() {
                    // .setSingleChoiceItems(singleItems, positionSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(getTag(), "setSingleChoiceItems onClickTipoComportamiento i: " + which);
                        /*Log.d(getTag(), "setPositiveButton onClickTipoComportamiento i: " + which);
                        int selectedPosition = ((android.support.v7.app.AlertDialog) dialog).getListView().getCheckedItemPosition();
                        if (selectedPosition != -1) {
                            Object objectSelected = items.get(selectedPosition);
                            if (presenter != null) {
                                presenter.onSingleItemSelected(objectSelected);
                            }
                        }
                        dialog.dismiss();*/
                    }
                });
        builder.create().show();
    }

    public static final int EDITAR_RUBRICABID = 10;
    public static final String EXTRA_CAPACIDAD = "capacidadUi";
    public static final String EXTRA_RUBRO = "rubroProcesoUi";

    @Override
    public void startEditActivity(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, int programaEducativoId) {
        Intent intent = EditarRubrosActivity.iniciarEditarRubBrosdActividad(getActivity(), capacidadUi, rubroProcesoUi, programaEducativoId);
        startActivityForResult(intent, EDITAR_RUBRICABID);
    }

    @Override
    public void startEditActivityRubroNormal(RubroProcesoWrapper rubroProcesoWrapper) {
        Intent intent = CrearRubroCabeceraActividad.launchEditeRubBrosdActividad(getActivity(), rubroProcesoWrapper);
        startActivityForResult(intent, EDITAR_RUBRICABID);

    }

    @Override
    public void showMensaje(String mensaje) {

        txtMensaje.setText(Html.fromHtml(mensaje));
    }

    @Override
    public void showMsgOffline() {
        contentVacio.setVisibility(View.VISIBLE);
        txtError.setVisibility(View.GONE);
        txtOffline.setVisibility(View.VISIBLE);
        imagenCarga.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMsgOffline() {
        contentVacio.setVisibility(View.GONE);
    }

    @Override
    public void showMsgOfflineError() {
        contentVacio.setVisibility(View.VISIBLE);
        txtError.setVisibility(View.VISIBLE);
        txtOffline.setVisibility(View.GONE);
        imagenCarga.setVisibility(View.GONE);
    }

    public void filtrarRubroCompetencia() {
        adapter.getFilter().filter(CompetenciaUi.Tipo.COMPETENCIA_BASE.toString());
    }

    public void filtrarRubroEnfoqueTrans() {
        adapter.getFilter().filter(CompetenciaUi.Tipo.COMPETENCIA_ENFQ.toString());
    }

    @Override
    public void onResumeFragment() {
        Log.d(TAG, "onResumeFragment REGISTROS");
        presenter.onResumeFragment();
    }

    @Override
    public void showSeleccionarRubros(CapacidadUi capacidadUi) {

    }

    @Override
    public void onPesoChanged(RubroProcesoUi rubroEvaluacionProcesoUi, CharSequence charSequence) {

    }

    @Override
    public void onClickSelectRubros(CapacidadUi capacidadUi) {

    }

    public void onActualizarFragment(String idCalendarioPeriodo, boolean status) {
        presenter.onRefrescarFragment(idCalendarioPeriodo, status);
    }

    @Override
    public void onClickAncla(CapacidadUi capacidadUi) {
        presenter.onClickAncla(capacidadUi);
    }


    @Override
    public void succesChangePublicar(RubroProcesoUi rubroProcesoUi, int programaEducativoId) {
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.RUBROEVALUACION);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @OnClick(R.id.btn_calcular)
    public void onViewCalcular() {
        showDialogCalcularNotas();
    }

    public void showDialogCalcularNotas() {

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Confirmación");
        builder.setMessage(getResources().getString(R.string.title_calcular_notas))
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onClickAceptarCalcular();
                        //alertDialog.show();
                    }
                })
                .setNegativeButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void showCardProcesamiento(String color1, String color2) {
        viewGroup.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
        try {
            if(!TextUtils.isEmpty(color1))view3.setBackgroundColor(Color.parseColor(color1));
            if(!TextUtils.isEmpty(color2))txtCalcular.setTextColor(Color.parseColor(color1));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    @Override
    public void hideCardProcesamiento() {
        viewGroup.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
    }

    @Override
    public void showDialogProgress() {
        alertDialog.show();
    }

    @Override
    public void hideDialogProgress() {
        Log.d(TAG, "hideDialogProgress");
        alertDialog.dismiss();
    }

    @Override
    public void actualizarRubroTipoAncla() {

    }

    @Override
    public void savePositionList() {

    }

    @Override
    public void clearPositionList() {

    }

    @Override
    public void startEditFormual(String key) {
        Intent intent = new Intent(getActivity(), CrearRubroFormulaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("rubroFormulaId", key);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
