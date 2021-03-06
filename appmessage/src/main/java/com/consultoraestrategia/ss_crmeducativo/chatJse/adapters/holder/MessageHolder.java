package com.consultoraestrategia.ss_crmeducativo.chatJse.adapters.holder;

import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.webp.decoder.WebpDrawable;
import com.bumptech.glide.integration.webp.decoder.WebpDrawableTransformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.appmessage.R2;
import com.consultoraestrategia.ss_crmeducativo.chatJse.adapters.MessageAdapter;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.consultoraestrategia.ss_crmeducativo.utils.LinkUtils;
import com.consultoraestrategia.ss_crmeducativo.utils.touchHelper.ItemTouchHelperViewHolder;
import com.vanniktech.emoji.EmojiTextView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageHolder extends RecyclerView.ViewHolder implements LinkUtils.OnClickListener, ItemTouchHelperViewHolder, View.OnClickListener, View.OnLongClickListener {
    @BindView(R2.id.img_compartir)
    ImageView imgCompartir;
    @BindView(R2.id.background_view_stub)
    ConstraintLayout backgroundViewStub;
    @BindView(R2.id.barra_sender_replick)
    View barraSenderReplick;
    @BindView(R2.id.img_sender_replick)
    ImageView imgSenderReplick;
    @BindView(R2.id.txt_sender_replick)
    TextView txtSenderReplick;
    @BindView(R2.id.textsender_replick)
    EmojiTextView textsenderReplick;
    @BindView(R2.id.content_sender_replick)
    CardView contentSenderReplick;
    @BindView(R2.id.textsender)
    EmojiTextView textsender;
    @BindView(R2.id.horaSen)
    TextView horaSen;
    @BindView(R2.id.contSender)
    LinearLayout contSender;
    @BindView(R2.id.horaSenDelete)
    TextView horaSenDelete;
    @BindView(R2.id.contSenderEliminado)
    LinearLayout contSenderEliminado;
    @BindView(R2.id.barra_reciver_replick)
    View barraReciverReplick;
    @BindView(R2.id.img_receiver_replick)
    ImageView imgReceiverReplick;
    @BindView(R2.id.txt_reciver_replick)
    TextView txtReciverReplick;
    @BindView(R2.id.textreceiver_replick)
    EmojiTextView textreceiverReplick;
    @BindView(R2.id.contet_reciver_replick)
    CardView contetReciverReplick;
    @BindView(R2.id.textreceiver)
    EmojiTextView textreceiver;
    @BindView(R2.id.horaRec)
    TextView horaRec;
    @BindView(R2.id.contReceiver)
    LinearLayout contReceiver;
    @BindView(R2.id.horaRecDelete)
    TextView horaRecDelete;
    @BindView(R2.id.contReceiverEliminado)
    LinearLayout contReceiverEliminado;
    @BindView(R2.id.foreground_view)
    public LinearLayout foregroundView;
    private MessageUi2 messageUic;
    private MessageAdapter.Listener listener;
    private boolean seleccionado;

    public MessageHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(MessageUi2 messageUic, int personaId, MessageAdapter.Listener listener){
        this.listener = listener;
        this.messageUic = messageUic;
        this.seleccionado = false;
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(messageUic.getFecha()!=null?messageUic.getFecha():new Date());

        imgReceiverReplick.setImageDrawable(null);
        imgSenderReplick.setImageDrawable(null);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) foregroundView.getLayoutParams();

        int hora=calendar.get(Calendar.HOUR_OF_DAY);
        int minuto= calendar.get(Calendar.MINUTE);
        String tiempo = hora+":"+minuto+ ""+( (hora>=12) ? "PM" : "AM");
        if(messageUic.getEmisorId()== personaId)
        {

            layoutParams.setMarginStart((int) Utils.convertDpToPixel(32, itemView.getContext()));
            layoutParams.setMarginEnd((int) Utils.convertDpToPixel(0, itemView.getContext()));
            foregroundView.setLayoutParams(layoutParams);

            contReceiverEliminado.setVisibility(View.GONE);
            contReceiver.setVisibility(View.GONE);

            contSender.setVisibility(View.VISIBLE);
            contSenderEliminado.setVisibility(View.GONE);

            textsender.setText(messageUic.getMensaje());
            horaSen.setText(tiempo);
            textsender.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_black_1000));
            textsender.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
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
                    textsender.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_500));
                    textsender.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_block, 0, 0, 0);
                    contSender.setVisibility(View.GONE);
                    contSenderEliminado.setVisibility(View.VISIBLE);
                    horaSenDelete.setText(tiempo);
                    break;
            }

            //region Replick message
            if((!TextUtils.isEmpty(messageUic.getMensajeReplickId())||
                    !TextUtils.isEmpty(messageUic.getMensajeReplick())||
                    !TextUtils.isEmpty(messageUic.getImagenReplick()))&&
                    messageUic.getEstado()!= MessageUi2.ESTADO.ELIMINADO){
                contentSenderReplick.setVisibility(View.VISIBLE);
                textsenderReplick.setText(TextUtils.isEmpty(messageUic.getImagenReplick())?messageUic.getMensajeReplick():"\uD83D\uDCF7 Foto");


                if(messageUic.getPersonaIdReplick() == messageUic.getEmisorId()){
                    txtSenderReplick.setTextColor(ContextCompat.getColor(textsenderReplick.getContext(), R.color.colorPrimary));
                    txtSenderReplick.setText("Tú                       ");
                    barraSenderReplick.setBackgroundColor(ContextCompat.getColor(textsenderReplick.getContext(), R.color.colorPrimary));
                }else {
                    txtSenderReplick.setTextColor(Color.parseColor("#1A7264"));
                    txtSenderReplick.setText(messageUic.getPersonaReplick());
                    barraSenderReplick.setBackgroundColor(Color.parseColor("#1A7264"));
                }

                if(!TextUtils.isEmpty(messageUic.getImagenReplick())){
                    imgSenderReplick.setVisibility(View.VISIBLE);
                    Glide.with(imgSenderReplick)
                            .asDrawable()
                            .load(messageUic.getImagenReplick())
                            .thumbnail(0.25f)
                            .apply(Utils.getGlideRequestOptionsSimple()
                                    .centerCrop())
                            .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(new FitCenter()))
                            .skipMemoryCache(true)
                            //.into(imgSenderReplick);
                            .into(new CustomTarget<Drawable>() {
                                @Override
                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    imgSenderReplick.setImageDrawable(resource);
                                    if (resource instanceof Animatable) {
                                        ((Animatable)resource).stop();
                                    }
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                }
                            });
                }else {
                    imgSenderReplick.setVisibility(View.GONE);
                }

            }else {
                contentSenderReplick.setVisibility(View.GONE);
            }
            //endregion

        }
        else
        {

            layoutParams.setMarginStart((int) Utils.convertDpToPixel(0, itemView.getContext()));
            layoutParams.setMarginEnd((int) Utils.convertDpToPixel(32, itemView.getContext()));
            foregroundView.setLayoutParams(layoutParams);

            contSender.setVisibility(View.GONE);
            contSenderEliminado.setVisibility(View.GONE);
            contReceiver.setVisibility(View.VISIBLE);
            contReceiverEliminado.setVisibility(View.GONE);
            textreceiver.setText(messageUic.getMensaje());
            horaRec.setText(tiempo);

            if (messageUic.getEstado() == MessageUi2.ESTADO.ELIMINADO) {
                String mensaje = "Eliminaste este mensaje";
                textreceiver.setText(mensaje);
                textreceiver.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_grey_500));
                textreceiver.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_block, 0, 0, 0);
                contReceiver.setVisibility(View.GONE);
                contReceiverEliminado.setVisibility(View.VISIBLE);
                horaRecDelete.setText(tiempo);
            }else {
                textreceiver.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.md_black_1000));
                textreceiver.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
            }

            //region Replick message
            if(!TextUtils.isEmpty(messageUic.getMensajeReplickId())&&
                    messageUic.getEstado()!= MessageUi2.ESTADO.ELIMINADO){
                contetReciverReplick.setVisibility(View.VISIBLE);
                textreceiverReplick.setText(messageUic.getMensajeReplick());


                if(messageUic.getPersonaIdReplick() == messageUic.getEmisorId()){
                    txtReciverReplick.setTextColor(ContextCompat.getColor(textsenderReplick.getContext(), R.color.colorPrimary));
                    txtReciverReplick.setText("Tú                       ");
                    barraReciverReplick.setBackgroundColor(ContextCompat.getColor(textsenderReplick.getContext(), R.color.colorPrimary));
                }else {
                    txtReciverReplick.setTextColor(Color.parseColor("#1A7264"));
                    txtReciverReplick.setText(messageUic.getPersonaReplick());
                    barraReciverReplick.setBackgroundColor(Color.parseColor("#1A7264"));
                }

                if(!TextUtils.isEmpty(messageUic.getImagenReplick())){
                    imgReceiverReplick.setVisibility(View.VISIBLE);
                    Glide.with(imgReceiverReplick)
                            .asDrawable()
                            .load(messageUic.getImagenReplick())
                            .thumbnail(0.25f)
                            .apply(Utils.getGlideRequestOptionsSimple()
                                    .centerCrop())
                            //.asDrawable()
                            .optionalTransform(WebpDrawable.class, new WebpDrawableTransformation(new FitCenter()))
                            .skipMemoryCache(true)
                            //.into(imgReceiverReplick);
                            .into(new CustomTarget<Drawable>() {
                                @Override
                                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    imgReceiverReplick.setImageDrawable(resource);
                                    if (resource instanceof Animatable) {
                                        ((Animatable)resource).stop();
                                    }
                                }

                                @Override
                                public void onLoadCleared(@Nullable Drawable placeholder) {

                                }
                            });
                }else {
                    imgReceiverReplick.setVisibility(View.GONE);
                }

            }else {
                contetReciverReplick.setVisibility(View.GONE);
            }
            //endregion

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
    public void onLinkClicked(View v, String link) {
        Log.d("onLinkClicked", "link: " + link);
        listener.onClick(messageUic);
    }

    @Override
    public void onClicked(View v) {
        Log.d("onClicked", "not Link: ");
        listener.onClick(messageUic);
    }


    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {
        imgCompartir.setVisibility(View.GONE);
        //backgroundViewStub.setBackgroundColor(0);
        if(seleccionado)listener.onSeleccionar(messageUic);
    }

    @Override
    public boolean canDrag() {
        return false;
    }

    @Override
    public boolean canSwipe() {
        return messageUic!=null && messageUic.getEstado()!= MessageUi2.ESTADO.ELIMINADO;
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
    public boolean onLongClick(View view) {
        listener.onLongClick(messageUic);
        return true;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(messageUic);
    }
}
