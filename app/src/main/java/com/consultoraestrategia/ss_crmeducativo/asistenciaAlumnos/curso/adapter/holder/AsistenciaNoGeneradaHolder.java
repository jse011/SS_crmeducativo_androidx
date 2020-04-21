package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.adapter.holder;

import android.graphics.Color;
import android.graphics.Typeface;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.listener.AsistenciaCursoListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsistenciaNoGeneradaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.txt_dia_semana)
    TextView txtDiaSemana;
    @BindView(R.id.txt_dia_mes)
    TextView txtDiaMes;
    @BindView(R.id.txt_descripcion)
    TextView txtDescripcion;
    @BindView(R.id.txt_sub_descripcion)
    TextView txtSubDescripcion;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;

    AsistenciaCursoListener asistenciaCursoListener;
    FechaAsistenciaUi fechaAsistenciaUi;
    int getCalendarioPeriodoSelectId;
    int calendarioPeriodoId;

    public AsistenciaNoGeneradaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }



    public void bind(FechaAsistenciaUi fechaAsistenciaUi, AsistenciaCursoListener asistenciaCursoListener, int calendarioPeriodoId, int getCalendarioPeriodoSelectId) {
        this.asistenciaCursoListener = asistenciaCursoListener;
        this.fechaAsistenciaUi = fechaAsistenciaUi;
        this.getCalendarioPeriodoSelectId=getCalendarioPeriodoSelectId;
        this.calendarioPeriodoId=calendarioPeriodoId;
        txtDiaMes.setText(fechaAsistenciaUi.getDiaMes());
        txtDiaSemana.setText(fechaAsistenciaUi.getDiaSemana());
        txtDescripcion.setText("La asistencia no a sido registrada");
        txtSubDescripcion.setText("Presione para generar la asistencia");
        try {
            if (fechaAsistenciaUi.getParametroDisenioUi().getColor3()!=null)
            constraintLayout.setBackgroundColor(Color.parseColor(fechaAsistenciaUi.getParametroDisenioUi().getColor3()));
            else constraintLayout.setBackgroundColor(itemView.getContext().getResources().getColor(R.color.md_grey_500));
        }catch (Exception e){
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        Calendar actual = Calendar.getInstance();

        actual.setTimeInMillis(fechaAsistenciaUi.getFechaAsistencia());
        if(calendar.get(Calendar.YEAR) == actual.get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) == actual.get(Calendar.MONTH) &&
                calendar.get(Calendar.DAY_OF_MONTH) == actual.get(Calendar.DAY_OF_MONTH)){
            txtDiaMes.setTextColor(Color.parseColor(fechaAsistenciaUi.getParametroDisenioUi().getColor1()));
            txtDiaMes.setTypeface(Typeface.DEFAULT_BOLD);
            txtDiaSemana.setTextColor(Color.parseColor(fechaAsistenciaUi.getParametroDisenioUi().getColor1()));
            txtDiaSemana.setTypeface(Typeface.DEFAULT_BOLD);
        }else {
            txtDiaSemana.setTypeface(null, Typeface.NORMAL);
            txtDiaMes.setTypeface(null, Typeface.NORMAL);
        }

    }

    @Override
    public void onClick(View view) {
        //if (calendarioPeriodoId==getCalendarioPeriodoSelectId)
        //asistenciaCursoListener.showAsistenciaCursoItemDialog(fechaAsistenciaUi);

    }
}
