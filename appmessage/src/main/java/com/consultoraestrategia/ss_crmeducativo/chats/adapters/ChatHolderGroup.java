package com.consultoraestrategia.ss_crmeducativo.chats.adapters;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;
import com.vanniktech.emoji.EmojiTextView;

import java.util.Calendar;
import java.util.Date;

public class ChatHolderGroup extends RecyclerView.ViewHolder {

    private ImageView circle;
    private TextView title_img;
    private TextView name;
    private TextView date;
    private EmojiTextView lastMessage;
    private ConstraintLayout root;
    private TextView txtTipo;
    private TextView unreadMessageCountTV;

    public ChatHolderGroup(@NonNull View itemView) {
        super(itemView);
        circle=(ImageView)itemView.findViewById(R.id.circle);
        title_img=(TextView)itemView.findViewById(R.id.title_img);
        name=(TextView)itemView.findViewById(R.id.name);
        date=(TextView)itemView.findViewById(R.id.date);
        lastMessage=(EmojiTextView)itemView.findViewById(R.id.lastMessage);
        root=(ConstraintLayout)itemView.findViewById(R.id.root);
        txtTipo = (TextView)itemView.findViewById(R.id.txt_tipo);
        unreadMessageCountTV = (TextView)itemView.findViewById(R.id.unreadMessageCountTV);
    }
    public void bind(final ChatUi chatUi,final ChatListener chatListener){
        if(chatUi.getCount()>0){
            unreadMessageCountTV.setVisibility(View.VISIBLE);
            unreadMessageCountTV.setText(String.valueOf(chatUi.getCount()));
            date.setTextColor(Color.parseColor("#0aea07"));
        }else {
            unreadMessageCountTV.setVisibility(View.GONE);
            date.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_500));
        }

        if(chatUi.getLastDate()!=null)date.setText(getHour(chatUi.getLastDate()));
        Typeface tf = Typeface.createFromAsset(name.getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        name.setTypeface(tf);
        name.setText(chatUi.getName());

        String texto = " " + chatUi.getLastMsg();
        lastMessage.setText(texto);
        Typeface tf1 = Typeface.createFromAsset(lastMessage.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        lastMessage.setTypeface(tf1);


        switch (chatUi.getTypeGroup()){
            case COURSE:
                String j="";
                if(!TextUtils.isEmpty(chatUi.getName())){
                    for(int i=0;chatUi.getName().length()>i; i++){
                        j=j+chatUi.getName().charAt(i);
                        if(i==2)break;
                    }
                }
                title_img.setText(j);
                title_img.setTextColor(Color.WHITE);
                Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.border_message);
                try {
                    drawable.mutate().setColorFilter(Color.parseColor(chatUi.getColor()), PorterDuff.Mode.SRC_ATOP);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                circle.setImageDrawable(drawable);
                break;
            case ACADEMIC:
                title_img.setText(chatUi.getImageRec());
                title_img.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.light_blank));
                circle.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.circle_backgroud));
                break;
            default:
                title_img.setText("");
                circle.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.group));
                break;

        }

        switch (chatUi.getTypePerson()){
            case TEACHERS:
                txtTipo.setText("General");
                break;
            case PARENTS:
                txtTipo.setText("Padres");
                break;
            case STUDENTS:
                txtTipo.setText("Alumnos");
                break;
        }

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatListener.clickChat(chatUi);
            }
        });
    }
    private String getHour(Date lastDate) {
        String hour;
        Calendar calendar= Calendar.getInstance();

        int hourToday= calendar.get(Calendar.HOUR_OF_DAY);
        int monthToday= calendar.get(Calendar.MONTH);
        int yearToday= calendar.get(Calendar.YEAR);
        int dayToday=  calendar.get(Calendar.DAY_OF_MONTH);


        calendar.setTime(lastDate);
        int hourOne= calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        int month= calendar.get(Calendar.MONTH);
        int year= calendar.get(Calendar.YEAR);
        int day=  calendar.get(Calendar.DAY_OF_MONTH);

        if(yearToday== year&& monthToday== month&& hourToday==hourOne){
            hour= hourOne%12 + ":" + ((minute<10) ? "0"+minute : minute)+ " " + ((hourOne>=12) ? "PM" : "AM");
        }
        else if(year== yearToday ){
            if(month==monthToday && day==dayToday-1){
                hour="Ayer";
            }else if(dayToday-1==0 && month==monthToday-1)  hour="Ayer";
            else  hour=day+"/"+(month+1)+"/"+year;
        }

        else hour=day+"/"+(month+1)+"/"+year;

        return hour;
    }
}
