package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.InfoRubroPresenter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.InfoRubroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.adapters.ArchivoComentarioColumnCountProvider;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.adapters.ComentarioPredeAdapter;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.DeleteArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.DeleteComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.GetArchivoComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.GetComentarioPred;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.SaveArchivoRubro;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase.SaveComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.repositorioComentario.RepositorioArchivoRubroPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.repositorioComentario.RepositorioArchivoViewImpl;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
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
import com.consultoraestrategia.ss_crmeducativo.repositorio.listener.RepositorioItemListener;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.CloneImagenCompress;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.ConvertirPathRepositorioUpload;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.GetUrlRepositorioArchivo;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UpdateRepositorio;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.UploadRepositorio;
import com.iceteck.silicompressorr.SiliCompressor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Jse on 15/09/2018.
 */

public class InfoComentarioDialog extends BaseDialogFragment<InfoRubroView, InfoRubroPresenter,InfoRubroListener> implements InfoRubroView, View.OnClickListener, ComentarioPredeAdapter.ArchivoComentarioListener, RepositorioItemListener, TextWatcher {


    @BindView(R.id.btnEnviar)
    ImageView btnEnviar;
    @BindView(R.id.inputComentario)
    EditText inputComentario;
    @BindView(R.id.content_comentario)
    ConstraintLayout contentComentario;
    @BindView(R.id.recycomentarios)
    RecyclerView recycomentarios;
    @BindView(R.id.rec_rubro_archivo)
    RecyclerView recRubroArchivo;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.btnRetroceder)
    Button btnRetroceder;
    private InfoRubroPresenter presenter;

    public static final String ID_EVALUACION_PROCESO = "eveluacionId";
    private ComentarioPredeAdapter comentarioPredeAdapter;
    private RepositorioAdapter repositorioAdapter;
    private RepositorioArchivoViewImpl repositorioViewImpl;
    private RepositorioArchivoRubroPresenterImpl baseRepositorioPresenter;
    private RepositoriotemDecoration repositorioItemDecoration;

    public static InfoComentarioDialog newInstance(String evaluacionProcesoId) {
        Bundle args = new Bundle();
        args.putString(ID_EVALUACION_PROCESO, evaluacionProcesoId);
        InfoComentarioDialog fragment = new InfoComentarioDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        View view = super.onCreateView(inflater, container, savedInstanceState);
        btnEnviar.setOnClickListener(this);
        return view;

    }

    private void initEditText() {
        inputComentario.clearFocus();
        inputComentario.addTextChangedListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        repositorioViewImpl = new RepositorioArchivoViewImpl(this, presenter);
        setRepositorioAchivoView(repositorioViewImpl);
        repositorioViewImpl.setPresenter(baseRepositorioPresenter);
        baseRepositorioPresenter.onViewCreated();
        super.onActivityCreated(savedInstanceState);
        RepositorioTBunble tBunble = new RepositorioTBunble();
        tBunble.setRepositorio(RepositorioUi.ARCHIVO_RUBRO);
        tBunble.setFragmentoTipo(FragmentoTipo.SUBIDA_DESCARGA_ARCHIVOS_VINCULOS);
        //tBunble.setColorCurso();
        baseRepositorioPresenter.setExtras(tBunble.getBundle());
        presenter.onActivityCreated();


    }

    @OnClick(R.id.btn_add_evidencias)
    public void onClickAddMultimedia() {
        repositorioViewImpl.onClickAddMultimedia();
    }

    @Override
    protected String getLogTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected InfoRubroPresenter getPresenter() {
        RepositorioRepository repositorioRepository = new RepositorioRepository(new RepositorioLocalDataSource(),
                new RepositorioPreferentsDataSource(),
                new RepositorioRemoteDataSource(getContext()));

        baseRepositorioPresenter = new RepositorioArchivoRubroPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new DowloadImageUseCase(repositorioRepository),
                new ConvertirPathRepositorioUpload(),
                new UploadRepositorio(repositorioRepository),
                new GetUrlRepositorioArchivo(repositorioRepository),
                new UpdateRepositorio(repositorioRepository,
                        new SiliCompressor(getContext())),
                new CloneImagenCompress(SiliCompressor.with(getContext()), getContext()));

        InfoRubroRepository infoRubroRepository = new InfoRubroRepository(new InfoRubroDataLocalSource());
        presenter = new InfoRubroPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetComentarioPred(infoRubroRepository),
                new GetArchivoComentario(infoRubroRepository),
                new SaveComentario(infoRubroRepository),
                new DeleteComentario(infoRubroRepository),
                new SaveArchivoRubro(infoRubroRepository),
                new DeleteArchivoComentario(infoRubroRepository));
        return presenter;
    }

    @Override
    protected InfoComentarioDialog getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_info_comentario_rubro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnRetroceder.setOnClickListener(this);
        setupAdapter();
        initEditText();
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }


    private void setupAdapter() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycomentarios.setLayoutManager(layoutManager);
        comentarioPredeAdapter = new ComentarioPredeAdapter(this);
        recycomentarios.setAdapter(comentarioPredeAdapter);
        recycomentarios.setNestedScrollingEnabled(false);

        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        ArchivoComentarioColumnCountProvider columnCountProvider = new ArchivoComentarioColumnCountProvider(getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        recRubroArchivo.setLayoutManager(autoColumnGridLayoutManager);
        repositorioAdapter = new RepositorioAdapter(this, null, recRubroArchivo, true);
        recRubroArchivo.setAdapter(repositorioAdapter);
        recRubroArchivo.setHasFixedSize(true);
        recRubroArchivo.setNestedScrollingEnabled(false);

        if(repositorioItemDecoration==null){
            repositorioItemDecoration = new RepositoriotemDecoration(700);
            recRubroArchivo.addItemDecoration(repositorioItemDecoration);
        }
    }


    private void setRepositorioAchivoView(RepositorioArchivoViewImpl repositorioViewImpl) {
        this.repositorioViewImpl = repositorioViewImpl;
        this.repositorioViewImpl.setAdapterArchivo(repositorioAdapter);
    }


    @Override
    public void showListComentarios(List<MensajeUi> mensajeUiList) {
        comentarioPredeAdapter.setMensajesList(mensajeUiList);
    }

    @Override
    public void showListComentariosArchivos(List<ArchivoComentarioUi> objects) {
        repositorioViewImpl.changeList(new ArrayList<RepositorioFileUi>(objects));
    }

    @Override
    public void cerrarDialog(String evaluacionProcesoId) {
        dismiss();
        if(listener!=null)listener.cerrarDialogComentario(evaluacionProcesoId);
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
    public void clearTextInpuComentario() {
        inputComentario.setText(null);
        inputComentario.setSelected(false);
        inputComentario.clearFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(inputComentario.getWindowToken(), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        baseRepositorioPresenter.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        baseRepositorioPresenter.onDestroyView();
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEnviar:
                presenter.saveComentario(String.valueOf(inputComentario.getText()));
                break;
            default:
                presenter.closeDialog();
                break;
        }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        repositorioViewImpl.onActivityResult(requestCode, resultCode, data, getContext());

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
}
