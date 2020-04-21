package com.consultoraestrategia.ss_crmeducativo.graficos;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.utils.ProgresBarAnim;
import com.consultoraestrategia.ss_crmeducativo.utils.RadarMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by CCIE on 13/09/2017.
 */

public class OthersActivity extends AppCompatActivity {
    private static String TAG = OthersActivity.class.getSimpleName();

    @BindView(R.id.chart)
    RadarChart mChart;

    @BindView(R.id.morePrimerBimestre)
    TextView morePrimerBimestre;
    @BindView(R.id.moreSegundoBimestre)
    TextView moreSegundoBimestre;

    /*@BindView(R.id.moreTercerBimestre)
    TextView moreTercerBimestre;
    @BindView(R.id.moreCuartoBimestre)
    TextView moreCuartoBimestre;*/

    ArrayList<String> labels;
    @BindView(R.id.horizontal_progress_bar)
    ProgressBar progressBarOne;
    @BindView(R.id.icon)
    TextView textViewCountProgres;
    @BindView(R.id.progressBar2)
    ProgressBar progressBarTwo;
    @BindView(R.id.countProgress)
    TextView textViewCountProgressTwo;
    @BindView(R.id.PrimerBimestre)
    ProgressBar progressBarPrimer;
    @BindView(R.id.SegundoBimestre)
    ProgressBar progressBarSegundo;

   /* @BindView(R.id.TercerBimestre)
    ProgressBar progressBarTercer;
    @BindView(R.id.CuartoBimestre)
    ProgressBar progressBarCuarto;*/

    Handler handler = new Handler();


    /*ProgressBar*/

    @BindView(R.id.horizontal_progress_barTr)ProgressBar progressBarCapacidad1;
    @BindView(R.id.horizontal_progress_barTr2)ProgressBar progressBarCapacidad2;
    @BindView(R.id.horizontal_progress_barTr3)ProgressBar progressBarCapacidad3;
    @BindView(R.id.horizontal_progress_barTr4)ProgressBar progressBarCapacidad4;


    @BindView(R.id.idRadioGroup)
    RadioGroup radioGroup;

