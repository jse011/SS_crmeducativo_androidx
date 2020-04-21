package com.consultoraestrategia.ss_crmeducativo.comentarios.ui;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.BaseTabFragmentView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.ComentarioAdapter;
import com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.ComentarioListener;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.ComentariosRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.local.ComentariosLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.comentarios.data.source.remote.ComentariosRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.comentarios.domain.usecase.GetComentariosList;
import com.consultoraestrategia.ss_crmeducativo.comentarios.domain.usecase.SaveComentario;
import com.consultoraestrategia.ss_crmeducativo.comentarios.presenter.ComentarioPresenter;
import com.consultoraestrategia.ss_crmeducativo.comentarios.presenter.ComentarioPresenterImp;
import com.consultoraestrategia.ss_crmeducativo.comentarios.presenter.ComentarioView;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.view.TabsSesionesActivityV2;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ComentariosFragment extends Fragment implements BaseTabFragmentView, ComentarioView, ComentarioListener {

    private static String TAG = ComentariosFragment.class.getSimpleName();

    ComentarioPresenter presenter;
    ComentarioAdapter comentarioAdapter;
    @BindView(R.id.rvComentarios)
    RecyclerView rvComentarios;
    Unbinder unbinder;
    @BindView(R.id.textocomentario)
    TextView textocomentario;
    @BindView(R.id.txt_comentarios)
    TextView tituloComentarios;
    @BindView(R.id.com_editcomentario)
    EditText comEditcomentario;
    @BindView(R.id.btn_send)
    FloatingActionButton btnSend;

    private static int sessionAprendizajeId;
    private static int personaId;
    private static int backgroudColorId;
    private static int cargaCursoId;
    private static int cursoId;
    private static int cargaAcademicaId;
    private static int parametroDisenioId;
    private static SesionAprendizajeUi sesionAprendizajeUi;

    public static final String INT_SESSION_ID = "ComentariosFragment.SessionId";
    public static final String INT_CARGAR__CURSO_ID = "ComentariosFragment.CargaCursoId";
    public static final String UI_SESSION_APRENDIZAJE = "ComentariosFragment.SessionAprendizajeUI";
    public static final String INT_PERSONA_ID = "ComentariosFragment.PersonaId";
    public static final String INT_BACKGROUD_COLOR = "ComentariosFragment.BackgroudColor";
    public static final String INT_CURSO_ID = "ComentariosFragment.CursoId";
    public static final String INT_CARGA_ACADEMICA_ID = "ComentariosFragment.CargaAcademicaId";
    public static final String INT_PARAMETRO_DISENIO_ID = "ComentariosFragment.ParametorDisenioId";


    public static ComentariosFragment newInstanceSesions(SesionAprendizajeUi SesionAprendizajeId, int PersonaId, int backgroudColor, int CargaCursoId, int CursoId, int CargaAcademicaId, int ParametroDisenioId) {
        sesionAprendizajeUi = SesionAprendizajeId;
        sessionAprendizajeId = SesionAprendizajeId.getSesionAprendizajeId();
        personaId = PersonaId;
        backgroudColorId = backgroudColor;
        cargaCursoId = CargaCursoId;
        cursoId = CursoId;
        cargaAcademicaId = CargaAcademicaId;
        parametroDisenioId = ParametroDisenioId;
        ComentariosFragment fragment = new ComentariosFragment();
        Bundle b = new Bundle();
        b.putInt(INT_SESSION_ID, SesionAprendizajeId.getSesionAprendizajeId());
        b.putInt(INT_CARGAR__CURSO_ID, CargaCursoId);
        b.putSerializable(UI_SESSION_APRENDIZAJE, SesionAprendizajeId);
        b.putInt(INT_PERSONA_ID, personaId);
        b.putInt(INT_BACKGROUD_COLOR, backgroudColor);
        b.putInt(INT_CURSO_ID, CargaCursoId);
        b.putInt(INT_CURSO_ID, CursoId);
        b.putInt(INT_CARGA_ACADEMICA_ID, cargaAcademicaId);
        b.putInt(INT_PARAMETRO_DISENIO_ID, parametroDisenioId);
        fragment.setArguments(b);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.comentarios_fragments, container, false);
        initPresenter();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initPresenter() {
        ComentariosRepository repository = new ComentariosRepository(new ComentariosLocalDataSource(InjectorUtils.providePersonaDao()), new ComentariosRemoteDataSource());
        presenter = new ComentarioPresenterImp(new UseCaseHandler(new UseCaseThreadPoolScheduler()), new GetComentariosList(repository), new SaveComentario(repository));
        setPresenter(presenter);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setExtras(sessionAprendizajeId);
        initView();
        presenter.onViewCreated();
    }

    private void initView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //layoutManager.setReverseLayout(true);
        rvComentarios.setLayoutManager(layoutManager);
        comentarioAdapter = new ComentarioAdapter(this, new ArrayList<Object>(), rvComentarios);
        rvComentarios.setAdapter(comentarioAdapter);
        rvComentarios.setHasFixedSize(true);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
        Log.d(TAG, "onActivityCreated");
    }


    @Override
    public void onResumeFragment() {
        presenter.onResumeFragment();
    }

    @Override
    public void onclickCancel() {

    }

    @Override
    public void onclickComentar() {

    }

    @Override
    public void clickCreateComentario(Object item) {


    }

    @Override
    public void showListComentarios(List<Object> objectList) {
        comentarioAdapter.setComentarioList(objectList);
    }

    @Override
    public void clearComentarios() {
        comentarioAdapter.clearComentarios();
    }

    @Override
    public void hideComentario() {
        textocomentario.setVisibility(View.GONE);
    }

    @Override
    public void onSaveSucceFull() {
//        CallService.jobServiceExportarTipos(getContext(), TipoExportacion.MENSAJE);
//        SimpleSyncIntenService.start(getContext());
    }

    @Override
    public void setPresenter(ComentarioPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @OnClick({R.id.btn_send, R.id.backPress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                sendMessage();
                break;
            case R.id.backPress:
                finish();
                break;
        }
    }

    private void finish() {
        if(getActivity() instanceof TabsSesionesActivityV2){
            TabsSesionesActivityV2 tabsSesionesActivityV2 = (TabsSesionesActivityV2)getActivity();
            tabsSesionesActivityV2.salirFrameLayoutComentarios();
        }
        /*Intent intent = TabsSesionesActivityV2.createTabSesionesIntent(getContext(), TabsSesionesActivityV2.class, getArguments(), sesionAprendizajeUi, personaId, backgroudColorId, cargaCursoId, cursoId, cargaAcademicaId, parametroDisenioId);
        getContext().startActivity(intent);*/

    }

    private void sendMessage() {
        presenter.onClickSaveComentario(String.valueOf(comEditcomentario.getText()));
        comEditcomentario.setText("");
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(comEditcomentario.getWindowToken(), 0);
        onResume();
    }

}
