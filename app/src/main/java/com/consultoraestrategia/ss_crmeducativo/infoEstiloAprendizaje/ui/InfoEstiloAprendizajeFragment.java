package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.base.dialogFragment.BaseDialogFragment;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.InfoEstiloAprendizajePresenter;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.InfoEstiloAprendizajePresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.InstrumentoAprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.LeyendaAdapter;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.adapter.ResultadoEstiloAprendizajeAdapter;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source.InfoEstiloAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source.local.InfoEstiloAprendizajeLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.GraficoEstilosAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.InstrumentoObservadoUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.LeyendaUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.listener.InfoEstiloAprendizajeListener;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.usecase.GetListDimensionObse;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.RadarMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class InfoEstiloAprendizajeFragment extends BaseDialogFragment<InfoEstiloAprendizajeView, InfoEstiloAprendizajePresenter, InfoEstiloAprendizajeListener> implements InfoEstiloAprendizajeView {
    public static final String ALUMNOID = "InfoEstiloAprendizajeFragment.alumnoId";
    public static final String ENTIDADID = "InfoEstiloAprendizajeFragment.entidadId";
    public static final String GEOREFERENCIAID = "InfoEstiloAprendizajeFragment.georeferenciaId";
    @BindView(R.id.img_alumn_profile)
    CircleImageView imgAlumnProfile;
    @BindView(R.id.text_alumn_name)
    TextView textAlumnName;
    @BindView(R.id.text_alumn_lastname)
    TextView textAlumnLastname;
    @BindView(R.id.rc_estilo_aprendizaje)
    RecyclerView rcEstiloAprendizaje;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(R.id.tab_one_container)
    ConstraintLayout tabOneContainer;
    @BindView(R.id.rc_precision)
    RecyclerView rcPrecision;
    @BindView(R.id.recyclerLeyenda)
    RecyclerView recyclerLeyenda;
    @BindView(R.id.tab_two_container)
    ConstraintLayout tabTwoContainer;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(R.id.tab_host)
    TabHost tabHost;
    @BindView(R.id.txt_evaluacion)
    TextView txtEvaluacion;
    @BindView(R.id.chart)
    RadarChart chart;
    @BindView(R.id.textView96)
    TextView textView96;
    @BindView(R.id.txt_empty_list_aprendizaje)
    TextView txtEmptyListAprendizaje;

    private InstrumentoAprendizajeAdapter adapter;
    private LeyendaAdapter leyendaAdapter;
    private ResultadoEstiloAprendizajeAdapter adapterResultado;

    public static InfoEstiloAprendizajeFragment newInstance(int alumnoId, int entidadId, int georeferenciaId) {
        Bundle args = new Bundle();
        args.putInt(ALUMNOID, alumnoId);
        args.putInt(ENTIDADID, entidadId);
        args.putInt(GEOREFERENCIAID, georeferenciaId);
        InfoEstiloAprendizajeFragment fragment = new InfoEstiloAprendizajeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return getClass().getSimpleName();
    }

    @Override
    protected InfoEstiloAprendizajePresenter getPresenter() {
        return new InfoEstiloAprendizajePresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                getResources(),
                new GetListDimensionObse(InfoEstiloAprendizajeRepository.getInstance(
                        new InfoEstiloAprendizajeLocalDataSource(
                                InjectorUtils.provideDimensionObservadaDao(),
                                InjectorUtils.provideInstrumentoObservadaDao(),
                                InjectorUtils.providePersonaDao()))));
    }

    @Override
    protected InfoEstiloAprendizajeView getBaseView() {
        return this;
    }

    @Override
    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.layout_info_estilo_evaluacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setupTabHost();
        setupAdapter();
        //initGraphic();
        this.getDialog().requestWindowFeature(STYLE_NO_TITLE);
        super.onViewCreated(view, savedInstanceState);

    }

    private void setupTabHost() {

        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("TEST");
        spec.setContent(R.id.tab_one_container);
        spec.setIndicator("TEST");
        tabHost.addTab(spec);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("RESULTADOS");
        spec2.setContent(R.id.tab_two_container);
        spec2.setIndicator("RESULTADOS");
        tabHost.addTab(spec2);

        TabHost.TabSpec spec3 = tabHost.newTabSpec("GRAFICO");
        spec3.setContent(R.id.tab_three_container);
        spec3.setIndicator("GRAFICO");
        tabHost.addTab(spec3);

        if (getContext() != null) {
            tabHost.getTabWidget().getChildAt(0).getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            tabHost.getTabWidget().getChildAt(1).getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            tabHost.getTabWidget().getChildAt(2).getBackground().mutate().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        }
    }

    private void setupAdapter() {
        adapter = new InstrumentoAprendizajeAdapter(new ArrayList<InstrumentoObservadoUi>());
        rcEstiloAprendizaje.setAdapter(adapter);
        rcEstiloAprendizaje.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterResultado = new ResultadoEstiloAprendizajeAdapter(new ArrayList<Object>());
        rcPrecision.setLayoutManager(new LinearLayoutManager(getContext()));
        rcPrecision.setAdapter(adapterResultado);

        leyendaAdapter = new LeyendaAdapter(new ArrayList<LeyendaUi>());
        recyclerLeyenda.setAdapter(leyendaAdapter);
        recyclerLeyenda.setLayoutManager(new LinearLayoutManager(getContext()));
    }



    @Override
    protected ViewGroup getRootLayout() {
        return null;
    }

    @Override
    protected ProgressBar getProgressBar() {
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setCabecera(AlumnoUi alumnoUi) {
        textAlumnName.setText(alumnoUi.getNombre());
        textAlumnLastname.setText(alumnoUi.getApellido());
        Glide.with(this)
                .load(alumnoUi.getImagen())
                .apply(Utils.getGlideRequestOptions(R.drawable.ic_account_circle))
                .into(imgAlumnProfile);
    }

    @Override
    public void setListTestEvalaucion(List<InstrumentoObservadoUi> instrumentoObservadoUiList) {
        if(instrumentoObservadoUiList.isEmpty()){
            txtEmptyListAprendizaje.setVisibility(View.VISIBLE);
        }else {
            txtEmptyListAprendizaje.setVisibility(View.GONE);
        }
        Log.d(getClass().getSimpleName(), "Size: "+ instrumentoObservadoUiList.size());
        adapter.setList(instrumentoObservadoUiList);
    }

    @Override
    public void setCabeceraResultadoEvalaucion(InstrumentoObservadoUi instrumentoObservadoUi) {
        txtEvaluacion.setText(instrumentoObservadoUi.getNombreEvaluacion());
    }

    @Override
    public void setListResultadoEvaluacion(List<Object> objectsList) {
        adapterResultado.setList(objectsList);
    }


    private RadarDataSet getDataSet(ArrayList<RadarEntry> radarEntries, String nombre, String mcolor) {
        RadarDataSet set1 = new RadarDataSet(radarEntries, nombre);
        int color = Color.WHITE;
        try {
            color = Color.parseColor(mcolor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        set1.setColor(color);
        set1.setFillColor(color);
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);
        set1.setDrawValues(true);
        return set1;
    }


    @Override
    public void showGraficoAprendizaje(final GraficoEstilosAprendizajeUi aprendizajeUi) {

        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries3 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries4 = new ArrayList<RadarEntry>();
        ArrayList<LeyendaUi> leyendaUis = new ArrayList<>();

        RadarDataSet set1 = getDataSet(entries1, aprendizajeUi.getEstiloApUno(), aprendizajeUi.getColorDimenUno());
        RadarDataSet set2 = getDataSet(entries2, aprendizajeUi.getEstiloApDos(), aprendizajeUi.getColorDimenDos());
        RadarDataSet set3 = getDataSet(entries3, aprendizajeUi.getEstiloApTres(), aprendizajeUi.getColorDimenTres());
        RadarDataSet set4 = getDataSet(entries4, aprendizajeUi.getEstiloApCuatro(), aprendizajeUi.getColorDimenCuatro());

        LeyendaUi leyendaUi1 = new LeyendaUi(aprendizajeUi.getEstiloApUno(), aprendizajeUi.getColorDimenUno());
        LeyendaUi leyendaUi2 = new LeyendaUi(aprendizajeUi.getEstiloApDos(), aprendizajeUi. getColorDimenDos());
        LeyendaUi leyendaUi3 = new LeyendaUi(aprendizajeUi.getEstiloApTres(), aprendizajeUi.getColorDimenTres());
        LeyendaUi leyendaUi4 = new LeyendaUi(aprendizajeUi.getEstiloApCuatro(), aprendizajeUi.getColorDimenCuatro());
        leyendaUis.add(leyendaUi1);
        leyendaUis.add(leyendaUi2);
        leyendaUis.add(leyendaUi3);
        leyendaUis.add(leyendaUi4);


        entries1.add(new RadarEntry(aprendizajeUi.getPuntoDimenUno()));
        entries1.add(new RadarEntry(aprendizajeUi.getPuntoDimenDos()));
        entries1.add(new RadarEntry(0));
        entries1.add(new RadarEntry(0));

        entries2.add(new RadarEntry(0));
        entries2.add(new RadarEntry(aprendizajeUi.getPuntoDimenDos()));
        entries2.add(new RadarEntry(aprendizajeUi.getPuntoDimenTres()));
        entries2.add(new RadarEntry(0));

        entries3.add(new RadarEntry(0));
        entries3.add(new RadarEntry(0));
        entries3.add(new RadarEntry(aprendizajeUi.getPuntoDimenTres()));
        entries3.add(new RadarEntry(aprendizajeUi.getPuntoDimenCuatro()));


        entries4.add(new RadarEntry(aprendizajeUi.getPuntoDimenUno()));
        entries4.add(new RadarEntry(0));
        entries4.add(new RadarEntry(0));
        entries4.add(new RadarEntry(aprendizajeUi.getPuntoDimenCuatro()));

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
        sets.add(set4);

        chart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(10f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private String[] mActivities = new String[]{
                    aprendizajeUi.getDimenUno(),
                    aprendizajeUi.getDimenDos(),
                    aprendizajeUi.getDimenTres(),
                    aprendizajeUi.getDimenCuatro()};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });

        xAxis.setTextColor(Color.DKGRAY);
        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(4, false);
        yAxis.setTextSize(10f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(30f);
        yAxis.setDrawLabels(false);

        chart.getDescription().setEnabled(false);
        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        MarkerView mv = new RadarMarkerView(getContext(), R.layout.radar_markerview);
        mv.setChartView(chart);
        chart.setMarker(mv);
        chart.setRotationEnabled(false);

       chart.getLegend().setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.VERTICAL);
//        l.setDrawInside(false);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(0f);
//        l.setTextColor(Color.WHITE);
//        l.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);


        leyendaAdapter.setList(leyendaUis);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(true);
        data.setValueTextColor(Color.DKGRAY);

        chart.setData(data);
        chart.invalidate();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        try {
            super.onAttach(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFinalMessageAceptCancel(CharSequence message, CharSequence messageTitle) {

    }
}