    private void radiGroup(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radioButtonOne:
                        Log.d(TAG,"radioButtonOne");
                        break;
                    case R.id.radioButtontwo:
                        Log.d(TAG,"radioButtonTwo");
                        break;
                }
            }
        });
    }


    private void initProgressBarcapacidad (){
        progressBarCapacidad1.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_yellow));
        progressBarCapacidad2.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_blue));
        progressBarCapacidad3.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_green));
        progressBarCapacidad4.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_red));


        ProgresBarAnim anim = new ProgresBarAnim(progressBarCapacidad1, 0, 40);
        ProgresBarAnim anim2 = new ProgresBarAnim(progressBarCapacidad2, 0, 60);
        ProgresBarAnim anim3 = new ProgresBarAnim(progressBarCapacidad3, 0, 70);
        ProgresBarAnim anim4 = new ProgresBarAnim(progressBarCapacidad4, 0, 80);
      //  ProgresBarAnim anim5 = new ProgresBarAnim(progressBarCapacidad5, 0, 80);
        anim.setDuration(7000);
        anim2.setDuration(7000);
        anim3.setDuration(7000);
        anim4.setDuration(7000);
        //anim5.setDuration(7000);
        // progressBarHorizontal.setProgress(40);
        progressBarCapacidad1.startAnimation(anim);
        progressBarCapacidad2.startAnimation(anim2);
        progressBarCapacidad3.startAnimation(anim3);
        progressBarCapacidad4.startAnimation(anim4);
       // progressBarCapacidad5.startAnimation(anim5);

    }

    private void initProgressBarBimestre() {

        progressBarPrimer.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_blue));
        progressBarSegundo.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_green));
      /*  progressBarTercer.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_orange));
        progressBarCuarto.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_yellow));*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Update the progress bar

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ProgresBarAnim anim = new ProgresBarAnim(progressBarPrimer, 0, 80);
                        ProgresBarAnim anim2 = new ProgresBarAnim(progressBarSegundo, 0, 60);
                        /*ProgresBarAnim anim3 = new ProgresBarAnim(progressBarTercer, 0, 70);
                        ProgresBarAnim anim4 = new ProgresBarAnim(progressBarCuarto, 0, 80);*/
                        anim.setDuration(7000);
                        anim2.setDuration(7000);
                        /*anim3.setDuration(7000);
                        anim4.setDuration(7000);*/
                        // progressBarHorizontal.setProgress(40);
                        progressBarPrimer.startAnimation(anim);
                        progressBarSegundo.startAnimation(anim2);
                       /* progressBarTercer.startAnimation(anim3);
                        progressBarCuarto.startAnimation(anim4);*/
                        // listener.scrollMoving(progressBarHorizontal);
                    }
                });
            }

        }).start();
    }

    private void iniciarProgressBar() {
        progressBarOne.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_yellow));
        progressBarOne.setMax(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 78; i++) {
                    try {
                        progressBarOne.setProgress(i);
                        Thread.sleep(300);
                        setTextOne(String.valueOf(i));
                        //1000 milliseconds is one second.
                        //textViewCountProgres.setText(i+"%");
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }

    private void iniciarProgressBarSecond() {
        progressBarTwo.setProgressDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.progress_bar_green));
        progressBarTwo.setMax(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 71; i++) {
                    try {
                        progressBarTwo.setProgress(i);
                        Thread.sleep(300);
                        setTextTwo(String.valueOf(i));
                        //1000 milliseconds is one second.
                        //textViewCountProgres.setText(i+"%");
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }


    private void setTextTwo(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewCountProgressTwo.setText(value + "%");
                textViewCountProgressTwo.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_green));
            }
        });
    }

    private void setTextOne(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewCountProgres.setText(value + "%");
                textViewCountProgres.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_yellow));
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_others);
        ButterKnife.bind(this);

        radiGroup();
        iniciarProgressBar();
        iniciarProgressBarSecond();
        initProgressBarBimestre();
        initProgressBarcapacidad();
        // mChart.setBackgroundColor(Color.rgb(255, 255, 255));
        // mChart.

        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.LTGRAY);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);


        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart
        mChart.setRotationEnabled(false);
        setData();


        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        //    xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"1.Proyección", "2.Agresividad", "3.Sin Balón", "4.Visión"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.DKGRAY);

        YAxis yAxis = mChart.getYAxis();

        yAxis.setLabelCount(4, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        // l.setTypeface(mTfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);


    }

    ArrayList<IRadarDataSet> sets;
    RadarDataSet set1, set2, set3, set4;
    ArrayList<RadarEntry> entries1, entries2, entries3, entries4;
    RadarData data;

    public void setData() {

        float mult = 80;
        float min = 20;
        int cnt = 4;

        //ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        entries1 = new ArrayList<RadarEntry>();
        //ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();
        entries2 = new ArrayList<RadarEntry>();
        entries3 = new ArrayList<RadarEntry>();
        entries4 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mult) + min;
            entries1.add(new RadarEntry(val1));

            float val2 = (float) (Math.random() * mult) + min;
            entries2.add(new RadarEntry(val2));

            float val3 = (float) (Math.random() * mult) + min;
            entries3.add(new RadarEntry(val3));

            float val4 = (float) (Math.random() * mult) + min;
            entries4.add(new RadarEntry(val4));


        }

        set1 = new RadarDataSet(entries1, "Bimestre I");
        set1.setColor(Color.rgb(50, 107, 242));
        set1.setFillColor(Color.rgb(156, 186, 255));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        set2 = new RadarDataSet(entries2, "Bimestre II");
        set2.setColor(Color.rgb(9, 183, 73));
        set2.setFillColor(Color.rgb(199, 255, 220));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        set3 = new RadarDataSet(entries3, "Bimestre III");
        set3.setColor(Color.rgb(255, 128, 0));
        set3.setFillColor(Color.rgb(255, 204, 153));
        set3.setDrawFilled(true);
        set3.setFillAlpha(180);
        set3.setLineWidth(2f);
        set3.setDrawHighlightCircleEnabled(true);
        set3.setDrawHighlightIndicators(false);

        set4 = new RadarDataSet(entries4, "Bimestre IV");
        set4.setColor(Color.rgb(255, 255, 0));
        set4.setFillColor(Color.rgb(255, 255, 153));
        set4.setDrawFilled(true);
        set4.setFillAlpha(180);
        set4.setLineWidth(2f);
        set4.setDrawHighlightCircleEnabled(true);
        set4.setDrawHighlightIndicators(false);


        //   ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();

        sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);
        /*sets.add(set3);
        sets.add(set4);*/

        //RadarData data = new RadarData(sets);
        data = new RadarData(sets);
        //  data.setValueTypeface(mTfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.DKGRAY); // color de los resultads

        mChart.setData(data);
        mChart.invalidate();
    }

    Boolean onClick = true;
    @OnClick(R.id.morePrimerBimestre)
    public void onClickMore() {
        if (onClick) {
            morePrimerBimestre.setText("hide");
           /* for (IDataSet<?> set : mChart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());
            set1.isDrawValuesEnabled();*/
            set1.setDrawValues(true);
            mChart.invalidate();
            onClick = false;
        } else {
            onClick = true;
           /* for (IDataSet<?> set : mChart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());*/
            set1.setDrawValues(false);
            mChart.invalidate();
            morePrimerBimestre.setText("Ver");
        }
    }

    Boolean onClickTwo = true;
    @OnClick(R.id.moreSegundoBimestre)
    public void onClickMoretwo() {
        if (onClickTwo) {
            moreSegundoBimestre.setText("hide");
            set2.setDrawValues(true);
            mChart.invalidate();
            onClickTwo = false;
        } else {
            onClickTwo = true;
            set2.setDrawValues(false);
            mChart.invalidate();
            moreSegundoBimestre.setText("Ver");
        }
    }

