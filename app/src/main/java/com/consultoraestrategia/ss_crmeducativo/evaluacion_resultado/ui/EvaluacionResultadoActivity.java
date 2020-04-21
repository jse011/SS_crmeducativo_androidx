package com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui.EvaluacionResultadoAbstractFragment.ARG_ID_RUBRO_EVAL_RESULTADO;
import static com.consultoraestrategia.ss_crmeducativo.evaluacion_resultado.ui.EvaluacionResultadoAbstractFragment.ARG_TIPO_COMPETENCIA;

public class EvaluacionResultadoActivity extends AppCompatActivity implements BaseFragmentListener {

    private static final String TAG = EvaluacionResultadoActivity.class.getSimpleName();
    @BindView(R.id.edtRubro)
    TextView edtRubro;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void launch(Context context, Bundle args, int idRubroEvalResultado) {
        Intent intent = new Intent(context, EvaluacionResultadoActivity.class);
        args.putInt(ARG_ID_RUBRO_EVAL_RESULTADO, idRubroEvalResultado);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    public static void launch(Context context, Bundle args, int idRubroEvalResultado, int tipoCompetencia) {
        Intent intent = new Intent(context, EvaluacionResultadoActivity.class);
        args.putInt(ARG_ID_RUBRO_EVAL_RESULTADO, idRubroEvalResultado);
        args.putInt(ARG_TIPO_COMPETENCIA, tipoCompetencia);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion_resultado);
        ButterKnife.bind(this);
        setupToolbar();
        setupFragment(getIntent().getExtras());
    }

    private void setupFragment(Bundle extras) {
        EvaluacionResultadoFragment fragment = getEvaluacionResultadoFragment(extras);
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frm_content, fragment);
            ft.commitNow();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //change color of status bar
            Window window = this.getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor("#FAFAFA"));
        }


    }

    public void setTitulo(String titulo) {
        edtRubro.setText(titulo);
    }

    private EvaluacionResultadoFragment getEvaluacionResultadoFragment(Bundle extras) {
        if (extras == null) return null;
        int idRubroEvalResultado = extras.getInt(ARG_ID_RUBRO_EVAL_RESULTADO);
        int tipoCompetencia = extras.getInt(ARG_TIPO_COMPETENCIA);
        EvaluacionResultadoFragment fragment = null;
        try {
            fragment = EvaluacionResultadoAbstractFragment.newInstance(EvaluacionResultadoFragment.class, idRubroEvalResultado, tipoCompetencia, extras);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return fragment;
    }
}
