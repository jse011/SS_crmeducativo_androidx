package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.adapter.holder;

import android.graphics.Color;
import android.graphics.Typeface;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.listener.AsistenciaCursoListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsistenciaGeneradaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.txt_dia_semana)
    TextView txtDiaSemana;
    @BindView(R.id.txt_dia_mes)
    TextView txtDiaMes;
    @BindView(R.id.cantPuntual)
    TextView textPuntual;
    @BindView(R.id.cantTarde)
    TextView textTarde;
    @BindView(R.id.cantAusente)
    TextView textAusente;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.progress_1)
    RoundCornerProgressBar progress1;
    @BindView(R.id.progress_2)
    RoundCornerProgressBar progress2;
    @BindView(R.id.progress_3)
    RoundCornerProgressBar progress3;

    AsistenciaCursoListener asistenciaCursoListener;
    FechaAsistenciaUi fechaAsistenciaUi;
    int getCalendarioPeriodoSelectId;
    int calendarioPeriodoId;

    public AsistenciaGeneradaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }



    public void bind(FechaAsistenciaUi fechaAsistenciaUi, AsistenciaCursoListener asistenciaCursoListener, int calendarioPeriodoId, int getCalendarioPeriodoSelectId) {
        this.asistenciaCursoListener = asistenciaCursoListener;
        this.fechaAsistenciaUi = fechaAsistenciaUi;
        this.getCalendarioPeriodoSelectId = getCalendarioPeriodoSelectId;
        this.calendarioPeriodoId = calendarioPeriodoId;

        txtDiaMes.setText(fechaAsistenciaUi.getDiaMes());
        txtDiaSemana.setText(fechaAsistenciaUi.getDiaSemana());

        try {
            if (fechaAsistenciaUi.getParametroDisenioUi()!=null)
                constraintLayout.setBackgroundColor(Color.parseColor(fechaAsistenciaUi.getParametroDisenioUi().getColor1()));
            else constraintLayout.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary));
        }catch (Exception e){
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        Calendar actual = Calendar.getInstance();

        if (fechaAsistenciaUi.getCantidadTotal()>0){


            textPuntual.setText(String.valueOf(fechaAsistenciaUi.getCantidadPuntual()));
            textTarde.setText(String.valueOf(fechaAsistenciaUi.getCantidadTarde()));
            textAusente.setText(String.valueOf(fechaAsistenciaUi.getCantidadAusente()));

        }


        actual.setTimeInMillis(fechaAsistenciaUi.getFechaAsistencia());
        if(calendar.get(Calendar.YEAR) == actual.get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) == actual.get(Calendar.MONTH) &&
                calendar.get(Calendar.DAY_OF_MONTH) == actual.get(Calendar.DAY_OF_MONTH)){
            if(fechaAsistenciaUi.getParametroDisenioUi()!=null){
                try{
                    txtDiaMes.setTextColor(Color.parseColor(fechaAsistenciaUi.getParametroDisenioUi().getColor1()));
                    txtDiaSemana.setTextColor(Color.parseColor(fechaAsistenciaUi.getParametroDisenioUi().getColor1()));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            txtDiaMes.setTypeface(Typeface.DEFAULT_BOLD);

            txtDiaSemana.setTypeface(Typeface.DEFAULT_BOLD);
        }else {
            txtDiaSemana.setTypeface(null, Typeface.NORMAL);
            txtDiaMes.setTypeface(null, Typeface.NORMAL);
        }


    }



    @Override
    public void onClick(View view) {
        asistenciaCursoListener.onClickAsistencia(fechaAsistenciaUi);
    }

}
