package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.EvaluacionResultadoPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.TableAdapterEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.adapter.holders.FilaAlumnosHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.entity.RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.ui.InfoRubroFragment;
import com.consultoraestrategia.ss_crmeducativo.infoUsuario.InfoUsuarioFragment;
import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableview.listener.ITableViewListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class EvaluacionResultadoAbstractFragment<T extends Fragment, P extends EvaluacionResultadoPresenter> extends Fragment implements EvaluacionResultadoView , ITableViewListener{

    private static final String TAG = EvaluacionResultadoAbstractFragment.class.getSimpleName();

    TableView tableEvaluacion;
    ProgressBar progressBar;

    TextView txtEmpty;
    Unbinder unbinder1;

    private TableAdapterEvaluacion tableAdapterEvaluacion;
    public static final String ARG_ID_RUBRO_EVAL_RESULTADO = "rubroEvalResultadoId";
    public static final String ARG_TIPO_COMPETENCIA = "tipoCompetencia";


    public EvaluacionResultadoAbstractFragment() {
        // Required empty public constructor
    }

    protected abstract P getPresenter();

    public static <T extends Fragment> T newInstance(Class<T> fragmentClass, int idRubroEvalResultaod, int tipoCompetencia, Bundle args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, java.lang.InstantiationException {
        Log.d(TAG, "newInstance");
        Constructor<T> c = fragmentClass.getConstructor();
        T fragment = c.newInstance();
        args.putInt(ARG_ID_RUBRO_EVAL_RESULTADO, idRubroEvalResultaod);
        args.putInt(ARG_TIPO_COMPETENCIA, tipoCompetencia);
        fragment.setArguments(args);
        return fragment;
    }

    public static <T extends Fragment> T newInstance(Class<T> fragmentClass, Bundle args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, java.lang.InstantiationException {
        Log.d(TAG, "newInstance");
        Constructor<T> c = fragmentClass.getConstructor();
        T fragment = c.newInstance();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_evaluacion_resultado, container, false);
        tableEvaluacion = (TableView) view.findViewById(R.id.table);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        txtEmpty = (TextView) view.findViewById(R.id.txtEmpty);
        unbinder1 = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        initTable();
        initPresenter();

    }

    private void initTable() {
        tableAdapterEvaluacion = new TableAdapterEvaluacion(getContext());
        tableEvaluacion.setAdapter(tableAdapterEvaluacion);
        tableEvaluacion.setHasFixedWidth(false);
    }


    private EvaluacionResultadoPresenter presenter;

    private void initPresenter() {
        Log.d(TAG, "initPresenter");
        if (presenter == null) {
            presenter = getPresenter();
        }
        setPresenter(presenter);
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
        presenter.onDestroy();
        unbinder1.unbind();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");
        super.onDetach();
    }

    @Override
    public void setPresenter(EvaluacionResultadoPresenter presenter) {
        Log.d(TAG, "setPresenter");
        presenter.setExtras(getArguments());
        presenter.attachView(this);
        presenter.onCreate();
        presenter.setResources(getResources());

    }

    @Override
    public void showProgress() {
        Log.d(TAG, "showProgress");
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        Log.d(TAG, "hideProgress");
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void showTableEvaluacion(List<AlumnoUi> alumnoUis, List<RubroEvaluacionUi> rubroEvaluacionUis, List<List<Object>> celdaUiList, RubroEvaluacionUi rubroEvaluacionUi) {
        Log.d(TAG, "showTableEvaluacion");
        txtEmpty.setVisibility(View.GONE);
        int row_header_width = (int) getContext().getResources().getDimension(R.dimen.table_header_corner_evaluacion_resultado_width);
        int row_header_height = (int) getContext().getResources().getDimension(R.dimen.table_header_corner_evaluacion_resultado_height);
        tableAdapterEvaluacion.setAllItems(rubroEvaluacionUis, alumnoUis, celdaUiList);
        tableAdapterEvaluacion.setCornerView(alumnoUis.size(), row_header_width, row_header_height);
        tableEvaluacion.setIgnoreSelectionColors(true);
        tableEvaluacion.setTableViewListener(EvaluacionResultadoAbstractFragment.this);
        if(getActivity() instanceof EvaluacionResultadoActivity){
            EvaluacionResultadoActivity evaluacionResultadoActivity = (EvaluacionResultadoActivity)getActivity();
            evaluacionResultadoActivity.setTitulo(rubroEvaluacionUi.getTitle());
        }
    }


    @Override
    public void showDialogMensajes(AlumnoUi alumnoUi) {
        Log.d(TAG, "showDialogMensajes");
    }

    @Override
    public void showEmptyView(String mensaje) {

        txtEmpty.setVisibility(View.VISIBLE);
        txtEmpty.setText(String.valueOf(mensaje));
    }


    public void showError(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(R.string.dialog_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        Log.d(TAG, "onRowHeaderClicked");
        if(rowHeaderView instanceof FilaAlumnosHolder){
            presenter.showFragmentInfoAlumno(row);
        }
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void showFragmentInfoAlumno(AlumnoUi alumnoUiSeleted) {
        String titulo = alumnoUiSeleted.getName() + " " + alumnoUiSeleted.getLastName();
        InfoUsuarioFragment infoAlumnoFragment = InfoUsuarioFragment.newInstance(null, alumnoUiSeleted.getUrlPicture(), titulo, Integer.parseInt(alumnoUiSeleted.getId()), "", new CRMBundle(getArguments()));
        infoAlumnoFragment.show(getFragmentManager(), InfoRubroFragment.class.getSimpleName());

    }

}
