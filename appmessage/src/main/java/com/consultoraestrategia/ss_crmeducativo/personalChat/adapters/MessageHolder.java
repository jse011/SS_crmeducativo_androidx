package com.consultoraestrategia.ss_crmeducativo.personalChat.adapters;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;

import java.util.Calendar;

public class MessageHolder extends RecyclerView.ViewHolder {


    TextView textViewSender;
    TextView textViewReceiver;
    TextView horaSen;
    TextView horaRec;
    ConstraintLayout contSen;
    ConstraintLayout contRec;
    ImageView check;
    ImageView doublecheck;

    public MessageHolder(@NonNull View itemView) {
        super(itemView);

        textViewSender=(TextView) itemView.findViewById(R.id.textsender);
        textViewReceiver=(TextView) itemView.findViewById(R.id.textreceiver);
        horaSen=(TextView) itemView.findViewById(R.id.horaSen);
        horaRec=(TextView) itemView.findViewById(R.id.horaRec);
        /*contSen=(ConstraintLayout) itemView.findViewById(R.id.contSender);
        contRec=(ConstraintLayout) itemView.findViewById(R.id.contReceiver);
        check=(ImageView) itemView.findViewById(R.id.check);
        doublecheck=(ImageView) itemView.findViewById(R.id.doublecheck);*/
    }

    public void bind(MessageUi messageUic , int idSender){

        Calendar calendar= Calendar.getInstance();
        calendar.setTime(messageUic.getDate());
     //   int format12=calendar.get(Calendar.HOUR);
        int hora=calendar.get(Calendar.HOUR_OF_DAY);
        int minuto= calendar.get(Calendar.MINUTE);

        if(messageUic.getIdsender()== idSender)
        {
            contRec.setVisibility(View.GONE);
            contSen.setVisibility(View.VISIBLE);
            textViewSender.setText(messageUic.getMessage());
            horaSen.setText( hora+":"+minuto+ ""+( (hora>=12) ? "PM" : "AM") );
            switch (messageUic.getState())
            {
                case SEEN:

                    break;
                case RECEIVED:

                    break;
                default:
                    check.setVisibility(View.VISIBLE);
                    doublecheck.setVisibility(View.GONE);
                    break;
            }

        }
        else
        {
            contSen.setVisibility(View.GONE);
            contRec.setVisibility(View.VISIBLE);
            textViewReceiver.setText(messageUic.getMessage());
            horaRec.setText(hora+":"+minuto+ ""+( (hora>=12) ? "PM" : "AM") );
        }


    }
}
