package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.TableViewAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.TableViewEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.cell.EvaluacionCellViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.adapter.holder.column.RubroEvaluacionColumnViewHolder;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.local.EvalRubBidLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.remote.EvalRubBidRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.AlumnoProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.PublicarEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubEvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.Cell;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.ColumnHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.tableView.RowHeader;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.EvalRubBidIndPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.EvalRubBidIndPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.adapter.ArchivoComentarioColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.adapter.ComentarioPredeAdapter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.dialogComentario.EvalRubBidComPred;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.listener.EvalRubBidIndListener;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.repositorioComentario.RepositorioArchivoRubroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.evalRubBidInd.repositorioComentario.RepositorioArchivoViewImpl;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.grupal.ui.EvaluacionBimencionalGrupalActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.individual.ui.EvaluacionBimencionalActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.plantilla.ui.EvaluacionBimencionalAbstractActividad;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.DeleteArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.DeleteComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetComentarioPred;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.GetPublicacionEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.SaveArchivoRubro;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.SaveComentario;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase.UpdatePublicacionEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.listener.PresicionListener;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.PresicionDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositorioAdapter;
import com.consultoraestrategia.ss_crmeducativo.repositorio.adapter.RepositoriotemDecoration;
import com.consultoraestrategia.ss_crmeducativo.repositorio.bundle.RepositorioTBunble;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioRepository;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.FragmentoTipo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemUpdateListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.CloneImagenCompress;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.ConvertirPathRepositorioUpload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.GetUrlRepositorioArchivo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorio;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.Tutorial;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.iceteck.silicompressorr.SiliCompressor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;

/**
 * Created by @stevecampos on 28/02/2018.
 */