//    Boolean onClickThree = true;
//    @OnClick(R.id.moreTercerBimestre)
//    public void onClickMoreThree() {
//        if (onClickThree) {
//            moreTercerBimestre.setText("hide");
//            set3.setDrawValues(true);
//            mChart.invalidate();
//            onClickThree = false;
//        } else {
//            onClickThree = true;
//            set3.setDrawValues(false);
//            mChart.invalidate();
//            moreTercerBimestre.setText("Ver");
//        }
//    }
//    Boolean onClickFour = true;
//    @OnClick(R.id.moreCuartoBimestre)
//    public void onClickFour() {
//        if (onClickFour) {
//            moreCuartoBimestre.setText("hide");
//            set4.setDrawValues(true);
//            mChart.invalidate();
//            onClickFour = false;
//        } else {
//            onClickFour = true;
//            set4.setDrawValues(false);
//            mChart.invalidate();
//            moreCuartoBimestre.setText("Ver");
//        }
//    }


    Boolean statusPrimer = true;

    @OnClick(R.id.PrimerBimestreClick)
    public void OnClickPrimerBimestre() {
        Log.d(TAG, "PrimerBimestreClick");
        if (statusPrimer) {
            // statusPrimer = false;
//            Log.d(TAG,"ifstatus");
//            statusPrimer = false;
//            sets.add(set2);
//            data = new RadarData(sets);
//            //  data.setValueTypeface(mTfLight);
//            data.setValueTextSize(8f);
//            data.setDrawValues(false);
//            data.setValueTextColor(Color.DKGRAY); // color de los resultads
//
//            mChart.bind(data);
//            mChart.invalidate();
            statusPrimer = false;

            set1.setDrawFilled(false);
            //set1.setHighlightEnabled(false);
            // sets.remove(set1);

            mChart.invalidate();
            // sets.add(set1);

        } else {
           /* Log.d(TAG,"else");

            sets.remove(set2);*/
            statusPrimer = true;
            set1.setDrawFilled(true);
            set1.setHighlightEnabled(true);


            //sets.remove(set1);
            // sets.add(set1);



            mChart.invalidate();
        }
    }

    Boolean statusSegundo = true;

    @OnClick(R.id.SegundoBimestreClick)
    public void OnClickSegundoBimestre() {
        Log.d(TAG, "PrimerBimestreClick");
        if (statusSegundo) {
            statusSegundo = false;
            Log.d(TAG, "ifstatus");
           /* statusSegundo = false;
            sets.add(set2);
            data = new RadarData(sets);
            //  data.setValueTypeface(mTfLight);
            data.setValueTextSize(8f);
            data.setDrawValues(false);
            data.setValueTextColor(Color.DKGRAY); // color de los resultads

            mChart.bind(data);*/
            //set2.setDrawFilled(false);

            sets.remove(set2);
            mChart.invalidate();

        } else {
           /* Log.d(TAG,"else");

            sets.remove(set2);*/
           /* statusSegundo = true;
            set2.setDrawFilled(true);*/
            statusSegundo = true;
            sets.add(set2);
            mChart.invalidate();
        }
    }

    Boolean statusTercer = true;

 //   @OnClick(R.id.tercerBimestreClick)
    public void OnClickTercerBimestre() {
        Log.d(TAG, "PrimerBimestreClick");
        if (statusTercer) {
         /*   Log.d(TAG,"ifstatus");

            sets.add(set3);
            data = new RadarData(sets);
            //  data.setValueTypeface(mTfLight);
            data.setValueTextSize(8f);
            data.setDrawValues(false);
            data.setValueTextColor(Color.DKGRAY); // color de los resultads

            mChart.bind(data);
            mChart.invalidate();*/
          /*  statusTercer = false;
            set3.setDrawFilled(false);*/
            statusTercer = false;
            sets.remove(set3);
            mChart.invalidate();
        } else {
           /* Log.d(TAG,"else");

            sets.remove(set3);*/
          /*  statusTercer = true;
            set3.setDrawFilled(true);*/
            statusTercer = true;
            sets.add(set3);
            mChart.invalidate();

        }
    }

    Boolean statusCuarto = true;

   // @OnClick(R.id.cuartoBimestreClick)
    public void OnClickCuartoBimestre() {
        Log.d(TAG, "PrimerBimestreClick");
        if (statusCuarto) {
            statusCuarto = false;
           /* Log.d(TAG,"ifstatus");
            statusCuarto = false;
            sets.add(set4);
            data = new RadarData(sets);
            //  data.setValueTypeface(mTfLight);
            data.setValueTextSize(8f);
            data.setDrawValues(false);
            data.setValueTextColor(Color.DKGRAY); // color de los resultads

            mChart.bind(data);*/
            //set4.setDrawFilled(false);
            sets.remove(set4);
            mChart.invalidate();
        } else {
            Log.d(TAG, "else");
          /*
            sets.remove(set4);*/
           /* statusCuarto = true;
            set4.setDrawFilled(true);*/
            statusCuarto = true;
            sets.add(set4);
            mChart.invalidate();
        }
    }
}
