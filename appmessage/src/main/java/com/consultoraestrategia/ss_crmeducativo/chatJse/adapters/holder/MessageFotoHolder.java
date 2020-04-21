package com.consultoraestrategia.ss_crmeducativo.chatJse.adapters.holder;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.card.MaterialCardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.adapters.MessageAdapter;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.LinkUtils;
import com.consultoraestrategia.ss_crmeducativo.utils.touchHelper.ItemTouchHelperViewHolder;
import com.vanniktech.emoji.EmojiTextView;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageFotoHolder extends RecyclerView.ViewHolder implements LinkUtils.OnClickListener, ItemTouchHelperViewHolder, View.OnLongClickListener, View.OnClickListener {

    @BindView(R2.id.img_compartir)
    ImageView imgCompartir;
    @BindView(R2.id.background_view_stub)
    ConstraintLayout backgroundViewStub;
    @BindView(R2.id.img_sender)
    ImageView imgSender;
    @BindView(R2.id.conten_imagen_sender)
    MaterialCardView contenImagenSender;
    @BindView(R2.id.textsender)
    EmojiTextView textsender;
    @BindView(R2.id.horaSen)
    TextView horaSen;
    @BindView(R2.id.contSender)
    LinearLayout contSender;
    @BindView(R2.id.img_reciver)
    ImageView imgReciver;
    @BindView(R2.id.conten_imagen_reciver)
    MaterialCardView contenImagenReciver;
    @BindView(R2.id.textreceiver)
    EmojiTextView textreceiver;
    @BindView(R2.id.horaRec)
    TextView horaRec;
    @BindView(R2.id.contReceiver)
    LinearLayout contReceiver;
    @BindView(R2.id.foreground_view)
    public LinearLayout foregroundView;

    private MessageUi2 messageUi2;
    private boolean seleccionado;
    private MessageAdapter.Listener listener;

    public MessageFotoHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(MessageUi2 messageUic, int personaId, MessageAdapter.Listener listener){
        this.messageUi2 = messageUic;
        this.seleccionado = false;
        this.listener = listener;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) foregroundView.getLayoutParams();

        Calendar calendar= Calendar.getInstance();
        calendar.setTime(messageUic.getFecha());

        final int hora=calendar.get(Calendar.HOUR_OF_DAY);
        int minuto= calendar.get(Calendar.MINUTE);
        String tiempo = hora+":"+minuto+ ""+( (hora>=12) ? "PM" : "AM");
        if(messageUic.getEmisorId()== personaId)
        {

            layoutParams.setMarginStart((int)Utils.convertDpToPixel(32, itemView.getContext()));
            layoutParams.setMarginEnd((int)Utils.convertDpToPixel(0, itemView.getContext()));
            foregroundView.setLayoutParams(layoutParams);

            contReceiver.setVisibility(View.GONE);
            contSender.setVisibility(View.VISIBLE);
            textsender.setText(messageUic.getMensaje());
            horaSen.setText(tiempo);

            textsender.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            textsender.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_black_1000));
            Log.d(MessageFotoHolder.class.getSimpleName(),"estado: " + messageUic.getEstado().toString());
            switch (messageUic.getEstado())
            {
                case CREADO:
                    horaSen.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_historia, 0);
                    break;
                case GUARDADO:
                    horaSen.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.check, 0);
                    break;
                case ENVIADO:
                    Drawable drawable = ContextCompat.getDrawable(itemView.getContext(), R.drawable.message_got_read_receipt_from_target);
                    ColorMatrix matrix = new ColorMatrix();
                    matrix.setSaturation(0);
                    ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                    drawable.mutate().setColorFilter(filter);
                    horaSen.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null);
                    break;
                case VISTO:
                    horaSen.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.message_got_read_receipt_from_target, 0);
                    break;
                default:
                    String mensaje = "Eliminaste este mensaje";
                    textsender.setText(mensaje);
                    textsender.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.md_grey_500));
                    textsender.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_block, 0, 0, 0);
                    break;
            }

            if(messageUic.getEstado()!= MessageUi2.ESTADO.ELIMINADO){
                imgSender.setImageDrawable(null);
                contenImagenSender.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(messageUi2.getImagenFcm())){
                   Glide.with(itemView.getContext())
                            .load(new File(messageUi2.getImagen()))
                            .thumbnail(0.25f)
                            .apply(Utils.getGlideRequestOptions()
                                    .override(85, 85)
                                    .dontAnimate()
                                    .centerCrop())
                           .into(imgSender);

                    Log.d(MessageFotoHolder.class.getSimpleName(), "Imagen: "+ messageUi2.getImagen() );
                }else {
                    //imgSender.setVisibility(View.VISIBLE);
                    Glide.with(imgSender)
                            .load(messageUi2.getImagenFcm())
                            .apply(Utils.getGlideRequestOptions()
                                    .centerCrop()
                                    .dontAnimate())
                            .into(imgSender);
                    //layPlaceholderSender.setVisibility(View.GONE);


                    Log.d(MessageFotoHolder.class.getSimpleName(), "ImagenFcm: "+ messageUi2.getImagenFcm() );
                }

            }else {
                imgSender.setImageDrawable(null);
                contenImagenSender.setVisibility(View.GONE);
            }


        }
        else
        {
            layoutParams.setMarginStart((int)Utils.convertDpToPixel(0, itemView.getContext()));
            layoutParams.setMarginEnd((int)Utils.convertDpToPixel(32, itemView.getContext()));
            foregroundView.setLayoutParams(layoutParams);

            contSender.setVisibility(View.GONE);
            contReceiver.setVisibility(View.VISIBLE);
            textreceiver.setText(messageUic.getMensaje());
            horaRec.setText(tiempo);

            if (messageUic.getEstado() == MessageUi2.ESTADO.ELIMINADO) {
                String mensaje = "Eliminaste este mensaje";
                textreceiver.setText(mensaje);
                textreceiver.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_500));
                textreceiver.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_block, 0, 0, 0);
            }else {
                textreceiver.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_black_1000));
                textreceiver.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            }



            if(messageUic.getEstado()!= MessageUi2.ESTADO.ELIMINADO){
                imgReciver.setImageDrawable(null);
                contenImagenReciver.setVisibility(View.VISIBLE);
                Log.d(getClass().getSimpleName(), "Imagen: "+ messageUi2.getImagen() );
                if(TextUtils.isEmpty(messageUi2.getImagenFcm())){
                    Glide.with(imgReciver)
                            .load(new File(messageUi2.getImagen()))
                            .thumbnail(0.25f)
                            .apply(Utils.getGlideRequestOptions()
                                    .override(85, 85)
                                    .dontAnimate()
                                    .centerCrop())
                            .into(imgReciver);

                    Log.d(getClass().getSimpleName(), "Imagen: "+ messageUi2.getImagen() );
                }else {
                    //imgSender.setVisibility(View.VISIBLE);
                    Glide.with(imgReciver)
                            .load(messageUi2.getImagenFcm())
                            .thumbnail(0.25f)
                            .apply(Utils.getGlideRequestOptions()
                                    .dontAnimate()
                                    .centerCrop())
                            .into(imgReciver);
                    //layPlaceholderSender.setVisibility(View.GONE);


                    Log.d(MessageFotoHolder.class.getSimpleName(), "ImagenFcm: "+ messageUi2.getImagenFcm() );
                }
            }else {
                imgReciver.setImageDrawable(null);
                contenImagenReciver.setVisibility(View.GONE);
            }
        }

        LinkUtils.autoLink(textreceiver, this);
        LinkUtils.autoLink(textsender, this);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        textreceiver.setOnLongClickListener(this);
        textsender.setOnLongClickListener(this);
        if(messageUic.isSelected()){
            itemView.setBackgroundColor(Color.parseColor("#5E90CAF9"));
        }else {
            itemView.setBackgroundColor(0);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        listener.onLongClick(messageUi2);
        return false;
    }



    @Override
    public void onLinkClicked(View v, String link) {
       Log.d("onLinkClicked", "link: " + link);
        listener.onClick(messageUi2);
    }

    @Override
    public void onClicked(View v) {
        Log.d("onClicked", "not Link: ");
        listener.onClick(messageUi2);
    }

    @Override
    public void onItemSelected() {
        //imgCompartir.setVisibility(View.VISIBLE);
        //backgroundViewStub.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onItemClear() {
        imgCompartir.setVisibility(View.GONE);
        //backgroundViewStub.setBackgroundColor(0);
        if(seleccionado)listener.onSeleccionar(messageUi2);
    }

    @Override
    public boolean canDrag() {
        return false;
    }

    @Override
    public boolean canSwipe() {
        return messageUi2!=null && messageUi2.getEstado()!= MessageUi2.ESTADO.ELIMINADO;
    }

    @Override
    public View backgroundView() {
        return foregroundView;
    }

    @Override
    public void setTranslationX(float dX, boolean isCurrentlyActive) {
        if(dX==0){
            imgCompartir.setVisibility(View.GONE);
        }else if(dX >60 && dX< 180){
            if(imgCompartir.getVisibility()!=View.VISIBLE)imgCompartir.setVisibility(View.VISIBLE);
            backgroundViewStub.setTranslationX(dX-100);
        }

        if(isCurrentlyActive){
            seleccionado = !(dX < 190);
        }

        Log.d("setTranslationX: ", "dx: " + dX);
    }

    @Override
    public void clearView() {
        backgroundViewStub.setTranslationX(0);
    }


    @Override
    public void onClick(View view) {
        listener.onClick(messageUi2);
    }
}