public class EvalRubBidIndFragment extends BaseDialogFragment<EvalRubBidIndView, EvalRubBidIndPresenter, EvalRubBidIndListener> implements EvalRubBidIndView, PresicionListener, View.OnClickListener, ITableViewListener, TextWatcher, ComentarioPredeAdapter.ArchivoComentarioListener, RepositorioItemListener, RepositorioItemUpdateListener {
    public static final String TAG = EvalRubBidIndFragment.class.getSimpleName();
    private static final int CUSTOM_REQUEST_CODE = 423;
    @BindView(R.id.text_alumn_name)
    TextView textAlumnName;
    @BindView(R.id.img_alumn_profile)
    CircleImageView imgAlumnProfile;
    @BindView(R.id.edt_puntos)
    TextInputEditText edtPuntos;
    @BindView(R.id.til_puntos)
    TextInputLayout tilPuntos;
    @BindView(R.id.edt_nota)
    TextInputEditText edtNota;
    @BindView(R.id.til_nota)
    TextInputLayout tilNota;
    @BindView(R.id.edt_desempenio)
    TextInputEditText edtDesempenio;
    @BindView(R.id.til_desempenio)
    TextInputLayout tilDesempenio;
    @BindView(R.id.edt_logro)
    TextInputEditText edtLogro;
    @BindView(R.id.til_logro)
    TextInputLayout tilLogro;
    @BindView(R.id.btnFinalizar)
    Button btnFinalizar;
    @BindView(R.id.btnRetroceder)
    Button btnRetroceder;
    @BindView(R.id.btnAvanzar)
    Button btnAvanzar;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.txt_contador)
    TextView txtContador;
    EvalRubBidComPred evalRubBidComPred;
    @BindView(R.id.text_alumn_lastname)
    TextView textAlumnLastname;
    @BindView(R.id.btn_change)
    ImageView btnChange;
    @BindView(R.id.btn_clear)
    ImageView btnClear;
    @BindView(R.id.table)
    TableViewEvaluacion table;
    @BindView(R.id.btnEnviar)
    ImageView btnEnviar;
    @BindView(R.id.inputComentario)
    EditText inputComentario;
    @BindView(R.id.content_comentario)
    ConstraintLayout contentComentario;
    @BindView(R.id.recycomentarios)
    RecyclerView recycomentarios;
    @BindView(R.id.btn_add_evidencias)
    Button btnAddEvidencias;
    @BindView(R.id.rec_rubro_archivo)
    RecyclerView recRubroArchivo;
    @BindView(R.id.btnMsgPre)
    ImageView btnMsgPre;
    @BindView(R.id.cont_archivo)
    FrameLayout contArchivo;
    @BindView(R.id.temp)
    ConstraintLayout temp;
    @BindView(R.id.textView66)
    TextView textView66;
    @BindView(R.id.group2)
    Group group;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    Unbinder unbinder;
    @BindView(R.id.btn_publicar)
    ImageView btnPublicar;

    private RepositorioArchivoRubroPresenterImpl baseRepositorioPresenter;
    private ComentarioPredeAdapter comentarioPredeAdapter;
    private RepositorioAdapter repositorioAdapter;
    private TableViewAdapter adapter;
    private RepositorioArchivoViewImpl repositorioViewImpl;
    private boolean initTutorial;
    private RepositoriotemDecoration repositorioItemDecoration;

    public static EvalRubBidIndFragment newInstance(Bundle bundle) {
        EvalRubBidIndFragment frag = new EvalRubBidIndFragment();
        frag.setArguments(bundle);
        return frag;
    }


    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    protected EvalRubBidIndPresenter getPresenter() {

        RepositorioRepository repositorioRepository = new RepositorioRepository(new RepositorioLocalDataSource(),
                new RepositorioPreferentsDataSource(),
                new RepositorioRemoteDataSource(ApiRetrofit.getInstance()));

        baseRepositorioPresenter = new RepositorioArchivoRubroPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new DowloadImageUseCase(repositorioRepository),
                new ConvertirPathRepositorioUpload(),
                new UploadRepositorio(repositorioRepository),
                new GetUrlRepositorioArchivo(repositorioRepository),
                new UpdateRepositorio(repositorioRepository, SiliCompressor.with(getContext())),
                new CloneImagenCompress(SiliCompressor.with(getContext()), getContext()));


        EvalRubBidRepository evalRubBidRepository = new EvalRubBidRepository(new EvalRubBidLocalDataSource(), new EvalRubBidRemoteDataSource());
        return new EvalRubBidIndPresenterImpl(
                new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(), new GetComentarioPred(evalRubBidRepository), new SaveComentario(evalRubBidRepository),
                new DeleteComentario(evalRubBidRepository),
                new GetArchivoComentario(evalRubBidRepository),
                new SaveArchivoRubro(evalRubBidRepository),
                new DeleteArchivoComentario(evalRubBidRepository),
                new GetPublicacionEvaluacion(evalRubBidRepository),
                new UpdatePublicacionEvaluacion(evalRubBidRepository)
        );

    }

    @Override
    protected EvalRubBidIndView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_eval_rubrica_bidimencionale_grupales, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getDialog() != null && getDialog().getWindow() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getDialog().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getDialog().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getDialog().getWindow().setStatusBarColor(Color.parseColor("#FAFAFA"));
            }
        }

        View view = super.onCreateView(inflater, container, savedInstanceState);
        btnMsgPre.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);
        setFocus(view);
        temp.requestFocus();
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    public void setFocus(View view) {
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setFocusable(false);
        } else if (view instanceof ViewGroup) {
            int size = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < size; i++) {
                setFocus(((ViewGroup) view).getChildAt(i));
            }
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        initEditText();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        repositorioViewImpl = new RepositorioArchivoViewImpl(this, presenter);
        setRepositorioAchivoView(repositorioViewImpl);
        repositorioViewImpl.setPresenter(baseRepositorioPresenter);
        baseRepositorioPresenter.onViewCreated();
        RepositorioTBunble tBunble = new RepositorioTBunble();
        tBunble.setRepositorio(RepositorioUi.ARCHIVO_RUBRO);
        tBunble.setFragmentoTipo(FragmentoTipo.SUBIDA_DESCARGA_ARCHIVOS_VINCULOS);
        //tBunble.setColorCurso();
        baseRepositorioPresenter.setExtras(tBunble.getBundle());
    }

    private void initEditText() {
        inputComentario.clearFocus();
        inputComentario.addTextChangedListener(this);
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycomentarios.setLayoutManager(layoutManager);
        comentarioPredeAdapter = new ComentarioPredeAdapter(this);
        recycomentarios.setAdapter(comentarioPredeAdapter);
        recycomentarios.setNestedScrollingEnabled(false);


        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        ArchivoComentarioColumnCountProvider columnCountProvider = new ArchivoComentarioColumnCountProvider(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recRubroArchivo.setLayoutManager(autoColumnGridLayoutManager);
        repositorioAdapter = new RepositorioAdapter(this, this, recRubroArchivo, true);
        recRubroArchivo.setAdapter(repositorioAdapter);
        recRubroArchivo.setHasFixedSize(true);
        recRubroArchivo.setNestedScrollingEnabled(false);


        if(repositorioItemDecoration==null){
            repositorioItemDecoration = new RepositoriotemDecoration(700);
            recRubroArchivo.addItemDecoration(repositorioItemDecoration);
        }
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @OnClick({R.id.btnFinalizar, R.id.btnRetroceder, R.id.btnAvanzar, R.id.btn_change, R.id.btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnFinalizar:
                presenter.onFinalizar();
                break;
            case R.id.btnRetroceder:
                presenter.onRetroceder();
                break;
            case R.id.btnAvanzar:
                presenter.onAvanzar();
                break;
            case R.id.btn_change:
                presenter.onClickBtnChange();
                break;
            case R.id.btn_clear:
                confimationOutDialog();
                break;
        }
    }

    private void confimationOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        TextView text = new TextView(getActivity());
        text.setPadding(30, 30, 30, 0);
        text.setText("¿Seguro que desea limpiar todas las notas?");
        text.setGravity(Gravity.CENTER);
        builder.setView(text)
                .setTitle("Mensaje de Confirmación")
                .setCancelable(false)
                .setNegativeButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(com.consultoraestrategia.ss_crmeducativo.core2.R.string.global_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        presenter.onClickBtnClear();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showAlumnoNombre(String nombre, String lastName) {
        textAlumnName.setText(nombre);
        textAlumnLastname.setText(lastName);
    }

    @Override
    public void showAlumnoProfile(String urlProfile) {

        Glide.with(this)
                .load(urlProfile)
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle))
                .into(imgAlumnProfile);
    }

    @Override
    public void showPuntos(String puntos) {
        Log.d(TAG, "showPuntos");
        edtPuntos.setText(puntos);
    }

    @Override
    public void showNota(double nota) {
        Log.d(TAG, "showNota");
        edtNota.setText(String.valueOf(nota));
    }

    @Override
    public void showDesempenio(String desempenio) {
        Log.d(TAG, "showDesempenio");
        edtDesempenio.setText(desempenio);
    }

    @Override
    public void showLogro(String logro) {
        edtLogro.setText(logro);
    }


    @Override
    public void salirDialogo() {
        dismiss();
    }

    @Override
    public void evaluarAlumno(RubBidUi rubBidUi, AlumnoProcesoUi alumnoProcesoUi, ValorTipoNotaUi valorTipoNotaUi) {

    }

    @Override
    public void changeContadorPagina(int inicio, int pocision) {
        txtContador.setText(String.valueOf(inicio + "/" + pocision));
    }

    @Override
    public void callActividad(ColumnHeader evaluado, EvalProcUi evalProcUi, List<EvalProcUi> evalProcUiList) {
        if (getActivity() instanceof EvaluacionBimencionalGrupalActividad) {
            Log.d(getTag(), "EvaluacionBimencionalGrupalActividad");
            EvaluacionBimencionalGrupalActividad evaluacionBimencionalGrupalActividad = (EvaluacionBimencionalGrupalActividad) getActivity();
            evaluacionBimencionalGrupalActividad.saveEvaluacionVidimencional(evaluado, evalProcUi, evalProcUiList);
        } else if (getActivity() instanceof EvaluacionBimencionalActividad) {
            Log.d(getTag(), "EvaluacionBimencionalActividad");
            EvaluacionBimencionalActividad evaluacionBimencionalActividad = (EvaluacionBimencionalActividad) getActivity();
            evaluacionBimencionalActividad.saveEvaluacionVidimencional(evaluado, evalProcUi, evalProcUiList);
        }
    }

    @Override
    public void callActividadTableNotifyDataSetChanged() {
        if (getActivity() instanceof EvaluacionBimencionalGrupalActividad) {
            Log.d(getTag(), "EvaluacionBimencionalGrupalActividad");
            EvaluacionBimencionalGrupalActividad evaluacionBimencionalGrupalActividad = (EvaluacionBimencionalGrupalActividad) getActivity();
            evaluacionBimencionalGrupalActividad.tableNotifyDataSetChanged();
        } else if (getActivity() instanceof EvaluacionBimencionalActividad) {
            Log.d(getTag(), "EvaluacionBimencionalActividad");
            EvaluacionBimencionalActividad evaluacionBimencionalActividad = (EvaluacionBimencionalActividad) getActivity();
            evaluacionBimencionalActividad.tableNotifyDataSetChanged();
        }
    }

    @Override
    public void showAlertDialog(RubEvalProcUi rubEvalProcUi) {
        try {
            EvaluacionBimencionalAbstractActividad bimencionalAbstractActividad = (EvaluacionBimencionalAbstractActividad) getActivity();
            bimencionalAbstractActividad.onClickRubroEvaluacion(rubEvalProcUi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callServiceNotifyDataSetChanged() {

    }


    @Override
    public void onShowDialogPresicion(double nota, String rubroEvaluacionId, String valorTipoNotaId, String color, boolean validar) {
        if (validar) {
            PresicionDialogFragment infoRubroFragment = PresicionDialogFragment.newInstance(nota, rubroEvaluacionId, valorTipoNotaId, color);
            infoRubroFragment.setTargetFragment(EvalRubBidIndFragment.this, 200);
            infoRubroFragment.show(getFragmentManager(), PresicionDialogFragment.class.getSimpleName());
        } else {
            Toast.makeText(getContext(), "Presicion Unica",
                    Toast.LENGTH_SHORT).show();
        }

    }


    private int convertDpToPx(int dp) {
        return Math.round(dp * (getResources().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));

    }

    @Override
    public void onStart() {
        super.onStart();
        this.getDialog().getWindow()
                .setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        this.getDialog().getWindow().
                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onSuccessNota(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId) {
        presenter.onSelectPrecicionEvaluacion(notaAnterior, notaActual, valorTipoNotaId, rubroEvaluacionId);
    }

    @Override
    public void changeEyeSimple() {
        btnChange.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_eeye));
    }

    @Override
    public void changerEyeFocus() {
        btnChange.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_vision));
    }


    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }


    @Override
    public void showDialogMsgPred() {
        evalRubBidComPred = new EvalRubBidComPred();
        evalRubBidComPred.show(getChildFragmentManager(), EvalRubBidIndFragment.class.getSimpleName());
    }

    @Override
    public void setData(ColumnHeader columnHeader, RubBidUi rubBidUi, List<ColumnHeader> columnHeaders) {
        presenter.setData(columnHeader, rubBidUi, columnHeaders);
    }

    @Override
    public void showPickPhoto(boolean enableVideo) {
        FilePickerBuilder filePickerBuilder = FilePickerBuilder.Companion.getInstance()
                //.setSelectedFiles(stringList)
                .setActivityTheme(R.style.LibAppThemeLibrary)
                //.setActivityTitle("Selección de multimedia")
                .enableVideoPicker(enableVideo)
                .enableCameraSupport(true)
                .showGifs(true)
                .showFolderView(true)
                //.enableSelectAll(false)
                .enableImagePicker(true)
                .setMaxCount(1);
                //.setCameraPlaceholder(R.drawable.custom_camera)
                //.withOrientation(Orientation.UNSPECIFIED);
        filePickerBuilder.pickPhoto(this, CUSTOM_REQUEST_CODE);
    }

    @Override
    public void onCleanSelector() {

    }

    @Override
    public void showTabComentario() {
        group.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTabComentario() {
        group.setVisibility(View.GONE);
    }

    @Override
    public void setPublicar(PublicarEvaluacionUi publicarEvaluacionUi) {
        if (publicarEvaluacionUi.isSelected()) {
            btnPublicar.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada_message));
        }else {
            btnPublicar.setImageDrawable(ContextCompat.getDrawable(root.getContext(), R.drawable.ic_tarea_publicada));
        }
    }

    @Override
    public void showBtnClean(boolean disabledEval) {
        if (disabledEval) btnClear.setVisibility(View.VISIBLE);
        else btnClear.setVisibility(View.GONE);
    }

    @Override
    public void hidePublicar() {
        btnPublicar.setVisibility(View.GONE);
    }

    @Override
    public void showPublicar() {
        btnPublicar.setVisibility(View.VISIBLE);
    }

    @Override
    public void notiftyDataBaseChange() {
        Log.d(TAG,"notiftyDataBaseChange");
        CMRE.saveNotifyChangeDataBase(getContext());
    }


    @Override
    public void onResume() {
        super.onResume();
        baseRepositorioPresenter.onResume();
        // single example
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        baseRepositorioPresenter.onDestroyView();
        unbinder.unbind();
    }


    public List<RepositorioFileUi> getListFiles() {
        return baseRepositorioPresenter.getListFiles();
    }

    @Override
    public void showTableModoAvanzado(String titulo, List<ColumnHeader> headerList, List<RowHeader> rows, List<List<Cell>> bodyList, boolean disabledEval) {
        showTableview(titulo, headerList, rows, bodyList, disabledEval, true);
    }

    @Override
    public void showTableModoSimple(String titulo, List<ColumnHeader> headerList, List<RowHeader> rows, List<List<Cell>> bodyList, boolean disabledEval) {
        showTableview(titulo, headerList, rows, bodyList, disabledEval, false);
    }

    private void showTableview(final String titulo, final List<ColumnHeader> headerList, final List<RowHeader> rows, final List<List<Cell>> bodyList, final boolean disabledEval, final boolean diseniotablaAlterno) {
        Log.d(TAG, "showTableview " + rows.size() + "," + headerList.size() + "," + bodyList.size());
        if (adapter == null) {
            adapter = new TableViewAdapter(getContext(), this);
        }

        table.setAdapter(adapter);
        table.setIgnoreSelectionColors(false);
        table.setHasFixedWidth(true);
        table.setIgnoreSelectionColors(true);

        if (!disabledEval) {
            table.setTableViewListener(this);
        }
        adapter.setDiseniotablaAlterno(diseniotablaAlterno);
        adapter.setAllItems(rows, headerList, bodyList);
        int row_header_width = (int) getResources().getDimension(R.dimen.table_firstheader_width_eval_session);
        int row_header_height = (int) getResources().getDimension(R.dimen.table_body_height_eval_session);
        adapter.setCornerView(titulo, row_header_width, row_header_height);
        /*if (!initTutorial) {
            initTutorial = true;
            table.post(new Runnable() {
                @Override
                public void run() {
                    initTutorial(false);
                }
            });
        }*/

    }

    private void initTutorial(boolean notPreferents) {
        Tutorial.showTutorialEvaluacionUno(EvalRubBidIndFragment.this, scroll, table.getRowHeaderRecyclerView(), table.getCellRecyclerView(), table.getColumnHeaderRecyclerView(), btnClear, btnChange, inputComentario, btnAddEvidencias, notPreferents);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMsgPre:
                presenter.showDialogMsgPred();
                break;
            case R.id.btnEnviar:
                presenter.saveComentario(String.valueOf(inputComentario.getText()));
                break;
        }
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder holder, int p_nXPosition, int p_nYPosition) {
        if (holder instanceof EvaluacionCellViewHolder) {
            EvaluacionCellViewHolder evaluacionCellViewHolder = (EvaluacionCellViewHolder) holder;
            TipoNotaUi tipoNotaUi = evaluacionCellViewHolder.getTipoNotaUi();
            if (tipoNotaUi == null) return;
            EvaluacionCellViewHolder deSelectEvaluacionCellViewHolder = (EvaluacionCellViewHolder) tipoNotaUi.getSelectCell();
            if (evaluacionCellViewHolder.equals(deSelectEvaluacionCellViewHolder)) {
                presenter.onLongEvaluacion(tipoNotaUi, evaluacionCellViewHolder.getValorTipoNotaUi());
                Log.d(TAG, "onDeSelectEvaluacion 1");
            } else if (deSelectEvaluacionCellViewHolder != null) {
                deSelectEvaluacionCellViewHolder.deSelectionColors();
                evaluacionCellViewHolder.SelectionColors();
                presenter.onDeSelectEvaluacion(evaluacionCellViewHolder.getValorTipoNotaUi(), deSelectEvaluacionCellViewHolder.getValorTipoNotaUi(), tipoNotaUi);
                Log.d(TAG, "onDeSelectEvaluacion 2");
            } else {
                evaluacionCellViewHolder.SelectionColors();

                if ((!evaluacionCellViewHolder.getValorTipoNotaUi().equals(tipoNotaUi.getSelectValorTipoNotaUi())) && tipoNotaUi.getSelectValorTipoNotaUi() != null) {
                    presenter.onDeSelectEvaluacion(evaluacionCellViewHolder.getValorTipoNotaUi(), tipoNotaUi.getSelectValorTipoNotaUi(), tipoNotaUi);
                    Log.d(TAG, "onDeSelectEvaluacion 3");
                } else {
                    presenter.onSelectEvaluacion(evaluacionCellViewHolder.getValorTipoNotaUi(), tipoNotaUi);
                    Log.d(TAG, "onSelectEvaluacion");
                }
            }
            evaluacionCellViewHolder.reCalcular();
        }

    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder holder, int column, int row) {

        if (holder instanceof EvaluacionCellViewHolder) {
            EvaluacionCellViewHolder evaluacionCellViewHolder = (EvaluacionCellViewHolder) holder;
            TipoNotaUi tipoNotaUi = evaluacionCellViewHolder.getTipoNotaUi();
            if (tipoNotaUi == null) return;
            EvaluacionCellViewHolder deSelectEvaluacionCellViewHolder = (EvaluacionCellViewHolder) tipoNotaUi.getSelectCell();
            if (evaluacionCellViewHolder.equals(deSelectEvaluacionCellViewHolder)) {
                deSelectEvaluacionCellViewHolder.deSelectionColors();
                presenter.onDeSelectEvaluacion(evaluacionCellViewHolder.getValorTipoNotaUi(), tipoNotaUi);
                Log.d(TAG, "onDeSelectEvaluacionLong 1");
            } else if (deSelectEvaluacionCellViewHolder != null) {
                Log.d(TAG, "onDeSelectEvaluacionLong 2");
            } else {
                if ((!evaluacionCellViewHolder.getValorTipoNotaUi().equals(tipoNotaUi.getSelectValorTipoNotaUi())) && tipoNotaUi.getSelectValorTipoNotaUi() != null) {
                    Log.d(TAG, "onDeSelectEvaluacionLong 3");
                } else {
                    Log.d(TAG, "onSelectEvaluacionLong");
                }
            }
            evaluacionCellViewHolder.reCalcular();
        }
    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jColumnHeaderView, int p_nXPosition) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder holder, int p_nYPosition) {
        if (holder instanceof RubroEvaluacionColumnViewHolder) {
            RubroEvaluacionColumnViewHolder rubroEvaluacionColumnViewHolder = (RubroEvaluacionColumnViewHolder) holder;
            presenter.onClickRubroEvaluacion(rubroEvaluacionColumnViewHolder.getRubEvalProcUi());
        }
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder p_jRowHeaderView, int p_nYPosition) {

    }

    @Override
    public void updateCellTableview(List<List<Cell>> bodyList) {
        adapter.setCellItems(bodyList);
    }

    @Override
    public void cleanCellTableview(List<ColumnHeader> columnHeaderList) {
        for (ColumnHeader columnHeader : columnHeaderList) {
            if (columnHeader instanceof RubEvalProcUi) {
                RubEvalProcUi rubEvalProcUi = (RubEvalProcUi) columnHeader;
                if (rubEvalProcUi.getTipoNotaUi().getSelectValorTipoNotaUi() != null) {
                    EvaluacionCellViewHolder deSelectEvaluacionCellViewHolder = (EvaluacionCellViewHolder) rubEvalProcUi.getTipoNotaUi().getSelectCell();
                    deSelectEvaluacionCellViewHolder.deSelectionColors();
                    presenter.onDeSelectEvaluacion(rubEvalProcUi.getTipoNotaUi().getSelectValorTipoNotaUi(), rubEvalProcUi.getTipoNotaUi());
                } else Log.d(TAG, "onDeSelectEvaluacionLong :3");
            }
        }

    }

    @Override
    public void showListComentarios(List<MensajeUi> mensajeUiList) {
        comentarioPredeAdapter.setMensajesList(mensajeUiList);
    }

    @Override
    public void showBtnSendComentario() {
        if (btnEnviar.getVisibility() == View.INVISIBLE) {
            btnEnviar.setVisibility(View.VISIBLE);
            btnEnviar.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_up));
        }

    }

    @Override
    public void hideBtnSendComentario() {
        if (btnEnviar.getVisibility() == View.VISIBLE) {
            btnEnviar.setVisibility(View.INVISIBLE);
            btnEnviar.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_down));
        }
    }

    @Override
    public void showListComentariosArchivos(List<ArchivoComentarioUi> objects) {
        repositorioViewImpl.changeList(new ArrayList<RepositorioFileUi>(objects));
    }

    @Override
    public void clearTextInpuComentario() {
        inputComentario.setText(null);
        inputComentario.setSelected(false);
        inputComentario.clearFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputComentario.getWindowToken(), 0);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        presenter.onTextChangedEditComentario(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClickComentarioNormal(MensajeUi mensajeUi) {
        presenter.onClickComentarioNormal(mensajeUi);
    }

    @Override
    public void onClickDownload(RepositorioFileUi repositorioFileUi) {
        repositorioViewImpl.onClickDownload(repositorioFileUi);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        repositorioViewImpl.onClickClose(repositorioFileUi);
    }

    @Override
    public void onClickCheck(RepositorioFileUi repositorioFileUi) {
        repositorioViewImpl.onClickCheck(repositorioFileUi);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        repositorioViewImpl.onClickArchivo(repositorioFileUi);
    }

    @Override
    public void onClickUpload(UpdateRepositorioFileUi updateRepositorioFileUi) {
        repositorioViewImpl.onClickUpload(updateRepositorioFileUi);
    }

    @Override
    public void onClickRemover(UpdateRepositorioFileUi updateRepositorioFileUi) {
        repositorioViewImpl.onClickRemover(updateRepositorioFileUi);
    }

    @Override
    public void onClickClose(UpdateRepositorioFileUi updateRepositorioFileUi) {
        repositorioViewImpl.onClickClose(updateRepositorioFileUi);
    }

    @Override
    public void onClickArchivo(UpdateRepositorioFileUi updateRepositorioFileUi) {
        repositorioViewImpl.onClickArchivo(updateRepositorioFileUi);
    }


    public void setRepositorioAchivoView(RepositorioArchivoViewImpl repositorioViewImpl) {
        this.repositorioViewImpl = repositorioViewImpl;
        this.repositorioViewImpl.setAdapterArchivo(repositorioAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        repositorioViewImpl.onActivityResult(requestCode, resultCode, data, getContext());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repositorioViewImpl.destroy();
    }

    @OnClick(R.id.btn_add_evidencias)
    public void onClickAddMultimedia() {
        repositorioViewImpl.onClickAddMultimedia();
    }

    @Override
    public void onConfigurationChanged(Configuration myConfig) {
        super.onConfigurationChanged(myConfig);
        try {
            int orientation = getResources().getConfiguration().orientation;
            Log.d("CHANGESCREEN", "Orientation: " + orientation);
            switch (orientation) {
                case Configuration.ORIENTATION_LANDSCAPE:
                    // Con la orientación en horizontal actualizamos el adaptador
                    table.refreshColum();
                    adapter.notify();
                    break;
                case Configuration.ORIENTATION_PORTRAIT:
                    // Con la orientación en vertical actualizamos el adaptador
                    adapter.notify();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @OnClick(R.id.img_eval_rubrica_help)
    public void onViewClicked() {
        initTutorial(true);
    }

    @OnClick(R.id.btn_publicar)
    public void onClickedBtnPublicar() {
        presenter.btnPublicar();
    }
}
