package com.consultoraestrategia.ss_crmeducativo.chats.adapters;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.view.ChatListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.vanniktech.emoji.EmojiTextView;

import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatHolder extends RecyclerView.ViewHolder {

    CircleImageView image_rec;
    TextView name;
    EmojiTextView lastmsg;
    TextView hour;
    RelativeLayout root;
    TextView unreadMessageCountTV;

    public ChatHolder(@NonNull View itemView) {
        super(itemView);
        image_rec=(CircleImageView)itemView.findViewById(R.id.image_rec);
        name=(TextView)itemView.findViewById(R.id.name);
        lastmsg=(EmojiTextView)itemView.findViewById(R.id.lastmsgchat);
        hour=(TextView)itemView.findViewById(R.id.hour);
        root=(RelativeLayout)itemView.findViewById(R.id.root);
        unreadMessageCountTV = (TextView)itemView.findViewById(R.id.unreadMessageCountTV);
    }
    public void bind(final ChatUi chatUi, final ChatListener chatListener){
        if(chatUi.getCount()>0){
            unreadMessageCountTV.setVisibility(View.VISIBLE);
            unreadMessageCountTV.setText(String.valueOf(chatUi.getCount()));
            hour.setTextColor(Color.parseColor("#0aea07"));
        }else {
            unreadMessageCountTV.setVisibility(View.GONE);
            hour.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_500));
        }
        name.setText(Html.fromHtml(chatUi.getName()));
        Typeface tf = Typeface.createFromAsset(name.getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        name.setTypeface(tf);
        String texto = " " + chatUi.getLastMsg();
        lastmsg.setText(texto);
        Typeface tf1 = Typeface.createFromAsset(lastmsg.getContext().getAssets(), "fonts/Roboto-Regular.ttf");
        lastmsg.setTypeface(tf1);
        String url= chatUi.getImageRec();

        if(TextUtils.isEmpty(chatUi.getImageRec())){
            Glide.with(image_rec)
                    .load(R.drawable.pic)
                    .into(image_rec);
        }else {
            Glide.with(image_rec)
                    .load(url)
                    .apply(Utils.getGlideRequestOptions(R.drawable.pic))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            image_rec.post(new Runnable() {
                                @Override
                                public void run() {
                                    Glide.with(image_rec)
                                            .load(R.drawable.pic)
                                            .into(image_rec);
                                }
                            });
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(image_rec);
        }

        switch (chatUi.getEstado())
        {
            case CREADO:
                lastmsg.setCompoundDrawablesRelativeWithIntrinsicBounds( R.drawable.ic_historia, 0, 0,0);
                break;
            case GUARDADO:
                lastmsg.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.check, 0, 0, 0);
                break;
            case ENVIADO:
                Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.message_got_read_receipt_from_target);
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);
                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                drawable.mutate().setColorFilter(filter);
                lastmsg.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null);
                break;
            case VISTO:
                lastmsg.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.message_got_read_receipt_from_target, 0, 0, 0);
                break;
            default:
                String mensaje = " Eliminaste este mensaje";
                lastmsg.setText(mensaje);
                lastmsg.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_grey_500));
                lastmsg.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_block, 0, 0, 0);
                break;
        }

        if(chatUi.getLastDate()!=null)hour.setText(getHour(chatUi.getLastDate()));
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
