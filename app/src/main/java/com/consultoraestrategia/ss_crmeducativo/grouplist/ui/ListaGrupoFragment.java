package com.consultoraestrategia.ss_crmeducativo.grouplist.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.createTeamList.ui.CreateTeamListActivity;
import com.consultoraestrategia.ss_crmeducativo.grouplist.GroupListPresenter;
import com.consultoraestrategia.ss_crmeducativo.grouplist.GroupListPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.grouplist.GroupListView;
import com.consultoraestrategia.ss_crmeducativo.grouplist.adapter.GroupListAdapter;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.GroupRepository;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.local.GroupLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.data.source.remote.GroupRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase.EliminarGrupoList;
import com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase.GetGroup;
import com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase.GetGroups;
import com.consultoraestrategia.ss_crmeducativo.grouplist.domain.usecase.SaveSeleccionGroup;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.Group;
import com.consultoraestrategia.ss_crmeducativo.grouplist.entities.TipoGrupoUi;
import com.consultoraestrategia.ss_crmeducativo.grouplist.filterChooser.CallbackFilterChooserBottomDialog;
import com.consultoraestrategia.ss_crmeducativo.grouplist.filterChooser.FilterChooserBottomSheetDialog;
import com.consultoraestrategia.ss_crmeducativo.grouplist.listener.GroupListener;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SimpleSyncIntenService;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Jse on 14/01/2019.
 */

public class ListaGrupoFragment extends Fragment implements GroupListView, GroupListener, View.OnClickListener, CallbackFilterChooserBottomDialog {
    private static final String ARG_ID_CARGA_CURSO = "cargaCursoId";
    private static final String ARG_ID_CURSO = "cursoId";
    private static final String ARG_ID_CARGA_ACADEMICA = "idCargaAcademica";
    private static final String ARG_ID_PROGRAMA_EDUCATIVO = "idProgramaEducativo";
    private static final String ARG_ID_ENTIDAD = "idEntidad";
    private static final String ARG_ID_GEOREFERENCIA = "idGeoreferencia";

    private static final String TAG = ListaGrupoFragment.class.getSimpleName();
    @BindView(R.id.recyclerGroup)
    RecyclerView recycler;
    @BindView(R.id.txtEmpty)
    TextView txtEmpty;
    @BindView(R.id.filtrarGroup)
    FloatingActionButton filtrarGroup;
    Unbinder unbinder;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar3;
    RegistroGroupList listenerregistroGroup;
    GroupListAdapter adapter;
    GroupListPresenter presenter;
    AlertDialog alert;
    private Boolean buttonFilter = false;

