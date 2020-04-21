package com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.lib.autoColumnGrid.AutoColumnGridLayoutManager;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.PresicionPresenter;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.PresicionPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source.PresicionRepository;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.data.source.local.LocalPresicionDataSource;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase.ConvertirTipoNotaTeclado;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase.GetValorTipoNotaPresicion;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.domin.usecase.ValidarTipoNotaTeclado;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.NotaCircularUi;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.entity.TecladoNumerico;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.listener.PresicionListener;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.adapter.DirectoPresicionAdapter;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.adapter.DirectoPresicionCountProvider;
import com.consultoraestrategia.ss_crmeducativo.presicionEvaluacion.ui.adapter.holder.DirectoPresicionHolder;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RecyclerItemClickListener;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PresicionDialogFragment extends BaseDialogFragment<PresicionView, PresicionPresenter, PresicionListener> implements PresicionView, RecyclerItemClickListener.RecyclerViewOnItemClickListener {
    public final static String VALORTIPONOTAID = "PresicionDialogFragment.valorTipoNota";
    public final static String COLOREVALUACION = "PresicionDialogFragment.color";
    public static final String RUBROEVALUACIONID = "PresicionDialogFragment.titulo_rubro";
    public static final String NOTA = "PresicionDialogFragment.nota";

    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(R.id.tab_one_container)
    ConstraintLayout tabOneContainer;
    @BindView(R.id.tab_two_container)
    ConstraintLayout tabTwoContainer;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(R.id.tab_host)
    TabHost tabHost;
    @BindView(R.id.root)
    ConstraintLayout root;
    @BindView(R.id.rc_precision)
    RecyclerView rcPrecision;
    @BindView(R.id.img_nota)
    CircleImageView imgNota;
    @BindView(R.id.txt_descrip_nota)
    TextView txtDescripNota;
    @BindView(R.id.txt_nota_asignada)
    TextView txtNotaAsignada;
    @BindView(R.id.txt_rango_nota)
    TextView txtRangoNota;
    @BindView(R.id.txt_rubro)
    TextView txtRubro;
    @BindView(R.id.txt_cal_valor_asignado)
    TextView txtCalValorAsignado;
    @BindView(R.id.txt_selector_nota)
    TextView txtSelectorNota;
    @BindView(R.id.txt_cuadro_color)
    TextView txtCuadroColor;

    private DirectoPresicionAdapter directoPresicionAdapter;

    public static PresicionDialogFragment newInstance(double nota, String rubroEvaluacionId, String valorTipoNota, String color) {
        Bundle args = new Bundle();
        args.putString(RUBROEVALUACIONID, rubroEvaluacionId);
        args.putString(VALORTIPONOTAID, valorTipoNota);
        args.putString(COLOREVALUACION, color);
        args.putDouble(NOTA, nota);
        PresicionDialogFragment fragment = new PresicionDialogFragment();
        fragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected String getLogTag() {
        return PresicionDialogFragment.class.getSimpleName();
    }

    @Override
    protected PresicionPresenter getPresenter() {
        return new PresicionPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()), getResources(),
                new GetValorTipoNotaPresicion(PresicionRepository.getInstance(
                        new LocalPresicionDataSource(InjectorUtils.provideRubroEvaluacionDao(),
                                InjectorUtils.provideEvaluacionProcesoDao(),
                                InjectorUtils.provideValorTipoNotaDao(),
                                InjectorUtils.provideTipoNotaDao())
                )),
                new ConvertirTipoNotaTeclado(),
                new ValidarTipoNotaTeclado());
    }

    @Override
    protected PresicionView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout_presicion_rubro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTabHost();
        setupAdapter();
    }

    private void setupAdapter() {
        directoPresicionAdapter = new DirectoPresicionAdapter(new ArrayList<NotaCircularUi>());
        AutoColumnGridLayoutManager autoColumnGridLayoutManager = new AutoColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        DirectoPresicionCountProvider columnCountProvider = new DirectoPresicionCountProvider(rcPrecision.getContext());
        autoColumnGridLayoutManager.setColumnCountProvider(columnCountProvider);
        rcPrecision.setLayoutManager(autoColumnGridLayoutManager);
        rcPrecision.setAdapter(directoPresicionAdapter);
        rcPrecision.addOnItemTouchListener(new RecyclerItemClickListener(rcPrecision, this));
    }

    private void setupTabHost() {

        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("Directo");
        spec.setContent(R.id.tab_one_container);
        spec.setIndicator("Directo");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Teclado");
        spec.setContent(R.id.tab_two_container);
        spec.setIndicator("Teclado");
        tabHost.addTab(spec);

        tabHost.getTabWidget().getChildAt(0).getBackground().mutate().setColorFilter(ContextCompat.getColor(tabHost.getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        tabHost.getTabWidget().getChildAt(1).getBackground().mutate().setColorFilter(ContextCompat.getColor(tabHost.getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
    }

    @Override
    protected ViewGroup getRootLayout() {
        return root;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void setListItems(List<NotaCircularUi> listItems) {
        Log.d("locuar", "tamaniol" + listItems.size());
        directoPresicionAdapter.setList(listItems);
    }

    @Override
    public void setValorNotaAsignado(String nota, String color) {
        txtNotaAsignada.setText(nota);
        try {
            txtCuadroColor.setBackgroundColor(Color.parseColor(color));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setImagenNota(String url) {
        Glide.with(this)
                .load(url)
                .apply(Utils.getGlideRequestOptionsSimple())
                .into(imgNota);
    }

    @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.90), (int) (size.y * 0.85));
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing

        super.onResume();
    }

    @Override
    public void setSelectorNota(String valor) {
        txtSelectorNota.setText(valor);
    }

    @Override
    public void setDescripcionNota(String descripcion) {
        txtDescripNota.setText(descripcion);
    }

    @Override
    public void setRangoNota(String rango) {
        txtRangoNota.setText(rango);
    }

    @Override
    public void setCalValorNota(String nota) {
        txtCalValorAsignado.setText(nota);
    }

    @Override
    public void setRubroEvaluacion(String nombre) {
        txtRubro.setText(nombre);
    }

    @Override
    public void setColorFondo(String color) {
        try {
            tabHost.getTabWidget().getChildAt(0).getBackground().mutate().setColorFilter( Color.parseColor(color), PorterDuff.Mode.MULTIPLY);
            tabHost.getTabWidget().getChildAt(1).getBackground().mutate().setColorFilter( Color.parseColor(color), PorterDuff.Mode.MULTIPLY);
            root.setBackgroundColor(Color.parseColor(color));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccessNota(double notaAnterior, double notaActual, String valorTipoNotaId, String rubroEvaluacionId) {
        listener.onSuccessNota(notaAnterior, notaActual, valorTipoNotaId, rubroEvaluacionId);
        dismiss();
    }


    @OnClick({R.id.btn_cal_1, R.id.btn_cal_2, R.id.btn_cal_3, R.id.btn_cal_borrar, R.id.btn_cal_4, R.id.btn_cal_5, R.id.btn_cal_6, R.id.btn_cal_7, R.id.btn_cal_8, R.id.btn_cal_9, R.id.btn_cal_punto, R.id.btn_cal_0, R.id.btn_cal_espacio, R.id.btn_cal_aceptar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cal_1:
                presenter.onClickCalNumero(TecladoNumerico.UNO);
                break;
            case R.id.btn_cal_2:
                presenter.onClickCalNumero(TecladoNumerico.DOS);
                break;
            case R.id.btn_cal_3:
                presenter.onClickCalNumero(TecladoNumerico.TRES);
                break;
            case R.id.btn_cal_borrar:
                presenter.onClickCalBorrar();
                break;
            case R.id.btn_cal_4:
                presenter.onClickCalNumero(TecladoNumerico.CUATRO);
                break;
            case R.id.btn_cal_5:
                presenter.onClickCalNumero(TecladoNumerico.CINCO);
                break;
            case R.id.btn_cal_6:
                presenter.onClickCalNumero(TecladoNumerico.SEIS);
                break;
            case R.id.btn_cal_7:
                presenter.onClickCalNumero(TecladoNumerico.SIETE);
                break;
            case R.id.btn_cal_8:
                presenter.onClickCalNumero(TecladoNumerico.OCHO);
                break;
            case R.id.btn_cal_9:
                presenter.onClickCalNumero(TecladoNumerico.NUEVE);
                break;
            case R.id.btn_cal_punto:
                presenter.onClickCalPunto();
                break;
            case R.id.btn_cal_0:
                presenter.onClickCalNumero(TecladoNumerico.CERO);
                break;
            case R.id.btn_cal_espacio:
                presenter.onClickCalEspacio();
                break;
            case R.id.btn_cal_aceptar:
                presenter.onClickCalAceptar();
                break;
        }
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View clickedView) {
        if (viewHolder instanceof DirectoPresicionHolder) {
            DirectoPresicionHolder directoPresicionHolder = (DirectoPresicionHolder) viewHolder;
            presenter.onClickDirectoPresicionItem(directoPresicionHolder.getNotaCircularUi());
        }
    }

    @Override
    public void onItemLongClick(RecyclerView.ViewHolder viewHolder, View clickedView) {

    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }


    @OnClick(R.id.img_home)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void showMessage(CharSequence error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}
