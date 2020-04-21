package com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.holder;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.comentarios.adapter.ComentarioListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComentariosHolderDia extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static String TAG = ComentarioHolderReceptor.class.getSimpleName();


    @BindView(R.id.textDia)
    TextView textDia;

    ComentarioListener listener;

    public ComentariosHolderDia(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Long diaComentario){
        try{
            Calendar calendarActual = Calendar.getInstance();
            int anhoActual = calendarActual.get(Calendar.YEAR);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(diaComentario);
            int anhio = calendar.get(Calendar.YEAR);

            if (anhio==anhoActual){
                if (calendarActual.get(Calendar.DAY_OF_MONTH)==calendar.get(Calendar.DAY_OF_MONTH))textDia.setText("Hoy");
                else {
                    if (calendarActual.get(Calendar.DAY_OF_MONTH)-1==calendar.get(Calendar.DAY_OF_MONTH))textDia.setText("Ayer");
                    else {
                        if(calendarActual.get(Calendar.DAY_OF_MONTH)-2==calendar.get(Calendar.DAY_OF_MONTH)||
                                calendarActual.get(Calendar.DAY_OF_MONTH)-3==calendar.get(Calendar.DAY_OF_MONTH)||
                                calendarActual.get(Calendar.DAY_OF_MONTH)-4==calendar.get(Calendar.DAY_OF_MONTH)||
                                calendarActual.get(Calendar.DAY_OF_MONTH)-5==calendar.get(Calendar.DAY_OF_MONTH)||
                                calendarActual.get(Calendar.DAY_OF_MONTH)-6==calendar.get(Calendar.DAY_OF_MONTH))
                            textDia.setText(Utils.f_dia_semana(diaComentario));
                        else {
                            textDia.setText(Utils.f_fecha_letras(diaComentario));
                        }
                    }

                }
            }else
                textDia.setText(Utils.getFechaDiaMesAnho(diaComentario));
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    @Override
    public void onClick(View v) {

    }
}