        public static ListaGrupoFragment newInstance(Bundle args, int idCargaCurso, int idCurso, int idCargaAcademica, int idProgramaEducativo, int idEntidad, int idGeoreferencia) {
            ListaGrupoFragment fragment = new ListaGrupoFragment();
            args.putInt(ARG_ID_CARGA_CURSO, idCargaCurso);
            args.putInt(ARG_ID_CURSO, idCurso);
            args.putInt(ARG_ID_CARGA_ACADEMICA, idCargaAcademica);
            args.putInt(ARG_ID_ENTIDAD, idEntidad);
            args.putInt(ARG_ID_GEOREFERENCIA, idGeoreferencia);
            args.putInt(ARG_ID_PROGRAMA_EDUCATIVO, idProgramaEducativo);
            fragment.setArguments(args);
            return fragment;
        }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegistroGroupList) {
            listenerregistroGroup = (RegistroGroupList) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerregistroGroup = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setupPresenter();
        if(listenerregistroGroup!=null)listenerregistroGroup.onCancelarGroupList();
    }



    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupPresenter() {
        GroupRepository repository = GroupRepository.getInstance(
                new GroupLocalDataSource(
                        InjectorUtils.provideGrupoDeEquipoDao(), InjectorUtils.provideCursoDao(),
                        InjectorUtils.provideParametrosDisenioDao()
                ),
                new GroupRemoteDataSource());
        presenter = new GroupListPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new GetGroups(repository),
                new EliminarGrupoList(repository),
                new GetGroup(repository), new SaveSeleccionGroup(repository));
        setPresenter(presenter);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_grouplist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setupCreateView();
        presenter.onViewCreated();
        presenter.onViewFilterFragment();
        filtrarGroup.setOnClickListener(this);
    }

    private void setupCreateView() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GroupListAdapter(new ArrayList<>(), this,
                AnimationUtils.loadAnimation(getContext(), R.anim.slide_up),
                AnimationUtils.loadAnimation(getContext(), R.anim.slide_down));
        recycler.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroy();

    }

    @Override
    public void showGroupList(List<Object> cursoGrupos, Boolean tipoitem, String  cargaCursoId) {
        adapter.setItems(cursoGrupos, tipoitem, cargaCursoId);
    }

    @Override
    public void showProgress() {
        progressBar3.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar3.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(int message) {
        Snackbar.make(recycler, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showTextEmpty() {
        txtEmpty.setText("No hay Registros");
        txtEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTextEmpty() {
        txtEmpty.setVisibility(View.GONE);
    }

    @Override
    public void updateList(Object object) {
           adapter.updateGroup(object);
    }

    @Override
    public void launchCreateTeamListActivity(String idCargaCurso, String idCurso, String groupoEquipoId, int tipoCreacionGrupo, Boolean tipoitem, int tipoCreacionGrupoId, int entidadId, int georeferenciaId, int programaEducativoId) {
        CreateTeamListActivity.startCreateTeamListActivity(getActivity(),getArguments(), groupoEquipoId, idCurso, idCargaCurso, tipoCreacionGrupo, tipoitem, tipoCreacionGrupoId, entidadId, georeferenciaId, programaEducativoId);
    }

    @Override
    public void onExportarGrupoList(int programaEducativoId) {
        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.GRUPO);
        SimpleSyncIntenService.start(getContext(), programaEducativoId);
        CMRE.saveNotifyChangeDataBase(getContext());
    }

    @Override
    public void onFilterListGrupos() {
        presenter.onFilterListGrupos();
    }

    @Override
    public void salirActualizar() {
            Log.d(TAG, "salirActualizar");
        if (alert != null) alert.cancel();
        if(listenerregistroGroup!=null)listenerregistroGroup.onSaveGroupList();
    }

    @Override
    public void setPresenter(GroupListPresenter presenter) {
        int mIdCargaCurso = getArguments().getInt(ARG_ID_CARGA_CURSO);
        int mIdCurso = getArguments().getInt(ARG_ID_CURSO);
        int mIdCargaAcademica = getArguments().getInt(ARG_ID_CARGA_ACADEMICA, 0);
        int mIdProgramaEducativo = getArguments().getInt(ARG_ID_PROGRAMA_EDUCATIVO, 0);
        int mIdEntidad = getArguments().getInt(ARG_ID_ENTIDAD, 0);
        int mIdGeoreferencia = getArguments().getInt(ARG_ID_GEOREFERENCIA, 0);
        String mIdRubroEvaluacion = getArguments().getString("rubroEvaluacionId", null);
        presenter.attachView(this);
        presenter.setExtras(mIdCargaCurso, mIdCurso, mIdCargaAcademica, mIdRubroEvaluacion, mIdProgramaEducativo, mIdEntidad, mIdGeoreferencia);
        presenter.onCreate();
    }

    @Override
    public void onGroupSelected(Group group) {
        Log.d(TAG, "onGroupSelected");
        //listener.onGroupSelected(group);
        if (presenter != null) presenter.onGroupSelected(group);
    }

    @Override
    public void onOptionSelect(View view, final Group group) {
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(getContext(), view);
        DroppyMenuPopup droppyMenu = droppyBuilder
                //.fromMenu(R.menu.droppy)
                .addMenuItem(new DroppyMenuItem("Eliminar"))
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int positionMenu) {
                        switch (positionMenu) {
                            case 0:
                                showDialogDeleteGroup(group);
                                break;
                        }

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

    private void showDialogDeleteGroup(final Group group) {
        CharSequence message= getResources().getString(R.string.title_delete_group);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.onClickEliminarListaGrupos(group);
                        dialogInterface.dismiss();

                    }
                })
                .setNegativeButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onCheckSelected(Group group) {
        presenter.onChecketGrupo(group);
    }

    @Override
    public void onClickSaveGrupoSesion() {
        presenter.onClickSaveGrupoSesion();
    }

    public void onResumeFragment() {
        Log.d(TAG,"onResumeFragment");
        presenter.onResumeFragment();
    }

    public void onParentFabClickedCrearGrupo(boolean isAprendizajeColegio) {
            Log.d(TAG, "onParentFabClickedCrearGrupo");
        presenter.onParentFabClickedCrearGrupo(isAprendizajeColegio);
    }

    @Override
    public void showDialogEspera() {
            if(getContext() !=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogAppTheme);
                builder.setMessage(R.string.msg_espera);
                builder.setCancelable(false);
                alert = builder.create();
                alert.show();
            }
    }

    @Override
    public void showButtonFilter() {
        filtrarGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideButtonFilter() {
        filtrarGroup.setVisibility(View.GONE);
    }

    @Override
    public void hideRecyclerView() {

    }

    @Override
    public void showRecyclerView() {

    }

    @Override
    public void initFabButtom(Boolean tipoListaGrupos) {
        rotateFabForward();
    }

    @Override
    public void showTextEmptyTipoTwo() {
        txtEmpty.setText("No hay lista de grupos");
        txtEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {
        txtEmpty.setText("Rubrica o rubro para grupos: Debe crear una lista de grupos");
        txtEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearGroupList() {
            adapter.clearList();
    }

    @Override
    public void showListFilterChooser(String seleccione, TipoGrupoUi[] values) {
        Log.d(TAG,"showListFilterChooser");
        FilterChooserBottomSheetDialog filterChooserBottomSheetDialog = new FilterChooserBottomSheetDialog();
        filterChooserBottomSheetDialog.setDialogTitle(seleccione);
        filterChooserBottomSheetDialog.setItems(values);
        filterChooserBottomSheetDialog.setCallbackFilter(this);
        filterChooserBottomSheetDialog.show(getFragmentManager(), "FilterChooserBottomSheetDialog");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.filtrarGroup:
                presenter.onFilterListGrupos();
                changeFilter();
                break;
        }

    }

    private void changeFilter() {
        if(buttonFilter){
            rotateFabForward();
            buttonFilter=false;
        }else {
            rotateFabBackward();
            buttonFilter=true;
        }
    }


    public void rotateFabForward() {
        Log.d(TAG, "true");
        ViewCompat.animate(filtrarGroup)
                .rotation(180f)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
    }

    public void rotateFabBackward() {
        Log.d(TAG, "false");
        ViewCompat.animate(filtrarGroup)
                .rotation(0f)
                .withLayer()
                .setDuration(300L)
                .setInterpolator(new OvershootInterpolator(10.0F));
    }

    @Override
    public void onItemClickTipoGrupo(TipoGrupoUi tipoGrupoUi) {
        presenter.onItemClickTipoGrupo(tipoGrupoUi);
    }


    public interface RegistroGroupList {
        void onSaveGroupList();

        void onCancelarGroupList();
    }


}

